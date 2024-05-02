package inf112.Screens;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Collections;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import inf112.Scenes.Score;
import inf112.skeleton.app.MegaMarius;

public class ShowScoreboardScreen implements Screen {

    private MegaMarius megaMariusGame;
    private Texture backgroundImage;
    private ArrayList<Score> scoreboardList;
    private Viewport viewport;
    private Stage stage;
    private SpriteBatch batch;
    private BitmapFont font;

    public ShowScoreboardScreen(MegaMarius megaMariusGame) {
        this.megaMariusGame = megaMariusGame;
        this.scoreboardList = new ArrayList<Score>();
        this.backgroundImage = new Texture("Screens/scoreboard-screen.png");
        this.viewport = new FitViewport(MegaMarius.M_Width, MegaMarius.M_Height, new OrthographicCamera());
        this.stage = new Stage(viewport,megaMariusGame.getSpriteBatch());
        this.font = new BitmapFont();
    }


    public void createNewScore(int time, int score, int level) {
        this.scoreboardList.add(new Score(time, score, level));
    }

    public ArrayList<Integer> getHighScores(ArrayList<Score> scores, int level) {
        ArrayList<Integer> scoresForLevel = new ArrayList<>();
        for (Score score : scores) {
            if (score.getLevel() == level) {
                scoresForLevel.add(score.getTime());
            }
        }
        return scoresForLevel;
    }

    public void drawScores(ArrayList<Integer> scores, int x, int y) {
        if (scores.isEmpty()) {
            font.draw(stage.getBatch(), "No scores available", x, y);
            return;
        }

        int count = 0;
        System.out.println(scores.size());
        if (scores.size() <= 5) {
            count = scores.size();
        } else {
            count = Math.min(scores.size(), 5);
        }
        for (int i = 0; i < count; i++) {
            font.draw(stage.getBatch(), (i + 1) + ". Time: " + scores.get(i), x, y - (i + 1) * 20);
        }
    }

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


    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            ScreenManager.getInstance().showScreen("MapSelect", new showMapSelect(megaMariusGame));
            dispose();
        }
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
}
