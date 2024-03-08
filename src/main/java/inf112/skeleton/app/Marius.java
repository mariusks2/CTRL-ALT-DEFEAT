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
import inf112.Screens.ShowGame;

	public class Marius extends Sprite {

		// Enum and states
		public enum State { FALLING, JUMPING, STANDING, RUNNING, DEAD };
		public State currentState;
		public State previousState;
		
		// Enteties
		public World world;
		public Body b2body;
		
		// Marius texture region
		private TextureRegion mariusStand;
		private TextureRegion mariusJump;
		private TextureRegion mariusDead;
		
		// Marius boolean state
		private boolean runningRight;
		private boolean mariusIsDead;
		private static boolean gameWon;

		// Marius animation
		private Animation mariusRun;
		
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

			// Cet jump animation frames and add them to marioJump Animation
			mariusJump = new TextureRegion(screen.getAtlas().findRegion("little_mario"), 80, 0, 16, 16);
	
			// Create texture region for standing marius
			mariusStand = new TextureRegion(screen.getAtlas().findRegion("little_mario"), 0, 0, 16, 16);
	
			// Create texture region for dead marius
			mariusDead = new TextureRegion(screen.getAtlas().findRegion("little_mario"), 96, 0, 16, 16);
	
			// Define marius in Box2d
			defineMairus();
	
			// Set initial values for marius location, width and height and also initial frame as mariusStand
			setBounds(0, 0, 16 / MegaMarius.PPM, 16 / MegaMarius.PPM);
			setRegion(mariusStand);
		}
	
		public void update(float dt){
			// Check if time is up or marius has died
			if((screen.getDisplay().isTimeUp() && !entityIsDead()) || fallOfMap()) {
				entityDie();
			}
			// Check if marius has won game
			if (b2body.getPosition().x >= 34.5) {
				setGameWon();
			}

			// Update our sprite to correspond with the position of our Box2D body and with correct frame depending on current state
            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
			setRegion(getFrame(dt));
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
				case DEAD:
					animationFrame = mariusDead;
					break;
				case JUMPING:
					animationFrame = mariusJump;
					break;
				case RUNNING:
					animationFrame = (TextureRegion) mariusRun.getKeyFrame(stateTimer, true);
					break;
				case FALLING:
					// ToDo
				case STANDING:
				default:
					animationFrame = mariusStand;
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
			if(mariusIsDead)
				return State.DEAD;
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
	
		public void defineMairus(){
			// Define marius character
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
			MegaMarius.ITEM_BIT |
			MegaMarius.FLAG_BIT;
	
			fdef.shape = shape;
			b2body.createFixture(fdef);
	
			EdgeShape head = new EdgeShape();
			head.set(new Vector2(-2 / MegaMarius.PPM, 6 / MegaMarius.PPM), new Vector2(2 / MegaMarius.PPM, 6 / MegaMarius.PPM));
			fdef.filter.categoryBits = MegaMarius.MARIUS_HEAD_BIT;
			fdef.shape = head;
			fdef.isSensor = true;
	
			b2body.createFixture(fdef).setUserData("head");
		}

		public void draw(Batch batch){
			super.draw(batch);
		}

		public void setGameWon() {
			gameWon = true;
		}

		public static boolean getGameWon() {
			return gameWon;
		}
}
