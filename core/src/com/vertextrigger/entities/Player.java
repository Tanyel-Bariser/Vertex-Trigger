package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.Coordinate;
import com.vertextrigger.factories.AnimationFactory;
import com.vertextrigger.factories.SpriteFactory;
import com.vertextrigger.screens.GameScreen;

/**
 * Main character of the game
 * This class manages the player's physical body & its movements & sprite animation
 */
public class Player implements Entity {
	private Body body;
	private BulletPool bulletPool;
	private GameScreen gameScreen;

	/**
	 * Creates player's physical body & its physical properties within the game
	 * world at the starting position of the particular level.
	 * 
	 * Creates sprite & animation factories.
	 * 
	 * Creates a pool of reusable bullets for the player to shoot.
	 * 
	 * @param world the player will reside in
	 * @param initialPosition of the player in a particular level
	 */
	public Player(World world, Coordinate initialPosition, GameScreen gameScreen) {
		// Set player's body as being of dynamic type
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		
		// Set initial position of player in the specific level
		bodyDef.position.set(initialPosition.x, initialPosition.y);
		
		// Create player's physical body shape as a rectangle
		PolygonShape shape = new PolygonShape();
		float width = 0.5f, height = 1.5f;
		shape.setAsBox(width, height);

		// Create player's physical body within the game world
		body = world.createBody(bodyDef);
		
		// Create fixture for player to bind his shape & his density to his body
		float density = 3f;
		Fixture fixture = body.createFixture(shape, density);
		
		// Set fixture label as "Player" to be identifiable by collision detector
		fixture.setUserData("Player");

		// Free shape resource from memory
		shape.dispose();
		
		// Create animations and set player sprite
		spriteAnimationSetup();
		
		// Create a pool of reusable bullets
		bulletPool = new BulletPool();		
	}
	
	/**
	 * Initialise sprites & animation objects
	 */
	private void spriteAnimationSetup() {
		// Create & set all sprites & animations the player will need
		SpriteFactory spriteFactory = new SpriteFactory();
		AnimationFactory animationFactory = new AnimationFactory();
		
	}
	
	/**
	 * Bullets are shot from the position of the player's gun
	 * in the direction the player is facing
	 */
	public void shoot() {
		// Create container for bullets currently being shot
		Array<Bullet> bullets = new Array<Bullet>();
		// Get a bullet from the bullet pool
		Bullet bullet = bulletPool.obtain();
		// Set position of bullet based on the position of the player's gun position
		bullet.setPosition(body.getPosition());
		// Find out the direction the player is facing/pointing gun left/right
		boolean gunPointingLeft = 0 > getDirection();
		// Shoot the bullet in that direction
		bullet.shoot(gunPointingLeft);
		// Add the bullet to the Player's & the Game Screen's containers
		gameScreen.addEntity(bullet);
		bullets.add(bullet);
	}
	
	/**
	 * Reset player position to the initial position of the level he's in
	 */
	public void playerDied() {
		// Set player position to the initial position of the level
	}
	
	/**
	 * Setter for the player's directional movement, left or right.
	 * Negative movement moves the player left
	 * Positive movement moves the player right
	 * 
	 * @param movement of player either left or right
	 */
	public void setMovement(float movement) {
		// Set player's new directional force in x-axis
	}
	
	/**
	 * Negative movement means the player is moving left
	 * Positive movement means the player is moving right
	 * 
	 * @return the speed & direction the player is moving horizontally
	 */
	private float getDirection() {
		// Return the magnitude of the player's
		// movement in either left or right direction
		return 0;
	}
	
	/**
	 * Additional force imposed on the player usually caused by the
	 * platform the player is standing on. 
	 * For example, a conveyor belt platform can push the player left or right, 
	 * i.e. by a negative or positive parameter respectively, dependent on
	 * which direction the conveyor belt platform is moving.  
	 * 
	 * @param xForce amount to push or pull the player left or right
	 */
	public void setXForce(float xForce) {
		// Alter directional (left or right movement)
		// force by a specific amount 
	}
	
	/**
	 * @param downForce additional force on player's y-axis
	 */
	public void setDownForce(float downForce) {
		// Set additional vertical force to be put on player 
	}
	
	/**
	 * Sets angle of player
	 */
	public void setAngle(float angle) {
		// Set player's new angle
	}
	
	/**
	 * @param canJump if true player can jump, otherwise he can't
	 */
	public void setJumpAbility(boolean canJump) {
		// Set whether or not the player can jump
	}
	
	/**
	 * @return whether or not the player is allowed to jump
	 */
	private boolean getJumpAbility() {
		// Return whether or not the player is allowed to jump
		return false;
	}
	
	/**
	 * Makes player jump
	 */
	public void jump() {
		// If player is allowed to jump
				// Make the player jump
	}
	
	/**
	 * If onSticky is true, then player is on a sticky
	 * platform & therefore moves at a slower speed.
	 * 
	 * @param onSticky
	 */
	public void setOnSticky(boolean onSticky) {
		// Set whether or not the player is on a sticky platform
	}
	
	/**
	 * @return true if player is on a sticky platform, else false
	 */
	private boolean getOnSticky() {
		// Return whether or not the player is on a skicky platform
		return false;
	}
	
	/**
	 * Moves physical body of player left or right.
	 * Chooses appropriate player sprite based on animation.
	 * Returns the updated player's sprite for rendering.
	 * 
	 * @param delta time passed between previous & current frame
	 * @return updated player sprite
	 */
	@Override
	public Sprite update(float delta) {
		// For each bullet in the bullet pool
				// If the bullet's existence time has run out
						// Free the bullet from the pool to be reused later
		
		// If player is on a sticky platform move at half the directional speed
		// Move player either left or right, according to the movement variable,
		// to an amount dependent on delta
		
		// Add delta to current animation key frame time
		// If player is rising/jumping
				// Set player sprite based on jumping animation key frame
		// If player is falling
				// Set player sprite based on falling animation key frame
		// If player is running, i.e. movement is not zero
				// Set player sprite based on running animation key frame
		
		// Flip player sprite so that if he's moving left, i.e. movement + xForce is negative,
		// the sprite is facing left and vice versa if he is moving right
		
		// Set player's sprite position & angle to match
		// the new position of player's physical body
		// Return player sprite after it's position/angle has been updated
		return null;
	}
}