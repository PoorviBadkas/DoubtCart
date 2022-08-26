/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Entity.Groups;
import Entity.User;
import java.util.Collection;
import java.util.Random;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;
import record.KeepRecord;

/**
 *
 * @author HP
 */
@DeclareRoles({"User","Admin"})
@Stateless
public class UserBean implements UserBeanLocal {

    
    @PersistenceContext(unitName = "ResourcesPU")
    EntityManager em;
    Pbkdf2PasswordHashImpl pb = new Pbkdf2PasswordHashImpl();

    
    
    @Override
    public void registerUser(String Name, String Email, String Password, Integer Semester,Integer OTP,Integer Points,Integer Warnings,Boolean isBlocked) {
        User u = new User(Name,Email,pb.generate(Password.toCharArray()),Semester,OTP,Points,Warnings,isBlocked);
        em.persist(u);
        
       Collection<Groups> group = em.createNamedQuery("Groups.findByGroupname").setParameter("groupname", "User").getResultList();
       u.setGroupsCollection(group);
       
       Groups g = em.find(Groups.class, "User");
       Collection<User> userg = g.getUserCollection();
       userg.add(u);
       g.setUserCollection(userg);
       em.merge(u);
    }  

    public Integer GenerateOTP() {
      Integer min = 100000;
      Integer max = 999999;
        
      //Generate random int value from 50 to 100 
      System.out.println("Random value in int from "+min+" to "+max+ ":");
      return (int)Math.floor(Math.random()*(max-min+1)+min);
    }
    
    @Override
    public Boolean ForgotPassword(String Email) {
        
           User u = (User) em.createNamedQuery("User.findByEmail").setParameter("email", Email).getResultList().get(0);
           Integer randomnum = this.GenerateOTP();
           u.setOtp(randomnum);
           em.merge(u);
           return EmailService.EmailService.sendFromGMail(u.getEmail(), "Forgot Password OTP",String.valueOf(randomnum)) ;
        
       
    }

    @Override
    public Boolean ResetPassword(String Email, String Password, String OTP) {

        User u = (User) em.createNamedQuery("User.findByEmail").setParameter("email", Email).getResultList().get(0);
        if(OTP.equals(String.valueOf( u.getOtp() ) ) )
        {
            u.setPassword(pb.generate(Password.toCharArray()));
            u.setOtp(000000);
            em.merge(u);
            return true;
        }
        return false;
        
    }

    @Override
    public Boolean ChangePassword(String oldPassword,String newPassword) {

        User u = (User) em.createNamedQuery("User.findByUsername").setParameter("username", KeepRecord.getUsername()).getSingleResult();
        if(pb.verify(oldPassword.toCharArray(), u.getPassword()))
        {
            u.setPassword(pb.generate(newPassword.toCharArray()));
            em.merge(u);
            return true;
        }
        return false;
        
    }
    
    @RolesAllowed({"User","Admin"})
    @Override
    public User UserInfo(String Username) {
                System.out.println("@@@@@@@@@@@@@@@"+Username);

        return (User) em.createNamedQuery("User.findByUsername").setParameter("username",Username).getSingleResult();
    }

    @RolesAllowed({"User","Admin"})
    @Override
    public Integer CountAllUsers() {
        return (Integer) em.createNamedQuery("User.findAll").getResultList().size();
    }

    @RolesAllowed({"User","Admin"})
    @Override
    public Integer CountAllUsersGTpoints(Integer Points) {
        return (Integer) em.createNamedQuery("User.findByPoints").setParameter("points",Points).getResultList().size();
    }

    @RolesAllowed({"User","Admin"})
    @Override
    public Integer CountActiveUsers() {
        return (Integer) em.createNamedQuery("User.findByIsBlocked").setParameter("isBlocked",false).getResultList().size();
    }
    
    
    
}
