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


public class showAboutScreen implements Screen {

    private Game game;
    private Viewport camera;
    private Stage stage;
    private LabelStyle font;
    private Table table;

    public showAboutScreen (Game game){
        this.game = game;
        this.camera = new FitViewport(MegaMarius.M_Width, MegaMarius.M_Height, new OrthographicCamera());
        this.stage = new Stage(camera,((MegaMarius) game).batch);
        this.font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        this.table = new Table();

        createAboutScreen(stage, table, font);
    }

    private void createAboutScreen (Stage stage, Table table, LabelStyle font){
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
    public void render(float delta) {
        MegaMarius megaMariusGame = (MegaMarius) game;
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            dispose();
            game.setScreen(new ShowStartGame(megaMariusGame));
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
        stage.dispose();
    }
    
}
