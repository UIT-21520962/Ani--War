    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Frame;

import PlayerandAnimal.Entity;

/**
 *
 * @author Admin
 */
public class CollisionCheck {
    
    GamePanel gp;
    
    public CollisionCheck(GamePanel gp) {
        this.gp = gp;
    }
    
    public void Checktile(Entity entity) {
        int entityLeftWorldX = entity.Worldx + entity.solidArea.x;
        int entityRightWorldX = entity.Worldx + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.Worldy + entity.solidArea.y;
        int entityBottomWorldY = entity.Worldy + entity.solidArea.y + entity.solidArea.height;
        
        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;
        
        int Tilenum1, Tilenum2;
        
        switch(entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                Tilenum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                Tilenum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileM.tile[Tilenum1].collision==true || gp.tileM.tile[Tilenum2].collision==true ) {
                    entity.CollisionOn=true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                Tilenum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                Tilenum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[Tilenum1].collision==true || gp.tileM.tile[Tilenum2].collision==true ) {
                    entity.CollisionOn=true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                Tilenum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                Tilenum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[Tilenum1].collision==true || gp.tileM.tile[Tilenum2].collision==true ) {
                    entity.CollisionOn=true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                Tilenum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                Tilenum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[Tilenum1].collision==true || gp.tileM.tile[Tilenum2].collision==true ) {
                    entity.CollisionOn=true;
                }
                break;
        }
    }
        public int checkObject(Entity entity, boolean player) {
            int index = 999;
            for(int i = 0; i < gp.obj.length; i++) {
                if(gp.obj[i] != null) {
                    //get obj's solidarea position
                    entity.solidArea.x = entity.Worldx + entity.solidArea.x;
                    entity.solidArea.y = entity.Worldy + entity.solidArea.y;
                    //get the object's solid are position
                    gp.obj[i].solidArea.x = gp.obj[i].Worldx + gp.obj[i].solidArea.x;
                    gp.obj[i].solidArea.y = gp.obj[i].Worldy + gp.obj[i].solidArea.y;
                    
                    switch(entity.direction) {
                        case "up":
                            entity.solidArea.y -= entity.speed;
                            break;
                        case "down":
                            entity.solidArea.y += entity.speed;
                            break;
                        case "left":
                            entity.solidArea.x -= entity.speed;
                            break;
                        case "right":
                            entity.solidArea.x += entity.speed;
                            break;                        
                    }
                    if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                        if(gp.obj[i].Collision == true) {
                            entity.Collision = true;
                        }
                        if(player == true) {
                        index = i;
                        }
                    }
                    entity.solidArea.x = entity.solidAreaDefaultX;
                    entity.solidArea.y = entity.solidAreaDefaultY;
                    gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                    gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
                }
                
            }
            return index;
        }
        
        public int CheckEntity(Entity entity, Entity[] target) {
                        int index = 999;
            for(int i = 0; i < target.length; i++) {
                if(target[i] != null) {
                    //get obj's solidarea position
                    entity.solidArea.x = entity.Worldx + entity.solidArea.x;
                    entity.solidArea.y = entity.Worldy + entity.solidArea.y;
                    //get the object's solid are position
                    target[i].solidArea.x = target[i].Worldx + target[i].solidArea.x;
                    target[i].solidArea.y = target[i].Worldy + target[i].solidArea.y;
                    
                    switch(entity.direction) {
                        case "up" -> entity.solidArea.y -= entity.speed;
                        case "down" -> entity.solidArea.y += entity.speed;
                        case "left" -> entity.solidArea.x -= entity.speed;
                        case "right" -> entity.solidArea.x += entity.speed;                        
                    }
                                    if(entity.solidArea.intersects(target[i].solidArea)) {
                                    if(target[i] != entity) {
                                        entity.CollisionOn = true;
                                        index = i;
                                        break;
                                    }                                                      
                            }
                    entity.solidArea.x = entity.solidAreaDefaultX;
                    entity.solidArea.y = entity.solidAreaDefaultY;
                    target[i].solidArea.x = target[i].solidAreaDefaultX;
                    target[i].solidArea.y = target[i].solidAreaDefaultY;
                }
                
            }
            return index;
        }
        public boolean CheckPlayer(Entity entity) {
                    boolean contactPlayer = false;
                                //get obj's solidarea position
                    entity.solidArea.x = entity.Worldx + entity.solidArea.x;
                    entity.solidArea.y = entity.Worldy + entity.solidArea.y;
                    //get the object's solid are position
                    gp.player.solidArea.x = gp.player.Worldx + gp.player.solidArea.x;
                    gp.player.solidArea.y = gp.player.Worldy + gp.player.solidArea.y;
                    
                    switch(entity.direction) {
                        case "up" -> entity.solidArea.y -= entity.speed;
                        case "down" -> entity.solidArea.y += entity.speed;
                        case "left" -> entity.solidArea.x -= entity.speed;
                        case "right" -> entity.solidArea.x += entity.speed;                        
                    }
                    
                    if(entity.solidArea.intersects(gp.player.solidArea)) {
                        entity.CollisionOn = true;
                        contactPlayer = true;
                    }
                    
                    entity.solidArea.x = entity.solidAreaDefaultX;
                    entity.solidArea.y = entity.solidAreaDefaultY;
                    gp.player.solidArea.x = gp.player.solidAreaDefaultX;
                    gp.player.solidArea.y = gp.player.solidAreaDefaultY;
                    return contactPlayer;
        }
        
    }

