
package saxapp;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/*
vypisat recept s maximalnym poctom poloziek
 */
public class SaxHandler4 extends DefaultHandler{
    
    String currentRecipe, maxRecipe, text;
    Integer currentCount = 0, maxCount = 0;
    
    Boolean polozka = false;
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals("recept")) {
            currentCount = 0;
        }
        
        
        if (localName.equals("polozka")) {
            polozka = true;
        }
    }
 
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = new String(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equals("polozka")) {
            currentCount++;
            polozka = false;
        }

        if (!polozka && localName.equals("nazov")) {
            currentRecipe = text;
        }
        
        
        if (localName.equals("recept")) {
            if (currentCount > maxCount) {
                maxRecipe = currentRecipe;
                maxCount = currentCount;
            }
        }
        
        
        if (localName.equals("receptar")) {
            System.out.println(maxRecipe + " " + maxCount);

        }
    }
    
    
}
