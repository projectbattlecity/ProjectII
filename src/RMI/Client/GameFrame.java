/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Client;

import Models.Audio;
import Models.Bullet;
import Models.Player;
import Models.Tank;
import RMI.Interfaces.IGameControl;
import java.awt.Dimension;
import static java.awt.SystemColor.control;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import utils.utils;

/**
 *
 * @author DK
 */
public class GameFrame extends JFrame implements KeyListener {

    IGameControl control = null;
    int clientID = -1;
    Audio audio = new Audio("ingame.wav");

    public GameFrame() {
        this.setVisible(true);
        this.setSize(new Dimension(805, 629));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle(utils.gameName);
        audio.getT1().start();
        this.addKeyListener(this);
        utils.map.AddComponents();
        this.add(new GamePanel());
        try {
            control = (IGameControl) Naming.lookup("rmi://" + utils.hostIP + ":1099/GameControl");
            clientID = control.getClientID();
            System.out.println(clientID);
            StartClient.clientPlayer.clientID = clientID;
            control.tankFromClient(StartClient.clientPlayer);

        } catch (RemoteException ex) {
            Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    Tank.Move move = Tank.Move.Stop;

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    move = Tank.Move.L;
                    control.tankMove(StartClient.clientPlayer, move);
//                    utils.map.tankCollide(StartClient.clientPlayer);
//                    StartClient.clientPlayer.tankMove(move);
                    break;
                case KeyEvent.VK_RIGHT:
                    move = Tank.Move.R;
                    control.tankMove(StartClient.clientPlayer, move);
//                    utils.map.tankCollide(StartClient.clientPlayer);
//                    StartClient.clientPlayer.tankMove(move);
                    break;
                case KeyEvent.VK_UP:
                    move = Tank.Move.U;
                    control.tankMove(StartClient.clientPlayer, move);
//                    utils.map.tankCollide(StartClient.clientPlayer);
//                    StartClient.clientPlayer.tankMove(move);
                    break;
                case KeyEvent.VK_DOWN:
                    move = Tank.Move.D;
                    control.tankMove(StartClient.clientPlayer, move);
//                    utils.map.tankCollide(StartClient.clientPlayer);
//                    StartClient.clientPlayer.tankMove(move);
                    break;
                case KeyEvent.VK_F:
                    Bullet fire = null;
                    for (Player tank : GamePanel.rebindComp.getTanks()) {
                        if (tank.clientID == clientID) {
                            fire = tank.fire();
                        }
                    }
//                    utils.map.tankCollide(StartClient.clientPlayer);
//                    fire = StartClient.clientPlayer.fire();
                    control.tankFire(fire);
                    System.out.println("fire");
                    break;
                default:
                    move = Tank.Move.Stop;
                    System.out.println("nope");
                    break;
            }
        } catch (RemoteException ex) {
            Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
