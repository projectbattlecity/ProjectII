package Models;

import java.awt.Graphics;
import java.awt.Image;
import javax.annotation.Resource;
import static utils.utils.tk;

public class BigExplosion {
	private int x, y;
	private boolean live = true; 

	private static Image[] imgs = {
			tk.getImage(Resource.class.getResource("/Images/Explosion/bigex1.gif")),
			tk.getImage(Resource.class.getResource("/Images/Explosion/bigex2.gif")),
			tk.getImage(Resource.class.getResource("/Images/Explosion/bigex3.gif")),
			tk.getImage(Resource.class.getResource("/Images/Explosion/bigex4.gif")),
			tk.getImage(Resource.class.getResource("/Images/Explosion/bigex5.gif")),
			tk.getImage(Resource.class.getResource("/Images/Explosion/bigex6.gif")),
			tk.getImage(Resource.class.getResource("/Images/Explosion/bigex7.gif")),
			tk.getImage(Resource.class.getResource("/Images/Explosion/bigex8.gif")),
			tk.getImage(Resource.class.getResource("/Images/Explosion/bigex9.gif")),
			tk.getImage(Resource.class.getResource("/Images/Explosion/bigex10.gif")),
			tk.getImage(Resource.class.getResource("/Images/Explosion/bigex11.gif")),
			tk.getImage(Resource.class.getResource("/Images/Explosion/bigex12.gif")),
        };
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

		g.drawImage(imgs[step], x-20, y-20,40,40, null);
		step++;
	}
}
