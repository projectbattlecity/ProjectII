/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Server;

import Models.BigExplosion;
import Models.Bullet;
import Models.Enemy;
import Models.Explosion;
import Models.Tank;
import RMI.Models.GameControl;
import RMI.Models.MapHelper;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.utils;

/**
 *
 * @author DK
 */
public class StartServer {

    public static MapHelper helper = null;
    public static int clientID = 0;
    public static GameControl control = null;

    public StartServer() {
        utils.map.AddComponents();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    for (Tank tank : utils.map.tanks) {
                        utils.map.tankCollide(tank);
                    }
                    for (Enemy enemyTank : utils.map.enemyTanks) {
                        utils.map.tankCollide(enemyTank);
                    }
                    utils.map.genNewEnemy();
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(StartServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }).start();
        try {
            helper = new MapHelper();
            initMapHelper();
            control = new GameControl();
            //LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://localhost:1099/MapHelper", helper);
            Naming.rebind("rmi://localhost:1099/GameControl", control);

        } catch (RemoteException ex) {
            Logger.getLogger(StartServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(StartServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    for (Bullet bullet : helper.getBullets()) {
                        if (!utils.map.bulletCollide(bullet)) {
                            bullet.bulletMove();
                        }
                    }
                    for (Enemy enemyTank : helper.getEnemyTanks()) {
                        enemyTank.tankMove(enemyTank.move());

                    }
                    for (Explosion explosion : helper.getExplosions()) {
                        if (!explosion.live) {
                            helper.getExplosions().remove(explosion);
                            break;
                        }
                        if (explosion.step >= 16) {
                            explosion.live = false;
                            explosion.step = 0;
                            break;
                        }
                        explosion.step++;
                    }
                    for (BigExplosion bigExplosion : helper.getBigExplosions()) {
                        if (!bigExplosion.live) {
                            helper.getBigExplosions().remove(bigExplosion);
                            break;
                        }
                        if (bigExplosion.step >= 16) {
                            bigExplosion.live = false;
                            bigExplosion.step = 0;
                            break;
                        }
                        bigExplosion.step++;
                    }

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(StartServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();

    }

    public static void main(String[] args) {
        new StartServer();
    }

    private static void initMapHelper() {
        utils.map.genEnemy();
        helper.setTanks(utils.map.tanks);
        helper.setHome(utils.map.home);
        helper.setHomeWalls(utils.map.homeWalls);
        helper.setObases(utils.map.obases);
        helper.setStoneWalls(utils.map.stoneWalls);
        helper.setRockWalls(utils.map.rockWalls);
        helper.setEnemyTanks(utils.map.enemyTanks);
        helper.setBullets(utils.map.bullets);
        helper.setBigExplosions(utils.map.bigExplosions);
        helper.setExplosions(utils.map.explosions);
        helper.setGenRate(utils.map.genRate);
        helper.setTimer(utils.map.timer);
        helper.setItems(utils.map.items);

    }
}
