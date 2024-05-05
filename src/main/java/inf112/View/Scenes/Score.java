package inf112.View.Scenes;

/**
 * Class for creating scores
 */
public class Score {

    // Variables
    private int time;
    private int score;
    private int level;

    /**
     * Creates as Score object using the below parameters
     * 
     * @param time
     * @param score
     * @param level
     */
    public Score(int time, int score, int level) {
        this.time = time;
        this.score = score;
        this.level = level;
    }

    /**
     * Method that returns time
     * @return time
     */
    public int getTime() {
        return this.time;
    }
    
    /**
     * Method that returns score
     * @return score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Method that returns level
     * @return level
     */
    public int getLevel() {
        return this.level;
    }
}
