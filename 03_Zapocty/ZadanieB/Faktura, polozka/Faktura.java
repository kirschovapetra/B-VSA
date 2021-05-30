package dbapp;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;


@Entity
public class Faktura implements Serializable {

    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String zakaznik;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date aktualizacia;

    @OneToMany(mappedBy = "faktura", cascade = CascadeType.PERSIST)
    private List<Polozka> polozky = new ArrayList<>();

    public Faktura(String zakaznik, Date aktualizacia) {
        this.zakaznik = zakaznik;
        this.aktualizacia = aktualizacia;
    }

    public Faktura(){}

    public String getZakaznik() {
        return zakaznik;
    }

    public void setZakaznik(String zakaznik) {
        this.zakaznik = zakaznik;
    }

    public Date getAktualizacia() {
        return aktualizacia;
    }

    public void setAktualizacia(Date aktualizacia) {
        this.aktualizacia = aktualizacia;
    }

    public List<Polozka> getPolozky() {
        return polozky;
    }

    public void setPolozky(List<Polozka> polozky) {
        this.polozky = polozky;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        if (!(object instanceof Faktura)) {
            return false;
        }
        Faktura other = (Faktura) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dbapp.Faktura[ id=" + id + " ]";
    }
    
}
