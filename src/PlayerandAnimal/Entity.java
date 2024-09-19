/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PlayerandAnimal;

import Frame.GamePanel;
import Frame.UtilityTools;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Thuong
 */
public class Entity {
    public GamePanel gp;
    public int Worldx,Worldy;
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public String direction = "down";
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0,0,48,48); 
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean CollisionOn = false;
    public int actionLockCounter = 0;
    public boolean invincible = false;
    boolean attacking = false;
    public int invincibleCounter = 0;
    String dialogues[] = new String[20];
    int dialogueIndex = 0;   
    public int maxLife;
    public int Life;
    public BufferedImage image;
    public BufferedImage image2;
    public BufferedImage image3;
    public String name;
    public boolean Collision = false;
    public int type; // 0 = player, 1 = npc, 2 = monster
    
    public Entity(GamePanel gp) {
        this.gp = gp;
    }
    public void setAction() {
        
    }
    public void speak() {
        
        
        if(dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.Ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
        
        switch(gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
             
        }
    }
    public void update() {
        setAction();
        CollisionOn = false;
        gp.cChecker.Checktile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.CheckEntity(this, gp.npc);
        gp.cChecker.CheckEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.CheckPlayer(this);
        if(this.type == 2 && contactPlayer == true) {
            if(gp.player.invincible == false) {
                gp.player.Life -= 1;
                gp.player.invincible = true;    
            }
        }
         //IF CONLLISION IS FALSE, PLAYER CAN MOVE
            if(CollisionOn == false) {
            switch(direction) {
                case "up": Worldy -= speed; 
                break;
                case "down": Worldy += speed; 
                break;
                case "left": Worldx -= speed; 
                break;
                case "right": Worldx += speed; 
                break;
            }
        }
        
        spriteCounter++;
        if(spriteCounter > 10) {
            if(spriteNum ==1) {
                spriteNum = 2;
            }
            else if(spriteNum ==2) {
             spriteNum=1;       
            }
           spriteCounter = 0;
        }
    }
   
    public void draw(Graphics2D g2) {
            BufferedImage image = null;
            int ScreenX = Worldx - gp.player.Worldx + gp.player.screenX;
            int ScreenY = Worldy - gp.player.Worldy + gp.player.screenY;
            
            if(Worldx + gp.tileSize > gp.player.Worldx - gp.player.screenX && 
               Worldx - gp.tileSize < gp.player.Worldx + gp.player.screenX && 
               Worldy + gp.tileSize > gp.player.Worldy - gp.player.screenY && 
               Worldy - gp.tileSize < gp.player.Worldy + gp.player.screenY) {
                switch(direction) {
            case "up" -> {
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2) {
                    image = up2;
                }
                break;
            }
            case "down" -> {
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                break;
            }
            case "left" -> {
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                break;
            }
            case "right" -> {
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                break;
            }  
        
                }   
                g2.drawImage(image, ScreenX, ScreenY, gp.tileSize, gp.tileSize, null);
            }
        
    }
    public BufferedImage setup(String imageName, int width, int height) {
        UtilityTools uTool = new UtilityTools();
        BufferedImage Image = null;
        
        try {
            Image = ImageIO.read(getClass().getResourceAsStream("/Image/"+ imageName+".png"));
            Image = uTool.scaleImage(Image, width, height);
            
        } catch(IOException e) {
            e.printStackTrace();
        }
        return Image;
    }
}
 