package inf112.Screen.Marius;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class Coin extends InteractiveTileObj{
    public Coin(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);
    }
}
