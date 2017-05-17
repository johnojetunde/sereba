package com.sereba.model;

import com.sereba.model.PaymentMethod;
import com.sereba.model.PaymentPlan;
import com.sereba.model.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-13T13:21:09")
@StaticMetamodel(SubscriptionPaylog.class)
public class SubscriptionPaylog_ { 

    public static volatile SingularAttribute<SubscriptionPaylog, Double> amount;
    public static volatile SingularAttribute<SubscriptionPaylog, Date> endPeriod;
    public static volatile SingularAttribute<SubscriptionPaylog, Integer> transactionStatus;
    public static volatile SingularAttribute<SubscriptionPaylog, String> source;
    public static volatile SingularAttribute<SubscriptionPaylog, PaymentPlan> pplanId;
    public static volatile SingularAttribute<SubscriptionPaylog, Date> timeModified;
    public static volatile SingularAttribute<SubscriptionPaylog, User> userId;
    public static volatile SingularAttribute<SubscriptionPaylog, PaymentMethod> pmethod;
    public static volatile SingularAttribute<SubscriptionPaylog, Integer> subId;
    public static volatile SingularAttribute<SubscriptionPaylog, Date> startPeriod;
    public static volatile SingularAttribute<SubscriptionPaylog, Integer> isDeleted;
    public static volatile SingularAttribute<SubscriptionPaylog, Date> timeCreated;
    public static volatile SingularAttribute<SubscriptionPaylog, String> transactionMessage;

}