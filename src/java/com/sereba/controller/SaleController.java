/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sereba.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import com.sereba.model.*;
import com.sereba.model.util.SessionData;
import com.sereba.model.util.*;
import com.sereba.model.util.Util;
import com.sereba.model.util.ProductHelper;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sereba.model.util.SaleHelper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.xml.bind.PropertyException;
/**
 *
 * @author John
 */

@Controller
public class SaleController extends HttpServlet {
    private Util util = new Util();
    private String error = "";
    private String BaseUrl = util.BaseUrl();
    private SaleHelper saleH = new SaleHelper();
    private Product product = new Product();
    private Sales sale =  new Sales();
   
    private ProductHelper productH = new ProductHelper();
    
    @RequestMapping("/sale/addsale")
    public ModelAndView addSale(HttpServletRequest request, HttpServletResponse response)throws Exception {
         SessionData temp = new SessionData(request);        
         BaseUrl = temp.BaseUrl(request);
                HttpSession session= request.getSession(false);  
        if(session == null || session.getAttribute("fullname")== null){
         String url = BaseUrl+"login/index.html?redirect=sale/addsale.html";
          return new ModelAndView("redirect:"+url);
        }
        
         Map aclData =  (Map) session.getAttribute("acl");
       
        if(!aclData.containsKey("add_sale") || !aclData.containsValue("add_sale")){
        String restrictedAccess = BaseUrl+"error/noaccess.html";
            return  new ModelAndView("redirect:"+restrictedAccess);
        }
                error = "";
                String returnMessage = "";
                
                StoreStaff storeStaff = (StoreStaff) session.getAttribute("staff");
                Integer creator_id = storeStaff.getStaffId(); // we get the id of the current logged in user
                User user = storeStaff.getUserId();
                String creator_type = (String) session.getAttribute("userType");
        
                ModelAndView model = new ModelAndView("sale/addsale", "appname", "Sereba Accounting");
                model.addObject("products",productH.listUserProducts(user));
                model.addObject("baseUrl",BaseUrl);
                 
                return model;
    }
    
