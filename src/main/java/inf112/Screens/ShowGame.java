package inf112.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import inf112.skeleton.app.MegaMarius;
import inf112.Scenes.Display;
import inf112.Screen.Marius.Spider;
import inf112.skeleton.MakeMarius.makemarius;
import inf112.skeleton.app.Marius;
import inf112.skeleton.app.WorldContactListener;

public class ShowGame implements Screen{
    private static MegaMarius game;
    private TextureAtlas atlas;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Display display;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;

    // Sprites
    private Marius player;
    private float accumulator = 0f;
    private float stepTime = 1/60f; // Fixed time step for physics updates
    private Spider spider;

    public ShowGame(MegaMarius game){
        atlas = new TextureAtlas("Mario_and_Enemies.pack");

        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(MegaMarius.M_Width / MegaMarius.PPM, MegaMarius.M_Height / MegaMarius.PPM, gamecam);

        display = new Display(game.batch);


        mapLoader = new TmxMapLoader();
        map = mapLoader.load("mario1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1  / MegaMarius.PPM);
        gamecam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);

        world = new World(new Vector2(0, -10), true);
        world.step(0, 0, 0);
        b2dr = new Box2DDebugRenderer();    

        new makemarius(this);

        player = new Marius(this);

        world.setContactListener(new WorldContactListener());

        spider = new Spider(this, .32f, .32f);
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }


    public void update(float dt){

        handleInput(dt);

        // Accumulate the time passed
        accumulator += Math.min(dt, 0.25f);

        // Perform fixed time step updates
        while (accumulator >= stepTime) {
            world.step(stepTime, 6, 2);
            accumulator -= stepTime;
        }

        player.update(dt);
        spider.update(dt);

        display.updateTime(dt);

        //attach our gamecam to our players.x coordinate
        if(player.currentState != Marius.State.DEAD) {
            gamecam.position.x = player.b2body.getPosition().x;
        }

        gamecam.update();
        renderer.setView(gamecam);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clears screen

        renderer.render();
        b2dr.render(world, gamecam.combined);



        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        player.draw(game.batch);
        spider.draw(game.batch);
        game.batch.end();

        game.batch.setProjectionMatrix(display.stage.getCamera().combined);
        display.stage.draw();

        // Check if game is over
        if(gameIsOver()) {
            game.setScreen(new ShowGameOver(game));
            dispose();
        }
    }

    private boolean gameIsOver() {
        if (player.currentState == Marius.State.DEAD && player.getStateTimer() > 3)
            return true;
        else
            return false;
    }


    private void handleInput(float dt) {
        //control our player using immediate impulses
         
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
            player.jump();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2)
            player.b2body.applyLinearImpulse(new Vector2(0.1f/3, 0), player.b2body.getWorldCenter(), true);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2)
            player.b2body.applyLinearImpulse(new Vector2(-0.1f/3, 0), player.b2body.getWorldCenter(), true);
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
        b2dr.dispose();
        display.dispose();
    }
    
    public Display getDisplay(){
        return display; 
    }
    public static MegaMarius getGame() {
        return game;
    }

}

