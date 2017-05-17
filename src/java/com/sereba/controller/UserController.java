/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sereba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.*;
import com.sereba.model.util.Util;
import com.sereba.model.util.UserHelper;
import com.sereba.model.User;
import com.sereba.model.StoreStaff;
import com.sereba.model.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 *
 * @author John
 */

@Controller
public class UserController extends HttpServlet {
    private String error = "";
    private Util util =  new Util();
    private User user = new User();
    private StoreStaff  staff = new StoreStaff();
    private String BaseUrl = util.BaseUrl();
    private UserHelper userH = new UserHelper();
    
    @RequestMapping("/user")
    public ModelAndView helloWorld() {
        
        String message = "<br><div style='text-align:center;'>"
                + "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from CrunchifyHelloWorld.java **********</div><br><br>";

        return new ModelAndView("index/welcome", "message", message);
    }
    
    
    @RequestMapping("/user/profile")
    public ModelAndView profile(HttpServletRequest request, HttpServletResponse response) throws Exception{
         SessionData temp = new SessionData(request);        
         BaseUrl = temp.BaseUrl(request);
        ModelAndView model = new ModelAndView("index/profile","appname","Sereba Accounting");
        HttpSession session= request.getSession(false);  
        if(session == null || session.getAttribute("fullname")==null){
             String url = BaseUrl+"login/index.html?redirect=user/profile.html";
          return new ModelAndView("redirect:"+url);
        }
        String returnMessage = "";
        error = "";
        try{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
         staff = (StoreStaff) session.getAttribute("staff");
         Integer staffId = staff.getStaffId();
         user = staff.getUserId();
         staff = em.find(StoreStaff.class,staffId);
            
        }catch(Exception err){
            returnMessage = util.errorAlertReturn("System error:"+err.getMessage());
        }
        model.addObject("baseUrl",BaseUrl);
        model.addObject("user"+user);
        model.addObject("staff",staff);
        return model;
    }
    
     @RequestMapping(value="/user/saveaccountuser", method = RequestMethod.POST)
    public @ResponseBody String saveUserAccount(HttpServletRequest request, HttpServletResponse response) throws Exception{
         SessionData temp = new SessionData(request);        
         BaseUrl = temp.BaseUrl(request);
        error ="";
        String returnMessage = "";
       
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        HttpSession session= request.getSession(false);  
        if(session == null || session.getAttribute("fullname")==null){
          return util.errorAlertReturn("User not signed in. Please try to log in again");
        }
        
        try{
            String fullname = request.getParameter("fullname") != null ? request.getParameter("fullname") : "";
            String phone_no = request.getParameter("phone_no") != null ? request.getParameter("phone_no") : "";
            String store_name = request.getParameter("store_name") != null ? request.getParameter("store_name") : "";
            String contact_address = request.getParameter("contact_address") != null ? request.getParameter("contact_address") : "";
            
           
             staff = (StoreStaff) session.getAttribute("staff");
             Integer staffId = staff.getStaffId();
             Integer userId = staff.getUserId().getUserId();
             staff = em.find(StoreStaff.class,staffId);
             user = em.find(User.class,userId);
//           
//            
            if(fullname.isEmpty()){
                error +="<br/>Please fill in your fullname";
            }
            
            if(phone_no.isEmpty()){
                error +="<br/>Please fill in your phone number";
            }
            
            if(store_name.isEmpty()){
              error +="<br/>Please fill in your Store Name";
            }else{
                if(userH.isStoreNameNotSame(store_name, userId)){
                    error +="<br/> Store name exists in the database";
                }
            }
            
           if(contact_address.isEmpty()){
              error +="<br/>Please fill in your Contact Address";
            }
//            

            if(!error.isEmpty()){
                returnMessage = util.errorAlertReturn(error);
            }else{
                   user.setFullname(fullname);
                   user.setPhoneNumber(phone_no);
                   user.setContactAddress(contact_address);
                   user.setStoreName(store_name);
                   user.setTimeModified(util.getCurrentTimeStamp());
                    
                    staff.setFullname(fullname);
                    staff.setPhoneNumber(phone_no);
                    staff.setTimeModified(util.getCurrentTimeStamp());
                
                
                
                
            }
           em.getTransaction().commit(); 
           em.close();
           emf.close();
           
           if(error.isEmpty()){
            if(!staff.toString().isEmpty()){
                 returnMessage = util.successAlertReturn("Account successfully updated...","");
         
            }else{
                returnMessage = util.errorAlertReturn("Unable to update your account details. Please try again...");
            }
           }else{
               returnMessage = util.errorAlertReturn(error);
           }
            
        }catch(Exception err){
            return util.errorAlertReturn("System error:"+err.getMessage());
        }
        
        
        return returnMessage;
        
    }
    
    
    @RequestMapping(value="/user/saveaccountstaff", method = RequestMethod.POST)
    public @ResponseBody String saveStaffAccount(HttpServletRequest request, HttpServletResponse response) throws Exception{
         SessionData temp = new SessionData(request);        
         BaseUrl = temp.BaseUrl(request);
        error ="";
        String returnMessage = "";
       
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        HttpSession session= request.getSession(false);  
        if(session == null || session.getAttribute("fullname")==null){
          return util.errorAlertReturn("User not signed in. Please try to log in again");
        }
        
        try{
            String fullname = request.getParameter("fullname") != null ? request.getParameter("fullname") : "";
            String phone_no = request.getParameter("phone_no") != null ? request.getParameter("phone_no") : "";
           
             staff = (StoreStaff) session.getAttribute("staff");
             Integer staffId = staff.getStaffId();
             
             staff = em.find(StoreStaff.class,staffId);
            
//           
//            
            if(fullname.isEmpty()){
                error +="<br/>Please fill in your fullname";
            }
            
            if(phone_no.isEmpty()){
                error +="<br/>Please fill in your phone number";
            }
//            
            if(!error.isEmpty()){
                returnMessage = util.errorAlertReturn(error);
            }else{
                    staff.setFullname(fullname);
                    staff.setPhoneNumber(phone_no);
                    staff.setTimeModified(util.getCurrentTimeStamp());
                
                
                
                
            }
           em.getTransaction().commit(); 
           em.close();
           emf.close();
           
           if(error.isEmpty()){
            if(!staff.toString().isEmpty()){
                 returnMessage = util.successAlertReturn("Account successfully updated...","");
         
            }else{
                returnMessage = util.errorAlertReturn("Unable to update your account details. Please try again...");
            }
           }else{
               returnMessage = util.errorAlertReturn(error);
           }
            
        }catch(Exception err){
            return util.errorAlertReturn("System error:"+err.getMessage());
        }
        
        
        return returnMessage;
        
    }
    
