package com.vertextrigger.levelbuilder;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.vertextrigger.entities.*;
import com.vertextrigger.entities.enemy.AbstractEnemy;
import com.vertextrigger.factory.*;
import com.vertextrigger.main.VertexTrigger;
import com.vertextrigger.portal.StaticPlatform;
import com.vertextrigger.screen.AbstractGameScreen;
import com.vertextrigger.util.*;

/**
 * A prototype level to allow manual testing of player controls & game objects
 */
public class PrototypeLevelBuilder extends AbstractLevelBuilder {
	SpriteFactory spriteFactory;
	AbstractGameScreen screen;
	
	public PrototypeLevelBuilder(World world, AbstractGameScreen screen) {
		super(world, screen);
		this.screen = screen;
		//VertexTrigger.ASSETS.loadPrototypeLevel();
		//spriteFactory = new SpriteFactory();
	}

	@Override
	protected void createEnemies() {
		//TODO make enemy have circle head and poly body. jumping on head should kill it
		AbstractEnemy enemy = EnemyFactory.createPokerEnemy(world, new Vector2(4,0));
		entities.add(enemy);
		screen.addMortal(enemy);		
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
		PlatformFactory factory = new PlatformFactory(world);
		GameObjectSize size = GameObjectSize.getSmallPlatformSize();
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
	protected void createGroundWalls() {
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