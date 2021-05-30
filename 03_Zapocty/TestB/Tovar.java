/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uloha1;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author vsa
 */
@Entity
public class Tovar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String nazov;

    private Integer Mnozstvo;
    public Tovar(){}
    public Tovar(String nazov, Integer Mnozstvo) {
        this.nazov = nazov;
        this.Mnozstvo = Mnozstvo;
    }

    public Integer getMnozstvo() {
        return Mnozstvo;
    }

    public void setMnozstvo(Integer Mnozstvo) {
        this.Mnozstvo = Mnozstvo;
    }

    private Double cena;

    /**
     * Get the value of cena
     *
     * @return the value of cena
     */
    public Double getCena() {
        return cena;
    }

    /**
     * Set the value of cena
     *
     * @param cena new value of cena
     */
    public void setCena(Double cena) {
        this.cena = cena;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nazov != null ? nazov.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the nazov fields are not set
        if (!(object instanceof Tovar)) {
            return false;
        }
        Tovar other = (Tovar) object;
        if ((this.nazov == null && other.nazov != null) || (this.nazov != null && !this.nazov.equals(other.nazov))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uloha1.Tovar[ id=" + nazov + " ]";
    }
    
}
