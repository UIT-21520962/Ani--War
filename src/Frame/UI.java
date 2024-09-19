/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Frame;

import Objects.Object_Heart;
import Objects.Object_Key;
import PlayerandAnimal.Entity;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thuong
 */
public class UI {

    Graphics2D g2;
    GamePanel gp;
    Font font1, font2;
    public int commandNum = 0;
    BufferedImage heart_full, heart_half, heart_blank;
    public boolean messageOn = false;
    public String message ="";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = " ";

    public UI(GamePanel gp) {
        this.gp = gp;
        Object_Key key = new Object_Key(gp);
        try {
            InputStream is = getClass().getResourceAsStream("/Image/MP16OSF.ttf");
            font1 = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/Image/MP16SC.ttf");
            font2 = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
        Entity heart = new Object_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }
    public void Draw(Graphics2D g2) {
                this.g2 = g2;
 
                //MESSAGE
                if(messageOn == true) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
            
                messageCounter++;
            
                if(messageCounter > 120) {
                messageCounter = 0;
                messageOn = false; 
                    }
                }    
                 if(gp.gameState == gp.titleState) {
                    DrawTileScreen();
                }
                if(gp.gameState == gp.pauseState) {                   
                    DrawPlayerlife();
                    DrawPauseScreen();
                }
                if(gp.gameState == gp.dialogueState) {
                    DrawPlayerlife();
                    DrawDialogueScreen();
                }
                if(gameFinished == true) {
            
                String text;
                int textLength;
                int x,y;
                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
                text = "You found the treasure!";
                textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();            
                x = gp.screenWidth/2 - textLength/2;
                y = gp.screenHeight/2 - (gp.tileSize*3);
                g2.drawString(text, x, y);
                
                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
                textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();            
                x = gp.screenWidth/2 - textLength/2;
                y = gp.screenHeight/2 - (gp.tileSize*4);
                g2.drawString(text, x, y);                                    
                gp.gameThread = null;           
                }
                else {
                if(gp.gameState ==  gp.playState) {   
                //MESSAGE
                DrawPlayerlife();
                if(messageOn == true) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
            
                messageCounter++;
            
                if(messageCounter > 120) {
                messageCounter = 0;
                messageOn = false; 
                    }
                } 
                }
        }
    }
    
    
    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void DrawTileScreen() {
        
        g2.setBackground(Color.yellow);
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // TILE NAME    
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 120F));
        String text = "Ani World";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 4;

        //SHADOW
        g2.setColor(Color.gray);
        g2.drawString(text, x, y);
        //MAIN COLOR
        g2.setColor(Color.white);
        g2.drawString(text, x + 5, y + 5);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
       
        
        text = "Log in";
        x = getXforCenteredText(text) * 10 / 11;
        y += gp.tileSize * 6;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }
       

        text = "Quit";
        x = getXforCenteredText(text) * 10 / 11;
        y += gp.tileSize * 2;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }

    }
    
    public void DrawPlayerlife() {
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        
        int i = 0;
        while(i < gp.player.maxLife/2) {
            g2.drawImage(heart_blank, x,y, null);
            i++;
            x += gp.tileSize;
        }
        //RESET
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;
        //DRAW CURRENT LIFE
        while(i < gp.player.Life) {
            g2.drawImage(heart_half, x,y, null);
            i++;
            if(i < gp.player.Life) {
                g2.drawImage(heart_full, x,y, null);
            }
            i++;
            x += gp.tileSize;
        }
        
        
        
    }
    public void DrawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 120F));
        String text = "PAUSED";
        int x;
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth / 2 - length / 2;
        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
    }

    public int getXforCenteredText(String text) {

        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }

    public void DrawDialogueScreen() {
       int x = gp.tileSize*2;
       int y = gp.tileSize/2;
       int width = gp.screenWidth - (gp.tileSize*4);
       int height = gp.tileSize*4;
       
       subWindow(x,y,width,height);
       
       g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
       x += gp.tileSize;
       y += gp.tileSize;
       
       for(String line : currentDialogue.split("\n")) {
          g2.drawString(currentDialogue, x, y);
          y += 40;
       }

    }
    
    public void subWindow(int x, int y, int width, int height) {
    Color c = new Color(0,0,255);
    g2.setColor(c);
    g2.fillRoundRect(x, y, width, height, 35, 35);
    
    c = new Color(255,255,255);
    g2.setColor(c);
    g2.setStroke(new BasicStroke(5));
    g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
}

}
