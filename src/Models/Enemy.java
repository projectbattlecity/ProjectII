/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Random;

/**
 *
 * @author ASUS
 */
public class Enemy extends Tank {

    private Random r = new Random();
    private int step = 0;
    private int find = 0;

    public Enemy(int x, int y) {
        super(x, y);
    }

    public Enemy(int x, int y, int tank_level) {
        super(x, y);
        this.lifeAmount = 1;
        this.tank_level = tank_level;
        this.move = Tank.Move.D;
    }

    public Enemy(int x, int y, int tank_level, Move m) {
        super(x, y, m);
        this.lifeAmount = 1;
        this.tank_level = tank_level;
    }

    public Enemy() {
    }

    public Move move() {
        Tank.Move[] moves = Tank.Move.values();

        if (find != 0) {
            find--;
            findHome(moves);
            return move;
        } else if (!isMove()) {
            dicideDerection(moves);
        } else {
            step++;
            if (step >= 150) {
                dicideDerection(moves);
                step = 0;
            }
        }

        if (r.nextInt(100) > 98) {
            this.fire();
        }

        if (r.nextInt(1000) > 998) {
            find = 50;
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

    @Override
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

    public void changToOldDir() {
        this.x = oldX;
        this.y = oldY;
    }

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
    private void findHome(Move[] moves) {
        if (!isMove()) {
            if (x < 9 * 40) {
                if (this.move == Tank.Move.D) {
                    move = Tank.Move.R;
                } else if (this.move == Tank.Move.L) {
                    move = Tank.Move.R;
                } else if (this.move == Tank.Move.U) {
                    move = Tank.Move.D;
                } else {
                    move = Tank.Move.D;
                }
            } else if (x > 10 * 40) {
                if (this.move == Tank.Move.L || this.move == Tank.Move.U) {
                    move = Tank.Move.D;
                } else {
                    move = Tank.Move.L;
                }
            }
        } else if (x >= 9 * 40 && x <= 10 * 40) {
            move = Tank.Move.D;
        }
    }
}
