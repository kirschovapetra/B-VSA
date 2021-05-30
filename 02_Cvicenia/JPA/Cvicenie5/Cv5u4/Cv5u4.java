
package cv5u4;

import java.util.*;
import javax.persistence.*;


public class Cv5u4 {


    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cv5u4PU");
        EntityManager em = emf.createEntityManager();
        
        //Vytvorí dokument, pridá mu niekoľko podkapitol.
        Dokument hlavny = new Dokument("hlavny dokument", "text hlavneho dokumentu"); 
        Dokument pod1 = new Dokument("podkapitola1", "text podkapitoly1"); 
        Dokument pod2 = new Dokument("podkapitola2", "text podkapitoly2"); 
        Dokument pod3 = new Dokument("podkapitola3", "text podkapitoly3"); 
        
        hlavny.getPodkapitoly().addAll(new ArrayList<>(Arrays.asList(pod1,pod2,pod3)));
        
        //Prvej podkapitole pridá daľšie podkapitoly (na druhej úrovni).
        Dokument podpod1 = new Dokument("podpodkapitola1", "text podpodkapitoly1");
        Dokument podpod2 = new Dokument("podpodkapitola2", "text podpodkapitoly2");
        
        
        pod1.getPodkapitoly().addAll(new ArrayList<>(Arrays.asList(podpod1, podpod2)));
        
        //všetky vytvorené objekty uloží do datbázy (naraz, v rámci jednej transakcie)
        em.getTransaction().begin();
        em.persist(hlavny);
        em.getTransaction().commit();  
        em.getEntityManagerFactory().getCache().evictAll();
        //nasledne načíta dokument opäť z databázy, odstráni z neho prvú kapitolu a aktualizuje stav v databáze.
        
        
        em.getTransaction().begin();
        em.remove(em.find(Dokument.class, pod1.getId()));
//        em.remove(em.find(Dokument.class, podpod2.getId()));
        em.getTransaction().commit();
        em.getEntityManagerFactory().getCache().evictAll();
        
//        System.out.println(em.find(Dokument.class, hlavny.getId()).getPodkapitoly());
    }
    

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cv5u4PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}
