/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Frame;

import java.awt.Rectangle;

/**
 *
 * @author Admin
 */
public class EventHandler {
    GamePanel gp;
    EventRect eventRect[][];
    
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
        int col = 0;
        int row = 0;
        while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
        eventRect[col][row] = new EventRect();
        eventRect[col][row].x = 23;
        eventRect[col][row].y = 23;
        eventRect[col][row].width = 2;
        eventRect[col][row].height = 2;
        eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
        eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;
        
        col++;
        if(col == gp.maxWorldCol) {
            col = 0;
            row++;
        }
        }

    }
    
    public void checkEvent() {
        
        //Check character more than 1 tile away from last event
        int xDistance = Math.abs(gp.player.Worldx - previousEventX);
        int yDistance = Math.abs(gp.player.Worldy - previousEventY);
        int Distance = Math.max(xDistance, yDistance);
        if(Distance > gp.tileSize) {
            canTouchEvent = true;
        }
        if(canTouchEvent == true) {
        if(hit(47,33,"right") == true) { dmgPit(47,33,gp.dialogueState);}
        if(hit(42,28,"up") == true) { healingPool(42,28,gp.dialogueState);}
        }
    }
    
    public boolean hit(int col, int row, String reqDirection) {
        boolean hit = false;
        gp.player.solidArea.x = gp.player.Worldx + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.Worldy + gp.player.solidArea.y;
        eventRect[col][row].x = col*gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row*gp.tileSize + eventRect[col][row].y;
        
        if(gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false) {
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
                
                previousEventX = gp.player.Worldx;
                previousEventY = gp.player.Worldy   ;
            }
        }
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;
        return hit;
    }
    
    public void teleport(int gameState) {
        gp.gameState = gameState;
        gp.Ui.currentDialogue = "Teleport!";
        gp.player.Worldx = gp.tileSize*62;
        gp.player.Worldy = gp.tileSize*22;  
    }
    
    public void dmgPit(int col, int row, int gameState) {
        gp.gameState = gameState;
        gp.Ui.currentDialogue = "Bạn đang đi vào khu vực nguy hiểm";
        gp.player.Life -= 1;
      //  eventRect[col][row].eventDone = true;
        canTouchEvent = false;
    }
    
    public void healingPool(int col, int row, int gameState) {
        if(gp.keyH.enterPressed == true) {
            gp.gameState = gameState;
            gp.Ui.currentDialogue = "Đây là suối nguồn tươi mát sẽ hồi phục sinh lực cho bạn ";
            gp.player.Life = gp.player.maxLife;
        }
    }
}
