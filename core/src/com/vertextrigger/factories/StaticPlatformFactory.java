package com.vertextrigger.factories;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 */
public class StaticPlatformFactory {

	/**
	 * @param world
	 * @param name
	 * @param spriteWidth
	 * @param spriteHeight
	 * @param bodyWidth
	 * @param bodyHeight
	 * @return
	 */
	public static Sprite createStickyPlatform(World world, String name,
			float spriteWidth, float spriteHeight, float bodyWidth,
			float bodyHeight) {
		// Set identifier label of fixture to "StickyPlatform"
		return null;
	}
	

	/**
	 * @param world
	 * @param name
	 * @param spriteWidth
	 * @param spriteHeight
	 * @param bodyWidth
	 * @param bodyHeight
	 * @return
	 */
	public static Sprite createSlipperyPlatform(World world, String name,
			float spriteWidth, float spriteHeight, float bodyWidth,
			float bodyHeight) {
		// Set identifier label of fixture to "SlipperyPlatform"
		return null;
	}
	

	/**
	 * @param world
	 * @param name
	 * @param spriteWidth
	 * @param spriteHeight
	 * @param bodyWidth
	 * @param bodyHeight
	 * @return
	 */
	public static Sprite createBouncyPlatform(World world, String name,
			float spriteWidth, float spriteHeight, float bodyWidth,
			float bodyHeight) {
		// Set identifier label of fixture to "BouncyPlatform"
		return null;
	}
}