/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Entity.Answer;
import Entity.Category;
import Entity.Doubt;
import Entity.Resource;
import Entity.Tags;
import Entity.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import record.KeepRecord;

/**
 *
 * @author HP
 */
@DeclareRoles({"User","Admin"})
@RolesAllowed({"User","Admin"})
@Stateless
public class DoubtBean implements DoubtBeanLocal {

    @PersistenceContext(unitName = "ResourcesPU")
    EntityManager em;  
   
    
    @Override    
    public Collection<Doubt> SolvedDoubts() {
        return em.createNamedQuery("Doubt.findByIsClosed").setParameter("isClosed", true).getResultList();
    }

    @Override
    public Collection<Doubt> UnsolvedDoubt() {
        return em.createNamedQuery("Doubt.findByIsClosed").setParameter("isClosed", false).getResultList();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Collection<Answer> usefulAnswers() {
        return em.createNamedQuery("Answer.findByIsHelpful").setParameter("isHelpful", true).getResultList();
    }

    @Override
    public Collection<Answer> NotusefulAnswers() {
        return em.createNamedQuery("Answer.findByIsHelpful").setParameter("isHelpful", false).getResultList();
    }

    
    @Override
    public Collection<Doubt> MyDoubts() {
        return em.find(User.class, KeepRecord.getUsername()).getDoubtCollection();
    }

    @Override
    public Collection<Doubt> SearchDoubtByTitle(String SearchKey) {
        return em.createNamedQuery("Doubt.findByTitle").setParameter("title", "%" + SearchKey + "%").getResultList();
    }
    
    @Override
    public Collection<Answer> MyAsnwers() {
        return em.find(User.class, KeepRecord.getUsername()).getAnswerCollection();
    }
    
    @Override
    public Collection<Category> ListCategory() {
        return em.createNamedQuery("Category.findAll").getResultList();
    }

    @Override
    public Collection<Tags> ListTags() {
        return em.createNamedQuery("Tags.findAll").getResultList();
    }

    @Override
    public void CreateDoubt(String title, String description, Integer Point) {
        Doubt dd = new Doubt(title,description,Point,false);        
        User u = em.find(User.class,KeepRecord.getUsername());        
        dd.setUsername(u);
        
        Collection<Doubt> dlist = u.getDoubtCollection();
        dlist.add(dd);
        u.setDoubtCollection(dlist);
        em.persist(dd);
        em.merge(u);
    }

    @Override
    public void UpdateDoubt(Integer ID, String title, String description, Integer Point, Integer[] categorydata, Integer[] tagsdata) {
        Doubt d = em.find(Doubt.class, ID);
        d.setTitle(title);
        d.setDescription(description);
        d.setPoint(Point);
        em.merge(d);
        
        ArrayList<Integer> category = (ArrayList<Integer>) Arrays.stream(categorydata).collect(Collectors.toList());
        ArrayList<Integer> tags = (ArrayList<Integer>) Arrays.stream(tagsdata).collect(Collectors.toList());

            d.getCategoryCollection().clear();
            em.merge(d);
            Collection<Category> catlist = d.getCategoryCollection();

            for(Integer cid : categorydata)
            {
                Category cat = (Category) em.find(Category.class, cid);

                if(!catlist.contains(cat))
                {
                    Collection<Doubt> doubt = cat.getDoubtCollection();
                    doubt.add(d);
                    catlist.add(cat);
                    d.setCategoryCollection(catlist);
                    cat.setDoubtCollection(doubt);

                    em.merge(d);
                    em.merge(cat);
                    System.out.println("%%%%%%%%%%%%%% "+cat.getName());
                }
                
            }    
        
        
        
            d.getTagsCollection().clear();
            em.merge(d);
                    Collection<Tags> taglist = d.getTagsCollection();

        for(Integer tid : tagsdata)
            {
                Tags tag = (Tags) em.find(Tags.class, tid);

                if(!taglist.contains(tag))
                {
                    Collection<Doubt> doubt = tag.getDoubtCollection();
                    doubt.add(d);
                    taglist.add(tag);
                    d.setTagsCollection(taglist);
                    tag.setDoubtCollection(doubt);

                    em.merge(d);
                    em.merge(tag);
                }

            }   
    }

    @Override
    public void DoubtClosed(Integer AnswerId) {
        Answer a = em.find(Answer.class, AnswerId);
        a.setIsHelpful(true);
        em.merge(a);
        
        Doubt d = em.find(Doubt.class, a.getDoubtID().getId());
        d.setIsClosed(true);
        em.merge(d);
        
        User u = em.find(User.class, a.getUsername().getUsername());
        Integer p = u.getPoints() + d.getPoint();
        u.setPoints(p);
        em.merge(u);
    }

    @Override
    public void DeleteDoubt(Integer Id) {
        Doubt d = em.find(Doubt.class, Id);
        User u = em.find(User.class,KeepRecord.getUsername());                
        Collection<Doubt> dlist = u.getDoubtCollection();
        dlist.remove(d);
        u.setDoubtCollection(dlist);
        
        em.merge(u);
        em.remove(d);

    }

    @Override
    public Collection<Doubt> ListDoubts() {
        return em.createNamedQuery("Doubt.findAll").getResultList();
    }

    @Override
    public Doubt SingleDoubt(Integer DoubtID) {
        return em.find(Doubt.class, DoubtID);
    }

    @Override
    public void CreateAnswer(Integer DoubtId, String Username, String description) {
        Answer a = new Answer(description,false);
        Doubt d = em.find(Doubt.class, DoubtId);
        User u = em.find(User.class, Username);
        
        a.setDoubtID(d);
        a.setUsername(u);
        em.persist(a);
        
        
        Collection<Answer> dAnswer = d.getAnswerCollection();
        dAnswer.add(a);
        em.merge(d);
        Collection<Answer> uAnswer = u.getAnswerCollection();
        uAnswer.add(a);
        em.merge(u);

    }

    @Override
    public void UpdateAnswer(Integer Id, String description) {
        Answer a = em.find(Answer.class, Id);
        a.setDescription(description);
        em.merge(a);   
    }

    @Override
    public void DeleteAnswer(Integer Id) {
        Answer a = em.find(Answer.class, Id);
        User u = em.find(User.class,a.getUsername().getUsername()); 
        Doubt d = a.getDoubtID();
        Collection<Answer> adlist = d.getAnswerCollection();
        adlist.remove(a);
        Collection<Answer> alist = u.getAnswerCollection();
        alist.remove(a);
        u.setAnswerCollection(alist);
        d.setAnswerCollection(adlist);
        em.merge(u);
        em.merge(d);
        em.remove(a);   
    }

    @Override
    public Collection<Answer> ListAnswerByDoubt(Integer DoubtID) {
        return em.find(Doubt.class, DoubtID).getAnswerCollection();
    }
}
