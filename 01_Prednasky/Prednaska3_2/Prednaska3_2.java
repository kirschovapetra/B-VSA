/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prednaska3_2;

/**
 *
 * @author vsa
 */
public class Prednaska3_2 {


    
    public static void main(String[] args) {
        DBManager dbm = new DBManager();
        dbm.addBooks();
        dbm.findBooksById(3L);
        dbm.findBooksByIdCreateQuery(2L);
        dbm.updateWithFind(2L);
        dbm.findBooksByIdCreateQuery(2L);
        
        dbm.findBooksByIdCreateQuery(1L);
        dbm.mergeBooks(1L, "MERGED");
        dbm.findBooksByIdCreateQuery(1L);
        
    }
    
}
