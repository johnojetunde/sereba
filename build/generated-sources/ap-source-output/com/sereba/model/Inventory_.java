package com.sereba.model;

import com.sereba.model.StoreStaff;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-13T13:21:09")
@StaticMetamodel(Inventory.class)
public class Inventory_ { 

    public static volatile SingularAttribute<Inventory, Date> date;
    public static volatile SingularAttribute<Inventory, Double> amount;
    public static volatile SingularAttribute<Inventory, String> transType;
    public static volatile SingularAttribute<Inventory, String> creatorType;
    public static volatile SingularAttribute<Inventory, Integer> isDeleted;
    public static volatile SingularAttribute<Inventory, Integer> invId;
    public static volatile SingularAttribute<Inventory, StoreStaff> creatorId;
    public static volatile SingularAttribute<Inventory, Date> timeCreated;
    public static volatile SingularAttribute<Inventory, String> source;
    public static volatile SingularAttribute<Inventory, String> title;
    public static volatile SingularAttribute<Inventory, Date> timeModified;

}