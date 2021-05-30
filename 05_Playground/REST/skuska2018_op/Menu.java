/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.*;
import javax.xml.bind.annotation.*;

@XmlRootElement
public class Menu {
    private List<Jedlo> jedla = new ArrayList<>();

    public Menu() {
    }

    public List<Jedlo> getJedla() {
        return jedla;
    }

    public void setJedla(List<Jedlo> jedla) {
        this.jedla = jedla;
    }
    
    
}
