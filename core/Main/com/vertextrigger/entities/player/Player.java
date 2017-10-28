package com.vertextrigger.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.vertextrigger.ai.MagnetBehaviour;
import com.vertextrigger.ai.SteerableBody;
import com.vertextrigger.assets.AudioManager;
import com.vertextrigger.entities.AbstractEntity;
import com.vertextrigger.entities.Animator;
import com.vertextrigger.entities.Mortal;
import com.vertextrigger.util.GameObjectSize;

/**
 * Main character of the game This class manages the player's physical body & its movements & sprite animation
 */
public class Player extends AbstractEntity implements Mortal {
	private static int deaths = 0;
	static final float JUMP_POWER = 110 * (GameObjectSize.OBJECT_SIZE / 15f);
	static final float MOVEMENT_FORCE = 80 * GameObjectSize.OBJECT_SIZE;
	private final Gun gun;
	private final Vector2 initialPosition;
	private boolean canJump = false;
	private float movement = 0;
	private final SteerableBody steerable;
	private Shield shield;
	private boolean isShieldSet = false;
	private final MagnetBehaviour magnetBehaviour;

	public Player(final Vector2 initialPosition, final Body body, final Gun gun, final Animator animator, final SteerableBody steerable,
			final MagnetBehaviour magnetBehaviour) {
		super(body, animator);
		this.initialPosition = initialPosition;
		this.gun = gun;
		this.steerable = steerable;
		this.magnetBehaviour = magnetBehaviour;
	}

	public static int getDeaths() {
		return deaths;
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
			final float power = isShieldSet ? JUMP_POWER * 1.4f : JUMP_POWER;
			AudioManager.playJumpSound();
			body.applyLinearImpulse(0, power, body.getWorldCenter().x, body.getWorldCenter().y, wakeForSimulation);
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
		applyDirectionalMovement();
		animator.setHorizontalMovement(body.getLinearVelocity().x);
		if (isShieldSet == false && shield != null) {
			body.createFixture(shield.getFixtureDef()).setUserData(shield);
			shield.setPlayerBody(body);
			isShieldSet = true;
			gun.setShielded();
		}

		if (isShieldSet) {
			// magnetBehaviour.calculateSteering();
			// magnetBehaviour.applySteering(delta);// Somewhere in here is the cause of the shield movement bug
		}

		return super.update(delta, alpha);
	}

	private void applyDirectionalMovement() {
		if (isBelowSpeed(2.5f) == false) {// max speed
			return;// fast enough
		}

		// Movement slower when in air to compensate for lack of friction with floor
		float force = canJump ? movement : movement / 2.5f;

		if (isBelowSpeed(0.8f)) {
			force *= 0.3f;
		} else if (isBelowSpeed(1.2f)) {
			Gdx.app.log("Slow", "");
			force *= 0.45f;
		} else if (isBelowSpeed(2.2f)) {
			Gdx.app.log("Medium", "");
			force *= 0.6;
		} else {
			Gdx.app.log("Fast", "");
		}

		body.applyForceToCenter(force, 0, true);
	}

	private boolean isBelowSpeed(final float speed) {
		if (isMovingLeft()) {
			return body.getLinearVelocity().x > -speed;
		} else if (isMovingRight()) {
			return body.getLinearVelocity().x < speed;
		} else {
			return true;
		}
	}

	private boolean isMovingLeft() {
		return movement < 0;
	}

	private boolean isMovingRight() {
		return 0 < movement;
	}

	public void setShield(final Shield shield) {
		this.shield = shield;
	}

	public void moveLeft() {
		movement = -MOVEMENT_FORCE;
	}

	public void moveRight() {
		movement = MOVEMENT_FORCE;
	}

	public void stopMoving() {
		movement = 0;
	}

	@Override
	public boolean isVolitionallyMoving() {
		return movement != 0;
	}

	@Override
	public boolean isFeetOnGround() {
		return canJump;
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
		deaths++;
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