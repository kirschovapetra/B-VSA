/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvicenie4;
 
import entities.Autor;
import entities.Kniha;
import entities.Obchod;
import entities.Polozka;
import entities.Vydavatelstvo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vsa
 */
public class Cvicenie4 {
    /*
    Cvicenie 4 uloha 4
    1. Importnut driver a pripojit sa k databaze
    2. Vytvorit entitne triedy cez "Entity Class" podla zadania (Autor, Kniha,...)
    3. v persistence.xml dat generation strategy na drop and create
    4. v triede "Cvicenie4" cez insert code -> use entity manager
       vygenerovat persist() a okopirovat riadky: 
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cvicenie4PU");
       EntityManager em = emf.createEntityManager();
    */

    
    public EntityManagerFactory emf;
    public EntityManager em;

    public Cvicenie4(){
        emf = Persistence.createEntityManagerFactory("Cvicenie4PU");
        em = emf.createEntityManager();
    }
    
    public static void main(String[] args) {
        Cvicenie4 c4 = new Cvicenie4();
        // Ulohy 1-3 rovnake ako prednasky
       
        //uloha 4
        
        /*inicializacia, vytvorenie objektov
          ked bolo objektov vela, napr autori, tak som to pridavala do zoznamov,
          nech tam potom nemam 400 riadkov ked to budem cez persist davat do tabulky
        */
        
        //obchody
        Obchod obchod1 = new Obchod("Martinus");
        Obchod obchod2 = new Obchod("Panta Rhei");
        
        //vydavatelstva
        Vydavatelstvo v1 = new Vydavatelstvo("Ikar");
        Vydavatelstvo v2 = new Vydavatelstvo("Absynt");
       
        //knihy
        List<Kniha> knihy = new ArrayList<>();
        for (int i=0; i<3; i++){
            knihy.add(new Kniha("Kniha"+i,"0"+i));
        }

        //autori
        List<Autor> autori = new ArrayList<>();
        List<Autor> autori2 = new ArrayList<>();
        for (int i=0; i<4; i++){
            autori.add(new Autor("Autor"+i));
            autori2.add(new Autor("Autor2"+i));
        }
        
        //prepojenia tabuliek
        
        //autori knih
        knihy.get(0).setAutori(autori);
        knihy.get(1).setAutori(autori);
        knihy.get(2).setAutori(autori2);
        
        //vydavatelstva knih
        for (int i=0; i<3; i++){
           if (i%2 == 0)
               knihy.get(i).setVydavatel(v1);
           else
               knihy.get(i).setVydavatel(v2);
            
        }
        
        List<Polozka> polozky = new ArrayList<>();
        polozky.add(new Polozka(10.50,knihy.get(0),obchod1));
        polozky.add(new Polozka(11.50,knihy.get(1),obchod1));
        polozky.add(new Polozka(12.50,knihy.get(2),obchod2));
        polozky.add(new Polozka(13.50,knihy.get(0),obchod2));
        polozky.add(new Polozka(14.50,knihy.get(1),obchod2));
        polozky.add(new Polozka(15.50,knihy.get(2),obchod1));
        
        
        /*zapis do tabulky - staci zapisat len knihy a polozky, teda tie objekty, 
        ktore boli na strane "many" */
        c4.em.getTransaction().begin();
        for (Kniha k: knihy)
            c4.em.persist(k); 
        for (Polozka p: polozky)
            c4.em.persist(p);    
        c4.em.getTransaction().commit();
        
    }

}
