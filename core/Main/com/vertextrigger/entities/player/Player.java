package com.vertextrigger.entities.player;

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
import com.vertextrigger.screen.AbstractGameScreen;
import com.vertextrigger.util.GameObjectSize;

/**
 * Main character of the game This class manages the player's physical body & its movements & sprite animation
 */
public class Player extends AbstractEntity implements Mortal {
	private static int deaths = 0;
	static final float JUMP_POWER = 0.25f;
	static final float MOVEMENT_FORCE = 2.5f;
	private float force;
	private final Gun gun;
	private final Vector2 initialPosition;
	private boolean canJump = false;
	private volatile float movement = 0;
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
		move(delta);
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

	private static final float SPEED_LIMIT = 2.5f;

	private void move(float delta) {
		if (isBelowSpeed(SPEED_LIMIT) == false) {
			return;
		}
		if (delta >= AbstractGameScreen.maxDelta) {
			delta = AbstractGameScreen.maxDelta;
		}

		if (canJump) {
			moveOnGround(delta);
		} else {
			moveInAir(delta);
		}
	}

	private void moveOnGround(final float delta) {
		if (isMovingRight()) {
			force += MOVEMENT_FORCE;
		} else if (isMovingLeft()) {
			force += -MOVEMENT_FORCE;
		} else {
			force = 0;
		}

		force *= 20 * delta;
		if (isShieldSet) {
			force *= 1.2f;
		}

		if (isMovingRight() || isMovingLeft()) {
			body.applyForceToCenter(force, 0, true);
		}
	}

	private void moveInAir(final float delta) {
		float inAirMovement = 0;
		if (isMovingRight()) {
			inAirMovement = MOVEMENT_FORCE * 7 * delta;
		} else if (isMovingLeft()) {
			inAirMovement = -MOVEMENT_FORCE * 7 * delta;
		}
		if (isShieldSet) {
			force *= 1.2f;
		}
		body.applyForceToCenter(inAirMovement, 0, true);
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

		// stop player moving so much after death
		body.setLinearVelocity(0, 0);
		body.setGravityScale(1);

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