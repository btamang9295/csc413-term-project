package edu.csc413.tankgame.model;

import edu.csc413.tankgame.KeyboardInputListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.font.GlyphMetrics;

public class PlayerTank extends Tank  {
    public PlayerTank(String id, double x, double y, double angle) {
        super(id, x, y, angle);
    }



    @Override
    public void move(GameState gameState){

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


         }
    }

}
