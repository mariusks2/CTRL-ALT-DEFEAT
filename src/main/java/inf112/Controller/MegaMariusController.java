package inf112.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import inf112.Model.app.Marius;

/**
 * Class for handeling movement of marius.
 */
public class MegaMariusController implements MegaMariusControllable {

    // Variables
    private Marius player;

    /**
     * Creates a MegaMariusController object with player as paramterer
     * 
     * @param player
     */
    public MegaMariusController(Marius player) {
        this.player = player;
    }

    @Override
    public void handlePlayerMovement() {
         //control our player using immediate impulses
        if (player.currentState != Marius.State.DEAD) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP) || (Gdx.input.isKeyJustPressed(Input.Keys.W)))
                player.jump();
            if ((Gdx.input.isKeyPressed(Input.Keys.RIGHT) ^ (Gdx.input.isKeyPressed(Input.Keys.D)) && player.b2body.getLinearVelocity().x <= 2 ))
                player.b2body.applyLinearImpulse(new Vector2(0.05f, 0), player.b2body.getWorldCenter(), true);
            if ((Gdx.input.isKeyPressed(Input.Keys.LEFT) ^ (Gdx.input.isKeyPressed(Input.Keys.A)) && player.b2body.getLinearVelocity().x >= -2))
                player.b2body.applyLinearImpulse(new Vector2(-0.05f, 0), player.b2body.getWorldCenter(), true);
        }
    }
}
