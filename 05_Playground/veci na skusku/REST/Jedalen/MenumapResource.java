package rest;

import java.util.*;
import javax.inject.Singleton;
import javax.ws.rs.core.*;
import javax.ws.rs.*;

@Path("menumap")
@Singleton
public class MenumapResource {

    @Context
    private UriInfo context;
    //< den, <jedlo, description>   >
    public Map<String, Map<String, String>> menuNaCelyTyzden = new HashMap<>();
    public List<String> allowed = new ArrayList(Arrays.asList("pondelok", "utorok", "streda", "stvrtok", "piatok"));

    public MenumapResource() {

    }

    // /menumap?nazov
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllFoods(@QueryParam("nazov") String nazovJedla) {
        String str = "";
        if (nazovJedla != null) {

            for (String den : menuNaCelyTyzden.keySet()) {
                if (menuNaCelyTyzden.get(den).containsKey(nazovJedla)) {
                    str += den + " ";
                }
            }
            return str;
        }

        for (String den : menuNaCelyTyzden.keySet()) {
            str += den + "\n";
            for (String jedlo : menuNaCelyTyzden.get(den).keySet()) {
                str += jedlo + ", ";
            }
            str += "\n";
        }
        return str;
    }

    // /menumap/{den}
    @DELETE
    @Path("{den}")
    public void deleteMenuByDay(@PathParam("den") String day) {
        menuNaCelyTyzden.remove(day);
    }

    // /menumap/{den}/{nazov}
    @GET
    @Path("{den}/{nazov}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getDescriptionByName(@PathParam("den") String day, @PathParam("nazov") String name, String description) {
        try {
            Map<String, String> jedlaNaDen = menuNaCelyTyzden.get(day);
            return jedlaNaDen.get(name);
        } catch (Exception e) {
            return null;
        }
    }
    
    @PUT
    @Path("{den}/{nazov}")
    @Consumes(MediaType.TEXT_PLAIN)
    public void putDescriptionByName(@PathParam("den") String day, @PathParam("nazov") String name, String description) {
        try {          
            Map<String, String> jedlaNaDen = menuNaCelyTyzden.get(day);
            jedlaNaDen.put(name, description);
            for(String den: menuNaCelyTyzden.keySet()){
                if (menuNaCelyTyzden.get(den).containsKey(name))
                    menuNaCelyTyzden.get(den).put(name, description);
            }
        } catch (Exception e) {
            menuNaCelyTyzden.put(day, new HashMap<>());
            Map<String, String> jedlaNaDen = menuNaCelyTyzden.get(day);
            jedlaNaDen.put(name, description);
            for (String den : menuNaCelyTyzden.keySet()) {
                if (menuNaCelyTyzden.get(den).containsKey(name)) {
                    menuNaCelyTyzden.get(den).put(name, description);
                }
            }
        }
    }

    @DELETE
    @Path("{den}/{nazov}")
    public void deleteFoodByName(@PathParam("den") String day, @PathParam("nazov") String name) {
        try {
            Map<String, String> jedlaNaDen = menuNaCelyTyzden.get(day);
            jedlaNaDen.remove(name);
        } catch (Exception e) {
        }
    }
}
