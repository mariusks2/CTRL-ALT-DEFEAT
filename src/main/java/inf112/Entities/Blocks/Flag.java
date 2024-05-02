package inf112.Entities.Blocks;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import inf112.Entities.InteractiveTileObj;
import inf112.View.Screens.ShowGame;
import inf112.skeleton.app.Marius;
import inf112.skeleton.app.MegaMarius;

public class Flag extends InteractiveTileObj{

    public Flag(ShowGame screen, MapObject object) {
        super(screen, object);
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
