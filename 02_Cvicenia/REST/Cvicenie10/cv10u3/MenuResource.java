
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
    
    private List<Menu> weekMenu = new ArrayList<>();

    public MenuResource() {
        /*
        Vytvorte jedlo vyprazany syr s cenou 3.20 a zaraďte ho do menu 
        na každý deň ako prvú položku.
        do menu každého dňa zaraďte dalšie dve jedlá (nemusia byť nutne rôzne) 
        */
        Jedlo jedlo1 = new Jedlo("vyprazany syr",3.2);
        Jedlo jedlo2 = new Jedlo("rezen",5.2);
        Jedlo jedlo3 = new Jedlo("pizza",4.5);
        Jedlo jedlo4 = new Jedlo("salat",2.5);
        Jedlo jedlo5 = new Jedlo("gulas",8.7);
        
        Menu pon = new Menu("PON");
        pon.getJedla().put(1, jedlo1);
        pon.getJedla().put(2, jedlo2);
        pon.getJedla().put(3, jedlo3);
        Menu ut = new Menu("UT");
        ut.getJedla().put(1, jedlo1);
        ut.getJedla().put(2, jedlo4);
        ut.getJedla().put(3, jedlo5);
        Menu str = new Menu("STR");
        str.getJedla().put(1, jedlo1);
        str.getJedla().put(2, jedlo3);
        str.getJedla().put(3, jedlo5);
        Menu stv = new Menu("STV");
        stv.getJedla().put(1, jedlo1);
        stv.getJedla().put(2, jedlo4);
        stv.getJedla().put(3, jedlo5);
        Menu pia = new Menu("PIA");
        pia.getJedla().put(1, jedlo1);
        pia.getJedla().put(2, jedlo2);
        pia.getJedla().put(3, jedlo5);
        
        weekMenu.add(pon);
        weekMenu.add(ut);
        weekMenu.add(str);
        weekMenu.add(stv);
        weekMenu.add(pia);
    }


    
    /********************************** /menu *********************************/
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Menu> getWholeMenu(){

        return weekMenu.isEmpty() ? null : weekMenu;
       
    }
    
    
    /******************** /menu/{day: (PON|UT|STR|STV|PIA)} /******************/
    
    //GET: vráti objekt triedy Menu pre daný deň.
    @GET@Path("{day: (PON|UT|STR|STV|PIA)}")
    @Produces(MediaType.APPLICATION_XML)
    public Menu getMenuByDay(@PathParam("day") String day) {
      
        for (Menu menu: weekMenu){
            if (menu.getDen().equals(day)) {
                return menu;
            }
        }
        
        return null;
    }
    
    //*********** /menu/{day: (PON|UT|STR|STV|PIA)}/{index: [0-9]+} ***********/
    
    
    //GET: Vyhľadá jedlo v ponuke dňa podľa údajov v URI a vráti objekt triedy Jedlo
    @GET
    @Path("{day: (PON|UT|STR|STV|PIA)}/{index: [0-9]+}")
    @Produces(MediaType.APPLICATION_XML)
    public Jedlo getFoodByDayIndex(@PathParam("day") String day, 
                                   @PathParam("index") int index) {
           
        if (index < 1) {
            return null;
        }
        
        try {
           
            for (Menu menu : weekMenu) {
                if (menu.getDen().equals(day)) {
                    TreeMap<Integer, Jedlo> jedlaPreDen = menu.getJedla();
                    return jedlaPreDen.get(index);
                }
            }
        }
        catch(Exception e){
            return null;
        }
        return null;
    }
    /*
    PUT: Vyhľadá jedlo v ponuke dňa podľa údajov v URI a aktualizuje jeho dáta.
        -   Ak položka pre daný index v ponuke dňa ešte neexistuje vytvorte ju 
            (náhrada namiesto POST). 
    */
    @PUT
    @Path("{day: (PON|UT|STR|STV|PIA)}/{index: [0-9]+}")
    @Consumes(MediaType.APPLICATION_XML)
    public void putFoodByDayIndex(@PathParam("day") String day,
                                   @PathParam("index") int index,
                                   Jedlo newJedlo) {

        if (index < 1) {
            return;
        }
   
        for (Menu menu : weekMenu) {
            if (menu.getDen().equals(day)) {
                TreeMap<Integer, Jedlo> jedlaPreDen = menu.getJedla();
                jedlaPreDen.put(index,newJedlo);
                return;
            }
        }
        
        Menu newMenu = new Menu(day);
        newMenu.getJedla().put(index, newJedlo);
        weekMenu.add(newMenu);
        
    }
    
    /*
    DELETE: Vyhľadá jedlo v ponuke dňa podľa údajov v URI a ostráni ho z ponuky dňa. 
    Implementujte tak aby sa po odstránení jedla URI (indexy) ostatných jedál nezmenili.
    */
    @DELETE
    @Path("{day: (PON|UT|STR|STV|PIA)}/{index: [0-9]+}")
    @Consumes(MediaType.APPLICATION_XML)
    public void delFoodByDayIndex(@PathParam("day") String day,
                                  @PathParam("index") int index) {

        if (index < 1) {
            return;
        }

        for (Menu menu : weekMenu) {
            if (menu.getDen().equals(day)) {
                TreeMap<Integer, Jedlo> jedlaPreDen = menu.getJedla();
                jedlaPreDen.remove(index);
            }
        }

    }
}
