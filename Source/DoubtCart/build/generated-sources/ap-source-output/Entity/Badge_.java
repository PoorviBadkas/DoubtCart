package Entity;

import Entity.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-06-17T10:54:14")
@StaticMetamodel(Badge.class)
public class Badge_ { 

    public static volatile SingularAttribute<Badge, String> image;
    public static volatile CollectionAttribute<Badge, User> userCollection;
    public static volatile SingularAttribute<Badge, String> name;
    public static volatile SingularAttribute<Badge, Integer> id;

}