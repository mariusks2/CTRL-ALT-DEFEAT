package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class actorMarius extends Actor{
    Texture style;
    float gravity;
    float size;
    float speed;

    public actorMarius(Texture style, float width, float height){
        this.style = style;
    }

    @Override
    public void act(float deltaTime){

    }
}
