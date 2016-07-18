/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.annotation.Resource;
import static utils.utils.tk;

/**
 *
 * @author especsily
 */
public class EnemyBase {

    private int width = 80;
    private int height = 80;

    public int x, y;
    public int life = 3; // <==== số phát bắn để diệt

    private boolean live = true;

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    Image[] obaseImg
            = {
                tk.getImage(Resource.class.getResource("/Images/Maps/enemygen3.png")),
                tk.getImage(Resource.class.getResource("/Images/Maps/enemygen2.png")),
                tk.getImage(Resource.class.getResource("/Images/Maps/enemygen1.png"))
            };

    public EnemyBase(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public EnemyBase() {
    }

    public void draw(Graphics g) {
        if (live) {
            g.drawImage(obaseImg[life-1], x, y, width, height, null);
        }
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }
}
