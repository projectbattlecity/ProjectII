/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServeMap;

import Server.ServerMain;
import java.awt.BorderLayout;
import java.awt.Graphics;
import javax.swing.JPanel;
import utils.utils;

/**
 *
 * @author DK
 */
public class ServerPanel extends JPanel {

    public static int screen_width = 800, screen_height = 600;

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
       
//        utils.map.drawTank(g);

        utils.map.tankCollide(g);
//        utils.map.drawTrees(g);
    }
    
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        utils.map.drawStaticComponents(g);
//        utils.map.drawNonStaticComponents(g);
//        ServerMain.serverTank.drawTank(g);
//        ServerMain.clientTank.drawTank(g);
//        
//    }
}
