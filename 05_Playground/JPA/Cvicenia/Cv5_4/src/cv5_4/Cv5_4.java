/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv5_4;

import java.util.*;
import javax.persistence.*;


public class Cv5_4 {


    
    EntityManagerFactory emf;
    EntityManager em;
    
    
    public Cv5_4(){
        emf = Persistence.createEntityManagerFactory("Cv5_4PU");
        em = emf.createEntityManager();
    }
    
    public void remove1stChapter() {
        
        TypedQuery<Dokument> q = em.createQuery("SELECT d FROM Dokument d WHERE d.nazov = 'doc'",Dokument.class);
        Dokument doc = q.getSingleResult();
        
        
        for (Dokument pod: doc.getPodkapitoly()) {
            if (pod.getNazov().equals("pod1")){
                em.getTransaction().begin();
                em.remove(em.find(Dokument.class,pod.getId()));
                em.getTransaction().commit();
                break;
            }
        }
        
        
    }
    public void remove1stChapterBi() {

        TypedQuery<DokumentBi> q = em.createQuery("SELECT d FROM DokumentBi d WHERE d.nazov = 'doc'", DokumentBi.class);
        DokumentBi doc = q.getSingleResult();

        for (DokumentBi pod : doc.getPodkapitoly()) {
            if (pod.getNazov().equals("pod1")) {
                em.getTransaction().begin();
                em.remove(em.find(DokumentBi.class, pod.getId()));
                em.getTransaction().commit();
                break;
            }
        }

    }
    
    public void remove1stChapterBi2() {


        em.getTransaction().begin();
        em.remove(em.find(DokumentBi.class, 6L));
        em.getTransaction().commit();

    }
    
    public void create() {
        Dokument pod11 = new Dokument("pod11", "text pod11");
        Dokument pod12 = new Dokument("pod12", "text pod12");
        
        Dokument pod1 = new Dokument("pod1", "text pod1");
        pod1.setPodkapitoly(new ArrayList<>(Arrays.asList(pod11, pod12)));
        
        Dokument pod2 = new Dokument("pod2", "text pod2");
        Dokument pod3 = new Dokument("pod3", "text pod3");
 
        Dokument doc = new Dokument("doc", "text doc");
        doc.setPodkapitoly(new ArrayList<>(Arrays.asList(pod1, pod2, pod3)));
        
        em.getTransaction().begin();
        em.persist(doc);
        em.getTransaction().commit();
    }
    public void createBi(){
        DokumentBi pod11 = new DokumentBi("pod11", "text pod11");
        DokumentBi pod12 = new DokumentBi("pod12", "text pod12");

        DokumentBi pod1 = new DokumentBi("pod1", "text pod1");
        
        pod11.setHlavny(pod1);
        pod12.setHlavny(pod1);
        
        pod1.setPodkapitoly(new ArrayList<>(Arrays.asList(pod11, pod12)));

        DokumentBi pod2 = new DokumentBi("pod2", "text pod2");
        DokumentBi pod3 = new DokumentBi("pod3", "text pod3");

        DokumentBi doc = new DokumentBi("doc", "text doc");
        
        pod1.setHlavny(doc);
        pod2.setHlavny(doc);
        pod3.setHlavny(doc);
        
        
        doc.setPodkapitoly(new ArrayList<>(Arrays.asList(pod1, pod2, pod3)));

        
        
        em.getTransaction().begin();
        em.persist(doc);
        em.getTransaction().commit();
    }
    
    public static void main(String[] args) {
       Cv5_4 x = new Cv5_4();
       //x.create();
       //x.remove1stChapter();
       x.createBi();
       x.remove1stChapterBi();
    }
 
    
}
