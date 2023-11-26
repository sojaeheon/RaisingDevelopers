package tile;

//import main.HomePanel;
import main.GamePanel;
//import main.HomePanel;
import main.UtilityTool;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

public class TileManager {
    GamePanel gp;
//    HomePanel hp;
    public Tile[] tile;
    public int mapTileNum[][][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[16];
        mapTileNum = new int[gp.maxMap][gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        loadMap("/res/background/map_Home.txt", 0);
        loadMap("/res/background/map_Lab.txt", 1);
    }

//    public TileManager(HomePanel hp) {
//        this.hp = hp;
//        tile = new Tile[10];
//        mapTileNum = new int[hp.maxScreenCol][hp.maxScreenRow];
//        loadLabMap("/res/background/map_Home.txt");
//        getTileImage();
//    }

    public void getTileImage() {
        // Lab
        setup(0, "tile", false); // 바닥
        setup(1, "tile", true);
        setup(2, "tile", true);
        setup(3, "tile", true);
        setup(4, "tile", true);
        setup(5, "tile", true);
        setup(6, "tile", true);
        setup(7, "tile", true);
        setup(8, "tile", "prof"); // NPC

        // Home
        setup(9, "tile", true);
        setup(10, "tile", true);
        setup(11, "tile", true);
        setup(12, "tile", true);
        setup(13, "tile", true);
        setup(14, "tile", true);
        setup(15, "tile", false); // 바닥
    }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setup(int index, String imageName, String name) {
        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = true;
            tile[index].name = name;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath, int map) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();

                while (col < gp.maxScreenCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {

        }
    }

//    public void loadLabMap_h(String filePath) {
//        try {
//            InputStream is = getClass().getResourceAsStream(filePath);
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//
//            int col = 0;
//            int row = 0;
//
//            while (col < hp.maxScreenCol && row < hp.maxScreenRow) {
//                String line = br.readLine();
//
//                while (col < hp.maxScreenCol) {
//                    String numbers[] = line.split(" ");
//                    int num = Integer.parseInt(numbers[col]);
//                    mapTileNum[col][row] = num;
//                    col++;
//                }
//                if (col == hp.maxScreenCol) {
//                    col = 0;
//                    row++;
//                }
//            }
//            br.close();
//
//        } catch (Exception e) {
//
//        }
//    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[gp.currentMap][col][row];
            g2.drawImage(tile[tileNum].image, x, y, null);
            col++;
            x += gp.tileSize;

            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }

//    public void draw_h(Graphics2D g2) {
//        int col = 0;
//        int row = 0;
//        int x = 0;
//        int y = 0;
//
//        while (col < hp.maxScreenCol && row < hp.maxScreenRow) {
//            int tileNum = mapTileNum[col][row];
//            g2.drawImage(tile[tileNum].image, x, y, hp.tileSize, hp.tileSize, null);
//            col++;
//            x += hp.tileSize;
//
//            if (col == hp.maxScreenCol) {
//                col = 0;
//                x = 0;
//                row++;
//                y += hp.tileSize;
//            }
//        }
//    }
}
