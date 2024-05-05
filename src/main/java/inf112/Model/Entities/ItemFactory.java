package inf112.Model.Entities;

import inf112.Model.Entities.Blocks.Pessi;
import inf112.Model.Factory.GenericFactory;

public class ItemFactory extends GenericFactory<Item> {
    public ItemFactory() {
        addType("Pessi", Pessi.class);
    }
}