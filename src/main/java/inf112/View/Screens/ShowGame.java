package inf112.View.Screens;

import java.util.concurrent.LinkedBlockingQueue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
//import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer; Uncomment to show hitbox
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


import inf112.skeleton.app.MegaMarius;
import inf112.Entities.Item;
import inf112.Entities.ItemDef;
import inf112.Entities.Blocks.Pessi;
import inf112.Entities.Enemies.Enemy;
import inf112.View.Scenes.Display;
import inf112.View.ScreenManagement.IScreenFactory;
import inf112.View.ScreenManagement.ScreenManager;
import inf112.skeleton.MakeMap.MakeMap;
import inf112.skeleton.app.Marius;
import inf112.skeleton.app.WorldContactListener;

/**
 * This class represents the main game screen and renders 
 */
public class ShowGame implements Screen{
    private MegaMarius game; //Reference to the main game object
    private TextureAtlas atlas; //Contains textures related to characters
    private OrthographicCamera camera; //
    private Viewport gamePort; //Mangages how content is displayed
    private Display display; //UI display for the game
    private Stage uiStage;
    private LabelStyle font; //Font style for UI labels
    private Label victoryLabel; //Text to display when level is completed
    private Label retryLabel; //Instruction on what to do after completing level
    private ShowMapSelect mapSelect; //Used for getting next map when level is completed
    private Label gameCompletedLabel; //Label to display when the final map is completed
    private Label completedDescriptionLabel; //Label to explain what the user should do after completing the game

    //For creating a grayed out screen when the game is won
    private ShapeRenderer shapeRenderer;

    //Renderer for the tiled map
    private TmxMapLoader mapLoader; //Loads the chosen map
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    
    private World world; //Represents the physical world within the game
    private MakeMap creator; //Creating the map

    private Marius player; //The players character
    private float accumulator = 0f; //Fixed timestep in game loop
    private float stepTime = 1/60f; //Timestep

    private Music music; //Background music for the game
    private Array<Item> items; //Items present in the game
    public LinkedBlockingQueue<ItemDef> itemsToSpawn; //items needed to be spawned
    public String fileName; //Name of the current map file
    private Box2DDebugRenderer b2dr;

    private IScreenFactory screenService;

    /**
     * Initialization of the game and variables used to display the game
     * @param game Reference to the main game object
     * @param fileName The filename of the map to display
     */
    public ShowGame(MegaMarius game, String fileName, IScreenFactory screenService){
        atlas = new TextureAtlas("Characters/MegaMariusCharacters.pack");

        this.game = game;
        this.fileName = fileName;
        camera = new OrthographicCamera();
        gamePort = new StretchViewport(MegaMarius.M_Width / MegaMarius.PPM, MegaMarius.M_Height / MegaMarius.PPM, camera);
        //gamePort = new FitViewport(MegaMarius.M_Width / MegaMarius.PPM, MegaMarius.M_Height / MegaMarius.PPM, camera);

        display = new Display(game.getSpriteBatch());

        mapLoader = new TmxMapLoader();
        map = mapLoader.load(fileName);
        renderer = new OrthogonalTiledMapRenderer(map, 1  / MegaMarius.PPM);
        camera.position.set(-10, gamePort.getWorldHeight()/2, 0);

        world = new World(new Vector2(0, -10), true); //world with gravity -10
        world.step(0, 0, 0);

        creator = new MakeMap(this);
        b2dr = new Box2DDebugRenderer();

        player = new Marius(this);

        this.screenService = screenService;

        world.setContactListener(new WorldContactListener());

        music = game.manager.get("audio/music/music1.mp3", Music.class);
        music.setLooping(true);
        music.setVolume(0.005f);
        music.play(); // Comment this out to stop music from playing

        items = new Array<Item>();
        itemsToSpawn = new LinkedBlockingQueue<ItemDef>();

        //Display map winning text:
        this.uiStage = new Stage (new FitViewport(MegaMarius.M_Width,MegaMarius.M_Height, new OrthographicCamera()), game.getSpriteBatch());
        this.font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        // Configure the victory message
        victoryLabel = new Label("Level Complete!", font);
        victoryLabel.setVisible(false);  // Initially invisible
        victoryLabel.setPosition(MegaMarius.M_Width / 2 - victoryLabel.getWidth() / 2, MegaMarius.M_Height / 2 + 20);  // Adjust Y position for visibility
        uiStage.addActor(victoryLabel);

        // Configure the instruction message
        retryLabel = new Label("Press ENTER for next level or ESC to go back to start game", font);
        retryLabel.setVisible(false);  // Initially invisible
        retryLabel.setPosition(MegaMarius.M_Width / 2 - retryLabel.getWidth() / 2, MegaMarius.M_Height / 2 - 20);  // Slightly below the victoryLabel
        uiStage.addActor(retryLabel);

        //Configure the game completed text:
        gameCompletedLabel = new Label("Congratulation you completed the game!", font);
        gameCompletedLabel.setVisible(false);  // Initially invisible
        gameCompletedLabel.setPosition(MegaMarius.M_Width / 2 - gameCompletedLabel.getWidth() / 2, MegaMarius.M_Height / 2 + 20);  // Adjust Y position for visibility
        uiStage.addActor(gameCompletedLabel);

        //Configure what to do 
        // Configure the instruction message
        completedDescriptionLabel = new Label("Press ENTER to return to start game or ESC to quit game", font);
        completedDescriptionLabel.setVisible(false);  // Initially invisible
        completedDescriptionLabel.setPosition(MegaMarius.M_Width / 2 - completedDescriptionLabel.getWidth() / 2, MegaMarius.M_Height / 2 - 20);  // Slightly below the victoryLabel
        uiStage.addActor(completedDescriptionLabel);

      

        this.mapSelect = new ShowMapSelect(game,screenService);
        this.shapeRenderer = new ShapeRenderer();
    }
    /**
     * @return Returns the TextureAtlas which contains characters textures
     */
    public TextureAtlas getAtlas() {
        return atlas;
    }

