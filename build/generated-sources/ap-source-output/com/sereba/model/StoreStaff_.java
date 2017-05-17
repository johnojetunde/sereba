package com.sereba.model;

import com.sereba.model.CredPayment;
import com.sereba.model.Creditor;
import com.sereba.model.Inventory;
import com.sereba.model.InventoryStock;
import com.sereba.model.Product;
import com.sereba.model.Sales;
import com.sereba.model.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-13T13:21:09")
@StaticMetamodel(StoreStaff.class)
public class StoreStaff_ { 

    public static volatile CollectionAttribute<StoreStaff, Product> productCollection;
    public static volatile CollectionAttribute<StoreStaff, Inventory> inventoryCollection;
    public static volatile SingularAttribute<StoreStaff, String> acl;
    public static volatile SingularAttribute<StoreStaff, String> source;
    public static volatile CollectionAttribute<StoreStaff, CredPayment> credPaymentCollection;
    public static volatile SingularAttribute<StoreStaff, Date> timeModified;
    public static volatile SingularAttribute<StoreStaff, User> userId;
    public static volatile CollectionAttribute<StoreStaff, Sales> salesCollection;
    public static volatile CollectionAttribute<StoreStaff, InventoryStock> inventoryStockCollection;
    public static volatile SingularAttribute<StoreStaff, String> password;
    public static volatile SingularAttribute<StoreStaff, String> phoneNumber;
    public static volatile SingularAttribute<StoreStaff, Integer> isDeleted;
    public static volatile CollectionAttribute<StoreStaff, Creditor> creditorCollection;
    public static volatile SingularAttribute<StoreStaff, Date> timeCreated;
    public static volatile SingularAttribute<StoreStaff, String> fullname;
    public static volatile SingularAttribute<StoreStaff, Integer> staffId;
    public static volatile SingularAttribute<StoreStaff, String> email;

}