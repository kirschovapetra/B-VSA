
package rest;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Jedlo {
    private String nazov;
    private Double cena;

    public Jedlo(String nazov, Double cena) {
        this.nazov = nazov;
        this.cena = cena;
    }
    
    public Jedlo(){}

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }
    
    
}
