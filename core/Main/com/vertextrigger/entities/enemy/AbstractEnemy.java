package com.vertextrigger.entities.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.assets.AudioManager;
import com.vertextrigger.entities.*;

/**
 * Enemies can kill the player if touched & follows a predefined path This class manages an enemy's physical body & its movements & sprite animation
 */
public abstract class AbstractEnemy implements Mortal {
	protected Path path;
	protected Body body;
	protected AnimationSet animationSet;
	protected Animator animator;
	protected boolean isDead;
	protected Vector2 newPositionFromPortal;
	protected boolean facingLeft;
	protected boolean isDeathAnimationFinished;
	private final InterpolatedPosition enemyState;
	private boolean isTeleportable = true;

	public AbstractEnemy(final Array<Vector2> coordinates, final Body body, final AnimationSet animationSet) {
		path = null;
		this.body = body;
		this.animationSet = animationSet;
		isDeathAnimationFinished = false;
		isDead = false;
		animator = new Animator(animationSet);
		animator.setEntity(this);
		setUserData(body);
		enemyState = new InterpolatedPosition(this.body);
	}

	@Override
	public Body getBody() {
		return body;
	}

	/**
	 * Moves the enemy further along its predefined path with the distance moved dependent on the delta. Delta is needed to frame rate independent
	 * movement.
	 *
	 * Chooses appropriate enemy sprite based on animation. Returns the updated enemy's sprite for rendering.
	 *
	 * @param delta
	 *            time passed between previous & current frame
	 * @return updated enemy sprite
	 */
	@Override
	public Sprite update(final float delta, final float alpha) {
		if (newPositionFromPortal != null) {
			body.setTransform(newPositionFromPortal, 0);
			setNewPositionFromPortal(null);
		}
		return animator.getUpdatedSprite(delta, enemyState.getNewPosition(alpha, body), enemyState.getNewAngle(alpha, body));
		// Move enemy further along it's predefined path based on delta
		// Update enemy sprite based on animation
		// Set enemy's sprite position & angle to match
		// the new position of enemy's physical body
		// Return enemy sprite after it's position/angle has been updated
	}

	@Override
	public void setFacingLeft() {
		facingLeft = true;
	}

	@Override
	public void setFacingRight() {
		facingLeft = false;
	}

	@Override
	public boolean isDead() {
		return isDead;
	}

	@Override
	public void setDead() {
		isDead = true;
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
	public void die() {
		AudioManager.playEnemyKilledSound();
		animator.playDeathAnimation(this);
	}

	@Override
	public void setUserData(final Body body) {
		body.setUserData(this);
		for (final Fixture fix : body.getFixtureList()) {
			fix.setUserData(this);
		}
	}

	@Override
	public void setNewPositionFromPortal(final Vector2 newPositionFromPortal) {
		this.newPositionFromPortal = newPositionFromPortal;
	}

	@Override
	public void cachePosition() {
		enemyState.setState(body);
	}

	@Override
	public boolean isTeleportable() {
		return isTeleportable;
	}

	@Override
	public void exitedPortal() {
		isTeleportable ^= true;
	}
}