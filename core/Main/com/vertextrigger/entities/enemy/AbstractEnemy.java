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

/**
 * Enemies can kill the player if touched & follows a predefined path
 * This class manages an enemy's physical body & its movements & sprite animation
 */
public abstract class AbstractEnemy implements Mortal {
	// Predefined path for enemy's physical body to follow
	protected Path path;
	protected Body body;
	protected AnimationSet animationSet;
	protected Animator animator;
	private boolean isDead;
	private Vector2 newPositionFromPortal;
	
	public AbstractEnemy(Array<Vector2> coordinates, Body body, AnimationSet animationSet) {
		path = null;
		this.body = body;
		this.animationSet = animationSet;
		isDead = false;
		animator = new Animator(animationSet);
		animator.setEntity(this);
		setUserData(body);
	}
		
	/**
	 * Create & set all sprites & animations the enemy will need
	 */
	protected abstract void spriteAnimationSetup();

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
		// Move enemy further along it's predefined path based on delta
		// Update enemy sprite based on animation
		// Set enemy's sprite position & angle to match
		// the new position of enemy's physical body
		// Return enemy sprite after it's position/angle has been updated		
		return null;
	}
	
	@Override
	public boolean isDead() {
		return isDead;
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