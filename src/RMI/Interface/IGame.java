/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Interface;

import RMI.Models.ListTank;
import ServeMap.ServerFrame;
import ServeMap.ServerPanel;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author DK
 */
public interface IGame extends Remote{
    public ServerFrame getFrame() throws RemoteException;
    public ServerPanel getPanel() throws RemoteException;
    public IListTank getListTank() throws RemoteException;
}
