package inf112.Model.Entities;

import inf112.Model.Entities.Blocks.Pessi;
import inf112.Model.Factory.GenericFactory;
/**
 * Factory for creating the different items
 */
public class ItemFactory extends GenericFactory<Item> {
    /**
     * Constructor for item factory to add the different items
     */
    public ItemFactory() {
        addType("Pessi", Pessi.class);
    }
}