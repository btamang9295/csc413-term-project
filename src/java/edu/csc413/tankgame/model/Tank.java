package edu.csc413.tankgame.model;

/**
 * Model class representing a tank in the game. A tank has a position and an angle. It has a movement speed and a turn
 * speed, both represented below as constants.
 */
// TODO: Notice that Tank has a lot in common with Shell. For full credit, you will need to find a way to share code
// between the two classes so that the logic for e.g. moveForward, etc. are not duplicated.
public abstract class Tank extends Entity{
    private static final double MOVEMENT_SPEED = 2.0;
    private static final double TURN_SPEED = Math.toRadians(3.0);

    public Tank(String id, int health, double x, double y, double angle) {
        super(id, health, x, y, angle, id);
    }


    // The following methods will be useful for determining where a shell should be spawned when it
    // is created by this tank. It needs a slight offset so it appears from the front of the tank,
    // even if the tank is rotated. The shell should have the same angle as the tank.

    private void move(){

    }
    
    protected void moveForward() {
        x += MOVEMENT_SPEED * Math.cos(angle);
        y += MOVEMENT_SPEED * Math.sin(angle);
    }

    protected void moveBackward() {
        x -= MOVEMENT_SPEED * Math.cos(angle);
        y -= MOVEMENT_SPEED * Math.sin(angle);
    }

    protected void turnLeft() {
        angle -= TURN_SPEED;
    }

    protected void turnRight() {
        angle += TURN_SPEED;
    }


    protected void shootShell(GameState gameState)
    {
        Shell shell = new Shell( getShellX(), getHealth(), getShellY(), getAngle(), id);
        gameState.addShellEntity(shell);
    }

    protected void shootSmartShell (GameState gameState)
    {
        Shell shell = new Shell (getShellX(), getHealth(), getShellY(), getAngle(), id);
        gameState.addShellEntity(shell);
    }

    private double getShellX() {
        return getX() + 30.0 * (Math.cos(getAngle()) + 0.5);
    }

    private double getShellY() {
        return getY() + 30.0 * (Math.sin(getAngle()) + 0.5);
    }

    @Override
    public double getXBound() {
        return getX() + 55.0;
    }

    @Override
    public double getYBound() {
        return getY() + 55.0;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    public int getHealth()
    {
        return health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    public void checkBounds(GameState gameState) {
        if( x < GameState.TANK_X_LOWER_BOUND)
        {
            x = GameState.TANK_X_LOWER_BOUND;
        }
        if(getX() > GameState.TANK_X_UPPER_BOUND)
        {
            x = GameState.TANK_X_UPPER_BOUND;
        }
        if(getY() < GameState.TANK_X_LOWER_BOUND)
        {
            y = GameState.TANK_Y_LOWER_BOUND;
        }
        if(getY() > GameState.TANK_Y_UPPER_BOUND)
        {
            y = GameState.TANK_Y_UPPER_BOUND;
        }
    }


}
