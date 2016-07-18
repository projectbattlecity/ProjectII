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
public class HomeWall {

    public int x, y;
    public int index; // <=== biến để vẽ đúng hình
    public int width, height;

    Image[] homeWallImgs
            = {
                tk.getImage(Resource.class.getResource("/Images/Maps/HomeWall.gif")),
                tk.getImage(Resource.class.getResource("/Images/Maps/HomeWall1.gif")),
                tk.getImage(Resource.class.getResource("/Images/Maps/HomeWall2.gif")),
                tk.getImage(Resource.class.getResource("/Images/Maps/HomeWall3.gif")),
                tk.getImage(Resource.class.getResource("/Images/Maps/HomeWall4.gif")),
                tk.getImage(Resource.class.getResource("/Images/Maps/HomeWall5.gif")),
                tk.getImage(Resource.class.getResource("/Images/Maps/HomeWall6.gif")),};

    public HomeWall() {

    }

    public HomeWall(int x, int y, int width, int height, int index) {
        this.x = x;
        this.y = y;
        this.index = index;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
            g.drawImage(homeWallImgs[index], x, y, width, height, null);
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }
}
