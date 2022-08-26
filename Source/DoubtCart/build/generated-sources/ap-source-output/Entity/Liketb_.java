package Entity;

import Entity.Resource;
import Entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-06-17T10:54:14")
@StaticMetamodel(Liketb.class)
public class Liketb_ { 

    public static volatile SingularAttribute<Liketb, Resource> resourceID;
    public static volatile SingularAttribute<Liketb, Integer> likeID;
    public static volatile SingularAttribute<Liketb, Date> lDate;
    public static volatile SingularAttribute<Liketb, User> userID;

}