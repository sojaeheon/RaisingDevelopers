package main;

import javax.swing.*;
import java.awt.*;

public class IntroPanel extends JPanel implements Runnable {

    //SCREEN SETTING
    final int originalTilesize = 16; // 16X16 title
    final int scale = 3;

    public final int tileSize = originalTilesize * scale; // 48*48 title
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 16;
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    int FPS = 60;

    Thread gameThread;

    private Image introImage;
    private int introWidth;
    private int introHeight;
    private JButton btn1;

    public IntroPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        // intro = new Intro(this);

        introImage = new ImageIcon(getClass().getResource("/res/background/Intro.gif")).getImage();
        introWidth = introImage.getWidth(null);
        introHeight = introImage.getHeight(null);

//        try {
//            introImage = new ImageIcon(getClass().getResource("/res/background/Intro.gif")).getImage();
//            introWidth = introImage.getWidth(null);
//            introHeight = introImage.getHeight(null);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long drawCount = 0;
        long timer = 0;

        while(gameThread !=null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= -1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000) {
                System.out.println("FPS:"+ drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() { }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(introImage, 0, 0, getWidth(), getHeight(), 0, 0, introWidth, introHeight, null);

        btn1.paint(g2);

//        intro.draw(g2);

        g2.dispose();
    }
}
