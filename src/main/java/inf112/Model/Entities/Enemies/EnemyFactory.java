package inf112.Model.Entities.Enemies;

import inf112.Model.Factory.GenericFactory;

/**
 * Enemy factory for creating the different enemies in the game
 */
public class EnemyFactory extends GenericFactory<Enemy> {
    /**
     * Constructor for adding the different enemies to the game
     */
    public EnemyFactory() {
        // Adds the enemy types to the enemyfactory
        addType("Spider", Spider.class);
        addType("Turtle", Turtle.class);
    }
}

