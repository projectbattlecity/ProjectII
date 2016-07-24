/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

/**
 *
 * @author Nguyen Xuan Hung
 */
public class Audio {

    static InputStream input = null;
    static AudioStream as = null;
    static AudioData ad = null;
    static ContinuousAudioDataStream loop = null;
    static SourceDataLine line = null;
    static AudioInputStream ais = null;
    AudioFormat outFormat = null;
    DataLine.Info info = null;
    File file = null;
    private String music;

    public Audio() {
    }

    public Audio(String music) {
        this.music = music;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }
    public void collideHomeWall() {
        try {
//            input = new FileInputStream("src/Audio/explosive.wav");
            input = this.getClass().getResourceAsStream("/Audio/explosive.wav");
            as = new AudioStream(input);
            AudioPlayer.player.start(as);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void collideRockWall() {
        try {
            input = this.getClass().getResourceAsStream("/Audio/collideRockWall.wav");
            as = new AudioStream(input);
            AudioPlayer.player.start(as);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void collideEnemyBase() {
        try {
            input = this.getClass().getResourceAsStream("/Audio/collideEnemyBase.wav");
            as = new AudioStream(input);
            AudioPlayer.player.start(as);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void collectItems(){
        try {
            input = this.getClass().getResourceAsStream("/Audio/magicsound.wav");
            as = new AudioStream(input);
            AudioPlayer.player.start(as);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public void enemyBaseDestroy(){
        try {
            input = this.getClass().getResourceAsStream("/Audio/enemyBaseDestroy.wav");
            as = new AudioStream(input);
            AudioPlayer.player.start(as);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public void gun(){
        try {
            input = this.getClass().getResourceAsStream("/Audio/gun.wav");
            as = new AudioStream(input);
            AudioPlayer.player.start(as);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public void playerDeath() {
        try {
            input = this.getClass().getResourceAsStream("/Audio/playerDeath.wav");
            as = new AudioStream(input);
            AudioPlayer.player.start(as);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void enemyDeath() {
        try {
            input = this.getClass().getResourceAsStream("/Audio/enemyDeath.wav");
            as = new AudioStream(input);
            AudioPlayer.player.start(as);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void backgroundGame() {
//        input = this.getClass().getResourceAsStream("/Audio/"+this.music);
//        try {
//            ais = AudioSystem.getAudioInputStream(input);
//            outFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, ais.getFormat().getSampleRate(), 16, ais.getFormat().getChannels(), ais.getFormat().getChannels() * 2, ais.getFormat().getSampleRate(), false);
//            info = new DataLine.Info(SourceDataLine.class, outFormat);
//            line = (SourceDataLine) AudioSystem.getLine(info);
//            if (line != null) {
//                line.open(outFormat);
////                line.start();
////                stream(ais, line);
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (UnsupportedAudioFileException ex) {
//            Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (LineUnavailableException ex) {
//            Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
//        }
        try {
            input = this.getClass().getResourceAsStream("/Audio/"+this.music);
            as = new AudioStream(input);
            AudioPlayer.player.start(as);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    void stream(AudioInputStream in, SourceDataLine line) {
        try {
            byte[] buffer = new byte[1024];
            for (int i = 0; i != -1; i = in.read(buffer, 0, buffer.length)) {
                line.write(buffer, 0, i);

            }
        } catch (IOException ex) {
            Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Thread getT1() {
        return t1;
    }

    public void setT1(Thread t1) {
        this.t1 = t1;
    }
    
    private Thread t1 = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    backgroundGame();
                    Thread.sleep(80000);
//                line.start();
//                stream(ais, line);
//                line.stop();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    });

}
