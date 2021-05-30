/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv01;

import javax.persistence.*;
public class Cv01 {

    //nájde v DB knihu so zadaným menom a vráti jej cenu. Ak neexistuje taká kniha vráti -1 a vypíše spávu "Knihu nemáme"
    public static double cenaKnihy(String meno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cv01PU");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Kniha> q = (TypedQuery<Kniha>) em.createNativeQuery("SELECT * FROM Kniha WHERE nazov = ?",Kniha.class);
        q.setParameter(1,meno);
        if (q.getResultList().isEmpty()){
            System.out.println("Knihu nemáme");
            return -1;
        }
        
        return q.getSingleResult().getCena();
    }
    
    //pridá do DB knihu s daným menom a cenou. Ak kniha s daným menom v DB už existuje, vráti false.
    public static boolean pridajKnihu(String meno, double cena) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cv01PU");
        EntityManager em = emf.createEntityManager();
        if (cenaKnihy(meno) != -1.0)
            return false;
        
        em.getTransaction().begin();
        em.createNativeQuery("insert into Kniha(id,nazov,cena) values(5,?,?)")
            .setParameter(1,meno)
            .setParameter(2,cena)
            .executeUpdate();
        em.getTransaction().commit();

        return true;
    }
    
    //nájde v DB knihu so zadaným menom a zníži jej cenu o o 20% (v databáze). Ak neexistuje taká kniha neurobí nič
    public static void zlava(String meno){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cv01PU");
        EntityManager em = emf.createEntityManager();
      
        double cena = cenaKnihy(meno);
        if (cena == -1.0)
            return;
        
        cena *= 0.8;
        
        em.getTransaction().begin();
        em.createNativeQuery("update Kniha set cena = ? where nazov = ?")
            .setParameter(1,cena)
            .setParameter(2,meno)
            .executeUpdate();
        em.getTransaction().commit();
        
    }
    
    public static void create(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cv01PU");
        EntityManager em = emf.createEntityManager();
        
        Kniha k1 = new Kniha("k1",1.1);
        Kniha k2 = new Kniha("k2",1.2);
        Kniha k3 = new Kniha("k3",1.3);
        
        em.getTransaction().begin();
        em.persist(k1);
        em.persist(k2);
        em.persist(k3);
        em.getTransaction().commit();
    }
    
    public static void main(String[] args) {
        create();
        System.out.println(pridajKnihu("k4",1.4));
        zlava("k4");
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cv01PU");
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
