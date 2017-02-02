/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poslovnalogika.so.prodavac;

import db.DatabaseBroker;
import domen.OpstiDomenskiObjekat;
import domen.Prodavac;
import poslovnalogika.so.OpstaSO;

/**
 *
 * @author Jovan
 */
public class PronadjiProdavcaSO extends OpstaSO{
    OpstiDomenskiObjekat p=null;
    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
        //nema preduslova
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
        p=DatabaseBroker.getInstance().pronadji((Prodavac) obj);
    }
    
    public OpstiDomenskiObjekat vratiProdavca(){
        return p;
    }
    
}
