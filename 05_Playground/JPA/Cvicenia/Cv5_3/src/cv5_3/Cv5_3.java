
package cv5_3;

import java.util.*;
import javax.persistence.*;

/*

implementujte funkciu void read(String meno), ktorá dostane meno učiteľa, 
nájde všetky predmety, ktoré učiteľ cvičí a na obrazovku vypíše ich názvov 
a meno prednášajúceho.

implementujte funkciu void release(Osoba o), ktorá dostane ako parameter osobu, 
zistí či taká osoba je v DB, ak áno odstráni ju, pričom odstráni aj všetky 
odkazy na ňu, t.j. nesmie ostať už ani prednášajúcim ani cvičiacim, 
žiadneho predmetu. 


*/


public class Cv5_3 {

    public EntityManagerFactory emf;
    public EntityManager em;

    public Cv5_3() {
        emf = Persistence.createEntityManagerFactory("Cv5_3PU");
        em = emf.createEntityManager();
    }
    
    
    public void create(){
        /*
          1. osoba bude prednášať 1. predmet,
          2. osoba bude prednášať aj cvičiť 2. predmet
          3. osoba bude cvičiť 1. aj 2. predmet 
        */
        
        
        Predmet p1 = new Predmet("predmet1");
        Predmet p2 = new Predmet("predmet2");
        
        Osoba o1 = new Osoba("osoba1");
        Osoba o2 = new Osoba("osoba2");
        Osoba o3 = new Osoba("osoba3");
        
        p1.setPrednasajuci(o1);
        p1.setCviciaci(new ArrayList<>(Arrays.asList(o3)));
       
        p2.setPrednasajuci(o2);
        p2.setCviciaci(new ArrayList<>(Arrays.asList(o2,o3)));

        
        o1.setPredmety_prednasane(new ArrayList<>(Arrays.asList(p1)));
        
        o2.setPredmety_prednasane(new ArrayList<>(Arrays.asList(p2)));
        o2.setPredmety_cvicene(new ArrayList<>(Arrays.asList(p2)));
        
        o3.setPredmety_cvicene(new ArrayList<>(Arrays.asList(p1,p2)));
        
        
        em.getTransaction().begin();
        em.persist(o1);
        em.persist(o2);
        em.persist(o3);
        em.getTransaction().commit();
        
    
    }
    
    public void read(String meno){
        TypedQuery<Osoba> q = em.createQuery("SELECT o from Osoba o WHERE o.meno = :meno",Osoba.class);
        q.setParameter("meno",meno);
        
        for (Osoba osoba: q.getResultList()) {
            System.out.println(meno + " cvici:");
            for (Predmet cviceny : osoba.getPredmety_cvicene()) {
                System.out.println(cviceny.getNazov()+ " Prednasa: "+cviceny.getPrednasajuci().getMeno());
            }
        }
        
    }
    public void release(Osoba o){
        Osoba dbOsoba = em.find(Osoba.class,o.getId());
        if (dbOsoba == null)
            return;
        
        em.getTransaction().begin();
        em.remove(dbOsoba);
        em.getTransaction().commit();
    }
    
    public static void main(String[] args) {
        Cv5_3 c = new Cv5_3();
        c.create();

        
        Osoba o = c.em.find(Osoba.class,1L);
        c.release(o);

        c.read("osoba1");
        System.out.println("\n");
        c.read("osoba2");
        System.out.println("\n");
        c.read("osoba3");
        
    }
    
}
