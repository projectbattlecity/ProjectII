/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Models.Player;
import ServeMap.ServerFrame;
import Models.Tank;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author DK
 */
public class ServerMain {

    static ServerSocket ss;
    static Socket s;
    public static Player serverTank = new Player(0, 0);
    public static Player clientTank = new Player(0, 100);

    public static void main(String[] args) {
        new ServerFrame();
        
        utils.utils.map.tanks.add(serverTank);
        utils.utils.map.tanks.add(clientTank);
        
//        try {
//            ss = new ServerSocket(1994);
//            System.out.println("Server is ready...");
//            System.out.println("Waiting for client");
//            s = ss.accept();
//            System.out.println("Client connected!");
//        } catch (IOException ex) {
//            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        //recieve client tank from client
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    //s = new Socket("localhost", 1994);
//                    ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
//                    clientTank = (Tank) ois.readObject();
//
//                    while (true) {
//                        clientTank = (Tank) ois.readObject();
//                    }
//                } catch (IOException ex) {
//                    Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (ClassNotFoundException ex) {
//                    Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }).start();
//
//        //new ServerFrame().add(new ServerPane(serverTank));
//        //send servertank to client
//        try {
//            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
//            while (true) {
//                oos.writeObject(serverTank);
//                oos.reset();
//                oos.flush();
//                Thread.sleep(100);
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

}
