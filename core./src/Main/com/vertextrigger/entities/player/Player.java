package com.vertextrigger.entities.player;
import static com.vertextrigger.util.GameObjectSize.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.vertextrigger.entities.Animator;
import com.vertextrigger.entities.Mortal;
import com.vertextrigger.util.ContactBody;
import com.vertextrigger.util.GameObjectSize;

/**
 * Main character of the game
 * This class manages the player's physical body & its movements & sprite animation
 */
public class Player implements Mortal {
	static final float JUMP_POWER = 600f;
	static final float MOVEMENT_SPEED = 1500;
	private final Body body;
	private final Animator animator;
	private final Gun gun;
	private final Vector2 initialPosition;
	private boolean canJump = false;
	private float movement = 0;
	private float additionalHorizontalForce = 0;
	private float onSticky = 1;
	private boolean keepJumping;
	boolean isFacingLeft;
	
	public Player(Vector2 initialPosition, Body body, Gun gun, Animator animator) {
		this.initialPosition = initialPosition;
		this.body = body;
		this.gun = gun;
		this.animator = animator;
		animator.setEntity(this);
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
	
	public void setAngle(float angle) {
		body.setTransform(body.getPosition(), angle);
	}
	
	public void setFacingLeft() {
		isFacingLeft = true;
	}
	
	public void setFacingRight() {
		isFacingLeft = false;
	}
	
	/**
	 * Bullets are shot from the position of the player's gun
	 * in the direction the player is facing
	 */
	public void shoot() {
		gun.shoot(body.getPosition(), isFacingLeft);
		animator.setAnimationShooting();
		isShooting = true;
		Timer.schedule(new Task() {
			@Override
			public void run() {
				isShooting = false;
			}
		}, 0.1f);
	}
	
	public void setCanJump() {
		canJump = true; 
	}
	
	public void setCannotJump() {
		canJump = false;
	}

	public void setKeepJumping() {
		keepJumping = true;
	}
	
	public void setStopJumping() {
		keepJumping = false;
	}
	
	public boolean isKeepJumping() {
		return keepJumping;
	}
	
    public void jump() {
		if (canJump || keepJumping) {
			boolean wakeForSimulation = true;
			body.applyLinearImpulse(0, JUMP_POWER, body.getWorldCenter().x,
					body.getWorldCenter().y, wakeForSimulation);
			setKeepJumping();
		}
	}

	boolean isShooting;
	private boolean isDeathAnimationFinished;
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
		gun.destroyTouchingBullets();
		movePlayer(delta);
		if (!isShooting) {
			animator.setAnimationType();
		}
		animator.setHorizontalMovement(body.getLinearVelocity().x);
		return animator.getUpdatedSprite(delta, body.getAngle(), body.getPosition());
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
	
	public void spinLikeCrazy() {
		body.setTransform(body.getPosition().x, body.getPosition().y, body.getAngle()+2);
	}

	@Override
	public float getOffsetX() {
		return GameObjectSize.PLAYER_SIZE.getOffsetX();
	}

	@Override
	public float getOffsetY() {
		return GameObjectSize.PLAYER_SIZE.getOffsetY();
	}


	@Override
	public void die() {
		animator.playDeathAnimation(this);
	}

	@Override
	public void setDeathAnimationFinished() {
		isDeathAnimationFinished = true;
	}
	
	@Override
	public boolean isDeathAnimationFinished() {
		return isDeathAnimationFinished;
	}
	
	@Override
	public boolean isDead() {
		return getBody().getUserData() == ContactBody.DEAD;
	}
}