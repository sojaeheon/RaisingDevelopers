package main;

import java.awt.*;

import javax.swing.JOptionPane;


import dto.Submit_dto;


public class EventHandler {
    GamePanel gp;
    EventRect eventRect[][][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    
    Submit_dto sub;
    
    Main main;

    public EventHandler(GamePanel gp, Main main) {
        this.gp = gp;
        this.main = main;
        System.out.println(gp+"이벤트 게임이 오류냐");
        eventRect = new EventRect[gp.maxMap][gp.maxScreenCol][gp.maxScreenRow];
        int map = 0;
        int col = 0;
        int row = 0;
        while (map < gp.maxMap && col < gp.maxScreenCol && row < gp.maxScreenRow) {
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
            
            

            col++;
            if (col == gp.maxScreenCol) {
                col = 0;
                row++;
                if (row == gp.maxScreenRow) {
                    row = 0;
                    map++;
                }
            }
        }
    }

    public void checkEvent() {

        // Check if the character is more than 1 tile away from the last event
        int xDistance = Math.abs(gp.player.x - previousEventX);
        int yDistance = Math.abs(gp.player.y - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize) {
            canTouchEvent = true;
        }

        if (canTouchEvent == true) {
            if ((hit(1,13,2,"any") == true || hit(1,13,2,"any") == true))
            {
            	teleport(0,9,12);
            }
            else if ((hit(0,13,4,"any") == true || hit(0,12,4,"any") == true))
            {
            	teleport(1,13,3);
            }
            else if ((hit(1,8,13,"any"))==true) {
            	
            	gp.stopGameThread();
            	
            	int result = JOptionPane.showConfirmDialog(gp, "퀴즈 게임을 시작하겠습니까?","confirm",JOptionPane.YES_NO_OPTION);
            	
            	if(result == JOptionPane.CLOSED_OPTION) {
            		gp.startGameThread();
            		gp.repaint();
            		teleport(1,9,13);
            	}else if(result == JOptionPane.YES_OPTION) {
            		main.change("quizpanel");

            	}else {
            		gp.startGameThread();
            		teleport(1,9,13);
            		
            	}
            }
            }
        }
    

    public boolean hit(int map, int col, int row, String reqDirection) {
        boolean hit = false;

        if (map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.x + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.y + gp.player.solidArea.y;
            eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y;

            if (gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
                if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;

                    previousEventX = gp.player.x;
                    previousEventY = gp.player.y;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }

        return hit;
    }

    public void teleport(int map, int col, int row) {
        gp.currentMap = map;
        gp.player.x = gp.tileSize*col;
        gp.player.y = gp.tileSize*row;
        previousEventX = gp.player.x;
        previousEventY = gp.player.y;
        canTouchEvent = false;
    }
    
    
}
