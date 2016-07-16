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
    public int life = 5;
    
    private boolean live = true;

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    private static Image obaseImg = tk.getImage(Resource.class.getResource("/Images/Maps/enemygen2.png"));

    public EnemyBase(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public EnemyBase() {
    }

    public void draw(Graphics g) {
        if (live) {
            g.drawImage(obaseImg, x, y, width, height, null);
        }
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }
}
