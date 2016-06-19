package com.vertextrigger.entities.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.vertextrigger.assets.AudioManager;
import com.vertextrigger.entities.*;
import com.vertextrigger.util.GameObjectSize;

/**
 * Main character of the game This class manages the player's physical body & its movements & sprite animation
 */
public class Player implements Mortal {
	static final float JUMP_POWER = 100 * (GameObjectSize.OBJECT_SIZE / 15f);
	static final float MOVEMENT_SPEED = 20 * GameObjectSize.OBJECT_SIZE;
	private final Body body;
	private final Animator animator;
	private final Gun gun;
	private final Vector2 initialPosition;
	private boolean canJump = false;
	private float movement = 0;
	boolean isFacingLeft;
	private boolean isDead;
	private final InterpolatedPosition playerState;
	boolean isShooting;
	private boolean isDeathAnimationFinished;
	private Vector2 newPositionFromPortal;
	private boolean canTeleport = true;
	private boolean exitedFirstPortal;

	public Player(final Vector2 initialPosition, final Body body, final Gun gun, final Animator animator) {
		this.initialPosition = initialPosition;
		this.body = body;
		this.gun = gun;
		this.animator = animator;
		isDead = false;
		animator.setEntity(this);
		setUserData(body);
		playerState = new InterpolatedPosition(this.body);
	}

	@Override
	public Body getBody() {
		return body;
	}

	/**
	 * Reset player position to the initial position of the level he's in
	 */
	public void diedResetPosition() {
		body.setTransform(initialPosition, 0);
	}

	public void setAngle(final float angle) {
		body.setTransform(body.getPosition(), angle);
	}

	@Override
	public void setFacingLeft() {
		isFacingLeft = true;
	}

	@Override
	public void setFacingRight() {
		isFacingLeft = false;
	}

	/**
	 * Bullets are shot from the position of the player's gun in the direction the player is facing
	 */
	public void shoot() {
		animator.playShootAnimation(gun.shoot(body.getPosition(), isFacingLeft));
	}

	void setCanJump() {
		canJump = true;
	}

	void setCannotJump() {
		canJump = false;
	}

	public void jump() {
		if (canJump) {
			final boolean wakeForSimulation = true;
			AudioManager.playJumpSound();
			body.applyLinearImpulse(0, JUMP_POWER, body.getWorldCenter().x, body.getWorldCenter().y, wakeForSimulation);
		}
	}

	/**
	 * Moves physical body of player left or right. Chooses appropriate player sprite based on animation. Returns the updated player's sprite for
	 * rendering.
	 *
	 * @param delta
	 *            time passed between previous & current frame
	 * @return updated player sprite
	 */
	@Override
	public Sprite update(final float delta, final float alpha) {
		if (newPositionFromPortal != null) {
			body.setTransform(newPositionFromPortal, 0);
			cachePosition();
			setNewPositionFromPortal(null);
		}

		body.setLinearVelocity(movement, body.getLinearVelocity().y);
		animator.setHorizontalMovement(body.getLinearVelocity().x);
		return animator.getUpdatedSprite(delta, playerState.getNewPosition(alpha, body), playerState.getNewAngle(alpha, body));
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

	@Override
	public float getOffsetX() {
		return GameObjectSize.PLAYER_SIZE.getOffsetX();
	}

	@Override
	public float getOffsetY() {
		return GameObjectSize.PLAYER_SIZE.getOffsetY();
	}

	@Override
	public void setDead() {
		stopMoving();
		isDead = true;
	}

	@Override
	public void die() {
		AudioManager.playPlayerKilledSound();
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
		return isDead;
	}

	public Vector2 getPosition() {
		return body.getPosition();
	}

	@Override
	public void setUserData(final Body body) {
		body.setUserData(this);
		for (final Fixture fix : body.getFixtureList()) {
			if (fix.getUserData() instanceof PlayerFeet) {
				((PlayerFeet) fix.getUserData()).setPlayer(this);
			} else {
				fix.setUserData(this);
			}
		}
	}

	@Override
	public void setNewPositionFromPortal(final Vector2 newPositionFromPortal) {
		this.newPositionFromPortal = newPositionFromPortal;
	}

	@Override
	public void cachePosition() {
		playerState.setState(body);
	}

	@Override
	public boolean canTeleport() {
		return canTeleport;
	}

	@Override
	public void enteredPortal() {
		canTeleport = false;
		exitedFirstPortal = false;
	}

	@Override
	public void exitedPortal() {
		if (exitedFirstPortal) {
			canTeleport = true;
		} else {
			exitedFirstPortal = true;
		}
	}
}