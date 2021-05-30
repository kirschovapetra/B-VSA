
package testa;

import entities.Tovar;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class TestA {

    public static void load(String csv) throws FileNotFoundException, IOException {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testAPU");
        EntityManager em = emf.createEntityManager();
        
        //csv: nazov;cena
        //Hoci názov tovaru neslúži ako klúč do
//        tabuľky, chceme tiež aby bol jedinečný, preto funkcia pri vkladaní tiež 
//        kontroluje, či tovar s rovnakým názvom už nebol do databázy vložený skôr, 
//        Ak už bol, druhý krát ho ignoruje. Môžete predpokladať, že v tabuľka je 
//        na začiatku prázdna, teda  stačí kontrolovať, či sa duplicitné názvy 
//        nenachádzajú vo vstupnom súbore.
 
        Set<String> nazvy = new HashSet<>();
        String line;
        BufferedReader br = new BufferedReader(new FileReader(csv));
        while ((line = br.readLine()) != null) {
            String s[] = line.split(";");
            
            if (s.length<2 || s[0].trim().equals("") || s[1].trim().equals(""))
                continue;
            
            String nazov = s[0].trim();
            double cena = Double.parseDouble(s[1].trim());

           if (nazvy.contains(nazov))
               return;
           
           
           em.getTransaction().begin();
           em.persist(new Tovar(nazov,cena));
           em.getTransaction().commit();

           nazvy.add(nazov);
        }


    }
    

    public static void main(String[] args) throws IOException {
        load("/media/sf_2020_VM_SHARED/testA/data.csv");
        
       
    }

   
}
