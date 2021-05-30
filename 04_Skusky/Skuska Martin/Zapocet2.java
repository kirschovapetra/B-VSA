package zapocet2;

import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class Zapocet2 {

    /**
     * @param args the command line arguments
     *
     * Len pre vase testovanie. Mozete si upravit.
     */
    public static void main(String[] args) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("zapocet2PU");
        EntityManager em = emf.createEntityManager();

        pridajVyucujuceho(em, "Hrach", "OOP");
        pridajVyucujuceho(em, "Mrkva", "VSA");
        pridajVyucujuceho(em, "Mrkva", "ASOS");
        pridajVyucujuceho(em, "Mrkva", "ASOS");

        System.out.println("Mrkvov uvazok: " + pocetPredmetov(em, "Mrkva"));    // vypise 2  

        Set<Osoba> z1= vyucujuci(em, "VSA");
        System.out.println("Pocet vyucujucich VSA: " + z1.size());              // vypise 1
    }

    /* Vrati zoznam vyucujucich predmetu so zadanym kodom
     * Ak kod nie je zadany alebo predmet s danym kodom neexistuje vrati null.
     */
    public static Set<Osoba> vyucujuci(EntityManager em, String kodPredmetu) throws Exception {
        if (kodPredmetu != null && ("".equals(kodPredmetu)) != true) {
            Predmet predmet = em.find(Predmet.class, kodPredmetu);
            if (predmet != null) {
                return predmet.getVyucujuci();
            }
        }
        return null;
    }

    /* Vrati pocet predmetov, ktore vyucuje osoba so zadanym menom. 
     * Ak meno nie je zadane alebo osoba s danym menom neexistuje vrati 0.
     * Pozn. Metoda sa moze spolahnut na to, ze v DB je meno osoby jedinecne
     */
    public static int pocetPredmetov(EntityManager em, String meno) throws Exception {
        
        if (meno != null && ("".equals(meno) != true)) {
            Query query = em.createNativeQuery("select * from OSOBA where meno='" + meno + "'", Osoba.class);
            Osoba osoba = (Osoba) query.getSingleResult();
            if (osoba != null) {
                return osoba.getPredmety().size();
            }
        }
        return 0;
    }

    /* Prida predmetu vyucujuceho:
     *
     * @param em            entity manager
     * @param meno          meno vyucujuceho
     * @param kodPredmetu   kod predmetu
     *
     * Metoda vyhlada osobu podla mena a predmet podla kodu. 
     *   Pozn. Metoda sa moze spolahnut na to, ze v DB je meno osoby jedinecne
     * Ak osoba s danym menom v DB neexistuje vytvori ju, pricom 
     *   datum narodenia nebude zadany (ostane null) 
     * Ak predmet s danym kodom neexistuje, vytvori ho, pricom rocnik bude "BC-1". 
     * Nasledne zaradi osobu medzi vyucujucich predmetu. 
     *
     * Navratova hodnota: 
     *   false: ak niektory z argumentov funkcie bol null. 
     *   true:  inak. 
     */
    public static boolean pridajVyucujuceho(EntityManager em, String meno, String kodPredmetu) throws Exception {
        

    if(meno == null || kodPredmetu==null || em==null){
            return false;
        }
        Osoba osoba = null;
        Predmet predmet = null;
        
        TypedQuery<Osoba> t = (TypedQuery)em.createNativeQuery("SELECT * from OSOBA", Osoba.class);
        for(Osoba osoby: t.getResultList()){
            if(osoby.getMeno().equals(meno)){
                osoba =osoby;
            }
        }
        if(osoba==null){
            osoba = new Osoba();
            osoba.setMeno(meno);
            em.getTransaction().begin();
            em.persist(osoba);
            em.getTransaction().commit();
        }
        
        TypedQuery<Predmet> q = (TypedQuery)em.createNativeQuery("SELECT * from PREDMET", Predmet.class);
        for(Predmet p : q.getResultList()){
            if(p.getKod().equals(kodPredmetu)){
                predmet=p;
            }
        }
        if(predmet==null){
            predmet=new Predmet();
        }
            predmet.setKod(kodPredmetu);
            predmet.setRocnik("BC-1");
            predmet.getVyucujuci().add(osoba);
            em.getTransaction().begin();
            em.persist(predmet);
            em.getTransaction().commit();
            
            osoba.getPredmety().add(predmet);
            em.getTransaction().begin();
            em.persist(osoba);
            em.getTransaction().commit();
           
        return true;
    }

}
