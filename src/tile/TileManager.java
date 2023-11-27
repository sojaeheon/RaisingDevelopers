package tile;

import main.GamePanel;
import main.UtilityTool;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[15];
        mapTileNum = new int[gp.maxMap][gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        loadMap("/res/background/map_Home.txt", 0);
        loadMap("/res/background/map_Lab.txt", 1);
    }

    public void getTileImage() {

        // Lab
        setup(0, "tile", false);    // 바닥
        setup(1, "tile", true);     // 문
        setup(2, "tile", true);     // 벽
        setup(3, "tile", true);     // 게시판
        setup(4, "tile", true);     // 책상
        setup(5, "tile", true);     // 의자
        setup(6, "tile", true);     // 책장
        setup(7, "tile", true);     // 정수기

        // Home
        setup(8, "tile", true);     // 문
        setup(9, "tile", true);     // 벽
        setup(10, "tile", true);    // TV
        setup(11, "tile", true);    // 서랍
        setup(12, "tile", true);    // 침대
        setup(13, "tile", true);   // 책장
        setup(14, "tile", false);    // 바닥
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
    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
//        int x = 0;
//        int y = 0;
//
//        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
//            int tileNum = mapTileNum[gp.currentMap][col][row];
//            g2.drawImage(tile[tileNum].image, x, y, null);
//            col++;
//            x += gp.tileSize;
//            if (col == gp.maxScreenCol) {
//                col = 0;
//                x = 0;
//                row++;
//                y += gp.tileSize;
//            }
//        }

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[gp.currentMap][col][row];

            int X = col * gp.tileSize;
            int Y = row * gp.tileSize;
            int screenX = X - gp.player.x + gp.player.screenX;
            int screenY = Y - gp.player.y + gp.player.screenY;

            if (X + gp.tileSize > gp.player.x - gp.player.screenX &&
                X - gp.tileSize < gp.player.y - gp.player.screenY &&
                Y + gp.tileSize > gp.player.y - gp.player.screenY &&
                Y - gp.tileSize < gp.player.y + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, X, Y, null);
            }

            col++;

            if (col == gp.maxScreenCol) {
                col = 0;
                row++;
            }
        }
    }
}
