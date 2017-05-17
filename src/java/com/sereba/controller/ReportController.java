/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sereba.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sereba.model.util.*;
import com.sereba.model.*;
import static com.sun.jmx.mbeanserver.Util.cast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 *
 * @author John
 */

@Controller
public class ReportController {
    private String error = "";
    private Sales sale = new Sales();
    private Product product = new Product();
    private SaleHelper saleH = new SaleHelper();
    private Util util = new Util();
    private String BaseUrl = util.BaseUrl();
    private ProductHelper productH = new ProductHelper();
    private ReportHelper reportH = new ReportHelper();
   
   
    
    
    @RequestMapping("/report")
    public ModelAndView helloWorld() {

        String message = "<br><div style='text-align:center;'>"
                + "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from CrunchifyHelloWorld.java **********</div><br><br>";

        return new ModelAndView("index/welcome", "message", message);
    }
    
    @RequestMapping("/report/averagesales")
    public ModelAndView averagesales(HttpServletRequest request,HttpServletResponse response)throws Exception{
        ModelAndView model = new ModelAndView("report/averagesales","appname","Sereba Accounting");
        try{
            SessionData temp = new SessionData(request);        
         BaseUrl = temp.BaseUrl(request);
                HttpSession session= request.getSession(false);  
        
        if(session == null || session.getAttribute("fullname")== null){
         String url = BaseUrl+"login/index.html?redirect=report/averagesales.html";
          return new ModelAndView("redirect:"+url);
        }
        StoreStaff storeStaff = (StoreStaff) session.getAttribute("staff");
         Integer creator_id = storeStaff.getStaffId(); // we get the id of the current logged in user
         User user = storeStaff.getUserId();
         String creator_type = (String) session.getAttribute("userType");
        
         Map aclData =  (Map) session.getAttribute("acl");
         
       
        if(!aclData.containsKey("reports") || !aclData.containsValue("reports")){
        String restrictedAccess = BaseUrl+"error/noaccess.html";
            return  new ModelAndView("redirect:"+restrictedAccess);
        }
        error = "";
        String returnMessage = ""; 
        
        model.addObject("products",productH.listUserProducts(user));
        
        }catch(Exception err){
            System.out.println("System error :"+err.getMessage());
        }
       
        
        
        model.addObject("baseUrl",BaseUrl);
        return model;
    }
    
    
     @RequestMapping("/report/salesfreq")
    public ModelAndView salesFrequency(HttpServletRequest request,HttpServletResponse response)throws Exception{
        ModelAndView model = new ModelAndView("report/salesfreq","appname","Sereba Accounting");
        try{
            SessionData temp = new SessionData(request);        
         BaseUrl = temp.BaseUrl(request);
                HttpSession session= request.getSession(false);  
        
        if(session == null || session.getAttribute("fullname")== null){
         String url = BaseUrl+"login/index.html?redirect=report/salesfreq.html";
          return new ModelAndView("redirect:"+url);
        }
        StoreStaff storeStaff = (StoreStaff) session.getAttribute("staff");
         Integer creator_id = storeStaff.getStaffId(); // we get the id of the current logged in user
         User user = storeStaff.getUserId();
         String creator_type = (String) session.getAttribute("userType");
        
         Map aclData =  (Map) session.getAttribute("acl");
         
       
        if(!aclData.containsKey("reports") || !aclData.containsValue("reports")){
        String restrictedAccess = BaseUrl+"error/noaccess.html";
            return  new ModelAndView("redirect:"+restrictedAccess);
        }
        error = "";
        String returnMessage = ""; 
        
        model.addObject("products",productH.listUserProducts(user));
        
        }catch(Exception err){
            System.out.println("System error :"+err.getMessage());
        }
       
        
        
        model.addObject("baseUrl",BaseUrl);
        return model;
    }
    
