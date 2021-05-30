package rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Jedlo {
    private String nazov;
    private double cena;

    public Jedlo(String nazov, double cena) {
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

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    

}
