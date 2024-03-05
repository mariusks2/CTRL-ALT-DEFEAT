package inf112.Screen.Marius;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.app.MegaMarius;

public class Marius extends Sprite{
    public World world;
    public Body b2body;

    public Marius(World world){
        this.world = world;
        defineMarius();
    }

    public void defineMarius(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32/ MegaMarius.PPM, 32/ MegaMarius.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5/ MegaMarius.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
