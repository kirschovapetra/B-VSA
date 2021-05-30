/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prednaska3;

import entities.Kniha;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static prednaska3.Stav.*;
import prednaska3.DBManager;

/**
 *
 * @author vsa
 */
public class Prednaska3 {

    
        
        
    public static void main(String[] args) {
        DBManager dbm = new DBManager();
        dbm.addBooks();
    }

    
    
    
    
}
