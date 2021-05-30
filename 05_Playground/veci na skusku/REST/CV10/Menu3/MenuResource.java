package rest;

import java.util.*;
import javax.inject.Singleton;
import javax.ws.rs.core.*;
import javax.ws.rs.*;


@Path("menu")
@Singleton
public class MenuResource {

    @Context
    private UriInfo context;
    private List<Menu> tyzdenneMenu = new ArrayList<>();

    public MenuResource() {
    }

    
    // menu/ - ponuka na cely tyzden
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Menu> getMenuNaTyzden() {
        return tyzdenneMenu;
    }
    
    // menu/{day} - ponuka dna
    @GET
    @Path("{day}")
    @Produces(MediaType.APPLICATION_XML)
    public Menu getMenuNaDen(@PathParam("day") String day) {
        for (Menu menu: tyzdenneMenu) {
            if (menu.getDen().equals(day))
                return menu;
        }
        return null;
    }
    
    // menu/{day}/{index} - jedno jedlo v ponuke dna; index od 1
    @GET
    @Path("{day}/{index:[0-9]+}")
    @Produces(MediaType.APPLICATION_XML)
    public Jedlo getJedloNaDen(@PathParam("day") String day, @PathParam("index") int index) {
       
        try{
            for (Menu menu : tyzdenneMenu) {
                if (menu.getDen().equals(day)){
                    TreeMap<Integer,Jedlo> jedla = menu.getJedla();
                    Jedlo toReturn = jedla.get(index);
                    return toReturn;
                }
            }
        } catch (Exception e){
            return null;
        }
        return null;
    }
    
    


    @PUT
    @Path("{day}/{index:[0-9]+}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_XML)
    public String putJedloNaDen(@PathParam("day") String day, @PathParam("index") int index, Jedlo newJedlo) {
        if (index<1)  return "FAIL";
        
        for (Menu menu : tyzdenneMenu) {
            if (menu.getDen().equals(day)) {
                if (menu.getJedla() == null){
                    menu.setJedla(new TreeMap<>());
                }
                menu.getJedla().put(index, newJedlo);
                return "OK";
            }       
        }
        
        Menu m = new Menu(day);
        m.setJedla(new TreeMap<>());
        m.getJedla().put(index, newJedlo);
        tyzdenneMenu.add(m);
        
        return "pridal sa novy den";
    }
    
    @DELETE
    @Path("{day}/{index:[0-9]+}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteJedloNaDen(@PathParam("day") String day, @PathParam("index") int index) {
        try {
            for (Menu menu : tyzdenneMenu) {
                if (menu.getDen().equals(day)) {
                    menu.getJedla().remove(index);
                    return "OK";
                }
            }
        } catch (Exception e) {
            return "FAIL";
        }
        return "FAIL";
    }
}
