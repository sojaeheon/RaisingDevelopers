package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_Prof extends Entity {
    public NPC_Prof(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }

    public void getImage() {
        up1 = setup("/res/npc/Npc0", gp.tileSize, gp.tileSize);
        up2 = setup("/res/npc/Npc1", gp.tileSize, gp.tileSize);
        up3 = setup("/res/npc/Npc2", gp.tileSize, gp.tileSize);
        down1 = setup("/res/npc/Npc0", gp.tileSize, gp.tileSize);
        down2 = setup("/res/npc/Npc1", gp.tileSize, gp.tileSize);
        down3 = setup("/res/npc/Npc2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/npc/Npc0", gp.tileSize, gp.tileSize);
        left2 = setup("/res/npc/Npc1", gp.tileSize, gp.tileSize);
        left3 = setup("/res/npc/Npc2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/npc/Npc0", gp.tileSize, gp.tileSize);
        right2 = setup("/res/npc/Npc1", gp.tileSize, gp.tileSize);
        right3 = setup("/res/npc/Npc2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue() {
        dialogues[0] = "아니, 이게 누구야. \n우리 연구원 뚱땅이 아닌가..!";
        dialogues[1] = "그래, 오늘도 출근을 했구나...\n";
        dialogues[2] = "어서 네 자리로 가서 \n문제를 풀어보거라.";
        dialogues[3] = "한 챕터를 다 풀어야만 \n집에 갈 수 있단다.";
        dialogues[4] = "원래 퇴근이란 \n자기 생각대로 되지 않는 법이란다... ";
        dialogues[5] = "오늘도 파이팅이다! \n그럼 이만..";
    }

    public void setAction() {

        actionLockCounter ++;

        if(actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1; // 1~100

            if(i <= 25) {
                direction = "up";
            }
            if(i > 25 && i <= 50) {
                direction = "down";
            }
            if(i > 50 && i <= 70) {
                direction = "left";
            }
            if(i > 70 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
    public void speak() {
        super.speak();
    }
}
