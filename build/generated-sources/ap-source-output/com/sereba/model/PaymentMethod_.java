package com.sereba.model;

import com.sereba.model.SubscriptionPaylog;
import com.sereba.model.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-13T13:21:09")
@StaticMetamodel(PaymentMethod.class)
public class PaymentMethod_ { 

    public static volatile SingularAttribute<PaymentMethod, Integer> isDeleted;
    public static volatile CollectionAttribute<PaymentMethod, User> userCollection;
    public static volatile SingularAttribute<PaymentMethod, Integer> pmethodId;
    public static volatile SingularAttribute<PaymentMethod, String> methodName;
    public static volatile SingularAttribute<PaymentMethod, Date> timeCreated;
    public static volatile SingularAttribute<PaymentMethod, String> source;
    public static volatile SingularAttribute<PaymentMethod, Date> timeModified;
    public static volatile CollectionAttribute<PaymentMethod, SubscriptionPaylog> subscriptionPaylogCollection;

}