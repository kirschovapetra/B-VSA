/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slovnikclient;

/**
 *
 * @author vsa
 */
public class SlovnikClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AbcdClient brutalneDlhyNazov = new AbcdClient();
        
        brutalneDlhyNazov.deleteSlovnik("sk");
        System.out.println(brutalneDlhyNazov.getText("sk", "hello"));
        System.out.println(brutalneDlhyNazov.getText("cz", "hello"));
        
        brutalneDlhyNazov.deleteSlovo("cz","hello");
        System.out.println(brutalneDlhyNazov.getText("cz", "hello"));
    }
    
}
