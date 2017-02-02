/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poslovnalogika.so.model;
import db.DatabaseBroker;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import poslovnalogika.so.OpstaSO;

/**
 *
 * @author Jovan
 */
public class PronadjiModelSO extends OpstaSO{
    List<OpstiDomenskiObjekat> lm;
    
    public List<OpstiDomenskiObjekat> vratiModele(){
        return lm;
    }
    
    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
        //nema preduslova
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
        List<Object> parametri = (List<Object>) obj;
        lm=DatabaseBroker.getInstance().vratiListuSaPretragom((String)parametri.get(0),(OpstiDomenskiObjekat)parametri.get(1));
    }
    
    
    
}
