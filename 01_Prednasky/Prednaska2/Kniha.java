/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prednaska2;

/**
 *
 * @author vsa
 */
public class Kniha {
    public String nazov;
    public double cena;

    public Kniha(String nazov, double cena) {
        this.nazov = nazov;
        this.cena = cena;
    }

    
    
    @Override
    public String toString() {
        return "Kniha{" + "nazov=" + nazov + ", cena=" + cena + '}';
    }
    
    
}
