/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sereba.controller;

/**
 *
 * @author John
 */
import com.sereba.model.util.Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sereba.model.*;
import com.sereba.model.util.CreditHelper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Date;
import java.util.Locale;
import com.sereba.model.util.SessionData;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.PropertyException;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
 

 
@Controller
public class CreditController extends HttpServlet {
    private Creditor credit = new Creditor();
    private CredPayment credpay = new CredPayment();
    private CreditHelper creditH = new CreditHelper();
    private Util util = new Util();
    private String BaseUrl = util.BaseUrl();
    private String error = "";
    
    
	@RequestMapping("/credit")
	public ModelAndView helloWorld(HttpServletRequest request, HttpServletResponse response)throws Exception {
             SessionData temp = new SessionData(request);        
             BaseUrl = temp.BaseUrl(request);
             HttpSession session= request.getSession(false);  
        if(session == null || session.getAttribute("fullname")==null){
          return new ModelAndView("redirect:"+BaseUrl);
        }
		String message = "<br><div style='text-align:center;'>"
				+ "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from CrunchifyHelloWorld.java **********</div><br><br>";
                
		return new ModelAndView("index/welcome", "message", message);
	}
        
        @RequestMapping("/credit/addcredit")
	public ModelAndView addCredit(HttpServletRequest request, HttpServletResponse response)throws Exception {
             SessionData temp = new SessionData(request);        
             BaseUrl = temp.BaseUrl(request);
             HttpSession session= request.getSession(false);  
        if(session == null || session.getAttribute("fullname") == null){
           String url = BaseUrl+"login/index.html?redirect=credit/addcredit.html";
          return new ModelAndView("redirect:"+url);
        }
         Map aclData =  (Map) session.getAttribute("acl");
       
        if(!aclData.containsKey("add_credit") || !aclData.containsValue("add_credit")){
        String restrictedAccess = BaseUrl+"error/noaccess.html";
            return  new ModelAndView("redirect:"+restrictedAccess);
        }
             
                ModelAndView model = new ModelAndView("credit/addcredit", "appname", "Sereba Accounting") ;
                //model.addObject("appname", "Sereba Accounting");
                return model;
	}
        
