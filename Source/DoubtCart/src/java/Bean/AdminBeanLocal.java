/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Entity.Comments;
import Entity.User;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author HP
 */
@Local
public interface AdminBeanLocal {
 
     void createResources(String title,String description,Integer semester,String Subject,String Image);
     void updateResources(Integer ID,String title,String description,Integer semester,String Subject,String Image);
     void SaveResourceFiles(Integer ResId,String file);
     void deleteResources(Integer ID);
     Collection<Comments> Allcomments();
     Collection<Comments> AllAnswers();
     
     
     Collection<User> AllUsers();
     
     void BlockUser(String username);
     void UnBlockUser(String username);
    
     void DeleteUser(String username);
}
