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
public class decor {
    private int width; 
    private int height;
    
    int x,y;
    private int index;
    private Image[] decorImg = 
    {
        tk.getImage(Resource.class.getResource("/Images/Maps/decor.png")),
        tk.getImage(Resource.class.getResource("/Images/Maps/decor1.gif")),
        tk.getImage(Resource.class.getResource("/Images/Maps/decor2.gif")),
        tk.getImage(Resource.class.getResource("/Images/Maps/decor3.gif")),
        tk.getImage(Resource.class.getResource("/Images/Maps/decor4.gif")),
        tk.getImage(Resource.class.getResource("/Images/Maps/decor5.gif")),
        tk.getImage(Resource.class.getResource("/Images/Maps/decor6.gif")),
        tk.getImage(Resource.class.getResource("/Images/Maps/decor7.gif")),
        tk.getImage(Resource.class.getResource("/Images/Maps/decor8.gif"))
    };

    public decor() {
    }

    public decor(int x, int y,int width, int height, int index) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.index = index;
    }

    public void draw(Graphics g)
    {
        g.drawImage(decorImg[index], x, y,width,height, null);
    }
    
    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }
}
