/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientMap;

import Models.Maps;
import ServeMap.*;
import Server.ServerMain;
import Models.Tank;
import RMI.Interface.IMap;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import utils.utils;
import static utils.utils.serverTank;

/**
 *
 * @author DK
 */
public class ClientFrame extends JFrame implements KeyListener, Runnable {

    Tank.Move move = Tank.Move.Stop;
    public static IMap objMap = null;

    public ClientFrame() {
        this.setVisible(true);
        this.setSize(new Dimension(805, 629));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        try {
            utils.objMap = new Maps();
            utils.objMap.AddComponents();
            objMap = (IMap) Naming.lookup("rmi://localhost:1099/map");
            objMap.AddComponents();
        } catch (NotBoundException ex) {
            Logger.getLogger(ClientFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClientFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.add(new ClientPanel());

        new Thread(this).start();

        this.addKeyListener(this);

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_A:
                move = Tank.Move.L;
                utils.serverTank.tankMove(move);
                break;
            case KeyEvent.VK_D:
                move = Tank.Move.R;
                utils.serverTank.tankMove(move);
                break;
            case KeyEvent.VK_W:
                move = Tank.Move.U;
                utils.serverTank.tankMove(move);
                break;
            case KeyEvent.VK_S:
                move = Tank.Move.D;
                utils.serverTank.tankMove(move);
                break;
            case KeyEvent.VK_SPACE:
                utils.serverTank.fire();
                break;
            case KeyEvent.VK_LEFT:
                move = Tank.Move.L;
                utils.clientTank.tankMove(move);
                break;
            case KeyEvent.VK_RIGHT:
                move = Tank.Move.R;
                utils.clientTank.tankMove(move);
                break;
            case KeyEvent.VK_UP:
                move = Tank.Move.U;
                utils.clientTank.tankMove(move);
                break;
            case KeyEvent.VK_DOWN:
                move = Tank.Move.D;
                utils.clientTank.tankMove(move);
                break;
            case KeyEvent.VK_NUMPAD0:
                utils.clientTank.fire();
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
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(ClientFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
