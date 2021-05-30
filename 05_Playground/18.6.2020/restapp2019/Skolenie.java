package rest;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Skolenie {
    private String info = "ZIADNE";
    private List<String> ucastnik = new ArrayList<>();


    public List<String> getUcastnik() {
        return ucastnik;
    }

    public void setUcastnik(List<String> ucastnik) {
        this.ucastnik = ucastnik;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
}
