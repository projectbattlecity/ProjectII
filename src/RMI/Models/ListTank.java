/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Models;

import Models.Tank;
import RMI.Interface.IListTank;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author DK
 */
public class ListTank extends UnicastRemoteObject implements IListTank, Serializable{
    
    private ConcurrentLinkedQueue<Tank> _tanks;

    public ListTank() throws RemoteException {
        super();
    }

    public ConcurrentLinkedQueue<Tank> getTanks() {
        return _tanks;
    }

    public void setTanks(ConcurrentLinkedQueue<Tank> _tanks) {
        this._tanks = _tanks;
    }
    
}
