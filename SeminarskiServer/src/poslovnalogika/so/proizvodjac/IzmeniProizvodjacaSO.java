/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poslovnalogika.so.proizvodjac;

import db.DatabaseBroker;
import domen.Proizvodjac;
import kontrola.Validacija;
import poslovnalogika.so.OpstaSO;

/**
 *
 * @author Jovan
 */
public class IzmeniProizvodjacaSO extends OpstaSO{

    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
       Validacija.getInstance().proveriProizvodjaca((Proizvodjac) obj);
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
        DatabaseBroker.getInstance().izmeni((Proizvodjac) obj);
    }
    
}
