/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vsa
 */
@Entity
@Table(name = "KNIHA2")
@NamedQueries({
    @NamedQuery(name = "Kniha2.findAll", query = "SELECT k FROM Kniha2 k"),
    @NamedQuery(name = "Kniha2.findById", query = "SELECT k FROM Kniha2 k WHERE k.id = :id"),
    @NamedQuery(name = "Kniha2.findByCena", query = "SELECT k FROM Kniha2 k WHERE k.cena = :cena"),
    @NamedQuery(name = "Kniha2.findByMeno", query = "SELECT k FROM Kniha2 k WHERE k.meno = :meno"),
    @NamedQuery(name = "Kniha2.findByStav", query = "SELECT k FROM Kniha2 k WHERE k.stav = :stav"),
    @NamedQuery(name = "Kniha2.findByVydana", query = "SELECT k FROM Kniha2 k WHERE k.vydana = :vydana")})
public class Kniha2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CENA")
    private Double cena;
    @Column(name = "MENO")
    private String meno;
    @Column(name = "STAV")
    private String stav;
    @Column(name = "VYDANA")
    @Temporal(TemporalType.DATE)
    private Date vydana;

    public Kniha2() {
    }

    public Kniha2(Long id) {
        this.id = id;
    }

    public Kniha2(String meno,Double cena, Date vydana,String stav) {
        this.cena = cena;
        this.meno = meno;
        this.stav = stav;
        this.vydana = vydana;
    }

    public Kniha2(Long id, String meno) {
        this.id = id;
        this.meno = meno;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public String getStav() {
        return stav;
    }

    public void setStav(String stav) {
        this.stav = stav;
    }

    public Date getVydana() {
        return vydana;
    }

    public void setVydana(Date vydana) {
        this.vydana = vydana;
    }

    @Override
    public String toString() {
        return "Kniha2{" + "id=" + id + ", cena=" + cena + ", meno=" + meno + ", stav=" + stav + ", vydana=" + vydana + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.cena);
        hash = 59 * hash + Objects.hashCode(this.meno);
        hash = 59 * hash + Objects.hashCode(this.stav);
        hash = 59 * hash + Objects.hashCode(this.vydana);
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
        final Kniha2 other = (Kniha2) obj;
        if (!Objects.equals(this.meno, other.meno)) {
            return false;
        }
        if (!Objects.equals(this.stav, other.stav)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.cena, other.cena)) {
            return false;
        }
        if (!Objects.equals(this.vydana, other.vydana)) {
            return false;
        }
        return true;
    }


   
}
