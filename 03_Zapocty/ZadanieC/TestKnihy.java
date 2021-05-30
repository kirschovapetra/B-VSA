
package testknihy;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class TestKnihy {

   public boolean aktualizujKnihu(String isbn, String nazov, Double cena){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testKnihyPU");
        EntityManager em = emf.createEntityManager();

        Kniha k = em.find(Kniha.class, isbn);
        boolean nastaliZmeny = false;

        if (k == null) {
            Kniha newK = new Kniha(isbn, nazov,cena);
            em.getTransaction().begin();
            em.persist(newK);
            em.getTransaction().commit();
            return true;
        }

        
        if (nazov != null && k.getNazov() == null) {
            k.setNazov(nazov);
            nastaliZmeny = true;
        }
        else if (nazov != null && k.getNazov() != null) {
            if (!nazov.equals(k.getNazov())) {
                return false;
            }
        }    
        
        if (cena != null) {
            k.setCena(cena);   
            nastaliZmeny = true;
        }      
        
        if (nastaliZmeny){
            em.getTransaction().begin();
            em.persist(k);
            em.getTransaction().commit();
        }
        return nastaliZmeny;
    }
    
    public void aktualizujCennik(Map<String, Double> cennik){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testKnihyPU");
        EntityManager em = emf.createEntityManager();

     

        
        Set<String> keys = cennik.keySet();
        
        for (String isbn: keys) {
            Double cena = cennik.get(isbn);
            
            Kniha k = em.find(Kniha.class, isbn);
    
            System.out.println(aktualizujKnihu(isbn,null,cena));
            
        }
        
    }
    
    
    
    public static void main(String[] args) {

        TestKnihy t = new TestKnihy();
        System.out.println(t.aktualizujKnihu("123","kniha1",10.50));
        System.out.println(t.aktualizujKnihu("124","kniha2",101.50));
        System.out.println(t.aktualizujKnihu("123",null,null));
        System.out.println(t.aktualizujKnihu("124","knihaNova",null));
   
        System.out.println("\n aktualizujCennik");
        
        Map<String,Double> cennik = new HashMap<>();
        cennik. put("123", 5.5);
        cennik. put("isbn2", 6.5);
        cennik. put("isbn3", 7.5);
        t.aktualizujCennik(cennik);
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testKnihyPU");
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
