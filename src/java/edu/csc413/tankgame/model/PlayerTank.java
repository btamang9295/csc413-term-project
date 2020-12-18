package edu.csc413.tankgame.model;

public class PlayerTank extends Tank  {
    public PlayerTank(String id, int health, double x, double y, double angle) {
        super(id, health, x, y, angle);
    }

    private int cooldown = 80 ;


    public void move(GameState gameState){
        cooldown++;

        if(GameState.upPressed)
        {
            moveForward();
        }

         if(GameState.downPressed)
        {
            moveBackward();
        }

         if(GameState.leftPressed)
         {
             turnLeft();
         }

         if(GameState.rightPressed)
         {
             turnRight();
         }
         if (GameState.shootPressed)
         {
             if (cooldown > 80) {
                 shootShell(gameState);
             }
             cooldown = 0;
         }
         checkBounds(gameState);

    }




}
