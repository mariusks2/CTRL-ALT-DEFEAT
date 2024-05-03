package inf112.Model.Factory;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

import inf112.Model.Entities.InteractiveTileObj;
import inf112.Model.Entities.Item;
import inf112.Model.Entities.Enemies.Enemy;
import inf112.Model.World.GameWorldManager;


/**
 * Interface  for creating objects like Enemies, bricks and items
 */
public interface IEntityFactory {
    /**
     * Function to create block
     * @param type type of block
     * @param world the world
     * @param map the map
     * @param object the collision blocks
     * @param worldManager the world manager
     * @returns a new block
     */
    public InteractiveTileObj createBlock(String type,World world, TiledMap map ,MapObject object, GameWorldManager worldManager);

    /**
     * Function to create enemy
     * @param type type of enemy
     * @param world the world
     * @param atlas the texture
     * @param x position x
     * @param y position y
     * @return new enemy
     */
    public Enemy createEnemy(String type, World world, TextureAtlas atlas, float x, float y);

    /**
     * Function to create item
     * @param type type of item
     * @param world the world
     * @param atlas the texture
     * @param x position x
     * @param y position y
     * @return new item
     */
    public Item createItem(String type, World world,TextureAtlas atlas, float x, float y);
}
