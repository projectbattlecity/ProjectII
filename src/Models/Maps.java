/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import ServeMap.ServerPanel;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author c1409l0937
 */
public final class Maps {

    Home home;

    Queue<RockWall> rockWalls = new ConcurrentLinkedQueue<>();
    Queue<StoneWall> stoneWalls = new ConcurrentLinkedQueue<>();
    Queue<River> rivers = new ConcurrentLinkedQueue<>();
    Queue<Tree> trees = new ConcurrentLinkedQueue<>();
    Queue<Swamp> swamps = new ConcurrentLinkedQueue<>();
    Queue<HomeWall> homeWalls = new ConcurrentLinkedQueue<>();
    Queue<EnemyBase> obases = new ConcurrentLinkedQueue<>();
    Queue<decor> decors = new ConcurrentLinkedQueue<>();
//    ArrayList<Bullet> bullets = new ArrayList<>();
    Queue<Bullet> bullets=new ConcurrentLinkedQueue<>();
//    List<Bullet> bullets = new Vector<>();
    public Queue<Tank> tanks = new ConcurrentLinkedQueue<>();
    Queue<Fire> fires = new ConcurrentLinkedQueue<>();
    Queue<Explosion> explosions = new ConcurrentLinkedQueue<>();
    Queue<BigExplosion> bigExplosions = new ConcurrentLinkedQueue<>();
    Queue<Road> roads = new ConcurrentLinkedQueue<>();

    public Maps() {

    }

    public void drawLines(Graphics g) {
        for (int i = 0; i < ServerPanel.screen_height / 40; i++) {
            g.drawLine(0, i * 40, 800, i * 40);
        }
        for (int i = 0; i < ServerPanel.screen_width / 40; i++) {
            g.drawLine(i * 40, 0, i * 40, 600);
        }
    }

    public void drawTank(Graphics g) {
        for (Tank tank : tanks) {
            tank.drawTank(g);
        }
    }

    public void AddComponents() {
        drawLevel(utils.utils.level);
        drawHomeWall();
        drawEnemyBases();
    }

    public void drawTrees(Graphics g) {
        for (Tree tree : trees) {
            tree.draw(g);
        }
    }

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
        
        for (decor decor : decors) {
            decor.draw(g);
        }
    }

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
        for (decor decor : decors) {
            if (bullet.hitDecor(decor)) {
                return true;
            }
        }
        return false;
    }

    public void tankCollide(Graphics g) {
        for (Tank tank : tanks) {

            tank.collideWithTanks(tanks);
            for (decor decor : decors) {
                tank.collideDecor(decor);
                decor.draw(g);
            }
            for (EnemyBase enemyBase : obases) {
                tank.collideEnemyBase(enemyBase);
                enemyBase.draw(g);
            }

            for (HomeWall homeWall : homeWalls) {
                tank.collideWithWall(homeWall);
                homeWall.draw(g);
            }

            for (StoneWall stoneWall : stoneWalls) {
                tank.collideWithWall(stoneWall);
                stoneWall.draw(g);
            }

            for (RockWall rockWall : rockWalls) {
                tank.collideWithWall(rockWall);
                rockWall.draw(g);
            }

            for (River river : rivers) {
                tank.collideRiver(river);
                river.draw(g);
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
        }
    }

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
        for (Fire fire: fires) {
            fire.draw(g);
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
                    decors.add(new decor(i * 40, 14 * 40, 40, 40, 6));
                    decors.add(new decor((20 - i - 1) * 40, 14 * 40, 40, 40, 6));
                }
                decors.add(new decor(5 * 40, 12 * 40, 80, 80, 1));
                decors.add(new decor(13 * 40, 12 * 40, 80, 80, 1));

                decors.add(new decor(6 * 40, 7 * 40, 40, 80, 5));
                decors.add(new decor(13 * 40, 7 * 40, 40, 80, 5));

                decors.add(new decor(2 * 40, 12 * 40, 80, 40, 8));
                decors.add(new decor((20 - 3 - 1) * 40, 12 * 40, 80, 40, 8));
                break;
        }
    }

    public void drawBackGround(Graphics g) {
        new Background().drawBg(g);
    }

}
