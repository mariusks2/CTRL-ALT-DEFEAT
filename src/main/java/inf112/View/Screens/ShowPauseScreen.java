package inf112.View.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import inf112.Model.app.Marius;
import inf112.Model.app.Marius.State;
import inf112.Model.app.MegaMarius;
import inf112.View.ScreenManagement.ScreenManager;


/**
 * Screen for handling the pause functionality of the game
 */
public class ShowPauseScreen implements Screen, InputHandler{

    private MegaMarius game; //Reference to the main game instance for scree transitions
    private Marius player; //Reference to the player character to manage it's state when pausing the game
    private Viewport viewport; //Manages how content is displayed
    private Stage stage; //Stage for holding UI elements for the screen
    private Texture backgroundImage; //Backgroundimage of the pause screen
    private State previousState; //The previous state of the player before the game was paused


    /**
     * Constructor that initializes the pause screen with necessary components. 
     * @param game The main game instance
     * @param player The player instance to manage state
     * @param state The previous state of the player before pausing
     */
    public ShowPauseScreen(MegaMarius game, Marius player, State state){
        this.game = game;
        this.player = player;
        this.viewport = new FitViewport(MegaMarius.M_Width, MegaMarius.M_Height, new OrthographicCamera());
        this.stage = new Stage(viewport,game.getSpriteBatch());
        this.backgroundImage = new Texture("Screens/pause-screen.png");
        this.previousState = state;
    }

    /**
     * Renders the pause screen and handles user input in the pause screen
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


    //Lifecycle methods form the implemenation of screen that are not used
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
     * Disposes of resources like stages and textures
     */
    @Override
    public void dispose() {
        stage.dispose();
        backgroundImage.dispose();
    }

    @Override
    public void handleInput() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            player.setCurrentState(previousState);
            game.setScreen(ScreenManager.getInstance().getShowGameScreen());
        }
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            Vector2 clickPosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(clickPosition);
            checkButtonPress(clickPosition);
        }
    }

    @Override
    public void checkButtonPress(Vector2 clickPosition) {
        //Defining resume game bounds
        Rectangle resumeBound = new Rectangle(165,141,66,7);

        //Defining back to map selection button
        Rectangle mapSelectionBound = new Rectangle(150,114,111,7);

        //Defining quit game bound
        Rectangle quitGameBound = new Rectangle(176,84,49,6);

        if(resumeBound.contains(clickPosition)){
            player.setCurrentState(previousState);
            game.setScreen(ScreenManager.getInstance().getShowGameScreen());
        }
        else if (mapSelectionBound.contains(clickPosition)){
            ScreenManager.getInstance().showScreen("MapSelect", new Object[]{game});
        }
        else if(quitGameBound.contains(clickPosition)){
            Gdx.app.exit();
        }
    }
    
}
