package edu.csc413.tankgame.model;

import edu.csc413.tankgame.WallImageInfo;

public class Wall extends Entity {
    public Wall(String id, int health, double x, double y, double angle) {
        super(id, health,  x, y, angle, id);
    }
    public void setID(String id)
    {
        this.id = id;
    }

    @Override
    public void move(GameState gameState) {

    }
    public String getId() {
        return id;
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

    public int getHealth()
    {
        //return health;
         System.out.println(health);
         return health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }


    @Override
    public void checkBounds(GameState gameState) {

    }

    @Override
    public double getXBound() {
        return getX() + 32.0;
    }

    @Override
    public double getYBound() {
        return getY() + 32.0;
    }

    @Override
    public void setX(double x) {

    }
    @Override
    public void setY(double y) {
    }

}
