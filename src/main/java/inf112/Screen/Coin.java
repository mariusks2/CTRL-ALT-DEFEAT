package inf112.Screen;
import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Vector2;

import inf112.Scenes.Display;
import inf112.Screens.ShowGame;
import inf112.skeleton.app.Marius;
import inf112.skeleton.app.MegaMarius;

public class Coin extends InteractiveTileObj{
    private static TiledMapTileSet tileSet;
    private final int BLANK_COIN = 4;
    private final int COIN = 58;

    public Coin(ShowGame screen, MapObject object){
        super(screen, object);
        tileSet = map.getTileSets().getTileSet("tileset1"); 
        fixture.setUserData(this);
        setCategoryFilter(MegaMarius.COIN_BIT); //Set the block to Coin bit.
    }

    @Override
    public void HeadHit(Marius marius) {
        if(getCell().getTile().getId() != BLANK_COIN){
            getCell().setTile(tileSet.getTile(BLANK_COIN)); //Set the graphic block to Blank Coin
            Display.updateScore(200); //Add score
            if(object.getProperties().containsKey("pepsi")){
                screen.spawnItems(new ItemDef(new Vector2(body.getPosition().x, body.getPosition().y + 16/MegaMarius.PPM), Pepsi.class));
            }
            else if(object.getProperties().containsKey("coin")){
                getCellA().setTile(tileSet.getTile(COIN)); //
                getCellA().setTile(null); // Set tile to null (removes the block from map)
                Display.updateScore(200); // Update score
            }
             
        }
    }
    public TiledMapTileLayer.Cell getCellA(){
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(8);
        layer.setVisible(true);
        return layer.getCell((int)(body.getPosition().x * MegaMarius.PPM / 16),
                (int)((body.getPosition().y) * MegaMarius.PPM / 16+1));
    }

    public TiledMapTileLayer.Cell getCellA(){
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        System.out.println((int)(body.getPosition().y * MegaMarius.PPM / 16+1));
        return layer.getCell((int)(body.getPosition().x * MegaMarius.PPM / 16),
        (int)(body.getPosition().y * MegaMarius.PPM / 16+1));
    }

    public TiledMapTileLayer.Cell getCellA(){
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        System.out.println((int)(body.getPosition().y * MegaMarius.PPM / 16+1));
        return layer.getCell((int)(body.getPosition().x * MegaMarius.PPM / 16),
        (int)(body.getPosition().y * MegaMarius.PPM / 16+1));
    }
}
