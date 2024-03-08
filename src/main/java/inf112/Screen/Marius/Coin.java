package inf112.Screen.Marius;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;

import inf112.Scenes.Display;
import inf112.Screens.ShowGame;
import inf112.Screens.ShowGameOver;
import inf112.skeleton.app.MegaMarius;

public class Coin extends InteractiveTileObj{
    private static TiledMapTileSet tileSet;
    private final int BLANK_COIN = 28;

    public Coin(ShowGame screen, MapObject object){
        super(screen, object);
        tileSet = map.getTileSets().getTileSet("tileset1");
        fixture.setUserData(this);
        setCategoryFilter(MegaMarius.COIN_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Coin", "Collision");
        setCategoryFilter(MegaMarius.COIN_BIT);
        getCell().setTile(tileSet.getTile(BLANK_COIN));
        Display.updateScore(200);
    }
    
}
