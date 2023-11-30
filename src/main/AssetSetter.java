package main;

import entity.NPC_Cat;
import entity.NPC_Prof;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setNPC() {
        int mapNum = 0;
        int i = 0;
        gp.npc[mapNum][i] = new NPC_Cat(gp);

        mapNum = 1;
        i = 0;
        gp.npc[mapNum][i] = new NPC_Prof(gp);
    }
}
