/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sereba.model.util;

import com.sereba.model.StoreStaff;
import com.sereba.model.User;
import java.util.List;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import com.sereba.model.util.UserHelper;
import java.util.ArrayList;
/**
 *
 * @author John
 */
public class StoreStaffHelper {
    
      public String addStaffValidation(HttpServletRequest request) throws Exception{
          
         String error = "";
             if(request.getParameter("fullname").isEmpty()){
            error += "<br/> Please enter the fullname of the staff";
        }
        
        if(request.getParameter("email").isEmpty()){
            error += "<br/> Please enter the email address of the staff";
        }
         try{
         InternetAddress emailAddr = new InternetAddress(request.getParameter("email"));
          emailAddr.validate();
          }catch(AddressException ex){
          error +="<br/> Please enter a valid email address";
          }
        
        if(request.getParameter("phone_no").isEmpty()){
            error +="<br/> Please enter the phone number of the staff";
        }
        
        if(request.getParameter("password").isEmpty()){
            error +="<br/> Please enter the password for the staff";
        }
        if(request.getParameter("confirm_password").isEmpty()){
            error +="<br/> Please confirm the password you enter for the staff";
        }
        
        if(!request.getParameter("password").equals(request.getParameter("confirm_password"))){
            error += "<br/> Password does not match with the confirm password value";
        }
      
      return error;
      }
      
      public String validateEditMode(HttpServletRequest request)throws Exception{
          String error = "";
          if(request.getParameter("fullname").isEmpty()){
            error += "<br/> Please enter the fullname of the staff";
        }
           if(request.getParameter("phone_no").isEmpty()){
            error +="<br/> Please enter the phone number of the staff";
        }
           if(!request.getParameter("password").isEmpty()){
                if(request.getParameter("confirm_password").isEmpty()){
            error +="<br/> Please confirm the password you enter for the staff";
        }
        
        if(!request.getParameter("password").equals(request.getParameter("confirm_password"))){
            error += "<br/> Password does not match with the confirm password value";
        }
           }
          return error;
      }
      public boolean isEmail(String email) throws Exception {
         boolean response = false;
         try{
          EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
          EntityManager em = emf.createEntityManager();
          
         Query jpqlQuery = em.createNamedQuery("StoreStaff.findByEmailIsDeleted");
         jpqlQuery.setParameter("email", email);
         jpqlQuery.setParameter("isDeleted", 0);
         
         List<StoreStaff> staff = jpqlQuery.getResultList();
         if(!staff.isEmpty()){
             response = true;
         }
         }
         catch (Exception err){
         System.out.println("Unable to check email"+err.getMessage());
         }
         
         return response;
     }
      
      public String validateEmail(HttpServletRequest request) throws Exception{
      String error = "";
      
         try{
         
        if(isEmail(request.getParameter("email")) ){
         error += "<br/> User exists in the database";
        }
                        
         }catch (Exception err){
             System.out.println("Error message"+err);
         }
         
         return error;
     }
      
       public List<Object> listStaffs()throws Exception{
        //List<Agent> agentList = new ArrayList<Agent>();
        List<Object> resultList = new ArrayList<Object>();
        try {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
        EntityManager em = emf.createEntityManager();
        
        //find by ID
        //Query jpqlQuery  = em.createNamedQuery("Agent.findByDeleted");
        Query jpqlQuery = em.createNamedQuery("StoreStaff.findByAdminIsDeleted");
        jpqlQuery.setParameter("isDeleted", 0);
         resultList = jpqlQuery.getResultList();
         
         em.clear();
        em.close();
        emf.close();
        }catch (Exception err){
            System.out.println("System error"+err.getMessage());
        }
        return resultList;
    }
        
        public List<Object> listUserStaffs(User user_id)throws Exception{
      
        List<Object> resultList = new ArrayList<Object>();
        try {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
        EntityManager em = emf.createEntityManager();
        
        //find by ID
        //Query jpqlQuery  = em.createNamedQuery("Agent.findByDeleted");
        Query jpqlQuery = em.createNamedQuery("StoreStaff.findByCreatorIsDeleted",Object[].class);
        jpqlQuery.setParameter("isDeleted", 0);
        jpqlQuery.setParameter("userId",user_id);
        //List<Product> productList = jpqlQuery.getResultList();
        
       resultList = jpqlQuery.getResultList();
        em.clear();
        em.close();
        emf.close();
        }catch(Exception err){
            System.out.println("System error:"+err.getMessage());
        }
        
        return resultList;
    }
      
}
