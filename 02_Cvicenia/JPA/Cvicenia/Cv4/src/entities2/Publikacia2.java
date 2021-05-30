
package entities2;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Publikacia2 implements Serializable {

    /*
    2. Publikácia s referenciou na jej vydavateľa N:1
    P: nazov
    V: adresa
    */
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nazov;
   
    @ManyToOne(cascade = CascadeType.PERSIST)
    Vydavatelstvo2 vydavatelstvo;

    public Publikacia2(String nazov, Vydavatelstvo2 vydavatelstvo) {
        this.nazov = nazov;
        this.vydavatelstvo = vydavatelstvo;
    }
    
    public Publikacia2(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public Vydavatelstvo2 getVydavatelstvo() {
        return vydavatelstvo;
    }

    public void setVydavatelstvo(Vydavatelstvo2 vydavatelstvo) {
        this.vydavatelstvo = vydavatelstvo;
    }
    
    
}
