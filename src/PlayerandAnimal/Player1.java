/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PlayerandAnimal;

import Frame.GamePanel;
import Frame.KeyHandler;
import Frame.UtilityTools;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Thuong
 */
public final class Player1 extends Entity {
    KeyHandler keyH;
    
    public final int screenX;
    public final int screenY;
    
    public int hasKey = 0; 

    public Player1(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;
        
        solidArea = new Rectangle(8,16,32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        
        attackArea.width = 36;
        attackArea.height = 36;
       
        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void getPlayerImage() {
        up1 = setup("boy_up_1",gp.tileSize,gp.tileSize);
        up2 = setup("boy_up_2",gp.tileSize,gp.tileSize);
        down1 = setup("boy_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("boy_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("boy_left_1",gp.tileSize,gp.tileSize);
        left2 = setup("boy_left_2",gp.tileSize,gp.tileSize);
        right1 = setup("boy_right_1",gp.tileSize,gp.tileSize);
        right2 = setup("boy_right_2",gp.tileSize,gp.tileSize);
    }
    
        public void getPlayerAttackImage() {
        attackUp1 = setup("boy_attack_up_1",gp.tileSize,gp.tileSize*2);
        attackUp2 = setup("boy_attack_up_2",gp.tileSize,gp.tileSize*2);
        attackDown1 = setup("boy_attack_down_1",gp.tileSize,gp.tileSize*2);
        attackDown2 = setup("boy_attack_down_2",gp.tileSize,gp.tileSize*2);
        attackLeft1 = setup("boy_attack_left_1",gp.tileSize*2,gp.tileSize);
        attackLeft2 = setup("boy_attack_left_2",gp.tileSize*2,gp.tileSize);
        attackRight1 = setup("boy_attack_right_1",gp.tileSize*2,gp.tileSize);
        attackRight2 = setup("boy_attack_right_2",gp.tileSize*2,gp.tileSize);
    }

    public void setDefaultValues() {
        Worldx = gp.tileSize*42;
        Worldy = gp.tileSize*38;
        speed = 4;
        direction = "down";
        
        //PLAYER STATUS
        maxLife = 6;
        Life = maxLife;
    }

    @Override
    public void update() {
        if(attacking == true) {
            attacking();
        }
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {    
        if (keyH.upPressed == true) {
            direction = "up";
        } else if (keyH.downPressed == true) {
            direction = "down";

        } else if (keyH.leftPressed == true) {
            direction = "left";
        } else if (keyH.rightPressed == true) {
            direction = "right";
        }
        
        
        // CHECK TILE COLLISION
        CollisionOn = false;
        gp.cChecker.Checktile(this);
        //CHECK OBJECT COLLISION
        int ObjIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(ObjIndex);
        //CHECK NPC COLLISION
        int npcIndex = gp.cChecker.CheckEntity(this, gp.npc);
        interactNPC(npcIndex);
        //CHECK MONSTER COLLISION
        int monsterIndex = gp.cChecker.CheckEntity(this, gp.monster);
        contactMonster(monsterIndex);
        //CHECK EVENT 
        gp.eHandler.checkEvent();
        //IF CONLLISION IS FALSE, PLAYER CAN MOVE
        if(CollisionOn == false && keyH.enterPressed == false) {
            switch(direction) {
                case "up" -> Worldy -= speed;
                case "down" -> Worldy += speed;
                case "left" -> Worldx -= speed;
                case "right" -> Worldx += speed;
            }
        }
        gp.keyH.enterPressed = false;
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
    

    if(invincible == true) {
        invincibleCounter++;
        if(invincibleCounter > 60) {
            invincible = false;
            invincibleCounter = 0;
        }
    } 
}
    public void attacking() {
        spriteCounter++;
        if(spriteCounter <= 5) {
            spriteNum = 1;
        }
        if(spriteCounter > 5 && spriteCounter <= 25) {
            //save the current Wx, Wx, solidArea
            spriteNum = 2;
            int currentWorldX = Worldx;
            int currentWorldY = Worldy;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.width;
            //adjust player's worldX/Y for the attackArea
            switch(direction) {
                case "up": Worldy -= attackArea.height; break;
                case "down": Worldy -= attackArea.height; break;
                case "left": Worldx -= attackArea.width; break;
                case "right": Worldx -= attackArea.width; break;
            }
            //attack  area become solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            //check monster collision
            int monsterIndex = gp.cChecker.CheckEntity(this, gp.npc);
            dmgMonster(monsterIndex);
            Worldx = currentWorldX;
            Worldy = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
            
        }
        if(spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
    public void pickUpObject(int i) {
        if(i != 999) {
            String Objectname = gp.obj[i].name;
            switch(Objectname) {
                case "Key" -> {
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i] = null;
                    gp.Ui.showMessage("You've got a key! ");
                }
                case "Door" -> {
                    if(hasKey > 0) {
                        gp.playSE(3);
                        gp.obj[i] = null;
                        gp.Ui.showMessage("You opened the door! ");
                        hasKey--;
                    }
                    else {
                        gp.Ui.showMessage("You need a key! ");
                        gp.playSE(5);
                    }
                    System.out.println("Key:"+hasKey);
                }
                case "boots" -> {
                    gp.playSE(2);
                    speed += 1;
                    gp.obj[i] = null;
                    gp.Ui.showMessage("Speed Up! ");
                }
                case "Chest" -> {
                    gp.Ui.gameFinished = true;
                    gp.stopMusic(i);
                    gp.playSE(4);
                }
            }
        }
        
    }
    
    public void interactNPC(int i) {
        if(gp.keyH.enterPressed == true) {
            if(i != 999) {
            gp.gameState = gp.dialogueState; 
           gp.npc[i].speak();
            }
            
        else {
                attacking = true;
            }
        }
    } 
    public void contactMonster(int i) {
        if(i != 999) {
            if(invincible == false) {           
            Life -= 1;
            invincible = true;
            }
        }
    }
    
    public void dmgMonster(int i) {
        if(i != 999) {
            System.out.println("Hit");
        }
        else {
            System.out.println("Miss");
        }
    }
    
    @Override
    public void draw(Graphics2D g2) {
        //g2.setColor(Color.pink);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;
        
        
        switch(direction) {
            case "up" -> {
                if(attacking == false) {
                  if(spriteNum == 1) {image = up1;}
                  if(spriteNum == 2) {image = up2;}  
                } 
                if(attacking == true) {
                   tempScreenY = screenY - gp.tileSize;
                  if(spriteNum == 1) {image = attackUp1;}
                  if(spriteNum == 2) {image = attackUp2;}
                }                
                break;
            }
            case "down" -> {
                if(attacking == false) {
                  if(spriteNum == 1) {image = down1;}
                  if(spriteNum == 2) {image = down2;}  
                }
                if(attacking == true) {
                  if(spriteNum == 1) {image = attackDown1;}
                  if(spriteNum == 2) {image = attackDown2;}
                }   
                break;
            }
            case "left" -> {
                if(attacking == false) {
                  if(spriteNum == 1) {image = left1;}
                  if(spriteNum == 2) {image = left2;}  
                }
                if(attacking == true) {
                    tempScreenX = screenX - gp.tileSize;
                  if(spriteNum == 1) {image = attackLeft1;}
                  if(spriteNum == 2) {image = attackLeft2;}
                }   
                break;
            }
            case "right" -> {
                if(attacking == false) {
                  if(spriteNum == 1) {image = right1;}
                  if(spriteNum == 2) {image = right2;}  
                }
                if(attacking == true) {
                  if(spriteNum == 1) {image = attackRight1;}
                  if(spriteNum == 2) {image = attackRight2;}
                }   
                break;
            }  
        }
        if(invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3F));
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null);
        //reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
        //DEBUG
       // g2.setFont(new Font("Arial", Font.PLAIN,26));
       // g2.setColor(Color.WHITE);
       // g2.drawString("Invincible:" + invincibleCounter, 10,400);
    }
}
