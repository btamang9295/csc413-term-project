package edu.csc413.tankgame.model;

/**
 * Model class representing a shell that has been fired by a tank. A shell has a position and an angle, as well as a
 * speed. Shells by default should be unable to turn and only move forward.
 */
// TODO: Notice that Shell has a lot in common with Tank. For full credit, you will need to find a way to share code
// between the two classes so that the logic for e.g. moveForward, etc. are not duplicated.
public class Shell extends Entity{
    private static final String SHELL_ID_PREFIX = "shell-";
    private static final double MOVEMENT_SPEED = 4.0;
    private static long uniqueId = 0L;

    public Shell( double x, int health, double y, double angle, String shooterID) {
      super(getUniqueId(), health,  x, y, angle,  shooterID);
    }

    private static String getUniqueId() {
        return SHELL_ID_PREFIX + uniqueId++;
    }

    @Override
    public void move(GameState gameState) {
        moveForward();
        //checkBounds(gameState);
    }

    public String getShooterID()
    {
        return shooterID;
    }

    @Override
    public int getHealth() {
        return 0;
    }

    @Override
    public void setHealth(int health) {

    }

    @Override
    public void checkBounds(GameState gameState) {

        for(Entity shellEntities: gameState.getEntities())
        {
        if( x < GameState.SHELL_X_LOWER_BOUND)
        {
            gameState.removeShellEntity(shellEntities);
        }
        else if(x > GameState.SHELL_X_UPPER_BOUND)
        {
            gameState.removeShellEntity(shellEntities);
        }
        else if(y < GameState.SHELL_Y_LOWER_BOUND)
        {
            gameState.removeShellEntity(shellEntities);
        }
        else if(y > GameState.SHELL_Y_UPPER_BOUND) {
            gameState.removeShellEntity(shellEntities);
        }
        }
    }

    @Override
    public double getXBound() {
        return getX() + 24.0;
    }

    @Override
    public double getYBound() {

        return getY() + 24.0;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    public void moveForward()
    {
        x += MOVEMENT_SPEED * Math.cos(angle);
        y += MOVEMENT_SPEED * Math.sin(angle);
    }


}
