package background;

import javax.imageio.ImageIO;
import javax.swing.*;

import SoTest.HomePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Home extends JFrame {
    private BufferedImage homeImage;
    private int homeWidth;
    private int homeHeight;
    private HomePanel hp;

    public Home(HomePanel hp) {
        this.hp = hp;
        try {
            homeImage = ImageIO.read(hp.getClass().getResource("/res/background/Home.png"));
            homeWidth = homeImage.getWidth();
            homeHeight = homeImage.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(homeImage, 0, 0, hp.getWidth(), hp.getHeight(), 0, 0, homeWidth, homeHeight, null);
    }
}