/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 *
 * @author Poorvi
 */
//@FormAuthenticationMechanismDefinition(
//        loginToContinue = @LoginToContinue(
//                loginPage = "/index.jsp"
//                ,errorPage = "/loginError.jsp"
//              
//        )
//)

//@CustomFormAuthenticationMechanismDefinition(
//        loginToContinue = @LoginToContinue(
//                loginPage = "/index.jsf"
//        )
//)
// Identity Store
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "resources",
        callerQuery = "select password from user where isBlocked != true  and Username = ?",
        groupsQuery = "select g.groupname from groups as g left join user_groups as ug on g.groupname = ug.groupname left join user as u on u.Username = ug.Username where isBlocked != true and u.Username = ?",
        hashAlgorithm = Pbkdf2PasswordHash.class,
        priority = 30)

@ApplicationScoped
public class Project {
    
}
