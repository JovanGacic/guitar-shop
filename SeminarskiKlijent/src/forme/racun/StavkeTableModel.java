/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.racun;

import domen.ModelGitare;
import domen.StavkaRacuna;
import forme.racun.FrmRacunUnos.IKolicinaPromena;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jovan
 */
public class StavkeTableModel extends AbstractTableModel {

    private final IKolicinaPromena kolicinaPromena;
    List<StavkaRacuna> stavke;
    List<StavkaRacuna> obrisaneStavke = new ArrayList<>();
    String[] kolone = new String[]{"StavkaID", "Model", "Prodajna cena", "Komada", "Ukupno"};

    public StavkeTableModel(List<StavkaRacuna> stavke, FrmRacunUnos.IKolicinaPromena kolicinaPromena) {
        this.stavke = stavke;
        this.kolicinaPromena = kolicinaPromena;
    }

    @Override
    public int getRowCount() {
        if (stavke == null) {
            return 0;
        }
        return stavke.size();

    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaRacuna s = stavke.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return s.getModel();
            case 2:
                return s.getProdajnaCena();
            case 3:
                return s.getKomada();
            case 4:
                return s.getUkupnaCena();
            default:
                return "N/A";
        }

    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 1 || columnIndex == 3) {
            return true;
        }
        return false;
    }

      public void dodajRed() {
        StavkaRacuna s = new StavkaRacuna();
        int id = stavke.size();
        id++;
        s.setStavkaID(id);
        stavke.add(s);
        fireTableDataChanged();
    }

    public List<StavkaRacuna> vratiStavke() {
        return stavke;
    }

    public List<StavkaRacuna> getObrisaneStavke() {
        return obrisaneStavke;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        StavkaRacuna s = stavke.get(rowIndex);
        switch (columnIndex) {
            case 1:
                s.set("model", aValue);
                s.setProdajnaCena(s.getModel().getCenaModela());
                setValueAt(s.getModel().getCenaModela() + "", rowIndex, 2);
                fireTableDataChanged();
                break;
            case 2:
                s.set("prodajnaCena", aValue);
                s.setUkupnaCena(s.getKomada() * s.getProdajnaCena());
                setValueAt(s.getKomada() * s.getProdajnaCena(), rowIndex, 4);
                break;
            case 3:
                s.set("komada", aValue);
                s.setUkupnaCena(s.getKomada() * s.getProdajnaCena());
                setValueAt(s.getKomada() * s.getProdajnaCena(), rowIndex, 4);
                kolicinaPromena.promenjenaKolicina(izracunajUkupno());
                break;
            case 4:
                s.set("ukupnaCena", aValue);
                break;

        }
    }

    public void obrisiRed(int i) {
        StavkaRacuna obrisanaStavka = stavke.get(i);
        obrisaneStavke.add(obrisanaStavka);
        stavke.remove(i);
//        srediRedneBrojeve();
        fireTableDataChanged();
    }

//    private void srediRedneBrojeve() {
//        int red = 1;
//        for (StavkaRacuna stavka : stavke) {
//            stavka.setRb(red);
//            red++;
//        }
//    }

    public double izracunajUkupno() {
        double ukupno = 0;
        for (StavkaRacuna s : stavke) {
            ukupno = ukupno + s.getUkupnaCena();
        }
        return ukupno;
    }

}