    @RequestMapping(value = "/user/savepassword", method = RequestMethod.POST)
    public @ResponseBody String savePassword(HttpServletRequest request, HttpServletResponse response)throws Exception{
         SessionData temp = new SessionData(request);        
         BaseUrl = temp.BaseUrl(request);
        error = "";
        String returnMessage = "";
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        HttpSession session= request.getSession(false);  
        if(session == null || session.getAttribute("fullname")==null){
          return util.errorAlertReturn("User not signed in. Please try to log in again");
        }
        try{
            String currentPassword = request.getParameter("current_password") != null ? request.getParameter("current_password") : "";
            String newPassword = request.getParameter("new_password") != null ? request.getParameter("new_password") : "";
            String confirmPassword = request.getParameter("confirm_password") != null ? request.getParameter("confirm_password") : "";
            
             staff = (StoreStaff) session.getAttribute("staff");
             Integer staffId = staff.getStaffId();
             
             staff = em.find(StoreStaff.class,staffId);
             Integer userId = staff.getUserId().getUserId();
             user = em.find(User.class,userId);
            if(!staff.getPassword().equals(util.md5(currentPassword))){
                error +="<br/> Password does not match with your current password";
            }
            
            if(newPassword.isEmpty() || confirmPassword.isEmpty()){
                error +=" Both password and confirm password fields are required fields";
            }else{
            if(!newPassword.equals(confirmPassword)){
                error += "<br/> Your password and the confirm password must match";
            }
            }
            
            if(!error.isEmpty()){
                returnMessage = util.errorAlertReturn(error);
            }else{
                if(session.getAttribute("userType").equals("user")){
                  user.setPassword(util.md5(newPassword));
                  user.setTimeModified(util.getCurrentTimeStamp());
                }
                
                    staff.setPassword(util.md5(newPassword));
                    staff.setTimeModified(util.getCurrentTimeStamp());
                
                
                
                
            }
           em.getTransaction().commit(); 
           em.close();
           emf.close();
           
           if(error.isEmpty()){
            if(!staff.toString().isEmpty()){
                 returnMessage = util.successAlertReturn("Password successfully updated...","passwordform");
         
            }else{
                returnMessage = util.errorAlertReturn("Unable to update your password. Please try again...");
            }
           }else{
               returnMessage = util.errorAlertReturn(error);
           }
            
        }catch(Exception err){
            return util.errorAlertReturn("System error:"+err.getMessage());
        }
        return returnMessage;
    }
    
}
