package com.vertextrigger.levelbuilder;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.inanimate.portal.Portal;
import com.vertextrigger.screen.AbstractGameScreen;

public class LevelThreeBuilder extends AbstractLevelBuilder {

	public LevelThreeBuilder(final World world, final AbstractGameScreen screen) {
		super(world, screen, 0, 0);
		// Load assets required for level three,
		// while unloading unneeded assets
		// Play level one music
	}

	@Override
	protected void createEnemies() {
	}

	@Override
	protected void createDangerousBalls() {
	}

	@Override
	protected void createMovingPlatforms() {
	}

	@Override
	protected void createTimedPlatforms() {
	}

	@Override
	protected void createStaticPlatforms() {
	}

	@Override
	protected void createGroundWalls() {
	}

	@Override
	public void resetLevelLayout() {
	}

	@Override
	public Vector2 getInitialPosition() {
		return null;
	}

	@Override
	public Array<Portal> createPortals() {
		return null;
	}
}