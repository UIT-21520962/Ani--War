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
public class Object_Chest extends Entity {
    public Object_Chest(GamePanel gp) {
        super(gp);
        name = "Chest";
        down1 = setup("chest",gp.tileSize,gp.tileSize);
    }
}
