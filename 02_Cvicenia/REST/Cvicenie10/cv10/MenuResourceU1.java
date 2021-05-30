package rest;

import java.util.*;
import javax.inject.Singleton;
import javax.ws.rs.core.*;
import javax.ws.rs.*;

@Path("menu1")
@Singleton
//a) list
//public class MenuResourceU1 {
//
//    @Context
//    private UriInfo context;
//    private List<Jedlo> menu = new ArrayList<>();
//   
//    public MenuResourceU1() {
//    }
//
//    // /menu
//    @GET
//    @Produces(MediaType.APPLICATION_XML)
//    public List<Jedlo> getMenu() {
//        return menu;
//    }
//    
//    @POST
//    @Consumes(MediaType.APPLICATION_XML)
//    @Produces(MediaType.TEXT_PLAIN)
//    public Integer addNewJedlo(Jedlo jedlo) {
//        menu.add(jedlo);
//        return menu.indexOf(jedlo);
//    }
//    
//    // /menu/{index}
//    @GET
//    @Path("{index: [0-9]+}")
//    @Produces(MediaType.APPLICATION_XML)
//    public Jedlo getJedloByIndex(@PathParam("index") int index) {
//        return menu.get(index);
//    }
//   
//    /*
//    PUT: Ako vstup dostane objekt triedy Jedlo, vyhľadá položku podľa indexu 
//    zadaného v URI a aktualizuje informácie jedle na základe vstupu. 
//    */
//    @PUT
//    @Path("{index: [0-9]+}")
//    @Consumes(MediaType.APPLICATION_XML)
//    public void updateJedloByIndex(@PathParam("index") int index, Jedlo jedlo) {
//        if (menu.get(index) != null){
//            menu.set(index, jedlo);
//        }
//    }
//    
//
//    //DELETE
//    @DELETE
//    @Path("{index: [0-9]+}")
//    public void deleteJedloByIndex(@PathParam("index") int index) {
//        menu.remove(index);
//    }
//}

//b) treemap

public class MenuResourceU1 {

    @Context
    private UriInfo context;
    private TreeMap<Integer,Jedlo> menu = new TreeMap<>();

    public MenuResourceU1() {
    }

    // /menu
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Collection<Jedlo> getMenu() {
        return menu.values();
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public int addNewJedlo(Jedlo jedlo) {
        try{
            menu.put(menu.lastKey()+1,jedlo);
            return menu.lastKey()+1;
        } 
        catch(Exception e){
            menu.put(0, jedlo);
            return 0;
        }
    }

    // /menu/{index}
    @GET
    @Path("{index: [0-9]+}")
    @Produces(MediaType.APPLICATION_XML)
    public Jedlo getJedloByIndex(@PathParam("index") int index) {
        return menu.get(index);
    }

    /*
    PUT: Ako vstup dostane objekt triedy Jedlo, vyhľadá položku podľa indexu 
    zadaného v URI a aktualizuje informácie jedle na základe vstupu. 
     */
    @PUT
    @Path("{index: [0-9]+}")
    @Consumes(MediaType.APPLICATION_XML)
    public void updateJedloByIndex(@PathParam("index") int index, Jedlo jedlo) {
        if (menu.get(index) != null) {
            menu.put(index, jedlo);
        }
    }

    //DELETE
    @DELETE
    @Path("{index: [0-9]+}")
    public void deleteJedloByIndex(@PathParam("index") int index) {
        menu.remove(index);
    }
}
