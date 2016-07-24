/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import ServeMap.ServerFrame;
import ServeMap.ServerPanel;
import Server.ServerMain;
import static Server.ServerMain.serverTank;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.annotation.Resource;
import javax.swing.Timer;
import static utils.utils.audio;
import static utils.utils.tk;

/**
 *
 * @author c1409l0937
 */
public final class Maps implements Serializable {

    //gen enemy
    public Timer timer;
    public int genRate = 0, genPos = 0;

    //map
    public Home home;
    public Random random;

    public Queue<RockWall> rockWalls = new ConcurrentLinkedQueue<>(); // dùng concurrentLinkedQueue vì arraylist hoặc list đều không hỗ trợ đa luồng
    public Queue<StoneWall> stoneWalls = new ConcurrentLinkedQueue<>();
    public Queue<River> rivers = new ConcurrentLinkedQueue<>();
    public Queue<Tree> trees = new ConcurrentLinkedQueue<>();
    public Queue<Swamp> swamps = new ConcurrentLinkedQueue<>();
    public Queue<HomeWall> homeWalls = new ConcurrentLinkedQueue<>();
    public Queue<EnemyBase> obases = new ConcurrentLinkedQueue<>();
    public Queue<Decor> decors = new ConcurrentLinkedQueue<>();
    public Queue<Bullet> bullets = new ConcurrentLinkedQueue<>();
    public Queue<Explosion> explosions = new ConcurrentLinkedQueue<>();
    public Queue<BigExplosion> bigExplosions = new ConcurrentLinkedQueue<>();
    public Queue<Road> roads = new ConcurrentLinkedQueue<>();
    public Queue<Item> items = new ConcurrentLinkedQueue<>();
    public Queue<Effect> effects = new ConcurrentLinkedQueue<>();
    public Queue<Player> tanks = new ConcurrentLinkedQueue<>();
    public Queue<Enemy> enemyTanks = new ConcurrentLinkedQueue<>();

    //item
    public Timer itemTimer;
    public int time = 0;

    public Maps() {
        testItems();
    }

    //thêm các components cho map
    public void AddComponents() {
        drawLevel(utils.utils.level);
        drawHomeWall();
        drawEnemyBases();
    }

    //clear toàn bộ map
    public void clearMap() {
        rockWalls.clear();
        stoneWalls.clear();
        rivers.clear();
        trees.clear();
        swamps.clear();
        homeWalls.clear();
        obases.clear();
        decors.clear();
        bullets.clear();
        tanks.clear();
        enemyTanks.clear();
        explosions.clear();
        bigExplosions.clear();
        roads.clear();
        items.clear();
        effects.clear();
    }

    // === các hàm vẽ map ===
    //vẽ đường kẻ
    public void drawLines(Graphics g) {
        for (int i = 0; i < ServerPanel.screen_height / 40; i++) {
            g.drawLine(0, i * 40, 800, i * 40);
        }
        for (int i = 0; i < ServerPanel.screen_width / 40; i++) {
            g.drawLine(i * 40, 0, i * 40, 600);
        }
    }

    //vẽ tanks
    public void drawTank(Graphics g) {
        for (Tank tank : tanks) {
            tankCollide(tank);
            tank.drawTank(g);
        }

        for (Enemy enemy : enemyTanks) {
            tankCollide(enemy);
            enemy.tankMove(enemy.move());
            enemy.drawTank(g);
        }

    }

    //vẽ cây
    public void drawTrees(Graphics g) {
        for (Tree tree : trees) {
            tree.draw(g);
        }
    }

    //vẽ các components không thay đổi
    public void drawStaticComponents(Graphics g) {

        drawBackGround(g);

        for (Swamp swamp : swamps) {
            swamp.draw(g);
        }

        for (Road road : roads) {
            road.draw(g);
        }

        for (River river : rivers) {
            river.draw(g);
        }

        for (Decor decor : decors) {
            decor.draw(g);
        }
    }

