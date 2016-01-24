package com.vertextrigger.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.collisiondetection.CollisionDetection;
import com.vertextrigger.entities.Entity;
import com.vertextrigger.entities.Mortal;
import com.vertextrigger.entities.player.Bullet;
import com.vertextrigger.entities.player.Player;
import com.vertextrigger.factory.GameScreenFactory;
import com.vertextrigger.levelbuilder.AbstractLevelBuilder;
import com.vertextrigger.main.VertexTrigger;
import com.vertextrigger.util.*;

public abstract class AbstractGameScreen implements Screen {
	protected Player player;
	protected World world;
	protected final Vector2 GRAVITY = new Vector2(0, -70000f);
	protected final float OLD_G = -70000f;
	private final static Array<Bullet> bullets = new Array<>();
	private final float ZOOM = 30f;
	private final VertexTrigger vertexTrigger;
	private final AbstractLevelBuilder levelBuilder;
	private final OrthographicCamera camera;
	private final SpriteBatch batch;
	private final float approxFPS = 60.0f;
	private final float TIMESTEP = 1.0f / approxFPS;
	private final int VELOCITYITERATIONS = 8; // Box2d manual recommends 8 & 3
	private final int POSITIONITERATIONS = 3; // for these iterations values
	private final Array<Mortal> mortalBeings;
	private final Array<Sprite> entitySprites;
	private final Box2DDebugRenderer physicsDebugger;
	private Array<Entity> entities;
	private Array<Sprite> backgroundSprites;
	private State state = State.RUNNING;
	private Stage stage;
	
	protected abstract void initialiseAssets();
	protected abstract AbstractLevelBuilder createLevelBuilder();
	
	/**
	 * Sets main game class for smooth screen transitions
	 * 
	 * @param vertexTrigger main game class
	 */
	public AbstractGameScreen(VertexTrigger vertexTrigger) {
		initialiseAssets();
		this.vertexTrigger = vertexTrigger;
		this.levelBuilder = createLevelBuilder();
		player = levelBuilder.getPlayer();
		mortalBeings = new Array<Mortal>();		
		entitySprites = new Array<Sprite>();
		camera = new OrthographicCamera(Gdx.graphics.getWidth()/ZOOM, Gdx.graphics.getHeight()/ZOOM);
		Vector2 initialPosition = setUpLevelAndReturnInitialPosition();
		batch = new SpriteBatch();
		entities.add(player);
		Controller controller = new Controller(player, this);
		Gdx.input.setInputProcessor(controller);
		stage = controller.getStage();
		physicsDebugger = new Box2DDebugRenderer();
		addMortal(player);
	}
	
	private Vector2 setUpLevelAndReturnInitialPosition() {
		levelBuilder.setGameScreen(this);
		entities = levelBuilder.buildEntities();
		backgroundSprites = levelBuilder.buildLevelLayout();
		return levelBuilder.getInitialPosition();
	}
	
	public static void addBullet(Bullet bullet) {
		if (!bullets.contains(bullet, true)) {
			bullets.add(bullet);
		}
	}
	
	/**
	 * Show method is invoked once when the level one screen is first created &
	 * is used to set up the screen of the first level
	 */
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
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
        
        for (Bullet bullet : bullets) {
        	if (!isInScreen(bullet.getSprite())) {
        		bullet.setNotVisible();
        	}
        }

		drawToScreen(delta, getVisibleSprites());
		removeDeadEntities();
		stage.draw();
		physicsDebugger.render(world, camera.combined);
	}
	
	private void clearScreen() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	private void removeDeadEntities() {
		for(Mortal mortal : mortalBeings) {
			if (mortal.isDead()) {
				mortal.die();
				if (mortal.isDeathAnimationFinished()) {
					mortalBeings.removeValue(mortal, true);
					entities.removeValue(mortal, true);
					world.destroyBody(mortal.getBody());
					if (mortal instanceof Player) {
						vertexTrigger.setScreen(GameScreenFactory.createPrototypeLevel(vertexTrigger));
					}
				}
			}
		}
	}
	
	private void updateWorld(float delta) {
		GRAVITY.y  = GRAVITY.y * delta;
		world.setGravity(new Vector2(0, OLD_G * delta));
		//TODO why does enemy fall faster than player
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
		boolean inRight = sprite.getX() < rightEdge + errorMargin;
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
		entities.add(entity);
	}
	
	public void addMortal(Mortal mortal) {
		mortalBeings.add(mortal);
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