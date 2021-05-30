/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zadanie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author vsa
 */
public class Zadanie {

    /*static int pocet(String nazov)
ktorá, zistí v databáze počet prednášok s daným názvom .
c) Implementujte funkciu
static boolean maprednasku(String akcia String autor)
ktorá, vyhľadá v databáze akcie s názvom akcia a zistí či niektorá má prednášku, ktorej autorom
je autor. Ak akcia s takou prednáškou existuje vráti true inak vráti false.
     */
    
    public static int pocet(String nazov){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("zadaniePU");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Prednaska> q = em.createQuery("select p from Prednaska p where p.nazov=:nazov",Prednaska.class);
        q.setParameter("nazov",nazov);
        
        return q.getResultList().size();
        
    }
    
    public static boolean maprednasku(String akcia, String autor){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("zadaniePU");
        EntityManager em = emf.createEntityManager();
    
        TypedQuery<Akcia> q = em.createQuery("select p from Akcia p where p.nazov=:nazov", Akcia.class);
        q.setParameter("nazov", akcia);

        
        for (Akcia a: q.getResultList()) {
            for (Prednaska p: a.getPrednasky()) {
                if (p.getAutori().contains(autor)) {
                    return true;
                
                }
            }
        
        }
        return false;
    }
    
    public static void main(String[] args) {
        System.out.println(pocet("p1"));
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("zadaniePU");
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
