package inf112.Model.Entities.Blocks;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import inf112.Model.Entities.InteractiveTileObj;
import inf112.Model.app.Marius;
import inf112.Model.app.MegaMarius;


public class Flag extends InteractiveTileObj{

    public Flag(World world, TiledMap map, MapObject object) {
        super(world, map, object);
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape pShape = new PolygonShape();
        setCategoryFilter(MegaMarius.FLAG_BIT);
        pShape.setAsBox(bounds.getWidth() / 2 / MegaMarius.PPM, bounds.getHeight() / 2 / MegaMarius.PPM);
        fixtureDef.shape = pShape;
        fixture = body.createFixture(fixtureDef);
    }


    @Override
    public void HeadHit(Marius marius) {
    }
}
