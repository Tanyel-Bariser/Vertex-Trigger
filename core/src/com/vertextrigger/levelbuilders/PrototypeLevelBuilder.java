package com.vertextrigger.levelbuilders;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

/**
 * A prototype level to allow manual testing of player controls & game objects
 */
public class PrototypeLevelBuilder  implements LevelBuilder {
	private World world;
	
	public PrototypeLevelBuilder() {
		// Load assets required for level one,
		// while unloading unneeded assets
		// Play level one music
	}
	
	/**
	 * @param world for all game objects in this level to reside in
	 */
	@Override
	public void setWorld(World world) {
		// Set game world for this level
	}
	
	@Override
	public Entity createPlayer() {
		return null;
	}

	@Override
	public Array<Entity> createEnemies() {
		return null;
	}

	@Override
	public Array<Entity> createDanerousBalls() {
		return null;
	}

	@Override
	public Array<Entity> createMovingPlatforms() {
		return null;
	}

	@Override
	public Array<Entity> createTimedPlatforms() {
		return null;
	}

	@Override
	public Array<Sprite> createStaticPlatforms() {
		return null;
	}

	@Override
	public Array<Sprite> createGroundWalls() {
		return null;
	}

	/**
	 * Resets the level layout when player has died & repositions
	 * the player back to the initial position of the level
	 * 
	 * DO NOT RECREATE GAME OBJECTS, ONLY REPOSITION THEM AS IT'S FASTER
	 */
	@Override
	public void resetLevelLayout() {	
	}
}