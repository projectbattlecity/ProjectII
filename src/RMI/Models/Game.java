/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Models;

import Models.Tank;
import RMI.Interface.IGame;
import RMI.Interface.IListTank;
import ServeMap.ServerFrame;
import ServeMap.ServerPanel;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ConcurrentLinkedQueue;
import utils.utils;

/**
 *
 * @author DK
 */
public class Game extends UnicastRemoteObject implements IGame{

    public Game() throws RemoteException {
        super();
    }

    
    
    @Override
    public ServerFrame getFrame() throws RemoteException {
        return new ServerFrame();
    }

    @Override
    public ServerPanel getPanel() throws RemoteException {
        return new ServerPanel();
    }

    @Override
    public IListTank getListTank() throws RemoteException {
        IListTank t = new ListTank();
        t.setTanks(utils.map.tanks);
        return t;
    }
    
}
