package inf112.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;

import inf112.Scenes.Display;
import inf112.Screens.ShowGame;
import inf112.skeleton.app.Marius;
import inf112.skeleton.app.MegaMarius;

/** 
 * Represents a simple Brick.
 * 
 * This class provieds functionality neccesary break 
 * bricks if the correct conditions are met.
 * 
 * @author CTRL-ALT-DEFEAT
 * @version 1.0
 * @since 2024-02
*/
public class Brick extends InteractiveTileObj{

    /**
     * Constructs a Brick object with given screen and map object.
     * 
     * @param screen Game screen
     * @param object Map object
     */
    public Brick(ShowGame screen, MapObject object){
        super(screen, object);
        fixture.setUserData(this);
        setCategoryFilter(MegaMarius.BRICK_BIT); //Set the block to Brick bit.
    }

    /**
     * Checks if Marius is big when his head collides with brick.
     * 
     * If Marius is big, then he is allowed to break the brick block
     * when his head collides with it.
     * 
     * @param marius The Marius object. Must not be null.
     * @throws NullPointerExpetion If Marius is null.
     */
    @Override
    public void HeadHit(Marius marius) {

        if(marius.isMariusBigNow()){
            Gdx.app.log("Brick", "Collision"); // Console logging
            setCategoryFilter(MegaMarius.DESTROYED_BIT); // Set the block to Destroyed bit.
            getCell().setTile(null); // Set tile to null (removes the block from map)
            Display.updateScore(200); // Update score
        }
        else {
            // Marius is not big
        }
    }
}
