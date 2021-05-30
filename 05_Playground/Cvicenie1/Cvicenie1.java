package cvicenie1;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import pkg.MyHandler;

public class Cvicenie1 {
    
    private SAXParserFactory spf;
    private SAXParser saxParser;
   

    public SAXParserFactory getSpf() {
        return spf;
    }

    public void setSpf(SAXParserFactory spf) {
        this.spf = spf;
    }

    public SAXParser getSaxParser() {
        return saxParser;
    }

    public void setSaxParser(SAXParser saxParser) {
        this.saxParser = saxParser;
    }
    
    public Cvicenie1() throws ParserConfigurationException, SAXException {
        spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);   
        saxParser = spf.newSAXParser();     
       
    }

    public void parse() throws SAXException, IOException {
        saxParser.parse("/media/sf_2020/cvika/SAX cvicenia/Cvicenie1/src/cvicenie1/adresar.xml", new MyHandler());     
        

    }

    public static void main(String[] args) {
        try {
            Cvicenie1 cv1 = new Cvicenie1();
            cv1.parse();
            
            
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Cvicenie1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Cvicenie1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cvicenie1.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
      
}
