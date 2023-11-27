package main;

import javax.swing.JFrame;

public class Main {
    public static JFrame window;
    public static void main(String[] args) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("연구원 키우기 게임");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
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