    //vẽ các components thay đổi
    public void drawNonStaticComponents(Graphics g) {
        drawHome(g);

        for (EnemyBase enemyBase : obases) {
            enemyBase.draw(g);
        }
        for (HomeWall homeWall : homeWalls) {
            homeWall.draw(g);
        }
        for (StoneWall stoneWall : stoneWalls) {
            stoneWall.draw(g);
        }
        for (RockWall rockWall : rockWalls) {
            rockWall.draw(g);
        }
        for (Item item : items) {
            item.draw(g);
        }
        for (Bullet bullet : bullets) {
            bullet.bulletMove();
            if (!bulletCollide(bullet)) {
                bullet.drawBullet(g);
            }
        }
        for (Explosion ex : explosions) {
            ex.draw(g);
        }
        for (BigExplosion ex : bigExplosions) {
            ex.draw(g);
        }
        for (Effect ef : effects) {
            ef.draw(g);
        }

    }

    public void drawEnemyBases() {
        obases.add(new EnemyBase(9 * 40, 0));
        obases.add(new EnemyBase(2 * 40, 0));
        obases.add(new EnemyBase(16 * 40, 0));

    }

    public void drawHomeWall() {
        homeWalls.add(new HomeWall(8 * 40, 14 * 40, 40, 40, 1));
        homeWalls.add(new HomeWall(8 * 40 + 10, 13 * 40, 19, 40, 2));
        homeWalls.add(new HomeWall(8 * 40, 12 * 40, 40, 40, 3));
        homeWalls.add(new HomeWall(9 * 40, 12 * 40 + 10, 40, 19, 4));
        homeWalls.add(new HomeWall(10 * 40, 12 * 40 + 10, 40, 19, 4));
        homeWalls.add(new HomeWall(11 * 40, 12 * 40, 40, 40, 5));
        homeWalls.add(new HomeWall(11 * 40 + 10, 13 * 40, 19, 40, 6));
        homeWalls.add(new HomeWall(11 * 40, 14 * 40, 40, 40, 1));
    }

    public void drawHome(Graphics g) {
        home.draw(g);
    }

