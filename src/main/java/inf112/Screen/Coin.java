package inf112.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Vector2;

import inf112.Scenes.Display;
import inf112.Screens.ShowGame;
import inf112.skeleton.app.Marius;
import inf112.skeleton.app.MegaMarius;

public class Coin extends InteractiveTileObj{
    private static TiledMapTileSet tileSet;
    private final int BLANK_COIN = 4;

    public Coin(ShowGame screen, MapObject object){
        super(screen, object);
        tileSet = map.getTileSets().getTileSet("customtileset");
        fixture.setUserData(this);
        setCategoryFilter(MegaMarius.COIN_BIT); //Set the block to Coin bit.
    }

    @Override
    public void HeadHit(Marius marius) {
        System.out.println(map.getTileSets());
        if(getCell().getTile().getId() != BLANK_COIN){
            getCell().setTile(tileSet.getTile(BLANK_COIN)); //Set the graphic block to Blank Coin
            Display.updateScore(200); //Add score
            if(object.getProperties().containsKey("pepsi")){
                screen.spawnItems(new ItemDef(new Vector2(body.getPosition().x, body.getPosition().y + 16/MegaMarius.PPM), Pepsi.class));
            }
            else {
                screen.spawnItems(new ItemDef(new Vector2(body.getPosition().x, body.getPosition().y + 16/MegaMarius.PPM), CoinAnimation.class));
            }
             
        }
    }
}
