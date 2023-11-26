package background;

//import main.GamePanel;

import main.IntroPanel;
import main.Main;

import java.awt.*;
import javax.swing.*;

public class Intro extends JFrame{
    private Image introImage;
    private int introWidth;
    private int introHeight;
    private IntroPanel ip;

//    public Intro() {
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setResizable(false);
//        setTitle("연구원 키우기");
//
//        IntroPanel introPanel = new IntroPanel();
//        add(introPanel);
//
//        pack();
//        setLocationRelativeTo(null);
//        setVisible(true);
//
//        introPanel.startGameThread();
//    }


    public Intro(IntroPanel ip) {
        this.ip = ip;
        introImage = new ImageIcon(ip.getClass().getResource("/res/background/Intro.gif")).getImage();
        introWidth = introImage.getWidth(null);
        introHeight = introImage.getHeight(null);
        
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(introImage, 0, 0, ip.getWidth(), ip.getHeight(), 0, 0, introWidth, introHeight, null);
    }
}
