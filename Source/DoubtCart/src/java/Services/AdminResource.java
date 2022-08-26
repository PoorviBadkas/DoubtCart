/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Bean.AdminBeanLocal;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author HP
 */
@Path("Admin")
@RequestScoped
public class AdminResource {

    @EJB AdminBeanLocal resource;
    @Context
    private UriInfo context;

    @Path("SaveResourceFiles/{ResId}/{file}")
    @POST
    public void SaveResourceFiles(@PathParam("ResId") Integer ResId,@PathParam("file") String file) {        
       resource.SaveResourceFiles(ResId, file);
    }
    
    @Path("createResources/{title}/{description}/{semester}/{Subject}/{Image}")
    @POST
    public void createResources(@PathParam("title") String title,@PathParam("description") String description, @PathParam("semester") Integer semester, @PathParam("Subject") String Subject, @PathParam("Image") String Image) {
        resource.createResources(title, description, semester, Subject,Image);
    }
    
    @Path("updateResources/{ID}/{title}/{description}/{semester}/{Subject}/{Image}")
    @PUT
    public void updateResources(@PathParam("ID") Integer ID, @PathParam("title") String title,@PathParam("description") String description, @PathParam("semester") Integer semester, @PathParam("Subject") String Subject,@PathParam("Image") String Image) {
        resource.updateResources(ID, title, description, semester, Subject,Image);
    }

    @Path("deleteResources/{ID}")
    @DELETE
    public void deleteResources(@PathParam("ID") Integer ID) {
        resource.deleteResources(ID);
    }
}
