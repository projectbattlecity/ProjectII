package Models;

import java.awt.Graphics;
import java.awt.Image;
import javax.annotation.Resource;
import static utils.utils.tk;

public class Fire {

    private int x, y;

    private static Image[] imgs = {
        tk.getImage(Resource.class.getResource("/Images/Fire/fire.gif")),
        tk.getImage(Resource.class.getResource("/Images/Fire/fire1.gif")),
        tk.getImage(Resource.class.getResource("/Images/Fire/fire2.gif")),
        tk.getImage(Resource.class.getResource("/Images/Fire/fire3.gif")),
        tk.getImage(Resource.class.getResource("/Images/Fire/fire4.gif")),
        tk.getImage(Resource.class.getResource("/Images/Fire/fire5.gif")),
        tk.getImage(Resource.class.getResource("/Images/Fire/fire6.gif")),
        tk.getImage(Resource.class.getResource("/Images/Fire/fire7.gif"))

    };
    int step = 0;

    public Fire(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {

        if (step == imgs.length) {
            step = 0;
            return;
        }

        g.drawImage(imgs[step], x, y, 20, 20, null);
        step++;
    }
}
