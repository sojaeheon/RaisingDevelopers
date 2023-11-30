//package main;
//
//import background.Ending;
//import entity.Player;
//import main.CollisionChecker;
//import main.KeyHandler;
//import tile.TileManager;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class EndingPanel extends JPanel implements Runnable{
//    private Ending end;
//
//    //SCREEN SETTING
//    final int originalTilesize = 16; // 16X16 title
//    final int scale = 3;
//
//    public final int tileSize = originalTilesize * scale; // 48*48 title
//    public final int maxScreenCol = 16;
//    public final int maxScreenRow = 16;
//    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
//    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
//
//    //FPS
//    int FPS = 60;
//
//    TileManager tileM = new TileManager(this);
//    KeyHandler keyH = new KeyHandler();
//    Thread gameThread;
//    public CollisionChecker cChecker = new CollisionChecker(this);
//    Player player = new Player(this, keyH);
////    Object object = new Object(this);
//
//    // set player's defalut position
//    int playerX = 100;
//    int playerY = 100;
//    int playerSpeed = 4;
//
//    public EndingPanel() {
//        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
//        this.setBackground(Color.black);
//        this.setDoubleBuffered(true);
//        this.addKeyListener(keyH);
//        this.setFocusable(true);
//
//        end = new Ending(this);
//    }
//
//    public void startGameThread() {
//        gameThread = new Thread(this);
//        gameThread.start();
//    }
//
//
//    public void run() {
//        double drawInterval = 1000000000/FPS;
//        double delta = 0;
//        long lastTime = System.nanoTime();
//        long currentTime;
//        long drawCount = 0;
//        long timer = 0;
//
//        while(gameThread !=null) {
//
//            currentTime = System.nanoTime();
//
//            delta += (currentTime - lastTime) / drawInterval;
//            timer += (currentTime - lastTime);
//            lastTime = currentTime;
//
//            if(delta >= -1) {
//                update();
//                repaint();
//                delta--;
//                drawCount++;
//            }
//            if(timer >= 1000000000) {
//                System.out.println("FPS:"+ drawCount);
//                drawCount = 0;
//                timer = 0;
//            }
//        }
//    }
//    public void update() {
//        player.update_e();
//    }
//
//
//    public void paintComponent(Graphics g) {
//
//        super.paintComponent(g);
//
//        Graphics2D g2 = (Graphics2D)g;
//
//        end.draw(g2);
//        tileM.draw_e(g2);
//        player.draw_e(g2);
//
//        g2.dispose();
//    }
//
//
//}
