package SoTest;

import background.Intro;
import dto.Submit_dto;

import javax.swing.*;

import SoTest.GamePanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntroPanel extends JPanel implements Runnable{
	private Main testScreen;
	
    private Image screenImage;
	private Graphics screenGraphic;
	
	private Image introImage = new ImageIcon(this.getClass().getResource("/res/background/Intro.gif")).getImage();
    private int introWidth = introImage.getWidth(null);
    private int introHeight = introImage.getHeight(null);
	
    


    public IntroPanel(Main testScreen) {
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
        
        this.add(startButton);

        // 버튼 클릭 이벤트를 처리하는 액션 리스너 추가
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            	checkAnswer();
            }
        });
    	
    }

    
    public void paint(Graphics g) {
		screenImage = createImage(testScreen.screenWidth, testScreen.screenHeight);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, this.getWidth(), this.getHeight(), 0, 0, introWidth, introHeight, null);
	}
    
    public void screenDraw(Graphics g) {
		g.drawImage(introImage, 0, 0, null);
		paintComponents(g);
		this.repaint();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	private void checkAnswer() {
        testScreen.change("gamepanel");
        
        
        
	}


}
