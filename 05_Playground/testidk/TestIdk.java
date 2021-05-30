/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testidk;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author vsa
 */
public class TestIdk {

  
    public static void main(String[] args) {
        add();
        System.out.println(pocet("p1"));
        maprednasku("akcia2","x");
    }

    static int pocet(String nazov){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testIdkPU");
        EntityManager em = emf.createEntityManager();

       
        TypedQuery<Prednaska> q = em.createQuery("select p from Prednaska p where p.nazov = :nazov",Prednaska.class);
        q.setParameter("nazov",nazov);
        
        
        return q.getResultList().size();
    }
    
    /*
    
    ktorá, vyhľadá v databáze akcie s názvom akcia a zistí či niektorá má prednášku, ktorej autorom
je autor. Ak akcia s takou prednáškou existuje vráti true inak vráti false.
    
    */
    
    static boolean maprednasku(String akcia, String autor) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testIdkPU");
        EntityManager em = emf.createEntityManager();
        em.getEntityManagerFactory().getCache().evictAll();

        TypedQuery<Akcia> q = em.createQuery("select a from Akcia a where a.nazov = :nazov", Akcia.class);
        q.setParameter("nazov", akcia);
     
        for (Akcia a: q.getResultList()) {
            System.out.println(a.getNazov());
            
            for (Prednaska p: a.getPrednasky()){
                System.out.println(p.getNazov());
                    
                if (p.getAutori().contains(autor)) {
                    System.out.println("Akcia "+a.getNazov()+" prednaska "+p.getNazov()+" ma autora "+autor);
                    return true;
                }
            }
        }
        
        return false;
    }
    
    static void add() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testIdkPU");
        EntityManager em = emf.createEntityManager();
     
        
        Akcia a1 = new Akcia("akcia1",new Date());
        Akcia a2 = new Akcia("akcia2", new Date());
        Akcia a3 = new Akcia("akcia3", new Date());
        
        

        
        List<String> autori = new ArrayList<>();
        List<String> autori2 = new ArrayList<>();
        
        autori.add("a");
        autori.add("b");
        autori.add("c");
        
        autori2.add("x");
        autori2.add("y");
        
        
        Prednaska p1 = new Prednaska("p1",autori);
        Prednaska p2 = new Prednaska("p2", autori2);
        Prednaska p3 = new Prednaska("p3", autori);
        Prednaska p4 = new Prednaska("p1", autori2);
        
     
        p1.setAkcia(a1);
        p2.setAkcia(a2);
        p3.setAkcia(a3);
        p4.setAkcia(a1);



        List<Prednaska> listPrednasok1 = new ArrayList<>();
        listPrednasok1.add(p1);
        listPrednasok1.add(p4);  

        List<Prednaska> listPrednasok2 = new ArrayList<>();
        listPrednasok2.add(p2);
       
        List<Prednaska> listPrednasok3 = new ArrayList<>();
        listPrednasok2.add(p3);
        
        a1.setPrednasky(listPrednasok1);
        a2.setPrednasky(listPrednasok2);
        a3.setPrednasky(listPrednasok3);
        
        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        em.persist(p4);
        em.getTransaction().commit();
        
  
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testIdkPU");
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
