package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;

    public Font Sam3KRFont, DOSIyagiBoldface, DungGeunMo;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;

    private Graphics2D g2;

    public UI(GamePanel gp) {
        this.gp = gp;

//        DOSIyagiBoldface = new Font("DOSIyagiBoldface", Font.PLAIN, 40);
//        Sam3KRFont = new Font("Sam3KRFont", Font.PLAIN, 40);
        DungGeunMo = new Font("DungGeunMo", Font.PLAIN, 30);
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(DungGeunMo);
        g2.setColor(Color.white);

        // TITLE STATE
        if(gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        // PLAY STATE
        if(gp.gameState == gp.playState) {
            //
        }
        // PAUSE STATE
        if(gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        // DIALOGUE STATE
        if(gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
    }

    public void drawTitleScreen() {
        int x, y;
        // BACKGROUND IMAGE
        BufferedImage image = null;
        x = 0;
        y = 0;
        try {
            image = ImageIO.read(getClass().getResource("/res/background/Intro.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2.drawImage(image, x, y, gp.getWidth(), gp.getHeight(), null);

        // TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 45F));
//        String text = "연구원 키우기 게임";
        String text = "HELLO, \nSTUDENT!";
        x = getXforCenteredText(text) + 60;
        y = gp.tileSize*5 - 20;

        for (String line : text.split("\n")) {
            // SHADOW1
            g2.setColor(new Color(53, 137, 67));
            g2.drawString(line, x-4, y+1);
            // SHADOW2
            g2.setColor(new Color(97, 182, 11));
            g2.drawString(line, x-2, y+1);
            // SHADOW3
            g2.setColor(new Color(101, 175, 113));
            g2.drawString(line, x-1, y+1);
            // MAIN COLOR
            g2.setColor(new Color(58, 209, 70));
            g2.drawString(line, x, y);
            y += 50;
        }

        // MENU
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 27F));

        text = "START";
        x = getXforCenteredText(text);
        y += gp.tileSize - 57;
        // SHADOW1
        g2.setColor(new Color(53, 137, 67));
        g2.drawString(text, x-2, y+1);
        // MAIN COLOR
        g2.setColor(new Color(58, 209, 70));
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x-gp.tileSize+25, y);
        }


        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.tileSize - 20;
        // SHADOW1
        g2.setColor(new Color(53, 137, 67));
        g2.drawString(text, x-2, y+1);
        // MAIN COLOR
        g2.setColor(new Color(58, 209, 70));
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x-gp.tileSize+25, y);
        }
    }

    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 60F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen() {
        // WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize/2 + 30;
        int width = gp.screenWidth - (gp.tileSize*5);
        int height = gp.tileSize*3;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(237,237,237);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }

    public int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenHeight/2- length/2;
        return x;
    }
}
