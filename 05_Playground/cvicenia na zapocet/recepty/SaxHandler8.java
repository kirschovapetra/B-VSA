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
public class SaxHandler8 extends DefaultHandler{
    String text, currentStream, oldestMovie, currentMovie, oldestStream;
    Boolean film = false,jeMinim = false;
    Integer oldestYear = Integer.MAX_VALUE, currentYear = Integer.MAX_VALUE;
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = new String(ch,start,length);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals("film")) {
           film = true;
        }
    }
    
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        
        if (!film && localName.equals("meno")) {
            currentStream = text;
        }
        
        if (film && localName.equals("meno")) {
            currentMovie = text;
        }

        if (localName.equals("rok")) {
            currentYear = Integer.parseInt(text);
        }
        
        if (localName.equals("film")) {
            
            if (currentMovie != null && currentYear != Integer.MAX_VALUE) {
                
                if (currentYear < oldestYear) {
                    oldestYear = currentYear;
                    oldestMovie = currentMovie;
                    jeMinim = true;
                    
                }
            }
            film = false;
            currentMovie = null;
            currentYear = Integer.MAX_VALUE;
        }
        
        
        if (localName.equals("streamSluzba")) {
            
            if (jeMinim) {
                oldestStream = currentStream;
                
            }
            jeMinim = false;
            currentStream = null;
        }
        if (localName.equals("sluzby")) {
            System.out.println(oldestStream + " "+oldestMovie+" "+oldestYear);
        }
    
    }
}
