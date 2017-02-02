/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poslovnalogika.so.model;

import db.DatabaseBroker;
import domen.ModelGitare;
import kontrola.Validacija;
import poslovnalogika.so.OpstaSO;

/**
 *
 * @author Jovan
 */
public class ZapamtiModelSO extends OpstaSO{

    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
        Validacija.getInstance().proveriModel((ModelGitare) obj);
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
        DatabaseBroker.getInstance().sacuvaj((ModelGitare) obj);
    }
    
}
