package Entity;

import Entity.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-06-17T10:54:14")
@StaticMetamodel(Groups.class)
public class Groups_ { 

    public static volatile CollectionAttribute<Groups, User> userCollection;
    public static volatile SingularAttribute<Groups, String> groupname;

}