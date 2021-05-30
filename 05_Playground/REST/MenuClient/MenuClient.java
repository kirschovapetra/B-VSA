/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menuclient;

/**
 *
 * @author vsa
 */
public class MenuClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NewJerseyClient a = new NewJerseyClient();
        
        System.out.println(a.getMenu(String.class));
        a.deleteItem("PON", "1");
        System.out.println("__________\n");
        System.out.println(a.getMenu(String.class));
    }
    
}
