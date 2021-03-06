/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zadanie;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;


@Entity
public class Akcia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nazov;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date termin;
    
    @OneToMany(mappedBy = "konferencia",cascade = CascadeType.PERSIST)
    private List<Prednaska> prednasky = new ArrayList<>();

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public Date getTermin() {
        return termin;
    }

    public void setTermin(Date termin) {
        this.termin = termin;
    }

    public List<Prednaska> getPrednasky() {
        return prednasky;
    }

    public void setPrednasky(List<Prednaska> prednasky) {
        this.prednasky = prednasky;
    }

    public Akcia(String nazov, Date termin) {
        this.nazov = nazov;
        this.termin = termin;
    }
    
    public Akcia(){}
    
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
        if (!(object instanceof Akcia)) {
            return false;
        }
        Akcia other = (Akcia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "zadanie.Akcia[ id=" + id + " ]";
    }
    
}
