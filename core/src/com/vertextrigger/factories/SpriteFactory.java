package com.vertextrigger.factories;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Encapsulates the creation of sprites
 */
public class SpriteFactory {

	/**
	 * Initialises storage of sprites that can be accessed by name
	 */
	public static void initialiseSpriteFactory() {
		// Create a sprite storage
	}

	/**
	 * @return main menu background sprite
	 */
	public static Sprite getMainMenuBackground() {
		// Get main menu background image from assets & return it
		return null;
	}

	/**
	 * @return level one background sprite
	 */
	public static Sprite getLevelOneBackground() {
		// Get level one background image from assets & return it
		return null;
	}

	/**
	 * @return level two background sprite
	 */
	public static Sprite getLevelTwoBackground() {
		// Get level two background image from assets & return it
		return null;
	}

	/**
	 * @return title sprite
	 */
	public Sprite getTitle() {
		// Get title image for main screen from assets
		// Set size & return it
		return null;
	}

	/**
	 * @param name of player sprite in texture atlas
	 * @param width of player sprite
	 * @param height of player sprite
	 * @return player sprite
	 */
	public Sprite getPlayerSprite(String name, float width, float height) {
		// Get a specific player sprite from assets by its name
		// Set player sprite size
		// Set player origin as middle of sprite
		// Return player sprite
		return null;
	}

	/**
	 * @param name of enemy sprite in texture atlas
	 * @param width of enemy sprite
	 * @param height of enemy sprite
	 * @return enemy sprite
	 */
	public Sprite getEnemySprite(String name, float width, float height) {
		// Get a specific enemy sprite from assets by its name
		// Set enemy sprite size
		// Set enemy origin as middle of sprite
		// Return enemy sprite
		return null;
	}

	/**
	 * @param name of dangerous ball sprite in texture atlas
	 * @param radius of dangerous ball sprite
	 * @return dangerous ball sprite
	 */
	public Sprite getDangerousBallSprite(String name, float radius) {
		// Get a specific dangerous ball sprite from assets by its name
		// Set dangerous ball sprite size
		// Set dangerous ball origin as middle of sprite
		// Return dangerous ball sprite
		return null;
	}
	
	/**
	 * @param name of platform sprite in texture atlas
	 * @param width of platform sprite
	 * @param height of platform sprite
	 * @return platform sprite
	 */
	public Sprite getPlatformSprite(String name, float width, float height) {
		// Get a specific platform sprite from assets by its name
		// Set platform sprite size
		// Return platform sprite
		return null;
	}
}