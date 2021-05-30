/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv5_2;

import java.util.*;
import javax.persistence.*;


public class Cv5_2 {

    public static void create() {
        Osoba o1 = new Osoba();
        o1.setMeno("Hopcroft");

        Osoba o2 = new Osoba();
        o2.setMeno("Ullman");

        Osoba o3 = new Osoba();
        o3.setMeno("Aho");

        Kniha k1 = new Kniha();
        k1.setId(333L);
        k1.setNazov("Uvod do teorie automatov");

        Kniha k2 = new Kniha();
        k2.setNazov("Algoritmy a datove struktury");

        o1.setDielo(new ArrayList<>());
        o1.getDielo().add(k1);
        o1.getDielo().add(k2);

        o2.setDielo(new ArrayList<>());
        o2.getDielo().add(k1);
        o2.getDielo().add(k2);

        o3.setDielo(new ArrayList<>());
        o3.getDielo().add(k2);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cv5_2PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {

            em.persist(k1);
            em.persist(k2);
            em.persist(o1);
            em.persist(o2);
            em.persist(o3);

            em.getTransaction().commit();
            em.clear();

            

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

    }

    public static void create2() {
        Osoba o1 = new Osoba("Hopcroft");
        Osoba o2 = new Osoba("Ullman");
        Osoba o3 = new Osoba("Aho");
    

        Kniha k1 = new Kniha("Uvod do teorie automatov");
        k1.setId(333L);
        Kniha k2 = new Kniha("Algoritmy a datove struktury");
      
        k1.setAutor(new ArrayList<>(Arrays.asList(o1,o2,o3)));
        k2.setAutor(new ArrayList<>(Arrays.asList(o1,o2,o3)));
       

        o1.setDielo(new ArrayList<>(Arrays.asList(k1, k2)));
        o2.setDielo(new ArrayList<>(Arrays.asList(k1, k2)));
        o3.setDielo(new ArrayList<>(Arrays.asList(k1, k2)));
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cv5_2PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
        
            em.persist(k1);
            em.persist(k2);

            em.getTransaction().commit();
            em.clear();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

    }
    
    
    
    public static void main(String[] args) {
        create2();
     
    }
    
}
