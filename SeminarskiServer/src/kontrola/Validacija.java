/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrola;

import domen.ModelGitare;
import domen.Proizvodjac;
import domen.Racun;
import domen.StavkaRacuna;
import javax.swing.JOptionPane;

/**
 *
 * @author Jovan
 */
public class Validacija {

    public static Validacija instance;

    private Validacija() {

    }

    public static Validacija getInstance() {
        if (instance == null) {
            instance = new Validacija();
        }
        return instance;
    }

    public void proveriProizvodjaca(Proizvodjac p) throws Exception {
        daLiJeProizvodjacIDOk(p.getProizvodjacID());
        daLiJeUneto(p.getNazivProizvodjaca());
        daLiJeUneto(p.getDrzava());
    }

    private boolean daLiJeProizvodjacIDOk(int proizvodjacID) throws Exception {
        if (proizvodjacID != 0) {
            return true;
        }
    
        throw new Exception("ProizvodjacID mora biti razlicit od nule !");
    }

    private boolean daLiJeUneto(Object polje) throws Exception {
        if (polje != null && !(polje.equals(""))) {
            return true;
        }
        throw new Exception("Morate uneti sva polja !");
    }

    public void proveriModel(ModelGitare modelGitare) throws Exception {
        daLiJeUneto(modelGitare.getModelGitareID());
        daLiJeUneto(modelGitare.getOznakaModela());
        daLiJeUneto(modelGitare.getStatusModela());
        daLiJeUneto(modelGitare.getCenaModela());
        daLiJeUneto(modelGitare.getPragovi());
        daLiJeUneto(modelGitare.getGarancija());
        daLiJeUneto(modelGitare.getTelo());
        daLiJeUneto(modelGitare.getBoja());
        daLiJeUneto(modelGitare.getProizvodjac());
        daLiJeUneto(modelGitare.getMagneti());
        daLiJeModelIDOk(modelGitare.getModelGitareID());
    }

    private boolean daLiJeModelIDOk(int modelGitareID) throws Exception {
        if (modelGitareID != 0) {
            return true;
        }
        throw new Exception("ID mora biti razlicit od nule !");
    }

    public void proveriRacun(Racun r) throws Exception {
        for (StavkaRacuna sr : r.getStavkeRacuna()) {
            daLiJeStavkaOK(sr);
        }
    }

    public boolean daLiJeStavkaOK(StavkaRacuna sr) throws Exception {
        if (sr.getKomada() > 0 && sr.getProdajnaCena() > 0) {
            return true;
        }
        throw new Exception("Kolicina i cena moraju biti vece od nule");
    }
}
