package cv4u4;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;


@Entity
public class Kniha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String isbn;
    private String nazov;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Vydavatel vydavatel;
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Autor> autori = new HashSet<>();;
    @OneToMany(mappedBy = "kniha")
    private List<Polozka> polozky = new ArrayList<>();

    public List<Polozka> getPolozky() {
        return polozky;
    }

    public void setPolozky(List<Polozka> polozky) {
        this.polozky = polozky;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public Vydavatel getVydavatel() {
        return vydavatel;
    }

    public void setVydavatel(Vydavatel vydavatel) {
        this.vydavatel = vydavatel;
    }

    public Set<Autor> getAutori() {
        return autori;
    }

    public void setAutori(Set<Autor> autori) {
        this.autori = autori;
    }
    public Kniha(){}
    public Kniha(String isbn, String nazov) {
        this.isbn = isbn;
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
        if (!(object instanceof Kniha)) {
            return false;
        }
        Kniha other = (Kniha) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cv4u4.Kniha[ id=" + id + " ]";
    }
    
}
