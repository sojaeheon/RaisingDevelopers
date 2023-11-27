package main;

import entity.NPC_Prof;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setNPC() {
        int mapNum = 1;
        int i = 0;
        gp.npc[mapNum][i] = new NPC_Prof(gp);
        gp.npc[mapNum][i].x = gp.tileSize*7;
        gp.npc[mapNum][i].y = gp.tileSize*3;
        i++;
    }
}
