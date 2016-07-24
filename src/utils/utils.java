/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import Menu.Host;
import Models.Audio;
import Models.Maps;
import ServeMap.ServerFrame;
import Server.ServerMain;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;

/**
 *
 * @author ASUS
 */
public class utils {
    public static Toolkit tk = Toolkit.getDefaultToolkit();
    public static int level = 1;

    public static Maps map = new Maps();
    public static int x1 = 6*40, x2 = 13 * 40, y1 = 14*40, y2 = 14*40;
    
    public static int tankDestroy =0;
    public static String hostIP = "localhost";
    public static String gameName = "Duong dep trai";
    public static DefaultListModel<Host> defaultListModel = new DefaultListModel<>();
    public static String newMessage = "";
    public static Audio audio = new Audio();
    public static DefaultListModel<String> listClient = new DefaultListModel<>();
    public static JFrame main = null;
}
