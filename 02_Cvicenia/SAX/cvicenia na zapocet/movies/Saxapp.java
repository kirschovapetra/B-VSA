/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxapp;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;


public class Saxapp {

   
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        SAXParser saxParser= spf.newSAXParser();

        //4. Nájdite dostupnú fantasy na HBO GO
        SaxHandler4 sh4 = new SaxHandler4();
        saxParser.parse("/media/sf_2020/cvika/movies/saxapp/src/saxapp/movies.xml", sh4);
        
        /*
        //1. Nájdite názov streamovacej služby s najstarším filmom
        SaxHandler1 sh1 = new SaxHandler1();
        saxParser.parse("/media/sf_2020/cvika/movies/saxapp/src/saxapp/movies.xml", sh1);
        
        //2. Nájdite film, ktorý nemá určenú dostupnosť
        SaxHandler2 sh2 = new SaxHandler2();
        saxParser.parse("/media/sf_2020/cvika/movies/saxapp/src/saxapp/movies.xml", sh2);
        
        //3. Nájdite najnovšiu rozprávku na netflixe
        SaxHandler3 sh3 = new SaxHandler3();
        saxParser.parse("/media/sf_2020/cvika/movies/saxapp/src/saxapp/movies.xml", sh3);
        
        //4. Nájdite dostupnú fantasy na HBO GO
        SaxHandler4 sh4 = new SaxHandler4();
        saxParser.parse("/media/sf_2020/cvika/movies/saxapp/src/saxapp/movies.xml", sh4);
        */
          
    }
    
}
