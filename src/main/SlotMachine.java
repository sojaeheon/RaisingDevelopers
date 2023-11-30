package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SlotMachine extends JFrame implements ActionListener {
	GamePanel gp;
    private final String[] symbols = {"Cherry", "Apple", "Orange", "Lemon", "Grapes", "Watermelon"};
    private final JLabel[] reels = new JLabel[3];
    private final JButton spinButton;
    static double score = 0;
    public SlotMachine(GamePanel gp) {
        setTitle("Slot Machine");
        this.gp =gp;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel reelsPanel = new JPanel(new GridLayout(1, 3));
        for (int i = 0; i < 3; i++) {
            reels[i] = new JLabel();
            reelsPanel.add(reels[i]);
        }
        add(reelsPanel, BorderLayout.CENTER);

        spinButton = new JButton("Spin");
        spinButton.addActionListener(this);
        add(spinButton, BorderLayout.SOUTH);

        setUndecorated(true);
        setBounds(670, 250, 600, 220);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == spinButton) {
            spinReels();
        }
    }

    private void spinReels() {
        Random random = new Random();
        int imageSize = 200; 
        int[] sym = new int[3];
        for (int i = 0; i < 3; i++) {
            int symbolIndex = random.nextInt(symbols.length);
            String imagePath = "/res/background/" + symbols[symbolIndex] + ".png";
            sym[i] = symbolIndex;
            ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));
            Image originalImage = originalIcon.getImage();
            Image scaledImage = originalImage.getScaledInstance(imageSize, imageSize, Image.SCALE_SMOOTH);
            Icon scaledIcon = new ImageIcon(scaledImage);
            reels[i].setIcon(scaledIcon);
        }
        if(sym[0] == sym[1]) score = 0.01;
        else if(sym[1] == sym[2]) score = 0.01;
        else if(sym[0] == sym[2]) score = 0.01;
        else if((sym[1] == sym[2]) && (sym[0] == sym[1])) score = 0.2;
        else score = 0;
        gp.player.score += score;
        gp.player.level = (int)gp.player.score;
        System.out.println(score);

        JOptionPane.showMessageDialog(this, "끝났습니다.");
//      gp.Level_status++;
        gp.requestFocus();
        gp.eHandler.tempMap = 0;
        gp.eHandler.tempCol = 8;
        gp.eHandler.tempRow = 14;
        gp.gameState = gp.transitionState;
        this.dispose();
    }

}
