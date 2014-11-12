package com.vertextrigger.levelbuilders;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class LevelTwoBuilder implements LevelBuilder {

	@Override
	public Entity createPlayer(World world) {
		return null;
	}

	@Override
	public Array<Entity> createEnemies(World world) {
		return null;
	}

	@Override
	public Array<Entity> createDanerousBalls(World world) {
		return null;
	}

	@Override
	public Array<Entity> createMovingPlatforms(World world) {
		return null;
	}

	@Override
	public Array<Entity> createTimedPlatforms(World world) {
		return null;
	}

	@Override
	public Array<Sprite> createStaticPlatforms(World world) {
		return null;
	}

	@Override
	public void createGroundWalls(World world) {
	}

	@Override
	public void resetLevelLayout() {	
	}
}