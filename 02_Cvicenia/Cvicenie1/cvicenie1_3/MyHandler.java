
package cvicenie1_3;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/*
2. Implementujte program, ktorý s využitím SAX parsera číta XML súbor s kuchárskymi 
receptami a zistí aké množstvo mlieka ide do omelety; na obrazovku vypíše 
množstvo aj jednotku. 
*/

/*
1. Implementujte program, ktorý s využitím SAX parsera číta XML súbor s kuchárskymi 
receptami a vypíše názvy všetkých receptov, ktoré obsahujú múku.
*/
public class MyHandler extends DefaultHandler {
    private String recipeName;
    private String itemName;
    private Boolean isFlour = false;
    private Boolean isRecipe = false;
    private Boolean isItem = false;
    private String text;
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
       String startTag = localName;
       if (startTag.equals("recept")) {
           isRecipe = true;
       }
       if (startTag.equals("polozka")) {
           isItem = true;
       }
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = new String(ch,start,length);
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String endTag = localName;

        if (endTag.equals("polozka")) {
            //System.out.println(itemName);
            isItem = false;
            
        }
       
        if (endTag.equals("nazov")) {
            if (!isItem) {
                recipeName = text;
            }
            else {
                itemName = text;
                if (itemName.equals("muka")) {
                    isFlour = true;
                }
            }
        }
       if (endTag.equals("recept") && isFlour) {
           System.out.println("RECEPT:"+recipeName);
           isRecipe = false;
           isFlour = false;
        }
       
    }
    
}


