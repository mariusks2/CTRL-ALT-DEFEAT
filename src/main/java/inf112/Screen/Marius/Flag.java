package inf112.Screen.Marius;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;

import inf112.Screens.ShowGame;
import inf112.Screens.ShowGameOver;
import inf112.skeleton.app.MegaMarius;

public class Flag extends InteractiveTileObj{
    private final int FLAG = 314;
    private static TiledMapTileSet tileSet;

    public Flag(ShowGame screen, MapObject object) {
        super(screen, object);
        fixture.setUserData(this);
        setCategoryFilter(MegaMarius.FLAG_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Flag", "Collision");
        Object test = map.getTileSets().getTileSet("tileset1").getTile(FLAG).getOffsetX();
        System.out.println(test);
        MegaMarius game = ShowGame.getGame();
        game.setScreen(new ShowGameOver(game));
    }
}
