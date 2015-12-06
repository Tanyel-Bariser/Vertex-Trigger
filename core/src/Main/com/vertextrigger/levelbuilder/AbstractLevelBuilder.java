package com.vertextrigger.levelbuilder;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.entities.Entity;
import com.vertextrigger.entities.player.Player;
import com.vertextrigger.factory.SpriteFactory;
import com.vertextrigger.factory.entityfactory.PlayerFactory;
import com.vertextrigger.screen.AbstractGameScreen;

public abstract class AbstractLevelBuilder {
	protected World world;
	protected Array<Entity> entities;
	protected Array<Sprite> sprites;
	protected AbstractGameScreen gameScreen;
	protected Player player;
	protected SpriteFactory spriteFactory;
	
	protected AbstractLevelBuilder(World world, AbstractGameScreen screen) {
		this.world = world;
		
		this.spriteFactory = new SpriteFactory();
		entities = new Array<Entity>();
		sprites = new Array<Sprite>();
	}
	
	public Player getPlayer() {
		return player;
	}
	

	public AbstractLevelBuilder setPlayer(Player player) {
		this.player = player;
		return this;
	}

	/**
	 * Create all enemies needed for the particular game level at the
	 * specific initial positions & to follow their predefined path
	 * Then adds the enemies to the entities container
	 * 
	 * @param world for all enemies in this level to reside in
	 */
	protected abstract void createEnemies();

	/**
	 * Create all dangerous balls needed for the particular game level at
	 * the specific initial positions & to follow their predefined path
	 * Then adds the dangerous balls to the entities container
	 * 
	 * @param world for all dangerous balls to reside in
	 */
	protected abstract void createDangerousBalls();

	/**
	 * Create all moving platforms needed for the particular game level at the
	 * specific initial positions & to follow their predefined path
	 * Then adds the moving platforms to the entities container
	 * 
	 * @param world for all platforms to be built in
	 */
	protected abstract void createMovingPlatforms();

	/**
	 * Create all timed platforms needed for the particular game level at the
	 * specific initial positions & the predefined time for the platforms to
	 * perform their actions
	 * Then adds the timed platforms to the entities container
	 * 
	 * @param world for all platforms to be built in
	 */
	protected abstract void createTimedPlatforms();

	/**
	 * Create all static platforms needed for the particular game level at the
	 * specific initial positions
	 * Then adds the platform's corresponding sprites to the sprites container
	 * 
	 * @param world for all platforms to be built in
	 */
	protected abstract void createStaticPlatforms();

	/**
	 * Create the ground, ceiling & walls for the particular level's layout
	 * Then adds the ground, ceiling & wall's corresponding sprites to the
	 * sprites container
	 * 
	 * @param world for the ground, ceiling & walls to be built in
	 */
	protected abstract void createGroundWalls();

	/**
	 * Resets the positions of all entities when player
	 * dies back to their initial positions of the level
	 */
	public abstract void resetLevelLayout();
	
	/**
	 * Invokes all methods required to build all of this level's entities &
	 * stores them in the entities container, before returning the container.
	 * 
	 * @param world for all game objects in this level to reside in
	 * @return all entities required for this level
	 */
	public Array<Entity> buildEntities() {
		// Create enemies then add them to the entities container
		createEnemies();
		// Create dangerous ball(s) then add them to the entities container
		createDangerousBalls();
		// Return container of all entities needed for this level
		return entities;
	}
	
	/**
	 * Invokes all methods required to build all of this level's platforms,
	 * ground, ceiling and walls and stores them in their corresponding
	 * sprites in the sprites container before returning the container.
	 * 
	 * @param world for all game objects in this level to reside in
	 * @return all entities required for this level
	 */
	public Array<Sprite> buildLevelLayout() {
		createStaticPlatforms();
		createGroundWalls();
		return sprites;
	}
	
	public void setGameScreen(AbstractGameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}

	/**
	 * @return position player starts the level at
	 */
	public abstract Vector2 getInitialPosition();
}