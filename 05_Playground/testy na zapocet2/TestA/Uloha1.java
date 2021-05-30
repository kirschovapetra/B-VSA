
package uloha1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Uloha1 {

    /*
   
Hoci názov tovaru neslúži ako klúč do tabuľky, chceme tiež aby 
    bol jedinečný, preto funkcia pri vkladaní tiež kontroluje, 
    či tovar s rovnakým názvom už nebol do databázy vložený skôr, 
    Ak už bol, druhý krát ho ignoruje. 
    

    */
    
    public static void load(String csv) throws IOException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("uloha1PU");
        EntityManager em = emf.createEntityManager();
        Set<String> nazvy = new HashSet<>();
        String line;
        BufferedReader br = new BufferedReader(new FileReader(csv));
        while ((line = br.readLine()) != null) {
            String s[] = line.split(";");
            if (s.length < 2 || s[0].trim().equals("") || s[1].trim().equals(""))
                continue;
            
            String nazov = s[0].trim();
            Double cena = Double.parseDouble(s[1].trim());
            
            if (!nazvy.contains(nazov)){
            em.getTransaction().begin();
            em.persist(new Tovar(nazov,cena));
            em.getTransaction().commit();
            }
          
            nazvy.add(nazov);
        }

    }
    

    public static void main(String[] args) throws IOException {
        load("/media/sf_2020_VM_SHARED/testy na zapocet2/TestA/data.csv");
        
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("uloha1PU");
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
