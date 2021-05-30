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
    public List<String> jedla;

    public MenuResource() {
        jedla = new ArrayList<String>();
    }

    // /menu
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public int getCount() {
       return jedla.size();
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public int postFood(String jedlo) {
        Set<String> jedlaSet = new HashSet<>();
        jedlaSet.addAll(jedla);
        if (!jedlaSet.contains(jedlo))
            jedla.add(jedlo);
        
        return jedla.indexOf(jedlo);
    }
    
    // /menu/{n}
    @GET
    @Path("{index}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getFoodByIndex(@PathParam("index") int n) {
        try {
            System.out.println("getFoodByIndex: food=" + jedla.get(n));
            return jedla.get(n);
        }
        catch (Exception e) {
            return null;
        }
    }
    @DELETE
    @Path("{index}")
    @Produces(MediaType.TEXT_PLAIN)
    public void deleteFoodByIndex(@PathParam("index") int n) {
        try {
            System.out.println("deleteFoodByIndex: food= "+jedla.get(n));
            jedla.remove(n);
        } catch (Exception e) {}
    }
}
