/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Entity.Comments;
import Entity.Resource;
import Entity.ResourceFiles;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author HP
 */
@Local
public interface ResourceBeanLocal {
    
    
    
     Collection<Resource> ListResources();
     Resource SingleResource(Integer ResourceID);

     void LikeResource(Integer ResourceID, String UserID);
     Integer CountLikes(Integer ResourceID);
     Boolean IsLikedByUser(Integer ResourceID, String UserID);
     void CommentResource(Integer ResourceID, String Comment,String username);
     Collection<Comments> CommentsByResource(Integer ResourceID);
     Integer CountComments(Integer ResourceID);
     void DeleteComment(Integer CommentID);
     void SaveProfile(String username ,String profileurl);
     
     
     //void DownloadResource(Integer ResourceID, Integer UserID);
     
     
     //search resorces by title and subject
     
     Collection<Resource> SearchByTitleandSubject(String SearchKey);
     Collection<Resource> ResourcesBySemester(Integer Semester);
     Collection<Resource> ResourcesBySubject(String Subject);
     Collection<ResourceFiles> FilesByResource(Integer ResourceID);
     Integer CountAllComments();
     Integer CountAllFiles();
   
}
