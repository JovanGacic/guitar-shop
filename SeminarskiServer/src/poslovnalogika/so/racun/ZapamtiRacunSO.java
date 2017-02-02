/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poslovnalogika.so.racun;

import db.DatabaseBroker;
import domen.Racun;
import domen.OpstiDomenskiObjekat;
import domen.StavkaRacuna;
import java.util.List;
import kontrola.Validacija;
import poslovnalogika.so.OpstaSO;

/**
 *
 * @author Jovan
 */
public class ZapamtiRacunSO extends OpstaSO {

    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
        Validacija.getInstance().proveriRacun((Racun) obj);
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
        Racun r = (Racun) obj;
        int racunID = DatabaseBroker.getInstance().vratiMaxVrednostIdentifikatora(r);
        racunID++;
        r.setRacunID(racunID);
        DatabaseBroker.getInstance().sacuvaj(r);

        int sifraSt = DatabaseBroker.getInstance().vratiMaxVrednostIdentifikatora(new StavkaRacuna());
        sifraSt++;
        for (int i = 0; i < r.getStavkeRacuna().size(); i++) {
            StavkaRacuna s = r.getStavkeRacuna().get(i);
            s.setStavkaID(sifraSt);
            s.setRacun(r);
            DatabaseBroker.getInstance().sacuvaj(s);
            sifraSt++;
        }

    }

}
