/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Models;

import Models.Bullet;
import Models.Explosion;
import Models.Player;
import Models.Tank;
import RMI.Interfaces.IGameControl;
import RMI.Server.StartServer;
import static RMI.Server.StartServer.clientID;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author DK
 */
public class GameControl extends UnicastRemoteObject implements IGameControl {

    public GameControl() throws RemoteException {
        super();
    }

    public Tank tankFromClient(Player t) {

        if (t.clientID == 0) {
            StartServer.helper.getTanks().add(new Player(utils.utils.x1, utils.utils.y1, t.move, t.clientID));
        }
        else if (t.clientID == 1) {
            StartServer.helper.getTanks().add(new Player(utils.utils.x2, utils.utils.y2, t.move, t.clientID));
        }
        System.out.print("added client ");
        System.out.println(t.clientID);
        return t;
    }

    public void tankMove(Player t, Tank.Move m) {
        for (Player tank : StartServer.helper.getTanks()) {
            if (t.clientID == tank.clientID) {
                tank.tankMove(m);
            }
        }
    }

    public void tankFire(Bullet b) {
        StartServer.helper.getBullets().add(new Bullet(b.x, b.y, b.move, b.tank_level, b.checkPlayer));
    }

    public void generateEnemy() {
        utils.utils.map.genEnemy();
    }

    public int getClientID() {
        return StartServer.clientID++;
    }

    public String getGameName() {
        return utils.utils.gameName;
    }
}
