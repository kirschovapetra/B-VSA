
package zadanie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;


@Entity
public class Akcia implements Serializable {

    @OneToMany(mappedBy = "konferencia")
    private List<Prednaska> prednasky = new ArrayList<>();

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nazov;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date termin;

    public Akcia(String nazov, Date termin) {
        this.nazov = nazov;
        this.termin = termin;
    }
    public Akcia(){}

    public List<Prednaska> getPrednasky() {
        return prednasky;
    }

    public void setPrednasky(List<Prednaska> prednasky) {
        this.prednasky = prednasky;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public Date getTermin() {
        return termin;
    }

    public void setTermin(Date termin) {
        this.termin = termin;
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
        if (!(object instanceof Akcia)) {
            return false;
        }
        Akcia other = (Akcia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "zadanie.Akcia[ id=" + id + " ]";
    }
    
}
