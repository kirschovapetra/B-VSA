
package entities4;

import java.io.Serializable;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
4. Publikácia so zoznamom mien autorov
P: nazov, zoznam autorov - string
 */
@Entity
public class Publikacia4 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nazov;
    
    @ElementCollection
    private List<String> autor;

    public Publikacia4(){}
    
    public Publikacia4(String nazov, List<String> autor) {
        this.nazov = nazov;
        this.autor = autor;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public void setAutor(List<String> autor) {
        this.autor = autor;
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
        if (!(object instanceof Publikacia4)) {
            return false;
        }
        Publikacia4 other = (Publikacia4) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities4.Publikacia4[ id=" + id + " ]";
    }
    
}
