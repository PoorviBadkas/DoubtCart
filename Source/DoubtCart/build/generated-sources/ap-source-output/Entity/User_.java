package Entity;

import Entity.Answer;
import Entity.Badge;
import Entity.Comments;
import Entity.Doubt;
import Entity.Doubtcomment;
import Entity.Groups;
import Entity.Liketb;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-06-17T10:54:14")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> profile;
    public static volatile SingularAttribute<User, Integer> warnings;
    public static volatile SingularAttribute<User, Boolean> isBlocked;
    public static volatile SingularAttribute<User, Integer> otp;
    public static volatile SingularAttribute<User, Integer> points;
    public static volatile CollectionAttribute<User, Doubt> doubtCollection;
    public static volatile SingularAttribute<User, String> password;
    public static volatile CollectionAttribute<User, Badge> badgeCollection;
    public static volatile CollectionAttribute<User, Doubtcomment> doubtcommentCollection;
    public static volatile CollectionAttribute<User, Answer> answerCollection;
    public static volatile SingularAttribute<User, Integer> semester;
    public static volatile CollectionAttribute<User, Comments> commentsCollection;
    public static volatile CollectionAttribute<User, Groups> groupsCollection;
    public static volatile CollectionAttribute<User, Liketb> liketbCollection;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> username;

}