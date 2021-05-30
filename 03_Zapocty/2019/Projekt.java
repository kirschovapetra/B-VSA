/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import entities.Kniha;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vsa
 */
public class Projekt {

    public boolean aktualizujKnihu(String isbn, String nazov, Double cena){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projektPU");
        EntityManager em = emf.createEntityManager();
        
        Kniha foundKniha = em.find(Kniha.class, isbn);
       
        try {
    //      Ak kniha v databáze neexistuje, vytvorí novú knihu so zadanými údajmi a vráti true.
            if (foundKniha == null){
                Kniha newKniha = new Kniha(isbn, nazov, cena);
                em.getTransaction().begin();
                em.persist(newKniha);
                em.getTransaction().commit();
                return true;
            }

            boolean zapisaloSa = true;

    //      ak argument metódy nazov je zadaný (nenulový) ale názov v databáze je NULL, zapíše zadaný názov do databázy.
            if (nazov != null && foundKniha.getNazov() == null) {
                foundKniha.setNazov(nazov);
            }
    //      ak sú oba názvy nenulové metóda preverí či sa zhodujú, ak nie vráti false (a nerobí žiadne zmeny v databáze).
            if (nazov != null && foundKniha.getNazov() != null) {
                if (!nazov.equals(foundKniha.getNazov())) {
                    zapisaloSa = false;
                } 
                
            }
    //      ak je argument metódy cena zadaný, aktualizuje cenu v databáze.
            if (cena != null) {
                foundKniha.setCena(cena);
            }



            if (zapisaloSa) {
                em.getTransaction().begin();
                em.persist(foundKniha);
                em.getTransaction().commit();
            }

            return zapisaloSa;
        } catch (Exception e) {return false;}
    }
    
    public void aktualizujCeny(Map<String, Double> cennik){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projektPU");
        EntityManager em = emf.createEntityManager();
        
  
        try {
            for (String isbn: cennik.keySet()) {
                aktualizujKnihu(isbn, null, cennik.get(isbn));
            }
        } catch (Exception e) {}
    }
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projektPU");
        EntityManager em = emf.createEntityManager();
    }


    
    
}
