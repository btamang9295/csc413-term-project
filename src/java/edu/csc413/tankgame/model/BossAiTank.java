package edu.csc413.tankgame.model;

public class BossAiTank extends Tank {
    public BossAiTank(String id, int health, double x, double y, double angle) {
        super(id, health, x, y, angle);
    }

    private int cooldown = 500;
    public double getXBound() {
        return getX() + 100.0;
    }

    @Override
    public double getYBound() {
        return getY() + 100.0;
    }


    @Override
    public void move(GameState gameState) {
        cooldown --;
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
        if (cooldown == 0) {
            shootSmartShell(gameState);
            cooldown = 500;
        }
    }


}
