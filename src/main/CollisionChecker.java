package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;
//    HomePanel hp;
    public CollisionChecker(GamePanel gp) { this.gp = gp; }
//    public CollisionChecker(HomePanel hp) { this.hp = hp; }

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
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    // NPC
    public void checkEntity(Entity entity) {
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
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].name == "prof" && gp.tileM.tile[tileNum2].name == "prof") {
                    entity.collisionNPC = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].name == "prof" && gp.tileM.tile[tileNum2].name == "prof") {
                    entity.collisionNPC = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].name == "prof" && gp.tileM.tile[tileNum2].name == "prof") {
                    entity.collisionNPC = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].name == "prof" && gp.tileM.tile[tileNum2].name == "prof") {
                    entity.collisionNPC = true;
                }
                break;
        }
    }

//    public void checkTile_h(Entity entity) {
//        int entityLeftX = entity.x + entity.solidArea.x;
//        int entityRightX = entity.x + entity.solidArea.x + entity.solidArea.width;
//        int entityTopY = entity.y + entity.solidArea.y;
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
}
