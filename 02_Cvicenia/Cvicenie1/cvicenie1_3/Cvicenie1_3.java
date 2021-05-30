
package cvicenie1_3;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;


public class Cvicenie1_3 {

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
    
    public Cvicenie1_3() throws ParserConfigurationException, SAXException {
        spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);   
        saxParser = spf.newSAXParser();     
       
    }

    public void parse() throws SAXException, IOException {
        saxParser.parse("/media/sf_2020/cvika/SAX cvicenia/Cvicenie1_3/src/cvicenie1_3/receptar.xml", new MyHandler2());     

    }
    public static void main(String[] args) {
       
        try {
            Cvicenie1_3 cv1 = new Cvicenie1_3();
            cv1.parse();
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(Cvicenie1_3.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
