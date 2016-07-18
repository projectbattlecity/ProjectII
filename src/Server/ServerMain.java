/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Models.Player;
import ServeMap.ServerFrame;
import RMI.Models.Game;
import RMI.Models.ListTank;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.utils;

/**
 *
 * @author DK
 */
public class ServerMain {

    public static Player serverTank = new Player(0, 0);
    //public static Player clientTank = new Player(0, 100);

    public static void main(String[] args) {
        

        utils.map.tanks.add(serverTank);
        try {
            utils.objGame = new Game();
            ListTank objListTank = new ListTank();
            objListTank.setTanks(utils.map.tanks);
            
            new ServerFrame();
            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://localhost:1099/game", utils.objGame);
            Naming.rebind("rmi://localhost:1099/listtank", objListTank);

        } catch (RemoteException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        //utils.utils.map.tanks.add(clientTank);

//       
    }

}
