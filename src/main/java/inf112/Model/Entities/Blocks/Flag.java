package inf112.Model.Entities.Blocks;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import inf112.Model.Entities.InteractiveTileObj;
import inf112.Model.app.Marius;
import inf112.Model.app.MegaMarius;

/** 
 * Represents the flag
 * 
 * This class provieds functionality neccesary to hit 
 * the flag
 * 
 * @author CTRL-ALT-DEFEAT
 * @version 1.0
 * @since 2024-02
*/
public class Flag extends InteractiveTileObj{

    /**
     * Constructor for flag
     * @param world Game screen
     * @param map The map
     * @param object The collision object
     */
    public Flag(World world, TiledMap map, RectangleMapObject object) {
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
    } //not used 
}
