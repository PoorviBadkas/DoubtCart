/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:AdminResource [Admin]<br>
 * USAGE:
 * <pre>
 *        AdminClient client = new AdminClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author HP
 */
public class AdminClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:3248/DoubtCart/webresources";

    public AdminClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        client.register(new MyRestFilter());
        webTarget = client.target(BASE_URI).path("Admin");
    }

    public void createResources(String title, String description, String semester, String Subject, String Image) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("createResources/{0}/{1}/{2}/{3}/{4}", new Object[]{title, description, semester, Subject, Image})).request().post(null);
    }

    public void SaveResourceFiles(String ResId, String file) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("SaveResourceFiles/{0}/{1}", new Object[]{ResId, file})).request().post(null);
    }

    public void deleteResources(String ID) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteResources/{0}", new Object[]{ID})).request().delete();
    }

    public void updateResources(String ID, String title, String description, String semester, String Subject, String Image) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateResources/{0}/{1}/{2}/{3}/{4}/{5}", new Object[]{ID, title, description, semester, Subject, Image})).request().put(Entity.json(""));
    }

    public void close() {
        client.close();
    }
    
}
