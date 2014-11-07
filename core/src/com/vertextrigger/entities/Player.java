package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.vertextrigger.Coordinate;

/**
 * Main character of the game
 * This class manages the player's physical body & its movements & sprite animation
 */
public class Player implements Entity {

	/**
	 * Creates player's physical body & its physical properties
	 * Creates sprite & animation factories
	 * 
	 * @param world the player will reside in
	 * @param startingPosition of the player in a particular level
	 */
	public Player(World world, Coordinate startingPosition) {
		// Create & set sprite factory
		// Create & set animation factory
		// Initialise physical properties, i.e. polygon shape, dynamic body, etc.
		// Set starting position
		// Create physical body
		// Set identifier label as "Player"
		// Create & set sprites & animations
	}
	
	/**
	 * Initialise sprites & animation objects
	 */
	private void spriteAnimationSetup() {
		// Create & set all sprites & animations the player will need
	}
	
	/**
	 * Setter for the player's directional movement, left or right.
	 * Negative force moves the player left
	 * Positive force moves the player right
	 * 
	 * @param force that moves player either left or right
	 */
	public void setForce(float force) {
		// Set player's new directional force
		// If force is negative
				// Set player facing left
		// If force is positive
				// Set player facing right
	}
	
	/**
	 * Usually caused by the platform the player is standing on. 
	 * For example, a conveyor belt platform can increase/decrease force, 
	 * i.e. by a positive or negative parameter respectively, dependent on
	 * which direction the conveyor belt platform is moving.  
	 * 
	 * @param force amount to increase player's directional force by
	 */
	public void alterForce(float force) {
		// Alter directional (left or right movement)
		// force by a specific amount 
	}
	
	/**
	 * Sets angle of player
	 */
	public void setAngle(float angle) {
		// Set player's new angle
	}
	
	/**
	 * @param canJump if true player can jump, otherwise he can't
	 */
	public void setJumpAbility(boolean canJump) {
		// Set whether or not the player can jump
	}
	
	/**
	 * @return whether or not the player is allowed to jump
	 */
	private boolean getJumpAbility() {
		// Return whether or not the player is allowed to jump
		return false;
	}
	
	/**
	 * Makes player jump
	 */
	public void jump() {
		// Thrust player upwards
	}
	
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
		// Move player either left or right, according to the force variable,
		// to an amount dependent on delta
		
		// Add delta to current animation key frame time
		// If player is rising/jumping
				// Set player sprite based on jumping animation key frame
		// If player is falling
				// Set player sprite based on falling animation key frame
		// If player is moving left
				// Set player sprite based on running animation key frame
		// Flip player sprite so that if he's moving left the sprite
		// is facing left and vice versa if he is moving right
		
		// Set player's sprite position & angle to match
		// the new position of player's physical body
		// Return player sprite after it's position/angle has been updated
		return null;
	}
}