/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prednaska2_2;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author vsa
 */
@Entity 
public class Kniha {
    @Id
    private String nazov;
    private double cena;

    public Kniha() {
    }

    public Kniha(String nazov, double cena) {
        this.nazov = nazov;
        this.cena = cena;
    }

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

    @Override
    public String toString() {
        return "Kniha{" + "nazov=" + nazov + ", cena=" + cena + '}';
    }
    
    
}
