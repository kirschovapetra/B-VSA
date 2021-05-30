/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvicenie1_3;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//zistí aké množstvo mlieka ide do omelety; na obrazovku vypíše množstvo aj jednotku.
public class MyHandler2 extends DefaultHandler{

    private String text;
    private String recipeName;
    private String itemName;
    private Double amount,milkAmount;
    private Boolean isRecipe = false;
    private Boolean isItem = false;
    private Boolean isMilk = false;
    private Boolean isOmeleta = false;
    private String unit,milkUnit;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        String startTag = localName;
        if (startTag.equals("recept")) {
            isRecipe = true;
        }
        if (startTag.equals("polozka")) {
            isItem = true;
        }
        if (startTag.equals("mnozstvo") && attributes.getValue("jednotka") != null) {
            unit = attributes.getValue("jednotka");
        }
    }
    
   
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = new String(ch,start,length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String endTag = localName;
        if (endTag.equals("recept")){
            isOmeleta = false;
            isRecipe = false;
        }
        
        if (endTag.equals("polozka") && isOmeleta && isMilk){
            System.out.println(amount + " "+unit);
            isItem = false;
            isMilk = false;
        }
        
        if (endTag.equals("mnozstvo")){
            amount = Double.parseDouble(text);
        }
        
        if (endTag.equals("nazov") && isRecipe){
            recipeName = text;
            if (recipeName.equals("omeleta")){
                isOmeleta = true;
            }
        }
        if (endTag.equals("nazov") && isItem) {
            itemName = text;
            if (itemName.equals("mlieko")){
                isMilk = true;
            }
        }
    }
 
}
