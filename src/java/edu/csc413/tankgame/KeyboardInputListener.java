package edu.csc413.tankgame;

import edu.csc413.tankgame.model.GameState;
import edu.csc413.tankgame.model.PlayerTank;
import edu.csc413.tankgame.model.Tank;
import edu.csc413.tankgame.view.MainView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyboardInputListener implements KeyListener {



    @Override
    public void keyTyped(KeyEvent event) {

    }

    @Override
    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.VK_W)
        {
            GameState.holdUpPressed();
            System.out.println("W pressed");
        }
        if (keyCode == KeyEvent.VK_S){
            GameState.holdDownPressed();
            System.out.println("S pressed");
        }
         if (keyCode == KeyEvent.VK_A){
            GameState.holdLeftPressed();
            System.out.println("A pressed");

        }
        else if (keyCode == KeyEvent.VK_D){
            GameState.holdRightPressed();
            System.out.println("D pressed");

        }
        else if (keyCode == KeyEvent.VK_SPACE){
            GameState.holdShootPressed();
            System.out.println("space pressed");
        }
         else if (keyCode == KeyEvent.VK_ESCAPE)
         {
             GameState.escapePressed();
             System.out.println("escape pressed");
         }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.VK_W)
        {
            GameState.upPressed = false;

            System.out.println("W released");
        }
        else if (keyCode == KeyEvent.VK_S){
            GameState.downPressed = false;
            System.out.println("S released");
        }
        else if (keyCode == KeyEvent.VK_A){
            GameState.leftPressed = false;
            System.out.println("A released");

        }
        else if (keyCode == KeyEvent.VK_D){
            GameState.rightPressed = false;
            System.out.println("D released");

        }
        else if (keyCode == KeyEvent.VK_SPACE){
            GameState.shootPressed = false;
            System.out.println("Space released");

        }


    }
}
