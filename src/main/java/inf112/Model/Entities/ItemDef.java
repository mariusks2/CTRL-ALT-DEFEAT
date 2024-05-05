package inf112.Model.Entities;

import com.badlogic.gdx.math.Vector2;

/**
 * Class for defining items.
 */
public class ItemDef {
    public Vector2 position;
    public Class<?> type;
    
    /**
     * Constructor to define item, used for pepsi item
     * @param position the vector position on where to define the item
     * @param type the class type to define
     */
    public ItemDef(Vector2 position, Class<?> type){
        this.position = position;
        this.type = type;
    }
}
