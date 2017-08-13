package com.vertextrigger.screen;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.controller.Controller;
import com.vertextrigger.entities.*;
import com.vertextrigger.entities.bullet.Bullet;
import com.vertextrigger.entities.player.Player;
import com.vertextrigger.factory.GameScreenFactory;
import com.vertextrigger.levelbuilder.AbstractLevelBuilder;
import com.vertextrigger.main.VertexTrigger;
import com.vertextrigger.util.*;

public abstract class AbstractGameScreen implements Screen {
	private static final float baseGravity = -9.81f;
	private Sprite background;
	protected Player player;
	protected World world;
	protected final Vector2 GRAVITY = new Vector2(0, baseGravity);
	private final static Array<Bullet> bullets = new Array<>();
	private static float phoneWidth = 768;
	public static final float WIDTH = Gdx.graphics.getWidth();
	public static final float HEIGHT = Gdx.graphics.getHeight();
	private static float adjustedPhoneWidth = WIDTH / phoneWidth;
	private static final float ZOOM = (18 / GameObjectSize.OBJECT_SIZE) * adjustedPhoneWidth;// Should be level specific
	private final VertexTrigger vertexTrigger;
	private AbstractLevelBuilder levelBuilder;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private final float approxFPS = 60.0f;
	private final float TIMESTEP = 1.0f / approxFPS;
	private final int VELOCITYITERATIONS = 8; // Box2d manual recommends 8 & 3
	private final int POSITIONITERATIONS = 3; // for these iterations values
	private Array<Mortal> mortalBeings;
	private Array<Mortal> deadMortals;
	private Array<Sprite> entitySprites;
	private Box2DDebugRenderer physicsDebugger;
	private Array<Entity> entities;
	private Array<Sprite> backgroundSprites;
	private State state = State.RUNNING;
	private Stage stage;
	private static final float roomForThumbs;
	private static final float maxDelta = 0.05f;
	private final Array<Sprite> visibleSprites = new Array<Sprite>();

	protected abstract void initialiseAssets();

	protected abstract void disposeOfAssets();

	protected abstract AbstractLevelBuilder createLevelBuilder();

	static {
		if (Gdx.app.getType() == ApplicationType.Android) {
			roomForThumbs = adjustedPhoneWidth / 2;
		} else {
			roomForThumbs = 0;
		}
	}

	public AbstractGameScreen(final VertexTrigger vertexTrigger) {
		this.vertexTrigger = vertexTrigger;
	}

	/**
	 * Show method is invoked automatically every time we recreate the level
	 *
	 * @see AbstractGameScreen#hide
	 *
	 *      Load level assets and set up objects
	 */
	@Override
	public void show() {
		initialiseAssets();
		initializeLevel();
	}

	/**
	 * Hide method is invoked automatically when the screen is switched to another screen
	 *
	 * @see AbstractGameScreen#show()
	 *
	 *      Unload level assets
	 */
	@Override
	public void hide() {
		dispose();
	}

	// TODO refactor this as it is too long and very dependent on statement order
	// TODO e.g. setUpLevel cannot be moved, not can setting the input processor
	private void initializeLevel() {
		levelBuilder = createLevelBuilder();
		player = levelBuilder.getPlayer();
		mortalBeings = new Array<>();
		deadMortals = new Array<>();
		entitySprites = new Array<>();
		camera = new OrthographicCamera(Gdx.graphics.getWidth() / ZOOM, Gdx.graphics.getHeight() / ZOOM);
		setUpLevel();
		batch = new SpriteBatch();
		entities.add(player);
		stage = new Stage();

		if (Gdx.app.getType() == ApplicationType.Desktop) {
			Gdx.input.setInputProcessor(Controller.createDesktopController(player, this));
		} else {
			Controller.createAndroidSliderController(player, this, stage);
			Gdx.input.setInputProcessor(stage);
		}
		physicsDebugger = new Box2DDebugRenderer();
		addMortal(player);
	}

	private void setUpLevel() {
		levelBuilder.setGameScreen(this);
		// background = levelBuilder.getBackground();
		entities = levelBuilder.buildEntities();
		backgroundSprites = levelBuilder.buildLevelLayout();
	}

	public static void addBullet(final Bullet bullet) {
		if (!bullets.contains(bullet, true)) {
			bullets.add(bullet);
		}
	}

	private final FPSLogger fpsLogger = new FPSLogger();
	private float acc = 0;
	private long diff, start = System.currentTimeMillis();
	private final int maxFPS = 30;

	public void limitToMax30FPS() {
		if (maxFPS > 0) {
			diff = System.currentTimeMillis() - start;
			final long targetDelay = 1000 / maxFPS;
			if (diff < targetDelay) {
				try {
					Thread.sleep(targetDelay - diff);
				} catch (final InterruptedException e) {
				}
			}
			start = System.currentTimeMillis();
		}
	}

	@Override
	public void render(final float delta) {
		// fpsLogger.log();
		clearScreen();
		if (state == State.RUNNING) {
			// limitToMax30FPS();
			if (delta < maxDelta) {
				acc += delta;
			} else {
				acc += maxDelta;
			}
			while (acc >= TIMESTEP) {
				cachePreviousEntityPositions();
				world.step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATIONS);
				acc -= TIMESTEP;
			}

			final float alpha = acc / TIMESTEP;
			updateEntities(delta, alpha);
			updateCamera();
		}

