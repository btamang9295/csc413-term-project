package edu.csc413.tankgame.model;

public class TurretAITank extends Tank{
    public TurretAITank(String id, int health, double x, double y, double angle) {
        super(id, health, x, y, angle);
    }

    int cooldown = 300;
    @Override
    public void move(GameState gameState) {

        Entity playerTank = gameState.getEntity(GameState.PLAYER_TANK_ID);
        double dx = playerTank.getX()-getX();
        double dy = playerTank.getY()-getY();

        double angleToPlayer = Math.atan2(dy,dx);
        double angleDifference = getAngle()-angleToPlayer;
        angleDifference -= Math.floor(angleDifference / Math.toRadians(360.0)+0.5)*Math.toRadians(360.0);

        if(angleDifference <-Math.toRadians(3.0)){
            turnRight();

        }else if(angleDifference >Math.toRadians(3.0)){
            turnLeft();
        }


        cooldown --;
        checkBounds(gameState);


        if (cooldown == 0) {
            shootShell(gameState);
            cooldown = 300;
        }
    }



}
