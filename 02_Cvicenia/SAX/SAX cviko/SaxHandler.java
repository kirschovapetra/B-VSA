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
public class SaxHandler extends DefaultHandler{
    private String text;
    private String maxBook = "neexistuje";
    private Double maxPrice = -1.0;
    private String currBook;
    private Double currPrice = -1.0;
    
    private Boolean knihkupectvo = false;
    private Boolean stopy = false;
    private Boolean kniha = false;
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals("knihkupectvo"))
            knihkupectvo = true;
        if (localName.equals("kniha") )
            kniha = true;
        if (attributes.getValue("edicia") != null && attributes.getValue("edicia").equals("Stopy"))
            stopy = true;
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = new String(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
  
        if (knihkupectvo && kniha && stopy) {
            if (localName.equals("meno")){
                currBook = text;
            }
            if (localName.equals("cena")){
                currPrice = Double.parseDouble(text);
            }
            
        } 

        if (localName.equals("kniha")){
            stopy = false;
            kniha = false;
            if (currPrice > maxPrice) {
                maxPrice = currPrice;
                maxBook = currBook;
            }
        }
       
        if (localName.equals("knihkupectvo")){
            knihkupectvo = false;
            System.out.println(maxBook);
        }
    }  
}
