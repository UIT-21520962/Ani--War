/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

import Frame.GamePanel;
import PlayerandAnimal.Entity;

/**
 *
 * @author Admin
 */
public class Object_Door extends Entity {
    public Object_Door(GamePanel gp) {
        super(gp);
        name = "Door";
        down1 = setup("door",gp.tileSize,gp.tileSize);
        Collision = true;
        
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}