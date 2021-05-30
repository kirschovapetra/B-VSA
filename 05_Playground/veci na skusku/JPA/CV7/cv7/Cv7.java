/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv7;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author vsa
 */
public class Cv7 {


    public static void jpqlAllElements() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cv7PU");
        EntityManager em = emf.createEntityManager();
        System.out.println("\n-- All Gui elements JPQL --------------------------");

        Query q3 = em.createQuery("select e from GuiElement e"); // JPQL"
        List<GuiElement> textelementlist = q3.getResultList();
        for (GuiElement e : textelementlist) {
            System.out.println(e);
        }

        em.close();
        emf.close();
    }
   
    public static void create() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cv7PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        GuiElement ge = new GuiElement();
        ge.setName("A gui element");

        TextElement te = new TextElement();
        te.setName("A text element");
        te.setText("hello");
        te.setFontFamily("Times New Roman");
        te.setFontSize(16);

        RectElement re = new RectElement();
        re.setName("A rectangle element");
        re.setHight(100);
        re.setWidth(200);

        em.persist(ge);
        em.persist(te);
        em.persist(re);

        tx.commit();
        em.getEntityManagerFactory().getCache().evictAll();
    }
    public static void main(String[] args) {
        create();
        jpqlAllElements();
    }

   
}
