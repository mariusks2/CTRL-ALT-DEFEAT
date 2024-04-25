package inf112.Screens;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.ui.List;

import inf112.Scenes.Score;

public class ShowScoreboardScreen {

    private ArrayList<Score> scoresLevelOne;
    private ArrayList<Score> scoresLevelTwo;

    private ArrayList<Score> scoreboardLevelOne;
    private ArrayList<Score> scoreboardLevelTwo;

    private Score score;

    public ShowScoreboardScreen() {
        this.scoresLevelOne = new ArrayList<Score>();
        this.scoresLevelTwo = new ArrayList<Score>();
        this.scoreboardLevelOne = new ArrayList<Score>();
        this.scoreboardLevelTwo = new ArrayList<Score>();
    }


    public void createNewScore(int time, int score, int level, String username) {
        this.score = new Score(time, score, level, username);
        ArrayList<Score> scoreboardList = getScoresLevel(level);
        getHighScoreObjects(scoreboardList, level);
    }

    public ArrayList<Score> getScoresLevel(int level) {
        if (level == 1)
            return this.scoresLevelOne;
        else if (level == 2)
            return this.scoresLevelTwo;
        else
            return null; 
    }

    public ArrayList<Score> getScoreboardLevel(int level) {
        if (level == 1)
            return this.scoreboardLevelOne;
        else if (level == 2)
            return this.scoreboardLevelTwo;
        else
            return null; 
    }

    public ArrayList<Score> getHighScoreObjects(ArrayList<Score> list, int level) {
        Score one = null;
        Score two = null;
        Score three = null;

        for (int i = 0; i < list.size(); i++) {
            Score temp = list.get(i);
            int tempTime = temp.getTime();
            if(tempTime > one.getTime())
                one = temp;
            else if(tempTime > two.getTime())
                two = temp;
            else if(tempTime > three.getTime());
                three = temp;
        }
        ArrayList<Score> topTime = getScoreboardLevel(level);
        topTime.add(one);
        topTime.add(two);
        topTime.add(three);
        return topTime;
    }
}
