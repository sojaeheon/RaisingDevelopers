package entity;

import main.*;

import java.awt.*;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Player extends Entity {
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    int standCounter = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = gp.tileSize*12;
        y = gp.tileSize*13;
        speed = 4;
        direction = "down";

        // PLAYER STATUS
        this.level = 0; // 레벨
        this.score = 0; // 학점
        this.currentCh = 1; maxCh = 4; // 현재 챕터, 전체 챕터
    }

    public void setDefaultPositions() {
        x = gp.tileSize*12;
        y = gp.tileSize*13;
        speed = 4;
        direction = "down";
    }

    public void restoreStatus() {
        this.level = 0;
        this.score = 0;
        this.currentCh = 1;
    }

    public void getPlayerImage() {
        up1 = setup("/res/player/up_1", gp.tileSize+5, gp.tileSize+5);
        up2 = setup("/res/player/up_2", gp.tileSize+5, gp.tileSize+5);
        up3 = setup("/res/player/up_3", gp.tileSize+5, gp.tileSize+5);
        down1 = setup("/res/player/down_1", gp.tileSize+5, gp.tileSize+5);
        down2 = setup("/res/player/down_2", gp.tileSize+5, gp.tileSize+5);
        down3 = setup("/res/player/down_3", gp.tileSize+5, gp.tileSize+5);
        left1 = setup("/res/player/left_1", gp.tileSize+5, gp.tileSize+5);
        left2 = setup("/res/player/left_2", gp.tileSize+5, gp.tileSize+5);
        left3 = setup("/res/player/left_3", gp.tileSize+5, gp.tileSize+5);
        right1 = setup("/res/player/right_1", gp.tileSize+5, gp.tileSize+5);
        right2 = setup("/res/player/right_2", gp.tileSize+5, gp.tileSize+5);
        right3 = setup("/res/player/right_3", gp.tileSize+5, gp.tileSize+5);
    }
    public void update() {

        if(keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {
            if(keyH.upPressed == true) {
                direction = "up";
            }
            else if(keyH.downPressed == true) {
                direction = "down";
            }
            else if(keyH.leftPressed == true) {
                direction = "left";
            }
            else if(keyH.rightPressed == true) {
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK NPC COLLESION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // CHECK EVENT
            gp.eHandler.checkEvent();

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (collisionOn == false && keyH.enterPressed == false) {
                switch (direction) {
                    case "up": y -= speed; break;
                    case "down": y += speed; break;
                    case "left": x -= speed; break;
                    case "right": x += speed; break;
                }
            }

            if (keyH.enterPressed == true) {
                spriteCounter = 0;
            }
            gp.keyH.enterPressed = false;

            spriteCounter++;
            if(spriteCounter > 12) {
                if(spriteNum == 1) {
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 3;
                }
                else if (spriteNum == 3) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void interactNPC(int i) {
        if (gp.keyH.enterPressed == true) {
            if (i != 999) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
        gp.keyH.enterPressed = false;
    }


    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {image = up1;}
                if (spriteNum == 2) {image = up2;}
                if (spriteNum == 3) {image = up3;}
                break;
            case "down":
                if (spriteNum == 1) {image = down1;}
                if (spriteNum == 2) {image = down2;}
                if (spriteNum == 3) {image = down3;}
                break;
            case "left":
                if (spriteNum == 1) {image = left1;}
                if (spriteNum == 2) {image = left2;}
                if (spriteNum == 3) {image = left3;}
                break;
            case "right":
                if (spriteNum == 1) {image = right1;}
                if (spriteNum == 2) {image = right2;}
                if (spriteNum == 3) {image = right3;}
                break;
        }

        g2.drawImage(image, x, y, null);

//        g2.drawImage(image, screenX, screenY, null);
    }
}
