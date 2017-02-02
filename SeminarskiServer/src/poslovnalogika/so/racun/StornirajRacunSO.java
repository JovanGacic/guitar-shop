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
import poslovnalogika.so.OpstaSO;

/**
 *
 * @author Jovan
 */
public class StornirajRacunSO extends OpstaSO{

    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
        //nema preduslova
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
        Racun r = (Racun) obj;
        List<OpstiDomenskiObjekat> ls = DatabaseBroker.getInstance().vratiListuSaPretragom(r.getRacunID()+"", new StavkaRacuna());
        for(OpstiDomenskiObjekat odo:ls){
            DatabaseBroker.getInstance().obrisi((StavkaRacuna)odo);
        }
        DatabaseBroker.getInstance().obrisi((Racun) obj);
    }
    
}
