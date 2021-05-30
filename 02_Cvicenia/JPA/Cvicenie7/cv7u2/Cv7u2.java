/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv7u2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vsa
 */
public class Cv7u2 {

    public static void create(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cv7u2PU");
        EntityManager em = emf.createEntityManager();
        
        Muzeum muzeum = new Muzeum(2, "otvorene", "Sered", "idk", "123", "muzeum");
        
        Nemocnica nemocnica = new Nemocnica(true, true, "Trnava", "ulica", "456", "nemocnica");
        
        Obchod obchod = new Obchod("otvorene", "obec", "ulica", "cislo", "nazov");
        
        Budova budova = new Budova("obec", "ulica", "cislo", "nazov");
        
        Kopec kopec = new Kopec(0, "nazov");
        
        Cesta cesta = new Cesta(1, "nazov");
        
//        GeoObject geoObject = new GeoObject("nazov");
        
        em.getTransaction().begin();
//        em.persist(geoObject);
        em.persist(cesta);
        em.persist(kopec);
        em.persist(budova);
        em.persist(obchod);
        em.persist(nemocnica);
        em.persist(muzeum);
        em.getTransaction().commit();
        
        
        em.getEntityManagerFactory().getCache().evictAll();
        
        em.getTransaction().begin();
        int executeUpdate = em.createQuery("delete from Budova b").executeUpdate();
        em.getTransaction().commit();
    }



    public static void main(String[] args) {
        create();
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cv7u2PU");
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
