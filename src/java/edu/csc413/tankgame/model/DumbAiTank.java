package edu.csc413.tankgame.model;

public class DumbAiTank extends Tank{
    public DumbAiTank(String id, int health, double x, double y, double angle) {
        super(id, health, x, y, angle);
    }

    int cooldown = 20;
    @Override
    public void move(GameState gameState) {
        checkBounds(gameState);
        cooldown++;

//        moveForward();
//        turnRight();
        if (cooldown > 20) {
            //shootShell(gameState);
        }
        cooldown = 0;
    }



}
