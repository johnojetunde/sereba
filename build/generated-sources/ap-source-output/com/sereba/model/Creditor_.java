package com.sereba.model;

import com.sereba.model.CredPayment;
import com.sereba.model.StoreStaff;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-13T13:21:09")
@StaticMetamodel(Creditor.class)
public class Creditor_ { 

    public static volatile SingularAttribute<Creditor, Double> amount;
    public static volatile SingularAttribute<Creditor, String> creatorType;
    public static volatile SingularAttribute<Creditor, StoreStaff> creatorId;
    public static volatile SingularAttribute<Creditor, Double> amountDeposited;
    public static volatile SingularAttribute<Creditor, String> source;
    public static volatile SingularAttribute<Creditor, Integer> credId;
    public static volatile CollectionAttribute<Creditor, CredPayment> credPaymentCollection;
    public static volatile SingularAttribute<Creditor, Date> timeModified;
    public static volatile SingularAttribute<Creditor, Date> expiryDate;
    public static volatile SingularAttribute<Creditor, Integer> prodQty;
    public static volatile SingularAttribute<Creditor, Integer> isDeleted;
    public static volatile SingularAttribute<Creditor, String> prodMeasurement;
    public static volatile SingularAttribute<Creditor, String> prodName;
    public static volatile SingularAttribute<Creditor, Date> timeCreated;
    public static volatile SingularAttribute<Creditor, String> prodDesc;

}