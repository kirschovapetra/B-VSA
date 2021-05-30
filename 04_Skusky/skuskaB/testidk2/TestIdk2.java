/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testidk2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/*
1. public static int pocetFaktur(String zakaznik)
ktorá dostane meno zákazníka, zistí a vráti koľko faktúr má zákazník s daným menom.

2. public static void pridajPolozku(int id, String produkt, double cena)
ktorá dostane id faktúry, meno a cenu produktu, vytvorí novú položku so zadaným produktom a
cenou a pridá ju do faktúry. Pri pridaní položky do faktúry súčasne nastaví dátum aktualizácie na
aktuány čas. Ak faktúra s daným id neexistuje neurobí nič.
 */
public class TestIdk2 {

    public static int pocetFaktur(String zakaznik){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestIdk2PU");
        EntityManager em = emf.createEntityManager();

        
        TypedQuery<Faktura> q = em.createQuery("select f from Faktura f where f.zakaznik = :zakaznik",Faktura.class);
        q.setParameter("zakaznik", zakaznik);
    
        return q.getResultList().size();
    }
    
    public static void pridajPolozku(int id, String produkt, double cena){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestIdk2PU");
        EntityManager em = emf.createEntityManager();

        Faktura f = em.find(Faktura.class, new Long(id));
        
        if (f!= null){
        
            f.setAktualizacia(new Date());
            Polozka p = new Polozka(f,produkt, cena);
            
            em.getTransaction().begin();
            
            em.persist(p);
            em.getTransaction().commit();
            
        }
        
        
    }
    
    
    public static void add() throws ParseException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestIdk2PU");
        EntityManager em = emf.createEntityManager();
        
        //2020-04-20
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      
        
        Date d1 = format.parse("2009-12-31");
        Date d2 = format.parse("2010-12-31");
        
        Faktura f1 = new Faktura("zakaznik1",d1);
        Faktura f2 = new Faktura("zakaznik2", d2);
        
        
        Polozka p1 = new Polozka(f1,"produkt1",2.5);
        Polozka p2 = new Polozka(f2, "produkt2", 3.5);
        Polozka p3 = new Polozka(f1, "produkt3", 4.5);
        Polozka p4 = new Polozka(f2, "produkt4", 5.5);
        Polozka p5 = new Polozka(f1, "produkt5", 6.5);
        
        
        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        em.persist(p4);
        em.persist(p5);
        em.getTransaction().commit();
        
        
    }
    
    public static void main(String[] args) throws ParseException {
        add();
        //System.out.println(pocetFaktur("zakaznik1"));
        
        pridajPolozku(2, "novy produkt", 200.00);
    }

   
    
    
}
