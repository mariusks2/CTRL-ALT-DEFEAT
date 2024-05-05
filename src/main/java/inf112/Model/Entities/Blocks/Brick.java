package inf112.Model.Entities.Blocks;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

import inf112.Model.Entities.InteractiveTileObj;
import inf112.View.Scenes.Display;
import inf112.Model.app.Marius;
import inf112.Model.app.MegaMarius;

/** 
 * Represents a simple Brick.
 * 
 * This class provieds functionality neccesary to break 
 * bricks if the correct conditions are met.
 * 
 * @author CTRL-ALT-DEFEAT
 * @version 1.0
 * @since 2024-02
*/
public class Brick extends InteractiveTileObj{

    private AssetManager manager;
    private Music music;

    /**
     * Constructs a Brick object with given screen and map object.
     * 
     * @param world the world
     * @param map the map
     * @param object the collision blocks
     */
    public Brick(World world, TiledMap map, RectangleMapObject object){
        super(world,map ,object);
        fixture.setUserData(this);
        setCategoryFilter(MegaMarius.BRICK_BIT); //Set the block to Brick bit.
        manager = new AssetManager();
        manager.load("audio/music/brick.wav", Music.class);
        manager.finishLoading();
    }

    /**
     * Checks if Marius is big when his head collides with brick.
     * 
     * If Marius is big, then he is allowed to break the brick block
     * when his head collides with it.
     * 
     * @param marius The Marius object. Must not be null.
     */
    @Override
    public void HeadHit(Marius marius) {
        if(marius.isMariusBigNow()){
            music = manager.get("audio/music/brick.wav", Music.class);
			music.setVolume(0.050f);
			music.play(); // Comment this out to stop music from playing
            setCategoryFilter(MegaMarius.DESTROYED_BIT); // Set the block to Destroyed bit.
            getCell().setTile(null); // Set tile to null (removes the block from map)
            Display.updateScore(200); // Update score
        }
    }
}
