/*

Implementujte metódy:




*/
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
    private Map<String, TreeMap<Integer,String>> menuNaCelyTyzden = new TreeMap<>();
    public MenuResource() {
    }

//  menu - vsetky dni, vsetky menu
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllMenus(){
        String str = "";
        for (String den: menuNaCelyTyzden.keySet()) {
            str+=den+": ";
            for (Integer cisloJedla: menuNaCelyTyzden.get(den).keySet()) {
                str+= menuNaCelyTyzden.get(den).get(cisloJedla)+",";
            }
            str+="\n";
        }
        return str;
    }
        
    
    
//  menu/{den} : skratky pracovných dní: Pon, Ut, Str, Stv, Pia
    
//  GET pre URI dňa - vráti počet jedál v danom dni
    @GET
    @Path("{den: (Pon|Ut|Str|Stv|Pia)}")
    @Produces(MediaType.TEXT_PLAIN)
    public int getMenuByDayCount(@PathParam("den") String den) {
        try{
            return menuNaCelyTyzden.get(den).size();
        }
        catch(Exception e){
            return 0;
        }
    }
//  POST pre URI dňa - pridá nové jedlo do menu dňa
    @POST
    @Path("{den: (Pon|Ut|Str|Stv|Pia)}") 
    @Consumes(MediaType.TEXT_PLAIN)
    public void postMenuByDay(@PathParam("den") String den, String jedlo) {
        
        TreeMap<Integer,String> menuNaDen = menuNaCelyTyzden.get(den);
        if (menuNaCelyTyzden.get(den) == null){
            menuNaCelyTyzden.put(den,new TreeMap<>());
            menuNaCelyTyzden.get(den).put(1, jedlo);
        }
        else{
            int index = menuNaDen.lastKey() + 1;
            menuNaDen.put(index, jedlo); 
        }
    }
    
    
//  menu/{den}/{index}: index je poradové číslo jedla začínajúc od 1.
    
//  GET pre URI jedla - názov jedla
    @GET
    @Path("{den : (Pon|Ut|Str|Stv|Pia)}/{index: [0-9]+}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getFoodByDayIndex(@PathParam("den") String den, @PathParam("index") Integer index) {
        if (index < 1) {
            return null;
        }

        if (menuNaCelyTyzden.get(den) == null) {
            return null;
        }

        return menuNaCelyTyzden.get(den).get(index);
    }
    
//  PUT pre URI jedla - modifikuje názov jedla. Pozor! ak také jedlo neexistuje neurobí nič.
    @PUT
    @Path("{den : (Pon|Ut|Str|Stv|Pia)}/{index: [0-9]+}")
    @Consumes(MediaType.TEXT_PLAIN)
    public void putFoodByDayIndex(@PathParam("den") String den, @PathParam("index") Integer index, String foodName) {
        if (index < 1 || menuNaCelyTyzden.get(den) == null || menuNaCelyTyzden.get(den).get(index) == null) {
            return;
        }
        menuNaCelyTyzden.get(den).put(index,foodName);
    }

//  DELETE pre URI jedla - odstráni jedlo z menu, aby sa URI ostatných jedál nezmenilo.
    @DELETE
    @Path("{den : (Pon|Ut|Str|Stv|Pia)}/{index: [0-9]+}")
    public void delFoodByDayIndex(@PathParam("den") String den, @PathParam("index") Integer index) {
        if (index < 1 || menuNaCelyTyzden.get(den) == null) {
            return;
        }
        menuNaCelyTyzden.get(den).remove(index);
    }
}
