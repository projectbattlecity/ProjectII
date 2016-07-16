/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientMap;

import Client.ClientMain;
import Server.ServerMain;
import Models.Tank;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author DK
 */
public class ClientFrame extends JFrame implements Runnable, KeyListener {

    Tank.Move move = Tank.Move.Stop;

    //ClientPanel cp;
    //Tank.Move move = Tank.Move.Stop;
    public ClientFrame() {
        this.setVisible(true);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        //cp = new ClientPanel(ClientMain.serverTank);
        this.add(new ClientPanel());
        this.addKeyListener(this);
        
        new Thread(this).start();

    }

    @Override
    public void run() {
        while (true) {
            //ClientMain.serverTank.tankMove(Tank.Move.U);
            //cp.Update();
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            move = Tank.Move.L;
        } else if (key == KeyEvent.VK_RIGHT) {
            move = Tank.Move.R;
        } else if (key == KeyEvent.VK_UP) {
            move = Tank.Move.U;
        } else if (key == KeyEvent.VK_DOWN) {
            move = Tank.Move.D;
        } else {
            move = Tank.Move.Stop;
            System.out.println("nope");
        }
        ClientMain.clientTank.tankMove(move);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
