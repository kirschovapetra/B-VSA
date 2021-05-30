package cv5u3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
public class Osoba implements Serializable {

    @ManyToMany(mappedBy = "cviciaci")
    private List<Predmet> cvicenePredmety = new ArrayList<>();

    @OneToMany(mappedBy = "prednasajuci")
    private List<Predmet> prednasanePredmety = new ArrayList<>();

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    
    private String meno;
    
    public Osoba(){}
    
    public Osoba(String meno) {
        this.meno = meno;
    }

    public List<Predmet> getCvicenePredmety() {
        return cvicenePredmety;
    }

    public void setCvicenePredmety(List<Predmet> cvicenePredmety) {
        this.cvicenePredmety = cvicenePredmety;
    }

    public List<Predmet> getPrednasanePredmety() {
        return prednasanePredmety;
    }

    public void setPrednasanePredmety(List<Predmet> prednasanePredmety) {
        this.prednasanePredmety = prednasanePredmety;
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
        return "Osoba{" + "id=" + id + ", meno=" + meno + '}';
    }

    
    
}
