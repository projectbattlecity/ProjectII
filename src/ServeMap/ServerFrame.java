/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServeMap;

import Models.Audio;
import Models.Item;
import Server.ServerMain;
import Models.Tank;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.Timer;
import utils.utils;

/**
 *
 * @author DK
 */
public class ServerFrame extends JFrame implements KeyListener, Runnable {

    Tank.Move move = Tank.Move.Stop;
    Audio audio = new Audio("ingame.wav");

    public ServerFrame() {
        this.setVisible(true);
        this.setSize(new Dimension(805, 629));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        audio.getT1().start();
        utils.map.AddComponents();
        utils.map.genEnemy();
        utils.map.genPlayer();
        utils.main = this;
        this.add(new ServerPanel());

        Thread tRepaint = new Thread(this);
        tRepaint.setPriority(Thread.MAX_PRIORITY);
        tRepaint.start();

        this.addKeyListener(this);

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    Timer tmr = null;
    int key = KeyEvent.VK_ENTER;

    @Override
    public void keyPressed(KeyEvent e) {
        key = e.getKeyCode();
        if (tmr == null) {
            tmr = new Timer(40, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
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
                            break;
                    }
                }
            });
        }
        if (!tmr.isRunning()) {
            tmr.start();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        tmr.stop();
    }

    @Override
    public void run() {
        while (true) {
            repaint();
            utils.map.genNewEnemy();

            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(ServerFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
