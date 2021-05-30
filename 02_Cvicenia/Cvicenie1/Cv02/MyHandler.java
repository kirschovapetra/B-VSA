/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv02;

import java.util.LinkedList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/*
1.vypíšte mená všetkých osôb
2.vypíšte mená všetkých žien
3.vypíšte mená všetkých miest (bydlisko) v ktorých niekto býva, bez opakovania.
4.zistite či v súbore nie sú dve osoby s rovnakým menom.
*/


public class MyHandler extends DefaultHandler{

    String text;
    List<String> houses;
    List<String> names;
    boolean isPerson = false, isWoman = false, isDuplicite = false;

    public MyHandler() {
        this.houses = new LinkedList<>();
        this.names = new LinkedList<>();
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = (new String(ch,start,length)).trim();
       
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("ns0:osoba"))
            isPerson = true;
        for (int i = 0; i < attributes.getLength(); i++) {
            if (attributes.getValue(i).equals("zena"))
                isWoman = true;  
        }
    }


    //1. vypis vsetkych osob
    /*@Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        
        if (qName.equals("ns0:meno") && isPerson) { 
            System.out.println(text);
            isPerson = false;
  
        }
    }*/
    
    //2. vypis vsetkych zien
    /*
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        
        if (qName.equals("ns0:meno") && isPerson && isWoman) { 
            System.out.println(text);
            isPerson = false;
            isWoman = false;
  
        }
    }
    */
    
    //3.vypis vsetkych bydlisk v ktorých niekto býva, bez opakovania.
    /*
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("ns0:bydlisko") && isPerson && qName.equals("ns0:bydlisko")) { 
            if (!houses.contains(text)){
                houses.add(text);
                System.out.println(text);
                isPerson = false;
            }
        }
    }   
    */
    
    //4.zistit ci v nie sú osoby s rovnakym menom
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        
        if (qName.equals("ns0:osoba"))
            isPerson = false;
        
        
        if (!isDuplicite){
        
            if (qName.equals("ns0:meno") && isPerson) { 
                //System.out.println("TENTO CLOVEK:"+text+" "+isPerson); 
                if (names.contains(text)){
                    isDuplicite = true;
                    System.out.println("OBSAHUJE DUPLICITNE:"+text);   
                }
                else
                    names.add(text);
                }
        }
    }  
    
}
