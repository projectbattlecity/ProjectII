package Models;

import java.awt.Graphics;
import java.awt.Image;
import javax.annotation.Resource;
import static utils.utils.tk;

public class BigExplosion {

    private int x, y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private boolean live = true;

    Image[] imgs = {
        tk.getImage(Resource.class.getResource("/Images/Explosion/bigex_01.png")),
        tk.getImage(Resource.class.getResource("/Images/Explosion/bigex_02.png")),
        tk.getImage(Resource.class.getResource("/Images/Explosion/bigex_03.png")),
        tk.getImage(Resource.class.getResource("/Images/Explosion/bigex_04.png")),
        tk.getImage(Resource.class.getResource("/Images/Explosion/bigex_05.png")),
        tk.getImage(Resource.class.getResource("/Images/Explosion/bigex_06.png")),
        tk.getImage(Resource.class.getResource("/Images/Explosion/bigex_07.png")),
        tk.getImage(Resource.class.getResource("/Images/Explosion/bigex_08.png")),
        tk.getImage(Resource.class.getResource("/Images/Explosion/bigex_09.png")),
        tk.getImage(Resource.class.getResource("/Images/Explosion/bigex_10.png")),
        tk.getImage(Resource.class.getResource("/Images/Explosion/bigex_11.png")),
        tk.getImage(Resource.class.getResource("/Images/Explosion/bigex_12.png")),
        tk.getImage(Resource.class.getResource("/Images/Explosion/bigex_13.png")),
        tk.getImage(Resource.class.getResource("/Images/Explosion/bigex_14.png")),
        tk.getImage(Resource.class.getResource("/Images/Explosion/bigex_15.png")),
        tk.getImage(Resource.class.getResource("/Images/Explosion/bigex_16.png")),};
    int step = 0;

    public BigExplosion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {

        if (!live) {
            utils.utils.map.bigExplosions.remove(this);
            return;
        }
        if (step == imgs.length) {
            live = false;
            step = 0;
            return;
        }

        g.drawImage(imgs[step], x, y - 40, 100, 100, null);
        step++;
    }
}
