package com.sereba.model;

import com.sereba.model.Admin;
import com.sereba.model.PaymentMethod;
import com.sereba.model.PaymentPlan;
import com.sereba.model.StoreStaff;
import com.sereba.model.SubscriptionPaylog;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-13T13:21:09")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Date> payExpire;
    public static volatile SingularAttribute<User, Admin> approvedBy;
    public static volatile SingularAttribute<User, PaymentMethod> pmethodId;
    public static volatile SingularAttribute<User, String> source;
    public static volatile SingularAttribute<User, PaymentPlan> pplanId;
    public static volatile SingularAttribute<User, Integer> userId;
    public static volatile SingularAttribute<User, Date> timeModified;
    public static volatile CollectionAttribute<User, StoreStaff> storeStaffCollection;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> phoneNumber;
    public static volatile SingularAttribute<User, Integer> isDeleted;
    public static volatile SingularAttribute<User, Date> payStart;
    public static volatile SingularAttribute<User, String> storeName;
    public static volatile SingularAttribute<User, String> contactAddress;
    public static volatile SingularAttribute<User, Date> timeCreated;
    public static volatile SingularAttribute<User, String> fullname;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, Integer> activated;
    public static volatile CollectionAttribute<User, SubscriptionPaylog> subscriptionPaylogCollection;

}