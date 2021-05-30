package zapocet2;

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
//        persist(new Osoba());
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("zapocet2PU");
        EntityManager em = emf.createEntityManager();

        novyPredmet(em, "OOP", "Hrach" );
        novyPredmet(em, "VSA", "Mrkva");
        novyPredmet(em, "ASOS", "Mrkva");

        System.out.println("Mrkvov uvazok: " + pocetPrednasok(em, "Mrkva"));    // vypise 2  

        Osoba vyuc = prednasajuci(em, "VSA");
        System.out.println("Prednasajuci VSA: " + vyuc);              
    }

    /* Vrati profesora prednasajuceho predmet s danym kodom
     * Ak kod nie je zadany alebo predmet s danym kodom neexistuje vrati null.
     */
    public static Osoba prednasajuci(EntityManager em, String kodPredmetu) throws Exception {
       
        if(kodPredmetu == null){
            return null;
        }
        
        TypedQuery<Predmet> q = 
                em.createQuery("select p from Predmet p where p.kod =:inputKod", Predmet.class);
      
        q.setParameter("inputKod", kodPredmetu);
        
        if(q.getResultList().isEmpty()) {
            return null;
        }
        
        return q.getResultList().get(0).getProfesor();
//  throw new Exception("NEIMPLEMENTOVANE");
   
    }

    /* Vrati pocet predmetov, ktore prednasa osoba so zadanym menom. 
     * Ak meno nie je zadane alebo osoba s danym menom neexistuje vrati 0.
     * Pozn. Metoda sa moze spolahnut na to, ze v DB je meno osoby jedinecne
     */
    public static int pocetPrednasok(EntityManager em, String meno) throws Exception {
        
        if(meno == null){
            return 0;
        }
        
        TypedQuery<Osoba> q
                = em.createQuery("select o from Osoba o where o.meno =:inputMeno", Osoba.class);
        
        q.setParameter("inputMeno", meno);
        
        if (q.getResultList().isEmpty()) {
            return 0;
        }
        
        return q.getResultList().get(0).getPrednasky().size();
        
//  throw new Exception("NEIMPLEMENTOVANE");
    }

    /* Vytvori novy predmet.
     *
     * @param em            entity manager
     * @param kodPredmetu   kod predmetu
     * @param meno          meno profesora ktory predmet prednasa
     *
     * Metoda naprv zisti ci predmet s danym kodom uz neexistuje.
     *   ak existuje, nerobi nic viac ale vrati false.
     *
     * Ak predmet s danym kodom neexistuje, vytvori ho, pricom rocnik bude "BC-1".
     * Nasledne, ak je meno prednasajuceho zadane, vyhlada osobu s danym menom. 
     *   Ak osoba v DB neexistuje vytvori ju, pricom datum narodenia nebude zadany (ostane null) (sigh)
     * Nakoniec nastavi tuto osobu ako profesora, ktory prednasa novy predmet.
     *   Pozn. ak meno nebolo zadane, prednasajuci profesor ostane null.   
     *   Pozn. metoda sa moze spolahnut na to, ze v DB je meno osoby jedinecne.
     *
     * Navratova hodnota: 
     *   false: ak predmet uz existoval alebo kod predmetu nebol zadany. 
     *   true:  inak. 
     */
    public static boolean novyPredmet(EntityManager em, String kodPredmetu, String meno) throws Exception {
        
       
        if(kodPredmetu == null || em.find(Predmet.class, kodPredmetu) != null){
            return false;
        }
        
        Predmet predmet = new Predmet(kodPredmetu, "BC-1");
        
        if(meno != null){
            TypedQuery<Osoba> q = 
                    em.createQuery("select o from Osoba o where o.meno= :inputMeno", Osoba.class);
            
            q.setParameter("inputMeno", meno);
            
            
            // sekcia profesor
            if(q.getResultList().isEmpty()) {
                Osoba newOsoba = new Osoba(meno, null);
                
                newOsoba.getPrednasky().add(predmet);
                predmet.setProfesor(newOsoba);
                
                em.getTransaction().begin();
                
                em.persist(newOsoba);
                
                em.getTransaction().commit();
                
                
            } else {
                Osoba foundOsoba = q.getResultList().get(0);
                
                predmet.setProfesor(foundOsoba);
                foundOsoba.getPrednasky().add(predmet);
                
                em.getTransaction().begin();

                em.persist(foundOsoba);

                em.getTransaction().commit();
                
            }
                       
        } else {
            em.getTransaction().begin();

            em.persist(predmet);

            em.getTransaction().commit();

        }
        
        //sekcia predmet
        
        return true;
// throw new Exception("NEIMPLEMENTOVANE");
    }

}
