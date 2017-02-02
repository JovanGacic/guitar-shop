  
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import domen.OpstiDomenskiObjekat;

/**
 *
 * @author Jovan
 */
public class DatabaseBroker {
    private Connection connection;
    private static DatabaseBroker instance;
    
    private DatabaseBroker() {
        
    }
    
    public static DatabaseBroker getInstance() {
        if (instance == null) {
            instance = new DatabaseBroker();
        }
        return instance;
    }
    
    public void ucitajDriver() throws Exception {
        try {
            Class.forName(Util.getInstace().get("driver"));
        } catch (ClassNotFoundException ex) {
            throw new Exception("Neuspesno ucitavanje drivera!", ex);
        }
    }
    
    public void otvoriKonekciju() throws Exception {
        try {
            connection = DriverManager.getConnection(Util.getInstace().get("url"), Util.getInstace().get("user"), Util.getInstace().get("password"));
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Neuspesno uspostavanje konekcije!", ex);
        }
        
    }
    
    public void zatvoriKonekciju() throws Exception {
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new Exception("Neuspesno zatvaranje konekcije!", ex);
        }
    }
    

    
    public void potvrdiTransakciju() throws Exception{
        try {
            connection.commit();
        } catch (SQLException ex) {
            throw new Exception("Neuspresno potvrdjivanje transakcije!",ex);
        }
    }
    
    public void ponistiTransakciju() throws Exception{
        try {
            connection.rollback();
        } catch (SQLException ex) {
            throw new Exception("Neuspresno ponistavanje transakcije!",ex);
        }
    }
    
    public synchronized List<OpstiDomenskiObjekat> vratiListu(OpstiDomenskiObjekat odo) throws Exception {
        try {
            List<OpstiDomenskiObjekat> list;
            String sql = "SELECT * FROM " + odo.vratiNazivTabele()+""+odo.vratiTabeluSaJoin();
            System.out.println(sql);
            Statement sqlStatement = connection.createStatement();
            ResultSet rs = sqlStatement.executeQuery(sql);
            list = odo.napuni(rs);
            rs.close();
            sqlStatement.close();
            return list;
        } catch (SQLException ex) {
            throw new Exception("Neuspesno ucitavanje objekata!", ex);
        }
    }
    
    public synchronized void sacuvaj(OpstiDomenskiObjekat odo) throws Exception {
        try {
             String sql = "INSERT INTO " + odo.vratiNazivTabele() + " VALUES (" + odo.vratiInsertVrednosti() + ")";
            System.out.println(sql);
            Statement sqlStatement = connection.createStatement();
            sqlStatement.executeUpdate(sql);
            sqlStatement.close();
        } catch (SQLException ex) {
            throw new Exception("Neuspesno cuvanje objekta!", ex);
        }
    }
    
    public synchronized OpstiDomenskiObjekat pronadji(OpstiDomenskiObjekat odo)throws Exception {
        try {
            String sql = "SELECT * FROM " + odo.vratiNazivTabele() +""+odo.vratiTabeluSaJoin()+ " WHERE " + odo.vratiUslovSaIdentifikatorom();
            System.out.println(sql);
            Statement sqlStatement = connection.createStatement();
            ResultSet rs = sqlStatement.executeQuery(sql);
            OpstiDomenskiObjekat odo1 = odo.konvertuj(rs);
            sqlStatement.close();
            return odo1;
        } catch (SQLException ex) {
            throw new Exception("Neuspesno ucitavanje objekta!", ex);
        }
    }
    
    public synchronized void izmeni(OpstiDomenskiObjekat odo) throws Exception{
        try {
            String sql = "UPDATE "+ odo.vratiNazivTabele()+ " SET "+ odo.vratiUpdateVrednosti()+" WHERE "+odo.vratiUslovSaIdentifikatorom();
            System.out.println(sql);
            Statement sqlStatement = connection.createStatement();
            sqlStatement.executeUpdate(sql);
            sqlStatement.close();
        } catch (SQLException ex) {
            throw new Exception("Neuspesno cuvanje objekta!",ex);
        }
    }
    
    public synchronized void obrisi(OpstiDomenskiObjekat odo) throws Exception{
        try {
            String sql = "DELETE FROM "+odo.vratiNazivTabele()+" WHERE " + odo.vratiUslovSaIdentifikatorom();
            System.out.println(sql);
            Statement sqlStatement = connection.createStatement();
            sqlStatement.executeUpdate(sql);
        } catch (SQLException ex) {
            throw new Exception("Neuspesno brisanje objekta!",ex);
        }
    }

    public synchronized List<OpstiDomenskiObjekat> vratiListuSaPretragom(String kriterijum, OpstiDomenskiObjekat odo) throws Exception {
        try {
            List<OpstiDomenskiObjekat> list;
            String sql = "SELECT * FROM " + odo.vratiNazivTabele()+""+odo.vratiTabeluSaJoin()+" WHERE "+odo.vratiKriterijumPretrage(kriterijum);
            System.out.println(sql);
            Statement sqlStatement = connection.createStatement();
            ResultSet rs = sqlStatement.executeQuery(sql);
            list = odo.napuni(rs);
            rs.close();
            sqlStatement.close();
            return list;
        } catch (SQLException ex) {
            throw new Exception("Neuspesno ucitavanje objekata!", ex);
        }
    }
    
    public synchronized int vratiMaxVrednostIdentifikatora(OpstiDomenskiObjekat odo) throws Exception{
        try {
            int max=0;
            String sql = "SELECT MAX("+odo.vratiIdentifikator()+") FROM " + odo.vratiNazivTabele();
            System.out.println(sql);
            Statement sqlStatement = connection.createStatement();
            ResultSet rs = sqlStatement.executeQuery(sql);
            rs.next();
            max = rs.getInt(1);
            rs.close();
            sqlStatement.close();
            return max;
        } catch (SQLException ex) {
            throw new Exception("Neuspesno ucitavanje maksimalne vrednosti!", ex);
        }
    }
    
}
