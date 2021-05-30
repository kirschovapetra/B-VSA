/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zadanie;

import javax.persistence.*;

/**
 *
 * @author vsa
 */
public class Zadanie {

    //ktorá, zistí v databáze počet prednášok s daným názvom .
    static int pocet(String nazov) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("zadaniePU");
        EntityManager em = emf.createEntityManager();

        TypedQuery<Prednaska> q = (TypedQuery<Prednaska>) em.createNativeQuery("SELECT * FROM Prednaska WHERE nazov=?", Prednaska.class);
        q.setParameter(1, nazov);

        return q.getResultList().size();
    }

    //vyhľadá v databáze akcie s názvom akcia a zistí či niektorá má prednášku, ktorej autorom
    //je autor Ak akcia s takou prednáškou existuje vráti true inak vráti false
    static boolean maprednasku(String akcia, String autor) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("zadaniePU");
        EntityManager em = emf.createEntityManager();

        TypedQuery<Akcia> q = (TypedQuery<Akcia>) em.createNativeQuery("SELECT * FROM Akcia WHERE nazov=?", Akcia.class);
        q.setParameter(1, akcia);

        for (Akcia a : q.getResultList()) {

            for (Prednaska p : a.getPrednasky()) {
                if (p.getAutori().contains(autor)) {
                    System.out.println("akcia " + akcia + " ma prednasku " + p.getNazov() + " ktorej autorom je " + autor);
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(pocet("prednaska1"));
        System.out.println(maprednasku("akcia3", "a3"));
    }
    
}
