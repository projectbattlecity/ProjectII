/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.awt.Graphics;
import java.io.Serializable;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.annotation.Resource;

/**
 *
 * @author DK
 */
public class Tank implements Serializable {

    public static enum Move {
        U, D, R, L, Stop;
    }

    // move tank
    public int x, y;
    protected int oldX, oldY;
    public int moveSpeed = 5;

    //control amount of bullet
    public int bulletAmount = 0;
    protected int maxBulletAmount = 3;

    Move move;

    // images
    public static int tank_width = 40;
    public static int tank_height = 40;

    //check tank live?
    protected boolean live = true;
    protected int lifeAmount = 2;

    public int getLifeAmount() {
        return lifeAmount;
    }

    public void setLifeAmount(int lifeAmount) {
        this.lifeAmount = lifeAmount;
    }

    protected static Toolkit tk = Toolkit.getDefaultToolkit();
    public static Image[][] tank_Imgs
            = {
                {tk.getImage(Resource.class.getResource("/Images/Tanks/tank1U.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/tank1R.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/tank1D.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/tank1L.png"))}
            };
    public static Image[][] otank_Imgs
            = {
                {tk.getImage(Resource.class.getResource("/Images/Tanks/otank1U.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/otank1R.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/otank1D.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/otank1L.png"))}
            };

    protected Image temp;

    //level of tanks
    public int tank_level = 0;

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Tank(int x, int y) {
        this.temp = (this instanceof Player) ? tank_Imgs[tank_level][0] : otank_Imgs[tank_level][0];
        this.x = x;
        this.y = y;
        this.oldX = x;
        this.oldY = y;
        this.move = Move.Stop;
    }

    public Tank() {
    }

    public void drawTank(Graphics g) {
        if (live) {
            g.drawImage(temp, x, y, tank_width, tank_height, null);
        } else {
            utils.utils.map.tanks.remove(this);
        }
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, tank_width, tank_height);
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    private void changToOldDir() {
        x = oldX;
        y = oldY;
    }

    //bat va cham voi object
    public boolean collideWithTanks(ArrayList<Tank> tanks) {
        for (Tank tank : tanks) {
            if (this != tank) {
                if (this.live && tank.isLive()
                        && this.getRect().intersects(tank.getRect())) {
                    this.changToOldDir();
                    tank.changToOldDir();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean collideSwamp(Swamp s) {
        return this.live && this.getRect().intersects(s.getRect());
    }

    public boolean collideRoad(Road r) {
        return this.live && this.getRect().intersects(r.getRect());
    }

    public boolean collideWithWall(HomeWall w) {
        if (this.live && this.getRect().intersects(w.getRect())) {
            this.changToOldDir();
            return true;
        }
        return false;
    }

    public boolean collideWithWall(RockWall w) {
        if (this.live && this.getRect().intersects(w.getRect())) {
            this.changToOldDir();
            return true;
        }
        return false;
    }

    public boolean collideWithWall(StoneWall w) {
        if (this.live && this.getRect().intersects(w.getRect())) {
            this.changToOldDir();
            return true;
        }
        return false;
    }

    public boolean collideDecor(decor d) {
        if (this.live && this.getRect().intersects(d.getRect())) {
            this.changToOldDir();
            return true;
        }
        return false;
    }

    public boolean collideRiver(River r) {
        if (this.live && this.getRect().intersects(r.getRect())) {
            this.changToOldDir();
            return true;
        }
        return false;
    }

    public boolean collideHome(Home h) {
        if (this.live && this.getRect().intersects(h.getRect())) {
            this.changToOldDir();
            return true;
        }
        return false;
    }

    public boolean collideEnemyBase(EnemyBase b) {
        if (this.live && this.getRect().intersects(b.getRect())) {
            this.changToOldDir();
            return true;
        }
        return false;
    }

    //tank move
    public void tankMove(Move m) {
        if (null != m) {
            move = m;
            this.oldX = x;
            this.oldY = y;
            switch (m) {
                case D:
                    temp = (this instanceof Player) ? tank_Imgs[tank_level][2] : otank_Imgs[tank_level][2];
                    y += moveSpeed;
                    this.oldY -= moveSpeed;
                    break;
                case L:
                    temp = (this instanceof Player) ? tank_Imgs[tank_level][3] : otank_Imgs[tank_level][3];
                    x -= moveSpeed;
                    this.oldX += moveSpeed;
                    break;
                case U:
                    temp = (this instanceof Player) ? tank_Imgs[tank_level][0] : otank_Imgs[tank_level][0];
                    y -= moveSpeed;
                    this.oldY += moveSpeed;
                    break;
                case R:
                    temp = (this instanceof Player) ? tank_Imgs[tank_level][1] : otank_Imgs[tank_level][1];
                    x += moveSpeed;
                    this.oldX -= moveSpeed;
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

    //fire bullets
    public Bullet fire() {
        if (!live) {
            return null;
        } else {
            if (move == Move.Stop || bulletAmount > maxBulletAmount) {
                return null;
            }
            int x=0;
            int y=0;
            switch (move) {
                case D:
                    x = this.x + tank_width / 2 - Bullet.width / 2;
                    y = this.y + tank_height / 2 - Bullet.height / 2 + 10;
                    break;
                case R:
                    x = this.x + tank_width / 2 - Bullet.width / 2+10;
                    y = this.y + tank_height / 2 - Bullet.height / 2;
                    break;
                case L:
                    
                    x = this.x + tank_width / 2 - Bullet.width / 2- 10;
                    y = this.y + tank_height / 2 - Bullet.height / 2;
                    break;
                case U:
                    x = this.x + tank_width / 2 - Bullet.width / 2;
                    y = this.y + tank_height / 2 - Bullet.height / 2 -10 ;
                    break;
            }

            Bullet bullet = new Bullet(x, y, move, tank_level, (this instanceof Player) ? 0 : 1);

            utils.utils.map.bullets.add(bullet);
            bulletAmount++;
            return bullet;
        }
    }
}
