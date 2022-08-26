/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Entity.Answer;
import Entity.Category;
import Entity.Doubt;
import Entity.Tags;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author HP
 */
@Local
public interface DoubtBeanLocal {
    
    Collection<Category> ListCategory();
    Collection<Tags> ListTags();
    
    
    void CreateDoubt(String title,String description,Integer Point);
    void UpdateDoubt(Integer ID,String title,String description,Integer Point,Integer[] categorydata, Integer[] tagsdata);
    void DoubtClosed(Integer AnswerId);
    void DeleteDoubt(Integer Id);
    Collection<Doubt> SearchDoubtByTitle(String SearchKey);
    
    Collection<Doubt> ListDoubts();
    Collection<Doubt> SolvedDoubts();
    Collection<Doubt> UnsolvedDoubt();
    Collection<Doubt> MyDoubts();
    Doubt SingleDoubt(Integer DoubtID);
    
    void CreateAnswer(Integer DoubtId,String Username,String description);
    void UpdateAnswer(Integer Id,String description);
    void DeleteAnswer(Integer Id);
    Collection<Answer> ListAnswerByDoubt(Integer DoubtID);
    Collection<Answer> MyAsnwers();
    Collection<Answer> usefulAnswers();
    Collection<Answer> NotusefulAnswers();
        
    
    
}
