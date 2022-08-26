package Entity;

import Entity.Comments;
import Entity.Liketb;
import Entity.ResourceFiles;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-06-17T10:54:14")
@StaticMetamodel(Resource.class)
public class Resource_ { 

    public static volatile SingularAttribute<Resource, String> image;
    public static volatile SingularAttribute<Resource, Integer> resourceID;
    public static volatile CollectionAttribute<Resource, ResourceFiles> resourceFilesCollection;
    public static volatile SingularAttribute<Resource, String> subject;
    public static volatile SingularAttribute<Resource, String> description;
    public static volatile SingularAttribute<Resource, Integer> semester;
    public static volatile CollectionAttribute<Resource, Comments> commentsCollection;
    public static volatile SingularAttribute<Resource, String> title;
    public static volatile CollectionAttribute<Resource, Liketb> liketbCollection;

}