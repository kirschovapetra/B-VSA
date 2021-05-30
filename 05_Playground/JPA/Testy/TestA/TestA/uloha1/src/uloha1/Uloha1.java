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
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author vsa
 */
public class Uloha1 {

      public static void load(String csv) throws FileNotFoundException, IOException {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("uloha1PU");
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

            if (s.length < 2 || s[0].trim().equals("") || s[1].trim().equals("")) {
                continue;
            }

            String nazov = s[0].trim();
            double cena = Double.parseDouble(s[1].trim());

            if (nazvy.contains(nazov)) {
                continue;
            }

            em.getTransaction().begin();
            em.persist(new Tovar(nazov, cena));
            em.getTransaction().commit();

            nazvy.add(nazov);
        }

    }

    public static void main(String[] args) throws IOException {
        load("//media/sf_2020_VM_SHARED/JPA stuff/TestA/data.csv");

    }
    
}
