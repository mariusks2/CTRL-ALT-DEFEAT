package inf112.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

import inf112.skeleton.app.Marius;
import inf112.skeleton.app.Marius.State;
import inf112.skeleton.app.MegaMarius;

public class showPauseScreen implements Screen{

    private MegaMarius game;
    private Marius player;
    private Viewport viewport;
    private Stage stage;
    private Texture backgroundImage;
    private State previousState;



    public showPauseScreen(MegaMarius game, Marius player, State state){
        this.game = game;
        this.player = player;
        this.viewport = new FitViewport(MegaMarius.M_Width, MegaMarius.M_Height, new OrthographicCamera());
        //this.stage = new Stage(viewport,game.getSpriteBatch());
        this.backgroundImage = new Texture("Screens/pause-screen.png");
        this.previousState = state;
  
        
    }

    @Override
    public void show() {
        
    }

    @Override
    public void render(float delta) {
        handleInput();
        ScreenManager.getInstance().clearScreen();
        ScreenManager.getInstance().drawBackground(backgroundImage, MegaMarius.M_Width, MegaMarius.M_Height);
        //stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        //stage.draw();
        
    }

    private void handleInput(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            player.setCurrentState(previousState);
            game.setScreen(ScreenManager.getInstance().getCurrentGameScreen());
        }
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            Vector2 clickPosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(clickPosition);
            checkButtonPress(clickPosition);
        }
    }

    private void checkButtonPress(Vector2 clickPosition){

        System.out.println("Clicked at: " + clickPosition.x + ", " + clickPosition.y);
        //Defining resume game bounds
        Rectangle resumeBound = new Rectangle(163,146,65,7);

        //Defining settings game bounds
        Rectangle settingsBound = new Rectangle(176,117,37,9);

        //Defining back to map selection button
        Rectangle mapSelectionBound = new Rectangle(146,90,108,7);

        //Defining quit game bound
        Rectangle quitGameBound = new Rectangle(172,57,49,8);

        if(resumeBound.contains(clickPosition)){
            player.setCurrentState(previousState);
            game.setScreen(ScreenManager.getInstance().getCurrentGameScreen());
        }
        else if (settingsBound.contains(clickPosition)){
            ScreenManager.getInstance().showScreen("Settings", new ShowSettingsScreen(game));
        } 
        else if (mapSelectionBound.contains(clickPosition)){
            ScreenManager.getInstance().showScreen("MapSelect", new showMapSelect(game));
        }
        else if(quitGameBound.contains(clickPosition)){
            Gdx.app.exit();
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
        //stage.dispose();
        backgroundImage.dispose();
    }
    
}
