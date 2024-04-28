package inf112.Screens;

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
//import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer; Uncomment to show hitbox
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


import inf112.skeleton.app.MegaMarius;
import inf112.Entities.Item;
import inf112.Entities.ItemDef;
import inf112.Entities.Blocks.CoinAnimation;
import inf112.Entities.Blocks.Pepsi;
import inf112.Entities.Enemies.Enemy;
import inf112.Scenes.Display;
import inf112.skeleton.MakeMap.MakeMap;
import inf112.skeleton.app.Marius;
import inf112.skeleton.app.WorldContactListener;

public class ShowGame implements Screen{
    private MegaMarius game;
    private TextureAtlas atlas;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Display display;
    private Stage uiStage;
    private LabelStyle font;
    private Label victoryLabel;
    private Label retryLabel;
    private showMapSelect mapSelect;

    //For creating a grayed out screen when the game is won
    private ShapeRenderer shapeRenderer;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    
    private World world;
    private MakeMap creator;

    private Marius player;
    private float accumulator = 0f;
    private float stepTime = 1/60f;

    private Music music;
    private Array<Item> items;
    public LinkedBlockingQueue<ItemDef> itemsToSpawn;
    public String fileName;
    //private Box2DDebugRenderer b2dr;


    public ShowGame(MegaMarius game, String fileName){
        atlas = new TextureAtlas("Characters/Mario_and_Enemies.pack");

        this.game = game;
        this.fileName = fileName;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(MegaMarius.M_Width / MegaMarius.PPM, MegaMarius.M_Height / MegaMarius.PPM, gameCam);

        display = new Display(game.getSpriteBatch());

        mapLoader = new TmxMapLoader();
        map = mapLoader.load(fileName);
        renderer = new OrthogonalTiledMapRenderer(map, 1  / MegaMarius.PPM);
        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);

        world = new World(new Vector2(0, -10), true);
        world.step(0, 0, 0);
        //b2dr = new Box2DDebugRenderer(); // uncomment to show hitbox 

        creator = new MakeMap(this);

        player = new Marius(this);

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
        retryLabel = new Label("Press ENTER for next level or ESC to quit game", font);
        retryLabel.setVisible(false);  // Initially invisible
        retryLabel.setPosition(MegaMarius.M_Width / 2 - retryLabel.getWidth() / 2, MegaMarius.M_Height / 2 - 20);  // Slightly below the victoryLabel
        uiStage.addActor(retryLabel);

      

        this.mapSelect = new showMapSelect(game);
        this.shapeRenderer = new ShapeRenderer();
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public void spawnItems(ItemDef itemDef){
        itemsToSpawn.add(itemDef);
    }

    public void handleSpawningItems(){
        if(!itemsToSpawn.isEmpty()){
            ItemDef itemDef = itemsToSpawn.poll();
            if(itemDef.type == Pepsi.class){
                items.add(new Pepsi(this, itemDef.positon.x, itemDef.positon.y));
            }
            if(itemDef.type == CoinAnimation.class){
                items.add(new CoinAnimation(this, itemDef.positon.x, itemDef.positon.y));
            }
        }
        
    }


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
            gameCam.position.x = player.b2body.getPosition().x;
        }

        gameCam.update();
        renderer.setView(gameCam);
    }

    @Override
    public void render(float delta) {
        updateState(delta);
        ScreenManager.getInstance().clearScreen();
        drawGameWorld();
        drawUI();
        handleScreenTransitions();
    }

    private void updateState(float delta) {
        update(delta);
        handleInput();
    }

    private void drawGameWorld() {
        renderer.render();
        game.getSpriteBatch().setProjectionMatrix(gameCam.combined);
        game.getSpriteBatch().begin();
        player.draw(game.getSpriteBatch());
        for (Enemy enemy : creator.getEnemies()) {
            enemy.draw(game.getSpriteBatch());
        }
        for (Item item : items) {
            item.draw(game.getSpriteBatch());
        }
        game.getSpriteBatch().end();
    }

    private void drawUI() {
        game.getSpriteBatch().setProjectionMatrix(display.stage.getCamera().combined);
        display.stage.draw();
        if (Marius.getGameWon()) {
            drawGrayOverlay();
            uiStage.draw();
            retryLabel.setVisible(true);
            victoryLabel.setVisible(true);
        }
    }

    private void handleScreenTransitions() {
        if (gameIsOver()) {
            ScreenManager.getInstance().showScreen("GameOver", new ShowGameOver(game, fileName));
        } else if (Marius.getGameWon()) {
            handleVictoryTransition();
        }
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            ScreenManager.getInstance().showScreen("PauseGame", new showPauseScreen(game, player, player.currentState));
            player.setCurrentState(Marius.State.PAUSED);
        }
    }

    private void handleVictoryTransition() {
        String nextMap = mapSelect.getNextMap(fileName);
        if (nextMap.equals("GameCompleted")) {
            ScreenManager.getInstance().showScreen("GameCompleted", new showGameCompleted(game));
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            ScreenManager.getInstance().showScreen("ShowGame", new ShowGame(game, nextMap));
            Display.updateLevel(1);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            System.exit(0);
        }
    }
    /*
     * Method for drawing a light gray overlay when the game is won, used to display a clear difference between game and game win screen
     */
    private void drawGrayOverlay() {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.setProjectionMatrix(gameCam.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.3f, 0.3f, 0.3f, 0.5f);  // Gray color with 50% opacity
        shapeRenderer.rect(0, 0, MegaMarius.M_Width, MegaMarius.M_Height);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    /*
     * Method for checking whether the game is over or not, returns true if the player is dead or the timer is done. Otherwise it returns false
     */
    private boolean gameIsOver() {
        if (player.currentState == Marius.State.DEAD && player.getStateTimer() > 1)
            return true;
        else
            return false;
    }

    /*
     * Method for handling the user input
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
        
    public TiledMap getMap(){
        return map;
    }

    public World getWorld(){
        return world;
    }


    @Override
    public void show() {
        
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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
        map.dispose();
        renderer.dispose();
        world.dispose();
        if(!Marius.getGameWon())
            display.dispose();
    }
    
    public Display getDisplay(){
        return display; 
    }
    public MegaMarius getGame() {
        return game;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}

