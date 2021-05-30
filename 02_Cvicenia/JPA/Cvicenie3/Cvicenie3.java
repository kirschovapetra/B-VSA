/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvicenie3;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


public class Cvicenie3 {
	
    /*  1.  vytvorit tabulku "Osoba" podla zadania (stlpce ID, MENO, VAHA)
        2.  vytvorit entitnu triedu cez "entity class from database" - 
            tam si vybrat ktoru tabulku pouzit (Osoba)
            
            Pozn.: V persistence.xml nechavam nastavene table genaration strategy = Create, 
            lebo v tomto cviku len hladam data v tabulke, nic nepridavam tak nechcem, 
            aby sa mi po kazdom spusteni vymazala
    
        3.  v triede sa automaticky vygeneruju NamedQuery (Osoba.findAll, Osoba.findById atd)
        4.  v triede "Cvicenie3" vygenerovat persist() cez insert code -> use entity manager, 
            okopirovat 2 riadky:
    
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cvicenie3PU");
            EntityManager em = emf.createEntityManager();
    */
	
    //entity manager = atributy triedy, aby sa dali pouzit v hocijakej funkcii
    public EntityManagerFactory emf;
    public EntityManager em;

    //konstruktor - inicializacia entity managera
    public Cvicenie3() {
        this.emf = Persistence.createEntityManagerFactory("Cvicenie3PU");
        this.em = emf.createEntityManager();
        
    }
    
    
    
    
/********************************** ULOHA 1 ***********************************/
    


    /*ULOHA 1 a) - implementujte a otestujete metódu, ktorá pomocou namedQuery 
    findAll vyselektuje všetky osoby z DB*/
    public void findAll(){
        /*  V entitnej triede "Osoba" je Named Query:
                        @NamedQuery(name = "Osoba.findAll", query = "SELECT o FROM Osoba o")
                Ta sa potom vo funkcii zavola cez em.createNamedQuery() kde treba dat nazov tej named query a nazov entitnej triedy, v ktorej sa nachadza
        */
        //Preto TypedQuery<Osoba>, lebo chcem aby mi to vratilo objekty typu Osoba, potom nemusim nic pretypovavat
        TypedQuery<Osoba> q = this.em.createNamedQuery("Osoba.findAll", Osoba.class);
    
	//q.getResultList() mi vrati vsetky vysledky, ktore nasla ta named query (cize zoznam objektov typu Osoba)
        List<Osoba> lst = q.getResultList();
	System.out.println("Vsetky osoby:");
        
	//prechadzam zoznam a kazdu osobu vypisem
	for (Osoba o: lst){
            System.out.println(o);
        }
        System.out.println("\n");        
 
    }
	
    /*ULOHA 1 b) implementujte a otestujete metódu, ktorá pomocou namedQuery 
    findByMeno vyhľadá osobu podľa mena*/
    public void findByMeno(String m){
	/*  V entitnej triede "Osoba" je Named Query:
		@NamedQuery(name = "Osoba.findByMeno", query = "SELECT o FROM Osoba o WHERE o.meno = :meno")
            Zavola sa tak isto ako v a)
	*/
		
        TypedQuery<Osoba> q = this.em.createNamedQuery("Osoba.findByMeno", Osoba.class);
        /*  rozdiel oproti a) treba pridat parameter, lebo v named query je nieco taketo:
            "...WHERE o.meno = :meno"
            To ":meno" predstavuje parameter, ktory si tam treba pridat, cize napriklad chceme najst taku osobu, ktorej meno je "Petra",
            tak parameter ":meno" bude Petra atd. To spravime cez "q.setParameter("meno", m)" - cize nastavujeme parameter "meno" a jeho hodnota
            bude m, co je premenna, ktora vstupuje do nasej funkcie findByMeno(-----> String m <----){...}
	*/
        q.setParameter("meno", m);

        //zase to iste - ziskame zoznam vysledkov a vypiseme
        List<Osoba> lst = q.getResultList();

        System.out.println("Osoby s menom "+ m +":");
        for (Osoba o: lst){
            System.out.println(o);
        }
        System.out.println("\n");
    }
    
    /*ULOHA 1 c) implementujte a otestujete metódu, ktorá pomocou namedQuery 
    vyhľadá všetky osoby z DB ktoré nemajú zadanú váhu, nastaví ich váhu 
    na 80.0 a aktualizuje DB. Návod: Pridajte do triedy Osoba definíciu novej 
    namedQuery alebo modifikujte niektorú z existujúcich.*/
    
    //(toto mi este nefunguje :D)
    public void setVaha(){
      
        TypedQuery<Osoba> q = this.em.createNamedQuery("Osoba.setVaha", Osoba.class);
       
        //List<Osoba> lst = q.getResultList();
    }
      
