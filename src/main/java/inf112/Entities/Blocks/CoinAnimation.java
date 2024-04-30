package inf112.Entities.Blocks;
import com.badlogic.gdx.physics.box2d.BodyDef;

import inf112.Entities.Item;
import inf112.Screens.ShowGame;
import inf112.skeleton.app.Marius;

public class CoinAnimation extends Item{
    private int timer = 0;

    public CoinAnimation(ShowGame screen, float x, float y){
        super(screen, x, y);
        setRegion(screen.getAtlas().findRegion("coin"));
    } 

    @Override
    public void use(Marius marius) {
    }
    
    @Override
    public void update(float dt){
        if (!destroyed) {
            super.update(dt);
           ;
            setCenter(b2body.getPosition().x, b2body.getPosition().y);
            if(timer>30) destroy();
            else timer++;
        }
    }

    @Override
    public void defineItem() {
        BodyDef bdef = new BodyDef();
        if (bdef != null){
            bdef.position.set(getX(), getY());
            bdef.type = BodyDef.BodyType.DynamicBody;
            b2body = world.createBody(bdef);
        }
    }
}
