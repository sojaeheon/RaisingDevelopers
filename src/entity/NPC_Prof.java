package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class NPC_Prof extends Entity {
    int spriteCounter = gp.player.spriteCounter;
    int spriteNum = gp.player.spriteNum;
    KeyHandler keyH;
    public NPC_Prof(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;

        setDefaultValues();
        getNPCImage();
        setDialogue();
    }

    public void setDefaultValues() {
        x = 58;
        y = 240;
        direction = "down";
    }

    public void getNPCImage() {
        npc0 = setup("NPC0");
        npc1 = setup("NPC1");
        npc2 = setup("NPC2");
    }

    public void setDialogue() {
        dialogues[0] = "아니, 이게 누구야. \n우리 연구원 뚱땅이 아닌가..!";
        dialogues[1] = "그래, 오늘도 출근을 했구나...\n";
        dialogues[2] = "어서 네 자리로 가서 \n문제를 풀어보거라.";
        dialogues[3] = "한 챕터를 다 풀어야만 \n집에 갈 수 있단다.";
        dialogues[4] = "원래 퇴근이란 \n자기 생각대로 되지 않는 법이란다... ";
        dialogues[5] = "오늘도 파이팅이다! \n그럼 이만..";
    }

    public BufferedImage setup(String imageName) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/NPC/" + imageName + ".png"));
            image = uTool.scaleImage(image, gp.tileSize+30, gp.tileSize+30);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void setAction() {
        if(keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true) {

            spriteCounter++;
            if(spriteCounter > 48) {
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

    public void speak() {
        super.speak();
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        if(spriteNum == 1) {
            image = npc0;
        }
        if(spriteNum == 2) {
            image = npc1;
        }
        if(spriteNum == 3) {
            image = npc2;
        }

        g2.drawImage(image, x, y, null);
    }
}
