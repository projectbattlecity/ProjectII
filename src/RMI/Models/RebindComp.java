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
import Models.Tank;
import RMI.Client.StartClient;
import static RMI.Client.StartClient.mapHelperRemote;
import RMI.Server.StartServer;
import java.rmi.RemoteException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.standard.JobStateReason;
import javax.swing.Timer;

/**
 *
 * @author DK
 */
public class RebindComp {

    private Home home;
    private Queue<RockWall> rockWalls;
    private Queue<StoneWall> stoneWalls;
    private Queue<HomeWall> homeWalls;
    private Queue<EnemyBase> obBases;
    private Queue<Tank> tanks;
    private Queue<Enemy> enemyTanks;
    private Queue<Bullet> bullets;
    private Queue<Explosion> explosions;
    private Queue<BigExplosion> bigExplosions;
    private int genRate;
    private Timer timer;
    Queue<Item> items;

    public Queue<Item> getItems() {
        Queue<Item> itemsDraw = new ConcurrentLinkedDeque<>();
        
        try {
            Queue<Item> sItems = StartClient.mapHelperRemote.getItems();
            for (Item s : sItems) {
                itemsDraw.add(new Item(s.x, s.y, s.index));
            }
        } catch (RemoteException ex) {
            Logger.getLogger(RebindComp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return  itemsDraw;
    }

    public void setItems(Queue<Item> items) {
        this.items = items;
    }
    
    public int getGenRate() {
        int rate = 0;
        try {
            rate = StartClient.mapHelperRemote.getGenRate();
        } catch (RemoteException ex) {
            Logger.getLogger(RebindComp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rate;
    }

    public void setGenRate(int genRate) {
        this.genRate = genRate;
    }

    public Timer getTimer() {
        Timer t = null;
        try {
            t = StartClient.mapHelperRemote.getTimer();
        } catch (RemoteException ex) {
            Logger.getLogger(RebindComp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public Queue<Bullet> getBullets() {
        Queue<Bullet> bulletsDraw = new ConcurrentLinkedQueue<>();

        try {
            Queue<Bullet> serverBullets = StartClient.mapHelperRemote.getBullets();
            for (Bullet s : serverBullets) {
                Bullet b = new Bullet(s.x, s.y, s.move, s.tank_level, s.checkPlayer);
                b.bulletMove();
                bulletsDraw.add(b);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(RebindComp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bulletsDraw;
    }

    public void setBullets(Queue<Bullet> bullets) {
        this.bullets = bullets;
    }

    public Queue<Explosion> getExplosions() {
        Queue<Explosion> ExplosionsDraw = new ConcurrentLinkedQueue<>();

        try {
            Queue<Explosion> serverExplosions = StartClient.mapHelperRemote.getExplosions();
            for (Explosion explosion : serverExplosions) {
                ExplosionsDraw.clear();
                ExplosionsDraw.add(new Explosion(explosion.x, explosion.y, explosion.live, explosion.step));
            }
        } catch (RemoteException ex) {
            Logger.getLogger(RebindComp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ExplosionsDraw;
    }

    public void setExplosions(Queue<Explosion> explosions) {
        this.explosions = explosions;
    }

    public Queue<BigExplosion> getBigExplosions() {
        Queue<BigExplosion> BigExplosionsDraw = new ConcurrentLinkedQueue<>();

        try {
            Queue<BigExplosion> serverBigExplosions = StartClient.mapHelperRemote.getBigExplosions();
            for (BigExplosion explosion : serverBigExplosions) {
                BigExplosionsDraw.clear();
                BigExplosionsDraw.add(new BigExplosion(explosion.x, explosion.y, explosion.live, explosion.step));
            }
        } catch (RemoteException ex) {
            Logger.getLogger(RebindComp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return BigExplosionsDraw;
    }

    public void setBigExplosions(Queue<BigExplosion> bigExplosions) {
        this.bigExplosions = bigExplosions;
    }

    public Queue<Enemy> getEnemyTanks() {
        Queue<Enemy> enemyDraw = new ConcurrentLinkedQueue<>();

        try {
            Queue<Enemy> serverEnemys = StartClient.mapHelperRemote.getEnemyTanks();
            for (Enemy s : serverEnemys) {
                enemyDraw.add(new Enemy(s.x, s.y, s.tank_level, s.move));
            }

        } catch (RemoteException ex) {
            Logger.getLogger(RebindComp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return enemyDraw;
    }

    public void setEnemyTanks(Queue<Enemy> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    public Queue<Player> getTanks() {
        Queue<Player> tanksDraw = new ConcurrentLinkedQueue<>();
        try {
            Queue<Player> serverTanks = StartClient.mapHelperRemote.getTanks();
            for (Player tank : serverTanks) {
                tanksDraw.add(new Player(tank.x, tank.y, tank.move, tank.clientID,tank.tank_level));
            }
        } catch (RemoteException ex) {
            Logger.getLogger(RebindComp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tanksDraw;
    }

    public void setTanks(Queue<Tank> tanks) {
        this.tanks = tanks;
    }

    public Home getHome() {
        Home homeDraw = null;

        try {
            Home sHome = mapHelperRemote.getHome();
            homeDraw = new Home(sHome.x, sHome.y);
        } catch (RemoteException ex) {
            Logger.getLogger(RebindComp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return homeDraw;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public Queue<RockWall> getRockWalls() {
        Queue<RockWall> rockWallsDraw = new ConcurrentLinkedQueue<>();
        try {
            Queue<RockWall> serverRockWalls = StartClient.mapHelperRemote.getRockWalls();
            for (RockWall s : serverRockWalls) {
                rockWallsDraw.add(new RockWall(s.x, s.y));
            }
        } catch (RemoteException ex) {
            Logger.getLogger(RebindComp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rockWallsDraw;
    }

    public void setRockWalls(Queue<RockWall> rockWalls) {
        this.rockWalls = rockWalls;
    }

    public Queue<StoneWall> getStoneWalls() {
        Queue<StoneWall> stoneWallsDraw = new ConcurrentLinkedQueue<>();
        try {
            Queue<StoneWall> serverStoneWalls = StartClient.mapHelperRemote.getStoneWalls();
            for (StoneWall s : serverStoneWalls) {
                stoneWallsDraw.add(new StoneWall(s.x, s.y));
            }
        } catch (RemoteException ex) {
            Logger.getLogger(RebindComp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stoneWallsDraw;
    }

    public void setStoneWalls(Queue<StoneWall> stoneWalls) {
        this.stoneWalls = stoneWalls;
    }

    public Queue<EnemyBase> getObBases() {
        Queue<EnemyBase> enemyBases = new ConcurrentLinkedQueue<>();

        try {
            Queue<EnemyBase> obases = StartClient.mapHelperRemote.getObases();
            for (EnemyBase obase : obases) {
                enemyBases.add(new EnemyBase(obase.x, obase.y));

            }
        } catch (RemoteException ex) {
            Logger.getLogger(RebindComp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return enemyBases;
    }

    public void setObBases(Queue<EnemyBase> obBases) {
        this.obBases = obBases;
    }

    public Queue<HomeWall> getHomeWalls() {
        Queue<HomeWall> homeWallsDraw = new ConcurrentLinkedQueue<>();

        try {
            Queue<HomeWall> serverHomeWalls = StartClient.mapHelperRemote.getHomeWalls();
            for (HomeWall hw : serverHomeWalls) {
                homeWallsDraw.add(new HomeWall(hw.x, hw.y, hw.width, hw.height, hw.index));
            }
        } catch (RemoteException ex) {
            Logger.getLogger(RebindComp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return homeWallsDraw;
    }

    public void setHomeWalls(Queue<HomeWall> homeWalls) {
        this.homeWalls = homeWalls;
    }
}