    public void drawLevel(int level) {
        home = new Home(9 * 40, 13 * 40);
        switch (level) {
            case 1:
                //river
                for (int i = 2; i < 5; i++) {
                    for (int j = 5; j < 10; j++) {
                        rivers.add(new River(i * 40, j * 40));
                        rivers.add(new River((20 - i - 1) * 40, (15 - j - 1) * 40));
                    }
                }
                //swamps
                for (int i = 6; i < 14; i++) {
                    for (int j = 4; j < 6; j++) {
                        swamps.add(new Swamp(i * 40, j * 40));
                    }
                }
                for (int i = 6; i < 14; i++) {
                    for (int j = 7; j < 11; j++) {
                        swamps.add(new Swamp(i * 40, j * 40));
                    }
                }

                //road
                for (int i = 4; i < 11; i++) {
                    roads.add(new Road(0, i * 40));
                    roads.add(new Road(19 * 40, i * 40));
                }

                //common walls
                for (int i = 1; i < 6; i++) {
                    homeWalls.add(new HomeWall(i * 40, 4 * 40, 40, 40, 0));
                    homeWalls.add(new HomeWall(i * 40, 10 * 40, 40, 40, 0));
                    homeWalls.add(new HomeWall((20 - i - 1) * 40, 4 * 40, 40, 40, 0));
                    homeWalls.add(new HomeWall((20 - i - 1) * 40, 10 * 40, 40, 40, 0));
                }
                for (int i = 4; i < 10; i++) {
                    homeWalls.add(new HomeWall(40, i * 40, 40, 40, 0));
                    homeWalls.add(new HomeWall(5 * 40, i * 40, 40, 40, 0));
                    homeWalls.add(new HomeWall((20 - 1 - 1) * 40, i * 40, 40, 40, 0));
                    homeWalls.add(new HomeWall((20 - 5 - 1) * 40, i * 40, 40, 40, 0));
                }
                for (int i = 6; i < 14; i++) {
                    homeWalls.add(new HomeWall(i * 40, 6 * 40, 40, 40, 0));
                }
                //rock walls
                for (int i = 7; i < 9; i++) {
                    for (int j = 9; j < 11; j++) {
                        rockWalls.add(new RockWall(i * 40, j * 40));
                        rockWalls.add(new RockWall((20 - i - 1) * 40, j * 40));
                    }
                }
                //stone walls
                for (int i = 1; i < 5; i++) {
                    stoneWalls.add(new StoneWall(i * 40, 13 * 40));
                    stoneWalls.add(new StoneWall((20 - i - 1) * 40, 13 * 40));
                }
                stoneWalls.add(new StoneWall(40, 14 * 40));
                stoneWalls.add(new StoneWall(4 * 40, 14 * 40));
                stoneWalls.add(new StoneWall((20 - 1 - 1) * 40, 14 * 40));
                stoneWalls.add(new StoneWall((20 - 4 - 1) * 40, 14 * 40));

                //trees
                for (int i = 1; i < 19; i++) {
                    for (int j = 2; j < 4; j++) {
                        trees.add(new Tree(i * 40, j * 40));
                    }
                }

                //decor
                for (int i = 2; i < 4; i++) {
                    decors.add(new Decor(i * 40, 14 * 40, 40, 40, 6));
                    decors.add(new Decor((20 - i - 1) * 40, 14 * 40, 40, 40, 6));
                }

                decors.add(new Decor(6 * 40, 7 * 40, 40, 80, 5));
                decors.add(new Decor(13 * 40, 7 * 40, 40, 80, 5));

                decors.add(new Decor(2 * 40, 12 * 40, 80, 40, 8));
                decors.add(new Decor((20 - 3 - 1) * 40, 12 * 40, 80, 40, 8));
                break;
            case 2:
                //homewall
                for (int i = 2; i < 20 - 2; i++) {
                    homeWalls.add(new HomeWall(i * 40, 4 * 40, 40, 40, 0));
                    homeWalls.add(new HomeWall(i * 40, 5 * 40, 40, 40, 0));
                }
                for (int j = 6; j < 11; j++) {
                    homeWalls.add(new HomeWall(8 * 40, j * 40, 40, 40, 0));
                    homeWalls.add(new HomeWall(11 * 40, j * 40, 40, 40, 0));
                }

                //river
                for (int i = 9; i < 11; i++) {
                    for (int j = 6; j < 11; j++) {
                        rivers.add(new River(i * 40, j * 40));
                    }
                }
                for (int i = 2; i < 6; i++) {
                    for (int j = 6; j < 11; j++) {
                        rivers.add(new River(i * 40, j * 40));
                    }
                }
                for (int i = 14; i < 18; i++) {
                    for (int j = 6; j < 11; j++) {
                        rivers.add(new River(i * 40, j * 40));
                    }
                }

                //trees
                for (int i = 6; i < 8; i++) {
                    for (int j = 6; j < 11; j++) {
                        trees.add(new Tree(i * 40, j * 40));
                    }
                }
                for (int i = 12; i < 14; i++) {
                    for (int j = 6; j < 11; j++) {
                        trees.add(new Tree(i * 40, j * 40));
                    }
                }
                for (int i = 0; i < 2; i++) {
                    for (int j = 6; j < 11; j++) {
                        trees.add(new Tree(i * 40, j * 40));
                    }
                }
                for (int i = 18; i < 20; i++) {
                    for (int j = 6; j < 11; j++) {
                        trees.add(new Tree(i * 40, j * 40));
                    }
                }

                //swamp
                for (int i = 2; i < 20 - 2; i++) {
                    swamps.add(new Swamp(i * 40, 2 * 40));
                    swamps.add(new Swamp(i * 40, 3 * 40));

                }
                //road
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 15; j++) {
                        roads.add(new Road(i * 40, j * 40));
                        roads.add(new Road((20 - i - 1) * 40, (15 - j - 1) * 40));
                    }
                }

