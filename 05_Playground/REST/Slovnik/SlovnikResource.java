
package rest;

import java.util.*;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("slovnik")
@Singleton
public class SlovnikResource {

    @Context
    private UriInfo context;
    //  < lang, <en, sk> >
    private Map<String, Map<String,String>> slovnik = new HashMap<>();
   
    public SlovnikResource() {
    }

  // ******************************** slovnik/ ********************************
    
//    GET pre koreňové URI slovnik - vráti reťazec obsahujúci zoznam podporovaných jazykov
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getSupportedLanguages() {
        String s = "";
        for (String lang : slovnik.keySet())
            s+= lang + ", ";
        return s;
    }

    //********************* slovnik/{lang}******************************
    
    @GET
    @Path("{lang}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getLanguageTranslations(@PathParam("lang") String lang) {
        if (slovnik.get(lang) != null)
            return slovnik.get(lang).toString();
        return null;
    }
    
    
    //DELETE pre URI slovnik/{lang}- odstráni celý prekladový slovník pre daný jazyk
    @DELETE
    @Path("{lang}")
    public void deleteLanguage(@PathParam("lang") String lang) {
        if (slovnik.containsKey(lang))
            slovnik.remove(lang);
    }
    
    //********************** slovnik/{lang}/{word} *********************
    
    //GET pre URI slovnik/{lang}/{word} - vráti preklad slova word do jazyka lang.
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{lang}/{word}")
    public String getTranslation(@PathParam("lang") String lang, @PathParam("word") String en) {

        if (slovnik.get(lang) != null) {
            return slovnik.get(lang).get(en);
        }
        return null;
    }
    
    
    //DELETE pre URI slovnik/{lang}/{word}- odstráni preklad slova zo slovníka daného jazyka
    @DELETE
    @Path("{lang}/{word}")
    public void deleteTranslation(@PathParam("lang") String lang, @PathParam("word") String en) {
      
        if (slovnik.get(lang) != null) {
            slovnik.get(lang).remove(en);
        }
    }
    
    /*
    PUT pre URI slovnik/{lang}/{word} - modifikuje preklad slova word do jazyka lang.
    -   Ak preklad daného slova ešte v slovníku nie je pridáme ho tam.
    -   Ak pre daný jazyk ešte nemáme vytvorený slovník vytvoríme ho.
    */
    @PUT
    @Path("{lang}/{word}")
    @Consumes(MediaType.TEXT_PLAIN)
    public void putText(@PathParam("lang") String lang, @PathParam("word") String en, String translation) {
        if (slovnik.get(lang) == null){
            slovnik.put(lang,new HashMap<>());
        }
        slovnik.get(lang).put(en, translation);
    }
}
