/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.awt.Graphics;
import java.awt.Image;
import javax.annotation.Resource;
import static utils.utils.tk;

/**
 *
 * @author c1409l0937
 */
public class Background {
    Image[] bg = {tk.getImage(Resource.class.getResource("/Images/Maps/grass.jpg")),
        tk.getImage(Resource.class.getResource("/Images/Maps/sand.jpg")),
        tk.getImage(Resource.class.getResource("/Images/Maps/sand1.jpg"))
    };
//    Random rd = new Random();
    
    public void drawBg(Graphics g)
    {
        g.drawImage(bg[0], 0, 0, 800, 600, null);
    }
}
