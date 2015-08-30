package com.vertextrigger.levelbuilders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.vertextrigger.entities.*;
import com.vertextrigger.factories.*;
import com.vertextrigger.main.VertexTrigger;
import com.vertextrigger.util.*;

/**
 * A prototype level to allow manual testing of player controls & game objects
 */
public class PrototypeLevelBuilder extends LevelBuilder {
	SpriteFactory spriteFactory;
	
	public PrototypeLevelBuilder() {
		VertexTrigger.ASSETS.loadPrototypeLevel();
		spriteFactory = new SpriteFactory();
	}

	@Override
	protected void createEnemies(World world) {
		//TODO make enemy have circle head and poly body. jumping on head should kill it
		// TODO enemy animate death??
		Enemy enemy = EnemyFactory.createPokerEnemy(world, new Vector2(0,0));
		sprites.add(enemy.getSprite());
		
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
		float positionX = 0;
		float positionY = -15;
		
		for (int i = 0; i < 5; i++) {
			Vector2 p0 = new Vector2(positionX, positionY);
			StaticPlatform platform = factory.createPlatform("slice17", size, p0);
			platform.setRotation(0);
			sprites.add(platform.getSprite());
			
			positionX += size.getPhysicalWidth() * 2;
			positionY += 5f;
		}
		Vector2 p = new Vector2(-19, -19);
		StaticPlatform bouncePlatform = factory.createPlatform("slice17", size, p);
		bouncePlatform.setRotation(200);
		sprites.add(bouncePlatform.getSprite());
		
		Vector2 p2 = new Vector2(-15, -15);
		StaticPlatform bouncePlatform2 = factory.createPlatform("slice17", size, p2);
		bouncePlatform2.setRotation(0);
		sprites.add(bouncePlatform2.getSprite());
	}
	
	@Override
	protected void createGroundWalls(World world) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(0, 0);

		ChainShape worldContainerShape = new ChainShape();

		final int WALL_HEIGHT = 50;
		Vector2 topLeft = new Vector2(-20f, WALL_HEIGHT);
		Vector2 bottomLeft = new Vector2(-20f, -20f);
		Vector2 bottomRight = new Vector2(20f, -20f);
		Vector2 topRight = new Vector2(20f, WALL_HEIGHT);

		worldContainerShape.createChain(new Vector2[] { topLeft, bottomLeft,
				bottomRight, topRight, topLeft });

		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = worldContainerShape;
		fixDef.friction = 0.9f;
		fixDef.restitution = 0;

		Body worldContainer = world.createBody(bodyDef);
		worldContainer.createFixture(fixDef).setUserData(ContactBody.GROUND);

		worldContainerShape.dispose();	
	}

	@Override
	public void resetLevelLayout() {
	}

	@Override
	public Vector2 getInitialPosition() {
		return new Vector2(0, 10);
	}
}