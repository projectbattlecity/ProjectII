/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import ClientMap.ClientFrame;
import Models.Tank;
import RMI.Interface.IGame;
import RMI.Interface.IMap;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.utils;

/**
 *
 * @author DK
 */
public class ClientMain {
    public static void main(String[] args) {
       
        new ClientFrame();
        
        try {
            
            IGame objGame = (IGame) Naming.lookup("rmi://localhost:1099/game");
            objGame.addClientTank();
            
            //utils.map.tanks.add(utils.clientTank);
        } catch (NotBoundException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
