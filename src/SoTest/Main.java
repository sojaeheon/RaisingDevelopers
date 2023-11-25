package SoTest;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import dto.Submit_dto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

	// SCREEN SETTING
	static final int originalTilesize = 16; // 16X16 title
	static final int scale = 3;
	
	private Main testScreen;

	public final static int tileSize = originalTilesize * scale; // 48*48 title
	public final static int maxScreenCol = 16;
	public final static int maxScreenRow = 16;
	public final static int screenWidth = tileSize * maxScreenCol; // 768 pixels
	public final static int screenHeight = tileSize * maxScreenRow; // 768 pixels

	public static QuizGame quizpanel = null;
	public static GamePanel gamepanel = null;
	public static Intro_Test intropanel = null;
	public static HomePanel homepanel = null;
	
	Submit_dto sub;

	public void change(String panelName) {
		if (panelName.equals("quizpanel")) {
			this.quizpanel = new QuizGame(this, sub.chap);
			getContentPane().removeAll();
			getContentPane().add(quizpanel);
			revalidate();
			repaint();
			
		} else if (panelName.equals("gamepanel")) {
			this.gamepanel = new GamePanel(this);
			this.getContentPane().removeAll();
			this.getContentPane().add(gamepanel);
			gamepanel.startGameThread();
			revalidate();
			repaint();
			gamepanel.requestFocus();
		} else if (panelName.equals("intropanel")) {
			getContentPane().removeAll();
			getContentPane().add(intropanel);
			revalidate();
			repaint();
		} else if(panelName.equals("homepanel")) {
			this.homepanel = new HomePanel(this);
			this.getContentPane().removeAll();
			this.getContentPane().add(homepanel);
			homepanel.startGameThread();
			revalidate();
			repaint();
		}
	}

	public Main() {
		renderUI();

	}

	public void renderUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);

		setSize(screenWidth, screenHeight);
		setLocationRelativeTo(null);

		
//		this.quizpanel = new QuizGame(this, sub.chap);
		this.intropanel = new Intro_Test(this);
		

		add(this.intropanel);
		
//		gamepanel.startGameThread();


		setVisible(true);

	}

	public static void main(String[] args) {
		Main testScreen = new Main();

	}

}
