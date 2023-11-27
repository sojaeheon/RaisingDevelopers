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
    private Graphics2D g2;

    public Font Sam3KRFont, DOSIyagiBoldface, DungGeunMo;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;


    public UI(GamePanel gp) {
        this.gp = gp;

//        DOSIyagiBoldface = new Font("DOSIyagiBoldface", Font.PLAIN, 40);
//        Sam3KRFont = new Font("Sam3KRFont", Font.PLAIN, 40);
        DungGeunMo = new Font("DungGeunMo", Font.PLAIN, 30);
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
        }
        // PAUSE STATE
        if(gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        // DIALOGUE STATE
        if(gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
        // CHARACTER STATE
        if(gp.gameState == gp.characterState) {
            drawCharacterScreen();
        }

        // OPTIONS STATE
        if(gp.gameState == gp.optionsState) {
            drawOptionsScreen();
        }

        //ENDING STATE
        if(gp.gameState == gp.endingState) {
            drawEndingScreen();
        }

        // GAME OVER STATE
        if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }
        
        //quiz load
        if(gp.gameState == gp.load_quiz_state) {
        	drawLoadScreen();
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
        x = getXforCenteredText(text) + 70;
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
        x = getXforCenteredText(text) + 70;
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
    
	public void drawLoadScreen() {

		int x = gp.tileSize * 5;
		int y = gp.tileSize * 6;
		int width = gp.tileSize * 6;
		int height = gp.tileSize * 5;
		String text;

		drawSubWindow(x, y, width, height);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));

		// Main
		text = "문제풀이를";
		x = getXforCenteredText(text);
		y = gp.tileSize * 8 - 20;
		g2.setColor(Color.white);
		g2.drawString(text, x, y);

		text = "시작하시겠습니까?";
		x = getXforCenteredText(text);
		y += 20;
		g2.setColor(Color.white);
		g2.drawString(text, x, y);

		// Retry
		g2.setFont(g2.getFont().deriveFont(20f));
		text = "YES";
		x = getXforCenteredText(text);
		y += gp.tileSize * 2 - 35;
		g2.drawString(text, x, y);
		if (commandNum == 0) {
			g2.drawString("↪", x - 40, y);
		}

		// Back to the title screen
		text = "NO";
		x = getXforCenteredText(text);
		y += 40;
		g2.drawString(text, x, y);
		if (commandNum == 1) {
			g2.drawString("↪", x - 40, y);
		}

	}

    public void drawCharacterScreen() {
        // CREATE A FRAME
        final int frameX = gp.tileSize*11;
        final int frameY = gp.tileSize*9;
        final int frameWidth = gp.tileSize*3;
        final int frameHeight = gp.tileSize*3;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // TEXT : 여기에 변수 추가
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(20F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 32;

        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Score", textX, textY);
        textY += lineHeight;
        g2.drawString("Ch", textX, textY);

        // VALUES
        int tallX = (frameX + frameWidth) - 30;
        // React textY
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlignToRightText(value, tallX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.format("%.2f", gp.player.score%1.0);
        textX = getXforAlignToRightText(value, tallX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.currentCh + "/" + gp.player.maxCh);
        textX = getXforAlignToRightText(value, tallX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
    }

    public void drawOptionsScreen() {
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        // SUB WINDOW
        int frameX = gp.tileSize*5;
        int frameY = gp.tileSize*4;
        int frameWidth = gp.tileSize*7;
        int frameHeight = gp.tileSize*7;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
    }

    public void drawEndingScreen() {

    }

    public void drawGameOverScreen() {
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110F));

        // Shadow
        text = "Game Over";
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize*5;
        g2.drawString(text, x, y);
        // Main
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);

        // Retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Restart";
        x = getXforCenteredText(text);
        y += gp.tileSize*6;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString("↪", x-40, y);
        }

        // Back to the title screen
        text = "Exit";
        x = getXforCenteredText(text);
        y += 60;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString("↪", x-40, y);
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

    // 연구실 게시판에 그릴 거임
//    public void drawSubWindow_C(int x, int y, int width, int height) {
//        Color c = new Color(204,218,235);
//        g2.setColor(c);
//        g2.fillRoundRect(x, y, width, height, 0, 0);
//    }

    public int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenHeight/2- length/2;
        return x;
    }
    public int getXforAlignToRightText(String text, int tailX) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
}
