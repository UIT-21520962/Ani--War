/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Monster;

import Frame.GamePanel;
import PlayerandAnimal.Entity;
import java.util.Random;

/**
 *
 * @author Admin
 */
public final class GreenSlime extends Entity {
    
    public GreenSlime(GamePanel gp) {
        super(gp);
        type = 2;
        name = "Green Slime";
        speed = 1;
        maxLife = 4;
        Life = maxLife;
        
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
    }
    public void getImage() {
        up1 = setup("greenslime_down_1",gp.tileSize,gp.tileSize);
        up2 = setup("greenslime_down_2",gp.tileSize,gp.tileSize);
        down1 = setup("greenslime_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("greenslime_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("greenslime_down_1",gp.tileSize,gp.tileSize);
        left2 = setup("greenslime_down_2",gp.tileSize,gp.tileSize);
        right1 = setup("greenslime_down_1",gp.tileSize,gp.tileSize);
        right2 = setup("greenslime_down_2",gp.tileSize,gp.tileSize);
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
    
}
