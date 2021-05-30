/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Menu {
    String den;
    
    List<Jedlo> jedla;

    public Menu(){}
    
    public Menu(String den, List<Jedlo> jedla) {
        this.den = den;
        this.jedla = jedla;
    }

    public List<Jedlo> getJedla() {
        return jedla;
    }

    public void setJedla(List<Jedlo> jedla) {
        this.jedla = jedla;
    }

    
    public String getDen() {
        return den;
    }

    public void setDen(String den) {
        this.den = den;
    }
    
    
}
