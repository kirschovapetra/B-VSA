/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxapp;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//3. Nájdite najnovšiu rozprávku na netflixe
public class SaxHandler3 extends DefaultHandler {

    String text;
    Boolean inFilm = false;
    Integer maxYear = 0;
    String maxRozpravka;
    Integer currentYear = 0;
    Integer currentMaxYear = 0;
    String currentFilm,currentMaxRozpravka;
    String currentStream;
    
    Boolean isNetflix = false;
    Boolean isRozpravka = false;
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals("film")) {
            inFilm = true;
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = new String(ch,start,length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        
        
        if (localName.equals("zaner")) {
            if (text.equals("rozpravka")) {
                isRozpravka = true;
            }
        }
        
        if (inFilm && localName.equals("meno")) {
            currentFilm = text;
        }
        
        if (inFilm && localName.equals("rok")) {
            currentYear = Integer.parseInt(text);
        }
        
        if (!inFilm && localName.equals("meno")) {
            currentStream = text;
            
            
        }
        
        if (localName.equals("film")) {
            
            if (isRozpravka) {
                if (currentYear > currentMaxYear) {
                    currentMaxYear = currentYear;
                    currentMaxRozpravka = currentFilm;
                }
            
            }
            
            
            isRozpravka = false;
            inFilm = false;
        }
        
        if (localName.equals("streamSluzba")) {
            if (currentStream.equals("Netflix")) {
                maxYear = currentMaxYear;
                maxRozpravka = currentMaxRozpravka;
                
            }
            
        }
        
    }

    @Override
    public void endDocument() throws SAXException {
      System.out.println(maxRozpravka);
    }

    
    
    
    
}
