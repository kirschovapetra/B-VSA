/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Singleton;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

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
    
    List<Menu> jedalnyListok = new ArrayList<>();
    

    public GenericResource(UriInfo context, List<Menu> jedalnyListok) {
        this.context = context;
        this.jedalnyListok = jedalnyListok;
    }

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
       
        Map<Integer, Jedlo> ponuka1 = new HashMap<>();
        Map<Integer, Jedlo> ponuka2 = new HashMap<>();
        Map<Integer, Jedlo> ponuka3 = new HashMap<>();
        Map<Integer, Jedlo> ponuka4 = new HashMap<>();
        Map<Integer, Jedlo> ponuka5 = new HashMap<>();
       
        ponuka1.put(1, new Jedlo("salat1", 78.60));
        ponuka1.put(2, new Jedlo("horcica1", 78.80));
        
        ponuka2.put(1, new Jedlo("salat2", 78.60));
        ponuka2.put(2, new Jedlo("horcica2", 78.80));
        
        ponuka3.put(1, new Jedlo("salat3", 78.60));
        ponuka3.put(2, new Jedlo("horcica3", 78.80));
        
        ponuka4.put(1, new Jedlo("salat4", 78.60));
        ponuka4.put(2, new Jedlo("horcica4", 78.80));
        
        ponuka5.put(1, new Jedlo("salat5", 78.60));
        ponuka5.put(2, new Jedlo("horcica5", 78.80));
        
        jedalnyListok.add(new Menu("PON", ponuka1)); 
        jedalnyListok.add(new Menu("UT", ponuka2));
        jedalnyListok.add(new Menu("STR", ponuka3));
        jedalnyListok.add(new Menu("STV", ponuka4));
        jedalnyListok.add(new Menu("PIA", ponuka5));
        
              
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Menu> getMenu() {
        return jedalnyListok;
    }
    
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public String getDepresion() {
//        String toReturn = "";
//
//        for (Menu jednoMenu : jedalnyListok) {
//
//            toReturn += jednoMenu.getDen() + "\n";
//
//            for (Integer index : jednoMenu.getPonuka().keySet()) {
//                toReturn += "nazov: "
//                        + jednoMenu.getPonuka().get(index).getNazov()
//                        + " cena: "
//                        + jednoMenu.getPonuka().get(index).getCena() + ", ";
//            }
//            toReturn += "\n";
//        }
//
//        return toReturn;
//    }

    @GET
    @Path("{day: (PON|UT|STR|STV|PIA)}")
    @Produces(MediaType.APPLICATION_XML)
    public Collection<Jedlo> getXml(@PathParam("day") String day) {
 
      for(Menu jednoMenu: jedalnyListok) {
            if(jednoMenu.getDen().equals(day)){
                return jednoMenu.getPonuka().values();
            }
        }
      return new ArrayList<>();
    }
    
    
    @GET
    @Path("{day: (PON|UT|STR|STV|PIA)}/{index: [0-9]+}")
    @Produces(MediaType.APPLICATION_XML)
    public Jedlo getByIndex(@PathParam("day") String day,
                            @PathParam("index") Integer index) {
        
        for (Menu jednoMenu : jedalnyListok) {
            if (jednoMenu.getDen().equals(day)) {
                if (jednoMenu.getPonuka().get(index) != null)
                    return jednoMenu.getPonuka().get(index);
            }
        }
        return new Jedlo();
    }
       
    @DELETE
    @Path("{day: (PON|UT|STR|STV|PIA)}/{index: [0-9]+}")
    public void deleteItem(@PathParam("day") String day,
                             @PathParam("index") Integer index) {
           
        for (Menu jednoMenu : jedalnyListok) {
            // hladame den
            if (jednoMenu.getDen().equals(day)) {
                // hladame jedlo

                jednoMenu.getPonuka().remove(index);           
            }
        }

    }
    
    @PUT
    @Path("{day: (PON|UT|STR|STV|PIA)}/{index: [0-9]+}")
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(@PathParam("day") String day,
                       @PathParam("index") Integer index, Jedlo jedlo) {
        
         for(Menu jednoMenu: jedalnyListok) {
             // hladame den
            if(jednoMenu.getDen().equals(day)){
                // hladame jedlo
                 jednoMenu.getPonuka().put(index, jedlo);
      
                 
            }
        }
    }
}
