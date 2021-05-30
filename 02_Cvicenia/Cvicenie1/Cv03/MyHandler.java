/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv03;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/*
1.Implementujte program, ktorý s využitím SAX parsera číta XML súbor 
s kuchárskymi receptami a vypíše názvy všetkých receptov, ktoré obsahujú múku.

2.Implementujte program, ktorý s využitím SAX parsera číta XML súbor 
s kuchárskymi receptami a zistí aké množstvo mlieka ide do omelety; 
na obrazovku vypíše množstvo aj jednotku.
*/

public class MyHandler extends DefaultHandler{

    String recipeName, text, quantity, unit, itemName, toPrint;
    boolean isItem = false, isFlour = false, isOmeleta = false;
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = new String(ch,start,length);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        
        
        if (qName.equals("polozka"))
            isItem = true;
        
        
        if (qName.equals("mnozstvo")) {
            for (int i=0; i< attributes.getLength(); i++){
                if ((attributes.getQName(i)).equals("jednotka")) {
                    unit = attributes.getValue(i);
                }
            }
        }
    }
    
    //1.názvy všetkých receptov, ktoré obsahujú múku
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("nazov") && !isItem) 
           recipeName = text;

        if (qName.equals("nazov") && isItem && text.equals("muka"))
            isFlour = true;
        
        if (qName.equals("polozka"))
            isItem = false;

        if (qName.equals("recept") && isFlour) {
            System.out.println(recipeName);
            isFlour = false;
            recipeName = null;
        }
    }
    
    
    //2. aké množstvo mlieka ide do omelety - množstvo aj jednotku
    
    /*
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        
        if (qName.equals("nazov") && !isItem) 
           recipeName = text;
            
        if (isItem) {
            if (qName.equals("nazov"))
                itemName = text;
            if (qName.equals("mnozstvo"))
                quantity = text;
        }
        
        if (qName.equals("polozka")){
            isItem = false;
                  
            if (itemName.equals("mlieko")) {
                toPrint = itemName +" "+ quantity + " " + unit;
            }
        }

        if (qName.equals("recept") && recipeName.equals("omeleta")) {
            System.out.println(toPrint);
            recipeName = null;
        }
        
    }
    */

}
