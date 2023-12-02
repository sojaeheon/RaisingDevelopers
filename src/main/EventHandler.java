package main;

public class EventHandler {
    GamePanel gp;
    EventRect eventRect[][][];

    static int cnt = 0;
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int tempMap, tempCol, tempRow;

    public EventHandler(GamePanel gp) {
        this.gp = gp;
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
            {teleport(0,9,12);}
            else if ((hit(0,13,4,"any") == true || hit(0,12,4,"any") == true))
            {
            	if (gp.player.currentCh >= 4 ) {
            		if(gp.player.score >= 4.0) {
            			gp.eHandler.tempMap = 2;
                		gp.eHandler.tempCol = 7;
                		gp.eHandler.tempRow = 14;
                		gp.gameState = gp.transitionState;
                		gp.GameMusic.close();
                		gp.graduation.start();
            		}

            	
        		}
            	else if((gp.player.level >= 1) && (cnt == 0) )
            	{
            		gp.gameState = gp.load_minigame1_state;
            		cnt++;
            	}
            	else if((gp.player.level >= 2) && (cnt == 1) )
            	{
            		gp.gameState = gp.load_minigame2_state;
            		cnt++;
            	}
            	else if((gp.player.level >= 3) && (cnt == 2))
            	{
            		gp.gameState = gp.load_minigame3_state;
            		cnt++;
            	}
            	else
            	{
            		teleport(1,13,3);
            	}

            }
            else if ((hit(1,8,13,"any"))==true) {
            	gp.gameState = gp.load_quiz_state;
            }
            else if((hit(2,7,4,"any")==true)||(hit(2,8,4,"any")==true)) {
            	gp.gameState = gp.rankingState;

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



		gp.gameState = gp.transitionState;
		tempMap = map;
		tempCol = col;
		tempRow = row;
//        gp.currentMap = map;
//        gp.player.x = gp.tileSize*col;
//        gp.player.y = gp.tileSize*row;
//        previousEventX = gp.player.x;
//        previousEventY = gp.player.y;
//        canTouchEvent = false;
	}


}
