/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vsa
 */
@XmlRootElement
public class Menu {
    String den;

    Map<Integer, Jedlo> ponuka = new HashMap<>();
    
    public Menu(){}
    
    //const
    public Menu(String den, Map<Integer, Jedlo> jedla) {
        this.den = den;
        ponuka = jedla;
    }

    public String getDen() {
        return den;
    }

    public void setDen(String den) {
        this.den = den;
    }

    //@XmlElement(name="ponuka")
    public Map<Integer, Jedlo> getPonuka() {
        return ponuka;
    }

    public void setPonuka(Map<Integer, Jedlo> ponuka) {
        this.ponuka = ponuka;
    }
    
    
    
}
