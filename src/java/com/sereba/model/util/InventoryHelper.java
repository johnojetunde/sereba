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
public class InventoryHelper {
    private Inventory inventory = new Inventory();
    
    public List<Object> listInventory(){
        //List<Agent> agentList = new ArrayList<Agent>();
        List<Object> resultList = new ArrayList<Object>();
        try {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
        EntityManager em = emf.createEntityManager();
        
        //find by ID
        //Query jpqlQuery  = em.createNamedQuery("Agent.findByDeleted");
        Query jpqlQuery = em.createNamedQuery("Inventory.findByAdminIsDeleted",Object[].class);
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
        
        public List<Object> listUserInventory(User user_id){
     
        List<Object> resultList = new ArrayList<Object>();
        try {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sereba_AccountingPU");
        EntityManager em = emf.createEntityManager();
        
        //find by ID
        //Query jpqlQuery  = em.createNamedQuery("Agent.findByDeleted");
        Query jpqlQuery = em.createNamedQuery("Inventory.findByCreatorIsDeleted",Object[].class);
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
