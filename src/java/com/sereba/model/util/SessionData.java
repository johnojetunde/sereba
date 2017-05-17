/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sereba.model.util;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import com.sereba.model.util.StoreStaffHelper;
import com.sereba.model.StoreStaff;
import com.sereba.model.User;
import com.sereba.model.util.UserHelper;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author John
 */
public class SessionData extends HttpServlet{
    private StoreStaff staff = new StoreStaff();
    private User user = new User();
   public SessionData(HttpServletRequest request)throws Exception{
       try{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
        EntityManager em = emf.createEntityManager();
        HttpSession session= request.getSession(false);  
        if(session != null && session.getAttribute("fullname") != null){
         staff = (StoreStaff) session.getAttribute("staff");
         Integer staffId = staff.getStaffId();
         staff = em.find(StoreStaff.class,staffId);
         session.setAttribute("fullname",staff.getFullname());
         session.setAttribute("staff", staff);
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
         
         if(session.getAttribute("userType").equals("user")){
            user = (User) session.getAttribute("user");
            Integer userId = user.getUserId();
            user = em.find(User.class,userId);
            session.setAttribute("user",user);
         }
       }
       }catch(Exception err){
           System.out.println(err.getMessage());
       }
   }
   
   public String BaseUrl(HttpServletRequest request)throws Exception{
       String base = "";
       try{
       StringBuffer url = request.getRequestURL();
        String uri = request.getRequestURI();
        String ctx = request.getContextPath();
         base = url.substring(0, url.length() - uri.length() + ctx.length()) + "/";
       }catch (Exception err){
           System.out.println("System error :"+err.getMessage());
       }
return base;
   }
}
