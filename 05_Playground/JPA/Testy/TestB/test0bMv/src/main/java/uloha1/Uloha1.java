
package uloha1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Uloha1 {

    public static void load(String csv) throws FileNotFoundException, IOException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("uloha1PU");
        EntityManager em = emf.createEntityManager();
        
        String line;
        BufferedReader br = new BufferedReader(new FileReader(csv));
        while ((line = br.readLine()) != null) {
           
            String s[] = line.split(";");
            
            if (s.length < 2 || s[0].trim().equals("") || s[1].trim().equals(""))
                continue;
            
            String nazov = s[0].trim();
            Integer mnozstvo = Integer.parseInt(s[1].trim());
            
            Tovar t = em.find(Tovar.class,nazov);
          
            if (t == null) {
                //vytvori novy
                Tovar newT = new Tovar(nazov,mnozstvo);
                em.getTransaction().begin();
                em.persist(newT);
                em.getTransaction().commit();
         
                
            }
            else {
                //pripocita mnozstvo
//               System.out.println(t.getMnozstvo());
//               System.out.println(mnozstvo);
//               System.out.println(t.getMnozstvo() + mnozstvo);
               
               
                t.setMnozstvo(t.getMnozstvo() + mnozstvo);
                em.getTransaction().begin();
                em.persist(t);
                em.getTransaction().commit();
                
            }
      
            
            
        }
        

    }
    
    
    public static void main(String[] args) throws IOException {
        load("/media/sf_2020_VM_SHARED/JPA stuff/TestB/data1.csv");
    }


    
}
