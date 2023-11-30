package background;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import main.EndingPanel;

public class Ending extends JPanel {
    private BufferedImage endImage;
    private int endWidth;  // end 이미지의 너비
    private int endHeight; // end 이미지의 높이
    private EndingPanel ep; // GamePanel 인스턴스 변수 추가

    public Ending(EndingPanel ep) {
        this.ep = ep; // GamePanel 변수 초기화
        try {
        	endImage = ImageIO.read(ep.getClass().getResource("/imgs/background/end.png"));
        	endWidth = endImage.getWidth();
        	endHeight = endImage.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        // end 이미지를 확대 또는 축소하여 GamePanel 크기에 맞게 그립니다.
        g2.drawImage(endImage, 0, 0, ep.getWidth(), ep.getHeight(), 0, 0, endWidth, endHeight, null);
    }
}