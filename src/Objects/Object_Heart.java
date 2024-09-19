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
public class Object_Heart extends Entity {
    public Object_Heart(GamePanel gp) {
        super(gp);
        name = "Heart";
        image = setup("heart_full",gp.tileSize,gp.tileSize);
        image2 = setup("heart_half",gp.tileSize,gp.tileSize);
        image3 = setup("heart_blank",gp.tileSize,gp.tileSize);
        Collision = true;
    }
}
