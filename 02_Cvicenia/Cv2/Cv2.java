/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv2;

import java.util.Date;
import javax.persistence.*;

/*
pomocou NativeQuery vyhľadávajú rôzne informácie o osobách, napr.:

Person theBig() zistí a vráti najťazšiu osobu (ak neexistuje null)
 */
public class Cv2 {
    
    static Person theBig(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cv2PU");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> q = (TypedQuery<Person>) em.createNativeQuery("select * from Person order by weight desc",Person.class);
        return q.getResultList().get(0).getWeight() == null ? null : q.getResultList().get(0);
    }
 
    static void create(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cv2PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Person("a",null));
        em.persist(new Person("b",null));
        em.persist(new Person("c",null));
        em.persist(new Person("d",null));
        em.persist(new Person("e",null));
        em.persist(new Person("f",null));
        em.getTransaction().commit();
    }
    
    public static void main(String[] args) {
       create();
       System.out.println(theBig());
        
    }
    
}
