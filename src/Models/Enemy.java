/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author ASUS
 */
public class Enemy extends Tank{

    public Enemy(int x, int y) {
        super(x, y);
    }

    public Enemy(int x, int y, int tank_level) {
        super(x, y);
        this.tank_level = tank_level;
    }
    
    public Enemy() {
    }
    
}
