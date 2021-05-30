/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;
import javax.ws.rs.core.*;
import javax.ws.rs.*;


@Path("skuska")
@Singleton
public class SkuskaResource {

    @Context
    private UriInfo context;
    private List<Skuska> skusky = new ArrayList<>();
  
    public SkuskaResource() {
        skusky.add(new Skuska("OOP","utorok"));
    }

    
  //todo potom vymaz
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Skuska> getXml() {
        return skusky;
    }

    
    
    
    
    
/*
    1. Pre koreňový resource skuska implementujete metódu
POST, ktorá slúži pre vytvorenie nového resoursu s informáciami o skúške.
• akceptuje XML dokument (MIME: APPLICATION_XML) podľa horeuvedenej špecifikácie.
• a vracia reťazec (MIME: TEXT_PLAIN) so skratkou predmetu, prevzatou z elementu predmet.
    */
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String postNovaSkuska(Skuska novaSkuska) {
        /*
        Pozor. Metóda musí najprv preveriť, či resource s danou skratkou predmetu 
        už neexistuje. Ak exsituje, neurobí nič a vráti reťazec CHYBA.
        */
        if (novaSkuska == null || 
            novaSkuska.getPredmet() == null || 
            novaSkuska.getDen() == null) {
            
            return "CHYBA";
        }
        
        for (Skuska s: skusky) {
            if (s.getPredmet().equals(novaSkuska.getPredmet())) {
                return "CHYBA";
            }
        }
        
        skusky.add(novaSkuska);
        return novaSkuska.getPredmet();

    }
    
    /*
     Metóda zistí, na ktoré skúšky je študent prihlásený a vráti
reťazec, zložený zo skratiek predmetov, na ktoré je prihlásený, oddelených medzerou. (MIME: TEXT_PLAIN)
Ak meno študenta nie je zadané ako parameter požiadavky nevráti nič, príp. prázdny reťazec.
    */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String prihlaseneSkusky(@QueryParam("student") String menoStudenta) {
        String str = "";
        
        if (menoStudenta == null || menoStudenta.equals(""))
            return "";
        
        for (Skuska s : skusky) {
            if (s.getStudent().contains(menoStudenta)) {
               str += s.getPredmet() + " ";
            }
        }
        
        return str;
    }
    
    
    //skuska/{predmet}
    /*
    GET pre MIME TEXT_PLAIN: vráti počet študentov prihlásených na skúšku. Ak resource neexistuje, malo
    by sa vrátiť 0 prípadne HTTP 204 alebo HTTP 404 .
    */
    @GET
    @Path("{predmet}")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer getPocetStudentov(@PathParam("predmet") String nazovPredmetu) {
        try {
            int count = 0;
            for (Skuska s: skusky) {
                if (s.getPredmet().equals(nazovPredmetu)){
                    count = s.getStudent().size();
                    return count;
                }
            }
            return count;
        }
        catch (Exception e) {
            return 0;
        }
    
    }
    
    /*
    GET pre MIME APPLICATION_XML: vyhľadá skúšku podľa skratky predmetu zadaného v URL a vráti
    informácie ako XML dokument s horeuvedenou štrukrúrou. Ak resource pre skúšku z daného predmet
    neexistuje, malo by sa vrátiť HTTP 204 alebo 404 (Pomôcka, implementacia vráti jednoducho null)
    */
    @GET
    @Path("{predmet}")
    @Produces(MediaType.APPLICATION_XML)
    public Skuska getSkuska(@PathParam("predmet") String nazovPredmetu) {
        try {
            for (Skuska s : skusky) {
                if (s.getPredmet().equals(nazovPredmetu)) {
                    return s;
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }

    }  
    
    /*
    POST slúži na prihlásenie študenta na skúšku. Očakáva reťazec s menom študenta (MIME: TEXT_PLAIN). Ak
    je študent s daným menom ešte nie je prihlásený, pridá ho medzi prihlásených študentov. Metóda nevracia nič.
    Ak je už študent prihlásený nerobí nič.
    */
    @POST
    @Path("{predmet}")
    @Consumes(MediaType.TEXT_PLAIN)
    public void postStudentNaSkusku(@PathParam("predmet") String nazovPredmetu, String menoStudenta) {
        try {
            for (Skuska s : skusky) {
                if (s.getPredmet().equals(nazovPredmetu)) {
                    if (s.getStudent() == null)
                        s.setStudent(new ArrayList<>());
                    
                    if (!s.getStudent().contains(menoStudenta))
                        s.getStudent().add(menoStudenta);
                }
            }
        } catch (Exception e) {}

    }
    
    
}
