package com.vertextrigger.levelbuilders;

import com.badlogic.gdx.physics.box2d.World;

/**
 * A prototype level to allow manual testing of player controls & game objects
 */
public class PrototypeLevelBuilder extends LevelBuilder {
	
	public PrototypeLevelBuilder() {
		// Load assets required for level one,
		// while unloading unneeded assets
		// Play level one music
	}

	@Override
	protected void createPlayer(World world) {
	}

	@Override
	protected void createEnemies(World world) {
	}

	@Override
	protected void createDangerousBalls(World world) {
	}

	@Override
	protected void createMovingPlatforms(World world) {
	}

	@Override
	protected void createTimedPlatforms(World world) {
	}

	@Override
	protected void createStaticPlatforms(World world) {
	}

	@Override
	protected void createGroundWalls(World world) {
	}

	@Override
	public void resetLevelLayout() {
	}
}