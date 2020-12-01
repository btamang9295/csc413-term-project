package edu.csc413.tankgame;

import com.sun.tools.javac.Main;
import edu.csc413.tankgame.model.*;
import edu.csc413.tankgame.view.MainView;
import edu.csc413.tankgame.view.RunGameView;
import edu.csc413.tankgame.view.StartMenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.Map;

/**
 * GameDriver is the primary controller class for the tank game. The game is launched from GameDriver.main, and
 * GameDriver is responsible for running the game loop while coordinating the views and the data models.
 */
public class GameDriver {
    // TODO: Implement.
    // Add the instance variables, constructors, and other methods needed for this class. GameDriver is the centerpiece
    // for the tank game, and should store and manage the other components (i.e. the views and the models). It also is
    // responsible for running the game loop.
    private final MainView mainView;
    private final RunGameView runGameView;
    private final GameState gameState;




    public GameDriver() {
        mainView = new MainView();
        runGameView = mainView.getRunGameView();
        gameState = new GameState();

    }

    public void start() {
        // TODO: Implement.
        // This should set the MainView's screen to the start menu screen.

        //pseudocode
        /*
        start main screen
        if 'start game' is selected run mainview.RUN_GAME_SCREEN
        else if 'exit' is selected exit the program
         */
       // mainView.setScreen(MainView.Screen.START_MENU_SCREEN);
        //if (MainView.start_clicked = true)
        mainView.setScreen(MainView.Screen.START_MENU_SCREEN);

        if (MainView.start_clicked = true)
        {
            System.out.println("Test game driver");
            mainView.setScreen(MainView.Screen.RUN_GAME_SCREEN);
        }

        if (MainView.exit_clicked )
        {
            mainView.closeGame();
        }
        runGame();

    }


    private void runGame() {
        Tank playerTank = new PlayerTank(
                GameState.PLAYER_TANK_ID,
                RunGameView.PLAYER_TANK_INITIAL_X,
                RunGameView.PLAYER_TANK_INITIAL_Y,
                RunGameView.PLAYER_TANK_INITIAL_ANGLE);

        Tank aiTank = new DumbAiTank(
                GameState.AI_TANK_ID,
                RunGameView.AI_TANK_INITIAL_X,
                RunGameView.AI_TANK_INITIAL_Y,
                RunGameView.AI_TANK_INITIAL_ANGLE);

        gameState.addEntity(playerTank);
        gameState.addEntity(aiTank);



        //creates a new entity in the run game screen with set position
        runGameView.addDrawableEntity(
                GameState.PLAYER_TANK_ID,
                RunGameView.PLAYER_TANK_IMAGE_FILE,
                playerTank.getX(),
                playerTank.getY(),
                playerTank.getAngle());
        runGameView.addDrawableEntity(
                GameState.AI_TANK_ID,
                RunGameView.AI_TANK_IMAGE_FILE,
                aiTank.getX(),
                aiTank.getY(),
                aiTank.getAngle());

        Runnable gameRunner = () -> {
            while (update()) {
                runGameView.repaint();
                try {
                    Thread.sleep(8L);
                } catch (InterruptedException exception) {
                    throw new RuntimeException(exception);
                }
            }
        };
        new Thread(gameRunner).start();
    }

    // TODO: Implement.
    // update should handle one frame of gameplay. All tanks and shells move one step, and all drawn entities
    // should be updated accordingly. It should return true as long as the game continues.
    private boolean update() {
        for(Entity entity: gameState.getEntities()){
            entity.move(gameState);
        }

        //Ask all tanks, shells, etc. to move

        //Ask all tanks, shells, etc. to check bounds

        /* for part 2  check collisions*/


        //Ask gamestate -- any new shells to draw?
        //if so call, addDrawableEntity

        //Ask gameState == any shells to remove?
        //if so, call remove DrawableEntity

        for (Entity entity: gameState.getEntities()) {
            runGameView.setDrawableEntityLocationAndAngle(entity.getId(), entity.getX(), entity.getY(), entity.getAngle());
        }
        return true;
    }

    public static void main(String[] args) {
        GameDriver gameDriver = new GameDriver();
        gameDriver.start();
    }
}
