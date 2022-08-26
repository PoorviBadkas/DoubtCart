/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.util.Set;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import record.KeepRecord;

/**
 *
 * @author Poorvi
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean {

    private String errorstatus = KeepRecord.getErrorStatus();
    
    private String username = KeepRecord.getUsername();
    private String password = KeepRecord.getPassword();
    private Set<String> roles = KeepRecord.getRoles();

    
    public String getErrorStatus() {
        return KeepRecord.getErrorStatus();
    }

    public void setErrorStatus(String status) {
        //status = KeepRecord.getErrorStatus();
        this.errorstatus = status;
    }

    public String getErrorstatus() {
        return KeepRecord.getErrorStatus();
    }

    public void setErrorstatus(String errorstatus) {
        this.errorstatus = errorstatus;
    }

    public String getUsername() {
        return KeepRecord.getUsername();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return KeepRecord.getPassword();
    }

    public void setPassword(String password) {
        this.password = password;
    }

 
    public LoginBean() {
        
       // errorstatus="";
    }
    
}
