/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jovan
 */
public class Drzava implements OpstiDomenskiObjekat {

    private int drzavaID;
    private String nazivDrzave;

    public Drzava(int drzavaID, String nazivDrzave) {
        this.drzavaID = drzavaID;
        this.nazivDrzave = nazivDrzave;
    }

    public Drzava() {
    }

    public int getDrzavaID() {
        return drzavaID;
    }

    public void setDrzavaID(int drzavaID) {
        this.drzavaID = drzavaID;
    }

    public String getNazivDrzave() {
        return nazivDrzave;
    }

    public void setNazivDrzave(String nazivDrzave) {
        this.nazivDrzave = nazivDrzave;
    }

    @Override
    public String vratiNazivTabele() {
        return "drzava";
    }

    @Override
    public List<OpstiDomenskiObjekat> napuni(ResultSet rs) throws Exception {
        try {
            List<OpstiDomenskiObjekat> ld = new ArrayList<>();
            while (rs.next()) {
                int drzavaID = rs.getInt("DrzavaID");
                String nazivDrzave = rs.getString("NazivDrzave");
                Drzava d = new Drzava(drzavaID, nazivDrzave);
                ld.add(d);
            }
            rs.close();
            return ld;
        } catch (SQLException ex) {
            throw new Exception("Neuspesno ucitavanje drzava!", ex);
        }
    }

    @Override
    public String vratiInsertVrednosti() {
        return drzavaID + ",'" + nazivDrzave + "'";
    }

    @Override
    public OpstiDomenskiObjekat konvertuj(ResultSet rs) throws Exception {
        if (rs.next()) {
            drzavaID = rs.getInt("DrzavaID");
            nazivDrzave = rs.getString("NazivDrzave");
        }
        Drzava d = new Drzava(drzavaID, nazivDrzave);
        return d;
    }

    @Override
    public String vratiUslovSaIdentifikatorom() {
        return "WHERE DrzavaID=" + drzavaID;
    }

    @Override
    public String vratiIdentifikator() {
        return "drzavaID";
    }

    @Override
    public Object get(String nazivAtributa) {
        switch (nazivAtributa) {
            case "drzavaID":
                return getDrzavaID();
            case "nazivDrzave":
                return getNazivDrzave();
            default:
                return "greska";
        }
    }

    @Override
    public void set(String nazivAtributa, Object vrednostAtributa) {
        switch (nazivAtributa) {
            case "drzavaID":
                setDrzavaID((int) vrednostAtributa);
                break;
            case "nazivDrzave":
                setNazivDrzave((String) nazivAtributa);
                break;

        }
    }

    @Override
    public String vratiTabeluSaJoin() {
        return "";
    }

    @Override
    public String vratiKriterijumPretrage(String kriterijum) {
        return "";
    }

    @Override
    public String vratiUpdateVrednosti() {
        return "DrzavaID=" + drzavaID + ", NazivDrzave='" + nazivDrzave + "'";
    }

    @Override
    public String toString() {
        return nazivDrzave;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Drzava other = (Drzava) obj;
        if (this.drzavaID != other.drzavaID) {
            return false;
        }
        return true;
    }

}
