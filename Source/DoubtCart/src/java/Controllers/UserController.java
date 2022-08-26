/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Bean.UserBeanLocal;
import client.UserClient;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HP
 */
@Named(value = "userController")
@ApplicationScoped
public class UserController {

    
    UserClient usercl = new UserClient();
    @EJB UserBeanLocal ubl;
    Response res;
    GenericType<Boolean> gboolean = new GenericType<Boolean> () {};
    Integer semester,OTP;
    String name,email,password;
    /**
     * Creates a new instance of UserController
     */
    public UserController() {
    }
    
    
    
    public String register(String name,String email,String password,Integer semester)
     {
        usercl.registerUser(name,email,password,String.valueOf(semester));
         //rbl.registerUser(name, email, password, semester);
         this.name = "";
         this.email = "";
         this.semester = null;
         this.password ="";
         return "/App.xhtml?faces-redirect=true";
     }
     
     public String forgotPassword(String Email)
     {
        res = usercl.ForgotPassword(Response.class,Email);
        Boolean result = res.readEntity(gboolean);    
        this.email = "";
         System.out.println("@@@@@@@@" + result);

        if(result){
           return "/ResetPassword.xhtml?faces-redirect=true";
        }
        else{
               return "/ForgotPassword.xhtml?faces-redirect=true";
        }
     }
     
     
      public String resetPassword(String Email,String Password, String OTP)
     {
        res = usercl.ResetPassword(Response.class,Email,Password,OTP);
        Boolean result = res.readEntity(gboolean);        
        this.email = "";
         this.OTP = null;
         this.password ="";
        if(result){
           return "/App.xhtml?faces-redirect=true";
        }
        else{
           return "/ResetPassword.xhtml?faces-redirect=true";
        }
     }

    public UserClient getUsercl() {
        return usercl;
    }

    public void setUsercl(UserClient usercl) {
        this.usercl = usercl;
    }

    public Response getRes() {
        return res;
    }

    public void setRes(Response res) {
        this.res = res;
    }

    public GenericType<Boolean> getGboolean() {
        return gboolean;
    }

    public void setGboolean(GenericType<Boolean> gboolean) {
        this.gboolean = gboolean;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Integer getOTP() {
        return OTP;
    }

    public void setOTP(Integer OTP) {
        this.OTP = OTP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
     
      
      
      
}
