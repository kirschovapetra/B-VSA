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
Implementujte program, ktorý s využitím SAX parsera číta XML súbor s kuchárskymi 
receptami a zistí aké množstvo vajec ide do prazenice; na obrazovku vypíše 
množstvo aj jednotku.
 */
public class SaxHandler7 extends DefaultHandler {

    String text,recipeName, prazenicaUnit,prazenicaEggs, currentUnit, currentItem;
    Boolean polozka = false, egg = false;
    
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
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = new String(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equals("polozka")) {
            if (egg) {
                prazenicaEggs = currentItem;
                prazenicaUnit = currentUnit;
            }
            polozka = false;
            egg = false;
        }
        if (localName.equals("mnozstvo")) {
            currentItem = text;
        }
        if (!polozka && localName.equals("nazov")) {
           recipeName = text;
        }
        if (polozka && localName.equals("nazov")) {
           if (text.equals("vajce")) {
               egg = true;
           }
       }
       if (localName.equals("recept")) {
           if (recipeName != null && recipeName.equals("prazenica") && prazenicaEggs != null && prazenicaUnit != null) {
               System.out.println(recipeName + " : " + prazenicaEggs + " " + prazenicaUnit);
           }
           prazenicaEggs = null;
           prazenicaUnit = null;
           recipeName = null;          
       }
    }

    
    
}
