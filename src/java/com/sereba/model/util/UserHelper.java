/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sereba.model.util;

import javax.servlet.http.HttpServletRequest;
import com.sereba.model.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.commons.codec.digest.DigestUtils;
import javax.mail.*;
import javax.mail.internet.*;
/**
 *
 * @author John
 */
public class UserHelper {
   private Util util = new Util();
   private User user = new User();
   private MailSender mailSender = new MailSender();
   //private StoreStaff storestaff= new StoreStaff();
   private StoreStaffHelper storestaffH = new StoreStaffHelper();
   private String error = "";
   
     public String addUserValidation(HttpServletRequest request) throws Exception{
       String error = "";
     
   
    if(request.getParameter("fullname")== null ||request.getParameter("fullname").isEmpty()){
        error += "<br/> Please fill in your fullname";
    }
    
    if(request.getParameter("email")== null || request.getParameter("email").isEmpty()){
        error += "<br/> Please fill in your email";
    }
    try{
     InternetAddress emailAddr = new InternetAddress(request.getParameter("email"));
     emailAddr.validate();
    }catch(AddressException ex){
     error +="<br/> Please enter a valid email address";
    }
     
    if(request.getParameter("store_name")== null || request.getParameter("store_name").isEmpty()){
        error +="<br/> Please fill in your Store name";
    }
    
    if(request.getParameter("phone_no")== null || request.getParameter("phone_no").isEmpty()){
        error +="<br/> Please fill in your phone number";
    }
    
    if(request.getParameter("contact_address")== null || request.getParameter("contact_address").isEmpty()){
        error +="<br/> Please fill in your contact address";
    }
    
    if(request.getParameter("password")== null ||request.getParameter("password").isEmpty()){
        error += "<br/> Please fill in your password";
    }
    
    if(request.getParameter("confirm_password")== null || request.getParameter("confirm_password").isEmpty()){
        error += "<br/> Please fill in your confirm password field";
    }
    
    if(!request.getParameter("password").equals(request.getParameter("confirm_password"))){
        error += "<br/> Your Password does not match with your Confirm Password";
    }
    
       
       return error;
   }
   public String geAllDefaultAcls(){
       String acls =  "add_product,view_product,edit_product,delete_product,add_sale,view_sale,view_product,add_credit,view_credit,delete_credit,add_credit_payment,view_credit_payment,add_inventory,view_inventory,delete_inventory,add_income_expense,delete_income_expense,view_income_expense,charts,reports";
       
       return acls;
   }
     public boolean isEmail(String email) throws Exception {
         boolean response = false;
         try{
          EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
          EntityManager em = emf.createEntityManager();
          
         Query jpqlQuery = em.createNamedQuery("User.findByEmailDeleted");
         jpqlQuery.setParameter("email", email);
         jpqlQuery.setParameter("isDeleted", 0);
         
         List<User> user = jpqlQuery.getResultList();
         if(!user.isEmpty()){
             response = true;
         }
         }
         catch (Exception err){
         System.out.println("Unable to check email"+err.getMessage());
         }
         
         return response;
     }
     public User getLastUser()throws Exception{
         User user = new User();
         try{
          EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
          EntityManager em = emf.createEntityManager();
          
         Query jpqlQuery = em.createNamedQuery("User.findByLastId",User.class);
              List<User> list = jpqlQuery.getResultList();
              if (list == null || list.isEmpty()) {
        return null;
              }else{
                  user = list.get(0);
              }

//                
//         user = (User) jpqlQuery.
//         Object o = out.get(0);
          }
         catch (Exception err){
         System.out.println("Unable to check email"+err.getMessage());
         }
         return user;
     }
     
      public static Object getSingleResultOrNull(Query query)throws Exception{
        List results = query.getResultList();
        if (results.isEmpty()) return null;
        else if (results.size() == 1) return results.get(0);
        throw new NonUniqueResultException();
    }
      
     public String md5(String password)  {
         String md5Hex = "";
         try {
         md5Hex = DigestUtils.md5Hex(password);
         }catch (Exception err){
             System.out.println("An error in the md5 error page"+ err.getMessage());
         }
         
        return md5Hex;
    }
     
     
     
     public boolean isStoreNameNotSame(String storeName, Integer userId)throws Exception{
          boolean response = false;
         try{
          EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
          EntityManager em = emf.createEntityManager();
          
         Query jpqlQuery = em.createNamedQuery("User.findByStoreNameNotSameIsDeleted");
         jpqlQuery.setParameter("storeName", storeName);
         jpqlQuery.setParameter("userId", userId);
         jpqlQuery.setParameter("isDeleted", 0);
         
         List<User> user = jpqlQuery.getResultList();
         if(!user.isEmpty()){
             response = true;
         }
         }
         catch (Exception err){
         System.out.println("Unable to verify store name"+err.getMessage());
         }
         return response;
     }
     
     
     
     public boolean isStoreName(String storeName) throws Exception{
      boolean response = false;
         try{
          EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
          EntityManager em = emf.createEntityManager();
          
         Query jpqlQuery = em.createNamedQuery("User.findByStoreNameDeleted");
         jpqlQuery.setParameter("storeName", storeName);
         jpqlQuery.setParameter("isDeleted", 0);
         
         List<User> user = jpqlQuery.getResultList();
         if(!user.isEmpty()){
             response = true;
         }
         }
         catch (Exception err){
         System.out.println("Unable to verify store name"+err.getMessage());
         }
         
         return response;
     
     }
     
     public StoreStaff checkStaffAccount(String email, String password)throws Exception{
         StoreStaff staff = new StoreStaff();
          try{
          EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
          EntityManager em = emf.createEntityManager();
          
         Query jpqlQuery = em.createNamedQuery("StoreStaff.findByEmailPasswordDeleted");
         jpqlQuery.setParameter("email", email);
         jpqlQuery.setParameter("password", password);
         jpqlQuery.setParameter("isDeleted", 0);
         
         staff = (StoreStaff) jpqlQuery.getSingleResult();
         
         }
         catch (Exception err){
         System.out.println("Unable to verify store name"+err.getMessage());
         }
          return staff;
     }
     public User checkUserAccount(String email, String password) throws Exception{
         User user = new User();
     try{
          EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
          EntityManager em = emf.createEntityManager();
          
         Query jpqlQuery = em.createNamedQuery("User.findByEmailPasswordDeleted");
         jpqlQuery.setParameter("email", email);
         jpqlQuery.setParameter("password", password);
         jpqlQuery.setParameter("isDeleted", 0);
         
         user = (User) jpqlQuery.getSingleResult();
         
         }
         catch (Exception err){
         System.out.println("Unable to verify store name"+err.getMessage());
         }
     return user;
     }
     
     public String validEmail(HttpServletRequest request)throws Exception{
         String error ="";
         if(isEmail(request.getParameter("email"))){
         error += "<br/> User exists in the database";
        }
         return error;
     }
     public String validateEmailStoreName(HttpServletRequest request) throws Exception{
         String error = "";
      
         try{
         
         error += validEmail(request);
       
        
        if(isStoreName(request.getParameter("store_name"))){
         error += "<br/> Store name exists in the database";
        }
        
        
         }catch (Exception err){
             System.out.println("Error message"+err);
         }
         
         return error;
     }
}
