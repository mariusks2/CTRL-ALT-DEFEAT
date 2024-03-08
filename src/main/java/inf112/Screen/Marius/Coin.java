package inf112.Screen.Marius;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;

import inf112.Scenes.Display;
import inf112.Screens.ShowGame;
import inf112.skeleton.app.MegaMarius;

public class Coin extends InteractiveTileObj{
    private static TiledMapTileSet tileSet;
    private final int BLANK_COIN = 4;

    public Coin(ShowGame screen, MapObject object){
        super(screen, object);
        tileSet = map.getTileSets().getTileSet("tileset1"); 
        fixture.setUserData(this);
        setCategoryFilter(MegaMarius.COIN_BIT); //Set the block to Coin bit.
    }

    @Override
    public void HeadHit() {
        getCell().setTile(tileSet.getTile(BLANK_COIN)); //Set the graphic block to Blank Coin
        Display.updateScore(200); //Add score
    }
    
}
