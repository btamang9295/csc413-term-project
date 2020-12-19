package edu.csc413.tankgame.model;

public class SmartShell extends Shell {
    public SmartShell(double x, int health, double y, double angle, String shooterID) {
        super(x, health, y, angle, shooterID);
    }
    private static final double MOVEMENT_SPEED = 2.5;
    private static final double TURN_SPEED = Math.toRadians(3.0); // changed move speed to 5


    public void moveForward()
    {
        x += MOVEMENT_SPEED * Math.cos(angle);
        y += MOVEMENT_SPEED * Math.sin(angle);
    }
    protected void turnLeft() {
        angle -= TURN_SPEED;
    }
    protected void turnRight() {
        angle += TURN_SPEED;
    }

    /*
    Smart shell added

     */
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

        moveForward();
    }

    public String getShooterID()
    {
        return shooterID;
    }

}


