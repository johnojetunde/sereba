/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sereba.controller;
 
import com.sereba.model.util.UserHelper;
import com.sereba.model.util.MailSender;
import com.sereba.model.util.AuthManager;
import com.sereba.model.util.StoreStaffHelper;
import com.sereba.model.util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sereba.model.*;
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
import javax.persistence.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.xml.bind.PropertyException;
import javax.servlet.ServletException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sereba.model.util.*;
 

/*
 * author: Crunchify.com
 * 
 */
 
@Controller
public class LoginController extends HttpServlet {
   private User user = new User();
   private Util util  = new Util();
   private String baseUrl = util.BaseUrl();
   private MailSender mailSender = new MailSender();
   private UserHelper userHelper = new UserHelper();
   private StoreStaffHelper storestaffH = new StoreStaffHelper();
   private String error = "";
   private String redirectUrl = "";
       
    @RequestMapping("/index")
	public ModelAndView hello() {
 
		String message = "";
		return new ModelAndView("index/welcome", "message", message);
	}
        
   @RequestMapping("/index/login")
   public ModelAndView index(HttpServletRequest request)throws Exception {
       SessionData temp = new SessionData(request);        
       baseUrl = temp.BaseUrl(request);
         ModelAndView model = new ModelAndView("login/index", "appname", "Sereba Accounting") ;
       try{
       HttpSession session= request.getSession(false);  
        if(session != null && session.getAttribute("fullname")!= null){
         String urlDashboard = baseUrl+"index/dashboard.html";
          return new ModelAndView("redirect:"+urlDashboard);
        }
       
                
                //model.addObject("appname", "Sereba Accounting");
                model.addObject(new User());
                model.addObject("baseUrl",baseUrl);
               // model.addAttribute(new User());
       }catch(Exception err){
           System.out.println("System error :"+err.getMessage());       }
		return  model;
	}

   @RequestMapping("/login/index")
   public ModelAndView login(HttpServletRequest request, HttpServletResponse response)throws Exception {
        SessionData temp = new SessionData(request);        
        baseUrl = temp.BaseUrl(request);
                String url =  request.getParameter("redirect") !=null ? request.getParameter("redirect") : "";
                HttpSession session= request.getSession(false);  
        error = "";
        if(session != null && session.getAttribute("fullname")!= null){
         String urlDashboard = baseUrl+"index/dashboard.html";
          return new ModelAndView("redirect:"+urlDashboard);
        }
		
                ModelAndView model = new ModelAndView("login/index", "appname", "Sereba Accounting") ;
               
                model.addObject(new User());
                model.addObject("baseUrl",baseUrl);
                model.addObject("redirectUrl",url);
               // model.addAttribute(new User());
		return  model;
	}
   
   
   @RequestMapping(value="/login/forgotpassword", method = RequestMethod.POST)
   public @ResponseBody String forgotPassword(HttpServletRequest request, HttpServletResponse response) throws Exception{
        SessionData temp = new SessionData(request);        
        baseUrl = temp.BaseUrl(request);
       String returnMessage = "";
       String email = request.getParameter("email") != null ? request.getParameter("email") : "";
       String userType = request.getParameter("user_type") != null ? request.getParameter("user_type") : "";
       error = "";
       String resetUrl = "";
       String userAccount = "";
       if(email.isEmpty()){
           error += "<br/> Please enter your email";
       }
        if(!error.isEmpty()){
             // there is an error
         returnMessage = util.errorAlertReturn(error);
         }else{
         if(userType.equals("staff")){
                  //this is where we get the content of a staff when he is trying to reset the password to his/her account
                  userAccount = "staff"; //assuming this is the return data from the insertion query
              }else{
                  //this is for the owner of the account ( I mean the user) when trying to reset his/her account
                  userAccount = "owner"; //assuming this is the return data from the insertion query
              }
         
         if(!userAccount.isEmpty()){
           String mailMessage = "You can reset your account's password by clicking this url:<br/>"
                     + "<a href='"+resetUrl+"'>Reset Password</a>";
             mailSender.sendHtmlEmail(email, "no-reply@sereba_accounting.com", "Forgot Password", mailMessage);
             
            returnMessage = util.successAlertReturn("Account found! Password reset sent to your mail", "forgotpassword");
         }else{
             returnMessage = util.errorAlertReturn("Account not found.");
         }
          
        }
       
       return returnMessage;
   }
       
