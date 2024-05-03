package inf112.View.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import inf112.View.Scenes.Display;
import inf112.View.ScreenManagement.IScreenFactory;
import inf112.Model.app.MegaMarius;

/**
 * This screen displays the Game over screen for the MegaMarius game
 */
public class ShowGameOver implements Screen, InputHandler {

    private MegaMarius megaMariusGame; //The main game object 
    private Viewport camera; //Viewport for handling screen rendering aspects
    private Stage stage; //Stage to render UI components
    private Texture backgroundImage; //Background image for the game over screen
    private String fileName; //File name indicating the level to restart on
    private IScreenFactory screenSerivce;

    /**
     * Constructs the game over screen and loads the image
     * @param megaMariusGame The main game object.
     * @param fileName Th level file name to restartd on.
     */
    public ShowGameOver(MegaMarius megaMariusGame,String fileName, IScreenFactory screenService) {
        // Initialize variables
        this.megaMariusGame = megaMariusGame;
        this.camera = new FitViewport(MegaMarius.M_Width, MegaMarius.M_Height, new OrthographicCamera());
        this.stage = new Stage(camera, megaMariusGame.getSpriteBatch());
        this.fileName = fileName;
        this.backgroundImage = new Texture("Screens/game-over.png");
        this.screenSerivce = screenService;
    }

    /**
     * Renders the game over screen
     * @param delta The time in seconds since the last frame
     */
    @Override
    public void render(float delta) {
        screenSerivce.clearScreen();
        screenSerivce.drawBackground(backgroundImage, MegaMarius.M_Width, MegaMarius.M_Height);
        handleInput();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }


    //Unused lifecycle methods from the screen interface
    @Override
    public void show() {}
    @Override
    public void resize(int width, int height) {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
    
    /**
     * Disposes of all resources and the image.
     */
    @Override
    public void dispose() {
        stage.dispose();
        backgroundImage.dispose();
    }

    //Methods from inputHandler
    @Override
    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            // Start new game if 'enter' key is pressed
            screenSerivce.showGameScreen(fileName);
            if (fileName.equals("MapAndTileset/level2.tmx")){
                Display.updateLevel(1);
            }
            else if (fileName.equals("MapAndTileset/level3.tmx")){
                Display.updateLevel(2);
            }

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            // Exit game if 'escape' key is pressed
            System.exit(0);
        }
    }

    @Override
    public void checkButtonPress(Vector2 clickPosition, IScreenFactory screenService) {
        //Not used in game over
    }
    
}
