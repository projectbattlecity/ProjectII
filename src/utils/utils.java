/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import Models.Maps;
import RMI.Models.Game;
import java.awt.Toolkit;

/**
 *
 * @author ASUS
 */
public class utils {
    public static Toolkit tk = Toolkit.getDefaultToolkit();
    public static int level = 1;

    public static Maps map = new Maps();
    public static Game objGame;
}
