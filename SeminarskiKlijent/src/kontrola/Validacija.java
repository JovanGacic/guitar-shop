/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrola;

import domen.Proizvodjac;
import domen.Racun;
import domen.StavkaRacuna;
import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Jovan
 */
public class Validacija {
    
    public static Validacija instance;
    
    private Validacija(){
        
    }
    
    public static Validacija getInstance(){
        if(instance==null)
            instance = new Validacija();
        return instance;
    }
    
    
    
    
    public String daLiJeUneto (Object polje){
        if (polje!=null && !(polje.equals(""))) return "ok";
        return "Morate popuniti sva polja !";
    }
    
    public boolean daLiJeStavkaOK(StavkaRacuna sr) throws Exception{
        if(sr.getKomada()>0 && sr.getProdajnaCena()>0) return true;
        throw new Exception("Kolicina i cena moraju biti uneti");
    }

    public void proveriRacun(Racun r) throws Exception{
        for(StavkaRacuna sr: r.getStavkeRacuna()){
            if(!sr.getStatus().equals("delete"))
                daLiJeStavkaOK(sr);
        }
    }
}
