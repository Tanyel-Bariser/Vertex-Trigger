package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.factories.AnimationFactory;
import com.vertextrigger.factories.SpriteFactory;
import com.vertextrigger.factories.bodyfactories.PlayerBodyFactory;
import com.vertextrigger.screens.GameScreen;

/**
 * Main character of the game
 * This class manages the player's physical body & its movements & sprite animation
 */
public class Player implements Entity {
	private final Body body;
	private final Vector2 initialPosition;
	private final BulletPool bulletPool;
	private final GameScreen gameScreen;
	private final float MOVEMENT_SPEED = 50f; 
	private float movement;
	private final float JUMP_POWER = 200;
	private boolean canJump;
	private float additionalHorizontalForce = 0;
	private float onSticky = 1;

	public Player(World world, Vector2 initialPosition, GameScreen gameScreen) {
		this(world, initialPosition, gameScreen, PlayerBodyFactory.getPlayerBody(world, initialPosition));
	}
	
	Player(World world, Vector2 initialPosition, GameScreen gameScreen, Body body) {
		this.initialPosition = initialPosition;
		this.gameScreen = gameScreen;
		this.body = body;		
		spriteAnimationSetup();
		bulletPool = new BulletPool(world);
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
	 * Reset player position to the initial position of the level he's in
	 */
	public void died() {
		body.setTransform(initialPosition, 0);
	}
	
	/**
	 * Sets angle of player
	 */
	public void setAngle(float angle) {
		body.setTransform(body.getPosition(), angle);
	}
	
	/**
	 * Bullets are shot from the position of the player's gun
	 * in the direction the player is facing
	 */
	public void shoot() {
		Bullet bullet = bulletPool.obtain();
		//TODO Set bullet position to exact position of gun
		bullet.setPosition(body.getPosition());

		boolean gunPointingLeft = 0 > body.getLinearVelocity().x;
		bullet.shoot(gunPointingLeft);

		gameScreen.addEntity(bullet);
	}	
	/**
	 * @param canJump if true player can jump, otherwise he can't
	 */
	public void setJumpAbility(boolean canJump) {
		this.canJump = canJump; 
	}
	
	/**
	 * Makes player jump
	 */
	public void jump() {
		if (canJump) {
			Vector2 jump = new Vector2(0, JUMP_POWER);
			boolean wakeForSimulation = true;
			body.applyLinearImpulse(jump, body.getPosition(),
					wakeForSimulation);
		}
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
		
		movePlayer(delta);
		
		// Add delta to current animation key frame time
		// If player is rising/jumping
				// Set player sprite based on jumping animation key frame
		// If player is falling
				// Set player sprite based on falling animation key frame
		// If player is running, i.e. movement is not zero
				// Set player sprite based on running animation key frame
		
		// Flip player sprite so that if he's moving left, i.e. movement + xForce is negative,
		// the sprite is facing left and vice versa if he is moving right
		
		// Set player's sprite position & angle to match the new position of player's physical body
		// Return player sprite after it's position/angle has been updated
		return null;
	}
	
	private void movePlayer(float delta) {
		float horizontalMovement = (movement + additionalHorizontalForce)
				* onSticky;
		body.setLinearVelocity(horizontalMovement * delta,
				body.getLinearVelocity().y * delta);
	}
	
	public void moveLeft() {		
		movement = -MOVEMENT_SPEED;
	}

	public void moveRight() {
		movement = MOVEMENT_SPEED;
	}

	public void stopMoving() {
		movement = 0;
	}
	
	/**
	 * Allows application of additional horizontal force on player,
	 * for example, if standing on a push/pull, i.e. conveyor belt, platform
	 * 
	 * @param horizontalForce
	 */
	public void additionalExternalHorizontalForce(float horizontalForce) {
		additionalHorizontalForce = horizontalForce;
	}
	
	/**
	 * Halves normal player horizontal speed
	 */
	public void setOnStickyPlatform() {
		onSticky  = 0.5f;
	}
	
	/**
	 * Restores normal player horizontal speed
	 */
	public void setOffStickyPlatform() {
		onSticky = 1;
	}
}