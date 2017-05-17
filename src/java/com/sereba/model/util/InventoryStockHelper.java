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
public class InventoryStockHelper {
    private InventoryStock inventoryStock = new InventoryStock();
    private Product product = new Product();
    private User user = new User();
    
    public InventoryStock findLastById()throws Exception{
         try{
          EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
          EntityManager em = emf.createEntityManager();
          
         Query jpqlQuery = em.createNamedQuery("InventoryStock.findByLastId",User.class);
              List<InventoryStock> list = jpqlQuery.getResultList();
              if (list == null || list.isEmpty()) {
        return null;
              }else{
                  inventoryStock = list.get(0);
              }
    }
         catch(Exception err){
             System.out.println("System error :"+err.getMessage());
         }
         return inventoryStock;
         
    }
    
    public List<Object> getProductInventory(Integer prodId,Integer userId)throws Exception{
      List<Object> resultList = new ArrayList<Object>();
        try{
           EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
        EntityManager em = emf.createEntityManager();
        product  = em.find(Product.class,prodId);
        user = em.find(User.class,userId);
        //find by ID
        //Query jpqlQuery  = em.createNamedQuery("Agent.findByDeleted");
        Query jpqlQuery = em.createNamedQuery("InventoryStock.findByCreatorProductIsDeleted",Object[].class);
        jpqlQuery.setParameter("isDeleted", 0);
        jpqlQuery.setParameter("prodId",product);
        jpqlQuery.setParameter("userId",user);
        //List<Product> productList = jpqlQuery.getResultList();
        
       resultList = jpqlQuery.getResultList();
          
      
      }catch(Exception err){
          System.out.println("system error :"+ err.getMessage());
      }
     return resultList;   
    }
}
