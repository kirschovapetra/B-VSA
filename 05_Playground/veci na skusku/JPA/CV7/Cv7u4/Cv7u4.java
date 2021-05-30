/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv7u4;

import java.util.*;
import javax.persistence.*;


public class Cv7u4 {

    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cv7u4PU");
        EntityManager em = emf.createEntityManager();
        create(em);
        
        findNode(em,"folder1");
        getPath(em,"folder1");
        delNode(em,"parent");
    }

    public static void findNode(EntityManager em,String name){
        TypedQuery<Node> q = em.createQuery("select f from Node f where f.name =:name",Node.class);
        q.setParameter("name", name);
        
        System.out.println(q.getResultList().get(0));

    }
    
    public static void getPath(EntityManager em, String name) {
        List<String> path = new ArrayList<>();
        String toPrint = "";
        
        TypedQuery<Node> q = em.createQuery("select f from Node f where f.name =:name", Node.class);
        q.setParameter("name", name);
        if (!q.getResultList().isEmpty()) {
            Node current = q.getResultList().get(0);
            path.add(current.getName());
            while (current.getParentFolder() != null){
                current = current.getParentFolder();
                path.add(current.getName());
            }
            
           
            for (int i=path.size()-1; i>=0; i--) {
                toPrint+="/"+path.get(i);
            }
            
        }
        
        System.out.println(toPrint);
    }
    
    public static void delNode(EntityManager em, String name) {
        TypedQuery<Node> q = em.createQuery("select f from Node f where f.name =:name", Node.class);
        q.setParameter("name", name);
        if (!q.getResultList().isEmpty()) {
            em.getTransaction().begin();
            em.remove(q.getResultList().get(0));
            em.getTransaction().commit();
        }
        

    }
    
    public static void create(EntityManager em) {
        Folder parent = new Folder("parent", 0);
        
        Folder f1 = new Folder("folder1", 0);
        Folder f2 = new Folder("folder2", 1);
        Folder f3 = new Folder("folder3", 0);
        Folder f4 = new Folder("folder4", 1);
        
        f1.setParentFolder(parent);
        f2.setParentFolder(parent);
        f3.setParentFolder(parent);
        f4.setParentFolder(parent);
        
        
        parent.getChildNodes().add(f1);
        parent.getChildNodes().add(f2);
        parent.getChildNodes().add(f3);
        parent.getChildNodes().add(f4);
        
        em.getTransaction().begin();

        em.persist(f1);
        em.persist(f2);
        em.persist(f3);
        em.persist(f4);
        em.persist(parent);
 
        em.persist(new DataFile("file1", 1,"serus"));
        em.persist(new DataFile("file2", 1, "nazdar"));
        em.persist(new DataFile("file3", 1, "cau"));
        em.getTransaction().commit();
        
        em.getEntityManagerFactory().getCache().evictAll();
    }
    
}
