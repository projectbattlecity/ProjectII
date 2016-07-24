/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI.Client;

import Models.BigExplosion;
import Models.Bullet;
import Models.Enemy;
import Models.EnemyBase;
import Models.Explosion;
import Models.HomeWall;
import Models.Item;
import Models.Player;
import Models.RockWall;
import Models.StoneWall;
import RMI.Models.RebindComp;
import java.awt.BorderLayout;
import java.awt.Graphics;
import javax.swing.JPanel;
import utils.utils;

/**
 *
 * @author DK
 */
public class GamePanel extends JPanel {
    public static RebindComp rebindComp = null;
    public GamePanel() {
        this.setSize(800, 600);
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {    
                    
                    rebindComp = new RebindComp();
                    
                    repaint();
                    
                    
                }
            }
        }).start();
    }
    
    
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        utils.map.drawStaticComponents(g);
        drawNonStaticComp(g);
        utils.map.drawTrees(g);
    }
    
    
    
    public void drawNonStaticComp(Graphics g){
        rebindComp.getHome().draw(g);
        for (HomeWall homeWall : rebindComp.getHomeWalls()) {
            homeWall.draw(g);
        }
        for (EnemyBase obBase : rebindComp.getObBases()) {
            obBase.draw(g);
        }
        
        for (RockWall rockWall : rebindComp.getRockWalls()) {
            rockWall.draw(g);
        }
        for (StoneWall stoneWall : rebindComp.getStoneWalls()) {
            stoneWall.draw(g);
        }
        
        for (Item item : rebindComp.getItems()) {
            item.draw(g);
        }
        
        for (Enemy enemyTank : rebindComp.getEnemyTanks()) {
            
            enemyTank.drawTank(g);
        }
        for (Player tank : rebindComp.getTanks()) {
            tank.drawTank(g);
        }
        
        for (BigExplosion bigExplosion : rebindComp.getBigExplosions()) {
            bigExplosion.draw(g);
        }
        for (Explosion explosion : rebindComp.getExplosions()) {
            explosion.draw(g);
            
        }
        for (Bullet bullet : rebindComp.getBullets()) {
            bullet.drawBullet(g);
        }
        
        
    }
    
    @Override
    public void repaint() {
        super.repaint();
    }
    
}
