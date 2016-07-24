/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;
import javax.annotation.Resource;
import static utils.utils.tk;

/**
 *
 * @author especsily
 */
public class StoneWall implements Serializable{

    public  int width = 40;
    public  int height = 40;

    public int x, y;
    transient Image stoneWallImg = tk.getImage(Resource.class.getResource("/Images/Maps/StoneWall.png"));

    public StoneWall(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public StoneWall() {
    }

    public void draw(Graphics g) {
        g.drawImage(stoneWallImg, x, y, width, height, null);
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }
}
