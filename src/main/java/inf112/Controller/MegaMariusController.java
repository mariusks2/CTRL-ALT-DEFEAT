package inf112.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.Marius;

public class MegaMariusController implements MegaMariusControllable {

    private Marius player;

    public MegaMariusController(Marius player) {
        this.player = player;
    }

    @Override
    public void handlePlayerMovement(float delta) {
         //control our player using immediate impulses
        if (player.currentState != Marius.State.DEAD) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
                player.jump();
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2)
                player.b2body.applyLinearImpulse(new Vector2(0.05f, 0), player.b2body.getWorldCenter(), true);
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2)
                player.b2body.applyLinearImpulse(new Vector2(-0.05f, 0), player.b2body.getWorldCenter(), true);
        } 
    } 
}
