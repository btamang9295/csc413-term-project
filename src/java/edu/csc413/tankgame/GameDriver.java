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
public class GameDriver{
    // TODO: Implement.
    // Add the instance variables, constructors, and other methods needed for this class. GameDriver is the centerpiece
    // for the tank game, and should store and manage the other components (i.e. the views and the models). It also is
    // responsible for running the game loop.
    private final MainView mainView;
    private final RunGameView runGameView;
    private final GameState gameState;
    private final ActionMenu actionMenu;


    public GameDriver() {
        actionMenu = new ActionMenu();
        mainView = new MainView(actionMenu);
        runGameView = mainView.getRunGameView();
        gameState = new GameState();
    }

    public void start() {
        // TODO: Implement.
        // This should set the MainView's screen to the start menu screen.
        mainView.setScreen(MainView.Screen.RUN_GAME_SCREEN);
        runGame();

    }

    private void runGame() {
        Tank playerTank = new PlayerTank(
                GameState.PLAYER_TANK_ID,
                3,
                RunGameView.PLAYER_TANK_INITIAL_X,
                RunGameView.PLAYER_TANK_INITIAL_Y,
                RunGameView.PLAYER_TANK_INITIAL_ANGLE);

        Tank aiTank = new DumbAiTank(
                GameState.AI_TANK_ID,
                3,
                RunGameView.AI_TANK_INITIAL_X,
                RunGameView.AI_TANK_INITIAL_Y,
                RunGameView.AI_TANK_INITIAL_ANGLE);

        Tank cushionAiTank = new CushionAiTank(
                        GameState.CUSHION_AI_TANK_ID,
                        3,
                        RunGameView.AI_TANK_2_INITIAL_X,
                        RunGameView.AI_TANK_2_INITIAL_Y,
                        RunGameView.AI_TANK_2_INITIAL_ANGLE);

        WallImageInfo.readWalls();
        for (int i = 0; i < WallImageInfo.readWalls().size(); i++)
        {
            String walls = "wall-" + i ;
            Wall wall = new Wall(walls, 3,
                    WallImageInfo.readWalls().get(i).getX(),
                    WallImageInfo.readWalls().get(i).getY(),
                    0
                    );
            gameState.addEntity(wall);
            runGameView.addDrawableEntity(walls,
                    WallImageInfo.readWalls().get(i).getImageFile(),
                    wall.getX(),
                    wall.getY(),
                    0);
        }


        gameState.addEntity(playerTank);
        gameState.addEntity(aiTank);
        gameState.addEntity(cushionAiTank);

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
        runGameView.addDrawableEntity(
                GameState.CUSHION_AI_TANK_ID,
                RunGameView.CUSHION_AI_TANK_IMAGE_FILE,
                cushionAiTank.getX(),
                cushionAiTank.getY(),
                cushionAiTank.getAngle());

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
            //gameState.addTempShell(entity);
            entity.move(gameState);
            entity.checkBounds(gameState);
            entity.checkLives(gameState);

            //entity.checkBounds(gameState) is inside the move()
        }

        for (Entity entity : gameState.getEntities()) {
            for (Entity entity2 : gameState.getEntities()) //handling collisions
            {
                if (gameState.entitiesOverlap(entity, entity2)) {
                    if (!entity.getId().equals(entity2.getId())) {
                        handleCollision(entity, entity2);
                        //System.out.println("collided");
                    }
                }
            }
        }

