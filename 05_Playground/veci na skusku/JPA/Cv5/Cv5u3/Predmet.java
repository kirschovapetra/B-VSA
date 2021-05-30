
package cv5u3;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity
public class Predmet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nazov;
    
    private int kredity;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Osoba prednasajuci;
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Osoba> cviciaci = new ArrayList<>();

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public int getKredity() {
        return kredity;
    }

    public void setKredity(int kredity) {
        this.kredity = kredity;
    }

    public Osoba getPrednasajuci() {
        return prednasajuci;
    }

    public void setPrednasajuci(Osoba prednasajuci) {
        this.prednasajuci = prednasajuci;
    }

    public List<Osoba> getCviciaci() {
        return cviciaci;
    }

    public void setCviciaci(List<Osoba> cviciaci) {
        this.cviciaci = cviciaci;
    }

    public Predmet(String nazov, int kredity) {
        this.nazov = nazov;
        this.kredity = kredity;
    }
    
    public Predmet(){}
    
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
        if (!(object instanceof Predmet)) {
            return false;
        }
        Predmet other = (Predmet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Predmet{" + "id=" + id + ", nazov=" + nazov + ", kredity=" + kredity + ", prednasajuci=" + prednasajuci + '}';
    }
    
}
