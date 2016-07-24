/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Client;

import Models.Player;
import RMI.Interfaces.IMapHelper;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.utils;

/**
 *
 * @author DK
 */
public class StartClient {
    
    public static IMapHelper mapHelperRemote;
    public static Player clientPlayer = new Player(0, 0);
    public static void main(String[] args) {
        new StartClient();
    }

    public StartClient() {
        try {
            mapHelperRemote = (IMapHelper) Naming.lookup("rmi://" + utils.hostIP + ":1099/MapHelper");
            System.out.println(utils.hostIP);
        } catch (NotBoundException ex) {
            Logger.getLogger(StartClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(StartClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(StartClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        new GameFrame();
        
    }
    
    
}
