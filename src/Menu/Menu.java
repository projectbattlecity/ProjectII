/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.annotation.Resource;
import static utils.utils.tk;

/**
 *
 * @author LinhTrinh
 */
public class Menu extends MouseAdapter {
    private int width = 30;
    private int height = 30;  
    public static int index = 3;
   
    Image[] bg = {tk.getImage(Resource.class.getResource("/Images/Menu/heart.png")),
        //tk.getImage(Resource.class.getResource("/Images/Menu/heart2.png")),
        tk.getImage(Resource.class.getResource("/Images/Menu/x.png")),
        tk.getImage(Resource.class.getResource("/Images/Menu/exit.png")),
        tk.getImage(Resource.class.getResource("/Images/Menu/restart.png")),
        tk.getImage(Resource.class.getResource("/Images/Menu/pause.png")),
        tk.getImage(Resource.class.getResource("/Images/Menu/resume.png")),
    };
    
    Image[] lstnumber = {tk.getImage(Resource.class.getResource("/Images/Menu/number0.png")),
        tk.getImage(Resource.class.getResource("/Images/Menu/number1.png")),
        tk.getImage(Resource.class.getResource("/Images/Menu/number2.png")),
        tk.getImage(Resource.class.getResource("/Images/Menu/number3.png")),
        tk.getImage(Resource.class.getResource("/Images/Menu/number4.png")),
        tk.getImage(Resource.class.getResource("/Images/Menu/number5.png")),
        tk.getImage(Resource.class.getResource("/Images/Menu/number6.png")),
        tk.getImage(Resource.class.getResource("/Images/Menu/number7.png")),
        tk.getImage(Resource.class.getResource("/Images/Menu/number8.png")),
        tk.getImage(Resource.class.getResource("/Images/Menu/number9.png")),
    };
    public void drawMenu(Graphics g)
    {
        g.drawImage(bg[0], 700, 615, width, height, null);
        g.drawImage(bg[1], 730, 630, 15, 15, null);
        g.drawImage(bg[2], 0, 615, width, height, null);
        g.drawImage(bg[3], 35, 615, width, height, null);
        g.drawImage(bg[4], 70, 615, width, height, null);
        
        //g.drawImage(bg[1], 580, 615, width, height, null);
        //g.drawImage(bg[2], 610, 630, 15, 15, null);
    }
    
    public void drawlifeplayer1(Graphics g){ 
        g.drawImage(lstnumber[index], 745, 620,24, 24, null);
    }
    //public void drawlifeplayer2(Graphics g){ 
        //g.drawImage(lstnumber[index], 625, 615,width, height, null);
    //}
    
    //public static void main(String[] args) {
        
   // }
    
  //  public void mousePressed(MouseEvent e){
   //     if (e.getMouse) {
            
   //     }
   // }
    
    
}
