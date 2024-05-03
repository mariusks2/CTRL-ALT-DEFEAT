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

import inf112.View.ScreenManagement.IScreenFactory;
import inf112.Model.app.MegaMarius;

/**
 * This screen provides help to players on how to play the game
 */
public class ShowHelpScreen implements Screen, InputHandler{

    private MegaMarius megaMariusGame; //Main game object
    private Viewport viewport; //Viewport for handling screen rendering
    private Stage stage; //Stage to render UI components
    private Texture backgroundImage; //Background image for the hekp screen
    private IScreenFactory screenService;

    /**
     * Constructor to initialize the help screen and loading in the backgroundimage
     * @param megaMariusGame The main game object to enable screen changes
     */
    public ShowHelpScreen (MegaMarius megaMariusGame, IScreenFactory screenService){
        this.megaMariusGame=megaMariusGame;
        this.viewport=new FitViewport(MegaMarius.M_Width,MegaMarius.M_Height, new OrthographicCamera());
        this.stage=new Stage(viewport,megaMariusGame.getSpriteBatch());
        this.backgroundImage = new Texture("Screens/help-screen.png");
        this.screenService = screenService;
    }

    /**
     * Renders the help screen
     * @param delta The time in seconds since the last fram
     */
    @Override
    public void render(float delta) {
        handleInput();
        screenService.clearScreen();
        screenService.drawBackground(backgroundImage, MegaMarius.M_Width, MegaMarius.M_Height);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }
    public void renderTest(){
        handleInput();
        screenService.clearScreen();
        screenService.drawBackground(backgroundImage, MegaMarius.M_Width, MegaMarius.M_Height);
    }

    //Lifecycle methods that are part of the Screen interface but are not used
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
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            screenService.showStartGame();
        }
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            Vector2 clickPosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(clickPosition);
            checkButtonPress(clickPosition, screenService);
        }
    }

    @Override
    public void checkButtonPress(Vector2 clickPosition, IScreenFactory screenService) {
         // Defines the bounding box where back arrow is located
         Rectangle backBounds = new Rectangle(3, 190,43 ,12 );
         if (backBounds.contains(clickPosition)) {
             screenService.showStartGame();
         }
    }
    
}
