package background;

//import main.GamePanel;

import main.IntroPanel;

import java.awt.*;
import javax.swing.*;

public class Intro extends JFrame{
    private Image introImage;
    private int introWidth;
    private int introHeight;
    private IntroPanel ip;

    public Intro(IntroPanel ip) {
        this.ip = ip;
        introImage = new ImageIcon(ip.getClass().getResource("/imgs/background/Intro.gif")).getImage();
        introWidth = introImage.getWidth(null);
        introHeight = introImage.getHeight(null);

/*        // 버튼 생성
        JButton startButton = new JButton("시작");
        startButton.setBounds(10, 10, 100, 100);

        // 버튼 클릭 이벤트를 처리하는 액션 리스너 추가
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                gp.switchToLab(); // lab 화면으로 전환하는 메서드 호출
            }
        });

        // 버튼을 GamePanel에 추가
        gp.setLayout(null); // 레이아웃 설정 제거
        gp.add(startButton);*/
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(introImage, 0, 0, ip.getWidth(), ip.getHeight(), 0, 0, introWidth, introHeight, null);
    }
}
