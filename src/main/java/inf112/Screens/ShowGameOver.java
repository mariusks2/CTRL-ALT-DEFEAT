package inf112.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import inf112.skeleton.app.MegaMarius;

public class ShowGameOver implements Screen {

    private Game game;
    private Viewport camera;
    private Stage handler;
    private LabelStyle font;
    private Table table;

    public ShowGameOver(Game game) {
        // Initialize variables
        this.game = game;
        camera = new FitViewport(MegaMarius.M_Width, MegaMarius.M_Height, new OrthographicCamera());
        handler = new Stage(camera, ((MegaMarius) game).batch);
        font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        table = new Table();

        // Create game over label
        createGameOverScreen(handler, table, font);
    }

    public void createGameOverScreen(Stage stage, Table table, LabelStyle font) {
        // Initialize tabel
        table.center();
        table.setFillParent(true);

        // Creating labels
        Label gameOverLabel = new Label("Game Over", font);
        Label retryLabel = new Label("Press 'Enter' to retry or 'Escape' to exit", font);

        // Adding labels to table
        table.add(gameOverLabel).expandX();
        table.row();
        table.add(retryLabel).expandX().padTop(10f);

        // Adding actor to stage
        stage.addActor(table);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            // Start new game if 'enter' key is pressed
            game.setScreen(new ShowGame((MegaMarius) game));
            dispose();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            // Exit game if 'escape' key is pressed
            dispose();
            System.exit(0);
        } else {
            // draw game over screen if none of the above statements are true
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            handler.draw();
        }
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
        handler.dispose();
    }
    
}
