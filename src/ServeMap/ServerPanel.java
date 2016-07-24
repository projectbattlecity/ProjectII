/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServeMap;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.annotation.Resource;
import javax.swing.JPanel;
import utils.utils;
import static utils.utils.tk;

/**
 *
 * @author DK
 */
public class ServerPanel extends JPanel {

    public static int screen_width = 800, screen_height = 600;
    Image gameover = tk.getImage(Resource.class.getResource("/Images/gameover.jpg"));

    public ServerPanel() {
        this.setSize(screen_width, screen_height);
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.

        utils.map.drawStaticComponents(g);
        utils.map.drawNonStaticComponents(g);

//        utils.map.drawLines(g);
        utils.map.drawTank(g);

        utils.map.drawTrees(g);

        if (utils.map.checkLose()) {
            g.drawImage(gameover, 7 * 40, 4 * 40, 40 * 6, 40 * 4, null);
            utils.level = 1;
            utils.map.tanks.clear();
        }
        
        
    }
}
