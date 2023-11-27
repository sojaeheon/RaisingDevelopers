package main;

import javax.swing.JFrame;


import dto.Submit_dto;

public class Main extends JFrame {

	public static QuizGame quizpanel = null;

	
	Submit_dto sub;
	GamePanel gamepanel = new GamePanel(this);
	
	public Main() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("연구원 키우기 게임");
		change("gamepanel");
		gamepanel.setupGame();
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		

		

	}

	public static void main(String[] args) {
		Main main = new Main();
		
	} 

	public void change(String panelName) {
		
		

		// 화면전환코드부분!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		if (panelName.equals("quizpanel")) {
			
			gamepanel.stopGameThread();
			this.quizpanel = new QuizGame(this, sub.chap);
			getContentPane().removeAll();
			getContentPane().add(quizpanel);
			this.revalidate();
			this.repaint();

		} else if (panelName.equals("gamepanel")) {
			System.out.println(this+"main이 오류냐");
			
			getContentPane().removeAll();
			getContentPane().add(gamepanel);
			gamepanel.startGameThread();
			gamepanel.requestFocus();
			revalidate();
			repaint();
			
			
		}
	}

}

/*
 * public class Main extends JFrame { private Image background = new
 * ImageIcon(Main.class.getResource("/image/Lab.png")).getImage();
 * 
 * public Main() { Labframe(); }
 * 
 * public void Labframe() { setTitle("Title"); setResizable(false);
 * setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 * 
 * // 배경 이미지를 표시하기 위한 커스텀 JPanel 생성 JPanel backgroundPanel = new JPanel() {
 * 
 * @Override protected void paintComponent(Graphics g) {
 * super.paintComponent(g); g.drawImage(background, 0, 0, getWidth(),
 * getHeight(), this); } };
 * 
 * // 배경 이미지의 크기 가져오기 int imageWidth = background.getWidth(null); int
 * imageHeight = background.getHeight(null);
 * 
 * // 프레임 크기를 이미지 크기에 맞게 설정 setSize(imageWidth, imageHeight);
 * 
 * // 배경 패널을 프레임에 추가 add(backgroundPanel);
 * 
 * // 화면 중앙에 프레임 위치 setLocationRelativeTo(null);
 * 
 * setVisible(true); }
 * 
 * public static void main(String[] args) { new Main(); } }
 */
