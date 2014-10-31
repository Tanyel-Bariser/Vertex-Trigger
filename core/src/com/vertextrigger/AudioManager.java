package com.vertextrigger;

/**
 * Manages the playing and stopping of music and sound effects
 */
public class AudioManager {

	/**
	 * Initialises music and sound effect variables
	 */
	public AudioManager() {
		// Create music, that loops, and sound effects
		// Create container of all music
	}

	/**
	 * Plays main menu music, while preventing other music from
	 * clashing with the main menu music and it only plays
	 * if the user has not muted the music in the setting
	 */
	public void playMainScreenMusic() {
		// Stop other music from playing
		// If user has not muted the music in settings
		// And the main menu music is not already playing
				// Play main menu screen music
	}

	/**
	 * Plays level one music, while preventing other music from
	 * clashing with the level one music and it only plays
	 * if the user has not muted the music in the setting
	 */
	public void playLevelOneMusic() {
		// Stop other music from playing
		// If user has not muted the music in settings
		// And the level one music is not already playing
				// Play main menu screen music
	}

	/**
	 * Plays level two music, while preventing other music from
	 * clashing with the level two music and it only plays
	 * if the user has not muted the music in the setting
	 */
	public void playLevelTwoMusic() {
		// Stop other music from playing
		// If user has not muted the music in settings
		// And the level two music is not already playing
				// Play main menu screen music
	}
	
	/**
	 * Checks if each music is playing and stops it
	 */
	public void stopMusic() {
		// For each music
				// If music is playing
						// Stop  music
	}
	
	/**
	 * Plays sound effect of player jumping as feedback
	 * for user signifying they have jumped 
	 */
	public void playJumpSound() {
		// Play a jumping sound effect
	}
	
	/**
	 * Plays rewarding sound effect as feedback for user
	 * signifying they have pick up something advantageous 
	 */
	public void playPickUpSound() {
		// Play a rewarding sound effect
	}

	/**
	 * Plays sound of gun firing as feedback for user
	 * signifying they have shot their gun
	 */
	public void playShootSound() {
		// Play gun shot sound effect
	}

	/**
	 * Plays sound effect of bullet ricocheting
	 * as feedback for user
	 */
	public void playRicochetSound() {
		// Play bullet richocheting sound effect
	}
	
	/**
	 * Play sound effect to inform user the player has
	 * been killed.
	 */
	public void playPlayerKilledSound() {
		// Play kill sound effect
	}

	/**
	 * Play sound effect to inform user an enemy has
	 * been killed.
	 */
	public void playEnemyKilledSound() {
		// Play enemy kill sound effect
	}
}