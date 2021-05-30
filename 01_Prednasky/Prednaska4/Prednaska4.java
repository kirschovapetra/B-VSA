
package prednaska4;

import entities.Kniha;
import entities.Osoba;
import entities.Vydavatelstvo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Prednaska4 {
    public EntityManagerFactory emf;
    public EntityManager em;

    public Prednaska4(){
        emf = Persistence.createEntityManagerFactory("Prednaska4PU");
        em = emf.createEntityManager();
    }
    
    public static void main(String[] args) {
        Prednaska4 p4 = new Prednaska4();
        
        //uloha 1 a 2
        Osoba o1 = new Osoba();
        Osoba o2 = new Osoba();
        Osoba o3 = new Osoba();
        Osoba o4 = new Osoba();
        o1.setMeno("Osoba1");
        o2.setMeno("Osoba2");
        o3.setMeno("Osoba3");
        o4.setMeno("Osoba4");
        List<Osoba> lst1 = new ArrayList<>();
        List<Osoba> lst2 = new ArrayList<>();
        lst1.add(o1);
        lst1.add(o2);
        lst2.add(o3);
        lst2.add(o4);
        
        Vydavatelstvo v = new Vydavatelstvo();
        v.setAdresa("Bratislava");
        
        Kniha k1 = new Kniha();
        Kniha k2 = new Kniha();
        k1.setNazov("Kniha1");
        k2.setNazov("Kniha2");
       /* k1.setAutori(lst1);
        k2.setAutori(lst2);
        k1.setVydavatel(v);
        k2.setVydavatel(v);*/
        
        //uloha 3
        Kniha k3 = new Kniha();
        Kniha k4 = new Kniha();
        k3.setNazov("Kniha3");
        k4.setNazov("Kniha4");
        Vydavatelstvo v2 = new Vydavatelstvo();
        v2.setAdresa("Sered");
        List<Kniha> lst3 = new ArrayList<>();
        lst3.add(k4);
        lst3.add(k3);
        v2.setPublikacie(lst3);
        
        //uloha 4
        Kniha k5 = new Kniha();
        k5.setNazov("Kniha5");
        List<String> lst4 = new ArrayList<>();
        lst4.add("Autor1");
        lst4.add("Autor2");
        lst4.add("Autor3");
        
        
        p4.em.getTransaction().begin();
        //uloha 1 a 2
        //p4.em.persist(k1);
        //p4.em.persist(k2);
        //uloha 3
        p4.em.persist(k3);
        p4.em.persist(k4);
        p4.em.persist(v2);
        //uloha 4
        p4.em.persist(k5);
        p4.em.getTransaction().commit();
        
    }

   
    
}
