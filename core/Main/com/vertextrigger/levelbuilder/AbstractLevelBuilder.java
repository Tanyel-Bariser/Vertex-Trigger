package com.vertextrigger.levelbuilder;

import com.badlogic.gdx.ai.steer.Steerable;
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
import com.vertextrigger.inanimate.*;
import com.vertextrigger.inanimate.portal.Portal;
import com.vertextrigger.inanimate.portal.PortalFactory;
import com.vertextrigger.inanimate.portal.PortalTeleportation;
import com.vertextrigger.screen.AbstractGameScreen;
import com.vertextrigger.util.GameObjectSize;

import static com.vertextrigger.factory.bodyfactory.PlatformBodyFactory.Friction;

public abstract class AbstractLevelBuilder {
	protected static final Array EMPTY_ARRAY = new Array();
	protected final World world;
	protected Player player;
	protected final SpriteFactory spriteFactory;
	protected final MagnetFlowField magnetFlowField;
	protected final PlatformFactory platformFactory;

	private final float containerWidth;
	private final float containerHeight;

	protected AbstractLevelBuilder(final World world, final AbstractGameScreen screen, final float containerWidth, final float containerHeight) {
		this.world = world;
		spriteFactory = new SpriteFactory();
		magnetFlowField = createMagnetFlowField();
		platformFactory = new PlatformFactory(world);

		this.containerWidth = containerWidth;
		this.containerHeight = containerHeight;
	}

	/*
	 * METHODS OVERRIDDEN BY SUBCLASSES
	 */

	protected abstract Array<Entity> createDangerousBalls();

	protected abstract Array<Enemy> createEnemies(final Steerable<Vector2> target);

	protected abstract Array<Inanimate> createGroundWalls();

	public abstract MagnetFlowField createMagnetFlowField();

	protected abstract Array<MovingPlatform> createMovingPlatforms();

	public abstract Array<Inanimate> createPortals();

	public abstract Array<Inanimate> createPowerUps();

	protected abstract Array<Inanimate> createStaticPlatforms();

	protected abstract Array<FadingPlatform> createTimedPlatforms();

	public abstract Sprite getBackground();

	public abstract void resetLevelLayout();

	/*
	 * UTILITY METHODS FOR GAME SCREEN
	 */

	/**
	 * Invokes all methods required to build all of this level's entities & stores them in the entities container, before returning the container.
	 */
	public Array<Entity> buildEntities() {
		final Array<Entity> entities = new Array<>();
		entities.addAll(createDangerousBalls());
		entities.addAll(createEnemies(getPlayer().getSteerable()));
		entities.addAll(createMovingPlatforms());
		entities.addAll(createTimedPlatforms());
		return entities;
	}

	public Array<Inanimate> buildInanimate() {
		final Array<Inanimate> inanimates = new Array<>();
		inanimates.addAll(createStaticPlatforms());
		inanimates.addAll(createGroundWalls());
		inanimates.addAll(createPortals());
		inanimates.addAll(createPowerUps());
		return inanimates;
	}

	/**
	 * Invokes all methods required to build all of this level's platforms, ground, ceiling and walls and stores them in their corresponding sprites
	 * in the sprites container before returning the container.
	 */
	public Array<Sprite> buildLevelLayout() {
		createStaticPlatforms();
		createMovingPlatforms();
		createGroundWalls();
		createPortals();
		createPowerUps();
		return sprites;
	}

	public void setGameScreen(final AbstractGameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}

	public float getGroundLevel() {
		return -containerHeight;
	}

	public float getCeilingLevel() {
		return containerHeight;
	}

	public float getLeftBorderOfLevel() {
		return -containerWidth;
	}

	public float getRightBorderOfLevel() {
		return containerWidth;
	}

	/*
	 * UTILITY METHODS FOR SUBCLASSES
	 */

	public Player getPlayer() {
		return player;
	}

	public AbstractLevelBuilder setPlayer(final Player player) {
		this.player = player;
		return this;
	}

	Ground createGroundWalls(final int containerWidth, final int containerHeight) {
		return createGroundWalls(containerWidth, containerHeight, 0);
	}

	Ground createGroundWalls(final int containerWidth, final int containerHeight, final float pitDepth) {
		final Vector2 bottomLeft = new Vector2(-containerWidth, -containerHeight - pitDepth);
		final Vector2 bottomRight = new Vector2(-containerWidth, containerHeight - pitDepth);
		final Vector2 topRight = new Vector2(containerWidth, containerHeight);
		final Vector2 topLeft = new Vector2(containerWidth, -containerHeight);

		return new Ground(world, new Vector2[] { topLeft, bottomLeft, bottomRight, topRight, topLeft });
	}

	StaticPlatform staticPlatform(final String sprite, final GameObjectSize size, final Vector2 position) {
		return staticPlatform(sprite, size, position, 0);
	}

	StaticPlatform staticPlatform(final String sprite, final GameObjectSize size, final Vector2 position, final float rotation) {
		return staticPlatform(sprite, size, position, Friction.NORMAL, rotation);
	}

	StaticPlatform staticPlatform(final String sprite, final GameObjectSize size, final Vector2 position, final Friction friction,
			final float rotation) {
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

	MovingPlatform rectangleMovingPlatform(final String spriteName, final GameObjectSize size, final Vector2 topLeft, final Vector2 bottomRight,
			final boolean clockwise) {
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
		final Array<Portal> portalPair = PortalFactory.createPortalPair(world, new Vector2(x1, y1), new Vector2(x2, y2), type);
		sprites.add(portalPair.get(0).getSprite());
		sprites.add(portalPair.get(1).getSprite());
		return portalPair;
	}

	float fromBottom(final float distanceFromFloor) {
		return -containerHeight + distanceFromFloor;
	}

	float fromLeft(final float distanceFromLeftWall) {
		return -containerWidth + distanceFromLeftWall;
	}
}