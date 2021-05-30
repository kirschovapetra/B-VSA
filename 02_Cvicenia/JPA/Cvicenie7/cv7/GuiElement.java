/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv7;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author vsa
 */
@Entity
@Table(name = "ELEMENT")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class GuiElement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int xCoord;
    private int yCoord;

// konstruktory, gettre a settre ...

    public GuiElement(){}
    
    public GuiElement(String name, int xCoord, int yCoord) {
        this.name = name;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }
    
    
    
    
    // pre pohodlny vypis
    @Override
    public String toString() {
        return "" + getId() + ":" + getName();
    }
}