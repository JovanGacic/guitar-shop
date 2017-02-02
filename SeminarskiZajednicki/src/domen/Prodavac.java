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
public class Prodavac implements OpstiDomenskiObjekat {
    private int prodavacID;
    private String imePrezime;
    private String korisnickoIme;
    private String korisnickaSifra;

    public Prodavac(int prodavacID, String imePrezime, String korisnickoIme, String korisnickaSifra) {
        this.prodavacID = prodavacID;
        this.imePrezime = imePrezime;
        this.korisnickoIme = korisnickoIme;
        this.korisnickaSifra = korisnickaSifra;
    }

    public Prodavac() {
    }
    
    public Prodavac(String korIme, String korSifra){
        this.korisnickoIme = korIme;
        this.korisnickaSifra = korSifra;
    }
   
    public int getProdavacID() {
        return prodavacID;
    }

    public void setProdavacID(int prodavacID) {
        this.prodavacID = prodavacID;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getKorisnickaSifra() {
        return korisnickaSifra;
    }

    public void setKorisnickaSifra(String korisnickaSifra) {
        this.korisnickaSifra = korisnickaSifra;
    }

    @Override
    public String vratiNazivTabele() {
        return "prodavac";
   }

    @Override
    public List<OpstiDomenskiObjekat> napuni(ResultSet rs) throws Exception {
        try {
            List<OpstiDomenskiObjekat> lp = new ArrayList<>();
            while (rs.next()) {
                int prodavacID = rs.getInt("ProdavacID");
                String imePrezime = rs.getString("ImePrezime");
                String korisnickoIme = rs.getString("KorisnickoIme");
                String korisnickaSifra = rs.getString("KorisnickaSifra");
                Prodavac p = new Prodavac(prodavacID, imePrezime, korisnickoIme, korisnickaSifra);
                lp.add(p);
            }
            rs.close();
            return lp;
            } catch (SQLException ex) {
                throw new Exception("Neuspesno ucitavanje prodavca!", ex);
        }   
    
    }

    @Override
    public String vratiInsertVrednosti() {
        return prodavacID+", "+imePrezime+", "+korisnickoIme+", "+", "+korisnickaSifra;
  }

    @Override
    public OpstiDomenskiObjekat konvertuj(ResultSet rs) throws Exception {
        try {
            OpstiDomenskiObjekat p=null;
            if (rs.next()) {
                int prodID = rs.getInt("ProdavacID");
                String ime = rs.getString("ImePrezime");
                String korIme = rs.getString("KorisnickoIme");
                String sifra = rs.getString("KorisnickaSifra");
                p = new Prodavac(prodID, ime, korIme, sifra);               
            }
            rs.close();
            return p;
            } catch (SQLException ex) {
                throw new Exception("Neuspesno ucitavanje prodavca!", ex);
        }    
    
    }

    @Override
    public String vratiUslovSaIdentifikatorom() {
        return "KorisnickoIme='"+korisnickoIme+"' AND KorisnickaSifra='"+korisnickaSifra+"'";    
    }

    @Override
    public String vratiIdentifikator() {
    return "prodavacID";
    }

    @Override
    public Object get(String nazivAtributa) {
        switch(nazivAtributa){
            case "prodavacID": return getProdavacID();
            case "imePrezime": return getImePrezime();
            case "korisnickoIme": return getKorisnickoIme();
            case "korisnickaSifra": return getKorisnickaSifra();
            default: return null;
        }        
    }

    @Override
    public void set(String nazivAtributa, Object vrednostAtributa) {
        switch(nazivAtributa){
            case "prodavacID": setProdavacID(Integer.parseInt((String) vrednostAtributa)); break;
            case "imePrezime": setImePrezime((String) vrednostAtributa); break;
            case "korisnickoIme": setKorisnickoIme((String) vrednostAtributa); break;
            case "korisnickaSifra": setKorisnickaSifra((String) vrednostAtributa); break;
        }        
    }

    @Override
    public String vratiTabeluSaJoin() {
        return "";
    }

    @Override
    public String vratiKriterijumPretrage(String kriterijum) {
        return "ProdavacID='"+prodavacID+"' AND ImePrezime='"+imePrezime+"' AND KorisnickoIme='"+korisnickoIme+"' AND KorisnickaSifra='"+korisnickaSifra+"'";
    }

    @Override
    public String vratiUpdateVrednosti() {
        return "ProdavacID='"+prodavacID+"', ImePrezime='"+imePrezime+"', korisnickoIme='"+korisnickoIme+"', korisnickaSifra='"+korisnickaSifra+"'";
    }
    
    
}
