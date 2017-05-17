/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sereba.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sereba.model.util.Util;
import com.sereba.model.util.ProductHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.sereba.model.*;
//import java.util.Date;
//import java.util.Locale;
//import java.util.logging.Level;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;
//import javax.xml.bind.PropertyException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.PropertyException;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import com.sereba.model.util.SessionData;
import com.sereba.model.InventoryStock;
import com.sereba.model.util.*;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

//import org.apache.commons.fileupload.*;
//import org.apache.commons.fileupload.util.*;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
/**
 *
 * @author John
 */

@MultipartConfig
@Controller
public class ProductController extends HttpServlet {
    private Util util = new Util();
     private String error = "";
     private String BaseUrl = util.BaseUrl();
     private  Product product = new Product();
     private ProductHelper productH = new ProductHelper();
     private InventoryStock inventoryStock = new InventoryStock();
     private InventoryStockHelper inventoryStockH = new InventoryStockHelper();
    //private Product product = new Product();
     
    private ServletContext context; 
     
     private String tempLink = "images/uploads/products/";
     //String absoluteDiskPath = getServletContext().getRealPath(relativeWebPath);
    private String UPLOAD_DIRECTORY = "";//"C:/Users/John/Documents/NetBeansProjects/Sereba Accounting/web/images/uploads/products/";
   // private String error= "";
    @RequestMapping("/product")
    public ModelAndView helloWorld(HttpServletRequest request, HttpServletResponse response) throws Exception{
        SessionData temp = new SessionData(request);
        BaseUrl = temp.BaseUrl(request);
        HttpSession session= request.getSession(false);       
        if(session==null || session.getAttribute("fullname") == null){
          return new ModelAndView("redirect:"+BaseUrl);
        }
        String message = "<br><div style='text-align:center;'>"
                + "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from CrunchifyHelloWorld.java **********</div><br><br>";

        return new ModelAndView("index/welcome", "message", message);
    }
    
