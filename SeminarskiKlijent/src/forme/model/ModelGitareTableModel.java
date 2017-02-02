/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.model;

import domen.ModelGitare;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jovan
 */
public class ModelGitareTableModel extends AbstractTableModel {
    private List<OpstiDomenskiObjekat> lm;
    String[] kolone = new String[]{"ID", "Status", "Oznaka", "Cena", "Garancija", "Magneti", "Pragovi", "Telo", "Boja", "Proizvođač"};

    public ModelGitareTableModel(List<OpstiDomenskiObjekat> lm) {
        this.lm = lm;
    }
    
    @Override
    public int getRowCount() {
        if (lm == null) {
            return 0;
        }
        return lm.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        OpstiDomenskiObjekat m = lm.get(rowIndex);
        switch (columnIndex) {
            case 0: return m.get("modelGitareID");
            case 1: return m.get("statusModela");
            case 2: return m.get("oznakaModela");
            case 3: return m.get("cenaModela");
            case 4: return m.get("garancija");
            case 5: return m.get("magneti");
            case 6: return m.get("pragovi");
            case 7: return m.get("telo");
            case 8: return m.get("boja");
            case 9: return m.get("proizvodjac");
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
        ModelGitare m = new ModelGitare();
        lm.add(m);
        System.out.println("Dodat je novi element!");
        fireTableDataChanged();
    }
    
    public OpstiDomenskiObjekat vratiModelGitare(int red) {
        return lm.get(red);
    }

    public void dodajRed(ModelGitare m) {
        lm.add(m);
        fireTableDataChanged();
    }

    public List<OpstiDomenskiObjekat> vratiModeleGitare() {
        return lm;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        OpstiDomenskiObjekat p = lm.get(rowIndex);
        switch(columnIndex) {
            case 0: p.set("modelGitareID",aValue);
                    break;
            case 1: p.set("statusModela",aValue);
                    break;
            case 2: p.set("oznakaModela",aValue);
                    break;
            case 3: p.set("cenaModela",aValue);
                    break;
            case 4: p.set("garancija",aValue);
                    break; 
            case 5: p.set("magneti",aValue);
                    break;    
            case 6: p.set("pragovi",aValue);
                    break; 
            case 7: p.set("telo",aValue);
                    break;                 
            case 8: p.set("boja",aValue);
                    break; 
            case 9: p.set("proizvodjac",aValue);
                    break;                 
        }
    }
    
    public void obrisiRed(int i){
        lm.remove(i);
        fireTableDataChanged();
    }
    
    
}
