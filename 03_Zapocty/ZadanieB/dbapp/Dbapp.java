/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbapp;

import java.util.*;
import javax.persistence.*;

/**
 *
 * @author vsa
 */
public class Dbapp {


    //dostane meno zákazníka, zistí a vráti koľko faktúr má zákazník s daným menom.
    public static int pocetFaktur(String zakaznik){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbappPU");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Faktura> q = em.createQuery("SELECT f FROM Faktura f WHERE f.zakaznik=:zakaznik",Faktura.class);
        q.setParameter("zakaznik",zakaznik);
        return q.getResultList().size();
    }
    
    /*
    dostane id faktúry, meno a cenu produktu, vytvorí novú položku so zadaným produktom a
cenou a pridá ju do faktúry. 
    Pri pridaní položky do faktúry súčasne nastaví dátum aktualizácie na
aktuány čas. 
    */
    public static void pridajPolozku(int id, String produkt, double cena){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbappPU");
        EntityManager em = emf.createEntityManager();
        Faktura faktura = em.find(Faktura.class,id);
        if (faktura == null)
            return;
        
        
        faktura.setAktualizacia(new Date());
        Polozka polozka = new Polozka(faktura,produkt, cena);
        
        em.getTransaction().begin();
        em.persist(polozka);
        em.getTransaction().commit();
        
    }
    
    public static void main(String[] args) {
      
        System.out.println(pocetFaktur("zak1"));
        pridajPolozku(2,"novy produkt",200.00);
    }

 
    
}
