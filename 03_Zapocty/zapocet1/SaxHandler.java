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
 * @author igor
 */

//vypise nazvy predmetov v rocniku Bc-2, ktoré prednasa prednasajuci so zadanym menom
public class SaxHandler extends DefaultHandler {

    private String meno, text, currentRocnik,currentPrednasajuci,currentPredmet;
    private Boolean isPredmet = false;

    
    public SaxHandler(String meno) {
        this.meno = meno;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = new String(ch, start, length);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals("predmet")) {
            isPredmet = true;
            currentRocnik = attributes.getValue("rocnik"); 
        }
        
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        
        if (isPredmet && localName.equals("nazov")) {
            currentPredmet = text;
        }

        if (localName.equals("meno")) {
            currentPrednasajuci = text;
        }

        if (localName.equals("predmet")) {
 
            if (this.meno != null && currentPrednasajuci != null &&
                currentPredmet != null && currentRocnik != null) {
                
                if (currentPrednasajuci.equals(this.meno) && currentRocnik.equals("Bc-2")) {
                    System.out.println(currentPredmet);
                }
                
            }
            
            currentPredmet = null;
            isPredmet = false;
            currentRocnik = null;
            currentPrednasajuci = null;
        }
        
    }
        

    
    
    
    
    
    @Override
    public void startDocument() throws SAXException {
        super.startDocument(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument(); //To change body of generated methods, choose Tools | Templates.
    }

}
