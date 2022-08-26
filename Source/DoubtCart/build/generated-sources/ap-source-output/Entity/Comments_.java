package Entity;

import Entity.Resource;
import Entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-06-17T10:54:14")
@StaticMetamodel(Comments.class)
public class Comments_ { 

    public static volatile SingularAttribute<Comments, Resource> resourceID;
    public static volatile SingularAttribute<Comments, Date> cdate;
    public static volatile SingularAttribute<Comments, Integer> commentID;
    public static volatile SingularAttribute<Comments, String> comment;
    public static volatile SingularAttribute<Comments, User> userID;

}