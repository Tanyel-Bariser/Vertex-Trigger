package com.vertextrigger.levelbuilder;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.entities.*;
import com.vertextrigger.entities.enemy.*;
import com.vertextrigger.entities.movingplatform.MovingPlatform;
import com.vertextrigger.entities.player.Player;
import com.vertextrigger.entities.mortalplatform.FadingPlatform;
import com.vertextrigger.factory.PlatformFactory;
import com.vertextrigger.factory.SpriteFactory;
import com.vertextrigger.factory.entityfactory.PlayerFactory;
import com.vertextrigger.inanimate.*;
import com.vertextrigger.inanimate.portal.Portal;
import com.vertextrigger.inanimate.portal.PortalFactory;
import com.vertextrigger.inanimate.portal.PortalTeleportation;
import com.vertextrigger.level.Level;
import com.vertextrigger.level.LevelSize;
import com.vertextrigger.screen.AbstractGameScreen;
import com.vertextrigger.util.GameObjectSize;

import static com.vertextrigger.factory.bodyfactory.PlatformBodyFactory.Friction;

public abstract class AbstractLevelBuilder {

	protected final Level level;
	private final float containerHeight;
	private final float containerWidth;

	final PlatformFactory platformFactory;
	final SpriteFactory spriteFactory = new SpriteFactory();
	protected Player player;

	AbstractLevelBuilder(final Level level, final float containerHeight, final float containerWidth) {
		this.level = level;
		this.containerHeight = containerHeight;
		this.containerWidth = containerWidth;
		this.platformFactory = new PlatformFactory(this.level.getWorld());
		//this.player = PlayerFactory.createPlayer(level.getWorld(), playerStartPosition(), level, createMagnetFlowField());
	}

	/*
	 * METHODS OVERRIDDEN BY SUBCLASSES
	 */

	protected abstract Array<Entity> createDangerousBalls();

	protected abstract Array<Enemy> createEnemies(final Steerable<Vector2> target);

	protected abstract Ground createGroundWalls();

	public abstract MagnetFlowField createMagnetFlowField();

	protected abstract Array<MovingPlatform> createMovingPlatforms();

	public abstract Array<Portal> createPortals();

	public abstract Array<Entity> createPowerUps();

	protected abstract Array<StaticPlatform> createStaticPlatforms();

	protected abstract Array<FadingPlatform> createTimedPlatforms();

	protected abstract Vector2 playerStartPosition();

	public abstract Sprite getBackground();

	public abstract void resetLevelLayout();

	public Player getPlayer() {
		if (player == null) {
			System.out.println("RECREATING PLAYER");
			player = PlayerFactory.createPlayer(level.getWorld(), playerStartPosition(), level, createMagnetFlowField());
		}
		return player;
	}

	public LevelSize getLevelSize() {
		return new LevelSize(containerHeight, containerWidth);
	};

	public Array<Entity> buildEntities() {
		final Array<Entity> entities = new Array<>();
		entities.addAll(createDangerousBalls());
		entities.addAll(createEnemies(getPlayer().getSteerable()));
		entities.addAll(createMovingPlatforms());
		entities.addAll(createTimedPlatforms());
		entities.addAll(createPowerUps());
		return entities;
	}

	public Array<Inanimate> buildInanimate() {
		final Array<Inanimate> inanimates = new Array<>();
		inanimates.addAll(createStaticPlatforms());
		inanimates.add(createGroundWalls());
		inanimates.addAll(createPortals());
		return inanimates;
	}

	/*
	 * UTILITY METHODS FOR SUBCLASSES
	 */

//	public Player getPlayer() {
//		return player;
//	}
//
//	public AbstractLevelBuilder setPlayer(final Player player) {
//		this.player = player;
//		return this;
//	}

	Ground createGroundWalls(final int containerWidth, final int containerHeight) {
		return createGroundWalls(containerWidth, containerHeight, 0);
	}

	Ground createGroundWalls(final int containerWidth, final int containerHeight, final float pitDepth) {
		final Vector2 bottomLeft = new Vector2(-containerWidth, -containerHeight - pitDepth);
		final Vector2 bottomRight = new Vector2(-containerWidth, containerHeight - pitDepth);
		final Vector2 topRight = new Vector2(containerWidth, containerHeight);
		final Vector2 topLeft = new Vector2(containerWidth, -containerHeight);

		return new Ground(level.getWorld(), new Vector2[] { topLeft, bottomLeft, bottomRight, topRight, topLeft });
	}

	StaticPlatform staticPlatform(final String sprite, final GameObjectSize size, final Vector2 position) {
		return staticPlatform(sprite, size, position, 0);
	}

	StaticPlatform staticPlatform(final String sprite, final GameObjectSize size, final Vector2 position, final float rotation) {
		return staticPlatform(sprite, size, position, Friction.NORMAL, rotation);
	}

	StaticPlatform staticPlatform(final String sprite, final GameObjectSize size, final Vector2 position, final Friction friction, final float rotation) {
		final StaticPlatform platform = platformFactory.createPlatform(sprite, size, position, friction);
		platform.setRotation(rotation);
		return platform;
	}

	FadingPlatform fadingPlatform(final String sprite, final GameObjectSize size, final Vector2 position) {
		return platformFactory.createFadingPlatform(sprite, size, position);
	}

	MovingPlatform simpleMovingPlatform(final String spriteName, final GameObjectSize size, final Vector2 start, final Vector2 end) {
		final MovingPlatform platform = platformFactory.createSimpleMovingPlatform(spriteName, size, start, end);
		platform.startMoving();
		return platform;
	}

	MovingPlatform rectangleMovingPlatform(final String spriteName, final GameObjectSize size, final Vector2 topLeft, final Vector2 bottomRight, final boolean clockwise) {
		final MovingPlatform platform = platformFactory.createRectangleMovingPlatform(spriteName, size, topLeft, bottomRight, clockwise);
		platform.startMoving();
		return platform;
	}

	Pit pit(final String sprite, final GameObjectSize size, final Vector2 position) {
		return platformFactory.createPit(sprite, size, position);
	}

	Spike spike(final String sprite, final GameObjectSize size, final Vector2 position) {
		return platformFactory.createSpike(sprite, size, position);
	}

	Array<Portal> portalPair(final float x1, final float y1, final float x2, final float y2, final PortalTeleportation type) {
		return PortalFactory.createPortalPair(level.getWorld(), new Vector2(x1, y1), new Vector2(x2, y2), type);
	}

	float fromBottom(final float distanceFromFloor) {
		return -getLevelSize().getContainerHeight() + distanceFromFloor;
	}

	float fromLeft(final float distanceFromLeftWall) {
		return -getLevelSize().getContainerWidth() + distanceFromLeftWall;
	}
}