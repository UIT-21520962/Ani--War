/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PlayerandAnimal;

import Frame.GamePanel;
import Frame.UtilityTools;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.util.Random;
import javax.imageio.ImageIO;



/**
 *
 * @author Admin
 */
public final class NPC extends Entity  {
    
    public NPC(GamePanel gp) {
        super(gp);
        
        direction = "down";
        speed = 1; 
        getImage();
        setDialogues();
    }
    public void getImage() {
        up1 = setup("oldman_up_1",gp.tileSize,gp.tileSize);
        up2 = setup("oldman_up_2",gp.tileSize,gp.tileSize);
        down1 = setup("oldman_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("oldman_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("oldman_left_1",gp.tileSize,gp.tileSize);
        left2 = setup("oldman_left_2",gp.tileSize,gp.tileSize);
        right1 = setup("oldman_right_1",gp.tileSize,gp.tileSize);
        right2 = setup("oldman_right_2",gp.tileSize,gp.tileSize);
    }
    
    public void setDialogues() {
        dialogues[0] = "Chào mừng bạn đến vương quốc Ani";
        dialogues[1] = "Tôi cũng đã từng đứng ở vị trí của cậu";
        dialogues[2] = "Hãy đi tìm kho báu của riêng mình, đừng như tôi!!";
        dialogues[3] = "Chúc cậu may mắn, chàng trai trẻ!";
    }
    
    @Override
    public void setAction() {
        actionLockCounter++;
        if(actionLockCounter == 120) {
           Random rand = new Random();
        int i = rand.nextInt(100)+1;
        if(i <= 25) {
            direction = "up";
        }
        if(i > 25 && i <= 50) {
            direction = "down";
        }
        if(i > 50 && i <= 75) {
            direction = "left";
        }
        if(i > 75 && i <= 100) {
            direction = "right";
        } 
        actionLockCounter = 0;
        }
        
    }
    
    @Override
    public void speak() {
        super.speak();
    }

    
}
