package rest;

import java.util.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Menu {
    private String den;
    private TreeMap<Integer,Jedlo> jedla = new TreeMap<>();

    public Menu(String den) {
        this.den = den;
    }

    public Menu(){}
    
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
