/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities3;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Vydavatelstvo3 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String nazov;
    
    @OneToMany(cascade=CascadeType.PERSIST)
    @JoinColumn(name="vydavatelstvo_id")
    List<Publikacia3> publikacie;

    public Vydavatelstvo3(String nazov, List<Publikacia3> publikacie) {
        this.nazov = nazov;
        this.publikacie = publikacie;
    }

    public Vydavatelstvo3(){}

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public List<Publikacia3> getPublikacie() {
        return publikacie;
    }

    public void setPublikacie(List<Publikacia3> publikacie) {
        this.publikacie = publikacie;
    }


    
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
        if (!(object instanceof Vydavatelstvo3)) {
            return false;
        }
        Vydavatelstvo3 other = (Vydavatelstvo3) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities3.Vydavatelstvo3[ id=" + id + " ]";
    }
    
}
