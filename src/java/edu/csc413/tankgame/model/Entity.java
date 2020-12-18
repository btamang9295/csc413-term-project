package edu.csc413.tankgame.model;

public abstract class Entity {
    protected final String id;
    protected int health;
    protected double x;
    protected double y;
    protected double angle;

    public Entity(String id, int health, double x, double y, double angle) {
        this.id = id;
        this.health = health;
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public String getId() {
        return id;
    }

    public int getHealth()
    {
        return health;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getAngle() {
        return angle;
    }



    public abstract void move(GameState gameState);
    public abstract void checkBounds(GameState gameState);
    public abstract void checkLives(GameState gameState);
    public abstract double getXBound();
    public abstract double getYBound();
    public abstract void setX(double x );
    public abstract void setY(double y);
    //public abstract void lives();


    /*
     *for dumbAi
     * moreForward();
     * turnRight();
     * */

    // TODO: The methods below are provided so you don't have to do the math for movement. However, note that they are
    // protected. You should not be calling these methods directly from outside the Tank class hierarchy. Instead,
    // consider how to design your Tank class(es) so that a Tank can represent both a player-controlled tank and an AI
    // controlled tank.

//    protected void moveForward() {
//        x += MOVEMENT_SPEED * Math.cos(angle);
//        y += MOVEMENT_SPEED * Math.sin(angle);
//    }
//
//    protected void moveBackward() {
//        x -= MOVEMENT_SPEED * Math.cos(angle);
//        y -= MOVEMENT_SPEED * Math.sin(angle);
//    }
//
//    protected void turnLeft() {
//        angle -= TURN_SPEED;
//    }
//
//    protected void turnRight() {
//        angle += TURN_SPEED;
//    }

}
