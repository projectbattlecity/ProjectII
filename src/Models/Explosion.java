package Models;

import java.awt.Graphics;
import java.awt.Image;
import javax.annotation.Resource;
import static utils.utils.tk;

public class Explosion {
	private int x, y;
	private boolean live = true; 

	private static Image[] imgs = {
			tk.getImage(Resource.class.getResource("/Images/Explosion/ex1.gif")),
			tk.getImage(Resource.class.getResource("/Images/Explosion/ex2.gif")),
			tk.getImage(Resource.class.getResource("/Images/Explosion/ex3.gif")),
			tk.getImage(Resource.class.getResource("/Images/Explosion/ex4.gif")),
			tk.getImage(Resource.class.getResource("/Images/Explosion/ex5.gif")),
			tk.getImage(Resource.class.getResource("/Images/Explosion/ex6.gif")),
			tk.getImage(Resource.class.getResource("/Images/Explosion/ex7.gif")),
			tk.getImage(Resource.class.getResource("/Images/Explosion/ex8.gif")),
			tk.getImage(Resource.class.getResource("/Images/Explosion/ex9.gif")),
			tk.getImage(Resource.class.getResource("/Images/Explosion/ex10.gif"))
        };
	int step = 0;

	public Explosion(int x, int y) {
		this.x = x;
		this.y = y;
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

		g.drawImage(imgs[step], x-10, y-10,50,40, null);
		step++;
	}
}
