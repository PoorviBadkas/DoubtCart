package Entity;

import Entity.Answer;
import Entity.Category;
import Entity.Doubtcomment;
import Entity.Tags;
import Entity.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-06-17T10:54:14")
@StaticMetamodel(Doubt.class)
public class Doubt_ { 

    public static volatile SingularAttribute<Doubt, Boolean> isClosed;
    public static volatile CollectionAttribute<Doubt, Category> categoryCollection;
    public static volatile CollectionAttribute<Doubt, Tags> tagsCollection;
    public static volatile CollectionAttribute<Doubt, Doubtcomment> doubtcommentCollection;
    public static volatile CollectionAttribute<Doubt, Answer> answerCollection;
    public static volatile SingularAttribute<Doubt, String> description;
    public static volatile SingularAttribute<Doubt, Integer> id;
    public static volatile SingularAttribute<Doubt, String> title;
    public static volatile SingularAttribute<Doubt, Integer> point;
    public static volatile SingularAttribute<Doubt, User> username;

}