/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zapocet2;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity
public class Osoba implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;                        // primarny kluc - AUTOGENEROVANE ID
    private String meno;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date narodena;
    @JoinTable(joinColumns = @JoinColumn(name = "osoba_id"),
            inverseJoinColumns = @JoinColumn(name = "predmet_kod"))
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Predmet> predmety = new HashSet<>();          // predmety, ktore osoba vyucuje

    public Osoba(String meno, Date narodena) {
        this.meno = meno;
        this.narodena = narodena;
    }

    public Osoba() {
    }
    
    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public Date getNarodena() {
        return narodena;
    }

    public void setNarodena(Date narodena) {
        this.narodena = narodena;
    }

    public Set<Predmet> getPredmety() {
        return predmety;
    }

    public void setPredmety(Set<Predmet> predmety) {
        this.predmety = predmety;
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
        if (!(object instanceof Osoba)) {
            return false;
        }
        Osoba other = (Osoba) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Osoba[ id=" + id + " ]";
    }

}
