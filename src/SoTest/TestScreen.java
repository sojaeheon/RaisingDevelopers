package SoTest;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import main.GamePanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestScreen extends JFrame {
	
	//SCREEN SETTING
    static final int originalTilesize = 16; // 16X16 title
    static final int scale = 3;

    public final static int tileSize = originalTilesize * scale; // 48*48 title
    public final static int maxScreenCol = 16;
    public final static int maxScreenRow = 16;
    public final static int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final static int screenHeight = tileSize * maxScreenRow; // 768 pixels
    
    public QuizGame quizpanel = null;
    public GamePanel gamepanel = null;
    
    public void change(String panelName) {
    	if(panelName.equals("quizpanel")) {
    		getContentPane().removeAll();
    		getContentPane().add(quizpanel);
    		revalidate();
    		repaint();
    	}else if(panelName.equals("gamepanel")) {
    		getContentPane().removeAll();
    		getContentPane().add(gamepanel);
    		gamepanel.startGameThread();
    		revalidate();
    		repaint();
    	}
    }

	private JLabel contentPane, lblNewLabel;
	private JTextField tfUsername, tfPassword;
	private JButton btnJoin, btnLogin;
	
	public TestScreen() {
		renderUI();
		
	}
	
	public void renderUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);

		setSize(screenWidth, screenHeight);
		setLocationRelativeTo(null);

		
		this.quizpanel = new QuizGame(this, 2);
		this.gamepanel = new GamePanel(this);
		
		add(this.quizpanel);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);

		setSize(screenWidth, screenHeight);
		setLocationRelativeTo(null);


		setVisible(true);

	}
	
	public static void main(String[] args) {
		TestScreen testScreen = new TestScreen();
		
	}

}
