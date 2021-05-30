/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxapp;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

// 1. Nájdite názov streamovacej služby s najstarším filmom
public class SaxHandler1 extends DefaultHandler {
    private String text;
    
    private String currentStream;
    private Integer currentYear = Integer.MAX_VALUE;
    
    private String minStream = "neexistuje";
    private Integer minYear = Integer.MAX_VALUE;   
    
    private boolean isStream = false;
    private boolean isMovie = false; 
    private boolean changedYear = false; 
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals("streamSluzba")){
            isStream = true;
        }
        if (localName.equals("film")){
            isMovie = true;
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = new String(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        
        if (localName.equals("rok")) {
            currentYear = Integer.parseInt(text);
        }
        if (isStream && !isMovie) {
            if (localName.equals("meno")) {
                currentStream = text;
            }
        }
        if (localName.equals("film")){
            isMovie = false;
            if (currentYear < minYear) {
                minYear = currentYear;
                changedYear = true;
                
            }
 
        }  
        
        if (localName.equals("streamSluzba")){
            isStream = false;
            if (changedYear) {
                minStream = currentStream;
            }
            changedYear = false;
        }
        
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println(minStream);
    }
    
    

}