		for (final Bullet bullet : bullets) {
			if (isInScreen(bullet.getSprite()) == false || bullet.isBulletDestroyed() || player.isDead() /* || bullet.isTooSlow() */) {
				bullets.removeValue(bullet, true);
				entities.removeValue(bullet, true);
				world.destroyBody(bullet.getBody());
			}
		}

		drawToScreen(delta, getVisibleSprites());
		stage.draw();
		// physicsDebugger.render(world, camera.combined);
		removeDeadEntities();
	}

	private void cachePreviousEntityPositions() {
		for (final Entity entity : entities) {
			entity.cachePosition();
		}
	}

	private void clearScreen() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	private void removeDeadEntities() {
		for (final Mortal mortal : mortalBeings) {
			if (mortal.isDead()) {
				// fixes bug where Entity#die is called tens of times in a row
				// before death animation is finished
				if (!deadMortals.contains(mortal, true)) {
					mortal.die();
					deadMortals.add(mortal);
				}

				if (mortal.isDeathAnimationFinished()) {
					mortalBeings.removeValue(mortal, true);
					entities.removeValue(mortal, true);
					deadMortals.removeValue(mortal, true);
					world.destroyBody(mortal.getBody());
					if (mortal instanceof Player) {
						world.dispose();
						// vertexTrigger.setScreen(GameScreenFactory.createTanyelLevel(vertexTrigger));
						vertexTrigger.setScreen(GameScreenFactory.createHughLevel(vertexTrigger));
					}
				}
			}
		}
	}

	private void updateEntities(final float delta, final float alpha) {
		entitySprites.clear();
		for (final Entity entity : entities) {
			final Sprite sprite = entity.update(delta, alpha);
			Sprite playerSprite = null;
			if (entity instanceof Player) {
				playerSprite = sprite;
			} else {
				entitySprites.add(sprite);
			}

			// Add playerSprite last in ordered array so is drawn last to screen so is on top of other images
			if (playerSprite != null) {
				entitySprites.add(playerSprite);
			}
		}
	}

	private Array<Sprite> getVisibleSprites() {
		visibleSprites.clear();
		for (final Sprite sprite : entitySprites) {
			if (isInScreen(sprite)) {
				visibleSprites.add(sprite);
			}
		}
		return visibleSprites;
	}

	private boolean isInScreen(final Sprite sprite) {
		final float topEdge = camera.position.y + (camera.viewportHeight / 2);
		final float bottomEdge = camera.position.y - (camera.viewportHeight / 2);
		final float leftEdge = camera.position.x - (camera.viewportWidth / 2);
		final float rightEdge = camera.position.x + (camera.viewportWidth / 2);
		final float errorMargin = adjustedPhoneWidth * 0.8f;

		final boolean belowTop = sprite.getY() < (topEdge + errorMargin);
		final boolean aboveBottom = sprite.getY() > (bottomEdge - errorMargin);
		final boolean inRight = sprite.getX() < (rightEdge + errorMargin);
		final boolean inLeft = sprite.getX() > (leftEdge - errorMargin);
		return belowTop && aboveBottom && inRight && inLeft;
	}

	private void updateCamera() {
		updateCameraPositionX();
		updateCameraPositionY();
		camera.update();
	}

	private void updateCameraPositionX() {
		final float playerX = player.getBody().getPosition().x;
		if (playerX < (levelBuilder.getLeftBorderOfLevel() + (camera.viewportWidth / 2))) {
			camera.position.x = levelBuilder.getLeftBorderOfLevel() + (camera.viewportWidth / 2);
		} else if (playerX > (levelBuilder.getRightBorderOfLevel() - (camera.viewportWidth / 2))) {
			camera.position.x = levelBuilder.getRightBorderOfLevel() - (camera.viewportWidth / 2);
		} else {
			camera.position.x = playerX;
		}
	}

	private void updateCameraPositionY() {
		final float playerY = player.getBody().getPosition().y;
		if (playerY < ((levelBuilder.getGroundLevel() + (camera.viewportHeight / 2)) - roomForThumbs)) {
			camera.position.y = (levelBuilder.getGroundLevel() + (camera.viewportHeight / 2)) - roomForThumbs;
		} else if (playerY > (levelBuilder.getCeilingLevel() - (camera.viewportHeight / 2))) {
			camera.position.y = levelBuilder.getCeilingLevel() - (camera.viewportHeight / 2);
		} else {
			camera.position.y = playerY;
		}
	}

	private void drawToScreen(final float delta, final Array<Sprite> visibleEntitySprite) {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		// background.draw(batch);
		for (final Sprite sprite : backgroundSprites) {
			sprite.draw(batch);
		}
		for (final Sprite sprite : visibleEntitySprite) {
			sprite.draw(batch);
		}
		batch.end();
	}

	/**
	 * Automatically called when application is resized
	 */
	@Override
	public void resize(final int width, final int height) {
	}

	/**
	 * @param entity
	 *            is added to the entity container/data structure
	 */
	public void addEntity(final Entity entity) {
		entities.add(entity);
	}

	public void addMortal(final Mortal mortal) {
		mortalBeings.add(mortal);
	}

	/**
	 * Resets the level layout when player has died & repositions the player back to the initial position of the level Does not recreate game objects,
	 * it only resets their initial positions
	 */
	public void resetLevel() {
		levelBuilder.resetLevelLayout();
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
	 * Dispose method needs to be invoked manually when this class is no longer being used to dispose of the assets it's using
	 */
	@Override
	public void dispose() {
		disposeOfAssets();
	}
}