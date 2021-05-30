
package cv4_uloha4;

import entities.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import javax.persistence.*;


public class Cv4_uloha4 {


    public EntityManagerFactory emf;
    public EntityManager em;

    public Cv4_uloha4() {
        emf = Persistence.createEntityManagerFactory("Cv4_uloha4PU");
        em = emf.createEntityManager();
    }
    
    private void load(String line,String isbn,String vydavatelstvo,String nazov,String autor,String obchod,Double cena) {
        String s[] = line.split(";");

        if (s.length < 6) {
            return;
        }

        isbn = s[0].trim();
        vydavatelstvo = s[1].trim();
        nazov = s[2].trim();
        autor = s[3].trim();
        obchod = s[4].trim();
        cena = Double.parseDouble(s[5].trim());
    }
    
    private void add(String csv) throws IOException{
        
        
        String line;
        String isbn = "", vydavatelstvo = "", nazov = "", autor = "", obchod = "";
        Double cena = null;
        
        BufferedReader br = new BufferedReader(new FileReader(csv));
        while ((line = br.readLine()) != null) {

            load(line,isbn,vydavatelstvo,nazov,autor,obchod,cena);
         
            //autori,knihy,obchody,      
            Autor a = new Autor(autor);
            Kniha k = new Kniha(isbn,nazov);
            Vydavatel vyd = new Vydavatel(vydavatelstvo);
            Obchod o = new Obchod(obchod);
            
            
            em.getTransaction().begin();
            
          //  em.persist();
            em.getTransaction().commit();
        }
    }
    
    public static void main(String[] args) throws IOException {
        new Cv4_uloha4().add("/media/sf_2020_VM_SHARED/JPA stuff/Cv4_uloha4/data3.csv");
        
    }
    
}
