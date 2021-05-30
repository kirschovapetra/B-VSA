package rest;

import java.util.ArrayList;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("skolenie")
@Singleton
public class SkolenieResource {

    @Context
    private UriInfo context;
   
    private Skolenie skolenie = new Skolenie();
    
    public SkolenieResource() {
    }

    // skolenie/
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Skolenie getSkolenie() {
        return skolenie;
    }
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putInfo(Skolenie newSkolenie) {
        skolenie = newSkolenie;
    }
    
    // skolenie/info
    @GET
    @Path("info")
    @Produces(MediaType.TEXT_PLAIN)
    public String getInfo() {
       if (skolenie.getInfo() == null)
           return "ZIADNE";
       return skolenie.getInfo();
       
    }
    
    @PUT
    @Path("info")
    @Consumes(MediaType.TEXT_PLAIN)
    public void putInfo(String program) {
        skolenie.setInfo(program);
    }
    
    // skolenie/ucast
    @GET
    @Path("ucast")
    @Produces(MediaType.TEXT_PLAIN)
    public int getUcast() {
        try {
            int count = skolenie.getUcastnik().size();
            return count;
        } catch (Exception e) {return 0;}
        
    }

    @POST
    @Path("ucast")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String pridajUcastnika(String meno) {
        
        if (skolenie.getUcastnik().contains(meno)) 
            return "DUPLICITA";
  
        if (skolenie.getUcastnik().size() == 5)
            return "OBSADENE";
           
        
        skolenie.getUcastnik().add(meno);
        return "PRIHLASENY";
        
        
    }
    
    
    //n od 1
    // skolenie/ucast/{n}
    @GET
    @Path("ucast/{n: [0-9]+}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMenoUcastnika(@PathParam("n") int n) {
        if (n<1)
            return null;
        
        try {
            String name = skolenie.getUcastnik().get(n-1);
            return name;
        } catch(Exception e){return null;}
    }
}
