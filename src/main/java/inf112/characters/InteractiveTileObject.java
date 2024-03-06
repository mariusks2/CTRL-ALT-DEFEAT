package inf112.characters;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.app.MegaMarius;

public abstract class InteractiveTileObject {
    protected World world;
    protected TiledMap map;
    protected Rectangle bounds;
    protected Body body;
    protected MapObject object;

    protected Fixture fixture;

    public InteractiveTileObject(World world, TiledMap map, Rectangle bounds){
        this.bounds=bounds;
        this.world = world;
        this.map=map;

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape polyShape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth()/2) / MegaMarius.PPM, (bounds.getY() + bounds.getHeight()/2)/MegaMarius.PPM);

        body = world.createBody(bdef);

        polyShape.setAsBox(bounds.getWidth()/2 / MegaMarius.PPM, bounds.getHeight()/2/MegaMarius.PPM);
        fdef.shape = polyShape;
        fixture = body.createFixture(fdef);

    }

    public abstract void headContact();

    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer mapLayer = (TiledMapTileLayer) map.getLayers().get(1);
        return mapLayer.getCell( (int) (body.getPosition().x * MegaMarius.PPM/16), (int)(body.getPosition().y*MegaMarius.PPM/16));
    }
}
