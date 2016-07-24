/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Interfaces;

import Models.BigExplosion;
import Models.Bullet;
import Models.Enemy;
import Models.EnemyBase;
import Models.Explosion;
import Models.Home;
import Models.HomeWall;
import Models.Item;
import Models.Player;
import Models.RockWall;
import Models.StoneWall;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Queue;
import javax.swing.Timer;

/**
 *
 * @author DK
 */
public interface IMapHelper extends Remote {

    public Home getHome() throws RemoteException;

    public void setHome(Home home) throws RemoteException;

    public Queue<RockWall> getRockWalls() throws RemoteException;

    public void setRockWalls(Queue<RockWall> rockWalls) throws RemoteException;

    public Queue<StoneWall> getStoneWalls() throws RemoteException;

    public void setStoneWalls(Queue<StoneWall> stoneWalls) throws RemoteException;

    public Queue<HomeWall> getHomeWalls() throws RemoteException;

    public void setHomeWalls(Queue<HomeWall> homeWalls) throws RemoteException;

    public Queue<EnemyBase> getObases() throws RemoteException;

    public void setObases(Queue<EnemyBase> obases) throws RemoteException;

    public Queue<Player> getTanks() throws RemoteException;

    public void setTanks(Queue<Player> tanks) throws RemoteException;

    public Queue<Enemy> getEnemyTanks() throws RemoteException;

    public void setEnemyTanks(Queue<Enemy> enemyTanks) throws RemoteException;

    public Queue<Bullet> getBullets() throws RemoteException;

    public void setBullets(Queue<Bullet> bullets) throws RemoteException;

    public Queue<Explosion> getExplosions() throws RemoteException;

    public void setExplosions(Queue<Explosion> explosions) throws RemoteException;

    public Queue<BigExplosion> getBigExplosions() throws RemoteException;

    public void setBigExplosions(Queue<BigExplosion> bigExplosions) throws RemoteException;

    public Timer getTimer() throws RemoteException;

    public void setTimer(Timer timer) throws RemoteException;

    public int getGenRate() throws RemoteException;

    public void setGenRate(int genRate) throws RemoteException;

    public Queue<Item> getItems() throws RemoteException;

    public void setItems(Queue<Item> items) throws RemoteException;
}
