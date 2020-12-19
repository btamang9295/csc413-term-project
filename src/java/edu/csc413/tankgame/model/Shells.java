package edu.csc413.tankgame.model;

public abstract class Shells extends Entity{
    public Shells( double x, int health, double y, double angle, String shooterID) {
      super(getUniqueId(), health,  x, y, angle,  shooterID);
    }

    private static final double TURN_SPEED = Math.toRadians(3.0); // changed move speed to 5
    private static final String SHELL_ID_PREFIX = "shell-";
    private static long uniqueId = 0L;
    private static final double MOVEMENT_SPEED = 4.0;

    private static String getUniqueId() {
        return SHELL_ID_PREFIX + uniqueId++;
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
    public int getHealth() { return health; }
    @Override
    public void setHealth(int health) { }
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

    protected void turnLeft() {
        angle -= TURN_SPEED;
    }
    protected void turnRight() {
        angle += TURN_SPEED;
    }
    public void moveForward()
    {
        x += MOVEMENT_SPEED * Math.cos(angle);
        y += MOVEMENT_SPEED * Math.sin(angle);
    }


}
