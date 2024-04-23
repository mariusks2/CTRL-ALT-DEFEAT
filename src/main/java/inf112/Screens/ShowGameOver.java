package inf112.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import inf112.skeleton.app.MegaMarius;

public class ShowGameOver implements Screen {

    private Game game;
    private Viewport camera;
    private Stage stage;
    private Texture backgroundImage;
    private String fileName;

    public ShowGameOver(Game game, String fileName) {
        // Initialize variables
        this.game = game;
        this.camera = new FitViewport(MegaMarius.M_Width, MegaMarius.M_Height, new OrthographicCamera());
        this.stage = new Stage(camera, ((MegaMarius) game).batch);
        this.fileName = fileName;
        this.backgroundImage = new Texture("Screens/game-over.png");
    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        MegaMarius megaMariusGame = (MegaMarius) game;
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the background image
        megaMariusGame.batch.begin();
        megaMariusGame.batch.draw(backgroundImage, 0, 0, MegaMarius.M_Width, MegaMarius.M_Height);
        megaMariusGame.batch.end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            // Start new game if 'enter' key is pressed
            game.setScreen(new ShowGame((MegaMarius) game, fileName));
            dispose();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            // Exit game if 'escape' key is pressed
            dispose();
            System.exit(0);
        }
    }

    public String getCurrentGame() {
        if(fileName == "custom1.tmx"){
            System.out.println("GAME DONE");
        }
        return "custom1.tmx";
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
        backgroundImage.dispose();
    }
    
}
