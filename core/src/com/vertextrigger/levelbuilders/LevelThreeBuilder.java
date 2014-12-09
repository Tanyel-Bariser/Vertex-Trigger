package com.vertextrigger.levelbuilders;

import com.badlogic.gdx.physics.box2d.World;
import com.vertextrigger.screens.GameScreen;

/**
 * 
 */
public class LevelThreeBuilder extends LevelBuilder {
	
	public LevelThreeBuilder() {
		// Load assets required for level three,
		// while unloading unneeded assets
		// Play level three music
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