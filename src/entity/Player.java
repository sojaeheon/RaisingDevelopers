package entity;

import main.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import main.GamePanel;

public class Player extends Entity {
//    HomePanel hp;
    KeyHandler keyH;

    public int screenX;
    public int screenY;


    int counter2 = 0;

//    public Player(HomePanel hp, KeyHandler keyH) {
//        this.hp = hp;
//        this.keyH = keyH;
//
//        screenX = hp.screenWidth/2 - (hp.tileSize/2);
//        screenY = hp.screenHeight/2 - (hp.tileSize/2);
//
//        solidArea = new Rectangle();
//        solidArea.x = 8;
//        solidArea.y = 16;
//        solidArea.width = 32;
//        solidArea.height = 32;
//
//        setDefaultValues_h();
//        getPlayerImage();
//    }

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
        x = 600;
        y = 200;
        speed = 4;
        direction = "down";
    }

//    public void setDefaultValues_h() {
//        x = 600;
//        y = 200;
//        speed = 4;
//        direction = "down";
//    }

    public void getPlayerImage() {
        up1 = setup("up_1");
        up2 = setup("up_2");
        up3 = setup("up_3");
        down1 = setup("down_1");
        down2 = setup("down_2");
        down3 = setup("down_3");
        left1 = setup("left_1");
        left2 = setup("left_2");
        left3 = setup("left_3");
        right1 = setup("right_1");
        right2 = setup("right_2");
        right3 = setup("right_3");
    }

    public BufferedImage setup(String imageName) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/player/" + imageName + ".png"));
            image = uTool.scaleImage(image, gp.tileSize+5, gp.tileSize+5);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void update() {

        if(keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true) {
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

            // CHECK NPC COLLISION
            gp.cChecker.checkEntity(this);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (collisionOn == false) {
                switch (direction) {
                    case "up": y -= speed; break;
                    case "down": y += speed; break;
                    case "left": x -= speed; break;
                    case "right": x += speed; break;
                }
            }
            else if (collisionNPC == true) {
                switch (direction) {
                    case "left": interactNPC(); break;
                }
            }

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

    public void interactNPC() {
        if (gp.keyH.enterPressed == true) {
            gp.gameState = gp.dialogueState;
//            gp.npc.speak();
        }
        gp.keyH.enterPressed = false;
    }

//    public void update_h() {
//
//        if(keyH.upPressed == true || keyH.downPressed == true ||
//                keyH.leftPressed == true || keyH.rightPressed == true) {
//            if(keyH.upPressed == true) {
//                direction = "up";
//            }
//            else if(keyH.downPressed == true) {
//                direction = "down";
//            }
//            else if(keyH.leftPressed == true) {
//                direction = "left";
//            }
//            else if(keyH.rightPressed == true) {
//                direction = "right";
//            }
//
//            // CHECK TILE COLLISION
//            collisionOn = false;
//            hp.cChecker.checkTile_h(this);
//
//            // IF COLLISION IS FALSE, PLAYER CAN MOVE
//            if (collisionOn == false) {
//                switch (direction) {
//                    case "up": y -= speed; break;
//                    case "down": y += speed; break;
//                    case "left": x -= speed; break;
//                    case "right": x += speed; break;
//                }
//            }
//
//            spriteCounter++;
//            if(spriteCounter > 12) {
//                if(spriteNum == 1) {
//                    spriteNum = 2;
//                }
//                else if (spriteNum == 2) {
//                    spriteNum = 3;
//                }
//                else if (spriteNum == 3) {
//                    spriteNum = 1;
//                }
//                spriteCounter = 0;
//            }
//        }
//    }


    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        // 확대할 비율 (1.2배로 확대)
        double scale = 1.2;

        switch (direction) {
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2) {
                    image = up2;
                }
                if(spriteNum == 3) {
                    image = up3;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                if(spriteNum == 3) {
                    image = down3;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                if(spriteNum == 3) {
                    image = left3;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                if(spriteNum == 3) {
                    image = right3;
                }
                break;
        }

        g2.drawImage(image, x, y, null);
    }

//    public void draw_h(Graphics2D g2) {
////        g2.setColor(Color.white);
////        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
//
//        BufferedImage image = null;
//
//        // 확대할 비율 (1.2배로 확대)
//        double scale = 1.2;
//
//        switch (direction) {
//            case "up":
//                if (spriteNum == 1) {
//                    image = up1;
//                }
//                if (spriteNum == 2) {
//                    image = up2;
//                }
//                if (spriteNum == 3) {
//                    image = up3;
//                }
//                break;
//            case "down":
//                if (spriteNum == 1) {
//                    image = down1;
//                }
//                if (spriteNum == 2) {
//                    image = down2;
//                }
//                if (spriteNum == 3) {
//                    image = down3;
//                }
//                break;
//            case "left":
//                if (spriteNum == 1) {
//                    image = left1;
//                }
//                if (spriteNum == 2) {
//                    image = left2;
//                }
//                if (spriteNum == 3) {
//                    image = left3;
//                }
//                break;
//            case "right":
//                if (spriteNum == 1) {
//                    image = right1;
//                }
//                if (spriteNum == 2) {
//                    image = right2;
//                }
//                if (spriteNum == 3) {
//                    image = right3;
//                }
//                break;
//        }
//
//
//        int PlayerWidth = (int) (hp.tileSize * scale);
//        int PlayerHeight = (int) (hp.tileSize * scale);
//        g2.drawImage(image, x, y, PlayerWidth, PlayerHeight, null);
//    }
}
