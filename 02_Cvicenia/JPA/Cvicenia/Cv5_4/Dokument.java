
package cv5_4;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;


@Entity
public class Dokument implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nazov;
    private String text;

    
    @OneToMany(cascade = CascadeType.PERSIST,orphanRemoval = true)
    @JoinColumn(name="pod_id")
    private List<Dokument> podkapitoly;


    public List<Dokument> getPodkapitoly() {
        return podkapitoly;
    }

    public void setPodkapitoly(List<Dokument> podkapitoly) {
        this.podkapitoly = podkapitoly;
    }

    public Dokument(){}
    
    public Dokument(String nazov, String text) {
        this.nazov = nazov;
        this.text = text;
    }

    
    
    
    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

 
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
        if (!(object instanceof Dokument)) {
            return false;
        }
        Dokument other = (Dokument) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cv5_4.Dokument[ id=" + id + " ]";
    }
    
}
