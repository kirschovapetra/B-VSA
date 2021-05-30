
package pkg;

import java.util.HashSet;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



//1. mena osob
/*public class MyHandler extends DefaultHandler{
    private String text;
    private String startTag;
    private String endTag;
    private String name;
    private Boolean isPerson = false;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyHandler() {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        startTag = localName;
        if (startTag.equals("osoba")){
            isPerson = true;
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = new String(ch,start,length);
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        endTag = localName;
        if (endTag.equals("osoba")){
            isPerson = false;
        }
          if (endTag.equals("meno")){
            name = text;
            System.out.println(name);
        }
    }
    
    
}*/

//2. mena zien
/*public class MyHandler extends DefaultHandler{
    private String text;
    private String startTag;
    private String endTag;
    private String name;
    
    private Boolean isPerson = false;
    private Boolean isWoman = false;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyHandler() {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        startTag = localName;
        if (startTag.equals("osoba")){
            isPerson = true;
        }
        
        if (attributes.getValue("gen") != null){
            
            if (attributes.getValue("gen").equals("zena"))
                isWoman = true;
        }
  
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = new String(ch,start,length);
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        endTag = localName;
        if (endTag.equals("osoba")){
            isPerson = false;
        }
        
        if (endTag.equals("meno") && isWoman){
            name = text;
            System.out.println(name);
            isWoman = false;
        }
          
      } 
}*/

//3. všetkých miest (bydlisko) v ktorých niekto býva, bez opakovania.
/*public class MyHandler extends DefaultHandler{
    private String text;
    private String startTag;
    private String endTag;
    private String name;
    private Integer count; 

    private HashSet<String>places;
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyHandler() {
        places = new HashSet<>();
    }
    

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        startTag = localName;
  
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = new String(ch,start,length);
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        endTag = localName;
       if (endTag.equals("bydlisko")){
           places.add(text);
       }
       
       if (endTag.equals("adresar")) {
           System.out.println(places);
       }
      } 
}*/

//duplicitne mena
public class MyHandler extends DefaultHandler{
    private String text;
    private String startTag;
    private String endTag;
    private HashSet<String> names;
    private HashSet<String> duplicateNames;
   
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MyHandler() {
        names = new HashSet<>();
        duplicateNames = new HashSet<>();
    }
    

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        startTag = localName;
  
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = new String(ch,start,length);
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        endTag = localName;
       if (endTag.equals("meno")){
           if (names.contains(text)) {
               duplicateNames.add(text);
           }
           else{
               names.add(text);
           }
           
       }
       
       if (endTag.equals("adresar")) {
           System.out.println(duplicateNames);
       }
      } 
}
