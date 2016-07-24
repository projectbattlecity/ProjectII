/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Models;

import Models.Tank;
import RMI.Interface.IGame;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Queue;
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
    public void addClientTank() throws RemoteException {
        utils.objMap.tanks.add(utils.clientTank);
    }


    @Override
    public String getGameName() throws RemoteException {
        return utils.gameName;
    }


    @Override
    public Queue<Tank> getTank() throws RemoteException {
        return utils.objMap.tanks;
    }

    

    
}
