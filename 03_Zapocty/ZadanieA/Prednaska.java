/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zadanie;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
/**
 *
 * @author vsa
 */
@Entity
public class Prednaska implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nazov;
    @ElementCollection
    private List<String> autori;
    
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Akcia konferencia;

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public List<String> getAutori() {
        return autori;
    }

    public void setAutori(List<String> autori) {
        this.autori = autori;
    }

    public Akcia getKonferencia() {
        return konferencia;
    }

    public void setKonferencia(Akcia konferencia) {
        this.konferencia = konferencia;
    }

    public Prednaska(String nazov, List<String> autori, Akcia konferencia) {
        this.nazov = nazov;
        this.autori = autori;
        this.konferencia = konferencia;
    }

    public Prednaska(String nazov, List<String> autori) {
        this.nazov = nazov;
        this.autori = autori;
    }
    
    public Prednaska(){}
    
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
        if (!(object instanceof Prednaska)) {
            return false;
        }
        Prednaska other = (Prednaska) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "zadanie.Prednaska[ id=" + id + " ]";
    }
    
}
