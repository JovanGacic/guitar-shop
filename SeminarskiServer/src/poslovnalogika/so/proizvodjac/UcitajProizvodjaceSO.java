/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poslovnalogika.so.proizvodjac;

import db.DatabaseBroker;
import domen.Proizvodjac;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import poslovnalogika.so.OpstaSO;

/**
 *
 * @author Jovan
 */
public class UcitajProizvodjaceSO extends OpstaSO{
    List<OpstiDomenskiObjekat> lp;
    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
        //nema preduslova
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
        lp =DatabaseBroker.getInstance().vratiListu((OpstiDomenskiObjekat) obj);
    }
    
    public List<OpstiDomenskiObjekat> vratiDobavljace(){
        return lp;
    }
    
}
