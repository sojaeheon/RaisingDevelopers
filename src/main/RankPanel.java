package main;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import javax.swing.*;


public class RankPanel extends JFrame {
	// private Ranking rank;
	JTextField userInput;
	JButton insertBtn;
	JButton initialize;

	// SCREEN SETTING
	static final int originalTilesize = 16; // 16X16 title
	static final int scale = 3;

	public final static int tileSize = originalTilesize * scale; // 48*48 title
	public final static int maxScreenCol = 16;
	public final static int maxScreenRow = 16;
	public final static int screenWidth = tileSize * maxScreenCol; // 768 pixels
	public final static int screenHeight = tileSize * maxScreenRow; // 768 pixels

	// 랭킹 배경 그리기
	private Image screenImage;
	private Graphics screenGraphic;

	private Image introBackground = new ImageIcon(getClass().getResource("../res/background/ranking.png")).getImage();
	private ImageIcon insertBtn_basic = new ImageIcon(Main.class.getResource("../res/background/insertBtn1.png"));
	private ImageIcon insertBtn_enter = new ImageIcon(Main.class.getResource("../res/background/insertBtn2.png"));
	private ImageIcon initialize1 = new ImageIcon(Main.class.getResource("../res/background/initialize1.png"));
	private ImageIcon initialize2 = new ImageIcon(Main.class.getResource("../res/background/initialize2.png"));

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://203.234.62.226:3306/game";
	static final String USER = "RaisingDeveloper";
	static final String PASS = "1234";

	Connection conn = null;
	PreparedStatement stmt = null;

	static int stringx = 275;
	static int stringy = 280;

	
	static String[] id = new String[5];
	static String[] scoreStr = new String[5];
	static String[] score = new String[5];
	static int i = 0;

	GamePanel gp;
	String userName;
	

	public RankPanel(GamePanel gp) {
		this.gp = gp;

		setUndecorated(true);
		setTitle("DynamicBeat");
		setSize(screenWidth, screenHeight);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);

		userInput = new JTextField(20);
		userInput.setBounds(440, 237, 110, 25);

		add(userInput);
		
		
		
		userInput.requestFocus();

		insertBtn = new JButton(insertBtn_basic);
		insertBtn.setBounds(430, 263, 127, 33);
		insertBtn.setBorderPainted(false);
		insertBtn.setContentAreaFilled(false);
		insertBtn.setFocusPainted(false);
		insertBtn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				insertBtn.setIcon(insertBtn_enter);
				insertBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
//				Music buttonEnteredMusic = new Music("button01a.mp3",false);
//				buttonEnteredMusic.start();
			}
			public void mouseExited(MouseEvent e) {
				insertBtn.setIcon(insertBtn_basic);
				insertBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e) {
				userName = userInput.getText();
				
				insertRanking();
				
				Music buttonEnteredMusic = new Music("btn_press.mp3",false);
				buttonEnteredMusic.start();
			}
		});
		add(insertBtn);
		
		
		DBconnection();
		load_ranking();
	}

	public void DBconnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected to database successfully...");
			System.out.println("Fetching real-time rankings...");
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC 드라이버를 로드하는데에 문제 발생" + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void insertRanking() {

		try {
			
			

			userInput.setVisible(false);;
			insertBtn.setVisible(false);
			
			
			
			String sqlInsert = "INSERT INTO game.rank(user_id, user_score) VALUES (?, ?) ";

			PreparedStatement insertStmt = conn.prepareStatement(sqlInsert);
			insertStmt.setString(1, userName);
			insertStmt.setDouble(2, gp.player.score);
			insertStmt.executeUpdate();
			
			
			initialize = new JButton(initialize1);
			initialize.setBounds(430, 237, 130, 58);
			initialize.setBorderPainted(false);
			initialize.setContentAreaFilled(false);
			initialize.setFocusPainted(false);
			initialize.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					initialize.setIcon(initialize2);
					initialize.setCursor(new Cursor(Cursor.HAND_CURSOR));
//					Music buttonEnteredMusic = new Music("button01a.mp3",false);
//					buttonEnteredMusic.start();
				}
				public void mouseExited(MouseEvent e) {
					initialize.setIcon(initialize1);
					initialize.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				public void mousePressed(MouseEvent e) {
					gp.gameState = gp.titleState;
	                gp.restart();
	                dispose();
					Music buttonEnteredMusic = new Music("btn_press.mp3",false);
					buttonEnteredMusic.start();
				}
			});
			initialize.setVisible(true);

			add(initialize);

		} catch (SQLException se) {
			
			JOptionPane.showMessageDialog(this, "랭킹에 있는 이름입니다. 다시 입력해주세요");
			insertBtn.setVisible(true);
			userInput.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();

		}
		load_ranking();

	}

	public void load_ranking() {

		String sqlSelect = "SELECT * FROM game.rank ORDER BY user_score DESC LIMIT 5";

		try {
			stmt = conn.prepareStatement(sqlSelect);
			ResultSet rs = stmt.executeQuery(sqlSelect);

			while (rs.next()) {

				id[i] = rs.getString("user_id");
				score[i] = String.valueOf(rs.getDouble("user_score"));
				i++;
				
			}
			i = 0;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
		}
		System.out.println("Real-time rankings:");
		repaint();

	}

	public void paint(Graphics g) {
		screenImage = createImage(gp.screenWidth, gp.screenHeight);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, this.getWidth(), this.getHeight(), 0, 0, gp.screenWidth, gp.screenHeight,
				null);

		int id_xindex = 275;
		int id_yindex = 310;
		int score_index = 380;
		int score_xindex = 440;
		int score_yindex = 310;

		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(new Font("궁서체", Font.BOLD, 15));
		g2.setColor(Color.BLACK);
		g2.drawString("등수", 220, 260);
		g2.setColor(Color.RED);
		g2.drawString("수석", 220, 310);
		g2.setColor(Color.BLUE);
		g2.drawString("차석", 220, 340);
		g2.setColor(Color.BLACK);
		g2.drawString("3등", 222, 370);
		g2.drawString("4등", 222, 400);
		g2.drawString("5등", 222, 430);

		g2.setColor(Color.RED);
		g2.drawString("학점: ", score_index, score_yindex);
		g2.setColor(Color.BLUE);
		g2.drawString("학점: ", score_index, score_yindex + 30);
		g2.setColor(Color.BLACK);
		g2.drawString("학점: ", score_index, score_yindex + 60);
		g2.drawString("학점: ", score_index, score_yindex + 90);
		g2.drawString("학점: ", score_index, score_yindex + 120);

		try {
			for (int i = 0; i < 5; i++) {
				g2.drawString(id[i], id_xindex, id_yindex);
				g2.drawString(score[i], score_xindex, score_yindex);
				g2.setColor(Color.GRAY);
				id_yindex += 30;
				score_yindex += 30;

			}

		} catch (NullPointerException e) {
			g2.drawString("", id_xindex, id_yindex);
			g2.drawString("", score_xindex, score_yindex);

		}
		;
		this.paintComponents(g);
	}


}
