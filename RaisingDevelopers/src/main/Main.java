package main;

import background.*;
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
    
    public static void setEnding() {
        JFrame endingFrame = new JFrame();
        endingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        endingFrame.setResizable(false);
        endingFrame.setTitle("졸업");
        EndingPanel endingPanel = new EndingPanel();
        endingFrame.add(endingPanel);
        endingFrame.pack();
        endingFrame.setLocationRelativeTo(null);
        endingFrame.setVisible(true);
        endingPanel.startGameThread();
    }

    public static void run() {
//      setIntro();
//      setLab();
//      setHome();
		setEnding();
    }

    public static void main(String[] args) {
        run();
    }
}



