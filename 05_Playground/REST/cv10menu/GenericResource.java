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

    TreeMap<Integer,Jedlo> menu = new TreeMap<>();
    public GenericResource() {
        menu.put(1,new Jedlo("rezen",3.50));
        menu.put(2,new Jedlo("pizza",4.00));
        menu.put(3,new Jedlo("svieckova",3.70));        
        
    }
       
    
    @GET
    @Path("{name: [a-zA-Z]+}")
    @Produces(MediaType.APPLICATION_XML)
    public Jedlo getText(@PathParam("name") String name) {
       for (Integer id : menu.keySet()) {
           if (menu.get(id).getNazov().equals(name)){
               return menu.get(id);
           }
       }
       return null;
    }

    @PUT
    @Path("{name: [a-zA-Z]+}")
    @Consumes(MediaType.APPLICATION_XML)
    public void putText(@PathParam("name") String name, Jedlo jedlo) {
        if (name != jedlo.getNazov()) {
            return;
        }
        for (Integer id : menu.keySet()) {
            if (menu.get(id).getNazov().equals(name)) {
                menu.get(id).setCena(jedlo.getCena());
                menu.get(id).setNazov(jedlo.getNazov());
            }
        }
    }
    
//    @DELETE
//    @Path("{index}")
//    public void delete(@PathParam("index") Integer index) {
//        menu.remove(index);
//    }
    
}
