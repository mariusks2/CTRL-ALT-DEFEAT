package inf112.Entities.Blocks;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import inf112.Entities.Item;
import inf112.Screens.ShowGame;
import inf112.skeleton.app.Marius;
import inf112.skeleton.app.MegaMarius;

/** 
 * Represents a Coin block with Pepsi inside
 * 
 * This class provieds functionality neccesary to hit 
 * coin blocks and give out a pessi
 * 
 * @author CTRL-ALT-DEFEAT
 * @version 1.0
 * @since 2024-02
*/
public class Pessi extends Item{
    FixtureDef fdef;

    /**
     * Constructor for Pepsi
     * @param screen game screen
     * @param x position x
     * @param y position y
     */
    public Pessi(ShowGame screen, float x, float y){
        super(screen, x, y);
        setRegion(screen.getAtlas().findRegion("pessi"));
        velocity = new Vector2(0.6f, 0);
    } 

    /**
     * Function for defining the pessi.
     * Defines the pessi after a headhit on a coin block with pessi as a property.
     */
    @Override
    public void defineItem() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / MegaMarius.PPM);
        fdef.filter.categoryBits = MegaMarius.ITEM_BIT;
        fdef.filter.maskBits = MegaMarius.MARIUS_BIT | MegaMarius.OBJECT_BIT | MegaMarius.GROUND_BIT | MegaMarius.BRICK_BIT | MegaMarius.COIN_BIT;
    

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }

    /**
     * Function for "drinking" a pessi.
     * checks if you are big marius, if not, you grow.
     * @param Marius the playable character
     */
    @Override
    public void use(Marius marius) {
        destroy();
        if(!marius.isMariusBigNow()) marius.grow();
    }
    /**
     * Function for updating
     * @param dt deltatime for updating. 
     */
    @Override
    public void update(float dt){
        super.update(dt);
        setCenter(b2body.getPosition().x, b2body.getPosition().y);
        velocity.y = b2body.getLinearVelocity().y;
        b2body.setLinearVelocity(velocity);
    }
    /**
     * Function for getting category bits
     * used for testing. 
     */
    public short getCatergoryBits(){
        return fdef.filter.categoryBits;
    }
}
