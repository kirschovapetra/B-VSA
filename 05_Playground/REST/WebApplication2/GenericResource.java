/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.*;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * REST Web Service
 *
 * @author vsa
 */
@Path("generic")
@Singleton
public class GenericResource {

    @Context
    private UriInfo context;

    TreeMap<Integer, Jedlo> menu = new TreeMap<>();

    public GenericResource() {
        menu.put(1, new Jedlo("rezen", 3.50));
        menu.put(2, new Jedlo("pizza", 4.00));
        menu.put(3, new Jedlo("svieckova", 3.70));

    }
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Collection<Jedlo> getAll() {
       return menu.values();
    }
    
    @GET
    @Path("{name: [a-zA-Z]+}")
    @Produces(MediaType.APPLICATION_XML)
    public Jedlo getText(@PathParam("name") String name) {
        for (Integer id : menu.keySet()) {
            if (menu.get(id).getNazov().equals(name)) {
                return menu.get(id);
            }
        }
        return new Jedlo(name,1.1);
    }

    @PUT
    @Path("{name: [a-zA-Z]+}")
    @Consumes(MediaType.APPLICATION_XML)
    public void putText(@PathParam("name") String name, Jedlo jedlo) {
      
        for (Integer id : menu.keySet()) {
            if (menu.get(id).getNazov().equals(name)) {
                menu.put(id, jedlo);
            }
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public void putText(Jedlo jedlo) {
     
        for (Jedlo j: menu.values()){
            if (j.getNazov().equals(jedlo.nazov))
                return;
        }
             
        menu.put(menu.lastKey(),jedlo);
    }
    
    @DELETE
    @Path("{name: [a-zA-Z]+}")
    public void delete(@PathParam("name") String name) {
        
        for (Integer id : menu.keySet()) {
            if (menu.get(id).getNazov().equals(name)) {
                menu.remove(id);
            }
        }
        
    }
}
