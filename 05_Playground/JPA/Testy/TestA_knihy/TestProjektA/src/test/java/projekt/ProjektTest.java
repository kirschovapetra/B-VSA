/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author igor
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjektTest {

    private static ResultSet rs = null;
    private static String db = "jdbc:derby://localhost:1527/sample";

    @BeforeClass
    public static void setUpClass() throws ClassNotFoundException {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
    }

    @Test
    public void UT01_NovaKniha_2B() {
        prepareTables();

        Boolean r = null;
        try {
            Projekt proj = new Projekt();
            r = proj.aktualizujKnihu("N01", "nova", 20.0);

        } catch (Exception e) {
            fail("CHYBA EX: " + e.getMessage());
        }
        assertEquals(true, r);
    }

    @Test
    public void UT02_NovaKnihaCheck_2B() {
        long cnt = -1;
        String nazov = null;
        double cena = Double.NaN;
        try (Connection con = DriverManager.getConnection(db, "app", "app")) {
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT count(*) FROM KNIHA");
            rs.next();
            cnt = rs.getLong(1);

            Statement st2 = con.createStatement();
            rs = st2.executeQuery("SELECT NAZOV, CENA FROM KNIHA WHERE ISBN='N01'");
            rs.next();
            nazov = rs.getString("NAZOV");
            cena = rs.getDouble("CENA");
        } catch (SQLException ex) {
            fail("CHYBA SQL: " + ex.getMessage());
        }

        assertEquals(3, cnt);
        assertEquals("nova", nazov);
        assertEquals(20.0, cena, 0.001);
    }

    @Test
    public void UT03_NovaCena_2B() {
        prepareTables();

        Boolean r = null;
        try {
            Projekt proj = new Projekt();
            r = proj.aktualizujKnihu("K00", "stara", 30.0);
        } catch (Exception e) {
            fail("CHYBA EX: " + e.getMessage());
        }
        assertEquals(true, r);
    }

    @Test
    public void UT04_NovaCenaCheck_1B() {
        long cnt = -1;
        double cena = Double.NaN;
        try (Connection con = DriverManager.getConnection(db, "app", "app")) {
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT count(*) FROM KNIHA");
            rs.next();
            cnt = rs.getLong(1);

            Statement st2 = con.createStatement();
            rs = st2.executeQuery("SELECT CENA FROM KNIHA WHERE ISBN='K00'");
            rs.next();
            cena = rs.getDouble("CENA");
        } catch (SQLException ex) {
            fail("CHYBA SQL: " + ex.getMessage());
        }

        assertEquals(2, cnt);
        assertEquals(30.0, cena, 0.001);
    }

    @Test
    public void UT05_NovyNazovCena_1B() {
        prepareTables();

        Boolean r = null;
        try {
            Projekt proj = new Projekt();
            r = proj.aktualizujKnihu("K01", "kniha1", 30.0);
        } catch (Exception e) {
            fail("CHYBA EX: " + e.getMessage());
        }
        assertEquals(true, r);
    }

    @Test
    public void UT06_NovyNazovCenaCheck_1B() {
        long cnt = -1;
        String nazov = null;
        double cena = Double.NaN;
        try (Connection con = DriverManager.getConnection(db, "app", "app")) {
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT count(*) FROM KNIHA");
            rs.next();
            cnt = rs.getLong(1);

            Statement st2 = con.createStatement();
            rs = st2.executeQuery("SELECT NAZOV, CENA FROM KNIHA WHERE ISBN='K01'");
            rs.next();
            nazov = rs.getString("NAZOV");
            cena = rs.getDouble("CENA");
        } catch (SQLException ex) {
            fail("CHYBA SQL: " + ex.getMessage());
        }

        assertEquals(2, cnt);
        assertEquals("kniha1", nazov);
        assertEquals(30.0, cena, 0.001);
    }

    @Test
    public void UT07_NespravnyNazov_2B() {
        prepareTables();
        Boolean r = null;
        try {
            Projekt proj = new Projekt();
            r = proj.aktualizujKnihu("K00", "zly nazov", 30.0);
        } catch (Exception e) {
            fail("CHYBA EX: " + e.getMessage());
        }
        assertEquals(false, r);
    }

    @Test
    public void UT08_ZiadneUdaje_1B() {

        Boolean r = null;
        try {
            Projekt proj = new Projekt();
            r = proj.aktualizujKnihu("K01", null, null);
        } catch (Exception e) {
            fail("CHYBA EX: " + e.getMessage());
        }
        assertEquals(true, r);

        double cena = Double.NaN;

        try (Connection con = DriverManager.getConnection(db, "app", "app")) {
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT CENA FROM KNIHA WHERE ISBN='K01'");
            rs.next();
            cena = rs.getDouble("CENA");
        } catch (SQLException ex) {
            fail("CHYBA SQL: " + ex.getMessage());
        }

        // ziadne zmeny
        assertEquals(10.0, cena, 0.001);
    }

    @Test
    public void UT09_NovyCennik_1B() {
        prepareTables2();

        Boolean r = null;
        try {
            Projekt proj = new Projekt();
            Map<String, Double> cennik = new HashMap<>();
            cennik.put("K11", 20.0);
            cennik.put("K12", 30.0);
            proj.aktualizujCeny(cennik);
        } catch (Exception e) {
            fail("CHYBA EX: " + e.getMessage());
        }
    }

    @Test
    public void UT10_NovyCennikCheck_2B() {
        long cnt = -1;
        String nazov = null;
        double c0 = Double.NaN;
        double c1 = Double.NaN;
        double c2 = Double.NaN;
        try (Connection con = DriverManager.getConnection(db, "app", "app")) {
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT count(*) FROM KNIHA");
            rs.next();
            cnt = rs.getLong(1);

            st = con.createStatement();
            rs = st.executeQuery("SELECT CENA FROM KNIHA WHERE ISBN='K10'");
            rs.next();
            c0 = rs.getDouble("CENA");
            st = con.createStatement();
            rs = st.executeQuery("SELECT CENA FROM KNIHA WHERE ISBN='K11'");
            rs.next();
            c1 = rs.getDouble("CENA");
            st = con.createStatement();
            rs = st.executeQuery("SELECT CENA FROM KNIHA WHERE ISBN='K12'");
            rs.next();
            c2 = rs.getDouble("CENA");
        } catch (SQLException ex) {
            fail("CHYBA SQL: " + ex.getMessage());
        }

        assertEquals(3, cnt);
        assertEquals(10.0, c0, 0.001);
        assertEquals(20.0, c1, 0.001);
        assertEquals(30.0, c2, 0.001);
    }

    @Test
    public void UT11_DalsiCennik_1B() {
        prepareTables2();

        Boolean r = null;
        try {
            Projekt proj = new Projekt();
            Map<String, Double> cennik = new HashMap<>();
            cennik.put("K10", 20.0);    // stara kniha s nazvom
            cennik.put("N10", 20.0);    // nova kniha bez nazvu
            proj.aktualizujCeny(cennik);
        } catch (Exception e) {
            fail("CHYBA EX: " + e.getMessage());
        }

        long cnt = -1;
        String nazov = null;
        try (Connection con = DriverManager.getConnection(db, "app", "app")) {
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT count(*) FROM KNIHA");
            rs.next();
            cnt = rs.getLong(1);

        } catch (SQLException ex) {
            fail("CHYBA SQL: " + ex.getMessage());
        }

        assertEquals(4, cnt);

    }

    @Test
    public void UT12_NovaKnihaCheck_1B() {
        long cnt = -1;
        String nazov = null;
        double c = Double.NaN;
        try (Connection con = DriverManager.getConnection(db, "app", "app")) {
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT count(*) FROM KNIHA");
            rs.next();
            cnt = rs.getLong(1);

            st = con.createStatement();
            rs = st.executeQuery("SELECT CENA, NAZOV FROM KNIHA WHERE ISBN='N10'");
            rs.next();
            c = rs.getDouble("CENA");
            nazov = rs.getString("NAZOV");
        } catch (SQLException ex) {
            fail("CHYBA SQL: " + ex.getMessage());
        }

        assertEquals(20.0, c, 0.001);
        assertNull(nazov);
    }

    @Test
    public void UT13_StaraKnihaCheck_1B() {
        long cnt = -1;
        String nazov = null;
        double c = Double.NaN;
        try (Connection con = DriverManager.getConnection(db, "app", "app")) {
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT count(*) FROM KNIHA");
            rs.next();
            cnt = rs.getLong(1);

            st = con.createStatement();
            rs = st.executeQuery("SELECT CENA, NAZOV FROM KNIHA WHERE ISBN='K10'");
            rs.next();
            c = rs.getDouble("CENA");
            nazov = rs.getString("NAZOV");
        } catch (SQLException ex) {
            fail("CHYBA SQL: " + ex.getMessage());
        }

        assertEquals(20.0, c, 0.001);
        assertEquals("stara", nazov);
    }

    static private void prepareTables() {
        try (Connection con = DriverManager.getConnection(db, "app", "app")) {
            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM KNIHA");
            st.executeUpdate("INSERT INTO KNIHA (ISBN, CENA, NAZOV) VALUES ('K00', 10.0, 'stara' )");
            st.executeUpdate("INSERT INTO KNIHA (ISBN, CENA, NAZOV) VALUES ('K01', 10.0, NULL )");
            con.commit();
//            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProjektTest.class.getName()).log(Level.SEVERE, ex.getMessage());
        }
    }

    static private void prepareTables2() {
        try (Connection con = DriverManager.getConnection(db, "app", "app")) {
            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM KNIHA");
            st.executeUpdate("INSERT INTO KNIHA (ISBN, CENA, NAZOV) VALUES ('K10', 10.0, 'stara' )");
            st.executeUpdate("INSERT INTO KNIHA (ISBN, CENA, NAZOV) VALUES ('K11', 10.0, NULL )");
            st.executeUpdate("INSERT INTO KNIHA (ISBN, CENA, NAZOV) VALUES ('K12', 10.0, NULL )");
            con.commit();
//            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProjektTest.class.getName()).log(Level.SEVERE, ex.getMessage());
        }
    }

}
