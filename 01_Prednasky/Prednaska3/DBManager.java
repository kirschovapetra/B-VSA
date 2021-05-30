/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prednaska3;

import entities.Kniha;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vsa
 */
public class DBManager {
    public EntityManagerFactory emf;
    public EntityManager em;

    public DBManager() {
        emf = Persistence.createEntityManagerFactory("Prednaska3PU");
        em = emf.createEntityManager();
    }
    
    
    public void addBooks() {

        
        List<Kniha> knihy = new LinkedList<Kniha>();
        knihy.add(new Kniha("a",10.2,new Date(), Stav.NAOBJEDNAVKU));
        knihy.add(new Kniha("b",11.2,new Date(), Stav.NAOBJEDNAVKU));
        knihy.add(new Kniha("c",12.2,new Date(), Stav.NASLKADE));
        knihy.add(new Kniha("d",13.2,new Date(), Stav.NASLKADE));
        knihy.add(new Kniha("e",14.2,new Date(), Stav.VYPREDANA));

        
        Kniha x = new Kniha();
       
       for (Kniha k: knihy) {
     
           persist(k);
           
       }
    }

    public void persist(Object object) {
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } 
    }
    
    
    
}
