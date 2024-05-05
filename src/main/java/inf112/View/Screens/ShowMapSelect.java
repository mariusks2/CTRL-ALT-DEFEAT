package inf112.View.Screens;

import com.badlogic.gdx.Screen;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import java.util.List;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import inf112.View.Scenes.Display;
import inf112.View.ScreenManagement.IScreenFactory;
import inf112.View.ScreenManagement.ScreenManager;
import inf112.Model.app.MegaMarius;

/**
 * A screen for selecting different game maps in the MegaMarius game.
 * Users can choose from three different maps to play
 */
public class ShowMapSelect implements Screen, InputHandler {

    private MegaMarius megaMariusGame; //Main game object
    private Viewport viewport; //Manages how content is displayed
    private Stage stage; //Stage to manage UI elements
    private Texture backgroundImage; //Background image for the map selection
    private List<String> mapList; //List of available maps

    /**
     * Constructor to initialize the map selection screen with necessary components 
     * @param megaMariusGame Reference to the main game object for screen transitions
     */
    public ShowMapSelect (MegaMarius megaMariusGame){
        this.megaMariusGame=megaMariusGame;
        this.viewport=new FitViewport(MegaMarius.M_Width,MegaMarius.M_Height, new OrthographicCamera());
        this.stage=new Stage(viewport,megaMariusGame.getSpriteBatch());
        this.backgroundImage = new Texture("Screens/mapSelect-screen.png");
        //Add maps here
        this.mapList = new ArrayList<String>();
        mapList.add("MapAndTileset/level1.tmx");
        mapList.add("MapAndTileset/level2.tmx");
        mapList.add("MapAndTileset/level3.tmx");
    }

    /**
     * Renders the map selection screen.
     * @param delta The time in seconds since the last frame.
     */
    @Override
    public void render(float delta) {
        handleInput();
        ScreenManager.getInstance().clearScreen();
        ScreenManager.getInstance().drawBackground(backgroundImage, MegaMarius.M_Width, MegaMarius.M_Height);
        //stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        //stage.draw();
    }

    //Lifecycle methods that are from the screen interface but are not used
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
     * Disposes of the background image and UI elements
     */
    @Override
    public void dispose() {
        stage.dispose();
        backgroundImage.dispose();
    }

   /**
    * Method for getting the next map in the list, and checking if you have completed all the maps
    * @param currentMap The current map on the game
    * @return The next map in the map list
    */
    public String getNextMap (String currentMap){
        int currentIndex = mapList.indexOf(currentMap);
        if (currentIndex+1 <mapList.size()){
            return mapList.get(currentIndex+1);
        }
        else if (currentIndex+1==mapList.size()){
            return "GameCompleted";
        }
        else{
            return mapList.get(0); //if any failures where to arise, we start at the first map again instead of throwing an error.
        }
    }

@Override
public void handleInput() {
    if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
        ScreenManager.getInstance().showScreen("StartGame", new Object[0]);
    }

    if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
        Vector2 clickPosition = new Vector2(Gdx.input.getX(),Gdx.input.getY());
        viewport.unproject(clickPosition);
        checkButtonPress(clickPosition);
    }
}

@Override
public void checkButtonPress(Vector2 clickPosition) {
    //Define back arrow
    Rectangle backBound = new Rectangle(5,190,39,14);
    //Defines the bounds where the first map is defined, and the text box below is defined
    Rectangle map1 = new Rectangle(151,133,100,52);
    Rectangle map1Text = new Rectangle(186,124,35,5);

    //Defines the bounds where the second map is defined and the text box below is defined
    Rectangle map2 = new Rectangle(79,65,99,52);
    Rectangle map2Text = new Rectangle(95,55,67,5);

    //Defines the bounds where the third map is defined and the text box below is defined
    Rectangle map3 = new Rectangle(224,65,100,51);
    Rectangle map3Text = new Rectangle(254,55,47,5);

    // Defines the bouds where the scoreboard button is defined.
    Rectangle scoreboardButton = new Rectangle(173, 17, 58, 12);

    // Check if the click is within the bounds of any of the rectangles
    //Here we could use a for loop for the map list for better implementation with more maps
    if (backBound.contains(clickPosition)) {
        ScreenManager.getInstance().showScreen("StartGAme", new Object[0]);
    }
    else if (map1.contains(clickPosition) || map1Text.contains(clickPosition)){
        ScreenManager.getInstance().showScreen("ShowGame", mapList.get(0));
    }
    else if (map2.contains(clickPosition)  || map2Text.contains(clickPosition)){
        ScreenManager.getInstance().showScreen("ShowGame", new Object[]{mapList.get(1)});
        Display.updateLevel(1);
    }
    else if (map3.contains(clickPosition) || map3Text.contains(clickPosition)){
       ScreenManager.getInstance().showScreen("ShowGame", new Object[]{mapList.get(2)});
       Display.updateLevel(2);
    }
    else if (scoreboardButton.contains(clickPosition)) {
        megaMariusGame.setScreen(megaMariusGame.getScoreboardScreen());
        dispose();
    }
}
    
}
