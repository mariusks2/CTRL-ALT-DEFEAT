package inf112.Model.Entities;

import com.badlogic.gdx.math.Vector2;

public class ItemDef {
    public Vector2 positon;
    public Class<?> type;
    
    /**
     * Constructor to define item, used for pepsi item
     * @param position the vector position on where to define the item
     * @param type the class type to define
     */
    public ItemDef(Vector2 position, Class<?> type){
        this.positon = position;
        this.type = type;
    }
}
