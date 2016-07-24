package Models;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;
import javax.annotation.Resource;
import static utils.utils.tk;

public class Item implements Serializable{

    public int x, y;
    public int width =40, height = 40;
    public int index = 0; //<=== biến để kiểm tra là icon nào

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    

    transient Image[] imgs = {
        tk.getImage(Resource.class.getResource("/Images/Icon/bomb.gif")),
        tk.getImage(Resource.class.getResource("/Images/Icon/bullet.gif")),
        tk.getImage(Resource.class.getResource("/Images/Icon/combat.gif")),
        tk.getImage(Resource.class.getResource("/Images/Icon/general.gif")),
        tk.getImage(Resource.class.getResource("/Images/Icon/shield.gif")),
        tk.getImage(Resource.class.getResource("/Images/Icon/sniper.gif")),
        tk.getImage(Resource.class.getResource("/Images/Icon/tank.gif")),
    };

    public Item(int x, int y, int index) {
        this.x = x;
        this.y = y;
        this.index = index;
    }

    public void draw(Graphics g) {
        g.drawImage(imgs[index], x, y, width, height, null);
    }
    
    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }
}
