/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.proizvodjac;

import domen.OpstiDomenskiObjekat;
import domen.Proizvodjac;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jovan
 */
public class ProizvodjacTableModel extends AbstractTableModel {
    private List<OpstiDomenskiObjekat> lp;
    String[] kolone = new String[]{"ID", "Naziv", "Država"};

    public ProizvodjacTableModel(List<OpstiDomenskiObjekat> lp) {
        this.lp = lp;
    }
    
    @Override
    public int getRowCount() {
        if (lp == null) {
            return 0;
        }
        return lp.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        OpstiDomenskiObjekat p = lp.get(rowIndex);
        switch (columnIndex) {
            case 0: return p.get("proizvodjacID");
            case 1: return p.get("nazivProizvodjaca");
            case 2: return p.get("drzava");
            default: return "n/a";
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
    
    public void dodajRed() {
        Proizvodjac p = new Proizvodjac();
        lp.add(p);
        System.out.println("Dodat je novi element!");
        fireTableDataChanged();
    }
    
    public OpstiDomenskiObjekat vratiProizvodjaca(int red) {
        return lp.get(red);
    }

    public void dodajRed(Proizvodjac p) {
        lp.add(p);
        fireTableDataChanged();
    }

    public List<OpstiDomenskiObjekat> vratiProizvodjace() {
        return lp;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        OpstiDomenskiObjekat p = lp.get(rowIndex);
        switch(columnIndex) {
            case 0: p.set("proizvodjacID",aValue);
                    break;
            case 1: p.set("nazivProizvodjaca",aValue);
                    break;
            case 2: p.set("drzava",aValue);
                    break;
            
        }
    }
    
    public void obrisiRed(int i){
        lp.remove(i);
        fireTableDataChanged();
    }
    
    
}
