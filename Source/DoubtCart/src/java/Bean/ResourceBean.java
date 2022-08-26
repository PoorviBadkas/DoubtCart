/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;


import Entity.Comments;
import Entity.Liketb;
import Entity.Resource;
import Entity.ResourceFiles;
import Entity.User;
import java.io.File;
import java.util.Collection;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HP
 */
@DeclareRoles({"User","Admin"})
@RolesAllowed({"User","Admin"})
@Stateless
public class ResourceBean implements ResourceBeanLocal {
    
    @PersistenceContext(unitName = "ResourcesPU")
    EntityManager em;  

    @Override
    public Integer CountAllComments() {
        return (Integer) em.createNamedQuery("Comments.findAll").getResultList().size();
    }

    @Override
    public Integer CountAllFiles() {
        return (Integer) em.createNamedQuery("ResourceFiles.findAll").getResultList().size();
    }

    
    @Override
    public Collection<Resource> ListResources() {
        return em.createNamedQuery("Resource.findAll").getResultList();
    }

    
    @Override
    public Resource SingleResource(Integer ResourceID) {
        return (Resource) em.createNamedQuery("Resource.findById").setParameter("id", ResourceID).getSingleResult();
    }

    @Override
    public void LikeResource(Integer ResourceID, String UserID) {
        Liketb l = new Liketb();
        Resource r = (Resource) em.find(Resource.class, ResourceID);
        User u = (User) em.find(User.class, UserID);
        
        Collection<Liketb> resLike = r.getLiketbCollection();
        resLike.add(l);
        r.setLiketbCollection(resLike);
        
        Collection<Liketb> userLike = u.getLiketbCollection();
        userLike.add(l);
        u.setLiketbCollection(userLike);
        
        em.persist(l);
        
        
    }

    @Override
    public void SaveProfile(String username ,String profileurl) {
        User u = (User) em.find(User.class, username);
         if(u.getProfile() != null)
        {
            File ff = new File("F:\\DoubtCart\\web\\FileAssets\\Profile",u.getProfile());
            if(ff.exists())
            {
                ff.deleteOnExit();
            }
        }
        u.setProfile(profileurl);
        em.merge(u);
    }

    
    @Override
    public Collection<Comments> CommentsByResource(Integer ResourceID) {
        return em.createNamedQuery("Comments.findByResourceID").setParameter("resourceID", ResourceID).getResultList();
    }

    @Override
    public Integer CountLikes(Integer ResourceID) {
        Resource r = (Resource) em.find(Resource.class, ResourceID);
        return r.getLiketbCollection().size();
    }

    @Override
    public Boolean IsLikedByUser(Integer ResourceID, String UserID) {
        return !(em.createNamedQuery("Liketb.ResLikedByUser").setParameter("resid", ResourceID).setParameter("userid", UserID).getResultList().isEmpty());        
    }

    @Override
    public void CommentResource(Integer ResourceID, String Comment,String username) {
        
        Comments c = new Comments(Comment);
        Resource r = (Resource) em.find(Resource.class, ResourceID);
        User u = (User) em.find(User.class, username);
        c.setResourceID(r);
        c.setUserID(u);                       

        Collection<Comments> resComment = r.getCommentsCollection();
        resComment.add(c);
        r.setCommentsCollection(resComment);
        
        Collection<Comments> userComment = u.getCommentsCollection();
        userComment.add(c);
        u.setCommentsCollection(userComment);
        
        em.merge(r);
        em.merge(u);
        em.persist(c);
    }

    @Override
    public Integer CountComments(Integer ResourceID) {
        Resource r = (Resource) em.find(Resource.class, ResourceID);
        return r.getCommentsCollection().size();
    }

    @Override
    public void DeleteComment(Integer CommentID) {
        Comments c = (Comments) em.find(Comments.class, CommentID);
        
        Resource r = c.getResourceID();
        User u = c.getUserID();
        r.getCommentsCollection().remove(c);
        u.getCommentsCollection().remove(c);
        em.merge(r);
        em.merge(u);
        
        em.remove(c);
        
        System.out.println("$$$$$$$$$$$$$ " + CommentID);
        
    }

//    @Override
//    public void DownloadResource(Integer ResourceID, Integer UserID) {
//        Resource r = (Resource) em.find(Resource.class, ResourceID);
//        User u = (User) em.find(User.class, UserID);
//        
//        Collection<User> resUsers = r.get();
//        resUsers.add(u);
//        r.setUserCollection(resUsers);
//        
//        Collection<Resource> userRes = u.getResourcesCollection();
//        userRes.add(r);
//        u.setResourcesCollection(userRes);   
//        
//        em.merge(r);
//    }

    @Override
    public Collection<Resource> SearchByTitleandSubject(String SearchKey) {
//            @NamedQuery(name = "Resource.findByTitleAndSubject", query = "SELECT r FROM Resource r WHERE r.title = :key OR r.subject = :key"),
        return em.createNamedQuery("Resource.findByTitleAndSubject").setParameter("key", "%" + SearchKey + "%").getResultList();
    }

    @Override
    public Collection<Resource> ResourcesBySemester(Integer Semester) {
        return em.createNamedQuery("Resource.findBySemester").setParameter("semester", Semester).getResultList();
    }

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Collection<Resource> ResourcesBySubject(String Subject) {
        return em.createNamedQuery("Resource.findBySubject").setParameter("subject", Subject).getResultList();
    }

    @Override
    public Collection<ResourceFiles> FilesByResource(Integer ResourceID) {
        return em.find(Resource.class,ResourceID).getResourceFilesCollection();
    }
    
    
    
    
    
    
    
}
