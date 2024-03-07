package inf112.Screen.Marius;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;

import inf112.Scenes.Display;
import inf112.Screens.ShowGame;

public class Flag extends InteractiveTileObj{
    private final int FLAG = 314;
    private static TiledMapTileSet tileSet;

    public Flag(ShowGame screen, MapObject object) {
        super(screen, object);
        tileSet = map.getTileSets().getTileSet("tileset1");
        fixture.setUserData(this);
        tileSet.getTile(FLAG);
    }

    @Override
    public void onHeadHit() {
        Display.updateScore(1000);
    }
    
}
