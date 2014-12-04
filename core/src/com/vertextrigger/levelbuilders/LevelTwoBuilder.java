package com.vertextrigger.levelbuilders;

import com.badlogic.gdx.physics.box2d.World;

/**
 * 
 */
public class LevelTwoBuilder extends LevelBuilder {
	
	public LevelTwoBuilder() {
		// Load assets required for level two,
		// while unloading unneeded assets
		// Play level two music
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