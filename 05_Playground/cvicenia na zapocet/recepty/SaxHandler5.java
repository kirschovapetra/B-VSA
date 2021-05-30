
package saxapp;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/*
vypisat popis a nazov receptov ktore obsahuju muku alebo mlieko aj + co z toho obsahuje
ked nema popis tak napisat nema popis
 */
public class SaxHandler5 extends DefaultHandler{
    
    String text, currentRecipe, currentPopis, currentUnit, mliekoUnit, mukaUnit;
    Double currentAmount = 0.0, mliekoAmount = 0.0, mukaAmount = 0.0;
    Boolean polozka = false, polozkaSMukou = false, polozkaSMliekom = false, receptSMukou = false, receptSMliekom = false;
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
          
        if (localName.equals("polozka")) {
            polozka = true;
        }
        
        if (localName.equals("mnozstvo")) {
            currentUnit = attributes.getValue("jednotka");
        }
    }
 
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = new String(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        
        if (localName.equals("polozka")) {
            polozka = false;
            
            if (polozkaSMukou) {
                mukaAmount = currentAmount;
                mukaUnit = currentUnit;
                
            }
            else if (polozkaSMliekom) {
                mliekoAmount = currentAmount;
                mliekoUnit = currentUnit;
            }
            
            polozkaSMukou = false;
            polozkaSMliekom = false;
            currentUnit = null;
            currentAmount = 0.0;
        }
        
        if (localName.equals("popis")) {
            currentPopis = text;
        }
        if (localName.equals("mnozstvo")) {
            currentAmount = Double.parseDouble(text);
        }
        
        if (!polozka && localName.equals("nazov")) {
            currentRecipe = text;
        }
        
        if (polozka && localName.equals("nazov")) {
            if (text.equals("muka")) {
                polozkaSMukou = true;
                receptSMukou = true;
            }
            if (text.equals("mlieko")) {
                polozkaSMliekom = true;
                receptSMliekom = true;
            }
            
        }
        
        
        if (localName.equals("recept")){
            if (currentPopis == null)
                currentPopis = "nema popis";
            
            System.out.println(currentRecipe + ": " + currentPopis);
            if (receptSMukou) {
                System.out.println("muka: "+mukaAmount + " "+ mukaUnit);
            }
            if (receptSMliekom) {
                System.out.println("mlieko: "+mliekoAmount + " "+mliekoUnit);
            }
            System.out.println("\n");
            currentPopis = null;
            receptSMukou = false;
            receptSMliekom = false;
            mliekoAmount = 0.0;
            mukaAmount = 0.0;
            mliekoUnit = null;
            mukaUnit = null;
        }
    }
    
    
}
