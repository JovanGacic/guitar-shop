/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.racun;

import domen.Racun;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jovan
 */
public class RacuniTableModel extends AbstractTableModel{
    List<OpstiDomenskiObjekat> lr;
    String[] kolone = new String[]{"Broj računa","Datum","Iznos","Prodavac"};

    public RacuniTableModel(List<OpstiDomenskiObjekat> lr) {
        this.lr = lr;
    }
    
    
    @Override
    public int getRowCount() {
        if (lr == null) {
            return 0;
        }
        return lr.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Racun r = (Racun) lr.get(rowIndex);
        switch(columnIndex){
            case 0: return r.getRacunID();
            case 1: return r.getDatum();
            case 2: return r.getIznosRacun();
            case 3: return r.getProdavac().getImePrezime();
            default: return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    public OpstiDomenskiObjekat vratiRacun(int red){
        return lr.get(red);
    }
    
    public List<OpstiDomenskiObjekat> vratiRacune(){
        return lr;
    }
    
    public void obrisiRed(int red){
        lr.remove(red);
        fireTableDataChanged();
    }
   
}
