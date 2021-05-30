
package projekt;

import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Projekt {
    
   public static boolean aktualizujKnihu(String isbn, String nazov, Double cena){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projektPU");
        EntityManager em = emf.createEntityManager();
        Kniha k = em.find(Kniha.class,isbn);
        Boolean zapisaloSa = false;
        if (k == null) {
            em.getTransaction().begin();
            em.persist(new Kniha(isbn,nazov,cena));
            em.getTransaction().commit();
            return true;
        }
        else {
            if (nazov != null && k.getNazov() == null) {
                k.setNazov(nazov);
                zapisaloSa = true;
            }
            else if (nazov != null && k.getNazov() != null) {
                if (!nazov.equals(k.getNazov()))
                    return false;
            }
            
            if (cena != null) {
                k.setCena(cena);
                zapisaloSa = true;
            }
            em.getTransaction().begin();
            em.persist(k);
            em.getTransaction().commit();
            return true;
        }
       
    }
    
    
 
    public void aktualizujCennik(Map<String, Double> cennik){
        for (String isbn: cennik.keySet()) {
            aktualizujKnihu(isbn, null, cennik.get(isbn));
        }
    
    }
    
    
    public static void main(String[] args) {
        System.out.println(new Projekt().aktualizujKnihu("isbn4",null,null));
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projektPU");
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
