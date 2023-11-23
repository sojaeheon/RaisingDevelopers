package background;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import main.GamePanel;

public class Lab extends JPanel {
    private BufferedImage labImage;
    private int labWidth;  // Lab 이미지의 너비
    private int labHeight; // Lab 이미지의 높이
    private GamePanel gp; // GamePanel 인스턴스 변수 추가

    public Lab(GamePanel gp) {
        this.gp = gp; // GamePanel 변수 초기화
        try {
            labImage = ImageIO.read(gp.getClass().getResource("/imgs/background/Lab.png"));
            labWidth = labImage.getWidth();
            labHeight = labImage.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        // Lab 이미지를 확대 또는 축소하여 GamePanel 크기에 맞게 그립니다.
        g2.drawImage(labImage, 0, 0, gp.getWidth(), gp.getHeight(), 0, 0, labWidth, labHeight, null);
    }
}