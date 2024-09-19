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
public class Object_boots extends Entity {
    public Object_boots(GamePanel gp) {
        super(gp);
        name = "boots";
        down1 = setup("boots",gp.tileSize,gp.tileSize); 
    }
}
