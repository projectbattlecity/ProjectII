/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientMap;

import Client.ClientMain;
import Models.HomeWall;
import RMI.Interface.IGame;
import RMI.Interface.IMap;
import ServeMap.*;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import utils.utils;

/**
 *
 * @author DK
 */
public class ClientPanel extends JPanel {

    public static int screen_width = 800, screen_height = 600;

    public ClientPanel() {
        this.setSize(screen_width, screen_height);
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        utils.objMap.drawStaticComponents(g);
        utils.objMap.drawNonStaticComponents(g);
        utils.objMap.drawTank(g);
        utils.objMap.drawTrees(g);

    }
}
