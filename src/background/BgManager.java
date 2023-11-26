package background;

import main.GamePanel;
import background.Bg;
//import main.HomePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class BgManager extends Bg {
    GamePanel gp;
//    HomePanel hp;

    public BgManager(GamePanel gp) {
        this.gp = gp;

        getImage("Home", 0);
        getImage("Lab", 1);
    }

//    public BgManager(HomePanel hp) {
//        this.hp = hp;
//
//        getLabImage();
//    }

    public void getImage(String mapName, int map) {
        try {
            image = ImageIO.read(getClass().getResource("/res/background/" + mapName + ".png"));
//            bg.imageWidth = bg.image.getWidth();
//            bg.imageHeight = bg.image.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int x = 0;
        int y = 0;
        g2.drawImage(image, x, y, gp.getWidth(), gp.getHeight(), null);
    }
}
