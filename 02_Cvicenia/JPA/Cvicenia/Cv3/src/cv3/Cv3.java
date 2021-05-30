
package cv3;

import entities.Osoba;
import javax.persistence.*;

public class Cv3 {

    public EntityManagerFactory emf;
    public EntityManager em;

    public Cv3() {
        emf = Persistence.createEntityManagerFactory("Cv3PU");
        em = emf.createEntityManager();
    }
    
    public void add(String meno, Float vaha) {
        em.getTransaction().begin();
        em.persist(new Osoba(meno,vaha));
        em.getTransaction().commit();
    }
    
    public void update() {
        TypedQuery<Osoba> q = em.createNamedQuery("Osoba.findNoVaha", Osoba.class);

        for (Osoba o : q.getResultList()) {
            o.setVaha(80F);
            em.getTransaction().begin();
            em.persist(o);
            em.getTransaction().commit();
    
        }
    }
    
    public static void main(String[] args) {
        Cv3 x = new Cv3();
        x.add("a",1F);
        x.add("b",11F);
        x.add("c",111F);
        x.add("d",null);
        x.add("e",null);
        x.add("f",null);
        
        x.update();
    }
    
}
