/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv02;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;


public class Cv02 {

    public static void main(String[] args) throws IOException {

      try {
         
         SAXParserFactory factory = SAXParserFactory.newInstance();
         SAXParser saxParser = factory.newSAXParser();
         
         saxParser.parse("adresar.xml", new MyHandler());
         
      } catch (ParserConfigurationException | SAXException e) {
          System.out.println(e.getMessage());
      }
   }   
}