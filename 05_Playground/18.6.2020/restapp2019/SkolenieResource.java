package rest;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("skolenie")
@Singleton
public class SkolenieResource {

    @Context
    private UriInfo context;
    private Skolenie skolenie = new Skolenie();
  
    public SkolenieResource() {
        
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Skolenie getRoot() {
       return skolenie; 
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putRoot(Skolenie skolenie) {
        this.skolenie = skolenie;
    }
    
    
    @GET
    @Path("info")
    @Produces(MediaType.TEXT_PLAIN)
    public String getInfo() {
       return skolenie.getInfo();
    }
    
    @PUT
    @Path("info")
    @Consumes(MediaType.TEXT_PLAIN)
    public void putInfo(String info) {
        skolenie.setInfo(info);
        skolenie.setInfo(info);
    }

    @GET
    @Path("ucast")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer getUcastnici() {
       return skolenie.getUcastnik().size();
       
    }

    @PUT
    @Path("ucast")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String postUcastnici(String menoUcastnika) {
       //max 5, neopakuju sa
       List<String> ucastnici = skolenie.getUcastnik();
       if (ucastnici.size() == 5) 
           return "OBSADENE";
       if (ucastnici.contains(menoUcastnika))
        return "DUPLICITA";
       
       ucastnici.add(menoUcastnika);
       return "PRIHLASENY";
    }
    

    @GET
    @Path("ucast/{n}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getUcastnik(@PathParam("n") Integer porCislo) {
        try {
            String meno =  skolenie.getUcastnik().get(porCislo-1);
            return meno;
        }
        catch(Exception e) {
            return null;
        }
    }
}