   @RequestMapping(value="/login/singin", method = RequestMethod.POST)
   public @ResponseBody String loginUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
        SessionData temp = new SessionData(request);        
        baseUrl = temp.BaseUrl(request);
       String returnMessage = "";
       String email = request.getParameter("email") != null ? request.getParameter("email") : "";
       String redirectUrl = request.getParameter("redirectUrl") != null ? request.getParameter("redirectUrl") : "";
       String password = request.getParameter("password") != null ? request.getParameter("password") : "";
       String userType = request.getParameter("user_type") != null ? request.getParameter("user_type") : "";
       error = "";
       String data = "";
       String url = "";
       try{
       if(redirectUrl.isEmpty()){
           url = baseUrl+"user/profile.html";
        }else{
           url = baseUrl+redirectUrl;
       }
       if(email.isEmpty()){
           error += "<br/> Please enter your email";
       }
       if(password.isEmpty()){
           error += "<br/> Please enter the password";
       }
       
          if(!error.isEmpty()){
             // there is an error
         returnMessage = util.errorAlertReturn(error);
         }else{
              //error += "<br/> We can't check for the admin now";
              if(userType.equals("staff")){
                  //this is where we get the content of a staff when he is trying to log in
                  data = "staff"; //assuming return value from the insertion
                  HttpSession session=request.getSession(true); 
                  StoreStaff staff = userHelper.checkStaffAccount(email, util.md5(password));
                   if(staff.getStaffId() != null){
                       session.setAttribute("userType","staff"); 
                       session.setAttribute("staff",staff);
                       session.setAttribute("fullname",staff.getFullname());
                       session.setAttribute("timecreated",staff.getTimeCreated());
                       session.setAttribute("timemodified",staff.getTimeModified());
                      
                       String acl = staff.getAcl();
                       String[] aclArray = acl.split(",");
                       Object det = new Object();
                       String aclObject = new String();
                      
                       Map<String, String> map = new HashMap<String, String>();
                       for(int i=0; i<aclArray.length; i++){
                        map.put(aclArray[i],aclArray[i]);
                        // aclObject.aclArray[i] = aclArray[i];
                       }
                       //map.
                       session.setAttribute("acl",map);
                       session.setAttribute("aclArray",aclArray);
                       session.setMaxInactiveInterval(900);
                   }else{
                       error += "<br/> Invalid Email or Password";
                   }
              }else if(userType.equals("user")){
                  User userData = userHelper.checkUserAccount(email, util.md5(password));
                //  error += "<br/> Invalid Email or Password"+userData.toString()+userData;
                  
                  if(userData.getUserId() != null){
                  HttpSession session= request.getSession(true);  
                    session.setAttribute("userType","user");  
                    session.setAttribute("user",userData); 
                    session.setAttribute("fullname",userData.getFullname());
                    session.setAttribute("timecreated",userData.getTimeCreated());
                    session.setAttribute("timemodified",userData.getTimeModified());
                    StoreStaff staff = userHelper.checkStaffAccount(email, util.md5(password));
                    session.setAttribute("staff",staff);
                   // String acl = staff;
                   String acl = staff.getAcl();
                       String[] aclArray = acl.split(",");
                       Object det = new Object();
                       String aclObject = new String();
                      
                       Map<String, String> map = new HashMap<String, String>();
                       for(int i=0; i<aclArray.length; i++){
                        map.put(aclArray[i],aclArray[i]);
                        // aclObject.aclArray[i] = aclArray[i];
                       }
                       //map.
                       session.setAttribute("acl",map);
                       session.setAttribute("aclArray",aclArray);
                       
                    session.setMaxInactiveInterval(900);
                  }else{
                      error += "<br/> Invalid Email or Password";
                  }
                  //this is for the owner of the account ( I mean the user)
                  data = "owner"; //assuming return value from the insertion
              }else{
              // this is the admin section for the login page
               error += "<br/> We can't check for the admin now";
              }
//              HttpSession session=request.getSession();  
//              session.setAttribute("userType",name);  
//session.setAttribute("user",name); 
//session.setAttribute("staff",name); 
              
              //assuming the user was successfully created
              if(error.isEmpty()){
               returnMessage = util.successRedirectReturn("Login successful. redirecting...",url, 2, "loginform");
              }else{
              returnMessage = util.errorAlertReturn(error);
              }
          }
          }catch (Exception err){
              error += util.errorAlertReturn("System eror :"+err.getMessage());
          } 
          return returnMessage;
   }
  
   // the ajax method for the add register form
   @RequestMapping(value = "/login/adduser", method = RequestMethod.POST)
   public @ResponseBody String registerUser(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException{
        SessionData temp = new SessionData(request);        
        baseUrl = temp.BaseUrl(request);
       //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullname", "Fullname is a required field");
       error = "";
       String returnMessage = "";
       try {
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
         returnMessage = util.errorAlertReturn(error);
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
             
             returnMessage = util.successAlertReturn("Registration successful. Please check your email and click the activation link to activate your account.", "registerform");
         
         }else{
             returnMessage = util.errorAlertReturn("Unable to register your account. Please try again");
         }
         
         }
       }catch(PropertyException err){
                err.printStackTrace();
       returnMessage = util.errorAlertReturn(err.getMessage());
                System.out.println("inside catch area: " + err.getMessage());
       }catch (Exception err){
         returnMessage = util.errorAlertReturn(err.getMessage());
       }
        return returnMessage;

   }
   
   @RequestMapping(value = "login/logout", method = {RequestMethod.GET, RequestMethod.POST})
   public ModelAndView logOut(HttpServletRequest request,HttpServletResponse response)throws Exception{
        SessionData temp = new SessionData(request);        
        baseUrl = temp.BaseUrl(request);
       HttpSession session= request.getSession();  
       session.invalidate();
//       response.sendRedirect(baseUrl);
       return new ModelAndView("redirect:"+baseUrl);
          //response.setHeader("Location", baseUrl);
   }
}
