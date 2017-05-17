package com.sereba.model;

import com.sereba.model.Product;
import com.sereba.model.StoreStaff;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-13T13:21:09")
@StaticMetamodel(InventoryStock.class)
public class InventoryStock_ { 

    public static volatile SingularAttribute<InventoryStock, Date> date;
    public static volatile SingularAttribute<InventoryStock, String> creatorType;
    public static volatile SingularAttribute<InventoryStock, Double> costPrice;
    public static volatile SingularAttribute<InventoryStock, StoreStaff> creatorId;
    public static volatile SingularAttribute<InventoryStock, Double> discount;
    public static volatile SingularAttribute<InventoryStock, Product> prodId;
    public static volatile SingularAttribute<InventoryStock, String> source;
    public static volatile SingularAttribute<InventoryStock, Date> timeModified;
    public static volatile SingularAttribute<InventoryStock, Integer> prodQty;
    public static volatile SingularAttribute<InventoryStock, Double> sellingPrice;
    public static volatile SingularAttribute<InventoryStock, Integer> isDeleted;
    public static volatile SingularAttribute<InventoryStock, Date> timeCreated;
    public static volatile SingularAttribute<InventoryStock, Integer> invstkId;

}