/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author vsa
 */
@Entity
public class Kniha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String isbn;
    private String nazov;

    @JoinTable(
            joinColumns = @JoinColumn(name = "kniha_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id"))
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Autor> autori = new ArrayList<>();
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Vydavatel vydavatel;
    
    @OneToMany(mappedBy = "kniha")
    private List<Polozka> polozky = new ArrayList<>();

    public Kniha(){}
    
    public Kniha(String isbn, String nazov, Vydavatel vydavatel) {
        this.isbn = isbn;
        this.nazov = nazov;
        this.vydavatel = vydavatel;
    }

    public List<Polozka> getPolozky() {
        return polozky;
    }

    public void setPolozky(List<Polozka> polozky) {
        this.polozky = polozky;
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

    public List<Autor> getAutori() {
        return autori;
    }

    public void setAutori(List<Autor> autori) {
        this.autori = autori;
    }

    public Vydavatel getVydavatel() {
        return vydavatel;
    }

    public void setVydavatel(Vydavatel vydavatel) {
        this.vydavatel = vydavatel;
    }
    
    
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kniha)) {
            return false;
        }
        Kniha other = (Kniha) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cv4.Kniha[ id=" + id + " ]";
    }
    
}
