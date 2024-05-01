package inf112.Entities.Blocks;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

import inf112.Entities.InteractiveTileObj;
import inf112.Entities.ItemDef;
import inf112.Scenes.Display;
import inf112.Screens.ShowGame;
import inf112.skeleton.app.Marius;
import inf112.skeleton.app.MegaMarius;

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
            if(object.getProperties().containsKey("pepsi")){ //if the property is pepsi, spawn pepsi. if not spawn a coin
                screen.spawnItems(new ItemDef(new Vector2(body.getPosition().x, body.getPosition().y + 16/MegaMarius.PPM), Pepsi.class));
            }
            else {
                getCellAbove().setTile(tileSet.getTile(COIN));
            }
             
        }
    }
}
