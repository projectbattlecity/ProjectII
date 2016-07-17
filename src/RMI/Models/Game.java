/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Models;

import RMI.Interface.IGame;
import ServeMap.ServerFrame;
import ServeMap.ServerPanel;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

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
    
}
