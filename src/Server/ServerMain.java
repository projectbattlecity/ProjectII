/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Models.Player;
import ServeMap.ServerFrame;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DK
 */
public class ServerMain {

  
    
    public static Player serverTank = new Player(0, 0);

    public static void main(String[] args) {
        new ServerFrame();
        
        utils.utils.map.tanks.add(serverTank);
        
        
        
    }

}
