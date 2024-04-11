package inf112.Screen;

import com.badlogic.gdx.math.Vector2;

public class ItemDef {
    public Vector2 positon;
    public Class<?> type;

    public ItemDef(Vector2 position, Class<?> type){
        this.positon = position;
        this.type = type;
    }
}
