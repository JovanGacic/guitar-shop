/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poslovnalogika.so.proizvodjac;

import db.DatabaseBroker;
import domen.Proizvodjac;
import poslovnalogika.so.OpstaSO;

/**
 *
 * @author Jovan
 */
public class ObrisiProizvodjacaSO extends OpstaSO{

    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
        //nema preduslova
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
        DatabaseBroker.getInstance().obrisi((Proizvodjac)obj);
    }
    
}
