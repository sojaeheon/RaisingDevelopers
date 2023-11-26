package main;

import background.*;
import entity.Player;
import tile.TileManager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;

import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{
    private Lab lab;

    //SCREEN SETTING
    final int originalTilesize = 16; // 16X16 title
    final int scale = 3;

    public final int tileSize = originalTilesize * scale; // 48*48 title
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 16;
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    Player player = new Player(this, keyH);
//    Object object = new Object(this);

    // set player's defalut position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    //GAME STATE
//    public int gameState;
//    public final int playState = 1;
//    public final int pauseState = 2;
//    public final int dialogueState = 3;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        lab = new Lab(this);
        lab.add(new JButton("tlqkf"));
    }

//    public void setupGame() {
//        gameState = playState;
//    }
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
    public void update() {
        player.update();
    }


    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        lab.draw(g2);
        tileM.draw(g2);
        player.draw(g2);
//        object.draw(g2);

        g2.dispose();
    }


}
