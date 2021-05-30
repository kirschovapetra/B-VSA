/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vsa
 */
@XmlRootElement
public class Menu {
    String den;
    TreeMap<Integer,Jedlo> jedla = new TreeMap<>();

    public Menu() {
    }

    public Menu(String den) {
        this.den = den;
    }
    public Menu(String den, TreeMap<Integer, Jedlo> jedla) {
        this.den = den;
        this.jedla = jedla;
    }
    public String getDen() {
        return den;
    }

    public void setDen(String den) {
        this.den = den;
    }

    public TreeMap<Integer, Jedlo> getJedla() {
        return jedla;
    }

    public void setJedla(TreeMap<Integer, Jedlo> jedla) {
        this.jedla = jedla;
    }
    
    
}
