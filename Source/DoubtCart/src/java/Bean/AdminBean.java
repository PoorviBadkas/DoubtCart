/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Entity.Comments;
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
@RolesAllowed("Admin")
@Stateless
public class AdminBean implements AdminBeanLocal {
    
    
    @PersistenceContext(unitName = "ResourcesPU")
    EntityManager em;  
        
    @Override
    public void SaveResourceFiles(Integer ResId, String file) {        
        Resource r = em.find(Resource.class, ResId);
        Collection<ResourceFiles>  rfcollection = r.getResourceFilesCollection();
        ResourceFiles rf = new ResourceFiles(file);
        rf.setResourceID(r);
        rfcollection.add(rf);
        r.setResourceFilesCollection(rfcollection);
        em.persist(rf);
        em.merge(r);
        
    }

    @Override
    public Collection<Comments> Allcomments() {
        return em.createNamedQuery("Comments.findAll").getResultList();
    }

    @Override
    public Collection<Comments> AllAnswers() {
        return em.createNamedQuery("Answer.findAll").getResultList();
    }
   
    
    
    @Override
    public void createResources(String title, String description, Integer semester, String Subject,String Image) {
        Resource r = new Resource(title,description,semester,Subject);
        r.setImage(Image);
        em.persist(r);
    }

    @Override
    public void updateResources(Integer ID, String title, String description, Integer semester, String Subject,String Image) {
        Resource r = (Resource) em.find(Resource.class, ID);
        r.setTitle(title);
        r.setDescription(description);
        r.setSemester(semester);
        r.setSubject(Subject);
        
        if(r.getImage() != null)
        {
            File ff = new File("F:\\DoubtCart\\web\\FileAssets\\ResourceImg",r.getImage());
            if(ff.exists())
            {
                ff.deleteOnExit();
            }
        }
        
        r.setImage(Image);
        em.merge(r);
    }

    @Override
    public void deleteResources(Integer ID) {
        System.out.println("############### "+ID);
        Resource r = (Resource) em.find(Resource.class, ID);
        
        em.remove(r);
    }

    @Override
    public Collection<User> AllUsers() {
        return  em.createNamedQuery("User.findAll").getResultList();
    }

    @Override
    public void BlockUser(String username) {
        User u = em.find(User.class, username);
        u.setIsBlocked(true);
        em.merge(u);
    }

    @Override
    public void UnBlockUser(String username) {
        User u = em.find(User.class, username);
        u.setIsBlocked(false);
        em.merge(u);
    }
    
    @Override
    public void DeleteUser(String username) {
        User u = em.find(User.class, username);
        em.remove(u);
    }
    
    
    
    

}
