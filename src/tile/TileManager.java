package tile;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

import SoTest.GamePanel;
import SoTest.HomePanel;

public class TileManager {
    GamePanel gp;
    HomePanel hp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(HomePanel hp) {
        this.hp = hp;
        tile = new Tile[10];
        mapTileNum = new int[hp.maxScreenCol][hp.maxScreenRow];

        getTileImage();
        loadLabMap_h("/res/background/map_Home.txt");
    }

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        loadLabMap("/res/background/map_Lab.txt");
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/background/tile.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/background/tile.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/background/tile.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/res/background/tile.png"));
            tile[3].collision = true;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/res/background/tile.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/background/tile.png"));
            tile[5].collision = true;

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/res/background/tile.png"));
            tile[6].collision = true;

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/res/background/tile.png"));
            tile[7].collision = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadLabMap(String filePath) {
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
                    mapTileNum[col][row] = num;
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

    public void loadLabMap_h(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < hp.maxScreenCol && row < hp.maxScreenRow) {
                String line = br.readLine();

                while (col < hp.maxScreenCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == hp.maxScreenCol) {
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
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
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

    public void draw_h(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < hp.maxScreenCol && row < hp.maxScreenRow) {
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image, x, y, hp.tileSize, hp.tileSize, null);
            col++;
            x += hp.tileSize;

            if (col == hp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += hp.tileSize;
            }
        }
    }
}
