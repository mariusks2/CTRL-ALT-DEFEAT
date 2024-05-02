package inf112.Entities;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import inf112.Screens.ShowGame;
import inf112.skeleton.app.Marius;
import inf112.skeleton.app.MegaMarius;

/** 
 * Abstract class for interactive tile objects
 * 
 * This class provides the functionality neccesary to 
 * interact with objects like coin and brick
 * 
 * @author CTRL-ALT-DEFEAT
 * @version 1.0
 * @since 2024-02
*/
public abstract class InteractiveTileObj {
    protected World world;
    protected TiledMap map;
    protected Rectangle bounds;
    protected Body body;
    protected ShowGame screen;
    protected MapObject object;

    protected Fixture fixture;

    /**
     * Constructor for interactivetileobj
     * 
     * @param screen Game screen
     * @param object The Map
     */
    public InteractiveTileObj(ShowGame screen, MapObject object){
        this.object = object;
        this.screen = screen;
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.bounds = ((RectangleMapObject) object).getRectangle();

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape pShape = new PolygonShape();
        //Define shape and position of tiles.
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((bounds.getX() + bounds.getWidth() / 2) / MegaMarius.PPM, (bounds.getY() + bounds.getHeight() / 2) / MegaMarius.PPM);

        body = world.createBody(bodyDef);

        pShape.setAsBox(bounds.getWidth() / 2 / MegaMarius.PPM, bounds.getHeight() / 2 / MegaMarius.PPM);
        fixtureDef.shape = pShape;
        fixture = body.createFixture(fixtureDef);
    }

    public abstract void HeadHit(Marius marius);

    /**
     * Function for setting category filter
     * 
     * @param filterbit the category filter to set to blocks
     */
    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }
    /**
     * Function for getting every cell in layer 1
     * 
     */
    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        return layer.getCell((int)(body.getPosition().x * MegaMarius.PPM / 16), (int)(body.getPosition().y * MegaMarius.PPM / 16));
    }
        /**
     * Function for getting every cell in layer 1 but the one above, 
     * used for putting coins above coin blocks on headhit
     * 
     */
    //Get a cellsfrom layer 1 but the one above the cell.
    public TiledMapTileLayer.Cell getCellAbove(){
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        return layer.getCell((int)(body.getPosition().x * MegaMarius.PPM / 16), (int)(body.getPosition().y * MegaMarius.PPM / 16)+1);
    }

    //for testing
    public Filter getFilterData(){
        return fixture.getFilterData();
    }
}
