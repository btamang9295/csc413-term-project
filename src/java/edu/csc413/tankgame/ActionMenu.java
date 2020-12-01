package edu.csc413.tankgame;

import com.sun.tools.javac.Main;
import edu.csc413.tankgame.view.MainView;
import edu.csc413.tankgame.view.RunGameView;
import edu.csc413.tankgame.view.StartMenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionMenu implements ActionListener {


    @Override
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        if (actionCommand.equals(StartMenuView.START_BUTTON_ACTION_COMMAND)) {
            MainView.pressed_startGame();
            System.out.println("Start Game");
            //runGameView.setScreen(MainView.Screen.RUN_GAME_SCREEN);
            //setFrame(MainView.Screen.RUN_GAME_SCREEN);
        } else if (actionCommand.equals(StartMenuView.EXIT_BUTTON_ACTION_COMMAND)) {
           MainView.pressed_ExitGame();
            System.out.println("exit");
        }
    }
}