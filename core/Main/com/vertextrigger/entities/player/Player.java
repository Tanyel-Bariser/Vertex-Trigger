package com.vertextrigger.entities.player;

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
	static final float JUMP_POWER = 100 * (GameObjectSize.OBJECT_SIZE / 15f);
	static final float MOVEMENT_SPEED = 20 * GameObjectSize.OBJECT_SIZE;
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
		body.setLinearVelocity(movement, body.getLinearVelocity().y);
		animator.setHorizontalMovement(body.getLinearVelocity().x);
		if (isShieldSet == false && shield != null) {
			body.createFixture(shield.getFixtureDef()).setUserData(shield);
			shield.setPlayerBody(body);
			isShieldSet = true;
			gun.setShielded();
		}

		if (isShieldSet) {
			magnetBehaviour.calculateSteering();
			magnetBehaviour.applySteering(delta);
		}
		return super.update(delta, alpha);
	}

	public void setShield(final Shield shield) {
		this.shield = shield;
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