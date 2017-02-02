/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.KlijentTransferObjekat;
import transfer.ServerTransferObjekat;

/**
 *
 * @author Jovan
 */
public class Komunikacija {
    private Socket socket;
    private String ipAdresa;
    private int port;
    
    private static Komunikacija instance;

    public Komunikacija(String ipAdresa, int port) throws IOException {
        socket = new Socket(ipAdresa, port);
        instance = this;
    }
    
    public static Komunikacija getInstance() throws IOException {
       
       return instance;
    }

    public String getIpAdresa() {
        return ipAdresa;
    }

    public void setIpAdresa(String ipAdresa) {
        this.ipAdresa = ipAdresa;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    
    
    
    public void posaljiZahtev(KlijentTransferObjekat kto) throws IOException {
        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(kto);
    }
    
    public ServerTransferObjekat procitajOdgovor() throws IOException, ClassNotFoundException {
        ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
        return (ServerTransferObjekat) inSocket.readObject();
    }
}
