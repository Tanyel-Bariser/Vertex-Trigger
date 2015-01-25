package com.vertextrigger.factories;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.entities.Entity;
import com.vertextrigger.util.Coordinate;

/**
 * PERHAPS INCLUDE CREATION FOR ALL TYPES OF PLATFORM!!!
 */
public class StaticPlatformFactory {	
	
	/**
	 * @param world platform resides in
	 * @param spriteName name of platform sprite in texture atlas
	 * @param userData is the identifier label of platform
	 * @param spriteWidth
	 * @param spriteHeight
	 * @param bodyWidth
	 * @param bodyHeight
	 * @return platform's sprite
	 */
	public static Sprite createStaticPlatform(World world, String spriteName, String userData,
			float spriteWidth, float spriteHeight, float bodyWidth,
			float bodyHeight) {
		// Set identifier label of fixture as the platform's userData
		return null;
	}
}