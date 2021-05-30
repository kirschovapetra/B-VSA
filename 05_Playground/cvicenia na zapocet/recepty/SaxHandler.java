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
receptami a vypíše názvy všetkých receptov, ktoré obsahujú múku.

Implementujte program, ktorý s využitím SAX parsera číta XML súbor s kuchárskymi 
receptami a zistí aké množstvo mlieka ide do omelety; na obrazovku vypíše 
množstvo aj jednotku
 */
public class SaxHandler extends DefaultHandler {

    String text,recipeName;
    Boolean polozka = false,muka = false;
    
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = new String(ch,start,length);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals("polozka")) {
           polozka = true;

        }
    }
    
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equals("polozka")) {
            polozka = false;
        }

        if (!polozka && localName.equals("nazov")) {
            recipeName = text;
        }
        
        if (polozka && localName.equals("nazov")) {
            if (text.equals("muka")) {
                muka = true;
            }         
        }

        if (localName.equals("recept")) {
            if (muka) {
                System.out.println(recipeName);
            }
            
            muka = false;
        }
    
    }

    
    
    

    
}
