/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vsa
 */
@XmlRootElement
public class Menu {
    String den;
    List<Jedlo> jedla = new ArrayList<>();
    
    public Menu(){   
    }

    public Menu(String den, List<Jedlo> jedla) {
        this.den = den;
        this.jedla = jedla;
    }
    @XmlTransient
    public String getDen() {
        return den;
    }

    public void setDen(String den) {
        this.den = den;
    }

    @XmlElement(name="jedlo")
    public List<Jedlo> getJedla() {
        return jedla;
    }

    public void setJedla(List<Jedlo> jedla) {
        this.jedla = jedla;
    }
    
    
}
