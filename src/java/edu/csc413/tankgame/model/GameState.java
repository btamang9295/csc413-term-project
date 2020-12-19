package edu.csc413.tankgame.model;

import edu.csc413.tankgame.view.RunGameView;

import java.util.ArrayList;
import java.util.List;

/**
 * GameState represents the state of the game "world." The GameState object tracks all of the moving entities like tanks
 * and shells, and provides the controller of the program (i.e. the GameDriver) access to whatever information it needs
 * to run the game. Essentially, GameState is the "data context" needed for the rest of the program.
 */
public class GameState {
    public static final double TANK_X_LOWER_BOUND = 30.0;
    public static final double TANK_X_UPPER_BOUND = RunGameView.SCREEN_DIMENSIONS.width - 100.0;
    public static final double TANK_Y_LOWER_BOUND = 30.0;
    public static final double TANK_Y_UPPER_BOUND = RunGameView.SCREEN_DIMENSIONS.height - 120.0;

    public static final double SHELL_X_LOWER_BOUND = -10.0;
    public static final double SHELL_X_UPPER_BOUND = RunGameView.SCREEN_DIMENSIONS.width;
    public static final double SHELL_Y_LOWER_BOUND = -10.0;
    public static final double SHELL_Y_UPPER_BOUND = RunGameView.SCREEN_DIMENSIONS.height;

    public static final String PLAYER_TANK_ID = "player-tank";
    public static final String AI_TANK_ID = "ai-tank";
    public static final String CUSHION_AI_TANK_ID = "cushion-ai";
    public static final String WALL_IMAGE_FILE_PREFIX = "wall-";


    // TODO: Feel free to add more tank IDs if you want to support multiple AI tanks! Just make sure they're unique.

    // TODO: Implement.
    // There's a lot of information the GameState will need to store to provide contextual information. Add whatever
    // instance variables, constructors, and methods are needed.
    public static boolean upPressed;
    public static  boolean downPressed;
    public static boolean leftPressed;
    public static boolean rightPressed;
    public static  boolean shootPressed;




    private final List<Entity> entities = new ArrayList <> ();
    public void addEntity(Entity tank){
        entities.add(tank);
    }
    public List<Entity> getEntities(){
        return entities;
    }
    public void removeEntity(Entity entity){
            entities.remove(entity);
    }

    public Entity getEntity(String id)
    {
        for(Entity entity: getEntities())
        {
            if(entity.getId().equals(id))
            {
                return entity;
            }
        }
        return null;
    }


    private final List <Entity> shellEntities = new ArrayList<>();
    public void addShellEntity(Entity shell)
    {
        shellEntities.add(shell);
        //entities.add(shell);
    }
    public void removeShellEntity(Entity shell)
    {
            shellEntities.remove(shell);
    }
    public void clearShellEntity ()
    {
        shellEntities.clear();
    }
    public List<Entity> getShellEntities(){
        return shellEntities;
    }



    private final List <Entity> removableEntity = new ArrayList<>();

    public void addRemovableEntity(Entity entity)
    { removableEntity.add(entity); }

    public List<Entity> getRemovableEntity()
    { return removableEntity; }

    public void removeRemovableEntity(Entity shell)
    { removableEntity.remove(shell); }

    public void  clearRemovableEntity()
    {
        removableEntity.clear();
    }




    public boolean entitiesOverlap(Entity entity1, Entity entity2)
    {
        return entity1.getX() < entity2.getXBound()
                && entity1.getXBound() > entity2.getX()
                && entity1.getY() < entity2.getYBound()
                && entity1.getYBound() > entity2.getY();
    }


    public static void holdUpPressed(){
        upPressed = true;
    }
     public static void holdDownPressed()
     {
         downPressed = true;
     }
     public static void holdLeftPressed()
     {
         leftPressed = true;
     }
     public static void holdRightPressed()
     {
         rightPressed = true;
     }
     public static void holdShootPressed()
     {
         shootPressed = true;
     }
}
