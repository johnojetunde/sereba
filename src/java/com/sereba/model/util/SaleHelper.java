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
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
/**
 *
 * @author John
 */
public class SaleHelper {
    private Sales sale = new Sales();
      /*TP: Listing all products that exists in the database and are not deleted*/
    public List<Object> listSales()throws Exception{
        //List<Agent> agentList = new ArrayList<Agent>();
        
        List<Object> resultList = new ArrayList<Object>();
        try{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
        EntityManager em = emf.createEntityManager();
        
        //find by ID
        //Query jpqlQuery  = em.createNamedQuery("Agent.findByDeleted");
        Query jpqlQuery = em.createNamedQuery("Sales.findByAdminIsDeleted");
        jpqlQuery.setParameter("isDeleted", 0);
         resultList = jpqlQuery.getResultList();
         }catch(Exception err){
            System.out.println("System error"+err.getMessage());
        }
        return resultList;
    }
    public Sales findProduct(Integer prodId)throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
        EntityManager em = emf.createEntityManager();
        Query jpqlQuery = em.createNamedQuery("Sales.findBySalesId", Product.class);
        jpqlQuery.setParameter("saleId", prodId);
        sale = (Sales) jpqlQuery.getSingleResult();
       return sale;
    }
    
    public List<Object> listUserSales(User user_id)throws Exception{
     
        List<Object> resultList = new ArrayList<Object>();
        try {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
        EntityManager em = emf.createEntityManager();
        
        //find by ID
        //Query jpqlQuery  = em.createNamedQuery("Agent.findByDeleted");
        Query jpqlQuery = em.createNamedQuery("Sales.findByCreatorIsDeleted",Object[].class);
        
        jpqlQuery.setParameter("isDeleted", 0);
        jpqlQuery.setParameter("userId",user_id);
        //List<Product> productList = jpqlQuery.getResultList();
        
       resultList = jpqlQuery.getResultList();
        }catch(Exception err){
            System.out.println("System error"+err.getMessage());
        }
        
        return resultList;
    }
    //public List<Object> fetchResult
    public List<Object> fetchAverageSales(Date startDate,Date endDate,Product prodId)throws Exception{
        List<Object> resultList = new ArrayList<Object>();
        try{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
        EntityManager em = emf.createEntityManager();
        
        Query jpqlQuery = em.createNamedQuery("Sales.selectSumSales",Object.class);
        
        jpqlQuery.setParameter("prodId", prodId);
        jpqlQuery.setParameter("startDate",startDate);
        jpqlQuery.setParameter("endDate",endDate);
        
        resultList = jpqlQuery.getResultList();
        
            
        }catch(Exception err){
            System.out.println("System error :"+err.getMessage());
        }
        
        return resultList;
    }
    
    public Object averageSales(String startDate,String endDate, String period,String[] prodId)throws Exception{
        Object result  =  new Object();
        try{
            
            
            
            
        
        }catch(Exception err){
            System.out.println("System error :"+err.getMessage());
        }
        return result;
    }
}
