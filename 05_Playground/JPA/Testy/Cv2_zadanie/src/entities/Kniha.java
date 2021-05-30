
package entities;

import java.io.Serializable;
import javax.persistence.*;


@Entity
public class Kniha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String nazov;
    String autor;
    Integer pocet;

    public Kniha(String id, String autor, Integer pocet) {
        this.nazov = id;
        this.autor = autor;
        this.pocet = pocet;
    }

   

    public Kniha(){}
    
    
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getPocet() {
        return pocet;
    }

    public void setPocet(Integer pocet) {
        this.pocet = pocet;
    }
    
    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    @Override
    public String toString() {
        return "Kniha{" + "id=" + nazov + ", autor=" + autor + ", pocet=" + pocet + '}';
    }

}
