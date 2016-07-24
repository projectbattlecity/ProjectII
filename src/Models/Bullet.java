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
import java.util.Random;
import javax.annotation.Resource;
import static utils.utils.tk;
import static utils.utils.audio;

/**
 *
 * @author DK
 */
public class Bullet implements Serializable {

    // move bullet
    public int x, y;
    public int moveSpeed = 8;
    public Tank.Move move; //hướng của đạn
    public int tank_level;

    // images
    public int checkPlayer = 0; // <======== biến check đạn của player là 0 hoặc của enemy là 1 (để vẽ)

    public int getCheckPlayer() {
        return checkPlayer;
    }

    public void setCheckPlayer(int checkPlayer) {
        this.checkPlayer = checkPlayer;
    }

    public static int width = 15;
    public static int height = 25;

    transient Image[][] bullet_Imgs
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

    transient Image imgTemp = bullet_Imgs[checkPlayer][0];

    Random random = new Random();

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

    //dịch chuyển của đạn
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

        if (x < 0 || y < 0 || x > 780 || y > 580) {
            explosion(x, y);
        }
    }

    public void explosion(int x, int y) {
        utils.utils.map.explosions.add(new Explosion(x, y));
        utils.utils.map.bullets.remove(this);
    }

    //bat va cham voi object 
    public boolean hitTank(Tank t) {

        if (this.getRect().intersects(t.getRect()) && t.isLive()) {
            if (checkPlayer != 1 || !(t instanceof Enemy)) {
                if (!t.isShield()) {
                    utils.utils.tankDestroy++;
                    if (utils.utils.tankDestroy % 3 == 0) {
                        int tempx = random.nextInt(19) * 40;
                        int tempy = random.nextInt(14) * 40;
                        audio.collectItems();
                        utils.utils.map.items.add(new Item(tempx, tempy, random.nextInt(5)));
                        utils.utils.map.effects.add(new Effect(tempx, tempy));
                    }
                    if (t instanceof Player) {
                        audio.playerDeath();
                    } else {
                        audio.enemyDeath();
                    }

                    utils.utils.map.bigExplosions.add(new BigExplosion(t.getX(), t.getY()));
                    if (t.lifeAmount > 0) {
                        t.lifeAmount--;
                        if (t.lifeAmount > 0 && t instanceof Player) {
                            ((Player) t).genPlayer();
                        } else {
                            t.setLive(false);
                        }
                    }
                }
                explosion(x, y);
                return true;
            }
        }
        utils.utils.map.checkWin();
        return false;
    }

    public boolean hitBullet(Bullet w) {
        if (this.getRect().intersects(w.getRect())) {
            if (checkPlayer != 1 || w.checkPlayer != 1) {
                explosion(x, y);
                utils.utils.map.bullets.remove(w);
                return true;
            }
        }
        return false;
    }

    public boolean hitWall(HomeWall w) {
        if (this.getRect().intersects(w.getRect())) {
            audio.collideHomeWall();
            explosion(x, y);
            utils.utils.map.homeWalls.remove(w);
            return true;
        }
        return false;
    }

    public boolean hitWall(RockWall w) {
        if (this.getRect().intersects(w.getRect())) {
            if (tank_level > 0) {
                audio.collideHomeWall();
                utils.utils.map.rockWalls.remove(w);
            } else {
                audio.collideRockWall();
            }
            explosion(x, y);
            return true;
        }
        return false;
    }

    public boolean hitWall(StoneWall w) {
        if (this.getRect().intersects(w.getRect())) {
            if (tank_level > 1) {
                audio.collideHomeWall();
                utils.utils.map.stoneWalls.remove(w);
            } else {
                audio.collideRockWall();
            }
            explosion(x, y);
            return true;
        }
        return false;
    }

    public boolean hitDecor(Decor d) {
        if (this.getRect().intersects(d.getRect())) {
            audio.collideRockWall();
            explosion(x, y);
            return true;
        }
        return false;
    }

    public boolean hitHome() {
        if (this.getRect().intersects(utils.utils.map.home.getRect())) {
            explosion(x, y);
            utils.utils.map.home.setLive(false);
            utils.utils.map.bigExplosions.add(new BigExplosion(x, y));
            return true;
        }
        return false;
    }

    public boolean hitEnemyBase(EnemyBase b) {
        if (this.getRect().intersects(b.getRect()) && b.isLive()) {
            if (checkPlayer == 0) {
                b.life--;
                explosion(x, y);
                if (b.life <= 0) {
                    audio.enemyBaseDestroy();
                    utils.utils.map.bigExplosions.add(new BigExplosion(x, y));
                    b.setLive(false);
                    utils.utils.map.obases.remove(b);
                }
                audio.collideEnemyBase();
            } else {
                explosion(x, y);
            }
            return true;
        }
        return false;
    }

}
