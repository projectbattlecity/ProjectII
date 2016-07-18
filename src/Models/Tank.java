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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Queue;
import javax.annotation.Resource;
import javax.swing.Timer;
import static utils.utils.tk;

/**
 *
 * @author DK
 */
public class Tank implements Serializable, ActionListener {

    // move tank
    public static enum Move {
        U, D, R, L, Stop;
    }
    public int x, y;
    public int moveSpeed = 5;
    Move move;
    protected int oldX, oldY; //<=== biến để tank chạm vào tường thì quay lại vị trí cũ


    //images
    public static int tank_width = 40;
    public static int tank_height = 40;
    Image shieldImg = tk.getImage(Resource.class.getResource("/Images/Effect/shields.png"));
    Image[][] tank_Imgs
            = {
                {tk.getImage(Resource.class.getResource("/Images/Tanks/tank1U.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/tank1R.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/tank1D.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/tank1L.png"))}
            };
    Image[][] otank_Imgs
            = {
                {tk.getImage(Resource.class.getResource("/Images/Tanks/otank1U.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/otank1R.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/otank1D.png")),
                    tk.getImage(Resource.class.getResource("/Images/Tanks/otank1L.png"))}
            };

    Image temp;

    //check tank live?
    protected boolean live = true;
    public int lifeAmount = 3;

    //vòng bảo vệ chống đạn
    private boolean shield = false;

    //level of tanks
    public int tank_level = 0;

    //timer check vòng bảo vệ
    Timer timer;
    int time=0;

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

    public Tank() {
    }

    public void drawTank(Graphics g) {
        if (live && !shield) {
            g.drawImage(temp, x, y, tank_width, tank_height, null);
        } else if (live && shield) {
            g.drawImage(temp, x, y, tank_width, tank_height, null);
            g.drawImage(shieldImg, x-5, y-5, tank_width +10, tank_height+10, null);
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
    public boolean collideWithTanks(Queue<Tank> tanks) {
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
        if (!live) {
            return null;
        } else {
            if (move == Move.Stop) {
                return null;
            }
            int x = 0;
            int y = 0;
            switch (move) {
                case D:
                    x = this.x + tank_width / 2 - Bullet.width / 2;
                    y = this.y + tank_height / 2 - Bullet.height / 2 + 20;
                    break;
                case R:
                    x = this.x + tank_width / 2 - Bullet.width / 2 + 20;
                    y = this.y + tank_height / 2 - Bullet.height / 2;
                    break;
                case L:

                    x = this.x + tank_width / 2 - Bullet.width / 2 - 20;
                    y = this.y + tank_height / 2 - Bullet.height / 2;
                    break;
                case U:
                    x = this.x + tank_width / 2 - Bullet.width / 2;
                    y = this.y + tank_height / 2 - Bullet.height / 2 - 30;
                    break;
            }

            Bullet bullet = new Bullet(x, y, move, tank_level, (this instanceof Player) ? 0 : 1);

            utils.utils.map.bullets.add(bullet);
            return bullet;
        }
    }

    public void genPlayer() {
        utils.utils.map.tanks.remove(this);
        x= utils.utils.x2;
        y= utils.utils.y2;
        oldX= utils.utils.x2;
        oldY= utils.utils.y2;
        
        timer = new Timer(1000, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        time++;
        if(time == 2)
        {
            utils.utils.map.tanks.add(this);
            shield = true;
        }
        if(time>= 5)
        {
            time =0;
            shield = false;
            timer.stop();
        }
    }
}
