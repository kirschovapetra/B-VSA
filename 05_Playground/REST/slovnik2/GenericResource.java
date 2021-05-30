/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.*;
import javax.inject.Singleton;
import javax.ws.rs.core.*;
import javax.ws.rs.*;

/**
 * REST Web Service
 *
 * @author vsa
 */
@Path("generic")
@Singleton
public class GenericResource {

    @Context
    private UriInfo context;
    
    
    //<  en,  <jazyk,preklad>   >
    private Map<String,Map<String,String>> slovnik  = new HashMap<>();
    
    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
        Map<String,String> applePreklady = new HashMap<>();
        applePreklady.put("sk","jablko");
        applePreklady.put("cz","jabko");
        applePreklady.put("ge","apfel");
        applePreklady.put("esp","manzana");
        
        Map<String, String> worldPreklady = new HashMap<>();
        worldPreklady.put("sk", "svet");
        worldPreklady.put("cz", "svjet");
        worldPreklady.put("ge", "welt");
        worldPreklady.put("esp", "mundo");
        
        Map<String, String> helloPreklady = new HashMap<>();
        helloPreklady.put("sk", "ahoj");
        helloPreklady.put("cz", "nazdar ty vole");
        helloPreklady.put("ge", "hallo");
        helloPreklady.put("esp", "hola");
        
        
        slovnik.put("apple", applePreklady);
        slovnik.put("world", worldPreklady);
        slovnik.put("hello", helloPreklady);
    }

    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAll() {
        Set<String> jazyky = new HashSet<>();

        for (String en : slovnik.keySet()) {
            Map<String,String> preklady = slovnik.get(en);
            jazyky.addAll(preklady.keySet());
        }
        
        String all = "";
        for (String jazyk : jazyky) {
            all += jazyk + ", ";
        }
        
        return all;
     
    }
    
//    GET pre URI slovnik/{lang}/{word} - vráti preklad slova word do jazyka lang.
    @GET
    @Path("{lang: [a-z]+}/{word: [a-z]+}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getText(@PathParam("lang") String lang, 
                          @PathParam("word") String word) {
        
       return slovnik.get(word).get(lang);
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param lang
     * @param preklad
     * @param word
     */
    @PUT
    @Path("{lang: [a-z]+}/{word: [a-z]+}")
    @Consumes(MediaType.TEXT_PLAIN)
    public void putText(@PathParam("lang") String lang,
            @PathParam("word") String word, String preklad) {
        slovnik.get(word).put(lang, preklad);
    }
    
    @DELETE
    @Path("{lang: [a-z]+}")
    public void deleteSlovnik(@PathParam("lang") String lang) {
        for (String en : slovnik.keySet()) {
            Map<String, String> preklady = slovnik.get(en);
            preklady.remove(lang);
        }
    }
    
    @DELETE
    @Path("{lang: [a-z]+}/{word: [a-z]+}")
    public void deleteSlovo(@PathParam("lang") String lang,
                            @PathParam("word") String word) {
        slovnik.get(word).remove(lang);
    }
}
