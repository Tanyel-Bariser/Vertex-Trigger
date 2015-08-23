package com.vertextrigger.levelbuilders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.vertextrigger.entities.StaticPlatform;
import com.vertextrigger.factories.PlatformFactory;
import com.vertextrigger.factories.SpriteFactory;
import com.vertextrigger.main.VertexTrigger;
import com.vertextrigger.util.ContactBody;
import com.vertextrigger.util.GameObjectSize;

/**
 * A prototype level to allow manual testing of player controls & game objects
 */
public class PrototypeLevelBuilder extends LevelBuilder {
	SpriteFactory spriteFactory;
	
	public PrototypeLevelBuilder() {
		spriteFactory = new SpriteFactory();
		VertexTrigger.ASSETS.loadPrototypeLevel();
	}

	@Override
	protected void createEnemies(World world) {
		spriteFactory.createEnemy("pokerMad", 10, 10);
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
			StaticPlatform platform = factory.createPlatform("slice17", size);
			Vector2 position = new Vector2(positionX, positionY);
			platform.setPosition(position);
			platform.setRotation(0);
			sprites.add(platform.getSprite());
			
			positionX += size.getPhysicalWidth() * 2;
			positionY += 5f;
		}
		
		StaticPlatform bouncePlatform = factory.createPlatform("slice17", size);
		bouncePlatform.setPosition(new Vector2(-19, -19));
		bouncePlatform.setRotation(200);
		sprites.add(bouncePlatform.getSprite());
		
		StaticPlatform bouncePlatform2 = factory.createPlatform("slice17", size);
		bouncePlatform2.setPosition(new Vector2(-15, -15));
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
		fixDef.friction = 0f;
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
		return new Vector2(0,10);
	}
}