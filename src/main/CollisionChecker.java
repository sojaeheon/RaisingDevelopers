package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftX = entity.x + entity.solidArea.x;
        int entityRightX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.y + entity.solidArea.y;
        int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftX/gp.tileSize;
        int entityRightCol = entityRightX/gp.tileSize;
        int entityTopRow = entityTopY/gp.tileSize;
        int entityBottomRow = entityBottomY/gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    // NPC
    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                // Get entity's solid area position
                entity.solidArea.x = entity.x + entity.solidArea.x;
                entity.solidArea.y = entity.y + entity.solidArea.y;
                // Get the target's solid area position
                target[i].solidArea.x = target[i].x + target[i].solidArea.x;
                target[i].solidArea.y = target[i].y + target[i].solidArea.y;

                switch (entity.direction) {
                    case "up": entity.solidArea.y -= entity.speed; break;
                    case "down": entity.solidArea.y += entity.speed; break;
                    case "left": entity.solidArea.x -= entity.speed; break;
                    case "right": entity.solidArea.x += entity.speed; break;
                }

                if (entity.solidArea.intersects(target[i].solidArea)) {
                    if (target[i] != entity) {
                        entity.collisionOn = true;
                        index = i;
                    }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }
    public boolean checkPlayer(Entity entity) {
        boolean contactPlayer = false;
        // Get entity's solid area position
        entity.solidArea.x = entity.x + entity.solidArea.x;
        entity.solidArea.y = entity.y + entity.solidArea.y;
        // Get the target's solid area position
        gp.player.solidArea.x = gp.player.x + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.y + gp.player.solidArea.y;

        switch (entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed; break;
            case "down":
                entity.solidArea.y += entity.speed; break;
            case "left":
                entity.solidArea.x -= entity.speed; break;
            case "right":
                entity.solidArea.x += entity.speed; break;
        }

        if (entity.solidArea.intersects(gp.player.solidArea)) {
            entity.collisionOn = true;
            contactPlayer = true;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        return contactPlayer;
    }
}
//        int entityLeftX = entity.X + entity.solidArea.x;
//        int entityRightX = entity.X + entity.solidArea.x + entity.solidArea.width;
//        int entityTopY = entity.Y + entity.solidArea.y;
//        int entityBottomY = entity.Y + entity.solidArea.y + entity.solidArea.height;
//
//        int entityLeftCol = entityLeftX/gp.tileSize;
//        int entityRightCol = entityRightX/gp.tileSize;
//        int entityTopRow = entityTopY/gp.tileSize;
//        int entityBottomRow = entityBottomY/gp.tileSize;
//
//        int tileNum1, tileNum2;
//
//        switch (entity.direction) {
//            case "up":
//                entityTopRow = (entityTopY - entity.speed)/gp.tileSize;
//                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
//                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
//                if (gp.tileM.tile[tileNum1].name == "prof" && gp.tileM.tile[tileNum2].name == "prof") {
//                    entity.collisionNPC = true;
//                }
//                break;
//            case "down":
//                entityBottomRow = (entityBottomY + entity.speed)/gp.tileSize;
//                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
//                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
//                if (gp.tileM.tile[tileNum1].name == "prof" && gp.tileM.tile[tileNum2].name == "prof") {
//                    entity.collisionNPC = true;
//                }
//                break;
//            case "left":
//                entityLeftCol = (entityLeftX - entity.speed)/gp.tileSize;
//                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
//                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
//                if (gp.tileM.tile[tileNum1].name == "prof" && gp.tileM.tile[tileNum2].name == "prof") {
//                    entity.collisionNPC = true;
//                }
//                break;
//            case "right":
//                entityRightCol = (entityRightX + entity.speed)/gp.tileSize;
//                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
//                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
//                if (gp.tileM.tile[tileNum1].name == "prof" && gp.tileM.tile[tileNum2].name == "prof") {
//                    entity.collisionNPC = true;
//                }
//                break;
//        }
//    }

//    public void checkTile_h(Entity entity) {
//        int entityLeftX = entity.X + entity.solidArea.x;
//        int entityRightX = entity.X + entity.solidArea.x + entity.solidArea.width;
//        int entityTopY = entity. + entity.solidArea.y;
//        int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;
//
//        int entityLeftCol = entityLeftX / hp.tileSize;
//        int entityRightCol = entityRightX / hp.tileSize;
//        int entityTopRow = entityTopY / hp.tileSize;
//        int entityBottomRow = entityBottomY / hp.tileSize;
//
//        int tileNum1, tileNum2;
//
//        switch (entity.direction) {
//            case "up":
//                entityTopRow = (entityTopY - entity.speed) / hp.tileSize;
//                tileNum1 = hp.tileM.mapTileNum[entityLeftCol][entityTopRow];
//                tileNum2 = hp.tileM.mapTileNum[entityRightCol][entityTopRow];
//                if (hp.tileM.tile[tileNum1].collision == true || hp.tileM.tile[tileNum2].collision == true) {
//                    entity.collisionOn = true;
//                }
//                break;
//            case "down":
//                entityBottomRow = (entityBottomY + entity.speed) / hp.tileSize;
//                tileNum1 = hp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
//                tileNum2 = hp.tileM.mapTileNum[entityRightCol][entityBottomRow];
//                if (hp.tileM.tile[tileNum1].collision == true || hp.tileM.tile[tileNum2].collision == true) {
//                    entity.collisionOn = true;
//                }
//                break;
//            case "left":
//                entityLeftCol = (entityLeftX - entity.speed) / hp.tileSize;
//                tileNum1 = hp.tileM.mapTileNum[entityLeftCol][entityTopRow];
//                tileNum2 = hp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
//                if (hp.tileM.tile[tileNum1].collision == true || hp.tileM.tile[tileNum2].collision == true) {
//                    entity.collisionOn = true;
//                }
//                break;
//            case "right":
//                entityRightCol = (entityRightX + entity.speed) / hp.tileSize;
//                tileNum1 = hp.tileM.mapTileNum[entityLeftCol][entityTopRow];
//                tileNum2 = hp.tileM.mapTileNum[entityRightCol][entityBottomRow];
//                if (hp.tileM.tile[tileNum1].collision == true || hp.tileM.tile[tileNum2].collision == true) {
//                    entity.collisionOn = true;
//                }
//                break;
//        }
//    }
//}
