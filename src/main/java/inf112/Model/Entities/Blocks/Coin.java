package inf112.Model.Entities.Blocks;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import inf112.Model.Entities.InteractiveTileObj;
import inf112.Model.Entities.ItemDef;
import inf112.Model.World.GameWorldManager;
import inf112.Model.app.Marius;
import inf112.Model.app.MegaMarius;
import inf112.View.Scenes.Display;

/** 
 * Represents a Coin block
 * 
 * This class provieds functionality neccesary to hit 
 * coin blocks and change that block.
 * 
 * @author CTRL-ALT-DEFEAT
 * @version 1.0
 * @since 2024-02
*/
public class Coin extends InteractiveTileObj{
    private static TiledMapTileSet tileSet;
    private final int BLANK_COIN = 28;
    private final int COIN = 66;
    private AssetManager manager;
    private Music music;
    private GameWorldManager worldManager;

    /**
     * Constructor for coin
     * @param world the world
     * @param map the map
     * @param object the collision blocks
     * @param worldManager the world manager
     */
    public Coin(World world,TiledMap map, RectangleMapObject object, GameWorldManager worldManager){
        super(world, map, object);
        this.worldManager = worldManager;
        tileSet = map.getTileSets().getTileSet("customtileset");
        fixture.setUserData(this);
        setCategoryFilter(MegaMarius.COIN_BIT); //Set the block to Coin bit.
        manager = new AssetManager();
        manager.load("audio/music/coin.wav", Music.class);
        manager.load("audio/music/pessi.wav", Music.class);
        manager.finishLoading();
    }
    
    /**
     * Function for headhit on a coin block
     * @param Marius The playable character
     */
    @Override
    public void HeadHit(Marius marius) {
        if(getCell().getTile().getId() != BLANK_COIN){
			

            getCell().setTile(tileSet.getTile(BLANK_COIN)); //Set the graphic block to Blank Coin
            
            Display.updateScore(200); //Add score
            if(object.getProperties().containsKey("pessi")){ //if the property is pessi, spawn pessi. if not spawn a coin
                worldManager.spawnItems(new ItemDef(new Vector2(body.getPosition().x, body.getPosition().y + 16/MegaMarius.PPM), Pessi.class));
                music = manager.get("audio/music/pessi.wav", Music.class);
                music.setVolume(0.12f);
                music.play(); // Comment this out to stop music from playing
            }
            else {
                Display.updateCoin(1);
                music = manager.get("audio/music/coin.wav", Music.class);
                music.setVolume(0.04f);
                music.play(); // Comment this out to stop music from playing
                getCellAbove().setTile(tileSet.getTile(COIN));
            }
        }
    }
}
