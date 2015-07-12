package com.vertextrigger.entities.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.vertextrigger.entities.Entity;
import com.vertextrigger.screens.GameScreen;

/**
 * Main character of the game
 * This class manages the player's physical body & its movements & sprite animation
 */
public class Player implements Entity {
	static final float JUMP_POWER = 200;
	static final float MOVEMENT_SPEED = 5000f;
	private final Body body;
	private final PlayerAnimator animator;
	private final Gun gun;
	private final Vector2 initialPosition;
	private boolean canJump = false;
	private float movement = 0;
	private float additionalHorizontalForce = 0;
	private float onSticky = 1;

	public Player(World world, Vector2 initialPosition, GameScreen gameScreen) {
		this(world, initialPosition, gameScreen, PlayerBodyFactory.getPlayerBody(world, initialPosition), new Gun(world, gameScreen), new PlayerAnimator());
	}
	
	/**
	 * Dependency injection for unit testing
	 */
	Player(World world, Vector2 initialPosition, GameScreen gameScreen, Body body, Gun gun, PlayerAnimator animator) {
		this.initialPosition = initialPosition;
		this.body = body;
		this.gun = gun;
		this.animator = animator;
	}
	
	public Body getBody() {
		return body;
	}
	
	/**
	 * Reset player position to the initial position of the level he's in
	 */
	public void diedResetPosition() {
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
		gun.shoot(body.getPosition(), body.getLinearVelocity().x);
	}
	
	public void setCanJump() {
		canJump = true; 
	}
	
	public void setCannotJump() {
		canJump = false;
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
		gun.freeExpiredBullets();
		movePlayer(delta);
		setAnimationType();
		animator.setHorizontalMovement(body.getLinearVelocity().x);
		return animator.getUpdatedSprite(delta, body.getAngle(), body.getPosition());
	}
	
	private void setAnimationType() {
		if (body.getLinearVelocity().y > 3) {
			animator.setAnimationRising();
		} else {
			animator.setAnimationStanding();
		}
	}
	
	private void movePlayer(float delta) {
		float totalHorizontalMovement = (movement + additionalHorizontalForce)
				* onSticky;
		body.setLinearVelocity(totalHorizontalMovement * delta,
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