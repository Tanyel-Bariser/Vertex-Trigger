package com.vertextrigger.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.collisiondetection.CollisionDetection;
import com.vertextrigger.entities.Entity;
import com.vertextrigger.entities.player.Player;
import com.vertextrigger.levelbuilders.LevelBuilder;
import com.vertextrigger.main.VertexTrigger;
import com.vertextrigger.util.*;

public class GameScreen implements Screen {
	private final float ZOOM = 30f;
	private final VertexTrigger vertexTrigger;
	private final LevelBuilder levelBuilder;
	private World world;
	private OrthographicCamera camera;
	private Player player;
	private SpriteBatch batch;
	private final float approxFPS = 60.0f;
	private final float TIMESTEP = 1.0f / approxFPS;
	private final int VELOCITYITERATIONS = 8; // Box2d manual recommends 8 & 3
	private final int POSITIONITERATIONS = 3; // for these iterations values
	private State state = State.RUNNING;
	private Array<Entity> entities;
	private Array<Sprite> entitySprites;
	private Array<Sprite> backgroundSprites;
	private final float GRAVITY = -70000f;
	private Box2DDebugRenderer physicsDebugger;
	
	/**
	 * Sets main game class for smooth screen transitions
	 * 
	 * @param vertexTrigger main game class
	 */
	public GameScreen(VertexTrigger vertexTrigger, LevelBuilder levelBuilder) {
		this.vertexTrigger = vertexTrigger;
		this.levelBuilder = levelBuilder;
	}
	
	private Vector2 setUpLevelAndReturnInitialPosition() {
		levelBuilder.setGameScreen(this);
		entities = levelBuilder.buildEntities(world);
		backgroundSprites = levelBuilder.buildLevelLayout(world);
		return levelBuilder.getInitialPosition();
	}
	
	/**
	 * Show method is invoked once when the level one screen is first created &
	 * is used to set up the screen of the first level
	 */
	@Override
	public void show() {

		entities = new Array<Entity>();
		entitySprites = new Array<Sprite>();
		backgroundSprites = new Array<Sprite>();
		world = new World(new Vector2(0, GRAVITY), true);
		camera = new OrthographicCamera(Gdx.graphics.getWidth()/ZOOM, Gdx.graphics.getHeight()/ZOOM);
		Vector2 initialPosition = setUpLevelAndReturnInitialPosition();
		player = new Player(world, initialPosition, this);
		batch = new SpriteBatch();
		entities.add(player);
		world.setContactListener(new CollisionDetection(player));
		Gdx.input.setInputProcessor(new Controller(player, this));
		physicsDebugger = new Box2DDebugRenderer();
	}

    int jumpCount = 10;
	/**
	 * Render method is invoked repeatedly once per frame, approximately 60
	 * frames per second, during the game
	 */
	@Override
	public void render(float delta) {
		clearScreen();
		if (state == State.RUNNING){
			updateWorld(delta);
			updateEntities(delta);
			updateCamera();
		}

        if (player.isKeepJumping() && jumpCount > 0) {
            player.jump();
            jumpCount--;
            if (jumpCount == 0) {
                player.setStopJumping();
                jumpCount = 10;
            }
        }

		drawToScreen(delta, getVisibleSprites());
		physicsDebugger.render(world, camera.combined);
	}
	
	private void clearScreen() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	private void updateWorld(float delta) {
		world.setGravity(new Vector2(0, GRAVITY * delta));
		world.step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATIONS);
	}
	
	private void updateEntities(float delta) {
		entitySprites.clear();
		for (Entity entity : entities) {
			Sprite sprite = entity.update(delta);
			entitySprites.add(sprite);
		}
	}
	
	private Array<Sprite> getVisibleSprites() {
//		Array<Sprite> sprites = new Array<Sprite>();
//		for (Sprite sprite : entitySprites) {
//			if (isInScreen(sprite)) {
//				sprites.add(sprite);
//			}
//		}
		return entitySprites;
	}
	
	private boolean isInScreen(Sprite sprite) {
		float topEdge = camera.position.y + camera.viewportHeight / 2;
		float bottomEdge = camera.position.y - camera.viewportHeight / 2;
		float leftEdge = camera.position.x - camera.viewportWidth / 2;
		float rightEdge = camera.position.x + camera.viewportWidth / 2;
		int errorMargin = 4;
		
		boolean belowTop = sprite.getY() < topEdge + errorMargin;
		boolean aboveBottom = sprite.getY() > bottomEdge - errorMargin;
		boolean inRight = sprite.getX() > rightEdge + errorMargin;
		boolean inLeft = sprite.getX() > leftEdge - errorMargin;
		return belowTop && aboveBottom && inRight && inLeft;
	}
	
	private void updateCamera() {
		float playerY = player.getBody().getPosition().y;
		float playerX = player.getBody().getPosition().x;
		camera.position.y = playerY;
		camera.position.x = playerX;
		camera.update();
	}
	
	private void drawToScreen(float delta, Array<Sprite> visibleEntitySprite) {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (Sprite sprite : visibleEntitySprite) {
			sprite.draw(batch);
		}
		for (Sprite sprite: backgroundSprites) {
			sprite.draw(batch);
		}
		batch.end();
	}

	/**
	 * Automatically called when application is resized
	 */
	@Override
	public void resize(int width, int height) {
	}
	
	/**
	 * @param entity is added to the entity container/data structure
	 */
	public void addEntity(Entity entity) {
		// Added an entity to the entity container
		entities.add(entity);
	}
	
	/**
	 * Resets the level layout when player has died & repositions
	 * the player back to the initial position of the level
	 * Does not recreate game objects, it only resets their initial positions
	 */
	public void resetLevel() {
		levelBuilder.resetLevelLayout();
	}

	/**
	 * Hide method is invoked automatically when the screen is switch to another
	 * screen & is an appropriate place to dispose of the assets being used in
	 * this screen
	 */
	@Override
	public void hide() {
		dispose();
	}

	/**
	 * Invoked when the user pauses the game play
	 */
	@Override
	public void pause() {
		state = State.PAUSED;
	}

	/**
	 * Invoked when the user unpauses the game to resume playing
	 */
	@Override
	public void resume() {
		state = State.RUNNING;
	}

	/**
	 * Dispose method needs to be invoked manually when this class is no longer
	 * being used to dispose of the assets it's using
	 */
	@Override
	public void dispose() {
		// Dispose assets being used in this screen
	}
}