        for (Entity newShellEntities: gameState.getShellEntities()) { // adding new shells
            runGameView.addDrawableEntity(
                    newShellEntities.getId(),
                    RunGameView.SHELL_IMAGE_FILE,
                    newShellEntities.getX(),
                    newShellEntities.getY(),
                    newShellEntities.getAngle());
            gameState.addEntity(newShellEntities);
        }
        gameState.clearShellEntity();
        /*
        *Do the same thing, but for removal.
        * Use another list of entities that need to be removed
        * (instead of removing them from the entities list directly),
        * and after collisions are handled,
        * go through that list and remove them from entities and from the RunGameView.Regards,Dawson
         */
    for( Entity entity: gameState.getRemovableEntity())
    {
        gameState.removeEntity(entity);
        runGameView.removeDrawableEntity(entity.getId());
    }
    gameState.clearRemovableEntity();
        // TODO: Ask gameState == any shells to remove?
        // TODO: if so, call removeDrawableEntity
        for (Entity entity: gameState.getEntities()) {
            runGameView.setDrawableEntityLocationAndAngle(entity.getId(), entity.getX(), entity.getY(), entity.getAngle());
        }
        return true;
    }

    public static void main(String[] args) {
        GameDriver gameDriver = new GameDriver();
        gameDriver.start();
    }

   public  class ActionMenu implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        if (actionCommand.equals(StartMenuView.START_BUTTON_ACTION_COMMAND)) {
            mainView.setScreen(MainView.Screen.RUN_GAME_SCREEN);
            System.out.println("Start Game");
        } else if (actionCommand.equals(StartMenuView.EXIT_BUTTON_ACTION_COMMAND)) {
            System.out.println("exit");
            mainView.closeGame();
        }
    }
}

    private void handleCollision(Entity entity1, Entity entity2){
        if (entity1 instanceof Tank && entity2 instanceof Tank)
        {
            tankVsTank(entity1, entity2);
        }
        else if (entity1 instanceof Wall && entity2 instanceof Tank)
        {
            tankVsWall(entity2, entity1);
        }
         else if (entity1 instanceof Shell && entity2 instanceof Wall)
        {
            shellVsWall(entity1, entity2);
        }
        else if (entity1 instanceof  Wall && entity2 instanceof Shell)
        {
            shellVsWall(entity2, entity1);
        }
//        else if ( entity1 instanceof  Tank && entity2 instanceof Shell)
//        {
//            tankVsShell(entity1, entity2);
//        }
//        else if (entity1 instanceof  Shell && entity2 instanceof Tank)
//        {
//            tankVsShell(entity2, entity1);
//        }
    }


    private void tankVsTank(Entity tankA, Entity tankB )
    {
        double value1 = tankA.getXBound() - tankB.getX();
        double value2 = tankB.getXBound() - tankA.getX();
        double value3 = tankA.getYBound() - tankB.getY();
        double value4 = tankB.getYBound() - tankA.getY();

        if (value1 < value2 && value1 < value3 && value1 < value4)
        {
            tankA.setX((tankA.getX()) - value1 / 2);
            tankB.setX((tankB.getX()) + value1 / 2) ;
        }
        else if (value2 < value1 && value2 < value3 && value2 < value4)
        {
            tankA.setX((tankA.getX()) + value2 / 2) ;
            tankB.setX((tankB.getX()) - value2 / 2) ;
        }
        else if (value3 < value4 && value3 < value1 && value3 < value2)
        {
            tankA.setY((tankA.getY()) - value3 / 2);
            tankB.setY((tankB.getY()) + value3 / 2);
        }
        else if ( value4 < value3 && value4 < value1 && value4 < value2)
        {
            tankA.setY((tankA.getY()) + value4 / 2);
            tankB.setY((tankB.getY()) - value4 / 2);
        }
    }

    private void tankVsWall (Entity entity1, Entity entity2)
    {
        double value1 = entity1.getXBound() - entity2.getX();
        double value2 = entity2.getXBound() - entity1.getX();
        double value3 = entity1.getYBound() - entity2.getY();
        double value4 = entity2.getYBound() - entity1.getY();

        if (value1 < value2 && value1 < value3 && value1 < value4)
        { entity1.setX ( entity1.getX() - value1);}
        else if (value2 < value1 && value2 < value3 && value3 < value4)
        { entity1.setX(entity1.getX() + value2); }
        else if (value3 < value4 && value3 < value1 && value3 < value2)
        { entity1.setY(entity1.getY() - value3); }
        else if ( value4 < value3 && value4 < value1 && value4 < value2)
        { entity1.setY(entity1.getY() + value4); }
    }
//    private void tankVsShell (Entity tank, Entity shell )
//    {
//        runGameView.removeDrawableEntity(shell.getId());
//        runGameView.addAnimation(RunGameView.SHELL_EXPLOSION_ANIMATION,
//                RunGameView.SHELL_EXPLOSION_FRAME_DELAY,
//                shell.getX(),
//                shell.getY());
//        gameState.removeEntity(shell);
//    }

    private void shellVsWall (Entity shell, Entity wall)
    {


        gameState.addRemovableEntity(wall);
        gameState.addRemovableEntity(shell);
        runGameView.addAnimation(RunGameView.SHELL_EXPLOSION_ANIMATION,
                RunGameView.SHELL_EXPLOSION_FRAME_DELAY,
                shell.getX(),
                shell.getY());
    }
}
