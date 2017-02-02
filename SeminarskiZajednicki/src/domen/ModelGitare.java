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
public class ModelGitare implements OpstiDomenskiObjekat {
    private int modelGitareID;
    private double cenaModela;
    private String statusModela;
    private String oznakaModela;
    private int garancija;
    private String magneti;
    private String pragovi;
    private String telo;
    private String boja;
    private Proizvodjac proizvodjac;

    public ModelGitare(int modelGitareID, double cenaModela, String statusModela, String oznakaModela, int garancija, String magneti, String pragovi, String telo, String boja, Proizvodjac proizvodjac) {
        this.modelGitareID = modelGitareID;
        this.cenaModela = cenaModela;
        this.statusModela = statusModela;
        this.oznakaModela = oznakaModela;
        this.garancija = garancija;
        this.magneti = magneti;
        this.pragovi = pragovi;
        this.telo = telo;
        this.boja = boja;
        this.proizvodjac = proizvodjac;
    }

    public ModelGitare() {
    }

    
    public int getModelGitareID() {
        return modelGitareID;
    }

    public void setModelGitareID(int modelGitareID) {
        this.modelGitareID = modelGitareID;
    }

    public double getCenaModela() {
        return cenaModela;
    }

    public void setCenaModela(double cenaModela) {
        this.cenaModela = cenaModela;
    }

    public String getStatusModela() {
        return statusModela;
    }

    public void setStatusModela(String statusModela) {
        this.statusModela = statusModela;
    }

    public String getOznakaModela() {
        return oznakaModela;
    }

    public void setOznakaModela(String oznakaModela) {
        this.oznakaModela = oznakaModela;
    }

    public int getGarancija() {
        return garancija;
    }

    public void setGarancija(int garancija) {
        this.garancija = garancija;
    }

    public String getMagneti() {
        return magneti;
    }

    public void setMagneti(String magneti) {
        this.magneti = magneti;
    }

    public String getPragovi() {
        return pragovi;
    }

    public void setPragovi(String pragovi) {
        this.pragovi = pragovi;
    }

    public String getTelo() {
        return telo;
    }

    public void setTelo(String telo) {
        this.telo = telo;
    }

    public String getBoja() {
        return boja;
    }

    public void setBoja(String boja) {
        this.boja = boja;
    }

