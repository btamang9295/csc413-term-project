package edu.csc413.tankgame.model;

public abstract class Entity {
    protected String id;  //changed the final here
    protected int health;
    protected double x;
    protected double y;
    protected double angle;
    protected String shooterID;

    public Entity(String id, int health, double x, double y, double angle, String shooterID) {
        this.id = id;
        this.health = health;
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.shooterID = shooterID;
    }

    public String getId() {
        return id;
    }
    public String getShooterID(){ return id; }

    public abstract int  getHealth();
    public abstract void setHealth(int health);
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
    public abstract double getXBound();
    public abstract double getYBound();
    public abstract void setX(double x);
    public abstract void setY(double y);


    private static final double MOVEMENT_SPEED = 2.0;
    private static final double TURN_SPEED = Math.toRadians(3.0);


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


}
