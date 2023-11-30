package entity;

import main.GamePanel;

public class NPC_Cat extends Entity{
    public NPC_Cat(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }

    public void getImage() {
        up1 = setup("/res/NPC/cat0", gp.tileSize+5, gp.tileSize+5);
        up2 = setup("/res/NPC/cat0", gp.tileSize+5, gp.tileSize+5);
        up3 = setup("/res/NPC/cat1", gp.tileSize+5, gp.tileSize+5);
        down1 = setup("/res/NPC/cat0", gp.tileSize+5, gp.tileSize+5);
        down2 = setup("/res/NPC/cat0", gp.tileSize+5, gp.tileSize+5);
        down3 = setup("/res/NPC/cat1", gp.tileSize+5, gp.tileSize+5);
        left1 = setup("/res/NPC/cat0", gp.tileSize+5, gp.tileSize+5);
        left2 = setup("/res/NPC/cat0", gp.tileSize+5, gp.tileSize+5);
        left3 = setup("/res/NPC/cat1", gp.tileSize+5, gp.tileSize+5);
        right1 = setup("/res/NPC/cat0", gp.tileSize+5, gp.tileSize+5);
        right2 = setup("/res/NPC/cat0", gp.tileSize+5, gp.tileSize+5);
        right3 = setup("/res/NPC/cat1", gp.tileSize+5, gp.tileSize+5);
    }

    public void setDialogue() {
        dialogues[0] = "집사야, 안녕! 일어났구나!";
        dialogues[1] = "문으로 가면 연구실로 출근할 수 있어!";
        dialogues[2] = "일정 레벨을 달성하면 미니게임도 풀 수 있어!";
        dialogues[3] = "오늘도 힘내서 출근하자..!";
    }

    public void speak() {
        super.speak();
    }
}