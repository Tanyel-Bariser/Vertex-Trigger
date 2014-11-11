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
	public Player(World world, Coordinate initialPosition) {
		// Set sprites & animations
		// Initialise physical properties, i.e. polygon shape, dynamic body, etc.
		// Set initial position of player in the level
		// Create physical body
		// Set identifier label as "Player" (for fixture not body)
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
	 * Negative movement moves the player left
	 * Positive movement moves the player right
	 * 
	 * @param movement of player either left or right
	 */
	public void setMovement(float movement) {
		// Set player's new directional force in x-axis
	}
	
	/**
	 * Negative movement means the player is moving left
	 * Positive movement means the player is moving right
	 * 
	 * @return the speed & direction the player is moving horizontally
	 */
	private float getMovement() {
		// Return the magnitude of the player's
		// movement in either left or right direction
		return 0;
	}
	
	/**
	 * Additional force imposed on the player usually caused by the
	 * platform the player is standing on. 
	 * For example, a conveyor belt platform can push the player left or right, 
	 * i.e. by a negative or positive parameter respectively, dependent on
	 * which direction the conveyor belt platform is moving.  
	 * 
	 * @param xForce amount to push or pull the player left or right
	 */
	public void setXForce(float xForce) {
		// Alter directional (left or right movement)
		// force by a specific amount 
	}
	
	/**
	 * @param downForce additional force on player's y-axis
	 */
	public void setDownForce(float downForce) {
		// Set additional vertical force to be put on player 
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
	 * If onSticky is true, then player is on a sticky
	 * platform & therefore moves at a slower speed.
	 * 
	 * @param onSticky
	 */
	public void setOnSticky(boolean onSticky) {
		// Set whether or not the player is on a sticky platform
	}
	
	/**
	 * @return true if player is on a sticky platform, else false
	 */
	private boolean getOnSticky() {
		// Return whether or not the player is on a skicky platform
		return false;
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
		// If player is on a sticky platform move at half the directional speed
		// Move player either left or right, according to the movement variable,
		// to an amount dependent on delta
		
		// Add delta to current animation key frame time
		// If player is rising/jumping
				// Set player sprite based on jumping animation key frame
		// If player is falling
				// Set player sprite based on falling animation key frame
		// If player is running, i.e. movement is not zero
				// Set player sprite based on running animation key frame
		
		// Flip player sprite so that if he's moving left, i.e. movement + xForce is negative,
		// the sprite is facing left and vice versa if he is moving right
		
		// Set player's sprite position & angle to match
		// the new position of player's physical body
		// Return player sprite after it's position/angle has been updated
		return null;
	}
}