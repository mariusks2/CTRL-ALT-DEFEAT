package inf112.Scenes;

public class Score {

    private int time;
    private int score;
    private int level;
    private String username;

    public Score(int time, int score, int level, String username) {
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
    
    public String getUsername() {
        return this.username;
    }
}
