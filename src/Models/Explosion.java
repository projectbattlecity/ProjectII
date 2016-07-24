package Models;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;
import javax.annotation.Resource;
import static utils.utils.tk;

public class Explosion implements Serializable {

    public int x, y;
    public boolean live = true;

    transient Image[] imgs = {
        tk.getImage(Resource.class.getResource("/Images/Explosion/Explosion_01.png")),
        tk.getImage(Resource.class.getResource("/Images/Explosion/Explosion_02.png")),
        tk.getImage(Resource.class.getResource("/Images/Explosion/Explosion_03.png")),
        tk.getImage(Resource.class.getResource("/Images/Explosion/Explosion_04.png")),
        tk.getImage(Resource.class.getResource("/Images/Explosion/Explosion_05.png")),
        tk.getImage(Resource.class.getResource("/Images/Explosion/Explosion_06.png")),
        tk.getImage(Resource.class.getResource("/Images/Explosion/Explosion_07.png")),
        tk.getImage(Resource.class.getResource("/Images/Explosion/Explosion_08.png")),
        tk.getImage(Resource.class.getResource("/Images/Explosion/Explosion_09.png")),
        tk.getImage(Resource.class.getResource("/Images/Explosion/Explosion_10.png")),
        tk.getImage(Resource.class.getResource("/Images/Explosion/Explosion_11.png")),
        tk.getImage(Resource.class.getResource("/Images/Explosion/Explosion_12.png")),
        tk.getImage(Resource.class.getResource("/Images/Explosion/Explosion_13.png")),
        tk.getImage(Resource.class.getResource("/Images/Explosion/Explosion_14.png")),
        tk.getImage(Resource.class.getResource("/Images/Explosion/Explosion_15.png")),
        tk.getImage(Resource.class.getResource("/Images/Explosion/Explosion_16.png")),};
    public int step = 0;

    public Explosion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Explosion(int x, int y, boolean live, int step) {
        this.x = x;
        this.y = y;
        this.live = live;
        this.step = step;
    }

    public void draw(Graphics g) {

        if (!live) {
            utils.utils.map.explosions.remove(this);
            return;
        }
        if (step == imgs.length) {
            live = false;
            step = 0;
            return;
        }

        g.drawImage(imgs[step], x - 40, y - 40, 100, 100, null);
        step++;
    }
}
