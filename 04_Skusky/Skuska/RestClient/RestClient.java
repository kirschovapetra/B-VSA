/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restclient;

/**
 *
 * @author vsa
 */
public class RestClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NewJerseyClient client = new NewJerseyClient();
        
//      1. zistí, v ktorý deň je skúška z predmetu OOP a vypíše deň na štandardný výstup.
        Skuska skuskaOOP = client.getSkuska(Skuska.class, "OOP");
        System.out.println(skuskaOOP.getDen());
        
//      2. následne prihlási jedného študenta na skúšku z OOP. Ako meno študenta zadajte vaše ID-študenta.
        client.postStudent("92222","OOP");
        
//      3. nakoniec zistí počet prihlásených študentov na predmet OOP a vypíše ich na štandardný výstup.
        System.out.println(client.getPocetStudentov(Integer.class, "OOP"));      
    }
    
}
