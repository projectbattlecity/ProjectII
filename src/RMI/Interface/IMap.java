/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Interface;

import Models.BigExplosion;
import Models.Bullet;
import Models.Decor;
import Models.Enemy;
import Models.EnemyBase;
import Models.Explosion;
import Models.Home;
import Models.HomeWall;
import Models.River;
import Models.Road;
import Models.RockWall;
import Models.StoneWall;
import Models.Swamp;
import Models.Tank;
import Models.Tree;
import java.awt.Graphics;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.swing.Timer;

/**
 *
 * @author DK
 */
public interface IMap extends Remote{
    
    public Queue<RockWall> rockWalls = new ConcurrentLinkedQueue<>(); // dùng concurrentLinkedQueue vì arraylist hoặc list đều không hỗ trợ đa luồng
    public Queue<StoneWall> stoneWalls = new ConcurrentLinkedQueue<>();
    public Queue<River> rivers = new ConcurrentLinkedQueue<>();
    public Queue<Tree> trees = new ConcurrentLinkedQueue<>();
    public Queue<Swamp> swamps = new ConcurrentLinkedQueue<>();
    public Queue<HomeWall> homeWalls = new ConcurrentLinkedQueue<>();
    public Queue<EnemyBase> obases = new ConcurrentLinkedQueue<>();
    public Queue<Decor> decors = new ConcurrentLinkedQueue<>();
    public Queue<Explosion> explosions = new ConcurrentLinkedQueue<>();
    public Queue<BigExplosion> bigExplosions = new ConcurrentLinkedQueue<>();
    public Queue<Road> roads = new ConcurrentLinkedQueue<>();
    public Queue<Bullet> bullets = new ConcurrentLinkedQueue<>();
    public Queue<Tank> tanks = new ConcurrentLinkedQueue<>();
    public Queue<Enemy> enemyTanks = new ConcurrentLinkedQueue<>();
    Timer timer = null;
    int genRate = 0;
    Home home = null;
    int remainEnemy = 20;
    
    
    
    
    
    public void AddComponents() throws RemoteException;
    public void clearMap() throws RemoteException;
    public void drawTrees(Graphics g) throws RemoteException;
    public void drawStaticComponents(Graphics g) throws RemoteException;
    public void drawNonStaticComponents(Graphics g) throws RemoteException;
    public void genEnemy() throws RemoteException;
    public void drawTank(Graphics g) throws RemoteException;
    public void drawEnemyBases() throws RemoteException;
    public void drawHomeWall()  throws RemoteException;
    public void drawHome(Graphics g) throws RemoteException;
    public void drawLevel(int level) throws RemoteException;
    public void drawBackGround(Graphics g) throws RemoteException;
    public boolean bulletCollide(Bullet bullet) throws RemoteException;
    public void tankCollide(Tank tank) throws RemoteException;
    
    
}
