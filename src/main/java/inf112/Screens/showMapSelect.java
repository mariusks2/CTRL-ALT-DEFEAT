package inf112.Screens;

import com.badlogic.gdx.Screen;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import java.util.List;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import inf112.Scenes.Display;
import inf112.skeleton.app.MegaMarius;

public class showMapSelect implements Screen {

    private MegaMarius megaMariusGame;
    private Viewport viewport;
    private Stage stage;
    private Texture backgroundImage;
    private List<String> mapList;

    public showMapSelect (MegaMarius megaMariusGame){
        this.megaMariusGame=megaMariusGame;
        this.viewport=new FitViewport(MegaMarius.M_Width,MegaMarius.M_Height, new OrthographicCamera());
        this.stage=new Stage(viewport,megaMariusGame.batch);
        this.backgroundImage = new Texture("Screens/mapSelect-screen.png");
        //Add maps here
        this.mapList = new ArrayList<String>();
        mapList.add("MapAndTileset/mario1.tmx");
        mapList.add("MapAndTileset/mario2.tmx");
        mapList.add("MapAndTileset/mario3.tmx");
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the background image
        megaMariusGame.batch.begin();
        megaMariusGame.batch.draw(backgroundImage, 0, 0, MegaMarius.M_Width, MegaMarius.M_Height);
        megaMariusGame.batch.end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            dispose();
            megaMariusGame.setScreen(new ShowStartGame(megaMariusGame));
       }

       // Check if the left mouse button is clicked
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            Vector3 clickPosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            viewport.unproject(clickPosition);

            System.out.println("Clicked at: " + clickPosition.x + ", " + clickPosition.y);

            // Defines the boundin box where the back arrow is defined
            Rectangle backBounds = new Rectangle(6, 197,35 , 8);

            //Defines the bounds where the first map is defined, and the text box below is defined
            Rectangle map1 = new Rectangle(150,131,100,51);
            Rectangle map1Text = new Rectangle(187,121,34,5);

            //Defines the bounds where the second map is defined and the text box below is defined
            Rectangle map2 = new Rectangle(88,63,99,50);
            Rectangle map2Text = new Rectangle(111,53,54,5);

            //Defines the bounds where the third map is defined and the text box below is defined
            Rectangle map3 = new Rectangle();
            Rectangle map3Text = new Rectangle();

            // Check if the click is within the bounds of any of the rectangles
            if (backBounds.contains(clickPosition.x, clickPosition.y)) {
                megaMariusGame.setScreen(new ShowStartGame(megaMariusGame));
                dispose();
            }
            else if (map1.contains(clickPosition.x,clickPosition.y) || map1Text.contains(clickPosition.x,clickPosition.y)){
                megaMariusGame.setScreen(new ShowGame(megaMariusGame,  mapList.get(0)));
                dispose();

            }
            else if (map2.contains(clickPosition.x, clickPosition.y)  || map2Text.contains(clickPosition.x, clickPosition.y)){
                megaMariusGame.setScreen(new ShowGame(megaMariusGame, mapList.get(1)));
                Display.updateLevel(1);
                dispose();
            }
            else if (map3.contains(clickPosition.x, clickPosition.y)  || map3Text.contains(clickPosition.x, clickPosition.y)){
                megaMariusGame.setScreen(new ShowGame(megaMariusGame,  mapList.get(2)));
                Display.updateLevel(2);
                dispose();
            }
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
        stage.dispose();
        backgroundImage.dispose();
    }

    /*
     * Method for getting the next map in the list, and checking if you have completed all the maps
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
