/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Models.Player;
import ServeMap.ServerFrame;
import Models.Tank;
import RMI.Models.Game;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DK
 */
public class ServerMain {

    public static Player serverTank = new Player(0, 0);
    //public static Player clientTank = new Player(0, 100);

    public static void main(String[] args) {

        try {
            Game objGame = new Game();
            objGame.getFrame().add(objGame.getPanel());
            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://localhost:1099/game", objGame);

        } catch (RemoteException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }

        utils.utils.map.tanks.add(serverTank);
        //utils.utils.map.tanks.add(clientTank);

//       
    }

}
