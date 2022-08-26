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
 * Jersey REST client generated for REST resource:RestResource [generic]<br>
 * USAGE:
 * <pre>
 *        ResourceClient client = new ResourceClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author HP
 */
public class ResourceClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:3248/DoubtCart/webresources";

    public ResourceClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        client.register(new MyRestFilter());
        webTarget = client.target(BASE_URI).path("generic");
    }

    public <T> T FilesByResource(Class<T> responseType, String ResourceID) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("FilesByResource/{0}", new Object[]{ResourceID}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T ListResources(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("ListResources");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T CountLikes(Class<T> responseType, String ResourceID) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("CountLikes/{0}", new Object[]{ResourceID}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void DeleteComment(String CommentID) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("DeleteComment/{0}", new Object[]{CommentID})).request().delete();
    }

    public <T> T IsLikedByUser(Class<T> responseType, String ResourceID, String UserID) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("IsLikedByUser/{0}/{1}", new Object[]{ResourceID, UserID}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void LikeResource(String ResourceID, String UserID) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("LikeResource/{0}/{1}", new Object[]{ResourceID, UserID})).request().post(null);
    }

    public <T> T ResourcesBySemester(Class<T> responseType, String Semester) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("ResourcesBySemester/{0}", new Object[]{Semester}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T CommentsByResource(Class<T> responseType, String ResourceID) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("CommentsByResource/{0}", new Object[]{ResourceID}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void SaveProfile(String username, String profileurl) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("SaveProfile/{0}/{1}", new Object[]{username, profileurl})).request().post(null);
    }

    public <T> T ResourcesBySubject(Class<T> responseType, String Subject) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("ResourcesBySubject/{0}", new Object[]{Subject}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T SingleResource(Class<T> responseType, String ResourceID) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("SingleResource/{0}", new Object[]{ResourceID}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T SearchByTitleandSubject(Class<T> responseType, String SearchKey) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("SearchByTitleandSubject/{0}", new Object[]{SearchKey}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void CommentResource(String ResourceID, String UserID, String Comment) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("CommentResource/{0}/{1}/{2}", new Object[]{ResourceID, UserID, Comment})).request().post(null);
    }

    public <T> T CountComments(Class<T> responseType, String ResourceID) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("CountComments/{0}", new Object[]{ResourceID}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
