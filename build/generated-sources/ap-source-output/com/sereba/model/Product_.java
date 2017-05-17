package com.sereba.model;

import com.sereba.model.InventoryStock;
import com.sereba.model.Sales;
import com.sereba.model.StoreStaff;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-13T13:21:09")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, String> creatorType;
    public static volatile SingularAttribute<Product, Double> prodSellingPrice;
    public static volatile SingularAttribute<Product, StoreStaff> creatorId;
    public static volatile SingularAttribute<Product, Double> discount;
    public static volatile SingularAttribute<Product, String> prodImagePath;
    public static volatile SingularAttribute<Product, Double> prodCostPrice;
    public static volatile SingularAttribute<Product, Integer> prodId;
    public static volatile SingularAttribute<Product, String> source;
    public static volatile SingularAttribute<Product, Date> timeModified;
    public static volatile CollectionAttribute<Product, Sales> salesCollection;
    public static volatile CollectionAttribute<Product, InventoryStock> inventoryStockCollection;
    public static volatile SingularAttribute<Product, Integer> prodQty;
    public static volatile SingularAttribute<Product, Integer> isDeleted;
    public static volatile SingularAttribute<Product, String> prodMeasurement;
    public static volatile SingularAttribute<Product, String> prodName;
    public static volatile SingularAttribute<Product, Date> timeCreated;

}