/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tile;

/**
 *
 * @author Thuong
 */
import Frame.GamePanel;
import Frame.UtilityTools;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

public final class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[310];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/Image/NewWorld.txt");
        //loadMap("/Image/worldV2.txt");
    }

    public void getTileImage() {
        /* setup(0,"grass00",false);
        setup(1,"grass00",false);
        setup(2,"grass00",false);
        setup(3,"grass00",false);
        setup(4,"grass00",false);
        setup(5,"grass00",false);
        setup(6,"grass00",false);
        setup(7,"grass00",false);
        setup(8,"grass00",false);
        setup(9,"grass00",false);
        setup(10,"grass00",false);
        setup(11,"grass01",false);
        setup(12,"water00",true);
        setup(13,"water01",true);
        setup(14,"water02",true);
        setup(15,"water03",true);
        setup(16,"water04",true);
        setup(17,"water05",true);
        setup(18,"water06",true);
        setup(19,"water07",true);
        setup(20,"water08",true);
        setup(21,"water09",true);
        setup(22,"water10",true);
        setup(23,"water11",true);
        setup(24,"water12",true);
        setup(25,"water13",true);
        setup(26,"road00",false);
        setup(27,"road01",false);
        setup(28,"road02",false);
        setup(29,"road03",false);
        setup(30,"road04",false);
        setup(31,"road05",false);
        setup(32,"road06",false);
        setup(33,"road07",false);
        setup(34,"road08",false);
        setup(35,"road09",false);
        setup(36,"road10",false);
        setup(37,"road11",false);
        setup(38,"road12",false);
        setup(39,"earth",false);
        setup(40,"wall",true);
        setup(41,"tree",true);*/
        setup(0, "earth", false);
        setup(1, "earth", false);
        setup(2, "earth_1", false);
        setup(3, "earth_1", false);
        setup(4, "earth_1", false);
        setup(5, "earth_1", false);
        setup(6, "earth_1", false);
        setup(7, "earth_1", false);
        setup(8, "earth_1", false);
        setup(9, "earth_1", false);
        setup(10, "earth_1", false);
        setup(11, "earth_1", false);
        setup(12, "earth_1", false);
        setup(13, "earth_1", false);
        setup(14, "earth_1", false);
        setup(15, "earth_1", false);
        setup(16, "earth_1", false);
        setup(17, "earth_1", false);
        setup(18, "earth_1", false);
        setup(19, "earth_1", false);
        setup(20, "earth_1", false);

        setup(21, "earth_1", false);
        setup(22, "earth_1", false);

        setup(23, "earth_1", false);
        setup(24, "earth_1", false);
        setup(25, "earth_1", false);

        setup(26, "grass00", false);
        setup(27, "grass01", false);

        setup(28, "grass01", false);
        setup(29, "grass01", false);
        setup(30, "grass01", false);
        setup(31, "grass01", false);

        setup(32, "hut", true);

        setup(33, "hut", true);
        setup(34, "hut", true);
        setup(35, "hut", true);
        setup(36, "hut", true);
        setup(37, "hut", true);
        setup(38, "hut", true);
        setup(39, "hut", true);
        setup(40, "hut", true);
        setup(41, "hut", true);
        setup(42, "hut", true);
        setup(43, "hut", true);
        setup(44, "hut", true);
        setup(45, "hut", true);
        setup(46, "hut", true);
        setup(47, "hut", true);
        setup(48, "hut", true);
        setup(49, "hut", true);

        setup(50, "road00", false);
        setup(51, "road01", false);
        setup(52, "road02", false);
        setup(53, "road03", false);
        setup(54, "road04", false);
        setup(55, "road05", false);
        setup(56, "road06", false);
        setup(57, "road07", false);
        setup(58, "road08", false);
        setup(59, "road09", false);
        setup(60, "road10", false);
        setup(61, "road11", false);
        setup(62, "road12", false);
        setup(63, "sand", false);

        setup(64, "sand", false);
        setup(65, "sand", false);
        setup(66, "sand", false);
        setup(67, "sand", false);
        setup(68, "sand", false);
        setup(69, "sand", false);

        setup(70, "tree_1", true);

        setup(71, "tree_1", true);

        setup(72, "wall_1", true);

        setup(73, "wall_1", true);

        setup(74, "water00", true);
        setup(75, "water01", true);
        setup(76, "water02", true);
        setup(77, "water03", true);
        setup(78, "water04", true);
        setup(79, "water05", true);
        setup(80, "water06", true);
        setup(81, "water07", true);
        setup(82, "water08", true);
        setup(83, "water09", true);
        setup(84, "water10", true);
        setup(85, "water11", true);
        setup(86, "water12", true);
        setup(87, "water13", true);

    }

    public void setup(int Index, String imageName, boolean Collision) {
        UtilityTools uTool = new UtilityTools();
        try {
            tile[Index] = new Tile();
            tile[Index].image = ImageIO.read(getClass().getResourceAsStream("/Image/" + imageName + ".png"));
            tile[Index].image = uTool.scaleImage(tile[Index].image, gp.tileSize, gp.tileSize);
            tile[Index].collision = Collision;
        } catch (IOException e) {
        }
    }

    public void loadMap(String filePath) {
        try {

            InputStream is = getClass().getResourceAsStream(filePath);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                int col = 0, row = 0;
                while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                    String line = br.readLine();
                    while (col < gp.maxWorldCol) {
                        String numbers[] = line.split(" ");
                        int num = Integer.parseInt(numbers[col]);
                        mapTileNum[col][row] = num;
                        col++;
                    }
                    if (col == gp.maxWorldCol) {
                        col = 0;
                        row++;
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
        }
    }

    public void draw(Graphics2D g2d) {

        int Worldcol = 0, Worldrow = 0;

        while (Worldcol < gp.maxWorldCol && Worldrow < gp.maxWorldRow) {
            int tileNum = mapTileNum[Worldcol][Worldrow];
            int WorldX = Worldcol * gp.tileSize;
            int WorldY = Worldrow * gp.tileSize;
            int ScreenX = WorldX - gp.player.Worldx + gp.player.screenX;
            int ScreenY = WorldY - gp.player.Worldy + gp.player.screenY;

            if (WorldX + gp.tileSize > gp.player.Worldx - gp.player.screenX
                    && WorldX - gp.tileSize < gp.player.Worldx + gp.player.screenX
                    && WorldY + gp.tileSize > gp.player.Worldy - gp.player.screenY
                    && WorldY - gp.tileSize < gp.player.Worldy + gp.player.screenY) {
                g2d.drawImage(tile[tileNum].image, ScreenX, ScreenY, null);
            }

            Worldcol++;
            if (Worldcol == gp.maxWorldCol) {
                Worldcol = 0;
                Worldrow++;

            }
        }
    }
}
