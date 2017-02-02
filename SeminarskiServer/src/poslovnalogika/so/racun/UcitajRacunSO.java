/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poslovnalogika.so.racun;

import db.DatabaseBroker;
import domen.Racun;
import poslovnalogika.so.OpstaSO;

/**
 *
 * @author Jovan
 */
public class UcitajRacunSO extends OpstaSO{
    Racun r = null;
    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
        //nema preduslova
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
        r = (Racun) DatabaseBroker.getInstance().pronadji((Racun) obj);
    }
    
    public Racun vratiRacun(){
        return r;
    }
    
}
