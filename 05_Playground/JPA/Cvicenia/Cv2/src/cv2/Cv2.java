
package cv2;

import entities.Osoba;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class Cv2 {

    public EntityManagerFactory emf;
    public EntityManager em;

    public Cv2() {
        emf = Persistence.createEntityManagerFactory("Cv2PU");
        em = emf.createEntityManager();
    }

    public void showAllPersons(EntityManager em) {
        TypedQuery<Osoba> q = em.createNamedQuery("Osoba.findAll", Osoba.class);
        
        for (Osoba o: q.getResultList()) {
            System.out.println(o);
        }
    }
    public Long addPerson(String name,Integer age, Float weight) {
        Osoba o = new Osoba(name,age,weight);
        
        em.getTransaction().begin();
        em.persist(o);
        em.getTransaction().commit();
        
        
        
        return o.getId();
    }
    
    /*
    pridajte nové metódy, ktoré pomocou NativeQuery vyhľadávajú rôzne informácie o osobách, napr.:
    int countAll_named() ktorá zistí a vráti počet osôb v DB
    int countNoNamed_named() ktorá zistí a vráti počet osôb bez mena
    Person theBig_named() zistí a vráti najťazšiu osobu (ak neexistuje null)
    
    */
    
    public int countAll_named(){
        TypedQuery<Osoba> q = em.createNamedQuery("Osoba.findAll", Osoba.class);
        return q.getResultList().size();
    }
    public int countNoNamed_named() {
        TypedQuery<Osoba> q = em.createNamedQuery("Osoba.findWithoutName", Osoba.class);
        return q.getResultList().size();
    }
    public Osoba theBig_named() {
        TypedQuery<Osoba> q = em.createNamedQuery("Osoba.findMaxWeight", Osoba.class);
        return q.getResultList().get(0);
    }
    
    public int countAll_native() {
        Query q =  em.createNativeQuery("SELECT * FROM Osoba", Osoba.class);
        return q.getResultList().size();
    }

    public int countNoNamed_native() {
        Query q = em.createNativeQuery("SELECT * FROM Osoba WHERE name IS NULL", Osoba.class);
        return q.getResultList().size();
    }

    public Osoba theBig_native() {
        Query q = em.createNativeQuery("SELECT * FROM Osoba ORDER BY weight DESC", Osoba.class);
        return (Osoba) q.getResultList().get(0);
    }
    
    public int countAll_jpql() {
        TypedQuery<Osoba> q = em.createQuery("SELECT o FROM Osoba o", Osoba.class);
        return q.getResultList().size();
    }

    public int countNoNamed_jpql() {
        TypedQuery<Osoba> q = em.createQuery("SELECT o FROM Osoba o WHERE o.name IS NULL", Osoba.class);
        return q.getResultList().size();
    }

    public Osoba theBig_jpql() {
        TypedQuery<Osoba> q = em.createQuery("SELECT o FROM Osoba o ORDER BY o.weight DESC", Osoba.class);
        return q.getResultList().get(0);
    }
    
    public static void main(String[] args) {
        Cv2 x = new Cv2();
        
        System.out.println(x.addPerson("a",1,10f));
        System.out.println(x.addPerson("b",2,20f));
        System.out.println(x.addPerson("c",3,30f));
        System.out.println(x.addPerson(null,4,40f));
        System.out.println(x.addPerson(null,5,50f));
        
        x.showAllPersons(x.em);
        
        System.out.println("\n"+x.countAll_named());
        System.out.println(x.countNoNamed_named());
        System.out.println(x.theBig_named());
        
//        System.out.println("\n" + x.countAll_native());
//        System.out.println(x.countNoNamed_native());
//        System.out.println(x.theBig_native());
        
//        System.out.println("\n" + x.countAll_jpql());
//        System.out.println(x.countNoNamed_jpql());
//        System.out.println(x.theBig_jpql());
    }

   
    
}
