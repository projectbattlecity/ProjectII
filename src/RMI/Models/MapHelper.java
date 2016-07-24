/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Models;

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
import RMI.Interfaces.IMapHelper;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Queue;
import javax.swing.Timer;

/**
 *
 * @author DK
 */
public class MapHelper extends UnicastRemoteObject implements IMapHelper{
    
    Timer timer;
    int genRate = 0;
    Home home;
    Queue<RockWall> rockWalls;
    Queue<StoneWall> stoneWalls;
    Queue<HomeWall> homeWalls;
    Queue<EnemyBase> obases;
    Queue<Player> tanks;
    Queue<Enemy> enemyTanks;
    Queue<Bullet> bullets;
    Queue<Explosion> explosions;
    Queue<BigExplosion> bigExplosions;
    Queue<Item> items;
    
    
    
    public MapHelper() throws RemoteException {
        super();
    }

    public Queue<Item> getItems() {
        return items;
    }

    public void setItems(Queue<Item> items) {
        this.items = items;
    }
    
    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public int getGenRate() {
        return genRate;
    }

    public void setGenRate(int genRate) {
        this.genRate = genRate;
    }

    public Queue<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(Queue<Bullet> bullets) {
        this.bullets = bullets;
    }

    public Queue<Explosion> getExplosions() {
        return explosions;
    }

    public void setExplosions(Queue<Explosion> explosions) {
        this.explosions = explosions;
    }

    public Queue<BigExplosion> getBigExplosions() {
        return bigExplosions;
    }

    public void setBigExplosions(Queue<BigExplosion> bigExplosions) {
        this.bigExplosions = bigExplosions;
    }

    public Queue<Player> getTanks() {
        return tanks;
    }

    public void setTanks(Queue<Player> tanks) {
        this.tanks = tanks;
    }

    public Queue<Enemy> getEnemyTanks() {
        return enemyTanks;
    }

    public void setEnemyTanks(Queue<Enemy> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    public Queue<HomeWall> getHomeWalls() {
        return homeWalls;
    }

    public void setHomeWalls(Queue<HomeWall> homeWalls) {
        this.homeWalls = homeWalls;
    }

    public Queue<EnemyBase> getObases() {
        return obases;
    }

    public void setObases(Queue<EnemyBase> obases) {
        this.obases = obases;
    }

    public Queue<RockWall> getRockWalls() {
        return rockWalls;
    }

    public void setRockWalls(Queue<RockWall> rockWalls) {
        this.rockWalls = rockWalls;
    }

    public Queue<StoneWall> getStoneWalls() {
        return stoneWalls;
    }

    public void setStoneWalls(Queue<StoneWall> stoneWalls) {
        this.stoneWalls = stoneWalls;
    }
    
    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }
    
}
