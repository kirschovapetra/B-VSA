/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skuskab;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author vsa
 */
public class SkuskaB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("skuskaBPU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        em.persist(new Faktura(1,"A"));
        em.persist(new Faktura(2,"B"));
        em.persist(new Faktura(4, "B"));
        em.persist(new Faktura(5, "B"));
        em.persist(new Faktura(3,"C"));
        
        em.getTransaction().commit();
            
        pridaPolozku(1, "polozka1", 5.9);
        pridaPolozku(1, "polozka2", 2.9);
        pridaPolozku(2, "polozka3", 0);
        pridaPolozku(3, "polozka4", 5.9);
        pridaPolozku(3, "polozka5", 5.9);
        
        System.out.println(pocetFaktur("A"));
        System.out.println(pocetFaktur("B"));

    }
    
    public static int pocetFaktur(String zakaznik){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("skuskaBPU");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Faktura> q =
                em.createQuery("select f from Faktura f where f.zakaznik=:inputZakaznik", Faktura.class);
    
        q.setParameter("inputZakaznik", zakaznik);
        
        return q.getResultList().size();
    }
    
    public static void pridaPolozku(int id, String produkt, double cena) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("skuskaBPU");
        EntityManager em = emf.createEntityManager();
        
        Faktura faktura = em.find(Faktura.class, id);
        
        if(faktura == null){
            return;
        }
        
        Polozka newPolozka = new Polozka(faktura, produkt, cena);
        
        faktura.getPolozky().add(newPolozka);
        faktura.setAktualizacia(new Date());
        
        em.getTransaction().begin();
        
        em.persist(faktura);
        
        em.getTransaction().commit();
        
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("skuskaBPU");
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
