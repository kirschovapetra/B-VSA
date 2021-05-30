/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvicenie2;

import entities.Osoba;
import entities.Person;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
/**
 *
 * @author vsa
 */
public class Cvicenie2 {

    /**
     * @param args the command line arguments
     */
    
    /*Uloha 1 a 2  - vytvorenie entitnej triedy Osoba, 
    instancii osob, zapis do DB*/
    public static void main12(String[] args) throws ParseException {
       Osoba o1 = new Osoba();
       Osoba o2 = new Osoba("osoba2",new Date(),45F);
       Osoba o3 = new Osoba("osoba3");
       
       String pattern = "MM-dd-yyyy";
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
       Date date = simpleDateFormat.parse("01-28-1998");
       Osoba o4 = new Osoba("osoba4",date,85F);
       
       
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cvicenie2PU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(o1);
        em.persist(o2);
        em.persist(o3);
        em.persist(o4);
       
        em.getTransaction().commit();
        
        System.out.println(o1.getId());
        System.out.println(o2.getId());
        System.out.println(o3.getId());
        System.out.println(o4.getId());
        
    }

    
    /*Uloha 3  - iny nazov tabulky, povinna vaha, nova DB*/
    public static void main3(String[] args) throws ParseException {
       Osoba o1 = new Osoba();
       Osoba o2 = new Osoba("osoba2",new Date(),45F);
       Osoba o3 = new Osoba("osoba3");
       
       o1.setVaha(50F);
       o3.setVaha(70F);
       
       String pattern = "MM-dd-yyyy";
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
       Date date = simpleDateFormat.parse("01-28-1998");
       Osoba o4 = new Osoba("osoba4",date,85F);
       
       
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cvicenie2PU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(o1);
        em.persist(o2);
        em.persist(o3);
        em.persist(o4);
       
        em.getTransaction().commit();
        
        System.out.println(o1.getId());
        System.out.println(o2.getId());
        System.out.println(o3.getId());
        System.out.println(o4.getId());
        
    }

    
    /*Uloha 4 - mapovanie Person na T_OSOBA, vypis tabulky*/
    public static void showAllPersons(EntityManager em){
        //Native Query
        //Query q = em.createNativeQuery("SELECT * FROM T_OSOBA", Person.class);
        
        //JPQL Query
        Query q = em.createQuery( "Select p from Person p", Person.class );
        
        List<Person> lst = q.getResultList();
        
        for (Person p : lst){
            System.out.println(p);
        }
        
    } 
    public static void main4(String[] args) throws ParseException {
       
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cvicenie2PU");
        EntityManager em = emf.createEntityManager();
        
        showAllPersons(em);
        
    }
    
    
    
    /*uloha 6 - EM je atrubutom triedy*/
    public EntityManagerFactory emf;
    public EntityManager em;

    public Cvicenie2() {
        this.emf = Persistence.createEntityManagerFactory("Cvicenie2PU");
        this.em = emf.createEntityManager();
        
    }
    public void showAllPersons(){
        
        //Native Query
        //Query q = this.em.createNativeQuery("SELECT * FROM T_OSOBA", Person.class);
        
        //JPQL Query
        Query q = this.em.createQuery("SELECT p FROM Person p", Person.class);
        
        List<Person> lst = q.getResultList();
        
        for (Person p : lst){
            System.out.println(p);
        }
        
    } 
    public Long addPerson(String meno, Float vaha){
        Person p = new Person(meno,vaha);
        this.em.getTransaction().begin();
        this.em.persist(p);
        this.em.getTransaction().commit();
        
        return p.getId();
    }
    public Person findPerson(Long id){
        Person p = this.em.find(Person.class, id);
        return p;
    }
    public Long countAll(){
        //Native query
        /*Query q = this.em.createNativeQuery(
                "SELECT COUNT(*) FROM T_OSOBA");*/
        
        //JPQL Query
        Query q = this.em.createQuery(
                "SELECT COUNT(p) FROM Person p");
        
        return  (Long) q.getSingleResult();
    
    }
    public Long countNoNamed(){
        //Native query
        /*Query q = this.em.createNativeQuery(
                "SELECT COUNT(*) FROM T_OSOBA "
                        + "WHERE meno is null");*/
        
        //JPQL Query
        Query q = this.em.createQuery(
                "SELECT COUNT(p) FROM Person p "
                        + "WHERE p.name is null");

        
        return  (Long) q.getSingleResult();
    }
    public Person theBig(){
        //Native query
        /*Query q = this.em.createNativeQuery(
                "SELECT * FROM T_OSOBA ORDER BY vaha DESC",Person.class);*/
        //JPQL Query
        Query q = this.em.createQuery(
                "SELECT p FROM Person p ORDER BY p.weight DESC",Person.class);

        
        return (Person) q.getResultList().get(0);
    }
    
    public static void main(String[] args) throws ParseException {

       Cvicenie2 cv2 = new Cvicenie2();
       cv2.showAllPersons();
       
       Long l = cv2.addPerson("nova osoba",70F);
       System.out.println(l);
       cv2.showAllPersons();
        
       Person p = cv2.findPerson(101L);
       System.out.println("\n"+p+"\n");
       
       System.out.println("\ncount:"+cv2.countAll());
       System.out.println("\ncountNoNamed:"+cv2.countNoNamed());
       System.out.println("\ntheBig:"+cv2.theBig());
       
    }
    
    
    
    
    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cvicenie2PU");
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
