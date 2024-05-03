package inf112.Model.World;

import com.badlogic.gdx.physics.box2d.World;

import inf112.Model.Entities.ItemDef;

public interface IGameWorldManager {

    public void update(float dt);
    /**
     * Queues an item to be spawned into the game world
     * @param itemDef The definition of the item to be spawned
     */
    public void spawnItems(ItemDef itemDef);
    /**
     * Method for returning the current game world
     * @return the current game world
     */
    public World getWorld();
    /**
     * Handles the creation and addition of items to the game world from the spaw queue.
     */
    public void handleSpawningItems();
    /**
     * Method for disposing UI elements
     */
    public void dispose();
}
