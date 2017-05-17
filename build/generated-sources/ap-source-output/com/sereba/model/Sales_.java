package com.sereba.model;

import com.sereba.model.Product;
import com.sereba.model.StoreStaff;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-13T13:21:09")
@StaticMetamodel(Sales.class)
public class Sales_ { 

    public static volatile SingularAttribute<Sales, Date> date;
    public static volatile SingularAttribute<Sales, Double> amount;
    public static volatile SingularAttribute<Sales, String> creatorType;
    public static volatile SingularAttribute<Sales, Integer> qtySold;
    public static volatile SingularAttribute<Sales, Double> costPrice;
    public static volatile SingularAttribute<Sales, StoreStaff> creatorId;
    public static volatile SingularAttribute<Sales, Double> discount;
    public static volatile SingularAttribute<Sales, Product> prodId;
    public static volatile SingularAttribute<Sales, String> source;
    public static volatile SingularAttribute<Sales, Date> timeModified;
    public static volatile SingularAttribute<Sales, Double> sellingPrice;
    public static volatile SingularAttribute<Sales, Integer> salesId;
    public static volatile SingularAttribute<Sales, Integer> isDeleted;
    public static volatile SingularAttribute<Sales, Date> timeCreated;

}