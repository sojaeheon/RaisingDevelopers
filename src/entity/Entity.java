package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {
    GamePanel gp;


    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public BufferedImage npc0, npc1, npc2;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    String dialogues[] = new String[20];

    // STATE
    public int x, y;
    public String direction = "down";
    public int spriteNum = 1;
    int dialogueIndex = 0;
    public boolean collisionOn = false;


    // COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;

    // CHARACTER ATTIBUTES
    public int type; // 0 = Player, 1 = npc
    public String name;
    public int speed;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {

    }
    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;


    }
    public void update() {

        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkPlayer(this);
//        boolean contactPlayer = gp.cChecker.checkPlayer(this);

    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
//        int screenX = x - gp.player.x + gp.player.screenX;
//        int screenY = y - gp.player.y + gp.player.screenY;

        if (x + gp.tileSize > gp.player.x - gp.player.screenX &&
            x - gp.tileSize < gp.player.x + gp.player.screenX &&
            y + gp.tileSize > gp.player.y - gp.player.screenY &&
            y - gp.tileSize < gp.player.y + gp.player.screenY) {

            switch (direction) {
                case "up":
                    if(gp.player.spriteNum == 1) {image = up1;}
                    if(gp.player.spriteNum == 2) {image = up2;}
                    if(gp.player.spriteNum == 3) {image = up3;}
                    break;
                case "down":
                    if(gp.player.spriteNum == 1) {image = down1;}
                    if(gp.player.spriteNum == 2) {image = down2;}
                    if(gp.player.spriteNum == 3) {image = down3;}
                    break;
                case "left":
                    if(gp.player.spriteNum == 1) {image = left1;}
                    if(gp.player.spriteNum == 2) {image = left2;}
                    if(gp.player.spriteNum == 3) {image = left3;}
                    break;
                case "right":
                    if(gp.player.spriteNum == 1) {image = right1;}
                    if(gp.player.spriteNum == 2) {image = right2;}
                    if(gp.player.spriteNum == 3) {image = right3;}
                    break;
            }

            g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        }

    }

    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
