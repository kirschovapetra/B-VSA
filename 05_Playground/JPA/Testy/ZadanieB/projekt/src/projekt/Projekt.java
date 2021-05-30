/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.util.*;
import javax.persistence.*;

/*

 */
public class Projekt {

    public boolean aktualizujKnihu(String isbn, String nazov, Double cena){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projektPU");
        EntityManager em = emf.createEntityManager();
        Boolean updated = false;
        
        Kniha kniha = em.find(Kniha.class, isbn);
        //neexistuje - vytvori
        if (kniha == null){
            Kniha newKniha = new Kniha(isbn,nazov,cena);
            em.getTransaction().begin();
            em.persist(newKniha);
            em.getTransaction().commit();
            return true;
        }
        //existuje
        else {
            //nazov != null ale v tabulke je nazov null
            if (nazov != null && kniha.getNazov() == null){
                kniha.setNazov(nazov);
                updated = true;
            }
            //oba nazvy != null
            else if (nazov != null && kniha.getNazov() != null) {
                if (!kniha.getNazov().equals(nazov)) {
                    return false;
                }
            }
            
            if (cena != null) {
                kniha.setCena(cena);
                updated = true;
            }
            
            
            if (updated == true){
                em.getTransaction().begin();
                em.persist(kniha);
                em.getTransaction().commit();
                return true;
            }
        
            
        }
        
        return false;
    }
    
    public void aktualizujCeny(Map<String, Double> cennik){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projektPU");
        EntityManager em = emf.createEntityManager();
        

        for (String isbn:cennik.keySet()) {
            aktualizujKnihu(isbn, null, cennik.get(isbn));
        }
        
    }
    
    public static void main(String[] args) {
        Projekt proj = new Projekt();
        Map<String, Double> cennik = new HashMap<>();
        cennik.put("K10", 20.0);    // stara kniha s nazvom
        cennik.put("N10", 20.0);    // nova kniha bez nazvu
        proj.aktualizujCeny(cennik);
//        Projekt p = new Projekt();
//        System.out.println(p.aktualizujKnihu("isbn1","k1",10.0));
//        System.out.println(p.aktualizujKnihu("isbn2",null,20.0));
//        System.out.println(p.aktualizujKnihu("isbn3","k3",20.0));
//        System.out.println(p.aktualizujKnihu("isbn4",null,20.0));
//        System.out.println(p.aktualizujKnihu("isbn5","k5",null));
//        
//        
//        Map<String,Double> map = new HashMap<>();
//        
//        map.put("isbn1", 100.0);
//        map.put("isbn2", 200.0);
//        map.put("isbn3", 300.0);
//        map.put("isbn4", 400.0);
//        map.put("isbn10", 1000.0);
//        
//        p.aktualizujCeny(map);
    }

   
    
}
