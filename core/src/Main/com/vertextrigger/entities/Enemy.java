package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

/**
 * Enemies can kill the player if touched & follows a predefined path
 * This class manages an enemy's physical body & its movements & sprite animation
 */
public abstract class Enemy implements Entity {
	// Predefined path for enemy's physical body to follow
	protected Path path;
	
	/**
	 * Creates enemy's physical body & its physical properties
	 * Creates sprite & animation factories
	 * 
	 * @param world the enemy will reside in
	 * @param coordinates is the path, series of x & y coordinates, the enemy follows
	 */
	public Enemy(World world, Array<Vector2> coordinates) {
		// Set enemy sprites & animations
		// Create physical body
		// Set the coordinates of the predefined path
		// for the enemy to follow in a loop
	}
	
	/**
	 * Create enemy's physical body & physical properties.
	 * 
	 * @param world the enemy will reside in
	 * @param position of enemy in game world
	 * @return physical body of enemy
	 */
	protected abstract Body createEnemy(World world, Vector2 position);
	
	/**
	 * Create & set all sprites & animations the enemy will need
	 */
	protected abstract void spriteAnimationSetup();
	
	/**
	 * Chooses appropriate enemy sprite based on animation.
	 * 
	 * @return updated enemy's sprite
	 */
	/* PSEUDOCODE FOR THIS ABSTRACT METHOD */
	// Add delta to current animation key frame time
	// If enemy is rising/jumping
			// Set enemy sprite based on jumping animation key frame
	// If enemy is falling
			// Set enemy sprite based on falling animation key frame
	// If enemy is moving left
			// Set enemy sprite based on running animation key frame
	// Flip enemy sprite so that if he's moving left the sprite
	// is facing left and vice versa if he is moving right
	protected abstract Sprite updateSprite(float delta);
	
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
		// Move enemy further along it's predefined path based on delta
		// Update enemy sprite based on animation
		// Set enemy's sprite position & angle to match
		// the new position of enemy's physical body
		// Return enemy sprite after it's position/angle has been updated
		return null;
	}
}