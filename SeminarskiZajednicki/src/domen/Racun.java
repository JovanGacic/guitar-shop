/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jovan
 */
public class Racun implements OpstiDomenskiObjekat {

    private int racunID;
    private double iznosRacun;
    private Date datum;
    private Prodavac prodavac;
    private List<StavkaRacuna> stavkeRacuna;

    public Racun(int racunID, double iznosRacun, Date datum, Prodavac prodavac, List<StavkaRacuna> stavkeRacuna) {
        this.racunID = racunID;
        this.iznosRacun = iznosRacun;
        this.datum = datum;
        this.prodavac = prodavac;
        this.stavkeRacuna = stavkeRacuna;
    }

    public Racun() {
    }

    public int getRacunID() {
        return racunID;
    }

    public void setRacunID(int racunID) {
        this.racunID = racunID;
    }

    public double getIznosRacun() {
        return iznosRacun;
    }

    public void setIznosRacun(double iznosRacun) {
        this.iznosRacun = iznosRacun;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }

    public List<StavkaRacuna> getStavkeRacuna() {
        return stavkeRacuna;
    }

    public void setStavkeRacuna(List<StavkaRacuna> stavkeRacuna) {
        this.stavkeRacuna = stavkeRacuna;
    }

    @Override
    public String vratiNazivTabele() {
        return "racun";
    }

    @Override
    public List<OpstiDomenskiObjekat> napuni(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lr = new ArrayList<>();
        while (rs.next()) {
            int racID = rs.getInt("RacunID");
            double iznosRac = rs.getDouble("IznosRacuna");
            Date dat = rs.getDate("Datum");
            int prodavacID = rs.getInt("ProdavacID");
            String imePrezime = rs.getString("ImePrezime");
            String korisnickoIme = rs.getString("KorisnickoIme");
            String korisnickaSifra = rs.getString("KorisnickaSifra");
            Prodavac p = new Prodavac(prodavacID, imePrezime, korisnickoIme, korisnickaSifra);
            Racun r = new Racun(racID, iznosRac, dat, p, new ArrayList<>());

            if (!lr.contains(r)) {
                lr.add(r);
            }
        }
        rs.close();
        return lr;
    }

    @Override
    public String vratiInsertVrednosti() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return racunID + ",'" + df.format(datum) + "'," + iznosRacun + "," + prodavac.getProdavacID();
    }

    @Override
    public OpstiDomenskiObjekat konvertuj(ResultSet rs) throws Exception {
        Racun r = null;
        try {
            while (rs.next()) {
                int racID = rs.getInt("RacunID");
                Date dat = rs.getDate("Datum");
                Double iznosRac = rs.getDouble("IznosRacuna");
                int prodavacID = rs.getInt("ProdavacID");
                String imePrezime = rs.getString("ImePrezime");
                String korisnickoIme = rs.getString("KorisnickoIme");
                String korisnickaSifra = rs.getString("korisnickaSifra");
                int stavkaID = rs.getInt("StavkaID");
                int komada = rs.getInt("Komada");
                double prodajnaCena = rs.getDouble("ProdajnaCena");
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
                String nazivProizvodjaca = rs.getString("NazivProizvodjaca");
                int drzavaID = rs.getInt("DrzavaID");
                String nazivDrzave = rs.getString("NazivDrzave");
                Drzava d = new Drzava(drzavaID, nazivDrzave);
                Proizvodjac proizvodjac = new Proizvodjac(proizvodjacID, nazivProizvodjaca, d);
                Prodavac p = new Prodavac(prodavacID, imePrezime, korisnickoIme, korisnickaSifra);
                ModelGitare modelGitare = new ModelGitare(modelGitareID, cenaModela, statusModela, oznakaModela, garancija, magneti, pragovi, telo, boja, proizvodjac);
                StavkaRacuna sr = new StavkaRacuna(stavkaID, modelGitare, prodajnaCena, komada, r);
                if (r == null) {
                    r = new Racun(racID, iznosRac, dat, p, new ArrayList<>());
                    sr.setUkupnaCena(komada * prodajnaCena);
                    sr.setRacun(r);
                    r.dodajStavkuRacuna(sr);
                } else {
                    sr.setUkupnaCena(komada * prodajnaCena);
                    r.dodajStavkuRacuna(sr);
                }

            }
            rs.close();
            return r;
        } catch (SQLException ex) {
            throw new Exception("Racun ne postoji!!!", ex);
        }
    }

    @Override
    public String vratiUslovSaIdentifikatorom() {
         return " racun.RacunID="+racunID;
    }

    @Override
    public String vratiIdentifikator() {
        return "racun.RacunID";
    }

    @Override
    public Object get(String nazivAtributa) {
            switch(nazivAtributa){
            case "racunID": return getRacunID();
            case "datum": return getDatum();
            case "iznosRacun": return getIznosRacun();
            case "prodavac": return getProdavac();
            case "stavkeRacuna": return getStavkeRacuna();
        }
        return null;
    }

    @Override
    public void set(String nazivAtributa, Object vrednostAtributa) {
            switch(nazivAtributa){
            case "racunID": setRacunID((int)vrednostAtributa); break;
            case "datum": setDatum((Date) vrednostAtributa); break;
            case "iznosRacun": setIznosRacun((double)vrednostAtributa);
            case "prodavac": setProdavac((Prodavac) vrednostAtributa);
            case "stavkeRacuna": setStavkeRacuna((List<StavkaRacuna>) vrednostAtributa);
        }
    }

    @Override
    public String vratiTabeluSaJoin() {
        return " JOIN prodavac p ON (racun.ProdavacID = p.ProdavacID) JOIN stavkaracuna sr ON (racun.RacunID=sr.RacunID) JOIN modelgitare mg ON (sr.ModelGitareID = mg.ModelGitareID) JOIN proizvodjac pro ON (mg.ProizvodjacID = pro.ProizvodjacID) JOIN drzava d ON (pro.DrzavaID = d.DrzavaID)";

    }

    @Override
    public String vratiKriterijumPretrage(String kriterijum) {
        return "racun.RacunID='"+kriterijum+"' OR Datum='"+kriterijum+"' OR IznosRacuna='"+kriterijum+"' OR ImePrezime='"+kriterijum+"'";
    }

    @Override
    public String vratiUpdateVrednosti() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return "RacunID="+racunID+", Datum='"+df.format(datum)+"', IznosRacuna="+iznosRacun+", ProdavacID='"+prodavac.getProdavacID()+"'";
    }

    public void dodajStavkuRacuna(StavkaRacuna sr) {
        stavkeRacuna.add(sr);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Racun other = (Racun) obj;
        if (this.racunID != other.racunID) {
            return false;
        }
        return true;
    }
    
    

}
