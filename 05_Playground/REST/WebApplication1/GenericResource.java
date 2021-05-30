/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.inject.Singleton;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("generic")
@Singleton
public class GenericResource {

    @Context
    private UriInfo context;

    private TreeMap<Integer,String> menu;

    public GenericResource() {
        menu = new TreeMap<>();
        menu.put(0,"Halusky");
        menu.put(1,"Rezen");
        menu.put(2,"Gulas");
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAll() {
        String all = "";
        for (Integer i : menu.keySet()) {
            all = all + menu.get(i) + "\n";
        }
        return all;
    }

    /**
     * vrati konkretnu polozku menu
     */
    @GET
    @Path("{index: [0-9]+}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMenu(@PathParam("index") int x) {
        if (x < menu.size()) {
            return menu.get(x);
        } else {
            return "nemame";
        }
    }

    /**
     * modifikuje existujucu polozku v menu
     */
    @PUT
    @Path("{index: [0-9]+}")
    @Consumes(MediaType.TEXT_PLAIN)
    public void putMenu(@PathParam("index") int x, String content) {
        if (x < menu.size()) {
            menu.put(x, content);
        }
    }

    /**
     * prida novu polozku do menu
     */
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public void postMenu(String content) {
        menu.put(menu.lastKey() + 1, content);
        
    }

    @DELETE
    @Path("{index: [0-9]+}")
    public void deleteMenu(@PathParam("index") int x) {

        menu.remove(x);
    }
}
