package SoTest;
import javax.swing.*;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class QuizStart extends JPanel {
	private QuizStart quizStart = this;
	
	private Image screenImage;
	private Graphics screenGraphic;
	
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/ExitButten1.jpg"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/ExitButten2.jpg"));

	
	private Image introBackground=  new ImageIcon(Main.class.getResource("../images/introImage.jpg")).getImage();
	private JLabel menubar = new JLabel(new ImageIcon(Main.class.getResource("../images/Menubar.jpg")));
	
	private JButton exitButton = new JButton(exitButtonBasicImage);
	
	private int mouseX,mouseY;
	
	public QuizStart() {
        this.setPreferredSize(new Dimension(Main.screenWidth, Main.screenHeight));
		this.setSize(Main.screenWidth, Main.screenHeight);
		setVisible(true);
		setBackground(new Color(0,0,0,0));
		setLayout(null);
		
		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//				Music buttonEnteredMusic = new Music("button01a.mp3",false);
//				buttonEnteredMusic.start();
			}
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e) {
//				Music buttonEnteredMusic = new Music("button01b.mp3",false);
//				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
				}catch(InterruptedException ex) {
					ex.printStackTrace();				
				}
				System.exit(0);
			}
		});
		
		add(exitButton);
		
		menubar.setBounds(0,0,1280,30);
		menubar.addMouseListener(new MouseAdapter(){
			public void MousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menubar.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x- mouseX,y-  mouseY);
			}
		});
		
		
		add(menubar);
		
		
		
		
//		Music introMusic = new Music("introMusic.mp3",true);
//		introMusic.start();
	}
	

	public void paint(Graphics g) {
		screenImage = createImage(Main.screenWidth,Main.screenHeight);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, null);
		paintComponents(g);
		this.repaint();
	}



}
