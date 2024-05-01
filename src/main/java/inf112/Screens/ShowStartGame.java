package inf112.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.MegaMarius;

/**
 * This screen represents the starting screen of the MegaMarius game,
 * and handles navigatipn to different screens like map selection, help screen and the about screen
 */
public class ShowStartGame implements Screen {

    private MegaMarius megaMariusGame; //Reference to the main game object for transition between screens
    private Viewport viewport; //Manages how content is displayed
    private Stage stage; //Stage to hold UI elements for this screen
    private Texture backgroundImage; //Background image for the start screen

    /**
     * Constructor that initializes the start screen with necessary components
     * @param megaMariusGame The main game object to enable screen changes
     */
    public ShowStartGame(MegaMarius megaMariusGame) {
        this.megaMariusGame = megaMariusGame;
        this.viewport = new FitViewport(MegaMarius.M_Width, MegaMarius.M_Height, new OrthographicCamera());
        this.stage = new Stage(viewport, megaMariusGame.getSpriteBatch());
        this.backgroundImage = new Texture("src/main/resources/Screens/start-screen.png");
    }
   
    /**
     * Renders the start screen and handles user input
     * @param delta The time in seconds since the last frame was rendered
     */
    @Override
    public void render(float delta) {
        handleInput();
        ScreenManager.getInstance().clearScreen();
        ScreenManager.getInstance().drawBackground(backgroundImage, MegaMarius.M_Width, MegaMarius.M_Height);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    /**
     * Handles user inputs such as keyboard presses and mouse clicks for navigation between screens
     */
    private void handleInput(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            ScreenManager.getInstance().showScreen("MapSelect", new showMapSelect(megaMariusGame));
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            Vector2 clickPosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(clickPosition);
            checkButtonPress(clickPosition);
        }
    }

    /**
     * Checks the position of a mouse click and responds by navigating to the correct screen.
     * @param clickPosition The screen coordinates of the mouse click
     */
    private void checkButtonPress(Vector2 clickPosition){
        //Define buttons bounds
        //Button for startgame
        Rectangle startGameBounds = new Rectangle(169,38,56,10);
        //button for help screen
        Rectangle helpBounds = new Rectangle(160,16,35,10);
        //Button for about screen
        Rectangle aboutBounds = new Rectangle(199,14,39,10);

        if (startGameBounds.contains(clickPosition)){
            ScreenManager.getInstance().showScreen("MapSelect", new showMapSelect(megaMariusGame));
        }
        else if (helpBounds.contains(clickPosition)){
            ScreenManager.getInstance().showScreen("Help", new showHelpScreen(megaMariusGame));
        }
        else if (aboutBounds.contains(clickPosition)){
            ScreenManager.getInstance().showScreen("About", new showAboutScreen(megaMariusGame));
        }
    }
    
    //Lifecycle methods from the screen interface which are not used
    @Override
    public void show() {}
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}

    /**
     * Disposes of resources like the stage and textures.
     */
    @Override
    public void dispose() {
        stage.dispose();
        backgroundImage.dispose();
    }
}