    @RequestMapping(value = "/report/frequencyquery", method = RequestMethod.POST)
    public @ResponseBody String freqQuery(HttpServletRequest request, HttpServletResponse response)throws Exception{
        error = "";
        String returnMessage = "";
         ArrayList dataList = new ArrayList();
         Object startYears = new Object();
         Object endYears = new Object();
         List<Object> resultList = new ArrayList<Object>();
         Map map = new HashMap();
        try{
            SessionData temp = new SessionData(request);        
         BaseUrl = temp.BaseUrl(request);

       
              EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
              EntityManager em = emf.createEntityManager();
              em.getTransaction().begin();
              
               HttpSession session= request.getSession(false);  
             if(session == null || session.getAttribute("fullname")== null){
            Map message = new HashMap();
            message.put("returnMessage", util.errorAlertReturn("User is not logged in. Kindly try to login again"));
            Gson gson = new GsonBuilder().create();
          
       
            String jsonResponse = gson.toJson(message);
        
        return jsonResponse;
            
             }
             
              Map aclData =  (Map) session.getAttribute("acl");
       
        if(!aclData.containsKey("reports") || !aclData.containsValue("reports")){
            return util.errorAlertReturn("<br/> You do not have the right(s) to perform this action"); 
        }
        
      
         
         String period = request.getParameter("period")!=null ? request.getParameter("period"):"";
         String startDate = request.getParameter("start_date")!=null ? request.getParameter("start_date"):"";
         String endDate = request.getParameter("end_date")!= null ? request.getParameter("end_date"):"";
         
         StoreStaff storeStaff = (StoreStaff) session.getAttribute("staff");
         Integer creator_id = storeStaff.getStaffId(); // we get the id of the current logged in user
         User user = storeStaff.getUserId();
         
         
         
        
         if(period.isEmpty()){
             error += "<br/> Please select a period category";
         }
         
         if(startDate.isEmpty()){
             error += "<br/> Please specify the start date for the query";
         }
        
         if(endDate.isEmpty()){
             error += "<br/> Please specify the end date for the query";
         }
         
         if(!period.equalsIgnoreCase("annual") && !period.equalsIgnoreCase("monthly") && !period.equalsIgnoreCase("aggregate") && !period.equalsIgnoreCase("6month") && !period.equalsIgnoreCase("3month")){
             error += "<br/> This option has not been activated";
         }
         if(!error.isEmpty()){
             returnMessage = util.errorAlertReturn(error);
         }else{
             

             Map result = util.parseDate(startDate,endDate,period);
            
             startYears = result.get("startYears");
             endYears = result.get("endYears");
          //   String[] arrayData = (String[]) startYears;//startYears.toString().replaceAll("]", "").replaceAll("[","").split(",");
             ArrayList startYearList = (ArrayList) startYears;
             ArrayList endYearList = (ArrayList) endYears;
             List<Object> results = new ArrayList();
             List <Object[]> resultListD = new ArrayList();
             for(int i=0; i<startYearList.size();i++){
                
                     //product = em.find(Product.class, Integer.parseInt(prodIds[r]));
                     Date startD = Util.formatDate("yyyy-MM-dd",startYearList.get(i).toString());
                     Date endD = Util.formatDate("yyyy-MM-dd",endYearList.get(i).toString());
                     
                       resultListD = reportH.fetchProductSalesFreq(startD, endD, user);
                       
                  Map resultDataM = new HashMap();
//                     
                       resultDataM.put("startDate",startYearList.get(i).toString());
                       resultDataM.put("endDate",endYearList.get(i).toString());
                       resultDataM.put("queryResult",resultListD);
                       results.add(resultDataM); 
//map.put("dtatjj",dat);
                 
            
             }
           
             map.put("data",results);
      
             
            
         }
        
            
        }catch(Exception err){
            System.out.println("System err :"+err.getMessage());
        }
        
        Gson gson = new GsonBuilder().create();
  
        // map.put("resultList",resultList);
         map.put("returnMessage", returnMessage);
      
        String jsonResponse = gson.toJson(map);
        
        return jsonResponse;
       
    }
    @RequestMapping(value = "/report/querysales", method = RequestMethod.POST)
    public @ResponseBody String querySales(HttpServletRequest request, HttpServletResponse response)throws Exception{
        error = "";
        String returnMessage = "";
         ArrayList dataList = new ArrayList();
         Object startYears = new Object();
         Object endYears = new Object();
         List<Object> resultList = new ArrayList<Object>();
         Map map = new HashMap();
        try{
            SessionData temp = new SessionData(request);        
         BaseUrl = temp.BaseUrl(request);

       
              EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
              EntityManager em = emf.createEntityManager();
              em.getTransaction().begin();
              // calling the validator method to validate the inout gotten from the register form
               error = "";
               HttpSession session= request.getSession(false);  
             if(session == null || session.getAttribute("fullname")== null){
            Map message = new HashMap();
            message.put("returnMessage", util.errorAlertReturn("User is not logged in. Kindly try to login again"));
            Gson gson = new GsonBuilder().create();
          
       
            String jsonResponse = gson.toJson(message);
        
        return jsonResponse;
            
             }
             
              Map aclData =  (Map) session.getAttribute("acl");
       
        if(!aclData.containsKey("reports") || !aclData.containsValue("reports")){
            return util.errorAlertReturn("<br/> You do not have the right(s) to perform this action"); 
        }
        
      
         String[] prodIds = request.getParameterValues("prodId");
         String period = request.getParameter("period")!=null ? request.getParameter("period"):"";
         String startDate = request.getParameter("start_date")!=null ? request.getParameter("start_date"):"";
         String endDate = request.getParameter("end_date")!= null ? request.getParameter("end_date"):"";
         
         
         //check if the parameters are empty or filled
         
         if(prodIds.length<=0 || prodIds.toString().isEmpty()){
             error += "<br/> Please select at least a product";
         }
        
         if(period.isEmpty()){
             error += "<br/> Please select a period category";
         }
         
         if(startDate.isEmpty()){
             error += "<br/> Please specify the start date for the query";
         }
        
         if(endDate.isEmpty()){
             error += "<br/> Please specify the end date for the query";
         }
         
         if(!period.equalsIgnoreCase("annual") && !period.equalsIgnoreCase("monthly") && !period.equalsIgnoreCase("aggregate") && !period.equalsIgnoreCase("6month") && !period.equalsIgnoreCase("3month")){
             error += "<br/> This option has not been activated";
         }
         if(!error.isEmpty()){
             returnMessage = util.errorAlertReturn(error);
         }else{
             

             Map result = util.parseDate(startDate,endDate,period);
            
             startYears = result.get("startYears");
             endYears = result.get("endYears");
          //   String[] arrayData = (String[]) startYears;//startYears.toString().replaceAll("]", "").replaceAll("[","").split(",");
             ArrayList startYearList = (ArrayList) startYears;
             ArrayList endYearList = (ArrayList) endYears;
             List<Object> results = new ArrayList();
             for(int i=0; i<startYearList.size();i++){
                 for(int r=0; r<prodIds.length; r++){
                     product = em.find(Product.class, Integer.parseInt(prodIds[r]));
                     Date startD = Util.formatDate("yyyy-MM-dd",startYearList.get(i).toString());
                     Date endD = Util.formatDate("yyyy-MM-dd",endYearList.get(i).toString());
                     
                     List <Object> resultListD = saleH.fetchAverageSales(startD,endD,product);
 
                   Map resultDataM = new HashMap();
                     
                     resultDataM.put("startDate",startYearList.get(i).toString());
                     resultDataM.put("endDate",endYearList.get(i).toString());
                     resultDataM.put("queryResult",resultListD.get(0));
                     results.add(resultDataM); 

                 }
            
             }
           
             map.put("data",results);
      
             
            
         }
        
            
        }catch(Exception err){
            System.out.println("System err :"+err.getMessage());
        }
        
        Gson gson = new GsonBuilder().create();
  
         map.put("resultList",resultList);
         map.put("returnMessage", returnMessage);
      
        String jsonResponse = gson.toJson(map);
        
        return jsonResponse;
       
    }
}
