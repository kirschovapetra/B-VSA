package zapocet2;

import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
        try {
            if (kodPredmetu != null) {

                TypedQuery<Predmet> q = em.createQuery("select p from Predmet p where p.kod=:kod", Predmet.class);
                q.setParameter("kod", kodPredmetu);

                if (!q.getResultList().isEmpty()) {
                    Set<Osoba> osoby = q.getResultList().get(0).getVyucujuci();
                    return osoby;
                }

            }

            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /* Vrati pocet predmetov, ktore vyucuje osoba so zadanym menom. 
     * Ak meno nie je zadane alebo osoba s danym menom neexistuje vrati 0.
     * Pozn. Metoda sa moze spolahnut na to, ze v DB je meno osoby jedinecne
     */
    public static int pocetPredmetov(EntityManager em, String meno) throws Exception {
        int count = 0;

        try {
            TypedQuery<Osoba> q = em.createQuery("select o from Osoba o where o.meno=:meno", Osoba.class);
            q.setParameter("meno", meno);

            if (!q.getResultList().isEmpty()) {
                count = q.getResultList().get(0).getPredmety().size();
            }

            return count;
        } catch (Exception e) {
            return 0;
        }
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
        if (meno == null || kodPredmetu == null) {
            return false;
        }

        //Metoda vyhlada osobu podla mena a predmet podla kodu
        TypedQuery<Osoba> q = em.createQuery("select o from Osoba o where o.meno=:meno", Osoba.class);
        q.setParameter("meno", meno);
        Osoba osobaToPersist = q.getResultList().isEmpty() ? null : q.getResultList().get(0);
        
        Predmet predmetToPersist = em.find(Predmet.class, kodPredmetu);

        // Ak osoba s danym menom v DB neexistuje vytvori ju, pricom datum narodenia nebude zadany(ostane null) 
        if (osobaToPersist == null) {
            osobaToPersist = new Osoba(meno, null);
        }

        //Ak predmet s danym kodom neexistuje, vytvori ho, pricom rocnik bude "BC-1".
        if (predmetToPersist == null) {
            predmetToPersist = new Predmet(kodPredmetu, "BC-1");
        }
        
        //Nasledne zaradi osobu medzi vyucujucich predmetu.
        predmetToPersist.getVyucujuci().add(osobaToPersist);
        osobaToPersist.getPredmety().add(predmetToPersist);

        em.getTransaction().begin();
        em.persist(osobaToPersist);
        em.persist(predmetToPersist);
        em.getTransaction().commit();
        
        return true;
    }

}
