package cv5_4;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
public class DokumentBi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nazov;
    private String text;


    @ManyToOne(cascade = CascadeType.PERSIST )
    private DokumentBi hlavny;
    @OneToMany(mappedBy = "hlavny",cascade = CascadeType.PERSIST,orphanRemoval = true)
    private List<DokumentBi> podkapitoly;
    
    public List<DokumentBi> getPodkapitoly() {
        return podkapitoly;
    }

    public void setPodkapitoly(List<DokumentBi> podkapitoly) {
        this.podkapitoly = podkapitoly;
    }

    public DokumentBi getHlavny() {
        return hlavny;
    }

    public void setHlavny(DokumentBi hlavny) {
        this.hlavny = hlavny;
    }

    public DokumentBi() {
    }

    public DokumentBi(String nazov, String text) {
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
    public String toString() {
        return "cv5_4.Dokument[ id=" + id + " ]";
    }

}