    @RequestMapping(value = "/product/savestock", method = RequestMethod.POST)
        public @ResponseBody String saveStock(HttpServletRequest request, HttpServletResponse response) throws PropertyException,ServletException,IOException,NullPointerException,Exception{
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
       
        if(!aclData.containsKey("add_inventory") || !aclData.containsValue("add_inventory")){
            error += "<br/> You do not have the right(s) to perform this action. Contact your store owner";
        }
              String product_id = request.getParameter("prodId") != null ? request.getParameter("prodId") : "";
              String selling_price =    request.getParameter("selling_price")!= null? request.getParameter("selling_price"):"";
              String cost_price = request.getParameter("cost_price")!=null ? request.getParameter("cost_price"): "";
              String discount = request.getParameter("discount") !=null ? request.getParameter("discount") : "";
              String qty_sold = request.getParameter("prod_qty") != null ? request.getParameter("prod_qty") : "";
             
             

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
            
              if(qty_sold.isEmpty() || qty_sold=="0"){
                  error += "<br/> Please enter the number of quantity sold";
              }


              if(!error.isEmpty()){
                  // there is an error
              returnMessage = util.errorAlertReturn(error);
              }else{
               // no error message. Our inputs are all valid
              Integer prod_qty = Integer.parseInt(qty_sold);
              Double sellingPrice = Double.parseDouble(selling_price);
              Double costPrice = Double.parseDouble(cost_price);
              Double discountSold = Double.parseDouble(discount);
              Integer prodId = Integer.parseInt(product_id);
              
              
               
                 product = em.find(Product.class,prodId);
                
                 product.setProdQty((product.getProdQty() + prod_qty));
                 product.setProdCostPrice(costPrice);
                 product.setProdSellingPrice(sellingPrice);
                 product.setDiscount(discountSold);
                 product.setTimeModified(util.getCurrentTimeStamp());
                 
                 inventoryStock.setProdQty(prod_qty);
                 inventoryStock.setDiscount(discountSold);
                 inventoryStock.setProdId(product);
                 inventoryStock.setCostPrice(costPrice);
                 inventoryStock.setSellingPrice(sellingPrice);
                 inventoryStock.setCreatorId(storeStaff);
                 inventoryStock.setCreatorType(creator_type);
                 inventoryStock.setIsDeleted(0);
                 inventoryStock.setDate(util.getCurrentTimeStamp());
                 inventoryStock.setTimeCreated(util.getCurrentTimeStamp());
                 
                 
              //insert into the database here. We wana create a new credit. We just pass the object to the database
               //persist only on save mode
                     if(!em.contains(inventoryStock)){
                         em.persist(inventoryStock);

                     }
               em.getTransaction().commit();
               em.close();
               emf.close();
                 
              if(!inventoryStock.toString().isEmpty()){
                 returnMessage = util.successAlertReturn("Stock successfully added...", "addinventory");


              }else{
                 returnMessage = util.errorAlertReturn("Unable to add a new stock's record to the database");
              }
                 }
             
            }catch (Exception err){
                  returnMessage = util.errorAlertReturn("System error."+ err.getMessage());
            }
             return returnMessage;

        }

        
    @RequestMapping(value = "/product/editproduct", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView editProduct(HttpServletRequest request, HttpServletResponse response) throws PropertyException,ServletException,IOException,NullPointerException,Exception{
        error = "";
        SessionData temp = new SessionData(request);
        BaseUrl = temp.BaseUrl(request);
        HttpSession session= request.getSession(false);  
         if(session==null || session.getAttribute("fullname") == null){
          String url = BaseUrl+"login/index.html?redirect=product/viewproduct.html";
          return new ModelAndView("redirect:"+url);
        }
        
         
        context = request.getServletContext();
        String path = context.getRealPath("/");
       
        UPLOAD_DIRECTORY = path.replaceAll("\\\\", "/")+tempLink;
       // error += UPLOAD_DIRECTORY;
          Map aclData =  (Map) session.getAttribute("acl");
       
        if(!aclData.containsKey("edit_product") || !aclData.containsValue("edit_product")){
        String restrictedAccess = BaseUrl+"error/noaccess.html";
            return  new ModelAndView("redirect:"+restrictedAccess);
        }
        StoreStaff storeStaff = (StoreStaff) session.getAttribute("staff");
        Integer creator_id = storeStaff.getStaffId(); // we get the id of the current logged in user
        User user = storeStaff.getUserId();
        String creator_type = (String) session.getAttribute("userType");
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
        EntityManager em = emf.createEntityManager();
        String prodId = request.getParameter("id") != null ? request.getParameter("id") : "";
        
        em.getTransaction().begin();
        Boolean display = true;
        // Configure a repository (to ensure a secure temp location is used)
        String returnMessage = "";
        ModelAndView model = new ModelAndView("product/editproduct", "message", "Sereba Accounting") ;
        boolean isPost = "POST".equals(request.getMethod());
       
                try {
                    if(prodId.isEmpty()){
                    error += "<br/> You need to select a product to edit";
                    display = false;
                    }
                    if(error.isEmpty()) {
                    product = em.find(Product.class,Integer.parseInt(prodId));
                    
                    if(product == null){
                    error += "<br/> Product not found";
                    display = false;
                    }else if(product.getIsDeleted() == 1){
                        error += "<br/> This product have been deleted";
                        display = false;
                    }
                    else{
                    
                    
                     if(isPost){
                    
                     //em.getTransaction().begin();
                     
         //error = "This is the error "+request.getParameter("").toString()+"";
         String prod_name = request.getParameter("prod_name") != null ? request.getParameter("prod_name") : "";
         String product_selling_price = request.getParameter("prod_selling_price")!= null? request.getParameter("prod_selling_price"):"";
         String product_cost_price = request.getParameter("prod_cost_price")!= null? request.getParameter("prod_cost_price"):"";
       //  String product_qty = request.getParameter("prod_qty") !=null ? request.getParameter("prod_qty") : "";
         String prod_measurement = request.getParameter("prod_measurement") != null ? request.getParameter("prod_measurement") : "";
         String product_discount = request.getParameter("discount") != null ? request.getParameter("discount") : "";
         //String prod_image_path = request.getParameter("prod_image_path") !=null ? request.getParameter("prod_image_path") : "";
         //String creator_type = ""; // we gee the uusr type from the session
        
         
         String prod_image_path = null;
         
         
  
         
                  
         
         if(prod_name.isEmpty()){
             error += "<br/> Product name is a required field";
         } 
         
         if(product_selling_price.isEmpty()){
             error += "<br/> Product selling price is a required field";
         }
         if(product_cost_price.isEmpty()){
             error += "<br/> Product cost price is a required field";
         }
//         if(product_qty.isEmpty()){
//             error += "<br/> Product quantity is a required field";
//         }
         
         if(prod_measurement.isEmpty()){
             error += "<br/> Product measurement is a required field";
         }
         
         
         if(!product.getProdName().equals(prod_name) ){
         validateProductData(request,user);
         }
         
       
         if(error.isEmpty()){
         //validateOtherInput
          if((request.getPart("prod_image") != null ) && request.getPart("prod_image").getSize() > 0 ){
             
              prod_image_path = uploadProductImage(request,prod_image_path);
         }
        
         }
         
         
       
         
         if(!error.isEmpty()){
             // there is an error
         returnMessage = util.errorAlertReturn(error);
         }else{
         
         
         
         
         //Integer prod_qty = Integer.parseInt(product_qty);
         Double prod_selling_price = Double.parseDouble(product_selling_price);
         Double prod_cost_price = Double.parseDouble(product_cost_price);
         Double  discount = Double.parseDouble(product_discount);
         
         
         product.setCreatorId(storeStaff);
               
         //product.setCreatorId(creator_id);
         product.setCreatorType(creator_type);
         if(prod_image_path!=null){
         product.setProdImagePath(prod_image_path);
         }
         product.setDiscount(discount);
         product.setProdName(prod_name);
         product.setProdCostPrice(prod_cost_price);
         product.setProdSellingPrice(prod_selling_price);
         product.setProdMeasurement(prod_measurement);
         //product.setProdQty(prod_qty);
         product.setIsDeleted(0);
         product.setTimeModified(util.getCurrentTimeStamp());
         
         
         //persist only on save mode
          
               
                em.getTransaction().commit();
                
                em.close();
                emf.close();
                
         // the insertion operation is to be done here
          if(!product.toString().isEmpty()){
            returnMessage = util.successAlertReturn("Product successfully Updated...", "editproduct");
         
            
         }else{
            returnMessage = util.errorAlertReturn("Unable to update product to the database");
         }
          
           
         
         }
         
         System.out.println("This is the return message "+returnMessage);
          
//         model.addObject("appname", "Sereba Accounting");
//         //model.addObject("product", product);
//         model.addObject("returnMessage","This is the message and the bimbo peopel");
                  }
                }
             }
                    if(!error.isEmpty()){
                         returnMessage = util.errorAlertReturn(error);
                   }
                }catch (Exception err){
                   returnMessage = util.errorAlertReturn("Error "+err.getMessage());
                }
                
                
        
             model.addObject("appname", "Sereba Accounting");
             model.addObject("product", product);
             model.addObject("baseUrl",BaseUrl);
             model.addObject("returnMessage",returnMessage);
             model.addObject("display",display);
             SessionData rSession = new SessionData(request);
                return model;
    }
    
   @RequestMapping(value = "/product/productdata", method = RequestMethod.POST)
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
   
   @RequestMapping(value = "/product/viewinventory")
   public ModelAndView viewInventory(HttpServletRequest request, HttpServletResponse response)throws Exception{
       ModelAndView model = new ModelAndView("product/viewinventory","appname","Sereba Accounting");
       List<Object> resultList = new ArrayList<Object>();
       String returnMessage = "";
       try{
            SessionData temp = new SessionData(request);        
         BaseUrl = temp.BaseUrl(request);
                HttpSession session= request.getSession(false);  
        if(session == null || session.getAttribute("fullname")== null){
         String url = BaseUrl+"login/index.html?redirect=product/viewinventory.html";
          return new ModelAndView("redirect:"+url);
        }
        
         Map aclData =  (Map) session.getAttribute("acl");
       
        if(!aclData.containsKey("view_inventory") || !aclData.containsValue("view_inventory")){
        String restrictedAccess = BaseUrl+"error/noaccess.html";
            return  new ModelAndView("redirect:"+restrictedAccess);
        }
        
         StoreStaff storeStaff = (StoreStaff) session.getAttribute("staff");
        Integer creator_id = storeStaff.getStaffId(); // we get the id of the current logged in user
        User user = storeStaff.getUserId();
        Integer userId = user.getUserId();
        String creator_type = (String) session.getAttribute("userType");
         
         
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
        EntityManager em = emf.createEntityManager();
        
        String product_id = request.getParameter("id")!=null?request.getParameter("id"):"";
        if(product_id.isEmpty() || product_id ==  null){
            String productUrl = BaseUrl+"product/viewproduct.html";
           return  new ModelAndView("redirect:"+productUrl);
        }
        Integer prodId = Integer.parseInt(product_id);
        product = em.find(Product.class, prodId);
        
        if(product.toString().isEmpty()){
            error += "<br/> Product with this product id does not exists in the database";
        }else{
            if(product.getIsDeleted()==1){
              error += "<br/> Product has been deleted from the database";
            }
            else{
            resultList = inventoryStockH.getProductInventory(prodId,userId);
                
            }
            
            
        }
        
        if(!error.isEmpty()){
           returnMessage = util.errorAlertReturn(error); 
        }
        
        
       }catch(Exception err){
           System.out.println("System error :"+err.getMessage());
       }
       model.addObject("baseUrl",BaseUrl);
       model.addObject("returnMessage",returnMessage);
       model.addObject("inventoryList",resultList);
       return model;
   }
   
   @RequestMapping("/product/addinventory")
    public ModelAndView addInventory(HttpServletRequest request, HttpServletResponse response)throws Exception {
         SessionData temp = new SessionData(request);        
         BaseUrl = temp.BaseUrl(request);
                HttpSession session= request.getSession(false);  
        if(session == null || session.getAttribute("fullname")== null){
         String url = BaseUrl+"login/index.html?redirect=product/addinventory.html";
          return new ModelAndView("redirect:"+url);
        }
        
         Map aclData =  (Map) session.getAttribute("acl");
       
        if(!aclData.containsKey("add_inventory") || !aclData.containsValue("add_inventory")){
        String restrictedAccess = BaseUrl+"error/noaccess.html";
            return  new ModelAndView("redirect:"+restrictedAccess);
        }
                error = "";
                String returnMessage = "";
                
                StoreStaff storeStaff = (StoreStaff) session.getAttribute("staff");
                Integer creator_id = storeStaff.getStaffId(); // we get the id of the current logged in user
                User user = storeStaff.getUserId();
                String creator_type = (String) session.getAttribute("userType");
        
                ModelAndView model = new ModelAndView("product/addinventory", "appname", "Sereba Accounting");
                model.addObject("products",productH.listUserProducts(user));
                model.addObject("baseUrl",BaseUrl);
                 
                return model;
    }
     
    @RequestMapping(value = "/product/addproduct", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView addProduct(HttpServletRequest request, HttpServletResponse response) throws PropertyException,ServletException,IOException,NullPointerException,Exception{
        SessionData temp = new SessionData(request);
        BaseUrl = temp.BaseUrl(request);
        HttpSession session= request.getSession(false);  
        if(session == null || session.getAttribute("fullname")== null){
         String url = BaseUrl+"login/index.html?redirect=product/addproduct.html";
          return new ModelAndView("redirect:"+url);
        }
        
         Map aclData =  (Map) session.getAttribute("acl");
       
        if(!aclData.containsKey("add_product") || !aclData.containsValue("add_product")){
        String restrictedAccess = BaseUrl+"error/noaccess.html";
            return  new ModelAndView("redirect:"+restrictedAccess);
        }
        error = "";
        StoreStaff storeStaff = (StoreStaff) session.getAttribute("staff");
        Integer creator_id = storeStaff.getStaffId(); // we get the id of the current logged in user
        User user = storeStaff.getUserId();
        String creator_type = (String) session.getAttribute("userType");
         
         
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // Configure a repository (to ensure a secure temp location is used)
        String returnMessage = "";
        ModelAndView model = new ModelAndView("product/addproduct", "message", "Sereba Accounting") ;
        boolean isPost = "POST".equals(request.getMethod());
        if(isPost){
                try {
         //String root =  getServletContext().getRealPath("/");
         
        context = request.getServletContext();
        String path = context.getRealPath("/");
       
        UPLOAD_DIRECTORY = path.replaceAll("\\\\", "/")+tempLink;
        //error += UPLOAD_DIRECTORY;
        //error += "<br/> "+realContextPath;
         //error = "This is the error "+request.getParameter("").toString()+"";
         String prod_name = request.getParameter("prod_name") != null ? request.getParameter("prod_name") : "";
         String product_selling_price = request.getParameter("prod_selling_price")!= null? request.getParameter("prod_selling_price"):"";
         String product_cost_price = request.getParameter("prod_cost_price")!= null? request.getParameter("prod_cost_price"):"";
         String product_qty = request.getParameter("prod_qty") !=null ? request.getParameter("prod_qty") : "";
         String prod_measurement = request.getParameter("prod_measurement") != null ? request.getParameter("prod_measurement") : "";
         String product_discount = request.getParameter("discount") != null ? request.getParameter("discount") : "";
         //String prod_image_path = request.getParameter("prod_image_path") !=null ? request.getParameter("prod_image_path") : "";
         //String creator_type = ""; // we gee the uusr type from the session
         
         
         String prod_image_path = null;
//         StoreStaff storestaff = new StoreStaff();
//         storestaff.setStaffId(creator_id);
         
  
         
                  
         
         if(prod_name.isEmpty()){
             error += "<br/> Product name is a required field";
         } 
         
         if(product_selling_price.isEmpty()){
             error += "<br/> Product selling price is a required field";
         }
         if(product_cost_price.isEmpty()){
             error += "<br/> Product cost price is a required field";
         }
         if(product_qty.isEmpty()){
             error += "<br/> Product quantity is a required field";
         }
         
         if(prod_measurement.isEmpty()){
             error += "<br/> Product measurement is a required field";
         }
         
       
         validateProductData(request,user);
         
         
        
         if(error.isEmpty()){
         //validateOtherInput
          if(( request.getPart("prod_image") != null ) ){
              prod_image_path = uploadProductImage(request,prod_image_path);
         }else{
              error += "<br/> You need to select an image";
          }
        
         }
         
         
       
         
         if(!error.isEmpty()){
             // there is an error
         returnMessage = util.errorAlertReturn(error);
         }else{
         
         
         
         
         Integer prod_qty = Integer.parseInt(product_qty);
         Double prod_selling_price = Double.parseDouble(product_selling_price);
         Double prod_cost_price = Double.parseDouble(product_cost_price);
         Double  discount = Double.parseDouble(product_discount);
         
         
         product.setCreatorId(storeStaff);
               
         //product.setCreatorId(creator_id);
         product.setCreatorType(creator_type);
         product.setProdImagePath(prod_image_path);
         product.setDiscount(discount);
         product.setProdName(prod_name);
         product.setProdCostPrice(prod_cost_price);
         product.setProdSellingPrice(prod_selling_price);
         product.setProdMeasurement(prod_measurement);
         product.setProdQty(prod_qty);
         product.setIsDeleted(0);
         product.setTimeCreated(util.getCurrentTimeStamp());
         
         
         //persist only on save mode
                if(!em.contains(product)){
                    em.persist(product);
                   
                }
               
                em.getTransaction().commit();
                
                em.close();
                emf.close();
                
                //EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
           emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
           em = emf.createEntityManager();
           em.getTransaction().begin();
           
          
                 product = productH.findByLastId();
                 product.setProdQty((product.getProdQty() + prod_qty));
                
                
                 inventoryStock.setProdQty(prod_qty);
                 inventoryStock.setDiscount(discount);
                 inventoryStock.setProdId(product);
                 inventoryStock.setCostPrice(prod_cost_price);
                 inventoryStock.setSellingPrice(prod_selling_price);
                 inventoryStock.setCreatorId(storeStaff);
                 inventoryStock.setCreatorType(creator_type);
                 inventoryStock.setIsDeleted(0);
                 inventoryStock.setDate(util.getCurrentTimeStamp());
                 inventoryStock.setTimeCreated(util.getCurrentTimeStamp());
                 
                 if(!em.contains(inventoryStock)){
                    em.persist(inventoryStock);
                   
                }
                 
                 em.getTransaction().commit();
                 em.close();
                 emf.close();
                 
         // the insertion operation is to be done here
          if(!product.toString().isEmpty()){
            returnMessage = util.successAlertReturn("Product successfully added...", "addproduct");
         
            
         }else{
            returnMessage = util.errorAlertReturn("Unable to add a new product to the database");
         }
          
           
         
         }
         
         System.out.println("This is the return message "+returnMessage);
          
//         model.addObject("appname", "Sereba Accounting");
//         //model.addObject("product", product);
//         model.addObject("returnMessage","This is the message and the bimbo peopel");
                } catch (Exception err){
                   returnMessage = util.errorAlertReturn("Error "+err.getMessage());
                }
                
        }else{
            
        }
             model.addObject("appname", "Sereba Accounting");
             model.addObject("product", product);
             model.addObject("baseUrl",BaseUrl);
             model.addObject("returnMessage",returnMessage);
             SessionData rSession = new SessionData(request);
                return model;
    }
    
    
    public void validateProductData(HttpServletRequest request, User user) throws Exception{
         
      
      
     EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
     EntityManager em = emf.createEntityManager();
     
     try{
         //em.getTransaction().begin();
//     Query jpqlQuery = em.createQuery("SELECT p FROM Product p LEFT JOIN StoreStaff s LEFT JOIN User u WHERE s.userId = :userId AND p.prodName = :prodName");
//     jpqlQuery.setParameter("prodName", request.getParameter("prod_name"));
//     jpqlQuery.setParameter("userId",creator_userid);
//     List<Product> productDetails = jpqlQuery.getResultList();
//     error += "This is the message "+productDetails.toString();
        Query jpqlQuery = em.createNamedQuery("Product.findByProdNameCreatorIsDeleted");
        jpqlQuery.setParameter("prodName", request.getParameter("prod_name"));
        jpqlQuery.setParameter("userId",user);
        jpqlQuery.setParameter("isDeleted", 0);
        List<Product> productDetails = jpqlQuery.getResultList();
        if(!productDetails.isEmpty()){
         error += "<br/> Product name exists in the database";
        }
        
     }catch(Exception err){
     System.out.println("This is the message"+ err.getMessage());
     error += "<br/> "+err.getMessage();
     }
     
    }
    
    
    @RequestMapping(value = "/product/delete", method = RequestMethod.POST)
    public @ResponseBody String deleteProduct(HttpServletRequest request, HttpServletResponse response) throws Exception{
        error = "";
        SessionData temp = new SessionData(request);
        BaseUrl = temp.BaseUrl(request);
       HttpSession session= request.getSession(false);  
         if(session==null || session.getAttribute("fullname") == null){
        return util.errorAlertReturn(" User is not logged in. Please try to log in");
        }

          Map aclData =  (Map) session.getAttribute("acl");
       
        if(!aclData.containsKey("delete_product") || !aclData.containsValue("delete_product")){
       
         return util.errorAlertReturn("You do not have access to this page. Contact your store owner");
        }

    
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
        EntityManager em = emf.createEntityManager();
        String returnMessage = "";
                try {
          
        
         
         //error = "This is the error "+request.getParameter("").toString()+"";
         String id = request.getParameter("id") != null ? request.getParameter("id") : "";
         String action = request.getParameter("action")!= null? request.getParameter("action"):"";
          em.getTransaction().begin();
         product = em.find(Product.class,Integer.parseInt(id));
        // product = em.find(Product.class, new Long(Integer.parseInt(id)));
        // error += "<br/>Ths is the product ID "+id+" action is called "+action+product.getProdId().toString();
         
       
         if(id.isEmpty()){
             error += "<br/> Product does not exists";
         } 
         
         if(action.isEmpty()){
             error += "<br/> Invalid request";
         }
       
         
         if(!error.isEmpty()){
             // there is an error
         returnMessage = util.errorAlertReturn(error);
         }else{
         
          
         product.setIsDeleted(1);
         product.setTimeModified(util.getCurrentTimeStamp());
         em.merge(product);
         em.flush();
         em.getTransaction().commit();
         
         em.close();
         emf.close();
         
          if(!product.toString().isEmpty()){
            returnMessage = util.successAlertReturn("Product successfully deleted...", "deleteproduct");
         
            
         }else{
            returnMessage = util.errorAlertReturn("Unable to delete product");
         }
          
         }
        
                } catch (Exception err){
                    
                }
                SessionData rSession = new SessionData(request);
          return returnMessage;
    }
    
    
      /*TP: Upload of the Agent's picture is done here*/
    private String uploadProductImage(HttpServletRequest request,String prodFilename)throws PropertyException,ServletException,IOException{
          //String root = getServletContext().getRealPath("/");
         
          long unixTime = System.currentTimeMillis() / 1000L;
     
                  if (request.getPart("prod_image")!=null){
                /*TP: Agent personal file upload*/
                        Part prodPartImage = request.getPart("prod_image"); 
                        String myName = getFileName(prodPartImage);
                         
                       
                            
                        int fnameLength = myName.length();
                        String type = prodPartImage.getHeader("content-type");
                        int startingPoint = (type.equals("image/jpeg"))?fnameLength - 5:fnameLength - 4;
                       
                        myName = myName.substring(startingPoint,fnameLength);
                        prodFilename = "product_"+unixTime+myName;
                        
                        photoImageUpload(prodFilename,UPLOAD_DIRECTORY,prodPartImage);
                  }
                      // error +=" This is the upload section";
                   
      return prodFilename;
     
     }
    
    
    /*TP: This is a generic method for image upload*/
    private void photoImageUpload(String prodFilename,String path, Part prodPartImage){
    OutputStream fout = null;
    InputStream filecontent = null;
   
      String type = prodPartImage.getHeader("content-type");
   if(type.equals("image/jpeg") || type.equals("image/png") || type.equals("image/jpg") || type.equals("image/gif") || type.equals("image/bmp") )
    {
          try {
            fout = new FileOutputStream(new File(path  + prodFilename));
          
            filecontent = prodPartImage.getInputStream();
            
            int read = 0;
            final byte[] bytes = new byte[32*1024];
// 
            while ((read =filecontent.read(bytes)) != -1) {
                fout.write(bytes, 0, read);
            }
            fout.flush();
            fout.close();
            } catch (Exception e) { 
         // errorMessages.put("error7","Invalid File Format");
         error += "<br/>Invalid File Format for the product image";
        }  
    } else { 
        //errorMessages.put("error7","Invalid File Format");
        error += "<br/>Invalid File Format for the product image";
        } 
    }
    
  
     /*TP: Getting the file name of an uploaded image*/
    private String getFileName(final Part part) {
            final String partHeader = part.getHeader("content-disposition");
            //LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
         for (String content : part.getHeader("content-disposition").split(";")) {
                if (content.trim().startsWith("filename")) {
                    return content.substring(
                    content.indexOf('=') + 1).trim().replace("\"", "");
                    }
                }
            return null;
        }
    
    
    @RequestMapping("/product/viewproduct")
    public ModelAndView manageProduct(HttpServletRequest request, HttpServletResponse response) throws Exception{
        SessionData temp = new SessionData(request);
        BaseUrl = temp.BaseUrl(request);
             HttpSession session= request.getSession(false);  
             
        if(session==null || session.getAttribute("fullname") == null){
          String url = BaseUrl+"login/index.html?redirect=product/viewproduct.html";
          return new ModelAndView("redirect:"+url);
        }
         Map aclData =  (Map) session.getAttribute("acl");
       
        if(!aclData.containsKey("view_product") || !aclData.containsValue("view_product")){
        String restrictedAccess = BaseUrl+"error/noaccess.html";
            return  new ModelAndView("redirect:"+restrictedAccess);
        }
//        //session.invalidate();
//        session = request.getSession(true);
//        session.setAttribute("error", "error message");
        
         
                ModelAndView model = new ModelAndView("product/viewproduct", "appname", "Seraba Accounting");
                try {
                 StoreStaff storeStaff = (StoreStaff) session.getAttribute("staff");
                // Integer creator_id = storeStaff.getStaffId(); // we get the id of the current logged in user
                 User user = storeStaff.getUserId();
                 String creator_type = (String) session.getAttribute("userType");
        
                
//        
                if(creator_type.equalsIgnoreCase("admin")){
                model.addObject("products",productH.listProducts());
                }else{
                model.addObject("products",productH.listUserProducts(user));
                }
                }catch(Exception err){
                    System.out.println("System error :"+err.getMessage());
                }
                
                //model.addObject("result",util.errorAlertReturn(listUserProducts(user_id).size().toString()));
                model.addObject("appname", "Sereba Accounting");
                model.addObject("baseUrl",BaseUrl);
                SessionData rSession = new SessionData(request);
                return model;
        
    }
    
    @RequestMapping("/product/view/id/*")
    public ModelAndView viewProduct(HttpServletRequest request, HttpServletResponse response)throws Exception {
        SessionData temp = new SessionData(request);
        BaseUrl = temp.BaseUrl(request);
           HttpSession session= request.getSession(false);  
         if(session==null || session.getAttribute("fullname") == null){
         String url = BaseUrl+"login/index.html?redirect=product/viewproduct.html";
          return new ModelAndView("redirect:"+url);
        }
         
         
        String message = "<br><div style='text-align:center;'>"
                + "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from CrunchifyHelloWorld.java **********</div><br><br>";

        return new ModelAndView("index/welcome", "message", message);
    }
}
