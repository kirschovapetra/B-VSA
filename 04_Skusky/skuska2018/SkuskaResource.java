package rest;

import java.util.*;
import javax.inject.Singleton;
import javax.ws.rs.core.*;
import javax.ws.rs.*;

@Path("skuska")
@Singleton
public class SkuskaResource {

    @Context
    private UriInfo context;
    private List<String> allowed = new ArrayList<>(Arrays.asList("VSA", "DBS", "AZA", "SWI", "RAL"));

    // < pid, < sid, znamka >  >
    private Map<String, Map<String, String>> hodnotenia = new HashMap<>();

    public SkuskaResource() {
    }

    // /skuska
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getRoot(@QueryParam("predmet") String pid, @QueryParam("student") String sid) {
        if (pid == null && sid == null) {
            return "CHYBA";
        }

        if (pid != null && !allowed.contains(pid)) {
            return "NEPLATNY PREDMET";
        }

        //Ak je zadaný len identifikátor študenta, metóda a vráti počet predmetov, pre ktoré má študent vložené hodnotenie
        if (sid != null && pid == null) {
            int count = 0;
            for (String currentPid : hodnotenia.keySet()) {
                if (hodnotenia.get(currentPid).containsKey(sid)) {
                    count++;
                }
            }
            return count + "";
        }

        /*
                Ak je zadaný identifikátor predmetu, metóda a vráti počet študentov, ktorí majú vložené hodnotenie
                z daného predmetu. Pozn. Ak je okrem identifikátora predmetu zadaný aj identifikátor študenta,
                identifikátor študenta sa ignoruje.
         */
        if (hodnotenia.containsKey(pid)) {
            return hodnotenia.get(pid).size() + "";
        }
        return "0";
    }

    // /skuska/{pid}/{sid}
    @GET
    @Path("{predmet: (VSA|DBS|AZA|SWI|RAL)}/{student}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getByPidSid(@PathParam("predmet") String pid, @PathParam("student") String sid) {
        //Ak zadaný identifikátor predmetu nie je platný vráti buď reťazec “NEPLATNY PREDMET”
        if (!allowed.contains(pid)) {
            return "NEPLATNY PREDMET";
        }

        //Ak študent nemá ešte hodnotenie z predmetu, vráti reťazec “NEMA”.
        if (!hodnotenia.containsKey(pid) || !hodnotenia.get(pid).containsKey(sid)) {
            return "NEMA";
        }

        return hodnotenia.get(pid).get(sid);
    }

    @PUT
    @Path("{predmet: (VSA|DBS|AZA|SWI|RAL)}/{student}")
    @Consumes(MediaType.TEXT_PLAIN)
    public void putByPidSid(@PathParam("predmet") String pid, @PathParam("student") String sid, String znamka) {

        if (!allowed.contains(pid)) {
            return;
        }

        //Ak študent ešte nemá hodnotenie v predmete, vytvorí resourse s hodnotením.
        if (!hodnotenia.containsKey(pid)) {
            hodnotenia.put(pid, new HashMap<>());
        }

        //Ak študent už má hodnotenie v predmete, zmení ho.
        hodnotenia.get(pid).put(sid, znamka);

    }

    /*
    • DELETE: odstráni hodnotenie študenta sid v predmete pid
        ◦ Ak študent v danom predmete ešte hodnotenie alebo identifikátor predmetu nie je platný, nerobí nič.
     */
    @DELETE
    @Path("{predmet: (VSA|DBS|AZA|SWI|RAL)}/{student}")
    public void deleteByPidSid(@PathParam("predmet") String pid, @PathParam("student") String sid) {

        if (!allowed.contains(pid) || !hodnotenia.containsKey(pid) || !hodnotenia.get(pid).containsKey(sid)) {
            return;
        }

        hodnotenia.get(pid).remove(sid);

    }
}
