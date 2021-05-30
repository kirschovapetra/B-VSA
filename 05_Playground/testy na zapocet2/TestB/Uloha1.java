/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uloha1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Uloha1 {

    public static void load(String csv) throws FileNotFoundException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("uloha1PU");
        EntityManager em = emf.createEntityManager();
        
        String line;
        BufferedReader br = new BufferedReader(new FileReader(csv));
        while ((line = br.readLine()) != null) {
            String s[] = line.split(";");
            if (s.length<2 || s[0].trim().equals("") || s[1].trim().equals("")) 
                continue;
            
            String nazov = s[0].trim();
            Integer mnozstvo = Integer.parseInt(s[1].trim());
            
            Tovar tovar = em.find(Tovar.class,nazov);
            
            if (tovar == null) {
                em.getTransaction().begin();
                em.persist(new Tovar(nazov,mnozstvo));
                em.getTransaction().commit();
            }
            else {
                tovar.setMnozstvo(tovar.getMnozstvo()+mnozstvo);
                em.getTransaction().begin();
                em.persist(tovar);
                em.getTransaction().commit();
            }
        }
        
    
    }

    public static void main(String[] args) throws IOException {
        load("/media/sf_2020_VM_SHARED/testy na zapocet2/TestB/data1.csv");
        load("/media/sf_2020_VM_SHARED/testy na zapocet2/TestB/data2.csv");
        load("/media/sf_2020_VM_SHARED/testy na zapocet2/TestB/data3.csv");
     

    }

    
}
