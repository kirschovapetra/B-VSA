package rest;

import java.util.*;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.*;


@Path("skuska")
@Singleton
public class SkuskaResource {

    @Context
    private UriInfo context;
    private List<String> allowed = new ArrayList<>(Arrays.asList("VSA","DBS","AZA","SWI","RAL"));
 
    //<predmet, <student, znamka> >
    private Map<String,Map<String,String>> hodnotenia = new HashMap<>();
    
    public SkuskaResource() {
        hodnotenia.put("VSA", new HashMap<>());
        hodnotenia.put("DBS", new HashMap<>());
        hodnotenia.put("AZA", new HashMap<>());
    }

    //skuska
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getRoot(@QueryParam("predmet") String predmet, @QueryParam("student") String student){

         //    Ak požiadavka nemá zadaný ani identifikátor predmetu ani identifikátor študenta,  vráti reťazec “CHYBA”
         if (predmet == null && student == null) 
             return "CHYBA";
         
        // Ak je zadaný identifikátor predmetu, metóda a vráti počet študentov, ktorí majú vložené hodnotenie z daného predmetu.  
        //    Ak je okrem identifikátora predmetu zadaný aj identifikátor študenta, identifikátor študenta sa ignoruje.
        if (predmet != null && !allowed.contains(predmet)) {
            //    Ak hodnota parametra predmet neudáva platný identifikátor predmetu, vráti reťazec “NEPLATNY PREDMET”
            return "NEPLATNY PREDMET";
        }

        //    Ak je zadaný len identifikátor študenta, metóda a vráti počet predmetov, pre ktoré má študent vložené hodnotenie. 
        if (predmet == null && student != null) {
            int sz = 0;
            
            for (String p: hodnotenia.keySet()){
                if (hodnotenia.get(p).containsKey(student))
                    sz++;       
            }
            return sz + "";
        }
           
        int sz = hodnotenia.get(predmet) == null ? 0 : hodnotenia.get(predmet).size();
        return sz + "";
    }
    
    //skuska/{pid}
    
    
    //skuska/{pid}/{sid} pid!="" sid!=""
    @GET
    @Path("{pid}/{sid}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getHodnotenie(@PathParam("pid") String pid, @PathParam("sid") String sid){
        if (pid == null || sid == null)
            return null;
        // Ak zadaný identifikátor predmetu nie je platný vráti buď reťazec “NEPLATNY PREDMET” (alebo chybu HTTP 404 príp. HTTP  204).
        if (!allowed.contains(pid))
            return "NEPLATNY PREDMET";
        
        try {
            String s =  hodnotenia.get(pid).get(sid);
            return s;
        }
        catch(Exception e){
            // Ak študent nemá ešte hodnotenie z predmetu, vráti reťazec “NEMA”
            return "NEMA";
        }
  
    }
    
    @PUT
    @Path("{pid}/{sid}")
    @Consumes(MediaType.TEXT_PLAIN)
    public void putHodnotenie(@PathParam("pid") String pid, @PathParam("sid") String sid, String znamka){
        if (pid == null || sid == null)
            return;
        /*
        Ak študent už má hodnotenie v predmete, zmení ho
        Ak študent ešte nemá hodnotenie v predmete,  vytvorí resourse s hodnotením
        */
        try {
         hodnotenia.get(pid).put(sid,znamka);
        }
         catch(Exception e){}
    }
    
    @DELETE
    @Path("{pid}/{sid}")
    public void deleteHodnotenie(@PathParam("pid") String pid, @PathParam("sid") String sid){
        //Ak študent v danom predmete ešte hodnotenie nema alebo identifikátor predmetu nie je platný, nerobí nič
        try {
            hodnotenia.get(pid).remove(sid);
        }
        catch(Exception e) {}
    }
    
}
