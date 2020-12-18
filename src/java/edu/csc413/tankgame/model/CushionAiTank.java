package edu.csc413.tankgame.model;

public class CushionAiTank extends Tank {
    public CushionAiTank(String id, int health, double x, double y, double angle) {
        super(id, health, x, y, angle);
    }
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
//        moveBackward();
//
//        turnLeft();
        double distance = Math.sqrt(dx*dx+dy*dy);
        if(distance >400.0)
        {
            moveForward();}
        else if(distance < 200.0)
        {
            moveBackward();
        }
        //shootShell(gameState);
  }


}
