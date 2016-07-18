/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Interface;

import Models.Tank;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author DK
 */
public interface IListTank extends Remote{
    public ConcurrentLinkedQueue<Tank> getTanks() throws RemoteException;
    public void setTanks(ConcurrentLinkedQueue<Tank> _tanks) throws RemoteException;

    
}