        @RequestMapping(value = "/credit/credpayment", method = RequestMethod.POST)
	public ModelAndView addCredPyment(HttpServletRequest request, HttpServletResponse response)throws Exception {
             SessionData temp = new SessionData(request);        
             BaseUrl = temp.BaseUrl(request);
             HttpSession session= request.getSession(false);  
        if(session == null || session.getAttribute("fullname") == null){
          String url = BaseUrl+"login/index.html?redirect=credit/credpayment.html";
          return new ModelAndView("redirect:"+url);
        }
         Map aclData =  (Map) session.getAttribute("acl");
       
        if(!aclData.containsKey("add_credit_payment") || !aclData.containsValue("add_credit_payment")){
        String restrictedAccess = BaseUrl+"error/noaccess.html";
            return  new ModelAndView("redirect:"+restrictedAccess);
        }
                String credId = request.getParameter("credId") != null ? request.getParameter("credId") : "";
               // boolean isPost = "POST".equals(request.getMethod());
               
                ModelAndView model = new ModelAndView("credit/credpayment", "appname", "Sereba Accounting") ;
                model.addObject("credId",credId);
                model.addObject("display",true);
                return model;
	}
        
        
         @RequestMapping(value = "/credit/savepayment", method = RequestMethod.POST)
	public ModelAndView saveCredPayment(HttpServletRequest request, HttpServletResponse response)throws Exception {
             SessionData temp = new SessionData(request);       
             BaseUrl = temp.BaseUrl(request);
             HttpSession session= request.getSession(false);  
        if(session == null || session.getAttribute("fullname") == null){
           String url = BaseUrl+"login/index.html?redirect=credit/credpayment.html";
          return new ModelAndView("redirect:"+url);
        }       
                error = "";
                Map aclData =  (Map) session.getAttribute("acl");
       
       
       
        if(!aclData.containsKey("add_credit_payment") || !aclData.containsValue("add_credit_payment")){
        String restrictedAccess = BaseUrl+"error/noaccess.html";
            return  new ModelAndView("redirect:"+restrictedAccess);
        }
                String returnMessage = "";
                String credId = request.getParameter("credId") != null ? request.getParameter("credId") : "";
                String amount = request.getParameter("amount") != null ? request.getParameter("amount") : "";
                boolean display = false;
                if(credId.isEmpty()){
                    error += "<br/> Something is wrong. Go back to the credit page";
                }
               
                if(amount.isEmpty()){
                    error += "<br/> You need to enter the amount you're paying";
                }
                
             StoreStaff storeStaff = (StoreStaff) session.getAttribute("staff");
             Integer creator_id = storeStaff.getStaffId(); // we get the id of the current logged in user
             User user = storeStaff.getUserId();
             String creator_type = (String) session.getAttribute("userType");
             
             EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
             EntityManager em = emf.createEntityManager();
             
             try{
                 if(error.isEmpty()) {
                    credit = em.find(Creditor.class,Integer.parseInt(credId));
                    
                    if(credit == null){
                        error += "<br/> This credit record not found";
                    }else{
                        //begin the transaction for the credit
                         em.getTransaction().begin();
                         credpay.setAmountPaid(Double.parseDouble(amount));
                         credpay.setCreatorId(storeStaff);
                         credpay.setCreatorType(creator_type);
                         credpay.setCredId(credit);
                         credpay.setIsDeleted(0);
                         credpay.setTimeCreated(util.getCurrentTimeStamp());
                        
                         
                        
                         Double tempAmount = credit.getAmountDeposited() + Double.parseDouble(amount);
                         credit.setAmountDeposited(tempAmount);
                         credit.setTimeModified(util.getCurrentTimeStamp());
                         
                         if(!em.contains(credpay)){
                         em.persist(credpay);
                           }
                         
                         em.getTransaction().commit();
                         em.close();
                         emf.close();
                         String url = BaseUrl+"credit/viewcredit.html";
                        if(!credpay.toString().isEmpty()){
                            String message = "Credit Payment successful...<a href='"+url+"' class='btn btn-success'><i class='fa fa-arrow-left'></i> Credit List</a>";
                            if(!aclData.containsKey("view_credit") || !aclData.containsValue("view_credit")){
                            message = "Credit Payment successful...";
                              }
                           
                            
                            returnMessage = util.successAlertReturn(message,"makepayment");
         
                        }
                        else{
                           returnMessage = util.errorAlertReturn("Unable to make payment to the database");
                    }
                    }
                    }else{
                     
                         returnMessage = util.errorAlertReturn(error);
                   
                 }
             
             }catch(Exception err){
                 returnMessage = util.errorAlertReturn("Error "+err.getMessage());
             }
                
               
                ModelAndView model = new ModelAndView("credit/credpayment", "appname", "Sereba Accounting") ;
                model.addObject("credId",credId);
                model.addObject("BaseUrl",BaseUrl);
                model.addObject("display",display);
                model.addObject("returnMessage",returnMessage);
                return model;
	}
        
        
         // the ajax method for the add register form
        @RequestMapping(value = "/credit/savecredit", method = RequestMethod.POST)
        public @ResponseBody String saveCredit(HttpServletRequest request, HttpServletResponse response) throws PropertyException,ServletException,IOException,NullPointerException,Exception{
            SessionData temp = new SessionData(request);        
            BaseUrl = temp.BaseUrl(request);            
//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullname", "Fullname is a required field");
            String returnMessage = "";
            try {
              EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
              EntityManager em = emf.createEntityManager();
              em.getTransaction().begin();
              // calling the validator method to validate the inout gotten from the register form
              String error = "";
               HttpSession session= request.getSession(false);  
             if(session == null || session.getAttribute("fullname")== null){
              return util.errorAlertReturn("User is not logged in. Kindly try to login again");
             }
             Map aclData =  (Map) session.getAttribute("acl");
       
                if(!aclData.containsKey("add_credit") || !aclData.containsValue("add_credit")){
               
                   return util.errorAlertReturn("You not have access to perform this action");
                }
              String prod_name = request.getParameter("prod_name") != null ? request.getParameter("prod_name") : "";
              String prod_desc =    request.getParameter("prod_desc")!= null? request.getParameter("prod_desc"):"";
              String product_qty = request.getParameter("prod_qty") !=null ? request.getParameter("prod_qty") : "";
              String prod_measurement = request.getParameter("prod_measurement") != null ? request.getParameter("prod_measurement") : "";
              String amount_param = request.getParameter("amount") != null ? request.getParameter("amount") : "";
              String expiryDate = request.getParameter("expiry_date") !=null ? request.getParameter("expiry_date") : "";

             StoreStaff storeStaff = (StoreStaff) session.getAttribute("staff");
             Integer creator_id = storeStaff.getStaffId(); // we get the id of the current logged in user
             User user = storeStaff.getUserId();
             String creator_type = (String) session.getAttribute("userType");

              if(prod_name.isEmpty()){
                  error += "<br/> Product name is a required field";
              }

              if(prod_desc.isEmpty()){
                  error += "<br/> Product description is a required field";
              }

              if(product_qty.isEmpty()){
                  error += "<br/> Product Quantity is a required field";
              }

              if(prod_measurement.isEmpty()){
                  error += "<br/> Product measurment is a required field";
              }

              if(amount_param.isEmpty()){
                  error += "<br/> Amount is a required field";
              }

              if(expiryDate.isEmpty()){
                  error += "<br/> Please specify an expiry date";
              }
             //  error += "<br/> "+expiryDate;

              if(!error.isEmpty()){
                  // there is an error
              returnMessage = util.errorAlertReturn(error);
              }else{
               // no error message. Our inputs are all valid
              Integer prod_qty = Integer.parseInt(product_qty);
              Double amount = Double.parseDouble(amount_param);
              
               credit.setProdName(prod_name);
               credit.setAmount(amount);
               credit.setProdName(prod_name);
               credit.setProdDesc(prod_desc);
               credit.setProdMeasurement(prod_measurement);
               credit.setAmountDeposited(0.0);
               credit.setProdQty(prod_qty);
               credit.setExpiryDate(util.formatDate("yyyy-MM-dd",expiryDate));
               credit.setCreatorId(storeStaff);
               credit.setCreatorType(creator_type);
               credit.setTimeCreated(util.getCurrentTimeStamp());
               credit.setTimeModified(util.formatDate("yyyy-MM-dd","000-00-00"));
               credit.setIsDeleted(0);

              //insert into the database here. We wana create a new credit. We just pass the object to the database
               //persist only on save mode
                     if(!em.contains(credit)){
                         em.persist(credit);

                     }
               em.getTransaction().commit();
               em.close();
               emf.close();

              if(!credit.toString().isEmpty()){
                 returnMessage = util.successAlertReturn("Credit successfully added...", "addcredit");


              }else{
                 returnMessage = util.errorAlertReturn("Unable to add a new credit to the database");
              }

             }
            }catch (Exception err){
                  returnMessage = util.errorAlertReturn("System error."+ err.getMessage());
            }
             return returnMessage;

        }

