/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Models.Player;
import ServeMap.ServerFrame;
import Models.Tank;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author DK
 */
public class ServerMain {

  
    public static Player serverTank = new Player(0, 0);
    public static Player clientTank = new Player(0, 100);

    public static void main(String[] args) {
        new ServerFrame();
        
        utils.utils.map.tanks.add(serverTank);
        utils.utils.map.tanks.add(clientTank);
        
//       
    }

}
