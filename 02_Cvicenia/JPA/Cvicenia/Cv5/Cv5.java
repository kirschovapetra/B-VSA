
package cv5;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Cv5 {

    EntityManagerFactory emf;
    EntityManager em;
    public Cv5(){
        emf = Persistence.createEntityManagerFactory("Cv5PU");
        em = emf.createEntityManager();

    }
    
  /* 
Implementujte entitné triedy podlľa UML class diagramu a jednoduchý testovaci 
    program, ktorý vytvorí dva predmety a jednu osobu, ktorá bude 
    aj prednášajúcim oboch predmetov.
    */
    public void add1() {
        Predmet p1 = new Predmet("predmet1",10);
        Predmet p2 = new Predmet("predmet2",20);
        
        Osoba o = new Osoba("osoba");
        
        p1.setOsoba(o);
        p2.setOsoba(o);
        
        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
        em.getTransaction().commit();
    }
    /*
Implementujte entitné triedy podlľa UML class diagramu a jednoduchý testovaci 
    program, ktorý vytvorí dva predmety a dve osoby, pričom jedna bude garantom 
    a druhý prednášajúcim oboch predmetov.   
     */
    public void add2() {
        Predmet2 p1 = new Predmet2("predmet1", 10);
        Predmet2 p2 = new Predmet2("predmet2", 20);

        Osoba2 o1 = new Osoba2("garant");
        Osoba2 o2 = new Osoba2("prednasajuci");

        p1.setGarant(o1);
        p2.setGarant(o1);

        p1.setPrednasajuci(o2);
        p2.setPrednasajuci(o2);
        
        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
        em.getTransaction().commit();
    }
    
    public static void main(String[] args) {
       new Cv5().add2();
        
    }

   
}
