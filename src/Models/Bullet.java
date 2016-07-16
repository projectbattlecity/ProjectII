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
public class Bullet implements Serializable {

    // move bullet
    public int x, y;

    private Tank.Move move;
    private int tank_level;

    public int getTank_level() {
        return tank_level;
    }

    public void setTank_level(int tank_level) {
        this.tank_level = tank_level;
    }

    public Tank.Move getMove() {
        return move;
    }

    public void setMove(Tank.Move move) {
        this.move = move;
    }

    // images
    private int checkPlayer = 0;
    public static int width = 15;
    public static int height = 25;
    public static int moveSpeed = 12;
    private static Toolkit tk = Toolkit.getDefaultToolkit();
    public static Image[][] bullet_Imgs
            = {
                {
                    tk.getImage(Resource.class.getResource("/Images/Bullet/bulletU.gif")),
                    tk.getImage(Resource.class.getResource("/Images/Bullet/bulletR.gif")),
                    tk.getImage(Resource.class.getResource("/Images/Bullet/bulletD.gif")),
                    tk.getImage(Resource.class.getResource("/Images/Bullet/bulletL.gif"))
                },
                {
                    tk.getImage(Resource.class.getResource("/Images/Bullet/obulletU.gif")),
                    tk.getImage(Resource.class.getResource("/Images/Bullet/obulletR.gif")),
                    tk.getImage(Resource.class.getResource("/Images/Bullet/obulletD.gif")),
                    tk.getImage(Resource.class.getResource("/Images/Bullet/obulletL.gif"))
                }
            };

    Image imgTemp = bullet_Imgs[checkPlayer][0];

    public int getCheckPlayer() {
        return checkPlayer;
    }

    public void setCheckPlayer(int checkPlayer) {
        this.checkPlayer = checkPlayer;
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

    public Bullet(int x, int y, Tank.Move move, int tank_level, int checkPlayer) {
        this.x = x;
        this.y = y;
        this.move = move;
        this.tank_level = tank_level;
        this.checkPlayer = checkPlayer;
    }

    public Bullet() {
    }

    public void drawBullet(Graphics g) {
        g.drawImage(imgTemp, x, y, width, height, null);
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }

    //bat va cham voi object
    //bullet move
    public void bulletMove() {
        switch (move) {
            case D:
                height = 20;
                width = 15;
                imgTemp = bullet_Imgs[checkPlayer][2];
                y += moveSpeed;
                break;
            case L:
                height = 15;
                width = 20;
                imgTemp = bullet_Imgs[checkPlayer][3];
                x -= moveSpeed;

                break;
            case U:
                height = 20;
                width = 15;
                imgTemp = bullet_Imgs[checkPlayer][0];
                y -= moveSpeed;

                break;
            case R:
                height = 15;
                width = 20;
                imgTemp = bullet_Imgs[checkPlayer][1];
                x += moveSpeed;
                break;
        }

        if (x < 0 || y < 0 || x > 760 || y > 560) {
            utils.utils.map.explosions.add(new Explosion(x, y));
            utils.utils.map.bullets.remove(this);
            Server.ServerMain.serverTank.bulletAmount--;
        }
    }


    public boolean hitTank(Tank t) {

        if (this.getRect().intersects(t.getRect()) && t.isLive()) {
//        if (this.getRect().intersects(t.getRect()) && t.isLive() && !(t instanceof Player)) {
            utils.utils.map.explosions.add(new Explosion(x, y));
            if (t.isLive() && t.getLifeAmount() > 0) {
                t.setLifeAmount(t.getLifeAmount() - 1);
                if (t.getLifeAmount() <= 0) {
                    t.setLive(false);
                    utils.utils.map.bigExplosions.add(new BigExplosion(t.getX(), t.getY()));
                }
            } else {
                t.setLive(false);
            }

//            utils.utils.map.bullets.remove(this);
            return true;
        }
        return false;
    }

    public boolean hitWall(HomeWall w) {
        if (this.getRect().intersects(w.getRect())) {
            utils.utils.map.explosions.add(new Explosion(x, y));
            utils.utils.map.bullets.remove(this);
            Server.ServerMain.serverTank.bulletAmount--;
            utils.utils.map.homeWalls.remove(w);
            return true;
        }
        return false;
    }

    public boolean hitWall(RockWall w) {
        if (this.getRect().intersects(w.getRect())) {
            if (tank_level > 0) {
                utils.utils.map.explosions.add(new Explosion(x, y));
                utils.utils.map.bullets.remove(this);
                Server.ServerMain.serverTank.bulletAmount--;
                utils.utils.map.rockWalls.remove(w);
            } else {
                utils.utils.map.explosions.add(new Explosion(x, y));
                utils.utils.map.bullets.remove(this);
                Server.ServerMain.serverTank.bulletAmount--;
            }
            return true;
        }
        return false;
    }

    public boolean hitWall(StoneWall w) {
        if (this.getRect().intersects(w.getRect())) {
            if (tank_level > 1) {
                utils.utils.map.explosions.add(new Explosion(x, y));
                utils.utils.map.bullets.remove(this);
                Server.ServerMain.serverTank.bulletAmount--;
                utils.utils.map.stoneWalls.remove(w);
            } else {
                utils.utils.map.explosions.add(new Explosion(x, y));
                utils.utils.map.bullets.remove(this);
                Server.ServerMain.serverTank.bulletAmount--;
            }
            return true;
        }
        return false;
    }

    public boolean hitDecor(decor d) {
        if (this.getRect().intersects(d.getRect())) {
            if (tank_level > 1) {
                utils.utils.map.explosions.add(new Explosion(x, y));
                utils.utils.map.bullets.remove(this);
                Server.ServerMain.serverTank.bulletAmount--;
                utils.utils.map.decors.remove(d);
            } else {
                utils.utils.map.explosions.add(new Explosion(x, y));
                utils.utils.map.bullets.remove(this);
                Server.ServerMain.serverTank.bulletAmount--;
            }
            return true;
        }
        return false;
    }

    public boolean hitHome() {
        if (this.getRect().intersects(utils.utils.map.home.getRect())) {
            utils.utils.map.explosions.add(new Explosion(x, y));
            utils.utils.map.bullets.remove(this);
            Server.ServerMain.serverTank.bulletAmount--;
            utils.utils.map.home.setLive(false);
            return true;
        }
        return false;
    }
    
    public boolean hitEnemyBase(EnemyBase b) {
        if (this.getRect().intersects(b.getRect())) {
            utils.utils.map.explosions.add(new Explosion(x, y));
            Server.ServerMain.serverTank.bulletAmount--;
            utils.utils.map.bullets.remove(this);
            b.setLive(false);
            return true;
        }
        return false;
    }
    
}
