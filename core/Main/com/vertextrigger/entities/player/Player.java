package com.vertextrigger.entities.player;

import java.util.*;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.vertextrigger.ai.*;
import com.vertextrigger.assets.AudioManager;
import com.vertextrigger.entities.*;
import com.vertextrigger.util.GameObjectSize;

/**
 * Main character of the game This class manages the player's physical body & its movements & sprite animation
 */
public class Player extends AbstractEntity implements Mortal {
	static final float JUMP_POWER = 95 * (GameObjectSize.OBJECT_SIZE / 15f);
	static final float MOVEMENT_FORCE = 100 * GameObjectSize.OBJECT_SIZE;
	private static final float MOVEMENT_FORCE_WITH_SHIELD = MOVEMENT_FORCE / 2;
	private final Gun gun;
	private final Vector2 initialPosition;
	private boolean canJump = false;
	private float movement = 0;
	private final SteerableBody steerable;
	private Shield shield;
	private boolean isShieldSet = false;
	private final MagnetBehaviour magnetBehaviour;
	private final Map<Vector2, EntityCallback> positionCallbacks = new HashMap<>();

	public Player(final Vector2 initialPosition, final Body body, final Gun gun, final Animator animator, final SteerableBody steerable,
			final MagnetBehaviour magnetBehaviour) {
		super(body, animator);
		this.initialPosition = initialPosition;
		this.gun = gun;
		this.steerable = steerable;
		this.magnetBehaviour = magnetBehaviour;
	}

	// TODO clean up as this is a hack but could be very useful
	public void addPositionCallback(final Vector2 position, final EntityCallback entityCallback) {
		positionCallbacks.put(position, entityCallback);
	}

	/**
	 * Reset player position to the initial position of the level he's in
	 */
	public void diedResetPosition() {
		body.setTransform(initialPosition, 0);
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
		if (isMovingLeftAndAllowIncreaseMovementSpeed() || isMovingRightAndAllowIncreaseMovementSpeed()) {
			if (canJump) {
				body.applyForceToCenter(movement, 0, true);
			} else {
				// Movement slower when in air to compensate for lack of friction with floor
				body.applyForceToCenter(movement / 3, 0, true);
			}
		}
		animator.setHorizontalMovement(body.getLinearVelocity().x);
		if (isShieldSet == false && shield != null) {
			body.createFixture(shield.getFixtureDef()).setUserData(shield);
			shield.setPlayerBody(body);
			isShieldSet = true;
			gun.setShielded();
			setMovementSpeedWithShield();
		}

		if (isShieldSet) {
			magnetBehaviour.calculateSteering();
			magnetBehaviour.applySteering(delta);
		}

		// TODO clean up as this is a hack but could be very useful
		final Vector2 position = body.getPosition();
		for (final Vector2 pos : positionCallbacks.keySet()) {
			if (position.x > pos.x - 0.5f && position.x < pos.x + 0.5f && position.y > pos.y - 0.5f && position.y < pos.y + 0.5f) {
				positionCallbacks.get(pos).run();
			}
		}

		return super.update(delta, alpha);
	}

	private boolean isMovingLeftAndAllowIncreaseMovementSpeed() {
		// movement is negative when moving left
		return movement < 0 && body.getLinearVelocity().x > movement / 5;
	}

	private boolean isMovingRightAndAllowIncreaseMovementSpeed() {
		return movement > 0 && body.getLinearVelocity().x < movement / 5;
	}

	private void setMovementSpeedWithShield() {
		if (Float.compare(movement, MOVEMENT_FORCE) == 0) {
			movement = MOVEMENT_FORCE_WITH_SHIELD;
		} else if (Float.compare(movement, -MOVEMENT_FORCE) == 0) {
			movement = -MOVEMENT_FORCE_WITH_SHIELD;
		}
	}

	public void setShield(final Shield shield) {
		this.shield = shield;
	}

	public void moveLeft() {
		movement = isShieldSet ? -MOVEMENT_FORCE_WITH_SHIELD : -MOVEMENT_FORCE;
	}

	public void moveRight() {
		movement = isShieldSet ? MOVEMENT_FORCE_WITH_SHIELD : MOVEMENT_FORCE;
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
		super.setDead();
	}

	@Override
	public void die() {
		AudioManager.playPlayerKilledSound();
		animator.playDeathAnimation(this);
	}

	@Override
	public void setUserData() {
		body.setUserData(this);
		for (final Fixture fix : body.getFixtureList()) {
			if (fix.getUserData() instanceof PlayerFeet) {
				((PlayerFeet) fix.getUserData()).setPlayer(this);
			} else {
				fix.setUserData(this);
			}
		}
	}

	public Steerable<Vector2> getSteerable() {
		return steerable;
	}

	public boolean isShielded() {
		return shield != null;
	}
}