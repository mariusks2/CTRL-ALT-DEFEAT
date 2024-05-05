package inf112.Model.Entities.Blocks;

import inf112.Model.Entities.InteractiveTileObj;
import inf112.Model.Factory.GenericFactory;
/**
 * Block factory for creating the different block objects
 */
public class BlockFactory extends GenericFactory<InteractiveTileObj> {
    /**
     * Constructor for adding the different blocks to the factory
     */
    public BlockFactory() {
        addType("Brick", Brick.class);
        addType("Coin", Coin.class);
        addType("Flag", Flag.class);
    }
}