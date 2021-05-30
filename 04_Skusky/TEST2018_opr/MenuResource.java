/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.inject.Singleton;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;


@Path("menu")
@Singleton
public class MenuResource {

    @Context
    private UriInfo context;

    List<Menu> menuNaCelyTyzden = new ArrayList<>();
    public MenuResource() {
        List<Jedlo> pon = new ArrayList<>();
        pon.add(new Jedlo("segedin", 89));
        pon.add(new Jedlo("zemlovka od babky", Double.MAX_VALUE));
            
        menuNaCelyTyzden.add(new Menu("PON", pon));
        
        List<Jedlo> ut = new ArrayList<>();
        ut.add(new Jedlo("strudel", 89));
        ut.add(new Jedlo("hamandeggs od oca", Double.MAX_VALUE));
        
        menuNaCelyTyzden.add(new Menu("UT", ut));       
        
//        pon.put(2, "segedin");
    }

    /*
    Pre URL ponuky dňa menu/{den} implementujte metódy:

GET pre MIME application/xml: vráti kompletné menu ako xml s horeuvedenou štruktúrou
    */

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Menu> getXml() {
      return menuNaCelyTyzden;
    }
    /*
    GET pre MIME text/plain: vráti aktuálny počet jedál v menu pre daný deň.
    */
    @GET
    @Path("{day}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount(@PathParam("day") String day) {
        
        for (Menu menu: menuNaCelyTyzden) {
            if (menu.getDen().equals(day)) {
                return menu.getJedla().size()+"";
            }
        }
        return 0+"";
    }
    
    /*
    pre menu/{den}
    GET pre MIME application/xml: vráti kompletné menu ako xml s horeuvedenou štruktúrou
     */
    @GET
    @Path("{day}")
    @Produces(MediaType.APPLICATION_XML)
    public Menu getMenuByDay(@PathParam("day") String day) {

        for (Menu menu : menuNaCelyTyzden) {
            if (menu.getDen().equals(day)) {
                return menu;
            }
        }
        return new Menu();
    }

    
    
    
    /*
    pre menu/{den}
    POST pre MIME application/xml: pridá nové jedlo do menu pre daný deň. Akceptuje xml dokument so
    štruktúrou:
    <jedlo>
    <cena>3.5</cena>
    <nazov>gulas</nazov>
    </jedlo>
    Pred vložením nového jedla do zoznamu metóda overí, či sa v zozname už jedlo s daným názvom
    nenachádza. Ak sa nachádza, nepridáva do zoznamu nové jedlo ale aktualizuje cenu existujúcho.
    */
    
    
    @POST
    @Path("{day}")
    @Consumes(MediaType.APPLICATION_XML)
    public void postXml(@PathParam("day") String day, Jedlo jedlo) {
        for (Menu menu: menuNaCelyTyzden) {
            if (menu.getDen().equals(day)) {
                for (Jedlo j: menu.getJedla()){
                    if (j.getNazov().equals(jedlo.getNazov())){
                        j.setCena(jedlo.getCena());
                        return;
                    }
                }
                menu.getJedla().add(jedlo);
            }
        }
    }
    
    
    /*
    Pre URL jedla menu/{den}/{n} implementujte metódy:
    GET pre MIME text/plain: vráti názov jedla s poradovým číslom n
    */
    @GET
    @Path("{day}/{index: [1-9]+}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getByDayIndex(@PathParam("day") String day,@PathParam("index") int index) {
        for (Menu menu: menuNaCelyTyzden) {
            if (menu.getDen().equals(day)){
                return menu.getJedla().get(index-1).getNazov()+"";
            }
        }
        return "nenaslo sa";
    }
    
    
    //GET pre MIME application/xml: vráti xml so všetkými informáciami o jedle s poradovým číslom n.
    @GET
    @Path("{day}/{index: [1-9]+}")
    @Produces(MediaType.APPLICATION_XML)
    public Jedlo getXmlByDayIndex(@PathParam("day") String day, @PathParam("index") int index) {
        for (Menu menu : menuNaCelyTyzden) {
            if (menu.getDen().equals(day)) {
                return menu.getJedla().get(index - 1);
            }
        }
        return null;
    }
    
    //    DELETE: odstráni z menu jedlo s poradovým číslom n.
    @DELETE
    @Path("{day}/{index: [1-9]+}")
    public void deleteJedlo(@PathParam("day") String day, @PathParam("index") int index) {
        for (Menu menu : menuNaCelyTyzden) {
            if (menu.getDen().equals(day)) {
                menu.getJedla().remove(index - 1);
                return;
            }
        }
    }
}
