/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author ASUS
 */
public class Player extends Tank implements ActionListener{

    public int clientID;

    public Player(int x, int y) {
        super(x, y);
    }

    public Player() {
    }

    public Player(int x, int y, Move m, int cID) {
        super(x, y, m);
        this.clientID = cID;
    }

    public Player(int x, int y, Move m, int cID, int level) {
        super(x, y, m,level);
        this.clientID = cID;
    }
    
    public void genPlayer() {
        utils.utils.map.tanks.remove(this);
        x = utils.utils.x1;
        y = utils.utils.y1;
        oldX = utils.utils.x1;
        oldY = utils.utils.y1;

        timer = new Timer(1000, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        time++;
        if (time <= 2) {
            shootable = false;
            this.move = Move.Stop;
        }
        if (time == 3) {
            shootable = true;
            utils.utils.map.tanks.add(this);
            shield = true;
            System.out.println(shootable);
        }
        if (time >= 5) {
            time = 0;
            shield = false;
            timer.stop();
        }
    }
}
