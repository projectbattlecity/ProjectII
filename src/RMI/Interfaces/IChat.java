/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Interfaces;

import RMI.Server.PrepareServer;
import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.swing.DefaultListModel;

/**
 *
 * @author DK
 */
public interface IChat extends Remote {

    public void sendMessage(String mess) throws RemoteException;

    public String writeToChatField() throws RemoteException;

    public String getGameName() throws RemoteException;

    public void addClient(String clientName) throws RemoteException;

    public DefaultListModel<String> listClient() throws RemoteException;
}
