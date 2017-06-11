package com.vertextrigger.levelbuilder;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.entities.Entity;
import com.vertextrigger.entities.MagnetFlowField;
import com.vertextrigger.entities.movingplatform.MovingPlatform;
import com.vertextrigger.entities.player.Player;
import com.vertextrigger.factory.PlatformFactory;
import com.vertextrigger.factory.SpriteFactory;
import com.vertextrigger.inanimate.Ground;
import com.vertextrigger.inanimate.StaticPlatform;
import com.vertextrigger.inanimate.portal.Portal;
import com.vertextrigger.inanimate.portal.PortalFactory;
import com.vertextrigger.inanimate.portal.PortalTeleportation;
import com.vertextrigger.screen.AbstractGameScreen;
import com.vertextrigger.util.GameObjectSize;

import static com.vertextrigger.factory.bodyfactory.PlatformBodyFactory.Friction;

public abstract class AbstractLevelBuilder {
	protected final World world;
	protected final Array<Entity> entities;
	protected final Array<Sprite> sprites;
	protected AbstractGameScreen gameScreen;
	protected Player player;
	protected final Array<Portal> portals;
	protected final SpriteFactory spriteFactory;
	protected final MagnetFlowField magnetFlowField;
	protected final PlatformFactory platformFactory;

	private final float containerWidth;
	private final float containerHeight;

	protected AbstractLevelBuilder(final World world, final AbstractGameScreen screen, final float containerWidth, final float containerHeight) {
		this.world = world;
		spriteFactory = new SpriteFactory();
		entities = new Array<>();
		sprites = new Array<>();
		portals = new Array<>();
		magnetFlowField = createMagnetFlowField();
		platformFactory = new PlatformFactory(world);

		this.containerWidth = containerWidth;
		this.containerHeight = containerHeight;
	}

	/*
	 * METHODS OVERRIDDEN BY SUBCLASSES
	 */

	protected abstract void createDangerousBalls();
	protected abstract void createEnemies(final Steerable<Vector2> target);
	protected abstract void createGroundWalls();
	public abstract MagnetFlowField createMagnetFlowField();
	protected abstract void createMovingPlatforms();
	public abstract Array<Portal> createPortals();
	public abstract void createPowerUps();
	protected abstract void createStaticPlatforms();
	protected abstract void createTimedPlatforms();
	public abstract Sprite getBackground();
	public abstract void resetLevelLayout();

	/*
	 * UTILITY METHODS FOR GAME SCREEN
	 */

	/**
	 * Invokes all methods required to build all of this level's entities & stores them in the entities container, before returning the container.
	 */
	public Array<Entity> buildEntities() {
		// Create enemies then add them to the entities container
		createEnemies(getPlayer().getSteerable());
		// Create dangerous ball(s) then add them to the entities container
		createDangerousBalls();
		// Return container of all entities needed for this level
		return entities;
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

	Ground createGroundWalls(int containerWidth, int containerHeight) {
		return createGroundWalls(containerWidth, containerHeight, 0);
	}

	Ground createGroundWalls(int containerWidth, int containerHeight, float pitDepth) {
		final Vector2 bottomLeft = new Vector2(-containerWidth, -containerHeight - pitDepth);
		final Vector2 bottomRight = new Vector2(-containerWidth, containerHeight - pitDepth);
		final Vector2 topRight = new Vector2(containerWidth, containerHeight);
		final Vector2 topLeft = new Vector2(containerWidth, -containerHeight);

		return new Ground(world, new Vector2[] { topLeft, bottomLeft, bottomRight, topRight, topLeft });
	}

	void staticPlatform(final String sprite, final GameObjectSize size, final Vector2 position) {
		staticPlatform(sprite, size, position, 0);
	}

	void staticPlatform(final String sprite, final GameObjectSize size, final Vector2 position, final float rotation) {
		staticPlatform(sprite, size, position, Friction.NORMAL, rotation);
	}

	void staticPlatform(final String sprite, final GameObjectSize size, final Vector2 position, final Friction friction, final float rotation) {
		final StaticPlatform platform = platformFactory.createPlatform(sprite, size, position, friction);
		platform.setRotation(rotation);
		sprites.add(platform.getSprite());
	}

	void simpleMovingPlatform(final String spriteName, final GameObjectSize size, final Vector2 start, final Vector2 end) {
		final MovingPlatform platform = platformFactory.createSimpleMovingPlatform(spriteName, size, start, end);
		platform.startMoving();
		entities.add(platform);
	}

	void rectangleMovingPlatform(final String spriteName, final GameObjectSize size, final Vector2 topLeft, final Vector2 bottomRight, final boolean clockwise) {
		final MovingPlatform platform = platformFactory.createRectangleMovingPlatform(spriteName, size, topLeft, bottomRight, clockwise);
		platform.startMoving();
		entities.add(platform);
	}

	void pit(final String sprite, final GameObjectSize size, final Vector2 position) {
		platformFactory.createPit(sprite, size, position);
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