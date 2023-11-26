package entity;

import main.GamePanel;
import main.HomePanel;
import main.EndingPanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    HomePanel hp;
    KeyHandler keyH;
    EndingPanel ep;

    public final int screenX;
    public final int screenY;

    int counter2 = 0;

    public Player(HomePanel hp, KeyHandler keyH) {
        this.hp = hp;
        this.keyH = keyH;

        screenX = hp.screenWidth/2 - (hp.tileSize/2);
        screenY = hp.screenHeight/2 - (hp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues_h();
        getPlayerImage();
    }
    public Player(EndingPanel ep, KeyHandler keyH) {
        this.ep = ep;
        this.keyH = keyH;

        screenX = ep.screenWidth/2 - (ep.tileSize/2);
        screenY = ep.screenHeight/2 - (ep.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues_e();
        getPlayerImage();
    }

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 600;
        y = 100;
        speed = 4;
        direction = "down";
    }
    
    public void setDefaultValues_e() {
        x = 359;
        y = 672;
        speed = 1;
        direction = "up";
    }

    public void setDefaultValues_h() {
        x = 600;
        y = 200;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/imgs/player/up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/imgs/player/up_2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/imgs/player/up_3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/imgs/player/down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/imgs/player/down_2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/imgs/player/down_3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/imgs/player/left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/imgs/player/left_2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/imgs/player/left_3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/imgs/player/right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/imgs/player/right_2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/imgs/player/right_3.png"));

        }catch (IOException e) {
            e.printStackTrace();
        }
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

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (collisionOn == false) {
                switch (direction) {
                    case "up": y -= speed; break;
                    case "down": y += speed; break;
                    case "left": x -= speed; break;
                    case "right": x += speed; break;
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
    public void update_e() {

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
            ep.cChecker.checkTile_e(this);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (collisionOn == false) {
                switch (direction) {
                    case "up": y -= speed; break;
                    case "down": y += speed; break;
                    case "left": x -= speed; break;
                    case "right": x += speed; break;
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
    public void update_h() {

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
            hp.cChecker.checkTile_h(this);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (collisionOn == false) {
                switch (direction) {
                    case "up": y -= speed; break;
                    case "down": y += speed; break;
                    case "left": x -= speed; break;
                    case "right": x += speed; break;
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


    public void draw(Graphics2D g2) {
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

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


        int PlayerWidth = (int) (gp.tileSize * scale);
        int PlayerHeight = (int) (gp.tileSize * scale);
        g2.drawImage(image, x, y, PlayerWidth, PlayerHeight, null);
    }

    public void draw_h(Graphics2D g2) {
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        // 확대할 비율 (1.2배로 확대)
        double scale = 1.2;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                if (spriteNum == 3) {
                    image = up3;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                if (spriteNum == 3) {
                    image = down3;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                if (spriteNum == 3) {
                    image = left3;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                if (spriteNum == 3) {
                    image = right3;
                }
                break;
        }


        int PlayerWidth = (int) (hp.tileSize * scale);
        int PlayerHeight = (int) (hp.tileSize * scale);
        g2.drawImage(image, x, y, PlayerWidth, PlayerHeight, null);
    }
    
    public void draw_e(Graphics2D g2) {
//      g2.setColor(Color.white);
//      g2.fillRect(x, y, gp.tileSize, gp.tileSize);

      BufferedImage image = null;

      // 확대할 비율 (1.2배로 확대)
      double scale = 1.2;

      switch (direction) {
          case "up":
              if (spriteNum == 1) {
                  image = up1;
              }
              if (spriteNum == 2) {
                  image = up2;
              }
              if (spriteNum == 3) {
                  image = up3;
              }
              break;
          case "down":
              if (spriteNum == 1) {
                  image = down1;
              }
              if (spriteNum == 2) {
                  image = down2;
              }
              if (spriteNum == 3) {
                  image = down3;
              }
              break;
          case "left":
              if (spriteNum == 1) {
                  image = left1;
              }
              if (spriteNum == 2) {
                  image = left2;
              }
              if (spriteNum == 3) {
                  image = left3;
              }
              break;
          case "right":
              if (spriteNum == 1) {
                  image = right1;
              }
              if (spriteNum == 2) {
                  image = right2;
              }
              if (spriteNum == 3) {
                  image = right3;
              }
              break;
      }


      int PlayerWidth = (int) (ep.tileSize * scale);
      int PlayerHeight = (int) (ep.tileSize * scale);
      g2.drawImage(image, x, y, PlayerWidth, PlayerHeight, null);
  }
}
