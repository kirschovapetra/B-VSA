/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxapp;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//2. Nájdite film, ktorý nemá určenú dostupnosť
public class SaxHandler2 extends DefaultHandler {

    private String text,movie = "neexistuje";
    private boolean isStream = false;
    private boolean isMovie = false; 
    private boolean maDostupnost = false;
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals("streamSluzba")) {
            isStream = true;
        }
        if (localName.equals("film"))  {
            isMovie = true;
            if (attributes.getValue("dostupnost") != null) {
                maDostupnost = true;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = new String(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equals("streamSluzba")) {
            isStream = false;
        }
        
        if (isMovie && localName.equals("meno")) {
            movie = text;
        }
        
        if (localName.equals("film"))  {
            isMovie = false;
            if (!maDostupnost) {
                System.out.println(movie);
            }
            maDostupnost = false;
        }
    }

    
}
