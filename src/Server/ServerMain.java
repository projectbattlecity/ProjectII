/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Models.Enemy;
import Models.Player;
import ServeMap.ServerFrame;
import Models.Tank;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        utils.utils.map.enemyTank.add(new Enemy(500, 0, 0));
        utils.utils.map.enemyTank.add(new Enemy(300, 0, 0));
        utils.utils.map.enemyTank.add(new Enemy(550, 0, 0));
        utils.utils.map.enemyTank.add(new Enemy(0, 0, 0));
        utils.utils.map.enemyTank.add(new Enemy(250, 0, 0));
        utils.utils.map.enemyTank.get(0).tankMove(Tank.Move.D);
        utils.utils.map.enemyTank.get(1).tankMove(Tank.Move.D);
        utils.utils.map.enemyTank.get(2).tankMove(Tank.Move.D);
        utils.utils.map.enemyTank.get(3).tankMove(Tank.Move.D);
        utils.utils.map.enemyTank.get(4).tankMove(Tank.Move.D);
        
        
//       
    }

}
