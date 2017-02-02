/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Jovan
 */
public interface OpstiDomenskiObjekat extends Serializable{

    public String vratiNazivTabele();

    public List<OpstiDomenskiObjekat> napuni(ResultSet rs) throws Exception;

    public String vratiInsertVrednosti();

    public OpstiDomenskiObjekat konvertuj(ResultSet rs) throws Exception;

    public String vratiUslovSaIdentifikatorom();

    public String vratiIdentifikator();
    
    public Object get(String nazivAtributa);
    
    public void set(String nazivAtributa, Object vrednostAtributa);

    public String vratiTabeluSaJoin();
    
    public String vratiKriterijumPretrage(String kriterijum);

    public String vratiUpdateVrednosti();

    
    
}
