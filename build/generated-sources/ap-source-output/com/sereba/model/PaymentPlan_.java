package com.sereba.model;

import com.sereba.model.SubscriptionPaylog;
import com.sereba.model.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-13T13:21:09")
@StaticMetamodel(PaymentPlan.class)
public class PaymentPlan_ { 

    public static volatile SingularAttribute<PaymentPlan, Integer> isDeleted;
    public static volatile CollectionAttribute<PaymentPlan, User> userCollection;
    public static volatile SingularAttribute<PaymentPlan, String> paymentDuration;
    public static volatile SingularAttribute<PaymentPlan, Date> timeCreated;
    public static volatile SingularAttribute<PaymentPlan, String> source;
    public static volatile SingularAttribute<PaymentPlan, Integer> pplanId;
    public static volatile SingularAttribute<PaymentPlan, String> paymentName;
    public static volatile SingularAttribute<PaymentPlan, Date> timeModified;
    public static volatile CollectionAttribute<PaymentPlan, SubscriptionPaylog> subscriptionPaylogCollection;

}