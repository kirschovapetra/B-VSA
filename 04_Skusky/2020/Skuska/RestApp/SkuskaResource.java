/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.*;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

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
    List<Skuska> skusky = new ArrayList<>();
    
    
    
    /*
    <skuska>
        <predmet>OOP</predmet>
        <den>utorok</den>
        <student>Jozef Mrkvicka</student>
        <student>Janko Hrasko</student>
    </skuska>
    
    element predmet obsahuje skratku predmetu, ktorá je zároveň jednoznačným identifikátorom skúšky.Teda musí byť zadaná.
    element den udáva deň konania skúšky.
    */
 
    public SkuskaResource() {
        Skuska s = new Skuska("OOP", "utorok");
        skusky.add(s);
    }

//    @GET //testovacia funkcia, vypise cele xml
//    @Produces(MediaType.APPLICATION_XML)
//    public List<Skuska> getXML() {
//        if (skusky.isEmpty()) {
//            return null;
//        }
//        return skusky;
//    }
//    
    // skuska/
    /*
    Metóda zistí, na ktoré skúšky je študent prihlásený a vráti
reťazec, zložený zo skratiek predmetov, na ktoré je prihlásený, oddelených medzerou. (MIME: TEXT_PLAIN)
Ak meno študenta nie je zadané ako parameter požiadavky nevráti nič, príp. prázdny reťazec.
    */
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getSkusky(@QueryParam("student") String menoStudenta) {
        if (menoStudenta == null)
            return "";
       
        String skuskyStr = "";
        for (Skuska currentSkuska : skusky) {
            if (currentSkuska.getStudent() != null && currentSkuska.getStudent().contains(menoStudenta)){
                skuskyStr += currentSkuska.getPredmet()+" ";
            }
        }
       
        return skuskyStr;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String postSkuska(Skuska newSkuska) {
        //Metóda musí najprv preveriť, či resource s danou skratkou predmetu už neexistuje. Ak exsituje, neurobí nič a vráti reťazec CHYBA
        
        if (newSkuska == null || newSkuska.getPredmet() == null)
            return "CHYBA";
        
        for (Skuska currentSkuska: skusky) {
            if (currentSkuska.getPredmet().equals(newSkuska.getPredmet())) {
                return "CHYBA";
            }
        }
        
        skusky.add(newSkuska);
        return newSkuska.getPredmet();
        
    }
    
  
    
    
    // skuska/{predmet}
    
//    vyhľadá skúšku podľa skratky predmetu zadaného v URL a vráti informácie ako XML dokument s horeuvedenou štrukrúrou.
//    Ak resource pre skúšku z daného predmet neexistuje - vrati null
    @GET
    @Path("{predmet}")
    @Produces(MediaType.APPLICATION_XML)
    public Skuska getSkuska(@PathParam("predmet") String predmet) {
        try {
           
            for (Skuska currentSkuska : skusky) {
                if (currentSkuska.getPredmet().equals(predmet)) {
                    return currentSkuska;
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
    
    //vráti počet študentov prihlásených na skúšku. Ak resource neexistuje, malo by sa vrátiť 0
    @GET
    @Path("{predmet}")
    @Produces(MediaType.TEXT_PLAIN)
    public int getPocetStudentov(@PathParam("predmet") String predmet) {
        try {
            int count = 0;
            for (Skuska currentSkuska : skusky) {
                if (currentSkuska.getPredmet().equals(predmet)) {
                   List<String> studenti = currentSkuska.getStudent();
                   count = studenti.size();
                   break;
                }
            }
            return count;
        } catch (Exception e) {
            return 0;
        }
    }
    
    
    /*
    slúži na prihlásenie študenta na skúšku. Očakáva reťazec s menom študenta (MIME: TEXT_PLAIN).
    Ak študent s daným menom ešte nie je prihlásený, pridá ho medzi prihlásených študentov. 
    Metóda nevracia nič.
    Ak je už študent prihlásený nerobí nič
    */
    @POST
    @Path("{predmet}")
    @Consumes(MediaType.TEXT_PLAIN)
    public void postStudent(@PathParam("predmet") String predmet, String menoStudenta) {
        try {
     
            for (Skuska currentSkuska : skusky) {
                if (currentSkuska.getPredmet().equals(predmet)) {
                    //Ak je už študent prihlásený nerobí nič
                    if (currentSkuska.getStudent().contains(menoStudenta))
                        return;
                    
                    //Ak študent s daným menom ešte nie je prihlásený, pridá ho medzi prihlásených študentov.
                    if (currentSkuska.getStudent() == null ||  currentSkuska.getStudent().isEmpty()){
                        currentSkuska.setStudent(new ArrayList<>());
                    }
                    
                    currentSkuska.getStudent().add(menoStudenta);
                }
            }
            
        } catch (Exception e) {}

    }


}
