/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prednaska2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author vsa
 */
public class Prednaska2 {

    List<Kniha> knihy = new LinkedList<>();
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample","app","app");
        
        ResultSet rs = con.createStatement().executeQuery("select * from kniha");
        
        while (rs.next() == true) {
            String nazov = rs.getString(1);
            double cena  = rs.getDouble(2);
            
            Kniha k = new Kniha(nazov, cena);
            //knihy.add(k);
            System.out.println(k.toString());
        }
    }
    
}
