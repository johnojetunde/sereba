/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sereba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sereba.model.*;
import com.sereba.model.util.Util;
import com.sereba.model.util.SessionData;
import com.sereba.model.util.MailSender;
import com.sereba.model.util.StoreStaffHelper;
import com.sereba.model.util.UserHelper;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.xml.bind.PropertyException;

/**
 *
 * @author John
 */

@Controller
public class StaffController extends HttpServlet {
    private Util util = new Util();
    private StoreStaff staff = new StoreStaff();
    private MailSender mailSender = new MailSender();
    private StoreStaffHelper storestaffH = new StoreStaffHelper();
    private UserHelper userH = new UserHelper();
    private String error = "";
    private String BaseUrl = util.BaseUrl();
    
    @RequestMapping("/staff")
    public ModelAndView helloWorld() {
        
        String message = "<br><div style='text-align:center;'>"
                + "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from CrunchifyHelloWorld.java **********</div><br><br>";

        return new ModelAndView("index/welcome", "message", message);
    }
    
    
    @RequestMapping( value = "/staff/addstaff", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView addStaff(HttpServletRequest request, HttpServletResponse response)throws Exception {
         SessionData temp = new SessionData(request);        
         BaseUrl = temp.BaseUrl(request);
        HttpSession session= request.getSession(false);  
        error = "";
        if(session == null || session.getAttribute("fullname")== null){
         String url = BaseUrl+"login/index.html?redirect=staff/addstaff.html";
          return new ModelAndView("redirect:"+url);
        }
        boolean display = true;
        Map aclData =  (Map) session.getAttribute("acl");
       String creator_type = (String) session.getAttribute("userType");
        if(!creator_type.equalsIgnoreCase("user")){
        String restrictedAccess = BaseUrl+"error/noaccess.html";
            return  new ModelAndView("redirect:"+restrictedAccess);
        }
        
        String returnMessage = "";
        Map<String, String> map = new HashMap<String, String>();
        ModelAndView model = new ModelAndView("attendant/addstaff", "appname", "Sereba Accounting");
        try{
        StoreStaff storeStaff = (StoreStaff) session.getAttribute("staff");
        Integer creator_id = storeStaff.getStaffId(); // we get the id of the current logged in user
        User user = storeStaff.getUserId();
        creator_type = (String) session.getAttribute("userType");
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
        EntityManager em = emf.createEntityManager();
         em.getTransaction().begin();
         
          boolean isPost = "POST".equals(request.getMethod());
          if(isPost){
         String[] aclArray = request.getParameterValues("acl");
        
        for(int i=0; i<aclArray.length; i++){
            map.put(aclArray[i],aclArray[i]);
        }
        
        
       String acl = String.join(",", aclArray);
        
        error += storestaffH.addStaffValidation(request);
        if(error.isEmpty()){
            error += storestaffH.validateEmail(request);
            
        }
        if(error.isEmpty()){
            error += userH.validEmail(request);
        }
        
        //now we want to deal with the real data
        if(error.isEmpty()){
            String fullname = request.getParameter("fullname") != null ? request.getParameter("fullname") : "";
         String email = request.getParameter("email")!= null? request.getParameter("email"):"";
         String phone_no = request.getParameter("phone_no")!= null? request.getParameter("phone_no"):"";
         String password = request.getParameter("password") !=null ? request.getParameter("password") : "";
         String confirm_password = request.getParameter("confirm_password") != null ? request.getParameter("confirm_password") : "";
        
       
       
       
        staff.setAcl(acl);
        staff.setFullname(fullname);
        staff.setEmail(email);
        staff.setPhoneNumber(phone_no);
        staff.setPassword(util.md5(password));
        staff.setUserId(user);
        staff.setIsDeleted(0);
        staff.setTimeCreated(util.getCurrentTimeStamp());
        if(!em.contains(staff)){
            em.persist(staff);
        }
        
          em.getTransaction().commit();
          em.close();
          emf.close();
          
            if(!staff.toString().isEmpty()){
             String mailMessage = "Registration successful. "
                     + "Your details goes thus: <b>Username:"+email
                     + "Password: "+password+"</b>"
                     + "To login into your account <a href='"+BaseUrl+"'>click here, and check the staff account type</a>";
             mailSender.sendHtmlEmail(email, "no-reply@sereba_accounting.com", "Registration success", mailMessage);
             map.clear();
             returnMessage = util.successAlertReturn("Registration successful. An Email has been sent to the user with their log in details.", "addstaff");
            }else{
                returnMessage = util.errorAlertReturn("Unable to create staff account. Please try again");
            }
     
        
        
        }else{
            returnMessage = util.errorAlertReturn(error);
        }
      
         
          }
        
        }catch(Exception err){
            returnMessage = util.errorAlertReturn("System error: "+err.getMessage());
        }
        
        
        
        model.addObject("baseUrl",BaseUrl);
        model.addObject("returnMessage",returnMessage);
        model.addObject("acl",map);
        SessionData rSession = new SessionData(request);

        return model;
    }
    
    @RequestMapping("/staff/viewstaff")
	public ModelAndView viewStaff(HttpServletRequest request, HttpServletResponse response) throws Exception {
             SessionData temp = new SessionData(request);        
             BaseUrl = temp.BaseUrl(request);
             HttpSession session= request.getSession(false);  
        if(session == null || session.getAttribute("fullname")== null){
           String url = BaseUrl+"login/index.html?redirect=staff/viewstaff.html";
          return new ModelAndView("redirect:"+url);
        }
         Map aclData =  (Map) session.getAttribute("acl");
       
         String creator_type = (String) session.getAttribute("userType");
        if(!creator_type.equalsIgnoreCase("user")){
        String restrictedAccess = BaseUrl+"error/noaccess.html";
            return  new ModelAndView("redirect:"+restrictedAccess);
        }
         ModelAndView model = new ModelAndView("attendant/viewstaff", "appname", "Seraba Accounting");
        
               try{
                 StoreStaff storeStaff = (StoreStaff) session.getAttribute("staff");
                // Integer creator_id = storeStaff.getStaffId(); // we get the id of the current logged in user
                 User user = storeStaff.getUserId();
                  creator_type = (String) session.getAttribute("userType");
        
              if(creator_type.equalsIgnoreCase("admin")){
                model.addObject("staffs",storestaffH.listStaffs());
                }else{
                model.addObject("staffs",storestaffH.listUserStaffs(user));
                }
               }catch(Exception err){
                   System.out.println("System error :"+err.getMessage());
               }
              model.addObject("baseUrl", BaseUrl);
              SessionData rSession = new SessionData(request);
                return model;
	}
     
        @RequestMapping( value = "/staff/editstaff", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView editStaff(HttpServletRequest request, HttpServletResponse response)throws Exception {
         SessionData temp = new SessionData(request);        
         BaseUrl = temp.BaseUrl(request);
        HttpSession session= request.getSession(false);  
        error = "";
        if(session == null || session.getAttribute("fullname")== null){
         String url = BaseUrl+"login/index.html?redirect=staff/viewstaff.html";
          return new ModelAndView("redirect:"+url);
        }
        
         Map aclData =  (Map) session.getAttribute("acl");
       String creator_type = (String) session.getAttribute("userType");
        if(!creator_type.equalsIgnoreCase("user")){
        String restrictedAccess = BaseUrl+"error/noaccess.html";
            return  new ModelAndView("redirect:"+restrictedAccess);
        }
        String returnMessage = "";
        Boolean display = true;
        Map<String, String> map = new HashMap<String, String>();
        ModelAndView model = new ModelAndView("attendant/editstaff", "appname", "Sereba Accounting");
        try{
        StoreStaff storeStaff = (StoreStaff) session.getAttribute("staff");
        Integer creator_id = storeStaff.getStaffId(); // we get the id of the current logged in user
        User user = storeStaff.getUserId();
         creator_type = (String) session.getAttribute("userType");
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
        EntityManager em = emf.createEntityManager();
         em.getTransaction().begin();
         
        String staffId = request.getParameter("id") != null ? request.getParameter("id") : "";
        
       
       
        // Configure a repository (to ensure a secure temp location is used)
       
        boolean isPost = "POST".equals(request.getMethod());
        if(staffId.isEmpty()){
                    error += "<br/> You need to select a staff to edit";
                    display = false;
            }
        
        staff = em.find(StoreStaff.class,Integer.parseInt(staffId));
                    
                    if(staff == null){
                    error += "<br/> Staff record not found";
                    display = false;
                    }else if(staff.getIsDeleted() == 1){
                        error += "<br/> This staff record have been deleted";
                        display = false;
                    }
                    else{   
          if(isPost){
              
         String[] aclArray = request.getParameterValues("acl");
        
        for(int i=0; i<aclArray.length; i++){
            map.put(aclArray[i],aclArray[i]);
        }
        
        
       String acl = String.join(",", aclArray);
        
        error += storestaffH.validateEditMode(request);
              
        //now we want to deal with the real data
        if(error.isEmpty()){
         String fullname = request.getParameter("fullname") != null ? request.getParameter("fullname") : "";
         String phone_no = request.getParameter("phone_no")!= null? request.getParameter("phone_no"):"";
         String password = request.getParameter("password") !=null ? request.getParameter("password") : "";
        
        
       
       
       
        staff.setAcl(acl);
        staff.setFullname(fullname);
        staff.setPhoneNumber(phone_no);
        if(!password.isEmpty()){
        staff.setPassword(util.md5(password));
        }
        staff.setIsDeleted(0);
        staff.setTimeModified(util.getCurrentTimeStamp());
       
        
          em.getTransaction().commit();
          em.close();
          emf.close();
          String message = "";
            if(!staff.toString().isEmpty()){
            if(!password.isEmpty()){
                String mailMessage = "Registration successful. "
                     + "The password to your account have been changed by the admin."
                     + "Youre new Password: "+password+"</b>"
                     + "To login into your account <a href='"+BaseUrl+"'>click here, and check the staff account type</a>";
             mailSender.sendHtmlEmail(staff.getEmail(), "no-reply@sereba_accounting.com", "Account Password Update", mailMessage);
             message = "Staff account update successful. An Email has been sent to the staff with their new password";
            }else{
                message = "Staff account update successful";
            }
             
             
             returnMessage = util.successAlertReturn(message, "savestaff");
            }else{
                returnMessage = util.errorAlertReturn("Unable to update staff account. Please try again");
            }
     
        
        
        }else{
            returnMessage = util.errorAlertReturn(error);
        }
      
         
          }else{
              String acl = staff.getAcl();
                   
         String[] aclArray = acl.split(",");
        
        for(int i=0; i<aclArray.length; i++){
            map.put(aclArray[i],aclArray[i]);
        }
          }
        }
        
        }catch(Exception err){
            returnMessage = util.errorAlertReturn("System error: "+err.getMessage());
        }
        
        
        
        model.addObject("baseUrl",BaseUrl);
        model.addObject("returnMessage",returnMessage);
        model.addObject("staff",staff);
        model.addObject("acl",map);
        model.addObject("display",display);
        SessionData rSession = new SessionData(request);

        return model;
    }
     
     @RequestMapping(value = "/staff/delete", method = RequestMethod.POST)
        public @ResponseBody String deleteCredit(HttpServletRequest request, HttpServletResponse response) throws Exception{
             SessionData temp = new SessionData(request);        
             BaseUrl = temp.BaseUrl(request);
        error = "";
       HttpSession session= request.getSession(false);  
         if(session==null || session.getAttribute("fullname") == null){
         error += "<br/> User is not logged in. Please try to log in";
        }
         
//           
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
        EntityManager em = emf.createEntityManager();
        String returnMessage = "";
                try {
          
        
         
         //error = "This is the error "+request.getParameter("").toString()+"";
         String id = request.getParameter("id") != null ? request.getParameter("id") : "";
         String action = request.getParameter("action")!= null? request.getParameter("action"):"";
          em.getTransaction().begin();
         staff = em.find(StoreStaff.class,Integer.parseInt(id));
        // product = em.find(Product.class, new Long(Integer.parseInt(id)));
        // error += "<br/>Ths is the product ID "+id+" action is called "+action+product.getProdId().toString();
         
       
         if(id.isEmpty()){
             error += "<br/> Staff does not exists";
         } 
         
         if(action.isEmpty()){
             error += "<br/> Invalid request";
         }
       
         
         if(!error.isEmpty()){
             // there is an error
         returnMessage = util.errorAlertReturn(error);
         }else{
         
          
         staff.setIsDeleted(1);
         staff.setTimeModified(util.getCurrentTimeStamp());
         em.merge(staff);
         em.flush();
         em.getTransaction().commit();
         
         em.close();
         emf.close();
         
          if(!staff.toString().isEmpty()){
            returnMessage = util.successAlertReturn("Staff record successfully deleted...", "deletestaff");
         
            
         }else{
            returnMessage = util.errorAlertReturn("Unable to delete staff record");
         }
          
         }
        
                } catch (Exception err){
                    
                }
                SessionData rSession = new SessionData(request);
          return returnMessage;
    }
    
        
}
