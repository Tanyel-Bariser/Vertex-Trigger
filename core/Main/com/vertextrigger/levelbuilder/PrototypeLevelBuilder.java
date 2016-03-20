package com.vertextrigger.levelbuilder;

import com.badlogic.gdx.math.Vector2;

import static com.vertextrigger.inanimate.portal.PortalTeleportation.*;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.entities.enemy.AbstractEnemy;
import com.vertextrigger.factory.*;
import com.vertextrigger.inanimate.Ground;
import com.vertextrigger.inanimate.StaticPlatform;
import com.vertextrigger.inanimate.portal.Portal;
import com.vertextrigger.inanimate.portal.PortalFactory;
import com.vertextrigger.inanimate.portal.PortalTeleportation;
import com.vertextrigger.screen.AbstractGameScreen;
import com.vertextrigger.util.*;

import static com.vertextrigger.util.GameObjectSize.SMALL_PLATFORM_SIZE;

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
		GameObjectSize size = SMALL_PLATFORM_SIZE;
		float positionX = 0;
		float positionY = -2.2f;
		
		for (int i = 0; i < 5; i++) {
			Vector2 p0 = new Vector2(positionX, positionY);
			StaticPlatform platform = factory.createPlatform("slice17", size, p0);
			platform.setRotation(0);
			sprites.add(platform.getSprite());
			
			positionX += size.getPhysicalWidth() * 2;
			positionY += 5f * GameObjectSize.OBJECT_SIZE;
		}
		Vector2 p = new Vector2(-2.5f, -2.5f);
		StaticPlatform bouncePlatform = factory.createPlatform("slice17", size, p);
		bouncePlatform.setRotation(200);
		sprites.add(bouncePlatform.getSprite());
	}
	
	@Override
	protected void createGroundWalls() {		
		float CONTAINER_SIZE = 2f;

		Vector2 bottomLeft = new Vector2(-CONTAINER_SIZE, -CONTAINER_SIZE);
		Vector2 bottomRight = new Vector2(CONTAINER_SIZE, -CONTAINER_SIZE);
		Vector2 topRight = new Vector2(CONTAINER_SIZE, CONTAINER_SIZE);
		Vector2 topLeft = new Vector2(-CONTAINER_SIZE, CONTAINER_SIZE);

		new Ground(world, new Vector2[] { topLeft, bottomLeft, bottomRight, topRight, topLeft });
	}

	@Override
	public void resetLevelLayout() {
	}

	@Override
	public Vector2 getInitialPosition() {
		return new Vector2(0, 0);
	}

	@Override
	public Array<Portal> createPortals() {
		float portalHeight = GameObjectSize.PORTAL_SIZE.getPhysicalHeight();
		float portalWidth = GameObjectSize.PORTAL_SIZE.getPhysicalWidth();
		
		Vector2 portal1Position = new Vector2(-2 + portalWidth, -2f + portalHeight);
		//Vector2 portal2Position = new Vector2(2f - portalWidth, -0.5f + portalHeight);
		Vector2 portal2Position = new Vector2(2f - portalWidth, -2f + portalHeight);

		return new PortalFactory().createPortalPair(world, 
				portal1Position, 
				portal2Position, 
				MOVING_DIFFERENT_XY_AXIS_DIRECTION);
	}
}