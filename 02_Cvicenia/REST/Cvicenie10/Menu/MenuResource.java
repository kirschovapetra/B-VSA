
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
    private List<Jedlo> ponuka = new ArrayList<>();
    
    public MenuResource() {
        ponuka.add(new Jedlo("halusky",2));
    }

    // menu/
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Jedlo> getXML() {
        return ponuka;
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getPonuka(){
        String str = "";
        
        for (int i=0; i<ponuka.size(); i++) {
            str+= i+": "+ponuka.get(i).getNazov()+" "+ponuka.get(i).getCena()+"\n";
        }

        return str;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public int addToPonuka(Jedlo jedlo) {
        ponuka.add(jedlo);
        return ponuka.indexOf(jedlo);
    }
    
    //menu/{index}
    @GET
    @Path("{index:[0-9]+}")
    @Produces(MediaType.APPLICATION_XML)
    public Jedlo getJedlo(@PathParam("index") int index) {
        try {
            Jedlo j = ponuka.get(index);
            return j;
        }
        catch (Exception e){return null;}
    }
    
    @PUT
    @Path("{index:[0-9]+}")
    @Consumes(MediaType.APPLICATION_XML) 
    @Produces(MediaType.TEXT_PLAIN)
    public String putJedlo(@PathParam("index") int index,Jedlo jedlo) {
        try {
            Jedlo j = ponuka.get(index);
            j.setCena(jedlo.getCena());
            j.setNazov(jedlo.getNazov());
            return "OK";        
        } catch (Exception e) {
            return "FAIL";
        }
    }
    
    @DELETE
    @Path("{index:[0-9]+}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteJedlo(@PathParam("index") int index) {
        try {
            ponuka.remove(index);
            return "OK";
        } catch (Exception e) {
            return "FAIL";
        }
    }
}
