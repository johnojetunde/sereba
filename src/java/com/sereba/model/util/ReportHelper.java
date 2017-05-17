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
import com.sereba.model.Product;
import com.sereba.model.util.UserHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author John
 */
public class ReportHelper {
    private Product product = new Product();
    
    public List<Object[]> fetchProductSalesFreq(Date startDate,Date endDate,User user)throws Exception{
        Integer userId = user.getUserId();
        List<Object[]> resultList = new ArrayList<Object[]>();
        
        try{
           EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
           EntityManager em = emf.createEntityManager();
        
         Query jpqlQuery = em.createNativeQuery("SELECT p.*,COUNT(sales_id) as salesfreq FROM product as p LEFT JOIN (SELECT * FROM sales WHERE  sales.date >= ?startDate AND sales.date<= ?endDate)  as sc ON p.prod_id = sc.prod_id \n" +
"LEFT JOIN store_staff as st ON st.staff_id = p.creator_id LEFT JOIN user as u ON u.user_id = st.user_id  WHERE p.is_deleted = ?isDeleted AND u.user_id = ?userId GROUP BY p.prod_id ORDER BY salesfreq DESC");
         jpqlQuery.setParameter("startDate", startDate);
         jpqlQuery.setParameter("endDate",endDate);
         jpqlQuery.setParameter("userId", userId);
         jpqlQuery.setParameter("isDeleted",0);
         
         resultList = jpqlQuery.getResultList();
         
            
            
        }catch(Exception err){
            System.out.println("System error :"+err.getMessage());
        }
        return resultList;
    }
}
