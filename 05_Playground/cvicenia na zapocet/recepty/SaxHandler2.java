/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxapp;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author vsa
 */
public class SaxHandler2 extends DefaultHandler{
    String text,recipeName, currentUnit,milkUnit, milkAmount,currentAmount;
    Boolean polozka = false, mlieko = false, receptMaMlieko = false;
    
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = new String(ch,start,length);
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals("polozka")) {
           polozka = true;
        }
        if (localName.equals("mnozstvo") && attributes.getValue("jednotka") != null) {
           currentUnit = attributes.getValue("jednotka");
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equals("polozka")) {
            polozka = false;
            if (mlieko){
                milkUnit = currentUnit;
                milkAmount = currentAmount;
            }
            mlieko = false;
        }

        if (!polozka && localName.equals("nazov")) {
            recipeName = text;
        }
        
        if (polozka && localName.equals("nazov")) {
            if (text.equals("mlieko")) {
                mlieko = true;
                receptMaMlieko = true;
            }         
        }
        
        if (polozka && localName.equals("mnozstvo")) {
            currentAmount = text;       
        }

        if (localName.equals("recept")) {
            if (receptMaMlieko)
                System.out.println(recipeName + ": mlieko - " + milkAmount + " " + milkUnit);
            receptMaMlieko = false;
        }
    
    }

    @Override
    public void endDocument() throws SAXException {
       //System.out.println(milkAmount + " " + milkUnit);
    }
    
    
}
