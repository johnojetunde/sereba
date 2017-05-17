/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sereba.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.net.URL;
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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.sereba.model.*;
import com.sereba.model.util.Util;
import com.sereba.model.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sereba.model.util.SaleHelper;
import com.sereba.model.util.InventoryHelper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.xml.bind.PropertyException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author John
 */

@Controller
public class InventoryController extends HttpServlet {
    private Util util = new Util();
    private String error = "";
    private String BaseUrl = util.BaseUrl();
    private Inventory inventory = new Inventory();
    private InventoryHelper inventoryH = new InventoryHelper();
    
	@RequestMapping("/inventory")
	public ModelAndView helloWorld() {
                String message = "<br><div style='text-align:center;'>"
				+ "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from Page HelloWork.java **********</div><br><br>";
                ModelAndView model = new ModelAndView("product/addproduct", "message", message) ;
                model.addObject("appname", "Sereba Accounting");
                return model;
	}
        
        @RequestMapping("/inventory/addincomeexpense")
        public ModelAndView addInventory(HttpServletRequest request, HttpServletResponse response)throws Exception {
             SessionData temp = new SessionData(request);        
             BaseUrl = temp.BaseUrl(request);
                HttpSession session= request.getSession(false);  
        if(session == null || session.getAttribute("fullname")== null){
          String url = BaseUrl+"login/index.html?redirect=inventory/addincomeexpense.html";
          return new ModelAndView("redirect:"+url);
        }
        
         Map aclData =  (Map) session.getAttribute("acl");
       
        if(!aclData.containsKey("add_income_expense") || !aclData.containsValue("add_income_expense")){
        String restrictedAccess = BaseUrl+"error/noaccess.html";
            return  new ModelAndView("redirect:"+restrictedAccess);
        }
                error = "";
                String returnMessage = "";
                
                StoreStaff storeStaff = (StoreStaff) session.getAttribute("staff");
                Integer creator_id = storeStaff.getStaffId(); // we get the id of the current logged in user
                User user = storeStaff.getUserId();
                String creator_type = (String) session.getAttribute("userType");
        
                ModelAndView model = new ModelAndView("inventory/addinventory", "appname", "Sereba Accounting");
                
                model.addObject("baseUrl",BaseUrl);
                 
                return model;
    }
    
    
        @RequestMapping("/inventory/saveinventory")
	public @ResponseBody String saveInventory(HttpServletRequest request, HttpServletResponse response)throws Exception {
             SessionData temp = new SessionData(request);        
             BaseUrl = temp.BaseUrl(request);
           error = "";
           String returnMessage = "";
           try{
               EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
              EntityManager em = emf.createEntityManager();
              em.getTransaction().begin();
              
               HttpSession session= request.getSession(false);  
              if(session == null || session.getAttribute("fullname")== null){
               return util.errorAlertReturn("User has been logged out. Please login again");
               }
              
               Map aclData =  (Map) session.getAttribute("acl");
       
                if(!aclData.containsKey("add_income_expense") || !aclData.containsValue("add_income_expense")){
               return util.errorAlertReturn("You do not have access to perform this action");
                }
              
              String title = request.getParameter("title") != null ? request.getParameter("title") : "";
              String trans_type =    request.getParameter("trans_type")!= null? request.getParameter("trans_type"):"";
              String amount = request.getParameter("amount") !=null ? request.getParameter("amount") : "";
             
             

             StoreStaff storeStaff = (StoreStaff) session.getAttribute("staff");
             Integer creator_id = storeStaff.getStaffId(); // we get the id of the current logged in user
             User user = storeStaff.getUserId();
             String creator_type = (String) session.getAttribute("userType");
             
             if(title.isEmpty()){
                 error += "<br/> Title is a required a field";
             }
             
             if(trans_type.isEmpty()){
                 error += "<br/> Please select a transaction type";
             }
             if(amount.isEmpty()){
                 error +="<br/> Please enter an amount";
             }
             if(Integer.parseInt(amount)<=0){
                 error += "<br/> Please enter an amount greater than 0";
             }
             
             if(!error.isEmpty()){
                returnMessage = util.errorAlertReturn(error);
             }else{
                 Double amountDB = Double.parseDouble(amount);
                 inventory.setAmount(amountDB);
                 inventory.setTitle(title);
                 inventory.setTransType(trans_type);
                 inventory.setCreatorId(storeStaff);
                 inventory.setCreatorType(creator_type);
                 inventory.setDate(util.getCurrentTimeStamp());
                 inventory.setTimeCreated(util.getCurrentTimeStamp());
                 inventory.setIsDeleted(0);
                 
                 if(!em.contains(inventory)){
                  em.persist(inventory);
                 }
                 
               em.getTransaction().commit();
               em.close();
               emf.close();
               if(!inventory.toString().isEmpty()){
                   returnMessage = util.successAlertReturn("Inventory successfully added...", "addinventory");
               }else{
                   returnMessage = util.errorAlertReturn("Unable to save inventory...");
               }
                 
             }
           
           }catch (Exception err){
                returnMessage = util.errorAlertReturn("System error"+err.getMessage());
              }
                
               
                return returnMessage;
	}
        
        
        
