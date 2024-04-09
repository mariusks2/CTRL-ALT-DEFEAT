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

public class ShowStartGame implements Screen {

    private Game game;
    private Viewport viewport;
    private Stage stage;
    private Label.LabelStyle fontStyle;
    private Table table;

    public ShowStartGame(Game game) {
        this.game = game;
        this.viewport = new FitViewport(MegaMarius.M_Width, MegaMarius.M_Height, new OrthographicCamera());
        this.stage = new Stage(viewport, ((MegaMarius) game).batch);
        this.fontStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        this.table = new Table();

        createStartScreen(stage, table, fontStyle);
    }

    private void createStartScreen(Stage stage, Table table, LabelStyle fontStyle) {
        table.center();
        table.setFillParent(true);

        Label welcomeLabel = new Label("Welcome to Super Mario", fontStyle);
        Label startLabel = new Label("Press 'Enter' to start or 'Escape' to exit", fontStyle);

        table.add(welcomeLabel).expandX();
        table.row();
        table.add(startLabel).expandX().padTop(10f);

        stage.addActor(table);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            // Transition to main game screen
            game.setScreen(new ShowGame((MegaMarius) game));
            dispose();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            // Exit the game
            dispose();
            Gdx.app.exit();
        } else {
            // Draw start screen
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
            stage.draw();
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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
        stage.dispose();
    }
}
