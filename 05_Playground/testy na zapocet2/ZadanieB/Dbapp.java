/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbapp;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author vsa
 */
public class Dbapp {

    
    public static int pocetFaktur(String zakaznik){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbappPU");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Faktura> q = em.createQuery("select f from Faktura f where f.zakaznik = :zakaznik",Faktura.class);
        q.setParameter("zakaznik", zakaznik);
        return q.getResultList().size();
    }
    
        /*
ktorá dostane id faktúry, meno a cenu produktu, vytvorí novú položku so zadaným produktom a
cenou a pridá ju do faktúry. Pri pridaní položky do faktúry súčasne nastaví dátum aktualizácie na
aktuány čas
     */
    public static void pridajPolozku(int id, String produkt, double cena){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbappPU");
        EntityManager em = emf.createEntityManager();
        Faktura f = em.find(Faktura.class,id);
        if (f  != null) {
            f.setAktualizacia(new Date());
            Polozka p = new Polozka(produkt, f, cena);
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        }
    
    }
    
    public static void main(String[] args) {
        pridajPolozku(1,"aa",2.2);
        System.out.println(pocetFaktur("x"));
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbappPU");
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
