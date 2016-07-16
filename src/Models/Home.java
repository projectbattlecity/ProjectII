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
 * @author ASUS
 */
public class Home {
    public int x, y;
    private boolean live = true;

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
    
    private int width = 80, height = 80;


    public  Image[] homeImgs
            = {
                tk.getImage(Resource.class.getResource("/Images/Maps/Home.gif")),
                tk.getImage(Resource.class.getResource("/Images/Maps/Home2.gif")),
                tk.getImage(Resource.class.getResource("/Images/Maps/Home3.png"))};

    public Home(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        if (live) {
            g.drawImage(homeImgs[1], x, y, width, height, null);
        }
    }
    
    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }

    private void gameOver() {

    }
}