        @RequestMapping(value = "/credit/paymenthistory",method = RequestMethod.GET)
        public ModelAndView paymentHistory(HttpServletRequest request, HttpServletResponse response)throws Exception{
             SessionData temp = new SessionData(request);        
             BaseUrl = temp.BaseUrl(request);  
            HttpSession session= request.getSession(false);  
        if(session == null || session.getAttribute("fullname")== null){
           String url = BaseUrl+"login/index.html?redirect=credit/paymenthistory.html";
          return new ModelAndView("redirect:"+url);
        }
        
        Map aclData =  (Map) session.getAttribute("acl");
       
        if(!aclData.containsKey("view_credit_payment") || !aclData.containsValue("view_credit_payment")){
        String restrictedAccess = BaseUrl+"error/noaccess.html";
            return  new ModelAndView("redirect:"+restrictedAccess);
        }
                error = "";
                String returnMessage = "";
                List<Object> resultList = new ArrayList<Object>();
                ModelAndView model = new ModelAndView("credit/paymenthistory", "appname", "Seraba Accounting");
                 StoreStaff storeStaff = (StoreStaff) session.getAttribute("staff");
                // Integer creator_id = storeStaff.getStaffId(); // we get the id of the current logged in user
                 User user = storeStaff.getUserId();
                 String creator_type = (String) session.getAttribute("userType");
                 String id = request.getParameter("id") != null ? request.getParameter("id") : ""; //this is the credit id
                 
                 EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
                 EntityManager em = emf.createEntityManager();
                 try{
                 
                 if(id.isEmpty()){
                     error += "<br/> Please select a credit record to query payment history";
                 }
                 
                 if(error.isEmpty()){
                 em.getTransaction().begin();
                 credit = em.find(Creditor.class,Integer.parseInt(id));
                 if(credit.toString()== null || credit.toString().isEmpty()){
                 error += "<br/> Credit record does not exists";
                 }else{
                     if(credit.getIsDeleted()==1){
                         error += "<br/> Credit record has been deleted";
                     }else{
                         //this is where we do the fetching of the payment records of the credit
                         resultList = creditH.listPaymentHistoryUnique(credit);
                     }
                 }
                 }else{
                     returnMessage = util.errorAlertReturn(error);
                 }
                 }catch(Exception err){
                      returnMessage = util.errorAlertReturn("System error."+ err.getMessage());
                 }
              
                model.addObject("credpayments",resultList);
                model.addObject("baseUrl", BaseUrl);
                return model;
        }
        
