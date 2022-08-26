/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:UserResource [User]<br>
 * USAGE:
 * <pre>
 *        UserClient client = new UserClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author HP
 */
public class UserClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:3248/DoubtCart/webresources";

    public UserClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("User");
    }

    public <T> T ResetPassword(Class<T> responseType, String Email, String Password, String OTP) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("ResetPassword/{0}/{1}/{2}", new Object[]{Email, Password, OTP})).request().post(null, responseType);
    }

    public <T> T ChangePassword(Class<T> responseType, String oldPassword, String newPassword) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("ChangePassword/{0}/{1}", new Object[]{oldPassword, newPassword})).request().post(null, responseType);
    }

    public void registerUser(String Username, String Email, String Password, String Semester) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("registerUser/{0}/{1}/{2}/{3}", new Object[]{Username, Email, Password, Semester})).request().post(null);
    }

    public <T> T UserInfo(Class<T> responseType, String Username) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("UserInfo/{0}", new Object[]{Username})).request().post(null, responseType);
    }

    public <T> T ForgotPassword(Class<T> responseType, String Email) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("ForgotPassword/{0}", new Object[]{Email})).request().post(null, responseType);
    }

    public void close() {
        client.close();
    }
    
}
