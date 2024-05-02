package inf112.Model.Entities.Blocks;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Vector2;
import inf112.Model.Entities.InteractiveTileObj;
import inf112.Model.Entities.ItemDef;
import inf112.View.Scenes.Display;
import inf112.View.Screens.ShowGame;
import inf112.Model.app.Marius;
import inf112.Model.app.MegaMarius;

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

    /**
     * Constructor for coin
     * @param screen Game screen
     * @param object The map
     */
    public Coin(ShowGame screen, MapObject object){
        super(screen, object);
        tileSet = map.getTileSets().getTileSet("customtileset");
        fixture.setUserData(this);
        setCategoryFilter(MegaMarius.COIN_BIT); //Set the block to Coin bit.
        manager = new AssetManager();
        manager.load("audio/music/coin.wav", Music.class);
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
                screen.spawnItems(new ItemDef(new Vector2(body.getPosition().x, body.getPosition().y + 16/MegaMarius.PPM), Pessi.class));
                music = manager.get("audio/music/coin.wav", Music.class);
                music.setVolume(0.04f);
                music.play(); // Comment this out to stop music from playing
            }
            else {
                music = manager.get("audio/music/coin.wav", Music.class);
                music.setVolume(0.04f);
                music.play(); // Comment this out to stop music from playing
                getCellAbove().setTile(tileSet.getTile(COIN));
            }
        }
    }
}
