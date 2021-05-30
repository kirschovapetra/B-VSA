package cv4u4;

import java.io.*;
import java.util.*;
import javax.persistence.*;



public class Cv4u4 {

    public static Autor createAutor(EntityManager em,String meno){

        
        TypedQuery<Autor> q = em.createQuery("select a from Autor a where a.meno=:meno", Autor.class);
        q.setParameter("meno", meno);
        
        return q.getResultList().isEmpty()? new Autor(meno) : q.getResultList().get(0);
        
    }
  
    public static Kniha createKniha(EntityManager em,String isbn, String nazov) {

        
        TypedQuery<Kniha> q = em.createQuery("select k from Kniha k where k.nazov =:nazov", Kniha.class);
        q.setParameter("nazov", nazov);

        return q.getResultList().isEmpty() ? new Kniha(isbn, nazov): q.getResultList().get(0);

    }
  
    public static Obchod createObchod(EntityManager em,String nazov) {
      

        TypedQuery<Obchod> q = em.createQuery("select o from Obchod o where o.nazov =:nazov", Obchod.class);
        q.setParameter("nazov", nazov);

        return q.getResultList().isEmpty() ? new Obchod(nazov) : q.getResultList().get(0);
    }
    
    public static Vydavatel createVydavatel(EntityManager em,String nazov) {
      

        TypedQuery<Vydavatel> q = em.createQuery("select v from Vydavatel v where v.nazov =:nazov", Vydavatel.class);
        q.setParameter("nazov", nazov);

        return q.getResultList().isEmpty() ? new Vydavatel(nazov) : q.getResultList().get(0);
    }

    /*
    Implementujte funkciu, ktorá dostane ako argument objekt triedy Autor a vráti zoznam všetkých obchodov 
    (objektov triedy Obchod), ktoré ponúkajú knihu od daného autora (t.j. je jedným z jej autorov). 
    Funkcia predpokladá, že všetky údaje o obchodoch knihách a autoroch sú už v pamäti a teda, nepristupuje do databázy.
*/
    public static List<Obchod> getObchody(Autor autor){
        List<Obchod> obchody = new ArrayList<>();
        Set<Kniha> knihyAutora = autor.getKnihy();
        for (Kniha k: knihyAutora) {        
            List<Polozka> polozky = k.getPolozky();
            for (Polozka p: polozky) {
                obchody.add(p.getObchod());
            }     
        }
        return obchody;
    }
    
    /*
Implementujte funkciu, ktorá načíta údaje o obchodoch, knihách a ich autoroch z databázy.
Implementujte program, ktorý otestuje funkčnosť oboch funkcií.
    */
    
    public static void printInfo(EntityManager em){
      
        TypedQuery<Obchod> q = em.createQuery("select o from Obchod o", Obchod.class);
        
        for (Obchod o: q.getResultList()) {
           
            System.out.println("obchod: " + o.getNazov());
            
            for (Polozka polozka : o.getPolozky()) {
                Kniha kniha = polozka.getKniha();
                System.out.print("kniha: " + kniha.getNazov());
                System.out.print(" autori: ");
                for (Autor autor : kniha.getAutori()) {
                    System.out.print(autor.getMeno() + ", ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
    
    public static void create(String path) throws FileNotFoundException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cv4u4PU");
        EntityManager em = emf.createEntityManager();
        
        String line;
        BufferedReader br = new BufferedReader(new FileReader(path));
        while ((line = br.readLine()) != null) {
            String s[] = line.split(";");
            if (s.length < 6) {
                continue;
            }
            
            String isbn = s[0].trim();
            String nazovVydavatela = s[1].trim();
            String nazovKnihy = s[2].trim();
            String menoAutora = s[3].trim();
            String nazovObchodu = s[4].trim();
            double cena = Double.parseDouble(s[5].trim());
            
            Autor autor = createAutor(em,menoAutora);
            Vydavatel vydavatel = createVydavatel(em,nazovVydavatela);
            Kniha kniha = createKniha(em,isbn, nazovKnihy);
            Obchod obchod = createObchod(em,nazovObchodu);
            
            // autor <---------> kniha
           
            boolean nachadzaSa = false;
            for (Autor a: kniha.getAutori()) {
                if (a.getMeno().equals(autor.getMeno())){
                    nachadzaSa = true;
                }
            }
            
            if (!nachadzaSa) {
                kniha.getAutori().add(autor);
                autor.getKnihy().add(kniha);
            }
            
            // vydavatel <---------> kniha
            kniha.setVydavatel(vydavatel);
            
            // polozka <---------> kniha, polozka <---------> obchod
            Polozka polozka = new Polozka(cena,kniha, obchod);
            obchod.getPolozky().add(polozka);
            kniha.getPolozky().add(polozka);
            
            em.getTransaction().begin();
            em.persist(polozka);
            em.getTransaction().commit();
          
           
            
        }
        em.getEntityManagerFactory().getCache().evictAll();
        printInfo(em);
        Autor a = em.find(Autor.class,37L);
        System.out.println(getObchody(a));
    }
   
    public static void main(String[] args) {
        try {
            
            String path = "/media/sf_2020_VM_SHARED/veci na skusku/JPA/Cv4u4/data3.csv";
            create(path);
            
            
        } catch (IOException ex) {
        }  
    }

  
    
}
