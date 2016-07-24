/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Models;

import RMI.Interfaces.IChat;
import RMI.Server.PrepareServer;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.DefaultListModel;

/**
 *
 * @author DK
 */
public class Chat extends UnicastRemoteObject implements IChat {

    public Chat() throws RemoteException {
        super();
    }

    public void sendMessage(String mess) {
        utils.utils.newMessage = mess;
    }
    
    public String writeToChatField() {
        return utils.utils.newMessage;
    }
    public String getGameName(){
        return utils.utils.gameName;
    }
    public void addClient(String clientName){
        utils.utils.listClient.addElement(clientName);
    }
    public DefaultListModel<String> listClient(){
        return utils.utils.listClient;
    }
    
}
