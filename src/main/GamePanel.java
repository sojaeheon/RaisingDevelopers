package main;

import background.*;
import entity.Entity;
import entity.Player;
import tile.TileManager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTING
    public final int originalTilesize = 16; // 16X16 tile
    final int scale = 3;

    public final int tileSize = originalTilesize * scale; // 48*48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 16;
    public final int maxMap = 3;
    public int currentMap = 0;
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //FPS
    int FPS = 60;

    // SYSTEM
    BgManager bgM = new BgManager(this);
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;




    // ENTITY
    public Player player = new Player(this, keyH);
    public Entity npc[][] = new Entity[maxMap][10];
    ArrayList<Entity> entityList = new ArrayList<>();

    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionsState = 5;
    public final int endingState = 6;
    public final int gameOverState = 7;
    public final int transitionState = 8;
    public final int load_quiz_state = 9;
    public final int rankingState = 10;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {

        aSetter.setNPC();
        gameState = titleState;
//        gameState = gameOverState;
    }

    public void retry() {
        player.setDefaultPositions();
        player.restoreStatus();
        aSetter.setNPC();
    }

    public void restart() {
        player.setDefaultValues();
        player.setDefaultPositions();
        player.restoreStatus();
        aSetter.setNPC();
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
            // PLAYER
            player.update();
            // NPC
            for (int i=0; i< npc[1].length; i++) {
                if(npc[currentMap][i] != null) {
                    npc[currentMap][i].update();
                }
            }
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
            // UI
            ui.draw(g2);
        }
        // OTHERS
        else {
            // BACKGROUND
            bgM.draw(g2);

            // TILE
            tileM.draw(g2);

            // ADD ENTITIES TO THE LIST
            entityList.add(player);

            for(int i=0; i< npc[1].length; i++) {
                if(npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }

            // SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.y, e2.y);
                    return result;
                }
            });

            // DRAW ENTITES
            for(int i=0; i<entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }

            // EMPTY ENTITY LIST
            entityList.clear();

            // UI
            ui.draw(g2);

            // PLAYER
//            player.draw(g2);
//            ui.draw(g2);
//            npc.draw(g2);
        }

        // DEBUG
        if (keyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time : " + passed, 10, 400);
        }

        g2.dispose();
    }
//    public void playMusic(int i) {
//        music.setFile(i);
//        music.play();
//        music.loop();
//    }
//    public void stopMusic() {
//        music.stop();
//    }
//    public void playSE(int i) {
//        se.setFile(i);
//        se.play();
//    }
}
