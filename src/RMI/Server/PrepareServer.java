/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Server;

import Menu.ChatRoom;
import RMI.Models.Chat;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DK
 */
public class PrepareServer {


    public PrepareServer() {
        try {
            LocateRegistry.createRegistry(1099);
            Chat objChat = new Chat();
            Naming.rebind("rmi://localhost:1099/Chat", objChat);

        } catch (RemoteException ex) {
            Logger.getLogger(ChatRoom.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ChatRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new PrepareServer();
        // new StartServer();
    }
}
