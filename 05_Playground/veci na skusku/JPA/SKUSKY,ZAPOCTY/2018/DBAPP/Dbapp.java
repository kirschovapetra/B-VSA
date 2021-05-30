package dbapp;

import java.util.*;
import javax.persistence.*;


public class Dbapp {

   //dostane meno zákazníka, zistí a vráti koľko faktúr má zákazník s daným menom
    public static int pocetFaktur(String zakaznik){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbappPU");
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Faktura> q = em.createQuery("select f from Faktura f where f.zakaznik=:zakaznik", Faktura.class);
            q.setParameter("zakaznik",zakaznik);
        
            int count = q.getResultList().size();
            return count;
        }
        catch (Exception e) {
            return 0;
        }
    }
    
    
  
    public static void pridajPolozku(int id, String produkt, double cena){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbappPU");
        EntityManager em = emf.createEntityManager();
        
        Faktura foundFaktura  = em.find(Faktura.class,id);
        
        //Ak faktúra s daným id neexistuje neurobí nič.
        if (foundFaktura == null)
            return;
        
        //vytvorí novú položku so zadaným produktom a cenou a pridá ju do faktúry.
        try {
            Polozka polozka = new Polozka(produkt, cena);
            polozka.setFaktura(foundFaktura);
            foundFaktura.getPolozky().add(polozka);
            foundFaktura.setAktualizacia(new Date());
            
            em.getTransaction().begin();
            em.persist(foundFaktura);
            em.getTransaction().commit();
            
            em.getEntityManagerFactory().getCache().evictAll();
        } catch(Exception e){}
        
    }
    
    public static void main(String[] args) {
      
    }

    
    
}
