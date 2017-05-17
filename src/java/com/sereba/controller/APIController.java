/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sereba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sereba.model.util.*;
import com.sereba.model.*;
import com.sereba.model.util.SessionData;
import javax.servlet.http.HttpServlet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 *
 */
/**
 *
 * @author John
 */

@Controller
public class APIController extends HttpServlet {
   private String error = "";
   private User user = new User();
   private Util util  = new Util();
   private String baseUrl = util.BaseUrl();
   private MailSender mailSender = new MailSender();
   private UserHelper userHelper = new UserHelper();
   private StoreStaffHelper storestaffH = new StoreStaffHelper();
    
    @RequestMapping(value = "/api/registerUser", method = RequestMethod.POST)
    public @ResponseBody String registerUser(HttpServletRequest request, HttpServletResponse response)throws Exception{
         String returnMessage = "";
         String error = "";
        String status = "";
          Map map = new HashMap();
        try{
        
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
          EntityManager em = emf.createEntityManager();
          em.getTransaction().begin();
         // calling the validator method to validate the input gotten from the register form
         error += userHelper.addUserValidation(request);
         
         if(error.isEmpty()){
         error += userHelper.validateEmailStoreName(request);
         
         }
         
         if(error.isEmpty()){
           error += storestaffH.validateEmail(request);
         }
         
         
         error = error.trim();
         if(!error.isEmpty()){
             // there is an error
         error = error.replaceAll("<br/>", ",");
         status = "Failed";
         //status = 101;
         }else{
          // no error message. Our inputs are all valid
         String fullname = request.getParameter("fullname") != null ? request.getParameter("fullname") : "";
         String email =    request.getParameter("email")!= null? request.getParameter("email"):"";
         String store_name = request.getParameter("store_name") !=null ? request.getParameter("store_name") : "";
         String phone_no = request.getParameter("phone_no") != null ? request.getParameter("phone_no") : "";
         String password = request.getParameter("password") != null ? request.getParameter("password") : "";
        // String confirm_password = request.getParameter("confirm_password") !=null ? request.getParameter("confirm_password") : "";
         String contact_address = request.getParameter("contact_address") != null ? request.getParameter("contact_address") : "";
        
         
         
         user.setFullname(fullname);
         user.setEmail(email);
         user.setStoreName(store_name);
         user.setPhoneNumber(phone_no);
         user.setPassword(util.md5(password));
         user.setContactAddress(contact_address);
         user.setTimeCreated(util.getCurrentTimeStamp());
         user.setIsDeleted(0);
         user.setSource("mobile");
         //insert into the database here
         
         
//         //persist only on save mode
                if(!em.contains(user)){
                    em.persist(user);
                   
                }
               em.getTransaction().commit();
               em.close();
               emf.close();
////                //em.close();
//          
////                
           emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
           em = emf.createEntityManager();
           em.getTransaction().begin();
         // we wana create a staff record for the user
         User userRecord = userHelper.getLastUser();
         
         StoreStaff storestaff = new StoreStaff();
         storestaff.setEmail(email);
         storestaff.setFullname(fullname);
         storestaff.setPassword(util.md5(password));
         storestaff.setUserId(userRecord);
         storestaff.setAcl(userHelper.geAllDefaultAcls());
         storestaff.setPhoneNumber(phone_no);
         storestaff.setTimeCreated(util.getCurrentTimeStamp());
         storestaff.setIsDeleted(0);
         storestaff.setSource("mobile");
         
             if (!em.contains(storestaff)) {
                 em.persist(storestaff);

             }
             
          em.getTransaction().commit();
          em.close();
          emf.close();
          
          //returnMessage =   util.errorAlertReturn(userRecord.toString());
          //I will get the activation link here
                
         String activationUrl = "";
         if(!user.toString().isEmpty()){
             String mailMessage = "Registration successful. "
                     + "Your details does thus: <b>Username:"+email
                     + "Password: "+password+"</b>"
                     + "To activate your account <a href='"+activationUrl+"'>click here</a>";
             mailSender.sendHtmlEmail(email, "no-reply@sereba_accounting.com", "Registration success", mailMessage);
             
             returnMessage = "Registration successful. Please check your email and click the activation link to activate your account.";
             status = "Succeed";
         }else{
             error += ",Unable to register your account. System error";
             status = "Failed";
         }
         
         }
        }catch(Exception err){
             error +=","+err.getMessage();
             status = "Failed";
        }
        Gson gson = new GsonBuilder().create();
  
        // map.put("resultList",resultList);
         map.put("returnMessage", returnMessage);
         map.put("error",error);
         map.put("status",status);
      
        String jsonResponse = gson.toJson(map);
        
        return jsonResponse;
    }
    
}
