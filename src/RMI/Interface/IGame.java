/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Interface;

import Models.Tank;
import ServeMap.ServerFrame;
import ServeMap.ServerPanel;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Queue;

/**
 *
 * @author DK
 */
public interface IGame extends Remote{
    public void addClientTank() throws RemoteException;
    public String getGameName() throws RemoteException;
    public Queue<Tank> getTank() throws RemoteException;
}
