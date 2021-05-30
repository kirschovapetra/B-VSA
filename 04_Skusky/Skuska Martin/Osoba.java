/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zapocet2;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="OSOBA")
public class Osoba implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;                        // primarny kluc - AUTOGENEROVANE ID
    private String meno;
    @Temporal(TemporalType.DATE)
    private Date narodena;
   
    @ManyToMany(mappedBy = "vyucujuci")
    private Set<Predmet> predmety = new HashSet<>();          // predmety, ktore osoba vyucuje

    public Osoba(Long id, String meno) {
        this.id = id;
        this.meno = meno;
    }

    public Osoba(Set<Predmet> predmety) {
        this.predmety = predmety;
    }

    public Osoba(String meno, Set<Predmet> predmety) {
        this.meno = meno;
        this.predmety = predmety;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Osoba() {
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
