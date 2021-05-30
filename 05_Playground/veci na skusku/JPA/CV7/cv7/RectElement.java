/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv7;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author vsa
 */
@Entity
public class RectElement extends GuiElement implements Serializable {

    private static final long serialVersionUID = 1L;

    private int hight;
    private int width;

// konstruktory, gettre a settre ...

    public RectElement(int hight, int width, String name, int xCoord, int yCoord) {
        super(name, xCoord, yCoord);
        this.hight = hight;
        this.width = width;
    }

    
    public RectElement(){
        super();
    }
    
    
    
    public int getHight() {
        return hight;
    }

    public void setHight(int hight) {
        this.hight = hight;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    
    
    
    
    // pre pohodlny vypis
    @Override
    public String toString() {
        return super.toString() + " WIDTH=" + width;
    }

}
