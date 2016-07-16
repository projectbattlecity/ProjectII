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
public class Road {
    private int width = 40; 
    private int height = 40;
    
    int x,y;
    
    private static Image roadImg = tk.getImage(Resource.class.getResource("/Images/Maps/Road.png"));

    public Road(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Road() {
    }
    
    public void draw(Graphics g)
    {
        g.drawImage(roadImg, x, y,width,height, null);
    }
    
    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }
}
