/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientMap;

import Client.ClientMain;
import Models.Tank;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author DK
 */
public  class ClientPanel extends JPanel{

    
    //Tank tank = ClientMain.serverTank;
   

    public ClientPanel() {
    }
    
    
    
    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        ClientMain.serverTank.drawTank(g);
        ClientMain.clientTank.drawTank(g);
    }

    public void Update(){
        repaint();
    }
    
    
    
}
