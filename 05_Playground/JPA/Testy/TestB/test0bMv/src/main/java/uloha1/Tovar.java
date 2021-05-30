
package uloha1;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name="TOVAR")
public class Tovar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="NAZOV")
    private String nazov;
    
    @Column(nullable=false, name="MNOZSTVO")
    private Integer mnozstvo;
    
    @Column(nullable=true,name="CENA")
    private Double cena;

    public Tovar(){}
    
    public Tovar(String nazov, Integer mnozstvo){
        this.nazov = nazov;
        this.mnozstvo = mnozstvo;
    }
    
    public Tovar(String nazov, Integer mnozstvo, Double cena) {
        this.nazov = nazov;
        this.mnozstvo = mnozstvo;
        this.cena = cena;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public Integer getMnozstvo() {
        return mnozstvo;
    }

    public void setMnozstvo(Integer mnozstvo) {
        this.mnozstvo = mnozstvo;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }
 

}
