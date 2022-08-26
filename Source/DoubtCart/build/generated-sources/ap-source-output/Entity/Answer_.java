package Entity;

import Entity.Doubt;
import Entity.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-06-17T10:54:14")
@StaticMetamodel(Answer.class)
public class Answer_ { 

    public static volatile SingularAttribute<Answer, String> description;
    public static volatile SingularAttribute<Answer, Integer> id;
    public static volatile SingularAttribute<Answer, Boolean> isHelpful;
    public static volatile SingularAttribute<Answer, Doubt> doubtID;
    public static volatile SingularAttribute<Answer, User> username;

}