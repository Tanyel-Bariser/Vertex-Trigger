package com.vertextrigger.levelbuilders;

import com.badlogic.gdx.physics.box2d.World;
import com.vertextrigger.screens.GameScreen;

/**
 * 
 */
public class LevelOneBuilder extends LevelBuilder {
		
	public LevelOneBuilder() {
		// Load assets required for level one,
		// while unloading unneeded assets
		// Play level one music
	}

	@Override
	protected void createPlayer(World world, GameScreen gameScreen) {
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