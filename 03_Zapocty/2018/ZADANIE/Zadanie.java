package zadanie;

import java.util.*;
import javax.persistence.*;

public class Zadanie {

    //zistí v databáze počet prednášok s daným názvom 
    static int pocet(String nazov){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("zadaniePU");
        EntityManager em = emf.createEntityManager();
        
        try {
            TypedQuery<Prednaska> q = em.createQuery("select p from Prednaska p where p.nazov=:nazov",Prednaska.class);
            q.setParameter("nazov",nazov);
            int count = q.getResultList().size();
            return count;
        }
        catch(Exception e){return 0;}
    }
    
//    vyhľadá v databáze akcie s názvom akcia a zistí či niektorá má prednášku, ktorej autorom je autor. 
//    Ak akcia s takou prednáškou existuje vráti true inak vráti false.
    static boolean maprednasku(String akcia, String autor) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("zadaniePU");
        EntityManager em = emf.createEntityManager();
        
        try {
            TypedQuery<Akcia> q = em.createQuery("select p from Akcia p where p.nazov=:nazov", Akcia.class);
            q.setParameter("nazov", akcia);
            List<Prednaska> prednaskyAkcie = q.getResultList().get(0).getPrednasky();
            for (Prednaska pred: prednaskyAkcie) {
                if (pred.getAutori().contains(autor))
                    return true;
            }
            return false;
           
        } catch (Exception e) {
            return false;
        }
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
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
