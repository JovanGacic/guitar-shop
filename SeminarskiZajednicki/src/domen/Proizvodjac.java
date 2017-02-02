/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jovan
 */
public class Proizvodjac implements OpstiDomenskiObjekat {
    private int proizvodjacID;
    private String nazivProizvodjaca;
    private Drzava drzava;

    public Proizvodjac(int proizvodjacID, String nazivProizvodjaca, Drzava drzava) {
        this.proizvodjacID = proizvodjacID;
        this.nazivProizvodjaca = nazivProizvodjaca;
        this.drzava = drzava;
    }

    public Proizvodjac() {
    }
    
        
    public int getProizvodjacID() {
        return proizvodjacID;
    }

    public void setProizvodjacID(int proizvodjacID) {
        this.proizvodjacID = proizvodjacID;
    }

    public String getNazivProizvodjaca() {
        return nazivProizvodjaca;
    }

    public void setNazivProizvodjaca(String nazivProizvodjaca) {
        this.nazivProizvodjaca = nazivProizvodjaca;
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }

    @Override
    public String vratiNazivTabele() {
        return "Proizvodjac";
    }

    @Override
    public List<OpstiDomenskiObjekat> napuni(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lp = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("ProizvodjacID");
            String naziv = rs.getString("NazivProizvodjaca");
            int drzavaID = rs.getInt("DrzavaID");
            String nazivDrzave = rs.getString("NazivDrzave");
            Drzava d = new Drzava(drzavaID, nazivDrzave);
            Proizvodjac p = new Proizvodjac(id, naziv, d);

            if (!lp.contains(p)) {
                lp.add(p);
            }

        }
        rs.close();
        return lp;       
   }

    @Override
    public String vratiInsertVrednosti() {
        return proizvodjacID + ",'" + nazivProizvodjaca + "'," + drzava.getDrzavaID();
   }

    @Override
    public OpstiDomenskiObjekat konvertuj(ResultSet rs) throws Exception {
            try {
            OpstiDomenskiObjekat p=null;
            if (rs.next()) {
                int proizvodjacID = rs.getInt("ProizvodjacID");
                String naziv = rs.getString("NazivProizvodjaca");
                String nazivD = rs.getString("NazivDrzave");
                int drzavaI = rs.getInt("DrzavaID");
                Drzava d = new Drzava(drzavaI, nazivD);
                p = new Proizvodjac(proizvodjacID, naziv, d);
            }
            rs.close();
            return p;
            } catch (SQLException ex) {
                throw new Exception("Neuspesno ucitavanje proizvodjaca!", ex);
        }
   }

    @Override
    public String vratiUslovSaIdentifikatorom() {
        return "ProizvodjacID=" + proizvodjacID;
    }

    @Override
    public String vratiIdentifikator() {
        return "proizvodjacID";
   }

    @Override
    public Object get(String nazivAtributa) {
            switch(nazivAtributa){
            case "proizvodjacID": return getProizvodjacID();
            case "nazivProizvodjaca": return getNazivProizvodjaca();
            case "drzava": return getDrzava();
            default: return "greska";
        }
   }

    @Override
    public void set(String nazivAtributa, Object vrednostAtributa) {
        switch(nazivAtributa){
            case "proizvodjacID": setProizvodjacID(Integer.parseInt((String)vrednostAtributa));
                break;
            case "nazivProizvodjaca": setNazivProizvodjaca((String) vrednostAtributa);
                break;
            case "drzava": setDrzava((Drzava) vrednostAtributa);
                break;
            
        }        
   }

    @Override
    public String vratiTabeluSaJoin() {
        return " JOIN drzava ON (proizvodjac.DrzavaID=drzava.DrzavaID)";
    }

    @Override
    public String vratiKriterijumPretrage(String kriterijum) {
        return " ProizvodjacID=" + kriterijum + " OR NazivProizvodjaca=" + kriterijum;
    }

    @Override
    public String vratiUpdateVrednosti() {
        return "ProizvodjacID ='"+proizvodjacID+"', NazivProizvodjaca='"+nazivProizvodjaca+"', DrzavaID="+drzava.getDrzavaID();
    }

    @Override
    public String toString() {
        return nazivProizvodjaca;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Proizvodjac other = (Proizvodjac) obj;
        if (this.proizvodjacID != other.proizvodjacID) {
            return false;
        }
        return true;
    }


    
    
    
    
}
