package com.vertextrigger.levelbuilder;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.behaviors.FollowFlowField.FlowField;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.entities.Entity;
import com.vertextrigger.entities.player.Player;
import com.vertextrigger.factory.SpriteFactory;
import com.vertextrigger.inanimate.portal.Portal;
import com.vertextrigger.screen.AbstractGameScreen;

public abstract class AbstractLevelBuilder {
	protected final float CONTAINER_WIDTH;
	protected final float CONTAINER_HEIGHT;

	protected final World world;
	protected final Array<Entity> entities;
	protected final Array<Sprite> sprites;
	protected AbstractGameScreen gameScreen;
	protected Player player;
	protected final Array<Portal> portals;
	protected final SpriteFactory spriteFactory;
	protected final Array<FlowField<Vector2>> magnetFlowFields;

	protected AbstractLevelBuilder(final World world, final AbstractGameScreen screen, final float CONTAINER_WIDTH, final float CONTAINER_HEIGHT) {
		this.world = world;
		spriteFactory = new SpriteFactory();
		entities = new Array<>();
		sprites = new Array<>();
		portals = new Array<>();
		magnetFlowFields = createMagnetFlowFields();

		this.CONTAINER_WIDTH = CONTAINER_WIDTH;
		this.CONTAINER_HEIGHT = CONTAINER_HEIGHT;
	}

	public Player getPlayer() {
		return player;
	}

	public AbstractLevelBuilder setPlayer(final Player player) {
		this.player = player;
		return this;
	}

	public abstract Array<FlowField<Vector2>> createMagnetFlowFields();

	/**
	 * Create all enemies needed for the particular game level at the specific initial positions & to follow their predefined path Then adds the
	 * enemies to the entities container
	 *
	 * @param world
	 *            for all enemies in this level to reside in
	 */
	protected abstract void createEnemies(final Steerable<Vector2> target);

	/**
	 * Create all dangerous balls needed for the particular game level at the specific initial positions & to follow their predefined path Then adds
	 * the dangerous balls to the entities container
	 *
	 * @param world
	 *            for all dangerous balls to reside in
	 */
	protected abstract void createDangerousBalls();

	/**
	 * Create all moving platforms needed for the particular game level at the specific initial positions & to follow their predefined path Then adds
	 * the moving platforms to the entities container
	 *
	 * @param world
	 *            for all platforms to be built in
	 */
	protected abstract void createMovingPlatforms();

	/**
	 * Create all timed platforms needed for the particular game level at the specific initial positions & the predefined time for the platforms to
	 * perform their actions Then adds the timed platforms to the entities container
	 *
	 * @param world
	 *            for all platforms to be built in
	 */
	protected abstract void createTimedPlatforms();

	/**
	 * Create all static platforms needed for the particular game level at the specific initial positions Then adds the platform's corresponding
	 * sprites to the sprites container
	 *
	 * @param world
	 *            for all platforms to be built in
	 */
	protected abstract void createStaticPlatforms();

	/**
	 * Create the ground, ceiling & walls for the particular level's layout Then adds the ground, ceiling & wall's corresponding sprites to the
	 * sprites container
	 *
	 * @param world
	 *            for the ground, ceiling & walls to be built in
	 */
	protected abstract void createGroundWalls();

	public abstract Array<Portal> createPortals();

	public abstract void createPowerUps();

	/**
	 * Resets the positions of all entities when player dies back to their initial positions of the level
	 */
	public abstract void resetLevelLayout();

	/**
	 * Invokes all methods required to build all of this level's entities & stores them in the entities container, before returning the container.
	 *
	 * @param world
	 *            for all game objects in this level to reside in
	 * @return all entities required for this level
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
	 *
	 * @param world
	 *            for all game objects in this level to reside in
	 * @return all entities required for this level
	 */
	public Array<Sprite> buildLevelLayout() {
		createStaticPlatforms();
		createGroundWalls();
		createPortals();
		createPowerUps();
		return sprites;
	}

	public void setGameScreen(final AbstractGameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}

	public float getGroundLevel() {
		return -CONTAINER_HEIGHT;
	}

	public float getCeilingLevel() {
		return CONTAINER_HEIGHT;
	}

	public float getLeftBorderOfLevel() {
		return -CONTAINER_WIDTH;
	}

	public float getRightBorderOfLevel() {
		return CONTAINER_WIDTH;
	}

	public abstract Sprite getBackground();
}