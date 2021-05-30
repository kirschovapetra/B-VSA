package cv4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import javax.persistence.*;

public class Cv4 {

    
    /*
    
    Implementujte funkciu, ktorá dostane ako argument objekt triedy Autor a vráti 
    zoznam všetkých obchodov (objektov triedy Obchod), ktoré ponúkajú knihu od 
    daného autora (t.j. je jedným z jej autorov). Funkcia predpokladá, že všetky 
    údaje o obchodoch knihách a autoroch sú už v pamäti a teda, nepristupuje do 
    databázy. 
    
    */
    public static List<Obchod> getObchody(Autor autor) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cv4PU");
        EntityManager em = emf.createEntityManager();
        
        Set<Obchod> obchody = new HashSet<>();
        
        for (Kniha kniha: autor.getKnihy()) {
           
            for (Polozka polozka : kniha.getPolozky()) {
                
                obchody.add(polozka.getObchod());
            }
        }
        
        List<Obchod> obchodyList = new ArrayList<>();
        obchodyList.addAll(obchody);
        
        return obchodyList;
    }
    
    /*
    Implementujte funkciu, ktorá načíta údaje o obchodoch, knihách a ich autoroch z databázy. 
    */
    
    
    public static void printStuff(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cv4PU");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Obchod> q = em.createQuery("select o from Obchod o", Obchod.class);
        
        
        for (Obchod obchod: q.getResultList()){
            System.out.println("obchod: "+obchod.getNazov());
            
            for (Polozka polozka: obchod.getPolozky()) {
                Kniha kniha = polozka.getKniha();
                System.out.print("kniha: " + kniha.getNazov());
                System.out.print(" autori: ");
                for (Autor autor: kniha.getAutori()) {
                    System.out.print(autor.getMeno()+", ");
                }
            }
            System.out.println("\n");
        }
        
    }
    
    
    public void importCSV(String path) throws FileNotFoundException, IOException{
        // /media/sf_2020_VM_SHARED/veci na skusku/Cv4/data3.csv
       
        Set<String> vsetciAutori = new HashSet<>();
        String line;
        BufferedReader br = new BufferedReader(new FileReader(path));
        while ((line = br.readLine()) != null) {
            String s[] = line.split(";");
            if (s.length < 6)
                continue;
            
            String isbn = s[0].trim();
            String nazovVydavatela = s[1].trim();
            String nazovKnihy = s[2].trim();
            String menaAutorov[] = s[3].trim().split(",");
            String nazovObchodu = s[4].trim();
            double cena = Double.parseDouble(s[5].trim());
            
            Obchod obchod = new Obchod(nazovObchodu);
            Vydavatel vydavatel = new Vydavatel(nazovVydavatela);
            
            List<Autor> autoriKnihy = new ArrayList<>();
            for (String meno: menaAutorov) { 
                if (!vsetciAutori.contains(meno))
                    autoriKnihy.add(new Autor(meno));
                
                vsetciAutori.add(meno);
            }
            
            //kniha ---> vydavatel
            Kniha kniha = new Kniha(isbn, nazovKnihy, vydavatel);
           
            //kniha <---> autor
            kniha.getAutori().addAll(autoriKnihy);
            for (Autor autorKnihy: kniha.getAutori()) {
                autorKnihy.getKnihy().add(kniha);
            }
            
            //polozka <---> obchod
            Polozka polozka = new Polozka(cena, kniha, obchod);
            obchod.getPolozky().add(polozka);
            
            //polozka <---> kniha
            kniha.getPolozky().add(polozka);
            
        }
    }
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cv4PU");
        EntityManager em = emf.createEntityManager();
    }

  
    
}
