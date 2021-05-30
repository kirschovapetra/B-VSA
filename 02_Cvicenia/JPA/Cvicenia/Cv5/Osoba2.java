/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv5;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author vsa
 */
@Entity
public class Osoba2 implements Serializable {

    @OneToMany(mappedBy = "garant",cascade = CascadeType.PERSIST)
    private List<Predmet2> predmety_garant;

    @OneToMany(mappedBy = "prednasajuci",cascade = CascadeType.PERSIST)
    private List<Predmet2> predmety_prednasajuci;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String meno;

    public Osoba2(String meno) {
        this.meno = meno;
    }
    
    public Osoba2(){}

    public List<Predmet2> getPredmety_garant() {
        return predmety_garant;
    }

    public void setPredmety_garant(List<Predmet2> predmety_garant) {
        this.predmety_garant = predmety_garant;
    }

    public List<Predmet2> getPredmety_prednasajuci() {
        return predmety_prednasajuci;
    }

    public void setPredmety_prednasajuci(List<Predmet2> predmety_prednasajuci) {
        this.predmety_prednasajuci = predmety_prednasajuci;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
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
        if (!(object instanceof Osoba2)) {
            return false;
        }
        Osoba2 other = (Osoba2) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cv5.Osoba2[ id=" + id + " ]";
    }
    
}
