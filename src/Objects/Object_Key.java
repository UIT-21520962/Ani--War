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
public class Object_Key extends Entity {
    public Object_Key(GamePanel gp) {
        super(gp);
 
        name = "Key";
        down1 = setup("key",gp.tileSize,gp.tileSize);
    }
}