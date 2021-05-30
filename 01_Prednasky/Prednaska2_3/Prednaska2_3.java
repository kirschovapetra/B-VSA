/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prednaska2_3;

import entities.Book;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vsa
 */
public class Prednaska2_3 {

    /**
     * @param args the command line arguments
     */
    
    public static void main2(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prednaska2_3PU");
        EntityManager em = emf.createEntityManager();
        
        Book b = em.find(Book.class,151L);
        System.out.println(b);
    }
    
    public static void main(String[] args) {
        Book b = new Book();
        b.setName("abc");
        b.setPrice(123.50);
        persist(b);
        System.out.println(b.getId());
        
    }

    public static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prednaska2_3PU");
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
