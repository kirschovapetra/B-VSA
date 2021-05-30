/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prednaska2_2;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author vsa
 */
public class Prednaska2_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prednaska2_2PU");
        EntityManager em = emf.createEntityManager();
        
        Query q = em.createNativeQuery("select * from KNIHA",Kniha.class);
        List<Kniha> knihy = q.getResultList();
        
        em.getTransaction().begin();
        
        for (Kniha k: knihy){
            System.out.println(k);
            k.setCena(k.getCena()*0.9);
            //System.out.println(k);
        }
        
        em.getTransaction().commit();
    }
    
    
}
