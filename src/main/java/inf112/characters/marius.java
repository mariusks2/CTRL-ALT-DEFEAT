package inf112.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class marius extends character{
    private Texture characterStyle;
    private Rectangle spriteRect;
    
    public marius(float x, float y){
        super(x, y, null);
        characterStyle = new Texture(Gdx.files.internal("MARIUS.png"));
		spriteRect = new Rectangle(x, y, characterStyle.getWidth() / 2, characterStyle.getHeight() / 2);
    }

    

}
