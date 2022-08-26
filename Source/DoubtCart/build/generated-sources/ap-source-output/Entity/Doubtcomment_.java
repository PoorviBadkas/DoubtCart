package Entity;

import Entity.Doubt;
import Entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-06-17T10:54:14")
@StaticMetamodel(Doubtcomment.class)
public class Doubtcomment_ { 

    public static volatile SingularAttribute<Doubtcomment, Date> ddate;
    public static volatile SingularAttribute<Doubtcomment, String> comment;
    public static volatile SingularAttribute<Doubtcomment, Integer> id;
    public static volatile SingularAttribute<Doubtcomment, User> userID;
    public static volatile SingularAttribute<Doubtcomment, Doubt> doubtID;

}