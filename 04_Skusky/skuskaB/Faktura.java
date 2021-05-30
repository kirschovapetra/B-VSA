/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skuskab;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author vsa
 */
@Entity
public class Faktura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    String zakaznik;    
   
    @Temporal(javax.persistence.TemporalType.DATE)
    Date aktualizacia;

    public Faktura(String zakaznik) {
        this.zakaznik = zakaznik;
    }

    public Faktura(int id, String zakaznik) {
        this.id = id;
        this.zakaznik = zakaznik;
    }
    
    
    
    public Faktura(){}
    
        @OneToMany(mappedBy = "faktura", cascade=CascadeType.PERSIST)
    List<Polozka> polozky = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZakaznik() {
        return zakaznik;
    }

    public void setZakaznik(String zakaznik) {
        this.zakaznik = zakaznik;
    }

    public Date getAktualizacia() {
        return aktualizacia;
    }

    public void setAktualizacia(Date aktualizacia) {
        this.aktualizacia = aktualizacia;
    }

    public List<Polozka> getPolozky() {
        return polozky;
    }

    public void setPolozky(List<Polozka> polozky) {
        this.polozky = polozky;
    }

    
    @Override
    public String toString() {
        return "skuskab.Faktura[ id=" + id + " ]";
    }
    
}
