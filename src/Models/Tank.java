/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Queue;
import javax.annotation.Resource;
import javax.swing.Timer;
import static utils.utils.tk;
import static utils.utils.audio;

/**
 *
 * @author DK
 */
public class Tank implements Serializable {

    // move tank
    public static enum Move {
        U, D, R, L, Stop;
    }
    public int x, y;
    public int moveSpeed = 4;
    public Move move = Move.U;
    protected int oldX, oldY; //<=== biến để tank chạm vào tường thì quay lại vị trí cũ

    //images
    public static int tank_width = 40;
    public static int tank_height = 40;
    transient Image shieldImg = tk.getImage(Resource.class.getResource("/Images/Effect/shields.png"));
    transient Image[][] tank_Imgs
            = {
                {tk.getImage(Resource.class.getResource("/Images/Tanks/tank1U.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/tank1R.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/tank1D.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/tank1L.png"))},
                {tk.getImage(Resource.class.getResource("/Images/Tanks/tank2U.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/tank2R.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/tank2D.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/tank2L.png"))},
                {tk.getImage(Resource.class.getResource("/Images/Tanks/tank3U.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/tank3R.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/tank3D.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/tank3L.png"))},
                {tk.getImage(Resource.class.getResource("/Images/Tanks/tank4U.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/tank4R.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/tank4D.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/tank4L.png"))},};
    transient Image[][] otank_Imgs
            = {
                {tk.getImage(Resource.class.getResource("/Images/Tanks/otank1U.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/otank1R.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/otank1D.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/otank1L.png"))},
                {tk.getImage(Resource.class.getResource("/Images/Tanks/otank2U.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/otank2R.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/otank2D.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/otank2L.png"))},
                {tk.getImage(Resource.class.getResource("/Images/Tanks/otank3U.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/otank3R.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/otank3D.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/otank3L.png"))},
                {tk.getImage(Resource.class.getResource("/Images/Tanks/otank4U.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/otank4R.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/otank4D.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/otank4L.png"))
                }
            };

    transient Image temp;

    //check tank live?
    public boolean live = true;
    public int lifeAmount = 3;

    //vòng bảo vệ chống đạn
    public boolean shield = false;

    //level of tanks
    public int tank_level = 0;

    //timer check vòng bảo vệ
    public Timer timer;
    public int time = 0;

    //fire delay
    public Timer timerFire;
    public boolean shootable = true;
    public double timeToFire = 0;
    public double fireRate;

    public boolean isShield() {
        return shield;
    }

    //
    public void setShield(boolean shield) {
        this.shield = shield;
    }

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

    public Tank(int x, int y, Move m) {
        this.temp = (this instanceof Player) ? tank_Imgs[tank_level][0] : otank_Imgs[tank_level][0];
        this.x = x;
        this.y = y;
        this.oldX = x;
        this.oldY = y;
        this.move = m;
    }

    public Tank(int x, int y, Move m, int level) {
        this.temp = (this instanceof Player) ? tank_Imgs[tank_level][0] : otank_Imgs[tank_level][0];
        this.x = x;
        this.y = y;
        this.oldX = x;
        this.oldY = y;
        this.move = m;
        this.tank_level = level;
    }

    public Tank() {
    }

    public void drawTank(Graphics g) {
        switch (move) {
            case D:
                temp = (this instanceof Player) ? tank_Imgs[tank_level][2] : otank_Imgs[tank_level][2];
                break;
            case L:
                temp = (this instanceof Player) ? tank_Imgs[tank_level][3] : otank_Imgs[tank_level][3];
                break;
            case U:
                temp = (this instanceof Player) ? tank_Imgs[tank_level][0] : otank_Imgs[tank_level][0];
                break;
            case R:
                temp = (this instanceof Player) ? tank_Imgs[tank_level][1] : otank_Imgs[tank_level][1];
                break;
        }
        
        
        if (live && !shield) {
            g.drawImage(temp, x, y, tank_width, tank_height, null);
        } else if (live && shield) {
            g.drawImage(temp, x, y, tank_width, tank_height, null);
            g.drawImage(shieldImg, x - 5, y - 5, tank_width + 10, tank_height + 10, null);
        } else if (this instanceof Player) {
            utils.utils.map.tanks.remove(this);
        } else {
            utils.utils.map.enemyTanks.remove(this);
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
    public boolean collideWithTanks(Queue<Player> tanks) {
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

    public boolean collideWithItem(Item item) {
        return this.live && this.getRect().intersects(item.getRect()) && this instanceof Player;
    }

    public boolean collideWithWall(StoneWall w) {
        if (this.live && this.getRect().intersects(w.getRect())) {
            this.changToOldDir();
            return true;
        }
        return false;
    }

    public boolean collideDecor(Decor d) {
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

    public boolean collideWithEnemys(Queue<Enemy> enemys) {
        for (Enemy enemy : enemys) {
            if (this != enemy && this instanceof Player) {
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
//                    this.oldY -= moveSpeed;
                    break;
                case L:
                    temp = (this instanceof Player) ? tank_Imgs[tank_level][3] : otank_Imgs[tank_level][3];
                    x -= moveSpeed;
//                    this.oldX += moveSpeed;
                    break;
                case U:
                    temp = (this instanceof Player) ? tank_Imgs[tank_level][0] : otank_Imgs[tank_level][0];
                    y -= moveSpeed;
//                    this.oldY += moveSpeed;
                    break;
                case R:
                    temp = (this instanceof Player) ? tank_Imgs[tank_level][1] : otank_Imgs[tank_level][1];
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

    //fire bullets
    public Bullet fire() {
        switch (tank_level) {
            case 0:
                fireRate = 1.75;
                break;
            case 1:
                fireRate = 1.25;
                break;
            case 2:
                fireRate = 0.75;
                break;
            case 3:
                fireRate = 0.5;
                break;
        }
        Bullet genBullet = null;
        if (live && shootable) {
            genBullet = genBullet();
            shootable = false;
            timerFire = new Timer(250, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    timeToFire += 0.25;
                    if (timeToFire >= fireRate) {
                        timeToFire = 0;
                        shootable = true;
                        timerFire.stop();
                    }
                }
            });
            timerFire.start();
        }

        return genBullet;
    }

    public Bullet genBullet() {
        if (move == Move.Stop) {
            return null;
        }
        int bulletx = 0;
        int bullety = 0;
        switch (move) {
            case D:
                bulletx = this.x + tank_width / 2 - Bullet.width / 2;
                bullety = this.y + tank_height / 2 - Bullet.height / 2 + 30;
                break;
            case R:
                bulletx = this.x + tank_width / 2 - Bullet.width / 2 + 30;
                bullety = this.y + tank_height / 2 - Bullet.height / 2;
                break;
            case L:
                bulletx = this.x + tank_width / 2 - Bullet.width / 2 - 30;
                bullety = this.y + tank_height / 2 - Bullet.height / 2;
                break;
            case U:
                bulletx = this.x + tank_width / 2 - Bullet.width / 2;
                bullety = this.y + tank_height / 2 - Bullet.height / 2 - 30;
                break;
        }
        if(this instanceof Player){
            audio.gun();
        }
        Bullet b = new Bullet(bulletx, bullety, move, tank_level, (this instanceof Player) ? 0 : 1);
        utils.utils.map.bullets.add(b);
        return b;
    }

}
