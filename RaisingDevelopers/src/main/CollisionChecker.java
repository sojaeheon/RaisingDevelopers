package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;
    HomePanel hp;
    EndingPanel ep;
    public CollisionChecker(GamePanel gp) { this.gp = gp; }
    public CollisionChecker(HomePanel hp) { this.hp = hp; }
    public CollisionChecker(EndingPanel ep) { this.ep = ep; }

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
    public void checkTile_e(Entity entity) {
        int entityLeftX = entity.x + entity.solidArea.x;
        int entityRightX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.y + entity.solidArea.y;
        int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftX/ep.tileSize;
        int entityRightCol = entityRightX/ep.tileSize;
        int entityTopRow = entityTopY/ep.tileSize;
        int entityBottomRow = entityBottomY/ep.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopY - entity.speed)/ep.tileSize;
                tileNum1 = ep.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = ep.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (ep.tileM.tile[tileNum1].collision == true || ep.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomY + entity.speed)/ep.tileSize;
                tileNum1 = ep.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = ep.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (ep.tileM.tile[tileNum1].collision == true || ep.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.speed)/ep.tileSize;
                tileNum1 = ep.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = ep.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (ep.tileM.tile[tileNum1].collision == true || ep.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightX + entity.speed)/ep.tileSize;
                tileNum1 = ep.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = ep.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (ep.tileM.tile[tileNum1].collision == true || ep.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
    public void checkTile_h(Entity entity) {
        int entityLeftX = entity.x + entity.solidArea.x;
        int entityRightX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.y + entity.solidArea.y;
        int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftX / hp.tileSize;
        int entityRightCol = entityRightX / hp.tileSize;
        int entityTopRow = entityTopY / hp.tileSize;
        int entityBottomRow = entityBottomY / hp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopY - entity.speed) / hp.tileSize;
                tileNum1 = hp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = hp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (hp.tileM.tile[tileNum1].collision == true || hp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomY + entity.speed) / hp.tileSize;
                tileNum1 = hp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = hp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (hp.tileM.tile[tileNum1].collision == true || hp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.speed) / hp.tileSize;
                tileNum1 = hp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = hp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (hp.tileM.tile[tileNum1].collision == true || hp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightX + entity.speed) / hp.tileSize;
                tileNum1 = hp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = hp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (hp.tileM.tile[tileNum1].collision == true || hp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
