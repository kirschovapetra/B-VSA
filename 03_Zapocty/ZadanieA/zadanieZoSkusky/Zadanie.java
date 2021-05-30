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


public class Zadanie {

    /*
Implementujte funkciu
static int
pocet(String nazov)
ktorá, zistí v databáze počet prednášok s daným názvom .

     */
    
    static int pocet(String nazov){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("zadaniePU");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Prednaska> q = em.createQuery(
                "select p from Prednaska p where p.nazov=:nazovParam", Prednaska.class);
        
        q.setParameter("nazovParam", nazov);

        return q.getResultList().size();
    }
    
    
    /*
    c) Implementujte funkciu
static boolean maprednasku(String akcia String autor)
ktorá, vyhľadá v databáze akcie s názvom akcia a zistí či niektorá má prednášku, ktorej autorom
je autor. Ak akcia s takou prednáškou existuje vráti true inak vráti false.
     */
    
    static boolean maprednasku(String akcia, String autor){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("zadaniePU");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Akcia> q = em.createQuery(
                "select p from Akcia p where p.nazov=:nazovParam", Akcia.class);

        q.setParameter("nazovParam", akcia);
        
        for (Akcia a: q.getResultList()) {
            for (Prednaska p: a.getPrednasky()) {
                if (!p.getAutori().isEmpty() && p.getAutori().contains(autor)){
                    return true;
                }
            }
        }
        
        return false;
    }
    
    
    
    public static void main(String[] args) {
//        System.out.println(pocet("prednaska"));
//        System.out.println(pocet("prednaska1"));
//        System.out.println(pocet("prednaska111111111"));

        System.out.println(maprednasku("akcia","autor1"));
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
