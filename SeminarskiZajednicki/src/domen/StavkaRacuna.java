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
public class StavkaRacuna implements OpstiDomenskiObjekat{
    
   
    private int komada;
    private double prodajnaCena;
    private double ukupnaCena;
    private ModelGitare model;
    private String status;
    private int stavkaID;
    private Racun racun = new Racun();

    public StavkaRacuna(int stavkaID, ModelGitare model , double prodajnaCena, int komada , Racun racun) {
        this.stavkaID = stavkaID;
        this.komada = komada;
        this.prodajnaCena = prodajnaCena;
        this.ukupnaCena = komada*prodajnaCena;
        this.model = model;
        this.status="insert";
        this.racun = racun;
    }

    public StavkaRacuna() {
        this.status="insert";        
    }

    public int getKomada() {
        return komada;
    }

    public void setKomada(int komada) {
        this.komada = komada;
    }

    public double getProdajnaCena() {
        return prodajnaCena;
    }

    public void setProdajnaCena(double prodajnaCena) {
        this.prodajnaCena = prodajnaCena;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public ModelGitare getModel() {
        return model;
    }

    public void setModel(ModelGitare model) {
        this.model = model;
    }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStavkaID() {
        return stavkaID;
    }

    public void setStavkaID(int stavkaID) {
        this.stavkaID = stavkaID;
    }
    
    

    @Override
    public String vratiNazivTabele() {
        return "stavkaracuna";
    }

    @Override
    public List<OpstiDomenskiObjekat> napuni(ResultSet rs) throws Exception {
            try {
            List<OpstiDomenskiObjekat> ls = new ArrayList<>();
            while (rs.next()) {
                int stavID=rs.getInt("StavkaID");
                StavkaRacuna sr = new StavkaRacuna();
                sr.setStavkaID(stavID);
                ls.add(sr);
            }
            rs.close();
            return ls;
            } catch (SQLException ex) {
                throw new Exception("Neuspesno ucitavanje stavki racuna!", ex);
        }
    }

    @Override
    public String vratiInsertVrednosti() {
        return  racun.getRacunID() + "," + komada + "," + prodajnaCena + "," + ukupnaCena + "," + model.getModelGitareID() + "," + stavkaID;
    }

    @Override
    public OpstiDomenskiObjekat konvertuj(ResultSet rs) throws Exception {
        try {
            OpstiDomenskiObjekat sr = null;
            if (rs.next()) {
                int stavkaI=rs.getInt("StavkaID");              
                sr = new StavkaRacuna();
                sr.set("StavkaID", stavkaI);
            }
            rs.close();
            return sr;
            } catch (SQLException ex) {
                throw new Exception("Neuspesno ucitavanje stavke racuna!", ex);
        }        
    }

    @Override
    public String vratiUslovSaIdentifikatorom() {
        return "StavkaID="+stavkaID;
    }

    @Override
    public String vratiIdentifikator() {
     return "StavkaID";
    }

    @Override
    public Object get(String nazivAtributa) {
        switch(nazivAtributa){
            case "stavkaID": return getStavkaID();
            case "komada": return getKomada();
            case "prodajnaCena": return getProdajnaCena();
            case "ukupnaCena": return getUkupnaCena();
            case "model": return getModel();
            case "racun": return getRacun();
            default: return "N/A";
        }    
    }

    @Override
    public void set(String nazivAtributa, Object vrednostAtributa) {
        switch(nazivAtributa){
            case "stavkaID": setStavkaID(Integer.parseInt((String) vrednostAtributa));
            case "komada": setKomada(Integer.parseInt((String)vrednostAtributa) ); break;
            case "model": setModel((ModelGitare) vrednostAtributa); break;
            case "prodajnaCena": setProdajnaCena(Double.parseDouble((String)vrednostAtributa)); break;
         //   case "ukupnaCena": setUkupnaCena(Double.parseDouble((String)vrednostAtributa)); break;

        }        
    }

    @Override
    public String vratiTabeluSaJoin() {
        return "";
    }

    @Override
    public String vratiKriterijumPretrage(String kriterijum) {
        return "StavkaID="+kriterijum;
    }

    @Override
    public String vratiUpdateVrednosti() {
        return "StavkaID" + stavkaID + ", RacunID=" + racun.getRacunID();
    }
    
    
    
    
    
    
    
}
