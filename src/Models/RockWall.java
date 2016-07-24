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
 * @author c1409l0937
 */
public class RockWall implements Serializable{

    public  int width = 40;
    public  int height = 40;

    public int x, y;
    transient Image rockWallImg = tk.getImage(Resource.class.getResource("/Images/Maps/rock.png"));

    public RockWall(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public RockWall() {
    }

    public void draw(Graphics g) {
        g.drawImage(rockWallImg, x, y, width, height, null);
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }
}
