package inf112.Screen.Marius;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;

import inf112.Screens.ShowGame;
import inf112.skeleton.app.Marius;
import inf112.skeleton.app.MegaMarius;

public class Coin extends InteractiveTileObj{
    private static TiledMapTileSet tileSet;
    private final int BLANK_COIN = 28;

    public Coin(ShowGame screen, MapObject object){
        super(screen, object);
        tileSet = map.getTileSets().getTileSet("tileset_gutter");
        fixture.setUserData(this);
        setCategoryFilter(MegaMarius.COIN_BIT);
    }

    @Override
    public void onHeadHit(Marius mario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onHeadHit'");
    }
    
}