        @RequestMapping("/inventory/viewincomeexpense")
        public ModelAndView viewInventory(HttpServletRequest request, HttpServletResponse response)throws Exception {
             SessionData temp = new SessionData(request);        
             BaseUrl = temp.BaseUrl(request);
          HttpSession session= request.getSession(false);      
        if(session == null || session.getAttribute("fullname")== null){
            String url = BaseUrl+"login/index.html?redirect=inventory/viewincome_expense.html";
          return new ModelAndView("redirect:"+url);
        }
         Map aclData =  (Map) session.getAttribute("acl");
       
        if(!aclData.containsKey("view_income_expense") || !aclData.containsValue("view_income_expense")){
        String restrictedAccess = BaseUrl+"error/noaccess.html";
            return  new ModelAndView("redirect:"+restrictedAccess);
        }
                ModelAndView model = new ModelAndView("inventory/viewinventory", "appname", "Seraba Accounting");
                 StoreStaff storeStaff = (StoreStaff) session.getAttribute("staff");
                // Integer creator_id = storeStaff.getStaffId(); // we get the id of the current logged in user
                 User user = storeStaff.getUserId();
                 String creator_type = (String) session.getAttribute("userType");
        
              if(creator_type.equalsIgnoreCase("admin")){
                model.addObject("inventorys",inventoryH.listInventory());
                }else{
                model.addObject("inventorys",inventoryH.listUserInventory(user));
                }
              model.addObject("baseUrl", BaseUrl);
                return model;
    }
        
          
        @RequestMapping(value = "/inventory/delete", method = RequestMethod.POST)
        public @ResponseBody String deleteInventory(HttpServletRequest request, HttpServletResponse response) throws Exception{
             SessionData temp = new SessionData(request);        
             BaseUrl = temp.BaseUrl(request);
        error = "";
       HttpSession session= request.getSession(false);  
         if(session==null || session.getAttribute("fullname") == null){
         return util.errorAlertReturn("User is not logged in. Please try to log in");
        }
         
          Map aclData =  (Map) session.getAttribute("acl");
       
        if(!aclData.containsKey("delete_income_expense") || !aclData.containsValue("delete_income_expense")){
       
            return  util.errorAlertReturn("You do not have access to perform this action");
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
         inventory = em.find(Inventory.class,Integer.parseInt(id));
        // product = em.find(Product.class, new Long(Integer.parseInt(id)));
        // error += "<br/>Ths is the product ID "+id+" action is called "+action+product.getProdId().toString();
         
       
         if(id.isEmpty()){
             error += "<br/> Credit does not exists";
         } 
         
         if(action.isEmpty()){
             error += "<br/> Invalid request";
         }
       
         
         if(!error.isEmpty()){
             // there is an error
         returnMessage = util.errorAlertReturn(error);
         }else{
         
          
         inventory.setIsDeleted(1);
         inventory.setTimeModified(util.getCurrentTimeStamp());
         em.merge(inventory);
         em.flush();
         em.getTransaction().commit();
         
         em.close();
         emf.close();
         
          if(!inventory.toString().isEmpty()){
            returnMessage = util.successAlertReturn("Inventory successfully deleted...", "deletecredit");
         
            
         }else{
            returnMessage = util.errorAlertReturn("Unable to delete inventory");
         }
          
         }
        
                } catch (Exception err){
                    
                }
                
          return returnMessage;
    }
}
