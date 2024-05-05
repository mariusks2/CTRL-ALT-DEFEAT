package inf112.Model.World;

import java.util.concurrent.LinkedBlockingQueue;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import inf112.Controller.MegaMariusControllable;
import inf112.Controller.MegaMariusController;
import inf112.Model.Entities.Item;
import inf112.Model.Entities.ItemDef;
import inf112.Model.Entities.ItemFactory;
import inf112.Model.Entities.Blocks.Pessi;
import inf112.Model.Entities.Enemies.Enemy;
import inf112.Model.Factory.IFactory;
import inf112.Model.MakeMap.MakeMap;
import inf112.Model.app.Marius;
import inf112.Model.app.MegaMarius;

/**
 * Class for managing game world.
 */
public class GameWorldManager implements IGameWorldManager{

    // Variables
    private World world;
    private MakeMap creator;
    private LinkedBlockingQueue<ItemDef> itemsToSpawn;
    private Array<Item> items;
    private TiledMap map;
    private TmxMapLoader mapLoader; //Loads the chosen map
    private Marius player;
    private MegaMariusControllable playerController;
    private TextureAtlas atlas;
    private float accumulator = 0f; //Fixed timestep in game loop
    private float stepTime = 1/60f; //Timestep
    private IFactory <Item> itemFactory;

    /**
     * Constructs a GameWorldManger object with given parameters.
     * 
     * @param filename
     * @param atlas
     */
    public GameWorldManager(String filename, TextureAtlas atlas) {
        this.world = new World(new Vector2(0, -10), true);
        world.step(0, 0, 0);
        this.mapLoader = new TmxMapLoader();
        this.map = mapLoader.load(filename);
        this.creator = new MakeMap(world, map, atlas,this);
        this.itemsToSpawn = new LinkedBlockingQueue<ItemDef>();
        this.items = new Array<>();
        this.atlas = atlas;
        this.itemFactory = new ItemFactory();
    }

    /**
     * Initialize player and create a controller object.
     * @param player
     */
    public void setPlayer(Marius player) {
        this.player = player;
        this.playerController = new MegaMariusController(player);
    }

    @Override
    public void update(float dt) {

        playerController.handlePlayerMovement();
        handleSpawningItems();
        
        accumulator += Math.min(dt, 0.25f);

        
        while (accumulator >= stepTime) {
            world.step(stepTime, 6, 2);
            accumulator -= stepTime;
        }

        player.update(dt);
        for(Enemy enemy : creator.getEnemies()){
            enemy.update(dt);
            if (enemy.getX() < player.getX() + 276/MegaMarius.PPM) {
                enemy.b2body.setActive(true);
            }
        }
        for(Item item : items){
            if(!item.isDestroyed()) item.update(dt);
        }
    }

   

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public void handleSpawningItems() {
         if(!itemsToSpawn.isEmpty()){
            ItemDef itemDef = itemsToSpawn.poll();
            if(itemDef.type == Pessi.class){
                items.add(itemFactory.create("Pessi", new Object[]{world, atlas, itemDef.position.x,itemDef.position.y }));
            }
        }
    }

    public void dispose() {
        world.dispose();
        map.dispose();
        atlas.dispose();
    }

    /**
     * Method that returns enemies.
     * @return creator.getEnemies()
     */
    public Array<Enemy> getEnemies() {
        return creator.getEnemies();
    }
    
    /**
     * Method that returns items
     * @return items
     */
    public Array<Item> getItems() {
        return items;
    }

    /**
     * Method that returns the map
     * @return map
     */
    public TiledMap getMap(){
        return map;
    }

    /**
     * Method that adds itemDef to list of itemsToSpawn.
     */
    @Override
    public void spawnItems(ItemDef itemDef) {
        itemsToSpawn.add(itemDef);
    }    
}
