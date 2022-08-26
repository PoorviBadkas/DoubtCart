/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Bean.ResourceBeanLocal;
import Bean.UserBeanLocal;
import Entity.Resource;
import Entity.User;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author HP
 */
@Path("User")
@RequestScoped
public class UserResource {
 @EJB UserBeanLocal resource;
    @Context
    private UriInfo context;

   
    
    @Path("registerUser/{Username}/{Email}/{Password}/{Semester}")
    @POST
    public void registerUser(@PathParam("Username") String Username,@PathParam("Email") String Email, @PathParam("Password") String Password, @PathParam("Semester") Integer Semester) {
        resource.registerUser(Username, Email, Password, Semester,0,0,0,false);
    }
    
    @Path("ForgotPassword/{Email}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean ForgotPassword(@PathParam("Email")String Email) {
        return resource.ForgotPassword(Email);
    }

    @Path("ResetPassword/{Email}/{Password}/{OTP}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean ResetPassword(@PathParam("Email") String Email, @PathParam("Password") String Password, @PathParam("OTP") String OTP) {
        return resource.ResetPassword(Email,Password,OTP);
    }
    
    @Path("UserInfo/{Username}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User UserInfo(@PathParam("Username") String Username) {
        return resource.UserInfo(Username);
    }
   
    @Path("ChangePassword/{oldPassword}/{newPassword}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean ChangePassword(@PathParam("oldPassword") String oldPassword,@PathParam("newPassword") String newPassword) {
        return resource.ChangePassword( oldPassword, newPassword);
    }
}
