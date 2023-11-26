package main;


import javax.imageio.ImageIO;
import main.RankPanel;
import java.awt.*;
import java.io.IOException;

public class BgManager extends Bg {
    RankPanel rp;
 
//    HomePanel hp;

    public BgManager(RankPanel rp) {
        this.rp = rp;

        getImage("Ranking");
    }
  
    public void getImage(String mapName) {
        try {
            image = ImageIO.read(getClass().getResource("/imgs/background/" + mapName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int x = 0;
        int y = 0;
        g2.drawImage(image, x, y, rp.getWidth(), rp.getHeight(), null);
    }
}