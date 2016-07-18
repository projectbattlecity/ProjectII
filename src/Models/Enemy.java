/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ASUS
 */
public class Enemy extends Tank {

    private Random r = new Random();
    private int step = 0;

    public Enemy(int x, int y) {
        super(x, y);
    }

    public Enemy(int x, int y, int tank_level) {
        super(x, y);
        this.tank_level = tank_level;
    }

    public Enemy() {
    }

    public Move move() {
        Tank.Move[] moves = Tank.Move.values();
//        if (step == 0) {
//                step = r.nextInt(12) + 3;
//                int mod = r.nextInt(9);
//                if (playertankaround()) {
//                    if (x == Server.ServerMain.serverTank.x) {
//                        if (y > Server.ServerMain.serverTank.y) {
//                            move = moves[1];
//                        } else if (y < Server.ServerMain.serverTank.y) {
//                            move = moves[3];
//                        }
//                    } else if (y == Server.ServerMain.serverTank.y) {
//                        if (x > Server.ServerMain.serverTank.x) {
//                            move = moves[0];
//                        } else if (x < Server.ServerMain.serverTank.x) {
//                            move = moves[2];
//                        }
//                    } else {
//                        int rn = r.nextInt(moves.length);
//                        move = moves[rn];
//                    }
//                    rate = 2;
//                } else if (mod == 1) {
//                    rate = 1;
//                } else if (1 < mod && mod <= 3) {
//                    rate = 1;
//                } else {
//                    int rn = r.nextInt(moves.length);
//                    move = moves[rn];
//                    rate = 1;
//                }
//            }
//            step--;
//            if (rate == 2) {
//                if (r.nextInt(40) > 35) {
//                    this.fire();
//                }
//            } else if (r.nextInt(40) > 38) {
//                this.fire();
//            }

        if (!isMove()) {
            dicideDerection(moves);
        } else {
            step++;
            if (step == 100) {
                dicideDerection(moves);
                this.fire();
                step = 0;
            }
        }
        if (r.nextInt(100) > 80) {
            if (this.x > Maps.home.x - 20 && this.x < Maps.home.x + 20) {
                move = moves[1];
            }
            if (this.y > Maps.home.y - 45) {
                if (this.x < Maps.home.x) {
                    move = moves[2];
                } else {
                    move = moves[3];
                }
            }
        }

        return move;
    }

    private void dicideDerection(Tank.Move[] moves) {
        if (move == moves[0] || move == moves[1]) {
            move = moves[r.nextInt(2) + 2];
        } else if (move == moves[2] || move == moves[3]) {
            move = moves[r.nextInt(2)];
        }
    }

    public void tankMove(Move m) {
        if (null != m) {
            move = m;
            this.oldX = x;
            this.oldY = y;
            switch (m) {
                case D:
                    temp = (this instanceof Enemy) ? otank_Imgs[tank_level][2] : tank_Imgs[tank_level][2];
                    y += moveSpeed;
//                    this.oldY -= moveSpeed;
                    break;
                case L:
                    temp = (this instanceof Enemy) ? otank_Imgs[tank_level][3] : tank_Imgs[tank_level][3];
                    x -= moveSpeed;
//                    this.oldX += moveSpeed;
                    break;
                case U:
                    temp = (this instanceof Enemy) ? otank_Imgs[tank_level][0] : tank_Imgs[tank_level][0];
                    y -= moveSpeed;
//                    this.oldY += moveSpeed;
                    break;
                case R:
                    temp = (this instanceof Enemy) ? otank_Imgs[tank_level][1] : tank_Imgs[tank_level][1];
                    x += moveSpeed;
//                    this.oldX -= moveSpeed;
                    break;
                default:
                    break;
            }
        }

        //khong cho ra ngoai man hinh
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        if (x > 760) {
            x = 760;
        }
        if (y > 560) {
            y = 560;
        }
    }

    private void changToOldDir() {
        this.x = oldX;
        this.y = oldY;
    }

    //bat va cham voi object
    public boolean collideWithEnemys(ArrayList<Enemy> enemys) {
        for (Enemy enemy : enemys) {
            if (this != enemy) {
                if (this.live && enemy.isLive()
                        && this.getRect().intersects(enemy.getRect())) {
                    this.changToOldDir();
                    enemy.changToOldDir();
                    return true;
                }
            }
        }
        return false;
    }

//    public boolean collideSwamp(Swamp s) {
//        return super.collideSwamp(s);
//    }
//
//    public boolean collideRoad(Road r) {
//        return super.collideRoad(r);
//    }
//
//    public boolean collideWithWall(HomeWall w) {
//        return super.collideWithWall(w);
//    }
//
//    public boolean collideWithWall(RockWall w) {
//        return super.collideWithWall(w);
//    }
//
//    public boolean collideWithWall(StoneWall w) {
//        return super.collideWithWall(w);
//    }
//
//    public boolean collideDecor(decor d) {
//        return super.collideDecor(d);
//    }
//
//    public boolean collideRiver(River r) {
//        return super.collideRiver(r);
//    }
//
//    public boolean collideHome(Home h) {
//        return super.collideHome(h);
//    }
//
//    public boolean collideEnemyBase(EnemyBase b) {
//        return super.collideEnemyBase(b);
//    }
//
//    public Bullet fire() {
//        return super.fire();
//    }

    public boolean isMove() {
        boolean result = false;
        if (x == oldX && y == oldY) {
        } else if (x == 0 && y == 0) {
        } else if (x == 0 && y == 560) {
        } else if (x == 760 && y == 0) {
        } else if (x == 760 && y == 560) {
        } else {
            result = true;
        }
        return result;
    }
}