                //stone walls
                for (int i = 2; i < 6; i++) {
                    for (int j = 13; j < 15; j++) {
                        stoneWalls.add(new StoneWall(i * 40, j * 40));
                        stoneWalls.add(new StoneWall((20 - i - 1) * 40, j * 40));
                    }
                }
                break;
            default:
                break;
        }
    }

    public void drawBackGround(Graphics g) {
        new Background().drawBg(g);
    }

    //các hàm bắt va chạm của đạn và tank
    public boolean bulletCollide(Bullet bullet) {
        bullet.hitHome();
        for (Tank tank : tanks) {
            if (bullet.hitTank(tank)) {
                return true;
            }
        }

        for (Tank tank : enemyTanks) {
            if (bullet.hitTank(tank)) {
                return true;
            }
        }

        for (Bullet b : bullets) {
            if (b.equals(bullet)) {
                continue;
            }
            if (bullet.hitBullet(b)) {
                return true;
            }
        }

        for (EnemyBase enemyBase : obases) {
            if (bullet.hitEnemyBase(enemyBase)) {
                return true;
            }
        }
        for (RockWall rockWall : rockWalls) {
            if (bullet.hitWall(rockWall)) {
                return true;
            }
        }
        for (HomeWall homeWall : homeWalls) {
            if (bullet.hitWall(homeWall)) {
                return true;
            }
        }
        for (StoneWall stoneWall : stoneWalls) {
            if (bullet.hitWall(stoneWall)) {
                return true;
            }
        }
        for (Decor decor : decors) {
            if (bullet.hitDecor(decor)) {
                return true;
            }
        }
        return false;
    }

    public void tankCollide(Tank tank) {
        tank.collideWithTanks(tanks);
        tank.collideWithEnemys(enemyTanks);
        for (Decor decor : decors) {
            tank.collideDecor(decor);
        }
        for (EnemyBase enemyBase : obases) {
            tank.collideEnemyBase(enemyBase);
        }

        for (HomeWall homeWall : homeWalls) {
            tank.collideWithWall(homeWall);
        }

        for (StoneWall stoneWall : stoneWalls) {
            tank.collideWithWall(stoneWall);
        }

        for (RockWall rockWall : rockWalls) {
            tank.collideWithWall(rockWall);
        }

        for (River river : rivers) {
            tank.collideRiver(river);
        }

        if (tank.moveSpeed != (tank instanceof Player ? 4 : 2)) {
            tank.moveSpeed = (tank instanceof Player ? 4 : 2);
        }

        for (Swamp swamp : swamps) {
            if (tank.collideSwamp(swamp)) {
                tank.moveSpeed = (tank instanceof Player ? 2 : 1);
            }
        }

        for (Road road : roads) {
            if (tank.collideRoad(road)) {
                tank.moveSpeed = (tank instanceof Player ? 7 : 5);
            }
        }

        for (Item item : items) {
            if (tank.collideWithItem(item)) {
                audio.collectItems();
                effects.add(new Effect(tank.x, tank.y));
                switch (item.getIndex()) {
                    case 0:
                        //destroy all enemies
                        audio.enemyDeath();
                        enemyTanks.clear();
                        items.remove(item);
                        checkWin();
                        break;
                    case 1:
                        //add tank level or add life
                        if (tank.tank_level != 3) {
                            tank.tank_level++;
                        } else {
                            tank.lifeAmount++;
                        }
                        items.remove(item);
                        break;
                    case 2:
                        //draw stone home wall
                        drawCombatHomeWall();
                        items.remove(item);
                        break;
                    case 3:
                        //set tank level = 3 or add life
                        if (tank.tank_level != 3) {
                            tank.tank_level = 3;
                        } else {
                            tank.lifeAmount++;
                        }
                        items.remove(item);
                        break;
                    case 4:
                        //enable shield for 5s
                        enableShield(tank);
                        items.remove(item);
                        break;
                    case 5:
                        //destroy 1 enemy
                        if (!enemyTanks.isEmpty()) {
                            bigExplosions.add(new BigExplosion(enemyTanks.element().x, enemyTanks.element().y));
                            enemyTanks.remove();
                            items.remove(item);
                            checkWin();
                        }

                        break;
                    case 6:
                        //add life
                        tank.lifeAmount++;
                        items.remove(item);
                        break;
                }
            }
        }
        tank.collideHome(home);
    }

    //item effect
    public void enableShield(final Tank tank) {
        tank.setShield(true);
        itemTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time++;
                if (time >= 4) {
                    tank.setShield(false);
                    time = 0;
                    itemTimer.stop();
                }
            }
        });
        itemTimer.start();
    }

    public void drawCombatHomeWall() {
        rockWalls.add(new RockWall(8 * 40, 14 * 40));
        rockWalls.add(new RockWall(8 * 40, 13 * 40));
        rockWalls.add(new RockWall(8 * 40, 12 * 40));
        rockWalls.add(new RockWall(9 * 40, 12 * 40));
        rockWalls.add(new RockWall(10 * 40, 12 * 40));
        rockWalls.add(new RockWall(11 * 40, 12 * 40));
        rockWalls.add(new RockWall(11 * 40, 13 * 40));
        rockWalls.add(new RockWall(11 * 40, 14 * 40));
    }

    //gen enemy
    public void genNewEnemy() {
        random = new Random();
        if (!obases.isEmpty()) {
            if (enemyTanks.size() < 6) {
                genRate++;
                if (genRate >= 120) {
                    if (genPos % 2 == 0) {
                        enemyTanks.add(new Enemy(obases.element().x - 40, 0, random.nextInt(3)));
                    } else {
                        enemyTanks.add(new Enemy(obases.element().x + 80, 0, random.nextInt(3)));
                    }
                    EnemyBase temp = obases.poll();
                    obases.add(temp);
                    genPos++;
                    genRate = 0;

                }
            }
        }
    }

    public void genEnemy() {
        enemyTanks.add(new Enemy(40 * 1, 0, 0));
        enemyTanks.add(new Enemy(40 * 4, 0, 0));
        enemyTanks.add(new Enemy(40 * 8, 0, 0));
        enemyTanks.add(new Enemy(40 * 11, 0, 0));
        enemyTanks.add(new Enemy(40 * 15, 0, 0));
        enemyTanks.add(new Enemy(40 * 18, 0, 0));
    }

    public void genPlayer() {
        ServerMain.serverTank = new Player(utils.utils.x1, utils.utils.y1);
        utils.utils.map.tanks.add(serverTank);
        utils.utils.map.effects.add(new Effect(utils.utils.x1, utils.utils.y1));
    }

    public void testItems() {
        //test items
        items.add(new Item(7 * 40, 11 * 40, 0));
        items.add(new Item(8 * 40, 11 * 40, 1));
        items.add(new Item(9 * 40, 11 * 40, 2));
        items.add(new Item(10 * 40, 11 * 40, 3));
        items.add(new Item(11 * 40, 11 * 40, 4));
        items.add(new Item(12 * 40, 11 * 40, 5));
    }

    public boolean checkLose() {
        return ((!utils.utils.map.home.isLive()) || ServerMain.serverTank.lifeAmount == 0);

    }

    public void checkWin() {
        if (utils.utils.map.enemyTanks.size() == 0 && utils.utils.map.obases.size()==0) {
            System.out.println("ok da vao win");
            utils.utils.level++;
            utils.utils.map.clearMap();
            utils.utils.main.dispose();
            new ServerFrame();
        }
    }
}
