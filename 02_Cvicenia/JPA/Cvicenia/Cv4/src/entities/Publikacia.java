
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

//1. Publikácia so zoznamom (referencií na) autorov N:N
//P: nazov
//A: meno

@Entity
public class Publikacia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nazov;

    public Publikacia(String nazov, List<Autor> autori) {
        
        this.nazov = nazov;
        this.autori = autori;
    }
    
    public Publikacia(){}
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Autor> autori;

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public List<Autor> getAutori() {
        return autori;
    }

    public void setAutori(List<Autor> autori) {
        this.autori = autori;
    }
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
}
