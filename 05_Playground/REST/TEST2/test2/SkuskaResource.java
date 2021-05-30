/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Singleton;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author vsa
 */
@Path("skuska")
@Singleton
public class SkuskaResource {

    @Context
    private UriInfo context;
    // <pid <sid, hodnotenie>   >
    Map<String,Map<String,String>> hodnotenia = new HashMap<>();
    List<String> allowed = new ArrayList<>(Arrays.asList("VSA","DBS", "AZA", "SWI", "RAL"));
    
    public SkuskaResource() {
        Map<String,String> vsa = new HashMap<>();
        vsa.put("92222","A");
        vsa.put("86223","A");
        vsa.put("12345","A");
        
        Map<String, String> dbs = new HashMap<>();
        dbs.put("92222", "C");
        dbs.put("67890", "D");
        
        Map<String, String> aza = new HashMap<>();
        aza.put("92222", "B");
        aza.put("222", "FX");
        
        hodnotenia.put("VSA", vsa);
        hodnotenia.put("DBS", dbs);
        hodnotenia.put("AZA", aza);
    }

  /*
    Servis bude poskytovať prístup k stromu resoursov s hodnoteniami študentov v piatich predmetoch.
• Každý z predmetov je reprezentovaný ako subresours s URI skuska/{pid}, kde pid je identifikátor
predmetu. Platné identifikátory predmetov, ktoré sprístupňuje servis sú reťazce:
“VSA”, “DBS”, “AZA”, “SWI” a “RAL”.
• Hodnotenie študenta v danom predmete je dostupné pod URI skuska/{pid}/{sid}, kde pid je
identifikátor predmetu a sid je identifikátor študenta.
◦ Hodnotenie je neprázdny reťazec.
◦ Identifikátor študenta je neprázdny reťazec.
    
    
    
    
    Pre koreňové URL skuska implementujte metódu pre GET, pričom sa predpokladá, že v požiadavke GET
bude zadaný buď identifikátor predmetu ako hodnota parametra predmet alebo identifikátor študenta ako
hodnota parametra student (dva @QueryParam).
• Ak je zadaný identifikátor predmetu, metóda a vráti počet študentov, ktorí majú vložené hodnotenie
z daného predmetu. Pozn. Ak je okrem identifikátora predmetu zadaný aj identifikátor študenta,
identifikátor študenta sa ignoruje.
    
• Ak je zadaný len identifikátor študenta, metóda a vráti počet predmetov, pre ktoré má študent
vložené hodnotenie.
• Ak hodnota parametra predmet neudáva platný identifikátor predmetu, vráti reťazec
“NEPLATNY PREDMET”
• Ak požiadavka nemá zadaný ani identifikátor predmetu ani identifikátor študenta, vráti reťazec
“CHYBA”
    
    
    */
    
    // ...skuska?predmet={pid}&student={sid}
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getText(@QueryParam("predmet") String predmet, @QueryParam("student") String student) {
       
        //Ak požiadavka nemá zadaný ani identifikátor predmetu ani identifikátor študenta, vráti reťazec “CHYBA”
        if (predmet == null && student == null)
            return "CHYBA";
        
        //Ak hodnota parametra predmet neudáva platný identifikátor predmetu, vráti reťazec “NEPLATNY PREDMET”
        if (predmet != null && !allowed.contains(predmet))
            return "NEPLATNY PREDMET";
        
        //Ak je zadaný len identifikátor študenta, metóda a vráti počet predmetov, pre ktoré má študent vložené hodnotenie
        if (predmet == null && student != null) {
           int count = 0;
           for (String pid: hodnotenia.keySet()) {
               if (hodnotenia.get(pid).get(student) != null) 
                   count ++;
           }
           return count+"";
        }
        
        /*
        Ak je zadaný identifikátor predmetu, metóda a vráti počet študentov, ktorí majú vložené hodnotenie
        z daného predmetu. Pozn. Ak je okrem identifikátora predmetu zadaný aj identifikátor študenta,
        identifikátor študenta sa ignoruje.
        */
        try {
            return hodnotenia.get(predmet).keySet().size() + "";
        }
        catch (Exception e) {
            return "CHYBA";
        }
                  
        
        
    }

/*
    Pre URL skuska/{pid}/{sid} implementujte metódy:
• GET: vyhľadá a vráti hodnotenie študenta s identifikátorom sid z predmetu s identifikátorm pid
(MIME: text/plain).
◦ Ak študent nemá ešte hodnotenie z predmetu, vráti reťazec “NEMA”.
◦ Ak zadaný identifikátor predmetu nie je platný vráti buď reťazec “NEPLATNY PREDMET”
(alebo chybu HTTP 404 príp. HTTP 204).
    */
    @GET
    @Path("{pid}/{sid}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getVeci(@PathParam("pid") String pid, @PathParam("sid") String sid) {
        if (pid == null) {
            return "";
        }
        
        if(!allowed.contains(pid)){
            return "NEPLATNY PREDMET";
        }
            

        if (hodnotenia.get(pid) != null && hodnotenia.get(pid).get(sid) == null) {
            return "NEMA";
        }

       return hodnotenia.get(pid).get(sid);

    }
       /*
    • PUT: Zadá resp. zmení hodnotenie študenta sid v predmete pid. Hodnotenie študenta dostane v
obsahu požiadavky (MIME: text/plain).
◦ Ak študent už má hodnotenie v predmete, zmení ho.
◦ Ak študent ešte nemá hodnotenie v predmete, vytvorí resourse(body) s hodnotením.
    */
    @PUT
    @Path("{pid}/{sid}")
    @Consumes(MediaType.TEXT_PLAIN)
    public void putText(@PathParam("pid") String pid, @PathParam("sid") String sid, String content) {
        if(pid == null || sid == null){
            return;
        }
        
        if(hodnotenia.get(pid) == null) {
            return;
        }
        
        hodnotenia.get(pid).put(sid, content);
              
    }
    
    
    /*
    
    • DELETE: odstráni hodnotenie študenta sid v predmete pid
◦ Ak študent v danom predmete ešte hodnotenie alebo identifikátor predmetu nie je platný, nerobí
nič.
    */
    @DELETE
    @Path("{pid}/{sid}")
    public void deleteMyExistence(@PathParam("pid") String pid, @PathParam("sid") String sid) {
        if (pid == null || sid == null) {
            return;
        }
        
        if (!allowed.contains(pid)) {
            return;
        }
        
        hodnotenia.get(pid).remove(sid);
    }
}
