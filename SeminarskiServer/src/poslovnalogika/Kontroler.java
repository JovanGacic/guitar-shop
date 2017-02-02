/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poslovnalogika;

import domen.Proizvodjac;
import domen.Drzava;
import domen.OpstiDomenskiObjekat;
import domen.ModelGitare;
import domen.StavkaRacuna;
import java.util.List;
import poslovnalogika.so.proizvodjac.ZapamtiProizvodjacaSO;
import poslovnalogika.so.OpstaSO;
import poslovnalogika.so.proizvodjac.IzmeniProizvodjacaSO;
import poslovnalogika.so.proizvodjac.ObrisiProizvodjacaSO;
import poslovnalogika.so.proizvodjac.VratiProizvodjaceSO;
import poslovnalogika.so.proizvodjac.UcitajProizvodjaceSO;
import poslovnalogika.so.drzava.VratiDrzaveSO;
import poslovnalogika.so.model.IzmeniModelSO;
import poslovnalogika.so.model.PronadjiModelSO;
import poslovnalogika.so.model.UcitajModeleSO;
import poslovnalogika.so.model.ZapamtiModelSO;
import poslovnalogika.so.prodavac.PronadjiProdavcaSO;
import poslovnalogika.so.racun.UcitajRacunSO;
//import poslovnalogika.so.racun.PronadjiMaxID;
import poslovnalogika.so.racun.PronadjiRacunSO;
import poslovnalogika.so.racun.StornirajRacunSO;
import poslovnalogika.so.racun.ZapamtiRacunSO;


/**
 *
 * @author Jovan
 */
public class Kontroler {

    private static Kontroler instance;

    private Kontroler() {

    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public List<OpstiDomenskiObjekat> vratiDrzave() throws Exception {
        VratiDrzaveSO vds = new VratiDrzaveSO();
        vds.izvrsenjeSO(new Drzava());
        return vds.vratiDrzave();
    }

    public void dodajProizvodjaca(Proizvodjac p) throws Exception {
        OpstaSO dps = new ZapamtiProizvodjacaSO();
        dps.izvrsenjeSO(p);
    }

    public void izmeniProizvodjaca(Proizvodjac p) throws Exception {
        OpstaSO ips = new IzmeniProizvodjacaSO();
        ips.izvrsenjeSO(p);

    }

    public List<OpstiDomenskiObjekat> vratiProizvodjace() throws Exception {
        UcitajProizvodjaceSO vps = new UcitajProizvodjaceSO();
        vps.izvrsenjeSO(new Proizvodjac());
        return vps.vratiDobavljace();
    }

    public void obrisiProizvodjaca(OpstiDomenskiObjekat odo) throws Exception {
        OpstaSO obs = new ObrisiProizvodjacaSO();
        obs.izvrsenjeSO(odo);
    }

    public List<OpstiDomenskiObjekat> vratiProizvodjaceSaPretragom(Object parametar) throws Exception {
        VratiProizvodjaceSO pps = new VratiProizvodjaceSO();
        pps.izvrsenjeSO(parametar);
        return pps.vratiProizvodjace();

    }

    public List<OpstiDomenskiObjekat> vratiRacuneSaPretragom(Object parametar) throws Exception {
        PronadjiRacunSO prs = new PronadjiRacunSO();
        prs.izvrsenjeSO(parametar);
        return prs.vratiRacune();

    }

    public List<OpstiDomenskiObjekat> vratiModele() throws Exception {
        UcitajModeleSO ums = new UcitajModeleSO();
        ums.izvrsenjeSO(new ModelGitare());
        return ums.vratiModele();

    }
//    public int vratiMaxID() throws Exception {
//        PronadjiMaxID pmid = new PronadjiMaxID();
//        pmid.izvrsenjeSO(null);
//        return ((PronadjiMaxID)pmid).getId();
//    }

    public OpstiDomenskiObjekat nadjiProdavca(OpstiDomenskiObjekat odo) throws Exception {
        PronadjiProdavcaSO nps = new PronadjiProdavcaSO();
        nps.izvrsenjeSO(odo);
        return nps.vratiProdavca();
    }

    public void izmeniModel(ModelGitare m) throws Exception {
        OpstaSO ims = new IzmeniModelSO();
        ims.izvrsenjeSO(m);
    }

    public void dodajModel(ModelGitare m) throws Exception {
        OpstaSO dms = new ZapamtiModelSO();
        dms.izvrsenjeSO(m);
    }

    public void sacuvajRacun(OpstiDomenskiObjekat odo) throws Exception {
        OpstaSO srs = new ZapamtiRacunSO();
        srs.izvrsenjeSO(odo);
    }

    public List<OpstiDomenskiObjekat> vratiModeleSaPretragom(Object parametar) throws Exception {
        PronadjiModelSO pms = new PronadjiModelSO();
        pms.izvrsenjeSO(parametar);
        return pms.vratiModele();
    }

    public void stornirajRacun(OpstiDomenskiObjekat odo) throws Exception {
        OpstaSO srs = new StornirajRacunSO();
        srs.izvrsenjeSO(odo);
    }

    public OpstiDomenskiObjekat vratiRacun(OpstiDomenskiObjekat odo) throws Exception {
        UcitajRacunSO prs = new UcitajRacunSO();
        prs.izvrsenjeSO(odo);
        return prs.vratiRacun();
    }

    
}
