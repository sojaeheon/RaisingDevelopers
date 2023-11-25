package SoTest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Intro_Test extends JPanel {
	private Main testScreen;
	
    private Image screenImage;
	private Graphics screenGraphic;
	
//	private Image introImage = new ImageIcon(this.getClass().getResource("/res/background/Intro.gif")).getImage();
//    private int introWidth = introImage.getWidth(null);
//    private int introHeight = introImage.getHeight(null);
//    
    public Intro_Test(Main testScreen) {
    	this.testScreen = testScreen;
    	initialize();
    }
    
    private void initialize() {

    	this.setLayout(null); // 레이아웃 설정 제거
        this.setPreferredSize(new Dimension(testScreen.screenWidth, testScreen.screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        
        
     // 버튼 생성
        JButton startButton = new JButton("시작");
        startButton.setBounds(10, 10, 100, 100);
     // 버튼을 GamePanel에 추가
        startButton.setFocusable(true);
        
        this.add(startButton);

        // 버튼 클릭 이벤트를 처리하는 액션 리스너 추가
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            	testScreen.change("gamepanel");
            }
        });
    	
    }

    
//    public void paint(Graphics g) {
//		screenImage = createImage(testScreen.screenWidth, testScreen.screenHeight);
//		screenGraphic = screenImage.getGraphics();
//		screenDraw(screenGraphic);
//		g.drawImage(screenImage, 0, 0, this.getWidth(), this.getHeight(), 0, 0, introWidth, introHeight, null);
//	}
//    
//    public void screenDraw(Graphics g) {
//		g.drawImage(introImage, 0, 0, null);
//		paintComponents(g);
//		this.repaint();
//	}

	


}
