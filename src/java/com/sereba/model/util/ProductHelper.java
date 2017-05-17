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
public class ProductHelper {
    private Product product = new Product();
      /*TP: Listing all products that exists in the database and are not deleted*/
    public List<Object> listProducts(){
        //List<Agent> agentList = new ArrayList<Agent>();
        List<Object> resultList = new ArrayList<Object>();
        try {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
        EntityManager em = emf.createEntityManager();
        
        //find by ID
        //Query jpqlQuery  = em.createNamedQuery("Agent.findByDeleted");
        Query jpqlQuery = em.createNamedQuery("Product.findByAdminIsDeleted");
        jpqlQuery.setParameter("isDeleted", 0);
         resultList = jpqlQuery.getResultList();
         }catch (Exception err){
            System.out.println("System error"+err.getMessage());
        }
        return resultList;
    }
    
    //get the last product entered into the database
    public Product findByLastId()throws Exception{
        
        try{
          EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
          EntityManager em = emf.createEntityManager();
          
         Query jpqlQuery = em.createNamedQuery("Product.findByLastId",User.class);
              List<Product> list = jpqlQuery.getResultList();
              if (list == null || list.isEmpty()) {
        return null;
              }else{
                  product = list.get(0);
              }
    }
         catch(Exception err){
             System.out.println("System error :"+err.getMessage());
         }
         return product;
    }
    
    
    
    public Product findProduct(Integer prodId){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
        EntityManager em = emf.createEntityManager();
        Query jpqlQuery = em.createNamedQuery("Product.findByProdId", Product.class);
        jpqlQuery.setParameter("prodId", prodId);
        product = (Product) jpqlQuery.getSingleResult();
       return product;
    }
    
    public List<Object> listUserProducts(User user_id){
     
        List<Object> resultList = new ArrayList<Object>();
        try{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
        EntityManager em = emf.createEntityManager();
        
        //find by ID
        //Query jpqlQuery  = em.createNamedQuery("Agent.findByDeleted");
        Query jpqlQuery = em.createNamedQuery("Product.findByCreatorIsDeleted",Object[].class);
        jpqlQuery.setParameter("isDeleted", 0);
        jpqlQuery.setParameter("userId",user_id);
        //List<Product> productList = jpqlQuery.getResultList();
        
       resultList = jpqlQuery.getResultList();
        
        }catch (Exception err){
            System.out.println("System error"+err.getMessage());
        }
        return resultList;
    }
  
}
