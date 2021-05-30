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

    Map<String, Menu> tyzdenneMenu = new HashMap<>();

    public MenuResource() {
    }

    /*
    <menu>
        <jedlo>
            <cena>3.5</cena>
            <nazov>gulas</nazov>
        </jedlo>
        <jedlo>
            <cena>3.3</cena>
            <nazov>palacinky</nazov>
        </jedlo>
    </menu>
     
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Collection<Menu> getWholeMenu() {
        if (tyzdenneMenu.isEmpty()) {
            return null;
        }
        return tyzdenneMenu.values();
    }

    //Pre URL ponuky dňa menu/{den} implementujte metódy:
    //GET pre MIME text/plain: vráti aktuálny počet jedál v menu pre daný deň.
    @GET
    @Path("{den: (pondelok|utorok|streda|stvrtok|piatok)}")
    @Produces(MediaType.TEXT_PLAIN)
    public int countMeals(@PathParam("den") String den) {
        try {
            int count = tyzdenneMenu.get(den).getJedla().size();
            return count;
        } catch (Exception e) {
            return 0;
        }
    }

    //GET pre MIME application/xml: vráti kompletné menu ako xml s horeuvedenou štruktúrou
    @GET
    @Path("{den: (pondelok|utorok|streda|stvrtok|piatok)}")
    @Produces(MediaType.APPLICATION_XML)
    public Menu getMenuByDay(@PathParam("den") String den) {
        try {
            Menu menu = tyzdenneMenu.get(den);
            return menu;
        } catch (Exception e) {
            return null;
        }
    }

    /*POST pre MIME application/xml: pridá nové jedlo do menu pre daný deň. 
        Pred vložením nového jedla do zoznamu metóda overí, či sa v zozname už jedlo s daným názvom
        nenachádza. Ak sa nachádza, nepridáva do zoznamu nové jedlo ale aktualizuje cenu existujúcho.    
     */
    @POST
    @Path("{den: (pondelok|utorok|streda|stvrtok|piatok)}")
    @Consumes(MediaType.APPLICATION_XML)
    public void postMenuByDay(@PathParam("den") String den, Jedlo newJedlo) {
        try {

            if (tyzdenneMenu.containsKey(den)) {
                for (Jedlo currentJedlo : tyzdenneMenu.get(den).getJedla()) {
                    if (currentJedlo.getNazov().equals(newJedlo.getNazov())) {
                        currentJedlo.setCena(newJedlo.getCena());
                        return;
                    }
                }
            } else {
                tyzdenneMenu.put(den, new Menu());
            }
            tyzdenneMenu.get(den).getJedla().add(newJedlo);

        } catch (Exception e) {
        }
    }

    // menu/{den}/{n} implementujte metódy:
    // GET pre MIME text/plain: vráti názov jedla s poradovým číslom n.
    @GET
    @Path("{den: (pondelok|utorok|streda|stvrtok|piatok)}/{n: [0-9]+}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getFoodName(@PathParam("den") String den, @PathParam("n") int n) {
        if (n < 1) {
            return null;
        }

        try {
            Jedlo jedlo = tyzdenneMenu.get(den).getJedla().get(n - 1);
            return jedlo.getNazov();

        } catch (Exception e) {
            return null;
        }
    }

    // GET pre MIME application/xml: vráti xml so všetkými informáciami o jedle s poradovým číslom n.
    @GET
    @Path("{den: (pondelok|utorok|streda|stvrtok|piatok)}/{n: [0-9]+}")
    @Produces(MediaType.APPLICATION_XML)
    public Jedlo getFood(@PathParam("den") String den, @PathParam("n") int n) {
        if (n < 1) {
            return null;
        }

        try {
            Jedlo jedlo = tyzdenneMenu.get(den).getJedla().get(n - 1);
            return jedlo;

        } catch (Exception e) {
            return null;
        }
    }

    // DELETE: odstráni z menu jedlo s poradovým číslom n. 
    @GET
    @Path("{den: (pondelok|utorok|streda|stvrtok|piatok)}/{n: [0-9]+}")
    public void deleteFood(@PathParam("den") String den, @PathParam("n") int n) {
        if (n < 1) {
            return;
        }

        try {
            tyzdenneMenu.get(den).getJedla().remove(n - 1);
        } catch (Exception e) {
        }
    }

}
