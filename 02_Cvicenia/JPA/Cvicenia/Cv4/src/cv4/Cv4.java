package cv4;

import entities.Autor;
import entities.Publikacia;

import entities2.Vydavatelstvo2;
import entities2.Publikacia2;
import entities3.Publikacia3;
import entities3.Vydavatelstvo3;
import entities4.Publikacia4;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
1. Publikácia so zoznamom (referencií na) autorov N:N
P: nazov
A: meno

2. Publikácia s referenciou na jej vydavateľa N:1
P: nazov
V: adresa

3. Vydavateľ so zoznamom publikácií, ktoré vydáva 1:N
P: nazov
V: adresa

4. Publikácia so zoznamom mien autorov
P: nazov, zoznam autorov - string

*/


public class Cv4 {
//    vytvorte projekt a v ňom entitné triedy podľa popisu z prednášky.
//    implementujte program, ktorý vytvorí aspoň jeden objekt hlavnej entitnej 
//    triedy a v prípade viacnasobných asociácií pridá aspoň dva asociované objekty.
//    Následne ich pomocou entity managera uloží do databázy.

    public EntityManagerFactory emf;
    public EntityManager em;
    public Cv4(){
        emf = Persistence.createEntityManagerFactory("Cv4PU");
        em = emf.createEntityManager();
    }
    
    public void add1() {
               
        Autor a1 = new Autor("autor1");
        Autor a2 = new Autor("autor2");
        Autor a3 = new Autor("autor3");
        Autor a4 = new Autor("autor4");
        Autor a5 = new Autor("autor5");
        
        List<Autor> l1 = new ArrayList<>();
        List<Autor> l2 = new ArrayList<>();
        
        l1.add(a1);
        l1.add(a2);
        l1.add(a3);
        
        l2.add(a2);
        l2.add(a3);
        l2.add(a4);
        l2.add(a5);
        
        Publikacia p1 = new Publikacia("p1",l1);
        Publikacia p2 = new Publikacia("p2", l2);
        
        
        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
        em.getTransaction().commit();
    }
    
    public void add2() {
        Vydavatelstvo2 v1 = new Vydavatelstvo2("vyd1");
        Vydavatelstvo2 v2 = new Vydavatelstvo2("vyd2");

        Publikacia2 p1 = new Publikacia2("p1", v1);
        Publikacia2 p2 = new Publikacia2("p2", v1);
        Publikacia2 p3 = new Publikacia2("p3", v2);
        Publikacia2 p4 = new Publikacia2("p4", v2);
        Publikacia2 p5 = new Publikacia2("p5", v2);

        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        em.persist(p4);
        em.persist(p5);
        em.getTransaction().commit();
    }
    
    public void add3() {
        Publikacia3 a1 = new Publikacia3("pub1");
        Publikacia3 a2 = new Publikacia3("pub2");
        Publikacia3 a3 = new Publikacia3("pub3");
        Publikacia3 a4 = new Publikacia3("pub4");
        Publikacia3 a5 = new Publikacia3("pub5");

        List<Publikacia3> l1 = new ArrayList<>();
        List<Publikacia3> l2 = new ArrayList<>();

        l1.add(a1);
        l1.add(a2);
    
        l2.add(a3);
        l2.add(a4);
        l2.add(a5);

        Vydavatelstvo3 v1 = new Vydavatelstvo3("vyd1", l1);
        Vydavatelstvo3 v2 = new Vydavatelstvo3("vyd2", l2);

        em.getTransaction().begin();
        em.persist(v1);
        em.persist(v2);
        em.getTransaction().commit();
    }
   
    public void add4() {
    
        List<String> l1 = new ArrayList<>(Arrays.asList("autor1", "autor2", "autor3"));
        
        List<String> l2 = new ArrayList<>(Arrays.asList("autor4", "autor5", "autor6"));
        
        Publikacia4 p1 = new Publikacia4("p1",l1);
        Publikacia4 p2 = new Publikacia4("p2",l2);
 
        
        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
   
        em.getTransaction().commit();
    }
    
    public static void main(String[] args) {
       //new Cv4().add1();
       //new Cv4().add2();
       //new Cv4().add3();
       new Cv4().add4();
        
    }

   
}
