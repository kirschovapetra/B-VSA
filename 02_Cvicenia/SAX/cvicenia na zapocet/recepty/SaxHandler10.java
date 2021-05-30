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
Nájdite najnovšiu rozprávku na netflixe
 */
public class SaxHandler10 extends DefaultHandler {

    String text,currentStream,currentName, currentZaner,newestZaner;
    Boolean film = false;
    Integer maxYear = 0, currentYear;
    String newestName;
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals("film")) {
            film = true;
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = new String(ch,start,length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (film && localName.equals("meno")) {
            currentName = text;
        }
        if (!film && localName.equals("meno")) {
            currentStream = text;
        }
        if (localName.equals("zaner")) {
            currentZaner = text;
        }
        if (localName.equals("rok")) {
            currentYear = Integer.parseInt(text);
        }
        
        if (localName.equals("film")) {
            if (currentName != null && currentYear != null && currentZaner != null) {
                if (currentYear > maxYear && currentZaner.equals("rozpravka")){
                    maxYear = currentYear;
                    newestName = currentName; 
                    newestZaner = currentZaner;
                }
            }
            film = false;
            currentName = null;
            currentZaner = null;
            currentYear = null;
        }
        if (localName.equals("streamSluzba")) {
            if (newestName == null) 
                System.out.println("nenasla sa rozpravka");
            else if (currentStream.equals("HBO GO")) {
                System.out.println(currentStream + ": "+newestZaner+" "+newestName + " "+maxYear);
            }
            maxYear = 0;
            newestName = null;
            newestZaner = null;
        }
    }

    
    
}
