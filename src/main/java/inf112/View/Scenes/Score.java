package inf112.View.Scenes;

public class Score {

    private int time;
    private int score;
    private int level;

    public Score(int time, int score, int level) {
        this.time = time;
        this.score = score;
        this.level = level;
    }

    public int getTime() {
        return this.time;
    }
    
    public int getScore() {
        return this.score;
    }

    public int getLevel() {
        return this.level;
    }
}
