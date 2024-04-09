package inf112.Screen;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import inf112.Screens.ShowGame;
import inf112.skeleton.app.Marius;
import inf112.skeleton.app.MegaMarius;

public class Pepsi extends Item{

    public Pepsi(ShowGame screen, float x, float y){
        super(screen, x, y);
        setRegion(screen.getAtlas().findRegion("pepsi"));
        velocity = new Vector2(0.7f, 0);
    } 

    @Override
    public void defineItem() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / MegaMarius.PPM);
        fdef.filter.categoryBits = MegaMarius.ITEM_BIT;
        fdef.filter.maskBits = MegaMarius.MARIUS_BIT | MegaMarius.OBJECT_BIT | MegaMarius.GROUND_BIT | MegaMarius.BRICK_BIT | MegaMarius.COIN_BIT;
    

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }

    @Override
    public void use(Marius marius) {
        destroy();
        marius.grow();
    }
    
    @Override
    public void update(float dt){
        super.update(dt);
        setCenter(b2body.getPosition().x, b2body.getPosition().y);
        velocity.y = b2body.getLinearVelocity().y;
        b2body.setLinearVelocity(velocity);
    }
}
