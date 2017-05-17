/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sereba.model.util;


import com.sereba.model.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author John
 */
public class CreditHelper {
     private Creditor credit = new Creditor();
     private CredPayment credpay = new CredPayment();
     
     public List<Object> listPaymentHistoryUnique(Creditor credit)throws Exception{
           
        List<Object> resultList = new ArrayList<Object>();
        try{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
        EntityManager em = emf.createEntityManager();
        
        //find by ID
        //Query jpqlQuery  = em.createNamedQuery("Agent.findByDeleted");
        Query jpqlQuery = em.createNamedQuery("CredPayment.findByCreditIsDeleted",Object[].class);
        jpqlQuery.setParameter("isDeleted", 0);
        jpqlQuery.setParameter("credId",credit);
        //List<Product> productList = jpqlQuery.getResultList();
        
       resultList = jpqlQuery.getResultList();
        em.clear();
        em.close();
        emf.close();
        }catch (Exception err){
            System.out.println("System error"+err.getMessage());
        }
        return resultList; 
        }
        public List<Object> listCredits()throws Exception{
        //List<Agent> agentList = new ArrayList<Agent>();
        List<Object> resultList = new ArrayList<Object>();
        try {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
        EntityManager em = emf.createEntityManager();
        
        //find by ID
        //Query jpqlQuery  = em.createNamedQuery("Agent.findByDeleted");
        Query jpqlQuery = em.createNamedQuery("Creditor.findByAdminIsDeleted");
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
        
        public List<Object> listUserCredits(User user_id)throws Exception{
     
        List<Object> resultList = new ArrayList<Object>();
        try {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
        EntityManager em = emf.createEntityManager();
        
        //find by ID
        //Query jpqlQuery  = em.createNamedQuery("Agent.findByDeleted");
        Query jpqlQuery = em.createNamedQuery("Creditor.findByCreatorIsDeleted",Object[].class);
        jpqlQuery.setParameter("isDeleted", 0);
        jpqlQuery.setParameter("userId",user_id);
        //List<Product> productList = jpqlQuery.getResultList();
        
       resultList = jpqlQuery.getResultList();
        em.clear();
        em.close();
        emf.close();
        }catch (Exception err){
            System.out.println("System error"+err.getMessage());
        }
        return resultList;
    }
}
