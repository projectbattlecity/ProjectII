package Models;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;
import javax.annotation.Resource;
import static utils.utils.tk;

public class Effect implements Serializable{
	public int x, y;
	public boolean live = true; 

	transient Image[] imgs = {
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_01.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_02.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_03.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_04.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_05.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_06.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_07.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_08.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_09.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_10.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_11.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_12.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_13.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_14.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_15.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_16.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_17.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_18.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_19.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_20.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_21.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_22.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_23.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_24.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_25.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_26.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_27.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_28.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_29.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_30.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_31.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_32.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_33.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_34.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_35.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_36.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_37.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_38.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_39.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_40.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_41.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_42.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_43.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_44.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_45.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_46.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_47.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_48.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_49.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_50.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_51.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_52.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_53.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_54.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_55.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_56.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_57.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_58.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_59.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_60.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_61.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_62.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_63.png")),
			tk.getImage(Resource.class.getResource("/Images/Effect/reborn_64.png"))
			

        };
	public int step = 0;

	public Effect(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g) { 

		if (!live) { 
			utils.utils.map.effects.remove(this);
			return;
		}
		if (step == imgs.length) {
			live = false;
			step = 0;
			return;
		}

		g.drawImage(imgs[step], x, y,80,80, null);
		step++;
	}
}
