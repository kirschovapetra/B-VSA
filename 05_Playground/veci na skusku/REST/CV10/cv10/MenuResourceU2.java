
package rest;

import java.util.*;
import javax.inject.Singleton;
import javax.ws.rs.core.*;
import javax.ws.rs.*;


@Path("menu2")
@Singleton
public class MenuResourceU2 {

    @Context
    private UriInfo context;
    Map<String,Jedlo> menu= new HashMap<>();

    public MenuResourceU2() {
        
    }
   
    // /menu
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Collection<Jedlo> getMenu() {
        return menu.values();
    }
    
    // /menu/{name}
    
    //GET: Vyhľadá info podľa nazvu zadaného v URI a vráti ju (ako objekt triedy Jedlo).
    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_XML)
    public Jedlo getJedlo(@PathParam("name") String name){
        return menu.get(name);
    }
    
    //PUT: Ako vstup dostane objekt triedy Jedlo, vyhľadá položku podľa nazvu zadaného v URI a aktualizuje informácie jedle na základe vstupu.
    @PUT
    @Path("{name}")
    @Consumes(MediaType.APPLICATION_XML)
    public void putJedlo(@PathParam("name") String name, Jedlo jedlo) {
        menu.put(name,jedlo);
    }
    
    //DELETE
    @DELETE
    @Path("{name}")
    public void deleteJedlo(@PathParam("name") String name){
        menu.remove(name);
    }
}
