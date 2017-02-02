/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import domen.ModelGitare;
import domen.Proizvodjac;
import domen.Racun;
import domen.OpstiDomenskiObjekat;
import domen.Prodavac;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import konstante.Konstante;
import poslovnalogika.Kontroler;
import transfer.KlijentTransferObjekat;
import transfer.ServerTransferObjekat;

/**
 *
 * @author Jovan
 */
public class NitKlijenta extends Thread {

    private Socket socket;
    private boolean aktivanKlijent;

    public NitKlijenta(Socket socket) {
        this.socket = socket;
        aktivanKlijent = true;
        start();
    }

    @Override
    public void run() {
        try {
            izvrsenjeOperacija();
        } catch (Exception ex) {
            aktivanKlijent = false;
        }
        System.out.println("Klijent je prekinuo rad!");
    }

    private void izvrsenjeOperacija() throws IOException, ClassNotFoundException {
        while (true) {
            ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
            KlijentTransferObjekat toZahtev = (KlijentTransferObjekat) inSocket.readObject();
            ServerTransferObjekat toOdgovor = new ServerTransferObjekat();
            System.out.println("Odgovor: " + toZahtev.getOperacija());
            try {
                switch (toZahtev.getOperacija()) {
                    case Konstante.NADJI_PRODAVCA:
                        OpstiDomenskiObjekat r = Kontroler.getInstance().nadjiProdavca((Prodavac) toZahtev.getParametar());
                        toOdgovor.setOdgovor(r);
                        toOdgovor.setRezultat(Konstante.REZULTAT_OK);
                        break;
                    case Konstante.VRATI_DRZAVE:
                        List<OpstiDomenskiObjekat> ld = Kontroler.getInstance().vratiDrzave();
                        toOdgovor.setOdgovor(ld);
                        toOdgovor.setRezultat(Konstante.REZULTAT_OK);
                        break;
                    case Konstante.DODAJ_PROIZVODJACA:
                        Kontroler.getInstance().dodajProizvodjaca((Proizvodjac) toZahtev.getParametar());
                        toOdgovor.setRezultat(Konstante.REZULTAT_OK);
                        break;
                    case Konstante.IZMENI_PROIZVODJACA:
                        Kontroler.getInstance().izmeniProizvodjaca((Proizvodjac) toZahtev.getParametar());
                        toOdgovor.setRezultat(Konstante.REZULTAT_OK);
                        break;
                    case Konstante.OBRISI_PROIZVODJACA:
                        Kontroler.getInstance().obrisiProizvodjaca((OpstiDomenskiObjekat) toZahtev.getParametar());
                        toOdgovor.setRezultat(Konstante.REZULTAT_OK);
                        break;
                    case Konstante.VRATI_PROIZVODJACE:
                        List<OpstiDomenskiObjekat> lp = Kontroler.getInstance().vratiProizvodjace();
                        toOdgovor.setOdgovor(lp);
                        toOdgovor.setRezultat(Konstante.REZULTAT_OK);
                        break;
                    case Konstante.VRATI_PROIZVODJACE_SA_PRETRAGOM:
                        List<OpstiDomenskiObjekat> lpp = Kontroler.getInstance().vratiProizvodjaceSaPretragom(toZahtev.getParametar());
                        toOdgovor.setOdgovor(lpp);
                        toOdgovor.setRezultat(Konstante.REZULTAT_OK);
                        break;
                    case Konstante.VRATI_MODELE:
                        List<OpstiDomenskiObjekat> lm = Kontroler.getInstance().vratiModele();
                        toOdgovor.setOdgovor(lm);
                        toOdgovor.setRezultat(Konstante.REZULTAT_OK);
                        break;
                    case Konstante.IZMENI_MODEL:
                        Kontroler.getInstance().izmeniModel((ModelGitare) toZahtev.getParametar());
                        toOdgovor.setRezultat(Konstante.REZULTAT_OK);
                        toOdgovor.setOdgovor(((ModelGitare) toZahtev.getParametar()).getModelGitareID());
                        break;
                    case Konstante.DODAJ_MODEL:
                        Kontroler.getInstance().dodajModel((ModelGitare) toZahtev.getParametar());
                        toOdgovor.setRezultat(Konstante.REZULTAT_OK);
                        break;
                    case Konstante.VRATI_MODELE_SA_PRETRAGOM:
                        List<OpstiDomenskiObjekat> lmp = Kontroler.getInstance().vratiModeleSaPretragom(toZahtev.getParametar());
                        toOdgovor.setOdgovor(lmp);
                        toOdgovor.setRezultat(Konstante.REZULTAT_OK);
                        break;                  
                    case Konstante.SACUVAJ_RACUN:
                        Kontroler.getInstance().sacuvajRacun((Racun) toZahtev.getParametar());
                        toOdgovor.setRezultat(Konstante.REZULTAT_OK);
                        toOdgovor.setOdgovor(((Racun) toZahtev.getParametar()).getRacunID());
                        break;
                    case Konstante.VRATI_RACUNE_SA_PRETRAGOM:
                        List<OpstiDomenskiObjekat> lrp = Kontroler.getInstance().vratiRacuneSaPretragom(toZahtev.getParametar());
                        toOdgovor.setOdgovor(lrp);
                        toOdgovor.setRezultat(Konstante.REZULTAT_OK);
                        break;
                    case Konstante.VRATI_RACUN:
                        OpstiDomenskiObjekat rac = Kontroler.getInstance().vratiRacun((Racun) toZahtev.getParametar());
                        toOdgovor.setOdgovor(rac);
                        toOdgovor.setRezultat(Konstante.REZULTAT_OK);
                        break;
                    case Konstante.STORNIRAJ_RACUN:
                        Kontroler.getInstance().stornirajRacun((OpstiDomenskiObjekat) toZahtev.getParametar());
                        toOdgovor.setRezultat(Konstante.REZULTAT_OK);
                        break;  
                   
                        
                        
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                toOdgovor.setRezultat(Konstante.REZULTAT_NOK);
                toOdgovor.setIzuzetak(ex);
            }
            posaljiOdgovor(toOdgovor);
        }

    }

    private void posaljiOdgovor(ServerTransferObjekat toOdgovor) throws IOException {
        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(toOdgovor);
    }
}
