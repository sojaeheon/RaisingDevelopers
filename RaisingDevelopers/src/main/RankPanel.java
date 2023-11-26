package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RankPanel extends JPanel{
	JTextField userInput;
    final int originalTilesize = 16;
    final int scale = 3;
    BgManager bgm = new BgManager(this);
    public final int tileSize = originalTilesize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 16;
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://203.234.62.226:3306/game";
    static final String USER = "RaisingDeveloper";
    static final String PASS = "1234";
    
    static int stringx = 275;
    static int stringy = 280;
    
    
    static String[] id = new String[6];
    static String[] scoreStr = new String[6];
    static double[] score = new double[6];
    static int i = 0;
    

    KeyHandler keyH = new KeyHandler();
    String userName;
    
    public RankPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setLayout(null);
        userInput = new JTextField(20);
        userInput.setBounds(500, 230, 100, 30);
        this.add(userInput);
        userInput.addKeyListener(new KeyAdapter() {
        	@Override
            public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
        			System.out.println("됨");
        			userName = userInput.getText();
        			repaint();
                }
            }
        });
    }
    public void rankString()
    {
    	String sqlInsert = "INSERT INTO game.rank(user_id, user_score) VALUES (?, ?)";
    	String sqlSelect = "SELECT * FROM game.rank ORDER BY user_score DESC";
    	Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected to database successfully...");
            System.out.println("Fetching real-time rankings...");
            stmt = conn.createStatement();
            PreparedStatement insertStmt = conn.prepareStatement(sqlInsert);
            insertStmt.setString(1,  userName);
            insertStmt.setDouble(2, 5.0);
            insertStmt.executeUpdate();
            ResultSet rs = stmt.executeQuery(sqlSelect);
            System.out.println("Real-time rankings:");
            while (rs.next()) {
            	id[i] = rs.getString("user_id");
            	score[i] = rs.getDouble("user_score");
                i++;
                if(i==6) {
                	break;
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        bgm.draw(g2);
        rankString();
        g2.setFont(new Font("궁서체", Font.BOLD, 17));
        g2.setColor(Color.BLACK);
        g2.drawString("등수", 220, 260);
        g2.setColor(Color.RED);
        g2.drawString("수석", 220, 310);
        g2.setColor(Color.BLACK);
        g2.drawString("차석", 220, 340);
        g2.setColor(Color.GRAY);
        g2.drawString("3등", 222, 370);
        g2.drawString("4등", 222, 400);
        g2.drawString("5등", 222, 430);
        g2.setColor(Color.RED);
        g2.drawString(id[0], 275, 310 ); 
        g2.drawString("학점: ", 380,310); 
        g2.drawString(String.valueOf(score[0]), 440 ,310);
        g2.setColor(Color.BLUE);
        g2.drawString(id[1], 275, 340 ); 
        g2.drawString("학점: ", 380,340); 
        g2.drawString(String.valueOf(score[1]), 440 ,340);
        g2.setColor(Color.GRAY);
        g2.drawString(id[2], 275, 370 ); 
        g2.drawString("학점: ", 380,370); 
        g2.drawString(String.valueOf(score[2]), 440 ,370);
        g2.drawString(id[3], 275, 400 ); 
        g2.drawString("학점: ", 380,400);
        g2.drawString(String.valueOf(score[3]), 440 ,400);
        g2.drawString(id[4], 275, 430); 
        g2.drawString("학점: ", 380,430); 
        g2.drawString(String.valueOf(score[4]), 440 ,430);
        g2.dispose();
        this.repaint();
    }


}

