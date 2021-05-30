
package cv5u4;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;


@Entity
public class Dokument implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nazov;
    private String text;
    
    @JoinColumn(name="parent_id")
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Dokument> podkapitoly = new ArrayList<>();

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

    public List<Dokument> getPodkapitoly() {
        return podkapitoly;
    }

    public void setPodkapitoly(List<Dokument> podkapitoly) {
        this.podkapitoly = podkapitoly;
    }

    public Dokument(String nazov, String text) {
        this.nazov = nazov;
        this.text = text;
    }
    
    public Dokument(){}
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.nazov);
        hash = 53 * hash + Objects.hashCode(this.text);
        hash = 53 * hash + Objects.hashCode(this.podkapitoly);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dokument other = (Dokument) obj;
        if (!Objects.equals(this.nazov, other.nazov)) {
            return false;
        }
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.podkapitoly, other.podkapitoly)) {
            return false;
        }
        return true;
    }

   

//    @Override
//    public String toString() {
//        return "Dokument{" + "id=" + id + ", nazov=" + nazov + ", text=" + text + '}';
//    }

    @Override
    public String toString() {
        return "Dokument{" + "id=" + id + ", nazov=" + nazov + ", text=" + text + ",\n podkapitoly:" + podkapitoly + '}';
    }

   
    
}
