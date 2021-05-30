package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
public class Kniha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nazov; 
    private String isbn;
    
    public Kniha(){}
    
    public Kniha(String nazov,String isbn) {
        this.nazov = nazov;
        this.isbn = isbn;
    }
    
    
    /*
    - kniha moze mat viacero autorov, autor moze napisat viacero knih
    - kniha obsahuje zoznam autorov (jednosmerna asociacia, v autorovi sa 
        nenachadza zoznam knih)
    - vdaka "CascadeType.PERSIST" nam netreba ukladat do tabulky kazdeho autora
        zvlast ale spravi to automaticky. Ked dame em.persist(kniha), tak sa do tabulky
        AUTOR zapisu vsetci autori, ktorych ma kniha v zozname "autori". Keby tam 
        "CascadeType.PERSIST" nie je, tak bz sa musel najskor cez persist pridat 
        do tabulky kazdy autor a az potom zavolat persist na knihu
    - automaticky sa vytvori spojovacia tabulka KNIHA_AUTOR, ktora bude obsahovat
        cudzie kluce: kniha_id a autor_id
    - nazov spojovacej tabulky sa da zmenit cez @JoinTable() napr
        @JoinTable(
            name="CUST_PHONE",
            joinColumns=
                @JoinColumn(name="CUST_ID", referencedColumnName="ID"),
            inverseJoinColumns=
                @JoinColumn(name="PHONE_ID", referencedColumnName="ID")
        )
        (toto JoinTable som skopirovala :D https://docs.oracle.com/javaee/7/api/javax/persistence/JoinTable.html)
    */ 
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Autor> autori;
    
    /*
    - kniha moze byt vydana iba jednym vydavatelstvom, vydavatelstvo moze vydat viacero knih
    - kniha obsahuje vydavatelstvo (nie zoznam, iba jedno, lebo je to many to one)
    - vdaka "CascadeType.PERSIST" nam tiez netreba ukladat aj vydavatelstvo, staci knihu 
      a vydavatelstvo sa prida automaticky
    - do tabuky KNIHA sa prida novy stlpec s cudzim klucom vydavatel_id
    - nazov tohoto stlpca vieme zmenit cez @JoinColumn(name = "nazov_stlpca")
    */  
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Vydavatelstvo vydavatel;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String inbn) {
        this.isbn = isbn;
    }

    public List getAutori() {
        return autori;
    }

    public void setAutori(List autori) {
        this.autori = autori;
    }

    public Vydavatelstvo getVydavatel() {
        return vydavatel;
    }

    public void setVydavatel(Vydavatelstvo vydavatel) {
        this.vydavatel = vydavatel;
    }

    
    @Override
    public String toString() {
        return "Kniha{" + "id=" + id + ", nazov=" + nazov + '}';
    }

    
    
}
