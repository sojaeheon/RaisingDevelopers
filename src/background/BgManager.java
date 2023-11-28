package background;

import main.GamePanel;
import background.Bg;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

//public class BgManager extends Bg {
//    GamePanel gp;
//
//    public Bg[] bg;
//    public int mapBgNum[];
//
//    public BgManager(GamePanel gp) {
//        this.gp = gp;
//        bg = new Bg[2];
//        mapBgNum = new int[gp.maxMap];
//
//        getBgImage("Home", 0);
//        getBgImage("Lab", 1);
//    }
//    public void getBgImage(String imageName, int map) {
//        try {
//            bg[map].image = ImageIO.read(getClass().getResource("/res/background/"+imageName+".png"));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void draw(Graphics2D g2) {
//        x = 0;
//        y = 0;
//        int mapNum = mapBgNum[gp.currentMap];
//        g2.drawImage(bg[mapNum].image, x, y, gp.getWidth(), gp.getHeight(), null);
//    }
//}

public class BgManager extends Bg {
    GamePanel gp;

    public BgManager(GamePanel gp) {
        this.gp = gp;

        switch (gp.currentMap) {

            case 0: getImage("Home"); break;
            case 1 : getImage("Lab"); break;
            case 2 : getImage("End"); break;
        }

    }
    public void getImage(String imageName) {
        try {
            image = ImageIO.read(getClass().getResource("/res/background/"+imageName+".png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        x = 0;
        y = 0;
        g2.drawImage(image, x, y, gp.getWidth(), gp.getHeight(), null);
    }
}
