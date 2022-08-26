/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Bean.ResourceBeanLocal;
import Entity.Comments;
import Entity.Resource;
import Entity.ResourceFiles;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author HP
 */
@Path("generic")
public class RestResource {

    
    @EJB ResourceBeanLocal resource;
    @Context
    private UriInfo context;

   
    @Path("FilesByResource/{ResourceID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ResourceFiles> FilesByResource(@PathParam("ResourceID") Integer ResourceID) {
        return resource.FilesByResource(ResourceID);
    }
    
    

    @Path("ListResources")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Resource> ListResources() {
        return resource.ListResources();
    }

    @Path("SingleResource/{ResourceID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Resource SingleResource(@PathParam("ResourceID") Integer ResourceID) {
        return resource.SingleResource(ResourceID);
    }

    @Path("LikeResource/{ResourceID}/{UserID}")
    @POST
    public void LikeResource(@PathParam("ResourceID") Integer ResourceID, @PathParam("UserID") String UserID) {
        resource.LikeResource(ResourceID, UserID);        
    }

    @Path("SaveProfile/{username}/{profileurl}")
    @POST
    public void SaveProfile(@PathParam("username") String username ,@PathParam("profileurl") String profileurl) {
        resource.SaveProfile(username, profileurl);
    }
    
    @Path("CountLikes/{ResourceID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Integer CountLikes(@PathParam("ResourceID") Integer ResourceID) {
            return resource.CountLikes(ResourceID);
    }

    @Path("IsLikedByUser/{ResourceID}/{UserID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean IsLikedByUser( @PathParam("ResourceID")Integer ResourceID, @PathParam("UserID")String UserID) {
        return resource.IsLikedByUser(ResourceID, UserID);
    }
    
    @Path("CommentsByResource/{ResourceID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Comments> CommentsByResource(@PathParam("ResourceID") Integer ResourceID) {
        return resource.CommentsByResource(ResourceID);
    }
     
    @Path("CommentResource/{ResourceID}/{UserID}/{Comment}")
    @POST
    public void CommentResource(@PathParam("ResourceID") Integer ResourceID, @PathParam("UserID") String UserID,@PathParam("Comment") String Comment) {
          resource.CommentResource(ResourceID, Comment, UserID);
    }

    @Path("CountComments/{ResourceID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Integer CountComments(@PathParam("ResourceID") Integer ResourceID) {
        return resource.CountComments(ResourceID);
    }

    @Path("DeleteComment/{CommentID}")
    @DELETE
    public void DeleteComment(@PathParam("CommentID") Integer CommentID) {
        resource.DeleteComment(CommentID);
    }

//    @Override
//    public void DownloadResource(Integer ResourceID, Integer UserID) {

//    }

    @Path("SearchByTitleandSubject/{SearchKey}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Resource> SearchByTitleandSubject(@PathParam("SearchKey") String SearchKey) {
        return resource.SearchByTitleandSubject(SearchKey);
    }

    @Path("ResourcesBySemester/{Semester}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Resource> ResourcesBySemester(@PathParam("Semester") Integer Semester) {
        return resource.ResourcesBySemester(Semester);
    }
    
    @Path("ResourcesBySubject/{Subject}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Resource> ResourcesBySubject(@PathParam("Subject") String Subject) {
        return resource.ResourcesBySubject(Subject);
    }

}