     // the ajax method for the add sale form
        @RequestMapping(value = "/sale/savesale", method = RequestMethod.POST)
        public @ResponseBody String saveSale(HttpServletRequest request, HttpServletResponse response) throws PropertyException,ServletException,IOException,NullPointerException,Exception{
         SessionData temp = new SessionData(request);        
         BaseUrl = temp.BaseUrl(request);
//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullname", "Fullname is a required field");
            String returnMessage = "";
            try {
              EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
              EntityManager em = emf.createEntityManager();
              em.getTransaction().begin();
              // calling the validator method to validate the inout gotten from the register form
               error = "";
               HttpSession session= request.getSession(false);  
             if(session == null || session.getAttribute("fullname")== null){
              error += " User is not logged in. Kindly try to login again";
             }
             
              Map aclData =  (Map) session.getAttribute("acl");
       
        if(!aclData.containsKey("add_sale") || !aclData.containsValue("add_sale")){
            error += "<br/> You do not have the right(s) to perform this action. Contact your store owner";
        }
              String product_id = request.getParameter("prodId") != null ? request.getParameter("prodId") : "";
              String selling_price =    request.getParameter("selling_price")!= null? request.getParameter("selling_price"):"";
              String discount = request.getParameter("discount") !=null ? request.getParameter("discount") : "";
              String qty_sold = request.getParameter("qty_sold") != null ? request.getParameter("qty_sold") : "";
              String amount = request.getParameter("amount") != null ? request.getParameter("amount") : "";
             

             StoreStaff storeStaff = (StoreStaff) session.getAttribute("staff");
             Integer creator_id = storeStaff.getStaffId(); // we get the id of the current logged in user
             User user = storeStaff.getUserId();
             String creator_type = (String) session.getAttribute("userType");

              if(product_id.isEmpty()){
                  error += "<br/> You need to select a product";
              }

              if(selling_price.isEmpty()){
                  error += "<br/> Product selling price is empty because no product was selected";
              }
            

              if(amount.isEmpty() || amount=="0"){
                  error += "<br/> Amount cannot be empty. Please be sure to enter the number of quantities sold";
              }

              if(qty_sold.isEmpty() || qty_sold=="0"){
                  error += "<br/> Please enter the number of quantity sold";
              }


              if(!error.isEmpty()){
                  // there is an error
              returnMessage = util.errorAlertReturn(error);
              }else{
               // no error message. Our inputs are all valid
              Integer prod_qty = Integer.parseInt(qty_sold);
              Double amountSold = Double.parseDouble(amount);
              Double discountSold = Double.parseDouble(discount);
              Integer prodId = Integer.parseInt(product_id);
              
              
               
                 product = em.find(Product.class,prodId);
                 if(product.getProdQty() < prod_qty){
                 error += "<br/> The number of quantities in stock is lower than the number of quantities you want to sell. "+product.getProdQty()+" quantity(ies) left.";
                 returnMessage = util.errorAlertReturn(error);
                 } else{
                 sale.setAmount(amountSold);
                 sale.setDiscount(discountSold);
                 sale.setQtySold(prod_qty);
                 sale.setProdId(product);
                 sale.setCostPrice(product.getProdCostPrice());
                 sale.setSellingPrice(product.getProdSellingPrice());
                 sale.setCreatorId(storeStaff);
                 sale.setCreatorType(creator_type);
                 sale.setIsDeleted(0);
                 sale.setDate(util.getCurrentTimeStamp());
                 sale.setTimeCreated(util.getCurrentTimeStamp());
                 
                 product.setProdQty(product.getProdQty() - prod_qty) ;
                 product.setTimeModified(util.getCurrentTimeStamp());
              //insert into the database here. We wana create a new credit. We just pass the object to the database
               //persist only on save mode
                     if(!em.contains(sale)){
                         em.persist(sale);

                     }
               em.getTransaction().commit();
               em.close();
               emf.close();
                 
              if(!sale.toString().isEmpty()){
                 returnMessage = util.successAlertReturn("Sales successfully added...", "addsale");


              }else{
                 returnMessage = util.errorAlertReturn("Unable to add a new sale's record to the database");
              }
                 }
             }
            }catch (Exception err){
                  returnMessage = util.errorAlertReturn("System error."+ err.getMessage());
            }
             return returnMessage;

        }

    
    @RequestMapping(value = "/sale/productdata", method = RequestMethod.POST)
    public @ResponseBody String fetchProductData(HttpServletRequest request, HttpServletResponse response)throws Exception{
         SessionData temp = new SessionData(request);        
         BaseUrl = temp.BaseUrl(request);
        error = "";
        String returnMessage = "";
         HttpSession session= request.getSession(false);  
         if(session==null || session.getAttribute("fullname") == null){
         error += "<br/> User is not logged in. Please try to log in";
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
        EntityManager em = emf.createEntityManager();
        Map<String, String> map = new HashMap<String, String>();
         try{
            
             if(error.isEmpty()){
         String id = request.getParameter("id") != null ? request.getParameter("id") : "";
         String action = request.getParameter("action")!= null? request.getParameter("action"):"";
         if(id.isEmpty()){
             error += "<br/> Please select a valid product";
         }
         if(action.isEmpty()){
             error += "<br/> Please select a valid action";
         }
         em.getTransaction().begin();
         product = em.find(Product.class,Integer.parseInt(id));
         
         if(product.toString()!=null && !product.toString().isEmpty()){
         if(product.getIsDeleted().equals(1)){
             error += "<br/> Product has been deleted earlier";
         }else{
              
               map.put("id", product.getProdId().toString());
               map.put("prodName",product.getProdName().toString());
               map.put("prodCostPrice",product.getProdCostPrice().toString());
               map.put("prodSellingPrice",product.getProdSellingPrice().toString());
               map.put("prodQty",product.getProdQty().toString());
               map.put("prodMeasurement",product.getProdMeasurement());
               map.put("discount",product.getDiscount().toString());
                 
            
   
         }
             
             
         }else{
             error +="<br/> Product not found";
         }
         
         }
             
             
            if(!error.isEmpty()){
                returnMessage = util.errorAlertReturn(error);
             }
         
         }catch(Exception err){
             returnMessage = util.errorAlertReturn(err.getMessage());
         }
         map.put("returnMessage",returnMessage);
         
         Gson gson = new GsonBuilder().create();
        String jsonResponse = gson.toJson(map);
        return jsonResponse;
    }
   
    @RequestMapping("/sale/viewsale")
    public ModelAndView viewSale(HttpServletRequest request, HttpServletResponse response)throws Exception {
         SessionData temp = new SessionData(request);        
         BaseUrl = temp.BaseUrl(request); 
        HttpSession session= request.getSession(false);      
        if(session == null || session.getAttribute("fullname")== null){
           String url = BaseUrl+"login/index.html?redirect=sale/viewsale.html";
          return new ModelAndView("redirect:"+url);
        }
           ModelAndView model = new ModelAndView("sale/viewsale", "appname", "Seraba Accounting");
           try{
         Map aclData =  (Map) session.getAttribute("acl");
       
        if(!aclData.containsKey("view_sale") || !aclData.containsValue("view_sale")){
        String restrictedAccess = BaseUrl+"error/noaccess.html";
            return  new ModelAndView("redirect:"+restrictedAccess);
        }
             
                 StoreStaff storeStaff = (StoreStaff) session.getAttribute("staff");
                // Integer creator_id = storeStaff.getStaffId(); // we get the id of the current logged in user
                 User user = storeStaff.getUserId();
                 String creator_type = (String) session.getAttribute("userType");
        
              if(creator_type.equalsIgnoreCase("admin")){
                model.addObject("sales",saleH.listSales());
                }else{
                model.addObject("sales",saleH.listUserSales(user));
                }
           }catch(Exception err){
               System.out.println("System error :"+err.getMessage());
           }
              model.addObject("baseUrl", BaseUrl);
                return model;
    }
}
