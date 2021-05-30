package cv5u3;

import java.util.*;
import javax.persistence.*;


public class Cv5u3 {

    
    public static void create(EntityManager em){
        Osoba osoba1 = new Osoba("fero");
        Osoba osoba2 = new Osoba("jano");
        Osoba osoba3 = new Osoba("stefan");
        
        Predmet predmet1 = new Predmet("VSA", 6);
        Predmet predmet2 = new Predmet("DBS", 5);
    
        
        osoba1.getPrednasanePredmety().add(predmet1);
        
        osoba2.getPrednasanePredmety().add(predmet2);
        osoba2.getCvicenePredmety().add(predmet2);
        
        osoba3.getCvicenePredmety().add(predmet1);
        osoba3.getCvicenePredmety().add(predmet2);
        
        predmet1.setPrednasajuci(osoba1);
        predmet1.getCviciaci().add(osoba3);
        
        predmet2.setPrednasajuci(osoba2);
        predmet2.getCviciaci().add(osoba2);
        predmet2.getCviciaci().add(osoba3);
        
        
        em.getTransaction().begin();
        em.persist(predmet1);
        em.persist(predmet2);
        em.getTransaction().commit();
        
        em.getEntityManagerFactory().getCache().evictAll();
    }
    
    public static void read(EntityManager em, String meno) {
        TypedQuery<Osoba> q = em.createQuery("select f from Osoba f where f.meno =:meno", Osoba.class);
        q.setParameter("meno", meno);
        if (!q.getResultList().isEmpty()) {
            List<Predmet> cvicene = q.getResultList().get(0).getCvicenePredmety();
            System.out.println(cvicene);
        }
    }
    
    public static void release(EntityManager em,Osoba o){
        em.getTransaction().begin();
        em.remove(o);
        em.getTransaction().commit();

    }
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cv5u3PU");
        EntityManager em = emf.createEntityManager();
        
        create(em);
        release(em, em.find(Osoba.class, 3L));
        read(em,"stefan");
    }

   
    
}
