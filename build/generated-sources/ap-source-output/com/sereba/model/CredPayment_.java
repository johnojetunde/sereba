package com.sereba.model;

import com.sereba.model.Creditor;
import com.sereba.model.StoreStaff;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-13T13:21:09")
@StaticMetamodel(CredPayment.class)
public class CredPayment_ { 

    public static volatile SingularAttribute<CredPayment, Integer> credpId;
    public static volatile SingularAttribute<CredPayment, Double> amountPaid;
    public static volatile SingularAttribute<CredPayment, String> creatorType;
    public static volatile SingularAttribute<CredPayment, Integer> isDeleted;
    public static volatile SingularAttribute<CredPayment, StoreStaff> creatorId;
    public static volatile SingularAttribute<CredPayment, Date> timeCreated;
    public static volatile SingularAttribute<CredPayment, String> source;
    public static volatile SingularAttribute<CredPayment, Creditor> credId;
    public static volatile SingularAttribute<CredPayment, Date> timeModified;

}