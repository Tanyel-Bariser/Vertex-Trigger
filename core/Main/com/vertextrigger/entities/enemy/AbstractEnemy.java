package com.vertextrigger.entities.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.entities.AnimationSet;
import com.vertextrigger.entities.Animator;
import com.vertextrigger.entities.Mortal;
import com.vertextrigger.entities.Path;
import com.vertextrigger.util.AudioManager;

/**
 * Enemies can kill the player if touched & follows a predefined path
 * This class manages an enemy's physical body & its movements & sprite animation
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
	
	public AbstractEnemy(Array<Vector2> coordinates, Body body, AnimationSet animationSet) {
		path = null;
		this.body = body;
		this.animationSet = animationSet;
		isDeathAnimationFinished = false;		
		isDead = false;
		animator = new Animator(animationSet);
		animator.setEntity(this);
		setUserData(body);
	}

	@Override
	public Body getBody() {
		return body;
	}

	/**
	 * Moves the enemy further along its predefined
	 * path with the distance moved dependent on the delta.
	 * Delta is needed to frame rate independent movement.
	 * 
	 * Chooses appropriate enemy sprite based on animation.
	 * Returns the updated enemy's sprite for rendering.
	 * 
	 * @param delta time passed between previous & current frame
	 * @return updated enemy sprite
	 */
	@Override
	public Sprite update(float delta) {
		if (newPositionFromPortal != null) {
			body.setTransform(newPositionFromPortal, 0);
			setNewPositionFromPortal(null);
		}
		return animator.getUpdatedSprite(delta, body.getAngle(), body.getPosition());
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
	public void setUserData(Body body) {
		body.setUserData(this);
		for (Fixture fix : body.getFixtureList()) {
			fix.setUserData(this);
		}
	}
	
	@Override
	public void setNewPositionFromPortal(Vector2 newPositionFromPortal) {
		this.newPositionFromPortal = newPositionFromPortal;
	}
}