    /**
     * Queues an item to be spawned into the game world
     * @param itemDef The definition of the item to be spawned
     */
    public void spawnItems(ItemDef itemDef){
        itemsToSpawn.add(itemDef);
    }

    /**
     * Handles the creation and addition of items to the game world from the spaw queue.
     */
    public void handleSpawningItems(){
        if(!itemsToSpawn.isEmpty()){
            ItemDef itemDef = itemsToSpawn.poll();
            if(itemDef.type == Pessi.class){
                items.add(new Pessi(this, itemDef.positon.x, itemDef.positon.y));
            }
        }
        
    }

    /**
     * Updates the game physics and enemies and renders the scene
     * @param dt The time passed since the last frame
     */
    public void update(float dt){
        if (player.currentState==Marius.State.PAUSED){
            return;
        }

        handleInput(dt);
        handleSpawningItems();
        
        accumulator += Math.min(dt, 0.25f);

        
        while (accumulator >= stepTime) {
            world.step(stepTime, 6, 2);
            accumulator -= stepTime;
        }

        player.update(dt);
        for(Enemy enemy : creator.getEnemies()){
            enemy.update(dt);
            if (enemy.getX() < player.getX() + 224/MegaMarius.PPM) {
                enemy.b2body.setActive(true);
            }
        }
        for(Item item : items){
            if(!item.isDestroyed()) item.update(dt);
        }
        
        display.updateTime(dt);

        //attach our gamecam to our players.x coordinate
        if(player.currentState != Marius.State.DEAD) {
            camera.position.x = player.b2body.getPosition().x;
        }

        camera.update();
        renderer.setView(camera);
    }

    /**
     * Renders the game world and UI, and handles user input.
     * @param delta Time passed since last frame
     */
    @Override
    public void render(float delta) {
        updateState(delta);
        screenService.clearScreen();
        drawGameWorld();
        drawUI();
        handleScreenTransitions();
    }

    /**
     * Updates the game state and handles input.
     * @param delta Time passed since last frame
     */
    private void updateState(float delta) {
        update(delta);
        handleInput();
    }