        @RequestMapping("/credit/viewcredit")
	public ModelAndView viewCredit(HttpServletRequest request, HttpServletResponse response) throws Exception {
             SessionData temp = new SessionData(request);        
             BaseUrl = temp.BaseUrl(request); 
            HttpSession session= request.getSession(false);  
        if(session == null || session.getAttribute("fullname")== null){
           String url = BaseUrl+"login/index.html?redirect=credit/viewcredit.html";
          return new ModelAndView("redirect:"+url);
        }
        
                Map aclData =  (Map) session.getAttribute("acl");
       
        if(!aclData.containsKey("view_credit") || !aclData.containsValue("view_credit")){
        String restrictedAccess = BaseUrl+"error/noaccess.html";
            return  new ModelAndView("redirect:"+restrictedAccess);
        }
                ModelAndView model = new ModelAndView("credit/viewcredit", "appname", "Seraba Accounting");
                 StoreStaff storeStaff = (StoreStaff) session.getAttribute("staff");
                // Integer creator_id = storeStaff.getStaffId(); // we get the id of the current logged in user
                 User user = storeStaff.getUserId();
                 String creator_type = (String) session.getAttribute("userType");
        
              if(creator_type.equalsIgnoreCase("admin")){
                model.addObject("credits",creditH.listCredits());
                }else{
                model.addObject("credits",creditH.listUserCredits(user));
                }
              model.addObject("baseUrl", BaseUrl);
                return model;
	}
        
     
        
        @RequestMapping(value = "/credit/delete", method = RequestMethod.POST)
        public @ResponseBody String deleteCredit(HttpServletRequest request, HttpServletResponse response) throws Exception{
         SessionData temp = new SessionData(request);        
         BaseUrl = temp.BaseUrl(request);
            error = "";
       HttpSession session= request.getSession(false);  
         if(session==null || session.getAttribute("fullname") == null){
         return util.errorAlertReturn("User is not logged in. Please try to log in");
        }
         
         Map aclData =  (Map) session.getAttribute("acl");
       
        if(!aclData.containsKey("view_credit_payment") || !aclData.containsValue("view_credit_payment")){
        //String restrictedAccess = BaseUrl+"error/noaccess.html";
          return util.errorAlertReturn("You do not have the rights to perform this action");
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
         credit = em.find(Creditor.class,Integer.parseInt(id));
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
         
          
         credit.setIsDeleted(1);
         credit.setTimeModified(util.getCurrentTimeStamp());
         em.merge(credit);
         em.flush();
         em.getTransaction().commit();
         
         em.close();
         emf.close();
         
          if(!credit.toString().isEmpty()){
            returnMessage = util.successAlertReturn("Credit successfully deleted...", "deletecredit");
         
            
         }else{
            returnMessage = util.errorAlertReturn("Unable to delete credit");
         }
          
         }
        
                } catch (Exception err){
                    
                }
                
          return returnMessage;
    }
    
}
