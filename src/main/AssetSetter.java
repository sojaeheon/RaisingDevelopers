package main;

import entity.NPC_Prof;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setNPC() {
        gp.npc[0] = new NPC_Prof(gp);
        gp.npc[0].x = gp.tileSize*7;
        gp.npc[0].y = gp.tileSize*3;
    }
}
