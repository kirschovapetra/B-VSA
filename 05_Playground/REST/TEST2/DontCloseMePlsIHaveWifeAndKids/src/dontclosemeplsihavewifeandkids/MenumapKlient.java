/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dontclosemeplsihavewifeandkids;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:MenumapResource [menumap]<br>
 * USAGE:
 * <pre>
 *        MenumapKlient client = new MenumapKlient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author vsa
 */
public class MenumapKlient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/Jedalen/webresources";

    public MenumapKlient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("menumap");
    }

    public void deleteMenuByDay(String den) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{den})).request().delete();
    }

    public void deleteFoodByName(String den, String nazov) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{den, nazov})).request().delete();
    }

    public String getDescriptionByName(String den, String nazov) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{den, nazov}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String getAllFoods(String nazov) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (nazov != null) {
            resource = resource.queryParam("nazov", nazov);
        }
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public void putDescriptionByName(Object requestEntity, String den, String nazov) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{den, nazov})).request(javax.ws.rs.core.MediaType.TEXT_PLAIN).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.TEXT_PLAIN));
    }

    public void close() {
        client.close();
    }
    
}
