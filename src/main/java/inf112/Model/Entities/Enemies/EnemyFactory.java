package inf112.Model.Entities.Enemies;

import inf112.Model.Factory.GenericFactory;

public class EnemyFactory extends GenericFactory<Enemy> {
    public EnemyFactory() {
        // Adds the enemy types to the enemyfactory
        addType("Spider", Spider.class);
        addType("Turtle", Turtle.class);
    }
}

