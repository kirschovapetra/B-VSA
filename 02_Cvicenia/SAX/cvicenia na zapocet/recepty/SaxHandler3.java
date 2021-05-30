/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxapp;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/*
vypisat tie ingrediencie ktorych mnozstvo je viac ako 1kg
 */
public class SaxHandler3 extends DefaultHandler {
    String text,currentUnit, currentIngredient;
    Double currentAmount = 0.0;
    Boolean polozka = false;
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        
        if (localName.equals("polozka")) {
            polozka = true;
        
        }
        
        if (localName.equals("mnozstvo")) {
            currentUnit = attributes.getValue("jednotka");
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = new String(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
      
        if (localName.equals("mnozstvo")) {
            currentAmount = Double.parseDouble(text);
        }
        
        if (polozka && localName.equals("nazov")) {
            currentIngredient = text;
            
        }
        
        if (localName.equals("polozka")) {
            if (currentUnit != null && currentUnit.equals("kg") && currentAmount < 1.0) {
                System.out.println(currentIngredient + " " + currentAmount + " " + currentUnit);
            }
            polozka = false;
            currentUnit = null;
        }
    }

    
    
    
    
}
