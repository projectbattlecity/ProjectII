/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Nguyen Xuan Hung
 */
public class Audio {

    static InputStream input = null;
    static AudioStream as = null;

    public static void colideHomeWall() {
        try {
            input = new FileInputStream("src/Audio/explosive.wav");
            as = new AudioStream(input);
            AudioPlayer.player.start(as);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void playerDeath() {
        try {
            input = new FileInputStream("src/Audio/death.wav");
            as = new AudioStream(input);
            AudioPlayer.player.start(as);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
