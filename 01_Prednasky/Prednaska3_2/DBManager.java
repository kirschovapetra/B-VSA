/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prednaska3_2;

import entities.Kniha2;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author vsa
 */
public class DBManager {
    public EntityManagerFactory emf;
    public EntityManager em;

    public DBManager() {
        emf = Persistence.createEntityManagerFactory("Prednaska3_2PU");
        em = emf.createEntityManager();
    }
    
    public void printBooks() {
        TypedQuery<Kniha2> q = em.createNamedQuery("Kniha2.findAll",Kniha2.class);
         List<Kniha2> knihy = q.getResultList();
         for (Kniha2 k: knihy) {
           System.out.println(k);
       }
    }
    public void findBooksById(Long id) {
        TypedQuery<Kniha2> q = em.createNamedQuery("Kniha2.findById",Kniha2.class);
        q.setParameter("id",id);
        List<Kniha2> knihy = q.getResultList();
        for (Kniha2 k: knihy) {
            System.out.println(k);
       }
    }
    
    public void findBooksByIdCreateQuery(Long id){
        TypedQuery<Kniha2> q = em.createQuery("SELECT k from Kniha2 k where k.id=:id",Kniha2.class);
        q.setParameter("id",id);
        List<Kniha2> knihy = q.getResultList();
        for (Kniha2 k: knihy) {
            System.out.println(k);
       }
    }
    
    
    public void updateWithFind(Long id){
        Kniha2 k = em.find(Kniha2.class,id);
        
        em.getTransaction().begin();
        k.setMeno("NOVE_MENO");
        em.getTransaction().commit();
    }
    
    public void mergeBooks(Long id, String name){
        
        Kniha2 k = new Kniha2(id,name);
        
        em.getTransaction().begin();
        Kniha2 k2 = em.merge(k);
        em.getTransaction().commit();
    }
    

    
    public void addBooks() {

        List<Kniha2> knihy = new LinkedList<Kniha2>();
        knihy.add(new Kniha2("a",10.2,new Date(), "NAOBJEDNAVKU"));
        knihy.add(new Kniha2("b",11.2,new Date(), "NAOBJEDNAVKU"));
        knihy.add(new Kniha2("c",12.2,new Date(), "Stav.NASLKADE"));
        knihy.add(new Kniha2("d",13.2,new Date(), "NASLKADE"));
        knihy.add(new Kniha2("e",14.2,new Date(), "VYPREDANA"));

       for (Kniha2 k: knihy) {

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
