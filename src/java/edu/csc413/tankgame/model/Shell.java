package edu.csc413.tankgame.model;

/**
 * Model class representing a shell that has been fired by a tank. A shell has a position and an angle, as well as a
 * speed. Shells by default should be unable to turn and only move forward.
 */
// TODO: Notice that Shell has a lot in common with Tank. For full credit, you will need to find a way to share code
// between the two classes so that the logic for e.g. moveForward, etc. are not duplicated.
public class Shell extends Shells{
    public Shell(double x, int health, double y, double angle, String shooterID) {
        super(x, health, y, angle, shooterID);
    }

    @Override
    public void move(GameState gameState) {
        moveForward();
    }

    public String getShooterID()
    {
        return shooterID;
    }


}
