/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServeMap;

import Server.ServerMain;
import Models.Tank;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import utils.utils;

/**
 *
 * @author DK
 */
public class ServerFrame extends JFrame implements KeyListener, Runnable {

    Tank.Move move = Tank.Move.Stop;
//   
    public ServerFrame() {
        this.setVisible(true);
        this.setSize(new Dimension(805, 629));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        utils.map.AddComponents();

        this.add(new ServerPanel());

//      
        new Thread(this).start();

        this.addKeyListener(this);

    }

//    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                move = Tank.Move.L;
                ServerMain.serverTank.tankMove(move);
                break;
            case KeyEvent.VK_RIGHT:
                move = Tank.Move.R;
                ServerMain.serverTank.tankMove(move);
                break;
            case KeyEvent.VK_UP:
                move = Tank.Move.U;
                ServerMain.serverTank.tankMove(move);
                break;
            case KeyEvent.VK_DOWN:
                move = Tank.Move.D;
                ServerMain.serverTank.tankMove(move);
                break;
            case KeyEvent.VK_F:
                ServerMain.serverTank.fire();
                break;
            default:
                move = Tank.Move.Stop;
                System.out.println("nope");
                break;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(ServerFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
