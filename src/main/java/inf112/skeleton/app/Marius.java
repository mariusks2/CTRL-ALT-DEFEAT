package inf112.skeleton.app;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import com.badlogic.gdx.utils.Array;

import inf112.Entities.Enemies.Enemy;
import inf112.Entities.Enemies.Turtle;
import inf112.Screens.ShowGame;
import inf112.Screens.ShowGameOver;

	public class Marius extends Sprite {

		// Enum and states
		public enum State {START, FALLING, JUMPING, STANDING, RUNNING, DEAD, GROWING, PAUSED};
		public State currentState;
		public State previousState;
		
		// Enteties
		public World world;
		public Body b2body;
		
		// Marius texture region
		private TextureRegion mariusStand;
		private TextureRegion mariusJump;
		private TextureRegion mariusDead;
		private TextureRegion bigMariusJump;
		private TextureRegion bigMariusStand;
		
		// Marius boolean state
		private boolean runningRight;
		private boolean mariusIsDead;
		private static boolean gameWon;
		private boolean isMariusBig;
		private boolean runGrowAnimation;
		private boolean timeToDefineBigMarius;
		private boolean timetoReDefineMarius;


		// Marius animation
		private Animation<TextureRegion> mariusRun;
		private Animation<TextureRegion> bigMariusRun;
		private Animation<TextureRegion> mariusGrow;

		// Timer & Screen
		private float stateTimer;
		private ShowGame screen;

		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Marius(ShowGame screen){
			// Initializing variables
			this.screen = screen;
			this.world = screen.getWorld();
			currentState = State.STANDING;
			previousState = State.STANDING;
			stateTimer = 0;
			runningRight = true;
			gameWon = false;
	
			Array<TextureRegion> frames = new Array<TextureRegion>();
			
			// Small marius
			// Retrieving run animation frames
			for(int i = 1; i < 4; i++)
				frames.add(new TextureRegion(screen.getAtlas().findRegion("little_mario"), i * 16, 0, 16, 16));
			// Adding run animation to marius
			mariusRun = new Animation(0.1f, frames);
			frames.clear();

			for(int i = 1; i < 4; i++)
				frames.add(new TextureRegion(screen.getAtlas().findRegion("big_mario"), i * 16, 0, 16, 32));
			// Adding run animation to marius
			bigMariusRun = new Animation(0.1f, frames);
			frames.clear();

			// Animation when Marius gets pepsi
			frames.add(new TextureRegion(screen.getAtlas().findRegion("big_mario"), 240, 0, 16, 32));
			frames.add(new TextureRegion(screen.getAtlas().findRegion("big_mario"), 0, 0, 16, 32));
			mariusGrow = new Animation(0.3f, frames);
			frames.clear();

			// Cet jump animation frames and add them to marioJump Animation
			mariusJump = new TextureRegion(screen.getAtlas().findRegion("little_mario"), 80, 0, 16, 16);
			bigMariusJump = new TextureRegion(screen.getAtlas().findRegion("big_mario"), 80, 0, 16, 32);
	
			// Create texture region for standing marius
			mariusStand = new TextureRegion(screen.getAtlas().findRegion("little_mario"), 0, 0, 16, 16);
			bigMariusStand = new TextureRegion(screen.getAtlas().findRegion("big_mario"), 0, 0, 16, 32);
	
	
			// Create texture region for dead marius
			mariusDead = new TextureRegion(screen.getAtlas().findRegion("little_mario"), 96, 0, 16, 16);
			
	
			// Define marius in Box2d
			defineMarius();
	
			// Set initial values for marius location, width and height and also initial frame as mariusStand
			setBounds(0, 0, 16 / MegaMarius.PPM, 16 / MegaMarius.PPM);
			setRegion(mariusStand);
		}
	
		public void update(float dt){
			
			if(currentState==State.PAUSED){
				return;
			}
			// Check if time is up or marius has died
			if((screen.getDisplay().isTimeUp() && !entityIsDead()) || fallOfMap()) {
				entityDie();
			}
			// Check if marius has won game
			if (b2body.getPosition().x >= 34 + 0.1) {
				setGameWon();
			}
			// Update our sprite to correspond with the position of our Box2D body and with correct frame depending on current state
			if (isMariusBig) {
				setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2 - 6/MegaMarius.PPM);
			}
			else setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
			setRegion(getFrame(dt));
			
            
			if (timeToDefineBigMarius) {
				defineBigMarius();
			}
			if (timetoReDefineMarius) {
				redefineMarius();
			}
		}

		private boolean fallOfMap() {
			// Check if marius body position is off the map
			if(b2body.getPosition().y <= 0)
				return true;
			else
				return false;
		}

		public boolean entityIsDead() {
			return mariusIsDead;
		}

		public void entityDie() {
			// Check that marius is not already dead
			if(!entityIsDead()) {
				mariusIsDead = true;
				Filter filter = new Filter();
				filter.maskBits = MegaMarius.NOTHING_BIT;

				for (Fixture fixture : b2body.getFixtureList()) {
					fixture.setFilterData(filter);
				}

				b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
			} else {
				//System.out.println("Entity is already dead");
			}
		}
	
		public TextureRegion getFrame(float dt){
			// Retrieve marius current state (jumping, running etc..)
			currentState = getState();
			TextureRegion animationFrame;
	
			// Retrive the animation frame depending on the current state
			switch(currentState){
				case GROWING: 
					animationFrame = mariusGrow.getKeyFrame(stateTimer, true);
					if(mariusGrow.isAnimationFinished(stateTimer)) runGrowAnimation = false;
					break;
				case DEAD:
					animationFrame = mariusDead;
					break;
				case JUMPING:
					if (isMariusBig) {
						animationFrame = bigMariusJump;
					} 
					else animationFrame = mariusJump;
					break;
				case RUNNING:
					if (isMariusBig) {
						animationFrame = (TextureRegion) bigMariusRun.getKeyFrame(stateTimer, true);
					} 
					else animationFrame = (TextureRegion) mariusRun.getKeyFrame(stateTimer, true);
					break;
				case FALLING:
					// ToDo
				case STANDING:
				default:
					if (isMariusBig) {
						animationFrame = bigMariusStand;
					}
					else animationFrame = mariusStand;
					break;
			}
	
			// Flip marius to the left if body is running left
			if((b2body.getLinearVelocity().x < 0 || !runningRight) && !animationFrame.isFlipX()){
				animationFrame.flip(true, false);
				runningRight = false;
			}

			// Flip marius to the right if body is running right
			else if((b2body.getLinearVelocity().x > 0 || runningRight) && animationFrame.isFlipX()){
				animationFrame.flip(true, false);
				runningRight = true;
			}

			// If the current state is the same as the previous state increase the state timer.
			// Otherwise the state has changed and we need to reset timer.
			stateTimer = currentState == previousState ? stateTimer + dt : 0;
			// Update previous state
			previousState = currentState;
			// Return animation frame
			return animationFrame;
		}
	
		public State getState(){
			//Test to Box2D for velocity on the X and Y-Axis
			//if mario is going positive in Y-Axis he is jumping... or if he just jumped and is falling remain in jump state
			if(runGrowAnimation){
				return State.GROWING;
			}
			if (gameWon){
				return State.PAUSED;
			}
			if(mariusIsDead)
				return State.DEAD;
			else if (runGrowAnimation)
				return State.GROWING;
			else if((b2body.getLinearVelocity().y > 0 && currentState == State.JUMPING) || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING))
				return State.JUMPING;
			//if negative in Y-Axis mario is falling
			else if(b2body.getLinearVelocity().y < 0)
				return State.FALLING;
			//if mario is positive or negative in the X axis he is running
			else if(b2body.getLinearVelocity().x != 0)
				return State.RUNNING;
			//if none of these return then he must be standing
			else
				return State.STANDING;
		}
	
		public float getStateTimer(){
			return stateTimer;
		}
	

		public void jump(){
			// Jump if not already jumping
			if (currentState != State.JUMPING) {
				b2body.applyLinearImpulse(new Vector2(0, 3.8f), b2body.getWorldCenter(), true);
				currentState = State.JUMPING;
			}
		}
	
		public void defineMarius(){
			BodyDef bdef = new BodyDef();
			bdef.position.set(32 / MegaMarius.PPM, 32 / MegaMarius.PPM);
			bdef.type = BodyDef.BodyType.DynamicBody;
			b2body = world.createBody(bdef);
	
			FixtureDef fdef = new FixtureDef();
			CircleShape shape = new CircleShape();
			shape.setRadius(6 / MegaMarius.PPM);
			fdef.filter.categoryBits = MegaMarius.MARIUS_BIT;
			fdef.filter.maskBits = MegaMarius.GROUND_BIT |
					MegaMarius.COIN_BIT |
					MegaMarius.BRICK_BIT |
					MegaMarius.ENEMY_BIT |
					MegaMarius.OBJECT_BIT |
					MegaMarius.ENEMY_HEAD_BIT |
					MegaMarius.ITEM_BIT;
	
			fdef.shape = shape;
			b2body.createFixture(fdef).setUserData(this);
	
			EdgeShape head = new EdgeShape();
			head.set(new Vector2(-2 / MegaMarius.PPM, 6 / MegaMarius.PPM), new Vector2(2 / MegaMarius.PPM, 6 / MegaMarius.PPM));
			fdef.filter.categoryBits = MegaMarius.MARIUS_HEAD_BIT;
			fdef.shape = head;
			fdef.isSensor = true;
	
			b2body.createFixture(fdef).setUserData(this);
		}

		public void defineBigMarius(){
			Vector2 currentPositionMarius =  b2body.getPosition();
			world.destroyBody(b2body);

			BodyDef bdef = new BodyDef();
			bdef.position.set(currentPositionMarius.add(0, 10/MegaMarius.PPM));
			bdef.type = BodyDef.BodyType.DynamicBody;
			b2body = world.createBody(bdef);
	
			FixtureDef fdef = new FixtureDef();
			CircleShape shape = new CircleShape();
			shape.setRadius(5 / MegaMarius.PPM);
			fdef.filter.categoryBits = MegaMarius.MARIUS_BIT;
			fdef.filter.maskBits = MegaMarius.GROUND_BIT |
					MegaMarius.COIN_BIT |
					MegaMarius.BRICK_BIT |
					MegaMarius.ENEMY_BIT |
					MegaMarius.OBJECT_BIT |
					MegaMarius.ENEMY_HEAD_BIT |
					MegaMarius.ITEM_BIT;
	
			fdef.shape = shape;
			b2body.createFixture(fdef).setUserData(this);
			shape.setPosition(new Vector2(0, -14/MegaMarius.PPM));
			b2body.createFixture(fdef).setUserData(this);
	
			EdgeShape head = new EdgeShape();
			head.set(new Vector2(-2 / MegaMarius.PPM, 6 / MegaMarius.PPM), new Vector2(2 / MegaMarius.PPM, 6 / MegaMarius.PPM));
			fdef.filter.categoryBits = MegaMarius.MARIUS_HEAD_BIT;
			fdef.shape = head;
			fdef.isSensor = true;
	
			b2body.createFixture(fdef).setUserData(this);
			timeToDefineBigMarius = false;
		}


		public void draw(Batch batch){
			super.draw(batch);
		}

		public void setGameWon() {
			gameWon = true;
		}

		public boolean isMariusBigNow(){
			return isMariusBig;
		}

		public static boolean getGameWon() {
			return gameWon;
		}

		public void hit(Enemy enemy){
			if (enemy instanceof Turtle && ((Turtle) enemy).getCurrentState() == Turtle.State.STANDING_SHELL) {
				((Turtle) enemy).kick(enemy.b2body.getPosition().x > b2body.getPosition().x ? Turtle.KICK_RIGHT : Turtle.KICK_LEFT);
			}
			else {
				if (isMariusBig){
					isMariusBig = false;
					timetoReDefineMarius = true;
					setBounds(getX(), getY(), getWidth(), getHeight()/2);
				}
				else {
					entityDie();
				}
			}
		}

		public void grow(){
			runGrowAnimation = true;
			isMariusBig = true;
			timeToDefineBigMarius = true;
			setBounds(getX(), getY(), getWidth(), getHeight()*2);
		}

		public void redefineMarius(){
			Vector2 posistion = b2body.getPosition();
			world.destroyBody(b2body);

			BodyDef bdef = new BodyDef();
			bdef.position.set(posistion);
			bdef.type = BodyDef.BodyType.DynamicBody;
			b2body = world.createBody(bdef);
	
			FixtureDef fdef = new FixtureDef();
			CircleShape shape = new CircleShape();
			shape.setRadius(6 / MegaMarius.PPM);
			fdef.filter.categoryBits = MegaMarius.MARIUS_BIT;
			fdef.filter.maskBits = MegaMarius.GROUND_BIT |
					MegaMarius.COIN_BIT |
					MegaMarius.BRICK_BIT |
					MegaMarius.ENEMY_BIT |
					MegaMarius.OBJECT_BIT |
					MegaMarius.ENEMY_HEAD_BIT |
					MegaMarius.ITEM_BIT;
	
			fdef.shape = shape;
			b2body.createFixture(fdef).setUserData(this);
	
			EdgeShape head = new EdgeShape();
			head.set(new Vector2(-2 / MegaMarius.PPM, 6 / MegaMarius.PPM), new Vector2(2 / MegaMarius.PPM, 6 / MegaMarius.PPM));
			fdef.filter.categoryBits = MegaMarius.MARIUS_HEAD_BIT;
			fdef.shape = head;
			fdef.isSensor = true;
	
			b2body.createFixture(fdef).setUserData(this);

			timetoReDefineMarius = false;
		}

		public ShowGame getScreen(){
			return screen;
		}

		public void setCurrentState(State newState) {
			// Before changing the state, update previousState
			this.previousState = this.currentState;
		
			// Change the current state to the new state
			this.currentState = newState;
		
			// Reset the state timer because the state has changed
			this.stateTimer = 0;
		}
}
