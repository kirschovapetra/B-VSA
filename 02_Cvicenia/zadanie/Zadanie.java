/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zadanie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public class Zadanie {
    
    public static void load(String csv) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("zadaniePU");
        EntityManager em = emf.createEntityManager();

        String line;
        Set<String> nazvy = new HashSet<>();
        BufferedReader br = new BufferedReader(new FileReader(csv));
        while ((line = br.readLine()) != null) {

            String s[] = line.split(";");
            if (s.length < 3 || s[0].trim().equals("") || s[1].trim().equals("") || s[2].trim().equals("")) {
                System.out.println("kratky riadok");
                return;
            }
            String nazov = s[0].trim();
            String autor = s[1].trim();
            Integer pocet = Integer.parseInt(s[2].trim());
            if (!nazvy.contains(nazov)){
                em.getTransaction().begin();
                em.persist(new Kniha(nazov,autor,pocet));
                em.getTransaction().commit();           
            }
            nazvy.add(nazov);          
        }
        
    }
    public static void main(String[] args) throws IOException {
        load("/media/sf_2020_VM_SHARED/cvika/zadanie/data1.csv");
        
    }
}
