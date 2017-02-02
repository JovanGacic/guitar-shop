/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poslovnalogika.so.drzava;

import db.DatabaseBroker;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import poslovnalogika.so.OpstaSO;

/**
 *
 * @author Jovan
 */
public class VratiDrzaveSO extends OpstaSO{
    private List<OpstiDomenskiObjekat> ld;
    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
        //nema preduslova
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
        ld = DatabaseBroker.getInstance().vratiListu((OpstiDomenskiObjekat) obj);
    }
    
    public List<OpstiDomenskiObjekat> vratiDrzave(){
        return ld;
    }
    
}
