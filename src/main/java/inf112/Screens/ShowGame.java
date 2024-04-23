package inf112.Screens;

import java.util.concurrent.LinkedBlockingQueue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
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

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    
    private World world;
    private Box2DDebugRenderer b2dr;
    private MakeMap creator;

    private Marius player;
    private float accumulator = 0f;
    private float stepTime = 1/60f;

    private Music music;
    private Array<Item> items;
    public LinkedBlockingQueue<ItemDef> itemsToSpawn;
    public String fileName;


    public ShowGame(MegaMarius game, String fileName){
        atlas = new TextureAtlas("Characters/Mario_and_Enemies.pack");

        this.game = game;
        this.fileName = fileName;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(MegaMarius.M_Width / MegaMarius.PPM, MegaMarius.M_Height / MegaMarius.PPM, gameCam);

        display = new Display(game.batch);

        mapLoader = new TmxMapLoader();
        //map = mapLoader.load("mario1.tmx");
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
        

        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clears screen

        renderer.render();

        //b2dr.render(world, gameCam.combined); // Uncomment to show hitbox

        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        player.draw(game.batch);
        for(Enemy enemy : creator.getEnemies()){
            enemy.draw(game.batch);
        for(Item item : items){
            item.draw(game.batch);
        }
        }
        game.batch.end();

        game.batch.setProjectionMatrix(display.stage.getCamera().combined);
        display.stage.draw();

        // Check if game is over
        if(gameIsOver()) {
            game.setScreen(new ShowGameOver(game, fileName));
            dispose();
        }
        if (Marius.getGameWon()) {
            game.setScreen(new ShowGameWon(game, fileName));
            dispose();
        }
    }

    private boolean gameIsOver() {
        if (player.currentState == Marius.State.DEAD && player.getStateTimer() > 1)
            return true;
        else
            return false;
    }


    private void handleInput(float dt) {
        //control our player using immediate impulses
        if (player.currentState != Marius.State.DEAD) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
                player.jump();
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2)
                player.b2body.applyLinearImpulse(new Vector2(0.1f/3, 0), player.b2body.getWorldCenter(), true);
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2)
                player.b2body.applyLinearImpulse(new Vector2(-0.1f/3, 0), player.b2body.getWorldCenter(), true);
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

