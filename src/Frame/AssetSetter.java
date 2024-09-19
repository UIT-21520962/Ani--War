/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Frame;

import Monster.GreenSlime;
import Objects.Object_boots;
import Objects.Object_Chest;
import Objects.Object_Door;
import Objects.Object_Key;
import PlayerandAnimal.NPC;



/**
 *
 * @author Admin
 */
public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    public void SetObject() {
        gp.obj[0] = new Object_Key(gp);
        gp.obj[0].Worldx = 35*gp.tileSize;
        gp.obj[0].Worldy = 80*gp.tileSize;
        
        gp.obj[1] = new Object_Key(gp);
        gp.obj[1].Worldx = 58*gp.tileSize;
        gp.obj[1].Worldy = 65*gp.tileSize;
        
        gp.obj[2] = new Object_Key(gp);
        gp.obj[2].Worldx = 42*gp.tileSize;
        gp.obj[2].Worldy = 22*gp.tileSize;
        
        gp.obj[3] = new Object_Door(gp);
        gp.obj[3].Worldx = 30*gp.tileSize;
        gp.obj[3].Worldy = 27*gp.tileSize;
        
        gp.obj[4] = new Object_Door(gp);
        gp.obj[4].Worldx = 29*gp.tileSize;
        gp.obj[4].Worldy = 27*gp.tileSize;
        
        gp.obj[5] = new Object_Door(gp);
        gp.obj[5].Worldx = 34*gp.tileSize;
        gp.obj[5].Worldy = 40*gp.tileSize;
        
        gp.obj[6] = new Object_Chest(gp);
        gp.obj[6].Worldx = 31*gp.tileSize;
        gp.obj[6].Worldy = 23*gp.tileSize;
        
        gp.obj[7] = new Object_boots(gp);
        gp.obj[7].Worldx = 57*gp.tileSize;
        gp.obj[7].Worldy = 62*gp.tileSize;        
    }
    
    public void setNPC() {
        gp.npc[0] = new NPC(gp);
        gp.npc[0].Worldx = gp.tileSize*41;
        gp.npc[0].Worldy = gp.tileSize*37;

    }
    
    public void setMonster() {
        gp.monster[0] = new GreenSlime(gp);
        gp.monster[0].Worldx = gp.tileSize*33;
        gp.monster[0].Worldy = gp.tileSize*74;
        
        gp.monster[1] = new GreenSlime(gp);
        gp.monster[1].Worldx = gp.tileSize*48;
        gp.monster[1].Worldy = gp.tileSize*76;
    }
}
