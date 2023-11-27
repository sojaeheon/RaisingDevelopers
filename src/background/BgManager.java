package background;

import main.GamePanel;
import background.Bg;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class BgManager extends Bg {
    GamePanel gp;

    public BgManager(GamePanel gp) {
        this.gp = gp;

        getImage("Lab");
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
