package inf112.Screens;

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

import inf112.Scenes.Display;
import inf112.skeleton.app.MegaMarius;

/**
 * A screen for selecting different game maps in the MegaMarius game.
 * Users can choose from three different maps to play
 */
public class showMapSelect implements Screen {

    private MegaMarius megaMariusGame; //Main game object
    private Viewport viewport; //Manages how content is displayed
    private Stage stage; //Stage to manage UI elements
    private Texture backgroundImage; //Background image for the map selection
    private List<String> mapList; //List of available maps

    /**
     * Constructor to initialize the map selection screen with necessary components 
     * @param megaMariusGame Reference to the main game object for screen transitions
     */
    public showMapSelect (MegaMarius megaMariusGame){
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
        handleInputs();
        ScreenManager.getInstance().clearScreen();
        ScreenManager.getInstance().drawBackground(backgroundImage, MegaMarius.M_Width, MegaMarius.M_Height);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    /**
     * Handles user input for navigating the map selection
     */
    private void handleInputs(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            ScreenManager.getInstance().showScreen("StartGame", new ShowStartGame(megaMariusGame));
        }

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            Vector2 clickPosition = new Vector2(Gdx.input.getX(),Gdx.input.getY());
            viewport.unproject(clickPosition);
            handleClickInput(clickPosition);
        }
    }
    
    /**
     * Handles mouse click inputs on the map selection screen
     * @param clickPosition The position of the click in screen coordinates
     */
    private void handleClickInput(Vector2 clickPosition){
        //Define back arrow
        Rectangle backBound = new Rectangle(6,197,25,8);
        //Defines the bounds where the first map is defined, and the text box below is defined
        Rectangle map1 = new Rectangle(150,131,100,51);
        Rectangle map1Text = new Rectangle(187,121,34,5);

        //Defines the bounds where the second map is defined and the text box below is defined
        Rectangle map2 = new Rectangle(88,63,99,50);
        Rectangle map2Text = new Rectangle(111,53,54,5);

        //Defines the bounds where the third map is defined and the text box below is defined
        Rectangle map3 = new Rectangle(212,63,100,51);
        Rectangle map3Text = new Rectangle(223,103,54,5);

        // Check if the click is within the bounds of any of the rectangles
        //Here we could use a for loop for the map list for better implementation with more maps
        if (backBound.contains(clickPosition)) {
            ScreenManager.getInstance().showScreen("StartGame", new ShowStartGame(megaMariusGame));
        }
        else if (map1.contains(clickPosition) || map1Text.contains(clickPosition)){
            ScreenManager.getInstance().showScreen("ShowGame", new ShowGame(megaMariusGame, mapList.get(0)));
        }
        else if (map2.contains(clickPosition)  || map2Text.contains(clickPosition)){
            ScreenManager.getInstance().showScreen("ShowGame", new ShowGame(megaMariusGame, mapList.get(1)));
            Display.updateLevel(1);
        }
        else if (map3.contains(clickPosition) || map3Text.contains(clickPosition)){
           ScreenManager.getInstance().showScreen("ShowGame", new ShowGame(megaMariusGame, mapList.get(2)));
           Display.updateLevel(2);
        }
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
    
}
