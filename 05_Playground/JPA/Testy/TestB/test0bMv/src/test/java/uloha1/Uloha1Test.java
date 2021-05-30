
package uloha1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import static uloha1.Uloha1.load;


public class Uloha1Test {
    
    public Uloha1Test() throws ClassNotFoundException {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
    }
    
    @Test
    public void testLoad1() throws SQLException {
        String[] args = new String[1];
        resetTables(); 
        try {
            load("src/test/csv/data1.csv"); 
         } catch (Exception ex) {
            Logger.getLogger(Uloha1Test.class.getName()).log(Level.SEVERE, ex.getMessage());
            fail("function execution failed");
        }
        checkModifications1();
             
    }

    @Test
    public void testLoad2() throws SQLException {
        String[] args = new String[1];
//        resetTables(); 
        try {
            load("src/test/csv/data2.csv"); 
         } catch (Exception ex) {
            Logger.getLogger(Uloha1Test.class.getName()).log(Level.SEVERE, ex.toString());
            ex.printStackTrace();
            fail("function execution failed");
        }
        checkModifications2();
             
    }

    private void resetTables() {
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app")) {
            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM TOVAR");            
        } catch (SQLException ex) {
            Logger.getLogger(Uloha1Test.class.getName()).log(Level.INFO, ex.getMessage());
        }
    }

    private void checkModifications1() throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app")) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT count(*) FROM TOVAR");

            assertTrue("next failed", rs.next());
            assertTrue("count == " + rs.getInt(1), rs.getInt(1)==4);
        }
    }
    
    private void checkModifications2() throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app")) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT count(*) FROM TOVAR");

            assertTrue("next failed", rs.next());
            assertTrue("count == " + rs.getInt(1), rs.getInt(1)==6);
        }
    }
}
