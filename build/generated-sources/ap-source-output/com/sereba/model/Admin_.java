package com.sereba.model;

import com.sereba.model.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-13T13:21:09")
@StaticMetamodel(Admin.class)
public class Admin_ { 

    public static volatile SingularAttribute<Admin, String> password;
    public static volatile SingularAttribute<Admin, Boolean> isDeleted;
    public static volatile CollectionAttribute<Admin, User> userCollection;
    public static volatile SingularAttribute<Admin, Integer> adminId;
    public static volatile SingularAttribute<Admin, Date> timeCreated;
    public static volatile SingularAttribute<Admin, String> fullname;
    public static volatile SingularAttribute<Admin, String> acl;
    public static volatile SingularAttribute<Admin, String> source;
    public static volatile SingularAttribute<Admin, Date> timeModified;
    public static volatile SingularAttribute<Admin, String> email;

}