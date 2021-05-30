package entities;

import java.io.Serializable;
import javax.persistence.*;



@Entity
public class Kniha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String isbn;

    private String nazov;
    private double cena;

    public Kniha(){}
    
    public Kniha(String isbn, String nazov, double cena) {
        this.isbn = isbn;
        this.nazov = nazov;
        this.cena = cena;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
    
    
    
}
