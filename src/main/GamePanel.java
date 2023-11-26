package main;

import background.*;
import entity.NPC_Prof;
import entity.Player;
import tile.TileManager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;

import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTING
    public final int originalTilesize = 16; // 16X16 tile
    final int scale = 3;

    public final int tileSize = originalTilesize * scale; // 48*48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 16;
    public final int maxMap = 2;
    public int currentMap = 0;
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //FPS
    int FPS = 60;

    BgManager bgM = new BgManager(this);
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    public Player player = new Player(this, keyH);
    public NPC_Prof npc = new NPC_Prof(this, keyH);

    // set player's defalut position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        gameState = titleState;
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
//                System.out.println("FPS:"+ drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update() {
        if(gameState == playState) {
            switch (currentMap) {
                case 0: tileM.loadMap("/res/background/map_Home.txt", 0);
                    bgM.getImage("Home");
                    break;
                case 1: tileM.loadMap("/res/background/map_Lab.txt", 1);
                    bgM.getImage("Lab"); npc.update(); npc.speak();
                    break;
            }
            // PLAYER
            player.update();
            // NPC
//            if (currentMap == 0) {
////                npc.update();
//            }
//            else if (currentMap == 1) {
//                npc.update();
//                npc.speak();
//            }
        }
        if(gameState == pauseState) {
            //nothing
        }
    }


    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        //DEBUG
        long drawStart = 0;
        if (keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }

        // TITLE SCREEN
        if (gameState == titleState) {
            ui.draw(g2);
        }
        else {
                this.revalidate();
                this.repaint();
                bgM.draw(g2);
                tileM.draw(g2);
                player.draw(g2);
            if (currentMap == 1) {
                this.revalidate();
                this.repaint();
                bgM.draw(g2);
                tileM.draw(g2);
                player.draw(g2);
                ui.draw(g2);
                npc.draw(g2);
            }
        }

        //DEBUG
        if (keyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time : " + passed, 10, 400);
        }

        g2.dispose();
    }


}
