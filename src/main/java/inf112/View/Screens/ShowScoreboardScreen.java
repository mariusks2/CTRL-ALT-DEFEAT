package inf112.View.Screens;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import inf112.Model.app.MegaMarius;
import inf112.View.Scenes.Score;
import inf112.View.ScreenManagement.ScreenManager;

/**
 * Class that creates a scorebaord.
 */
public class ShowScoreboardScreen implements Screen, InputHandler {

    // Variables
    private Texture backgroundImage;
    private ArrayList<Score> scoreboardList;
    private Viewport viewport;
    private Stage stage;
    private BitmapFont font;
    private MegaMarius game;

    /**
     * Creates a scoreboard screen object with given paramter.
     * 
     * @param megaMariusGame
     */
    public ShowScoreboardScreen(MegaMarius megaMariusGame) {
        this.scoreboardList = new ArrayList<Score>();
        this.backgroundImage = new Texture("Screens/scoreboard-screen.png");
        this.viewport = new FitViewport(MegaMarius.M_Width, MegaMarius.M_Height, new OrthographicCamera());
        this.stage = new Stage(viewport,megaMariusGame.getSpriteBatch());
        this.game = megaMariusGame;
        this.font = new BitmapFont();
    }

    /**
     * Creates a new score and adds it ot scoreboard list.
     * 
     * @param time
     * @param score
     * @param level
     */
    public void createNewScore(int time, int score, int level) {
        this.scoreboardList.add(new Score(time, score, level));
    }

    /**
     * Method that returns list of scores for given level.
     * 
     * @param scores
     * @param level
     * @return scoresForLevel
     */
    public ArrayList<Integer> getHighScores(ArrayList<Score> scores, int level) {
        ArrayList<Integer> scoresForLevel = new ArrayList<>();
        for (Score score : scores) {
            if (score.getLevel() == level) {
                scoresForLevel.add(score.getTime());
            }
        }
        return scoresForLevel;
    }

    /**
     * Method that draws the score for given scores list, and draws at the given x and y position.
     * 
     * @param scores
     * @param x
     * @param y
     */
    public void drawScores(ArrayList<Integer> scores, int x, int y) {
        if (scores.isEmpty()) {
            font.draw(stage.getBatch(), "No scores available", x, y);
            return;
        }

        int count = 0;
        if (scores.size() <= 5) {
            count = scores.size();
        } else {
            count = Math.min(scores.size(), 5);
        }

        Collections.sort(scores, Comparator.reverseOrder());
        for (int i = 0; i < count; i++) {
            font.draw(stage.getBatch(), (i + 1) + ". Time: " + scores.get(i), x, y - (i + 1) * 20);
        }
    }

    /**
     * Draws the scoreboard.
     */
    public void drawScoreboard() {
        backgroundImage = new Texture("Screens/scoreboard-screen.png");
        ScreenManager.getInstance().drawBackground(backgroundImage, MegaMarius.M_Width, MegaMarius.M_Height);
        ArrayList<Integer> scores_level_one = getHighScores(scoreboardList, 1);
        ArrayList<Integer> scores_level_two = getHighScores(scoreboardList, 2);
        ArrayList<Integer> scores_level_three = getHighScores(scoreboardList, 3);

        stage.getBatch().begin();
        font.getData().setScale(0.5f);
        font.draw(stage.getBatch(), "Top 5: Level 1:", 100, 150);
        drawScores(scores_level_one, 100, 140);

        font.draw(stage.getBatch(), "Top 5: Level 2:", 175, 150);
        drawScores(scores_level_two, 175, 140);

        font.draw(stage.getBatch(), "Top 5: Level 3:", 250, 150);
        drawScores(scores_level_three, 250, 140);
        stage.getBatch().end();
        this.backgroundImage = new Texture("Screens/scoreboard-screen.png");
        
    }

    @Override
    public void render(float delta) {
        ScreenManager.getInstance().clearScreen();
        ScreenManager.getInstance().drawBackground(backgroundImage, MegaMarius.M_Width, MegaMarius.M_Height);
        drawScoreboard();
        handleInput();
        //stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        //stage.draw();
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        backgroundImage.dispose();
    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            ScreenManager.getInstance().showScreen("MapSelect", new Object[]{game});
        }
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            Vector2 clickPosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(clickPosition);
            checkButtonPress(clickPosition);
            
        }
        dispose();
    }

    @Override
    public void checkButtonPress(Vector2 clickPosition) {
         // Defines the bounding box where back arrow is located
         Rectangle backBounds = new Rectangle(6, 197,35 , 8);
         if (backBounds.contains(clickPosition)) {
             ScreenManager.getInstance().showScreen("MapSelect", new Object[]{game});
         }
    }
}
