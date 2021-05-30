package entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Kniha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nazov; 
    
    //@ManyToMany(cascade = CascadeType.PERSIST)
    //List<Osoba> autori;
    
    //@ManyToOne(cascade = CascadeType.PERSIST)
    //Vydavatelstvo vydavatel;
    
    @ElementCollection
    private List<String>menaAutorov;
    
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

    /*public List getAutori() {
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
    }*/

    
    @Override
    public String toString() {
        return "Kniha{" + "id=" + id + ", nazov=" + nazov + '}';
    }

    
    
}