    public Proizvodjac getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(Proizvodjac proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    @Override
    public String vratiNazivTabele() {
        return "modelgitare";
    }

    @Override
    public List<OpstiDomenskiObjekat> napuni(ResultSet rs) throws Exception {
        try {
            List<OpstiDomenskiObjekat> lm = new ArrayList<>();
            while (rs.next()) {
                int modelGitareID = rs.getInt("ModelGitareID");
                double cenaModela = rs.getInt("CenaModela");
                String statusModela = rs.getString("StatusModela");
                String oznakaModela = rs.getString("OznakaModela");
                int garancija = rs.getInt("Garancija");
                String magneti = rs.getString("Magneti");
                String pragovi = rs.getString("Pragovi");
                String telo = rs.getString("Telo");
                String boja = rs.getString("Boja");
                int proizvodjacID = rs.getInt("ProizvodjacID");
                int drzavaID = rs.getInt("DrzavaID");
                String nazivProizvodjaca = rs.getString("NazivProizvodjaca");
                String nazivDrzave = rs.getString("NazivDrzave");
                Drzava drz = new Drzava(drzavaID, nazivDrzave);
                Proizvodjac pr = new Proizvodjac(proizvodjacID, nazivProizvodjaca, drz);
                ModelGitare mg = new ModelGitare(modelGitareID, cenaModela, statusModela, oznakaModela, garancija, magneti, pragovi, telo, boja, pr);
                lm.add(mg);
            }
            rs.close();
            return lm;
        } catch (SQLException ex) {
            throw new Exception("Neuspesno ucitavanje modela gitare!", ex);
        }   
    }

    @Override
    public String vratiInsertVrednosti() {
        return "'"+ modelGitareID+ "','" + statusModela +"','"+ oznakaModela + "','" 
                +cenaModela + "','"+ garancija+ "','" + magneti +"','"+ pragovi +"','"+ telo 
                +"','"+ boja +"','"+proizvodjac.getProizvodjacID()+"'";
    }

    @Override
    public OpstiDomenskiObjekat konvertuj(ResultSet rs) throws Exception {       
        ModelGitare mg = null;
        if(rs.next()){
                int modelGitareID = rs.getInt("ModelGitareID");
                double cena = rs.getInt("CenaModela");
                String status = rs.getString("StatusModela");
                String oznaka = rs.getString("OznakaModela");
                int gar = rs.getInt("Garancija");
                String magneti = rs.getString("Magneti");
                String pragovi = rs.getString("Pragovi");
                String telo = rs.getString("telo");
                String boja= rs.getString("boja");
                int proizv = rs.getInt("ProizvodjacID");
                int drzavaID = rs.getInt("DrzavaID");
                String nazivPro = rs.getString("NazivProizvodjaca");
                String nazivDrzave = rs.getString("NazivDrzave");
                Drzava drz = new Drzava(drzavaID, nazivDrzave);
                Proizvodjac pr = new Proizvodjac(proizv, nazivPro, drz);
                mg = new ModelGitare(modelGitareID, cena, status, oznaka, gar, magneti, pragovi, telo, boja, pr);     
        }  
            return mg;        
    }

    @Override
    public String vratiUslovSaIdentifikatorom() {
        return " ModelGitareID="+modelGitareID;
    }

    @Override
    public String vratiIdentifikator() {
        return "modelGitareID";
   }

    @Override
    public Object get(String nazivAtributa) {
            switch(nazivAtributa){
            case "modelGitareID": return getModelGitareID();
            case "cenaModela": return getCenaModela();
            case "statusModela": return getStatusModela();
            case "oznakaModela": return getOznakaModela();
            case "pragovi": return getPragovi();           
            case "garancija": return getGarancija();
            case "magneti": return getMagneti();
            case "telo": return getTelo();
            case "boja": return getBoja();
            case "proizvodjac": return getProizvodjac();
            default: return "greska";
        }
    }

    @Override
    public void set(String nazivAtributa, Object vrednostAtributa) {
        switch(nazivAtributa){
            case "modelGitareID": setModelGitareID((Integer)vrednostAtributa);
                break;
            case "cenaModela": setCenaModela((Double) vrednostAtributa);
                break;
            case "statusModela": setStatusModela((String) vrednostAtributa);
                break;
            case "oznakaMozela": setOznakaModela((String) vrednostAtributa);
                break;
            case "pragovi": setPragovi((String) vrednostAtributa);
                break;
            case "garancija": setGarancija((Integer)vrednostAtributa);
                break;
            case "magneti": setMagneti((String) vrednostAtributa);
                break;
            case "telo": setTelo((String) vrednostAtributa);
                break;
            case "boja": setBoja((String) vrednostAtributa);
                break;
            case "proizvodjac": setProizvodjac((Proizvodjac) vrednostAtributa);
                break;
            
        }        
   }

    @Override
    public String vratiTabeluSaJoin() {
        return " JOIN proizvodjac ON (modelgitare.ProizvodjacID = proizvodjac.ProizvodjacID) JOIN drzava ON (proizvodjac.DrzavaID = drzava.DrzavaID)";     
    }

    @Override
    public String vratiKriterijumPretrage(String kriterijum) {
       return "ModelGitareID='"+kriterijum+"' OR OznakaModela LIKE'%"+kriterijum+"%' OR NazivProizvodjaca='"+kriterijum+"' OR CenaModela='"+kriterijum+"' OR StatusModela='"+kriterijum+"' OR Magneti='"+kriterijum+"'";
    }

    @Override
    public String vratiUpdateVrednosti() {
        return "ModelGitareID="+modelGitareID+", StatusModela='"+statusModela+"', OznakaModela='"+oznakaModela+"', CenaModela='"+cenaModela+"', Garancija='"+garancija+"', Magneti='"+magneti+"', modelgitare.Pragovi='"+pragovi+"', Telo='"+telo+"', Boja='"+boja+"', ProizvodjacID='"+proizvodjac.getProizvodjacID()+"'";
    }

    @Override
    public String toString() {
        return proizvodjac.getNazivProizvodjaca() + " " + oznakaModela;
    }
    
    
    
    
    
    
}
