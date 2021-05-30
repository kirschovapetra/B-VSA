/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;


//Uloha 3 - in nazov tabulky, povinna vaha

@Table(name="T_OSOBA")
@Entity
public class Osoba implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String meno;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date narodena;
    
    @Column(nullable=false)
    private Float vaha;

    public Osoba() {
    }

    public Osoba(Long id) {
        this.id = id;
    }

    public Osoba(String meno) {
        this.meno = meno;
    }

    public Osoba(Long id, String meno) {
        this.id = id;
        this.meno = meno;
    }

    public Osoba(Long id, String meno, Date narodena, Float vaha) {
        this.id = id;
        this.meno = meno;
        this.narodena = narodena;
        this.vaha = vaha;
    }

    public Osoba(String meno, Date narodena, Float vaha) {
        this.meno = meno;
        this.narodena = narodena;
        this.vaha = vaha;
    }
    
    
    
    public Float getVaha() {
        return vaha;
    }
    public void setVaha(Float vaha) {
        this.vaha = vaha;
    }
    public Date getNarodena() {
        return narodena;
    }
    public void setNarodena(Date narodena) {
        this.narodena = narodena;
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
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.meno);
        hash = 71 * hash + Objects.hashCode(this.narodena);
        hash = 71 * hash + Objects.hashCode(this.vaha);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Osoba other = (Osoba) obj;
        if (!Objects.equals(this.meno, other.meno)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.narodena, other.narodena)) {
            return false;
        }
        if (!Objects.equals(this.vaha, other.vaha)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Osoba{" + "id=" + id + ", meno=" + meno + ", narodena=" + narodena + ", vaha=" + vaha + '}';
    }


    
}
