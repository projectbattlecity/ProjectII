/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import ClientMap.ClientFrame;
import Models.Tank;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DK
 */
public class ClientMain {

    public static Tank serverTank = new Tank(700, 100);
    static Socket s;
    public static Tank clientTank = new Tank(550, 700);

    public static void main(String[] args) {

        try {
            s = new Socket(InetAddress.getByAddress(new byte[]{127, 0, 0, 1}), 1994);
        } catch (IOException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                    while (true) {
                        oos.writeObject(clientTank);
                        oos.reset();
                        oos.flush();
                        Thread.sleep(100);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }).start();

        //recieve server tank from server
        try {
            //s = new Socket("localhost", 1994);
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            serverTank = (Tank) ois.readObject();
            new ClientFrame();
            while (true) {
                serverTank = (Tank) ois.readObject();
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }

        //send client tank to server
        //System.out.println("Disconnected!");
    }

}