    //main k ulohe 1 a) b)
    public static void main_1_2(String[] args) {
        Cvicenie3 cv3 = new Cvicenie3();    //vytvori sa instancia triedy "Cvicenie3", funkcie sa potom zavolaju napr cv3.funkcia()
        cv3.findAll();                      //a)
        cv3.findByMeno("osoba2");           //b)
	//setVaha();                        //c) nefunguje
    }
    
    
/********************************** ULOHA 2 ***********************************/
/* ULOHA 2 a) persit a merge

    1.  Vytvorte inštanciu triedy Osoba - zadajte id, meno a váhu
    2.  Vytvorte druhú inštanciu triedy Osoba - zadajte rovnaké id 
        ale odlišné meno alebo váhu
    3.  pre prvú inštanciu zavolajte em.persist
    4.  pre druhú inštanciu zavolajte em.merge
    5.  ukončite transakciu

    Otázka: čo spraví program po spustení? 
    Odpoved: v tabulke pri id=123L budu nastavene data mergnuteho objektu, 
            nie toho, na ktory bolo zavolane persist(), 
	    teda nebude tam meno "Osoba" ale "MergovanaOsoba" 
            a vaha nebude 80.50 ale 70.50
*/
    public static void main_persist_merge(String[] args) {
        Cvicenie3 cv3 = new Cvicenie3();
        
	Osoba o1 = new Osoba(123L,"Osoba",80.50);               //1.
        Osoba o2 = new Osoba(123L,"MergovanaOsoba",70.50); 	//2.
        
        cv3.em.getTransaction().begin();
        cv3.em.persist(o1); 					//3.
        cv3.em.merge(o2);   					//4.
        cv3.em.getTransaction().commit(); 			//5.
    }

/* ULOHA 2 b) clear

    1.  Vytvorte inštanciu triedy Osoba - zadajte id a meno
    2.  Vytvorte druhú inštanciu triedy Osoba - zadajte rovnaké id 
        ale odlišné meno
    3.  pre prvú inštanciu zavolajte em.persist
    4.  zavolajte em.clear
    5.  pre druhú inštanciu zavolajte em.persist
    6.  ukončite transakciu

    Otázka: čo spraví program po spustení? 
    Odpoved: clear ako keby odstrani objekt z entity managera 
            (nebude managovany entity managerom) a po zavolani 
            persist na o2 sa o2 zapise do tabulky 
*/
    public static void main_clear(String[] args) {
        Cvicenie3 cv3 = new Cvicenie3();
        Osoba o1 = new Osoba(11L,"ClearTest",44.55);		//1.
        Osoba o2 = new Osoba(11L,"ClearTest2",44.55); 		//2.
        
        cv3.em.getTransaction().begin();
        cv3.em.persist(o1); 					//3.
        cv3.em.clear();						//4.
        cv3.em.persist(o2); 					//5.
        cv3.em.getTransaction().commit();
    }
    
/* ULOHA 2 c) detach

    1.  Vytvorte inštanciu triedy Osoba - zadajte id a meno
    2.  Vytvorte druhú inštanciu triedy Osoba - zadajte rovnaké id 
        ale odlišné meno
    3.  pre obe inštancie zavolajte em.persist
    4.  pre druhú inštanciu zavolajte em.detach
    5.  ukončite transakciu

    Otázka: čo spraví program po spustení?
    Odpoved: detach "odpojí" o2 z entity managera a ostane iba o1, ktora sa 
            zapise do tabulky. Keby sa detach nezavolalo, tak vyhodi chybu, 
            lebo sa nemozu do tabulky zapisat 2 zaznamy s rovnakym ID
*/
    public static void main_detach(String[] args) {
        Cvicenie3 cv3 = new Cvicenie3();
        Osoba o1 = new Osoba(250L,"DetachTest",123.4);		//1.
        Osoba o2 = new Osoba(250L,"DetachTest2",567.8);		//2.
        
        cv3.em.getTransaction().begin();
        cv3.em.persist(o1);					//3.
        cv3.em.persist(o2);					//4.
        cv3.em.detach(o2);					//5.
        cv3.em.getTransaction().commit();
    }
    
/* ULOHA 2 d) find a detach

    1. pomocou em.find načítajte z DB inštanciu osoby podľa zadaného klúča.
    2. zavolajte em.detach na načítanú inšanciu osoby.
    3. zopakujte volanie em.find s tým istým klúčom.
    4. Overte si či tieto dve volania vrátili ten istý objekt alebo dva rôzne objekty. 
	
    Odpoved: vypise sa "false", lebo uz to nie su rovnake objekty - o1 uz nie je 
            spravovany entity managerom. Keby sa detach() nezavola, tak vypise true - 
            o1 a o2 by ukazovali na ten isty objekt

*/
    public static void main(String[] args) {
        Cvicenie3 cv3 = new Cvicenie3();				
        Osoba o1 = cv3.em.find(Osoba.class,123L);		//1.  
	cv3.em.detach(o1);                                      //2.
        Osoba o2 = cv3.em.find(Osoba.class,123L);		//3.
        System.out.println("Rovnaju sa: "+(o1==o2));		//4.
    }
    
}
