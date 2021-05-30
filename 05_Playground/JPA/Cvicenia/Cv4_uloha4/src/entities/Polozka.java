
package entities;

import java.io.Serializable;
import javax.persistence.*;


@Entity
public class Polozka implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double cena;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Kniha kniha;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Obchod obchod;

    public Polozka(Double cena, Kniha kniha, Obchod obchod) {
        this.cena = cena;
        this.kniha = kniha;
        this.obchod = obchod;
    }

    public Kniha getKniha() {
        return kniha;
    }

    public void setKniha(Kniha kniha) {
        this.kniha = kniha;
    }

    public Obchod getObchod() {
        return obchod;
    }

    public void setObchod(Obchod obchod) {
        this.obchod = obchod;
    }
    
    
    
    public Polozka(){}
    
    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
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
        if (!(object instanceof Polozka)) {
            return false;
        }
        Polozka other = (Polozka) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Polozka[ id=" + id + " ]";
    }
    
}
