/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import ServeMap.ServerPanel;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.swing.Timer;

/**
 *
 * @author c1409l0937
 */
public final class Maps implements ActionListener {

    Timer timer;
    int genRate = 0;
    Home home;

    Queue<RockWall> rockWalls = new ConcurrentLinkedQueue<>(); // dùng concurrentLinkedQueue vì arraylist hoặc list đều không hỗ trợ đa luồng
    Queue<StoneWall> stoneWalls = new ConcurrentLinkedQueue<>();
    Queue<River> rivers = new ConcurrentLinkedQueue<>();
    Queue<Tree> trees = new ConcurrentLinkedQueue<>();
    Queue<Swamp> swamps = new ConcurrentLinkedQueue<>();
    Queue<HomeWall> homeWalls = new ConcurrentLinkedQueue<>();
    Queue<EnemyBase> obases = new ConcurrentLinkedQueue<>();
    Queue<Decor> decors = new ConcurrentLinkedQueue<>();
    Queue<Bullet> bullets = new ConcurrentLinkedQueue<>();
    Queue<Explosion> explosions = new ConcurrentLinkedQueue<>();
    Queue<BigExplosion> bigExplosions = new ConcurrentLinkedQueue<>();
    Queue<Road> roads = new ConcurrentLinkedQueue<>();
    public Queue<Tank> tanks = new ConcurrentLinkedQueue<>();
    public Queue<Enemy> enemyTanks = new ConcurrentLinkedQueue<>();

    int remainEnemy = 20;

    public Maps() {

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
        explosions.clear();
        bigExplosions.clear();
        roads.clear();
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
//            tankCollide(enemy);
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
        home = new Home(9 * 40, 13 * 40);
        home.draw(g);
    }

    public void drawLevel(int level) {
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

        if (tank.moveSpeed != 5) {
            tank.moveSpeed = 5;
        }
        for (Swamp swamp : swamps) {
            if (tank.collideSwamp(swamp)) {
                tank.moveSpeed = 2;
            }
        }

        for (Road road : roads) {
            if (tank.collideRoad(road)) {
                tank.moveSpeed = 10;
            }
        }
        tank.collideHome(home);

        //enemy
        for (Enemy enemy : enemyTanks) {
            enemy.collideWithEnemys(enemyTanks);
            for (Decor decor : decors) {
                enemy.collideDecor(decor);
            }
            for (EnemyBase enemyBase : obases) {
                enemy.collideEnemyBase(enemyBase);
            }

            for (HomeWall homeWall : homeWalls) {
                enemy.collideWithWall(homeWall);
            }

            for (StoneWall stoneWall : stoneWalls) {
                enemy.collideWithWall(stoneWall);
            }

            for (RockWall rockWall : rockWalls) {
                enemy.collideWithWall(rockWall);
            }

            for (River river : rivers) {
                enemy.collideRiver(river);
            }

            if (enemy.moveSpeed != 5) {
                enemy.moveSpeed = 5;
            }
            for (Swamp swamp : swamps) {
                if (enemy.collideSwamp(swamp)) {
                    enemy.moveSpeed = 2;
                }
            }

            for (Road road : roads) {
                if (enemy.collideRoad(road)) {
                    enemy.moveSpeed = 10;
                }
            }
            enemy.collideHome(home);
        }
    }

    public void genEnemy() {

//        utils.utils.map.tanks.add(new Enemy(x, y, random.nextInt(3)));
        timer = new Timer(1000, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Random random = new Random();
        genRate++;

        for (EnemyBase obase : obases) {
            if (genRate >= 5 && enemyTanks.size() < 3) {
                utils.utils.map.enemyTanks.add(new Enemy(obase.x - 40, 0, 0));
            }
        }
    }
}
