/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv5_1;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author vsa
 */
@Entity
public class Predmet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nazov;
    private int kredity;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Osoba prednasajuci;
   
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Osoba> cviciaci;
    public String getNazov() {
        return nazov;
    }

    public Osoba getPrednasajuci() {
        return prednasajuci;
    }

    public void setPrednasajuci(Osoba prednasajuci) {
        this.prednasajuci = prednasajuci;
    }

    public List<Osoba> getCviciaci() {
        return cviciaci;
    }

    public void setCviciaci(List<Osoba> cviciaci) {
        this.cviciaci = cviciaci;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public int getKredity() {
        return kredity;
    }

    public void setKredity(int kredity) {
        this.kredity = kredity;
    }

   

    public Predmet(String nazov, int kredity) {
        this.nazov = nazov;
        this.kredity = kredity;
  
    }
    
    public Predmet(){}
    
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
        if (!(object instanceof Predmet)) {
            return false;
        }
        Predmet other = (Predmet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cv5_1.Predmet[ id=" + id + " ]";
    }
    
}
