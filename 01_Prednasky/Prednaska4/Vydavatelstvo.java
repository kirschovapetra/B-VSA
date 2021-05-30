
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
public class Vydavatelstvo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String adresa;
        
    @OneToMany//(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "vydavatelstvo_id")
    private List<Kniha> publikacie;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public List<Kniha> getPublikacie() {
        return publikacie;
    }

    public void setPublikacie(List<Kniha> publikacie) {
        this.publikacie = publikacie;
    }

    
    
    @Override
    public String toString() {
        return "Vydavatelstvo{" + "id=" + id + ", adresa=" + adresa + '}';
    }

    
    
    
}
