/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vsa
 */
@XmlRootElement
public class Skuska {
    String predmet;
    String den;
    List<String> student = new ArrayList<>();

    public Skuska(String predmet, String den) {
        this.predmet = predmet;
        this.den = den;
    }

    public Skuska(){}
    
    public String getPredmet() {
        return predmet;
    }

    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }

    public String getDen() {
        return den;
    }

    public void setDen(String den) {
        this.den = den;
    }

    public List<String> getStudent() {
        return student;
    }

    public void setStudent(List<String> student) {
        this.student = student;
    }
    
    
}
