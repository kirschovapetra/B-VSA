/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv2_zadanie;

import entities.Kniha;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*


Pri importe údajov z csv-súboru použite ako

Keďže nazov je klúč, nesmie byť prázdny a musí byť jedinečný. Kontrolu toho, či sa v 1. stĺpci vstupného súboru nenachádzajú duplicity urobí program ešte pred tým než záznam vloží do databázy. Návod: pre kontrolu duplicít použite kontainer java.util.set.

Pred vložením textových údajov odstráňte z reťazcov prázdne znaky pred a za. Prázdne reťazce do databázy nevkladajte.
 */



public class Cv2_zadanie {
 
    public EntityManagerFactory emf;
    public EntityManager em;
    
    public Cv2_zadanie() {
        emf = Persistence.createEntityManagerFactory("Cv2_zadaniePU");
        em = emf.createEntityManager();
    }
    
    public void read() throws IOException {
 
        String line;
        Set<String> nazvy = new HashSet<>();
        BufferedReader br = new BufferedReader(new FileReader("/media/sf_2020_VM_SHARED/JPA stuff/Cv2_zadanie/data1.csv"));
        while ((line = br.readLine()) != null) {
            if (line.isEmpty())
                return;
                      
            String s[] = line.split(";");
            if (s.length < 3) {
                return;
            }
           
            String nazov = s[0].trim();
            String autor = s[1].trim();
            Integer pocet = Integer.parseInt(s[2].trim());
            
            
            if (nazov.length() == 0 || nazvy.contains(nazov))
                return;
                     
            Kniha k = new Kniha(nazov,autor,pocet);
            this.em.getTransaction().begin();
            this.em.persist(k);
            this.em.getTransaction().commit();
 
            nazvy.add(nazov);
        }
    }
        
    public static void main(String[] args) throws IOException {
        Cv2_zadanie x = new Cv2_zadanie();
        x.read();
    }

  
    
    
}
