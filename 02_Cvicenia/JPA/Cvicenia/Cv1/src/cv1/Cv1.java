/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv1;

import entities.Kniha;
import javax.persistence.*;

/**
 *
 * @author vsa
 */
public class Cv1 {

    EntityManagerFactory emf;
    EntityManager em;
    
    public Cv1() {
         emf = Persistence.createEntityManagerFactory("Cv1PU");
         em = emf.createEntityManager();
    }
    
    Kniha getKniha(String meno){
       
        return em.find(Kniha.class, meno);
    }
    
    double cenaKnihy(String meno) {
       
        Kniha k = getKniha(meno);
        
        if (k != null){
             
            return k.getCena();
        }
        
        System.out.println("Knihu nemáme");
        return -1.0;
    }
    
    boolean pridajKnihu(String meno, double cena) {
       
        if (getKniha(meno) != null)
            return false;
    
        Kniha k = new Kniha(meno,cena);
               
        
        this.em.getTransaction().begin();
        this.em.persist(k);
        this.em.getTransaction().commit();
        
        return true;
    }
    
    void zlava(String meno) {
        
        Kniha k = getKniha(meno);
        if (k == null)
            return;
        
        
        k.setCena(k.getCena()*0.8);
        
        this.em.getTransaction().begin();
        this.em.persist(k);
        this.em.getTransaction().commit();
    
    }
    
 
    public static void main(String[] args) {
        Cv1 x = new Cv1();

        System.out.println(x.pridajKnihu("k1", 1.1));
        System.out.println(x.pridajKnihu("k2", 2.1));
        System.out.println(x.pridajKnihu("k3", 3.1));
        System.out.println(x.pridajKnihu("k2", 4.1));

        System.out.println("\nCena");
        System.out.println(x.cenaKnihy("k1"));
        System.out.println(x.cenaKnihy("k2"));
        System.out.println(x.cenaKnihy("k3"));
        System.out.println(x.cenaKnihy("k4"));

        x.zlava("k2");

        System.out.println("\nZlava");
        System.out.println(x.cenaKnihy("k1"));
        System.out.println(x.cenaKnihy("k2"));
        System.out.println(x.cenaKnihy("k3"));
        System.out.println(x.cenaKnihy("k4"));
        
    }

   
    
}
