/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv4u4;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author vsa
 */
@Entity
public class Obchod implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String nazov;
    @OneToMany(mappedBy = "obchod")
    private Set<Polozka> polozky = new HashSet<>();

    public Set<Polozka> getPolozky() {
        return polozky;
    }

    public void setPolozky(Set<Polozka> polozky) {
        this.polozky = polozky;
    }

    
    
    public Obchod(String nazov) {
        this.nazov = nazov;
    }
    
    public Obchod(){}

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
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
        if (!(object instanceof Obchod)) {
            return false;
        }
        Obchod other = (Obchod) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Obchod{" + "id=" + id + ", nazov=" + nazov + '}';
    }

   
    
}
