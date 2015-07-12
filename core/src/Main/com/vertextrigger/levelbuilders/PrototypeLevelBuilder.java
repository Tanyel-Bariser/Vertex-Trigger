package com.vertextrigger.levelbuilders;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.vertextrigger.factories.PlatformFactory;
import com.vertextrigger.factories.SpriteFactory;
import com.vertextrigger.main.VertexTrigger;
import com.vertextrigger.util.GameObjectSize;

/**
 * A prototype level to allow manual testing of player controls & game objects
 */
public class PrototypeLevelBuilder extends LevelBuilder {
	SpriteFactory spriteFactory;
	
	public PrototypeLevelBuilder() {
		spriteFactory = new SpriteFactory();
		VertexTrigger.ASSETS.loadLevelOne();
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
		PlatformFactory factory = new PlatformFactory(world);
		GameObjectSize size = GameObjectSize.createSmallPlatformSize();
		Sprite platform = factory.createPlatform("slice17", size);
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
		return new Vector2(0,50);
	}
}