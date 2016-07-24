/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Interfaces;

import Models.Bullet;
import Models.Player;
import Models.Tank;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author DK
 */
public interface IGameControl extends Remote{
    public void tankMove(Player t, Tank.Move m) throws RemoteException;
    public Tank tankFromClient(Player t) throws RemoteException;
    public void generateEnemy()throws RemoteException;
    public int getClientID() throws RemoteException;
    public void tankFire(Bullet b) throws RemoteException;
    public String getGameName()throws RemoteException;
}
