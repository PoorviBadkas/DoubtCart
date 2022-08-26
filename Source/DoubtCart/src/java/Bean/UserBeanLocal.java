/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Entity.User;
import javax.ejb.Local;

/**
 *
 * @author HP
 */
@Local
public interface UserBeanLocal {
    
         void registerUser(String Name,String Email,String Password,Integer Semester,Integer OTP,Integer Points,Integer Warnings,Boolean isBlocked);
         Boolean ForgotPassword(String Email);
         Boolean ResetPassword(String Email,String Password,String OTP);
         Boolean ChangePassword(String oldPassword,String newPassword);
         
         
         User UserInfo(String Username);
         Integer CountAllUsers();
         Integer CountAllUsersGTpoints(Integer Points);
         Integer CountActiveUsers();
         
}