    /**
     * Draws all game world elements
     */
    private void drawGameWorld() {
        renderer.render();
        game.getSpriteBatch().setProjectionMatrix(camera.combined);
        game.getSpriteBatch().begin();
        player.draw(game.getSpriteBatch());
        for (Enemy enemy : creator.getEnemies()) {
            enemy.draw(game.getSpriteBatch());
        }
        for (Item item : items) {
            item.draw(game.getSpriteBatch());
        }
        game.getSpriteBatch().end();
        //b2dr.render(world, gameCam.combined); Hitboxes
    }

    /**
     * Draws the game UI such as scores and status messages like game won and game over
     */
    private void drawUI() {
        game.getSpriteBatch().setProjectionMatrix(display.stage.getCamera().combined);
        display.stage.draw();
        if (Marius.getGameWon()) {
            String nextMap = mapSelect.getNextMap(fileName);
            drawGrayOverlay();
            //game.getScoreboardScreen().createNewScore(display.getTimer(), display.getScoreCount(), 1);
            if (nextMap.equals("GameCompleted")) {
                game.getScoreboardScreen().createNewScore(display.getTimer(), display.getScoreCount(), 1); // TODO change the level parameter to get the current level
                uiStage.draw();
                gameCompletedLabel.setVisible(true);
                completedDescriptionLabel.setVisible(true);
            }
            else{
            uiStage.draw();
            retryLabel.setVisible(true);
            victoryLabel.setVisible(true);
            }
        }
    }

    /**
     * Handles transitions between different game screens based on game state
     */
    private void handleScreenTransitions() {
        if (gameIsOver()) {
            screenService.showGameOverScreen(fileName);
        } else if (Marius.getGameWon()) {
            handleVictoryTransition();
        }
    }

    /**
     * Handles user input for when the player wnats to pause the game
     */
    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            screenService.showPauseGameScreen(player, player.currentState);
        }
    }
  

    /**
     * Handles the game screens and user input when the game is won
     */
    private void handleVictoryTransition() {
        String nextMap = mapSelect.getNextMap(fileName);

        if(nextMap.equals("GameCompleted")){
            if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
                screenService.showStartGame();
            }
            else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
                Gdx.app.exit();
            }
        }
        else{
            if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                game.getScoreboardScreen().createNewScore(display.getTimer(), display.getScoreCount(), 1); // TODO change the level parameter to get the current level
                screenService.showGameScreen(nextMap);
                Display.updateLevel(1);
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                screenService.showStartGame();
            }
        }
    }
    /**
     * Method for drawing a light gray overlay when the game is won, used to display a clear difference between game and game win screen
     */
    private void drawGrayOverlay() {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.3f, 0.3f, 0.3f, 0.5f);  // Gray color with 50% opacity
        shapeRenderer.rect(0, 0, MegaMarius.M_Width, MegaMarius.M_Height);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    /**
     * Method for checking whether the game is over or not, returns true if the player is dead or the timer is done. Otherwise it returns false
     */
    private boolean gameIsOver() {
        if (player.currentState == Marius.State.DEAD && player.getStateTimer() > 1)
            return true;
        else
            return false;
    }

    /**
     * Method for handling the user input
     * @param dt time since last frame.
     */
    private void handleInput(float dt) {
        //control our player using immediate impulses
        if (player.currentState != Marius.State.DEAD) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
                player.jump();
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2)
                player.b2body.applyLinearImpulse(new Vector2(0.05f, 0), player.b2body.getWorldCenter(), true);
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2)
                player.b2body.applyLinearImpulse(new Vector2(-0.05f, 0), player.b2body.getWorldCenter(), true);
        } 
    }
    
    /**
     * Method for returning the tiledmap
     * @return The tiled map
     */
    public TiledMap getMap(){
        return map;
    }

    /**
     * Method for returning the current game world
     * @return the current game world
     */
    public World getWorld(){
        return world;
    }


  
    /**
     * Method for resizing the game screen
     */
    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    //Lifecycle methods from the screen interface not used
    @Override
    public void show() {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}

    /**
     * Method for disposing UI elements
     */
    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        if(!Marius.getGameWon())
            display.dispose();
    }
    
    /**
     * Method for returning the current display
     * @return current display
     */
    public Display getDisplay(){
        return display; 
    }

    /**
     * Method for returning the current game object
     * @return current game object
     */
    public MegaMarius getGame() {
        return game;
    }

}

