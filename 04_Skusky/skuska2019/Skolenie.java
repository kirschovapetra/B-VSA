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
public class Skolenie {
    private String info;
    private ArrayList<String> ucastnik = new ArrayList<>();

    public Skolenie(String info) {
        this.info = info;
    }
    
    public Skolenie(){}

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public ArrayList<String> getUcastnik() {
        return ucastnik;
    }

    public void setUcastnik(ArrayList<String> ucastnik) {
        this.ucastnik = ucastnik;
    }
    
    
    
}
