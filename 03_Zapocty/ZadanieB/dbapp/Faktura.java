/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbapp;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 *
 * @author vsa
 */
@Entity
public class Faktura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String zakaznik;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date aktualizacia;

    public Date getAktualizacia() {
        return aktualizacia;
    }

    public void setAktualizacia(Date aktualizacia) {
        this.aktualizacia = aktualizacia;
    }

    public String getZakaznik() {
        return zakaznik;
    }

    public void setZakaznik(String zakaznik) {
        this.zakaznik = zakaznik;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        if (!(object instanceof Faktura)) {
            return false;
        }
        Faktura other = (Faktura) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dbapp.Faktura[ id=" + id + " ]";
    }
    
}
