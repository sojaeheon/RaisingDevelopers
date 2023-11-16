package main;

import background.Home;
import background.Intro;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main{

    public static void setIntro() {
        JFrame introFrame = new JFrame();
        introFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        introFrame.setResizable(false);
        introFrame.setTitle("연구원 키우기");
        IntroPanel introPanel = new IntroPanel();
        introFrame.add(introPanel);
        introFrame.pack();
        introFrame.setLocationRelativeTo(null);
        introFrame.setVisible(true);
        introPanel.startGameThread();
    }

    public static void setLab() {
        JFrame labFrame = new JFrame();
        labFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        labFrame.setResizable(false);
        labFrame.setTitle("연구실");
        GamePanel gamePanel = new GamePanel();
        labFrame.add(gamePanel);
        labFrame.pack();
        labFrame.setLocationRelativeTo(null);
        labFrame.setVisible(true);
        gamePanel.startGameThread();
    }

    public static void setHome() {
        JFrame homeFrame = new JFrame();
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setResizable(false);
        homeFrame.setTitle("집");
        HomePanel homePanel = new HomePanel();
        homeFrame.add(homePanel);
        homeFrame.pack();
        homeFrame.setLocationRelativeTo(null);
        homeFrame.setVisible(true);
        homePanel.startGameThread();
    }

    public static void run() {
//        setIntro();
        setLab();
//        setHome();
    }

    public static void main(String[] args) {
        run();
    }
}


/*
public class Main extends JFrame {
    private Image background = new ImageIcon(Main.class.getResource("/image/Lab.png")).getImage();

    public Main() {
        Labframe();
    }

    public void Labframe() {
        setTitle("Title");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 배경 이미지를 표시하기 위한 커스텀 JPanel 생성
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // 배경 이미지의 크기 가져오기
        int imageWidth = background.getWidth(null);
        int imageHeight = background.getHeight(null);

        // 프레임 크기를 이미지 크기에 맞게 설정
        setSize(imageWidth, imageHeight);

        // 배경 패널을 프레임에 추가
        add(backgroundPanel);

        // 화면 중앙에 프레임 위치
        setLocationRelativeTo(null);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}*/
