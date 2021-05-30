
package cv5_3;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
public class Osoba implements Serializable {

    @ManyToMany(mappedBy = "cviciaci",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Predmet> predmety_cvicene;

    @OneToMany(mappedBy = "prednasajuci",cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Predmet> predmety_prednasane;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String meno;

    public List<Predmet> getPredmety_cvicene() {
        return predmety_cvicene;
    }

    public void setPredmety_cvicene(List<Predmet> predmety_cvicene) {
        this.predmety_cvicene = predmety_cvicene;
    }

    public List<Predmet> getPredmety_prednasane() {
        return predmety_prednasane;
    }

    public void setPredmety_prednasane(List<Predmet> predmety_prednasane) {
        this.predmety_prednasane = predmety_prednasane;
    }

    public Osoba(String meno) {
        this.meno = meno;
    }

    public Osoba(){}
    
    
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
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Osoba)) {
            return false;
        }
        Osoba other = (Osoba) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cv5_3.Osoba[ id=" + id + " ]";
    }
    
}
