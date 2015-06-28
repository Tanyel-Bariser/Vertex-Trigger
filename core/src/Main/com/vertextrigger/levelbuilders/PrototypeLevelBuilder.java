package com.vertextrigger.levelbuilders;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.vertextrigger.factories.SpriteFactory;

/**
 * A prototype level to allow manual testing of player controls & game objects
 */
public class PrototypeLevelBuilder extends LevelBuilder {
	SpriteFactory spf;
	
	public PrototypeLevelBuilder() {
		spf = new SpriteFactory();
		// Load assets required for level one,
		// while unloading unneeded assets
		// Play level one music
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
		Sprite platform = spf.createPlatform("platform", 100f, 20f);
		sprites.add(platform);
	}	

	@Override
	protected void createGroundWalls(World world) {
	}

	@Override
	public void resetLevelLayout() {
	}

	@Override
	public Vector2 getInitialPosition() {
		return null;
	}
}