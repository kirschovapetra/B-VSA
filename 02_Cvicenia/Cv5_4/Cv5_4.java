
package cv5_4;

import java.util.*;
import javax.persistence.*;

public class Cv5_4 {

   
    private static void create(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cv5_4PU");
        EntityManager em = emf.createEntityManager();

        Dokument doc = new Dokument("main","stuff");
        Dokument pod1 = new Dokument("pod1","stuff pod1");
        Dokument pod2 = new Dokument("pod2","stuff pod2");
        Dokument pod3 = new Dokument("pod3","stuff pod3");
        
        
        Dokument pod11 = new Dokument("pod11", "stuff pod11");
        Dokument pod12 = new Dokument("pod12", "stuff pod12");
        Dokument pod13 = new Dokument("pod13", "stuff pod13");
        
        pod1.setPodkapitoly(new ArrayList<>(Arrays.asList(pod11,pod12,pod13)));
        doc.setPodkapitoly(new ArrayList<>(Arrays.asList(pod1,pod2,pod3)));
        
        em.getTransaction().begin();
        em.persist(doc);
        em.getTransaction().commit();
    
    }
    
    public static void removeChapter(String name){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cv5_4PU");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Dokument> q = em.createQuery("select o from Dokument o where o.nazov=:nazov", Dokument.class);
        q.setParameter("nazov", name);
        
        for (Dokument d: q.getResultList()) {
            if (d.getNazov().equals(name)){
                em.getTransaction().begin();
                em.remove(d);
                em.getTransaction().commit();
                
            }
        }
    }
    
    public static void main(String[] args) {
        create();
        removeChapter("pod2");
    }
    
}
