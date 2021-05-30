
package cv5_1;

import java.util.*;
import javax.persistence.*;


public class Cv5_1 {


    public static void read(String meno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cv5_1PU");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Osoba> q = em.createQuery("select o from Osoba o where o.meno=:meno",Osoba.class);
        q.setParameter("meno", meno);
        for (Osoba o: q.getResultList()) {
            for (Predmet p: o.getCvicene()){
                System.out.println(p.getNazov() + " "+p.getPrednasajuci().getMeno());
            }
        }
    }  
    
    
    public static void release(Osoba o){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cv5_1PU");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Osoba> q = em.createQuery("select o from Osoba o where o.meno=:meno", Osoba.class);
        q.setParameter("meno", o.getMeno());
       
        if (q.getResultList().isEmpty())
            return;
        
        Osoba osoba = q.getResultList().get(0);
        
        if (osoba != null) {
            em.getTransaction().begin();
            em.remove(osoba);
            em.getTransaction().commit();
        }
        
    }

    
    
    public static void create(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cv5_1PU");
        EntityManager em = emf.createEntityManager();

        Predmet p1 = new Predmet("aza", 6);
        Predmet p2 = new Predmet("vsa", 6);

        Osoba o1 = new Osoba("vojvoda");
        Osoba o2 = new Osoba("kossacky");
        Osoba o3 = new Osoba("zajac");

        p1.setPrednasajuci(o1);
        p2.setPrednasajuci(o2);
        p2.setCviciaci(new ArrayList<>(Arrays.asList(o2, o3)));
        p1.setCviciaci(new ArrayList<>(Arrays.asList(o3)));

        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
        em.getTransaction().commit();
        
        em.getEntityManagerFactory().getCache().evictAll();
    }
    
    public static void main(String[] args) {
        create();
        read("zajac");
        release(new Osoba("zajac"));
        read("zajac");
    }
    
}
