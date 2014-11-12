package com.vertextrigger.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public interface LevelBuilder {

	Entity createPlayer(World world);
	
	Array<Entity> createEnemies(World world);
	
	Array<Entity> createDanerousBalls(World world);
	
	Array<Entity> createMovingPlatforms(World world);
	
	Array<Entity> createTimedPlatforms(World world);
	
	Array<Sprite> createStaticPlatforms(World world);
	
	void createGroundWalls(World world);
	
	void resetLevelLayout();
}