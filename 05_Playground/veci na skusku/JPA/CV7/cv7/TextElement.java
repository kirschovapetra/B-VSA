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
public class TextElement extends GuiElement implements Serializable {

    private static final long serialVersionUID = 1L;
    // private Long id;

    private String text;
    private int fontSize;
    private String fontFamily;

    // konstruktory, gettre a settre ...
    public TextElement(){
        super();
    }
    
    public TextElement(String text, int fontSize, String fontFamily, String name, int xCoord, int yCoord) {
        super(name, xCoord, yCoord);
        this.text = text;
        this.fontSize = fontSize;
        this.fontFamily = fontFamily;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }
    
    
    
    
    // pre pohodlny vypis
    @Override
    public String toString() {
        return super.toString() + " TEXT=" + text;
    }
}
