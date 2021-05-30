/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxapp;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//4. Nájdite dostupnú fantasy na HBO GO
public class SaxHandler4 extends DefaultHandler {

    String text,streamName,movieName;
    Boolean inFilm = false,jeFantasy = false,available = false;
    List<String> fantasy;
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals("film")){
            inFilm = true;
        }
        if (localName.equals("streamSluzba")){
            fantasy = new ArrayList<>();
        }
        if (inFilm && attributes.getValue("dostupnost") != null && attributes.getValue("dostupnost").equals("ano")){
            available = true;
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = new String(ch,start,length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
       
        if (!inFilm && localName.equals("meno")) {
            streamName = text;   
        }
        
        if (inFilm && localName.equals("meno")) {
            movieName = text;
        }
        
        if (localName.equals("zaner") && text.equals("fantasy")) {
            jeFantasy = true;
        }
        
        if (localName.equals("film")){
            if (jeFantasy && available) {
                fantasy.add(movieName);
            }

            inFilm = false;
            jeFantasy = false;
            available = false;
        }
        
        if (localName.equals("streamSluzba")){
            if (streamName.equals("HBO GO")) {
                System.out.println(fantasy);
            }
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
