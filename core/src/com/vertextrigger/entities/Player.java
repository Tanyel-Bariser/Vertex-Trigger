package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.vertextrigger.Coordinate;

/**
 * Main character of the game
 * This class manages the player's physical body & its movements & sprite animation
 */
public class Player {

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
	 * Sets angle of player
	 */
	public void setAngle(float angle) {
		// Set player's new angle
	}
	
	/**
	 * Makes player jump
	 */
	public void jump() {
		// Thrust player upwards
	}
	
	
	public void move(float delta) {
		// Move player either left or right, according to the force variable,
		// to an amount dependent on delta 
	}
	
	public Sprite updateSprite(float delta) {
		// Add delta to current animation key frame time
		// If player is rising/jumping
				// Set player sprite based on jumping animation key frame
		// If player is falling
				// Set player sprite based on falling animation key frame
		// If player is moving left
				// Set player sprite based on running animation key frame
		// Flip player sprite so that if he's moving left the sprite
		// is facing left and vice versa if he is moving right
		// Return updated sprite
		return null;
	}
}