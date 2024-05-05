package inf112.Model.Entities.Blocks;

import inf112.Model.Entities.InteractiveTileObj;
import inf112.Model.Factory.GenericFactory;

public class BlockFactory extends GenericFactory<InteractiveTileObj> {
    public BlockFactory() {
        //Adds the different block types to the factory
        addType("Brick", Brick.class);
        addType("Coin", Coin.class);
        addType("Flag", Flag.class);
    }
}