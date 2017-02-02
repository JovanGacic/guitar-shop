/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jovan
 */
public class Komunikacija extends Thread{
    boolean aktivanServer = true;
    ServerSocket welcomeSocket;
    
    public void setAktivanServer(boolean aktivanServer) {
        this.aktivanServer = aktivanServer;
        if(aktivanServer==false){
            try {
                welcomeSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    
    public void pokreniServer(int port) throws IOException, ClassNotFoundException {
        welcomeSocket = new ServerSocket(port);
        System.out.println("Server je pokrenut i spreman za rad.");
        aktivanServer=true;
        start();
        
    }
    

    @Override
    public void run() {
        while (aktivanServer) {
            try {
                Socket socket = welcomeSocket.accept();
                NitKlijenta nitKlijent = new NitKlijenta(socket);
                System.out.println("Klijent se povezao.");
            } catch (IOException ex) {
                Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
