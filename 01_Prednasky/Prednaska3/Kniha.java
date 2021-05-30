/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import prednaska3.Stav;



@Entity
public class Kniha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String meno;
    private double cena;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date vydana;
    @Enumerated(javax.persistence.EnumType.STRING)
    private Stav stav;

    public Kniha() {
    }

    public Kniha(String meno, double cena, Date vydana, Stav stav) {
        this.meno = meno;
        this.cena = cena;
        this.vydana = vydana;
        this.stav = stav;
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

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Date getVydana() {
        return vydana;
    }

    public void setVydana(Date vydana) {
        this.vydana = vydana;
    }

    public Stav getStav() {
        return stav;
    }

    public void setStav(Stav stav) {
        this.stav = stav;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.meno);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.cena) ^ (Double.doubleToLongBits(this.cena) >>> 32));
        hash = 79 * hash + Objects.hashCode(this.vydana);
        hash = 79 * hash + Objects.hashCode(this.stav);
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
        final Kniha other = (Kniha) obj;
        if (Double.doubleToLongBits(this.cena) != Double.doubleToLongBits(other.cena)) {
            return false;
        }
        if (!Objects.equals(this.meno, other.meno)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.vydana, other.vydana)) {
            return false;
        }
        if (this.stav != other.stav) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Kniha{" + "id=" + id + ", meno=" + meno + ", cena=" + cena + ", vydana=" + vydana + ", stav=" + stav + '}';
    }

    
    
   
    
}
