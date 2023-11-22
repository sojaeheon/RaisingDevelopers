package SoTest;
import javax.swing.JFrame;

public class Main extends JFrame {
	//SCREEN SETTING
    static final int originalTilesize = 16; // 16X16 title
    static final int scale = 3;

    public final static int tileSize = originalTilesize * scale; // 48*48 title
    public final static int maxScreenCol = 16;
    public final static int maxScreenRow = 16;
    public final static int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final static int screenHeight = tileSize * maxScreenRow; // 576 pixels
    
	public static void main(String[] args) {
		new TestScreen();
		
	}

}
