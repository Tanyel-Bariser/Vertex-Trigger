package com.vertextrigger.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.entities.*;
import com.vertextrigger.entities.player.*;
import com.vertextrigger.factory.GameScreenFactory;
import com.vertextrigger.levelbuilder.AbstractLevelBuilder;
import com.vertextrigger.main.VertexTrigger;
import com.vertextrigger.util.*;

public abstract class AbstractGameScreen implements Screen {
	private static final float baseGravity = -9.81f;
	protected Player player;
	protected World world;
	protected final Vector2 GRAVITY = new Vector2(0, baseGravity);
	private final static Array<Bullet> bullets = new Array<>();
	private static float phoneWidth = 768;
	public static final float WIDTH = Gdx.graphics.getWidth();
	public static final float HEIGHT = Gdx.graphics.getHeight();
	private static float adjustedPhoneWidth = WIDTH / phoneWidth;
	private final float ZOOM = (20 / GameObjectSize.OBJECT_SIZE) * adjustedPhoneWidth;
	private final VertexTrigger vertexTrigger;
	private final AbstractLevelBuilder levelBuilder;
	private final OrthographicCamera camera;
	private final SpriteBatch batch;
	private final float approxFPS = 60.0f;
	private final float TIMESTEP = 1.0f / approxFPS;
	private final int VELOCITYITERATIONS = 8; // Box2d manual recommends 8 & 3
	private final int POSITIONITERATIONS = 3; // for these iterations values
	private final Array<Mortal> mortalBeings;
	private final Array<Mortal> deadMortals;
	private final Array<Sprite> entitySprites;
	private final Box2DDebugRenderer physicsDebugger;
	private Array<Entity> entities;
	private Array<Sprite> backgroundSprites;
	private State state = State.RUNNING;
	private final Stage stage;
	private static final float roomForThumbs;

	protected abstract void initialiseAssets();

	protected abstract AbstractLevelBuilder createLevelBuilder();

	static {
		if (Gdx.app.getType() == ApplicationType.Android) {
			roomForThumbs = adjustedPhoneWidth / 2;
		} else {
			roomForThumbs = 0;
		}
	}

	/**
	 * Sets main game class for smooth screen transitions
	 *
	 * @param vertexTrigger
	 *            main game class
	 */
	public AbstractGameScreen(final VertexTrigger vertexTrigger) {
		initialiseAssets();
		this.vertexTrigger = vertexTrigger;
		levelBuilder = createLevelBuilder();
		player = levelBuilder.getPlayer();
		mortalBeings = new Array<>();
		deadMortals = new Array<>();
		entitySprites = new Array<>();
		camera = new OrthographicCamera(Gdx.graphics.getWidth() / ZOOM, Gdx.graphics.getHeight() / ZOOM);
		final Vector2 initialPosition = setUpLevelAndReturnInitialPosition();
		batch = new SpriteBatch();
		entities.add(player);
		final Controller controller = new Controller(player, this, state);
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

	public static void addBullet(final Bullet bullet) {
		if (!bullets.contains(bullet, true)) {
			bullets.add(bullet);
		}
	}

	/**
	 * Show method is invoked once when the level one screen is first created & is used to set up the screen of the first level
	 */
	@Override
	public void show() {
		if (Gdx.app.getType() == Application.ApplicationType.Android) {
			Gdx.input.setInputProcessor(stage);
		}
	}

	/**
	 * Render method is invoked repeatedly once per frame, approximately 60 frames per second, during the game
	 */
	@Override
	public void render(final float delta) {
		clearScreen();
		if (state == State.RUNNING) {
			updateWorld(delta);
			updateEntities(delta);
			updateCamera();
		}

		for (final Bullet bullet : bullets) {
			if ((isInScreen(bullet.getSprite()) == false) || bullet.hitPlayer() || player.isDead() || bullet.isTooSlow()) {
				bullets.removeValue(bullet, true);
				entities.removeValue(bullet, true);
				world.destroyBody(bullet.getBody());
			}
		}

		drawToScreen(delta, getVisibleSprites());
		stage.draw();
		physicsDebugger.render(world, camera.combined);
		removeDeadEntities();
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
						vertexTrigger.setScreen(GameScreenFactory.createPrototypeLevel(vertexTrigger));
					}
				}
			}
		}
	}

	private void updateWorld(final float delta) {
		final float adjustedDelta = approxFPS * delta;
		GRAVITY.y = baseGravity * adjustedDelta * adjustedDelta;
		world.setGravity(GRAVITY);
		world.step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATIONS);
	}

	private void updateEntities(final float delta) {
		entitySprites.clear();
		for (final Entity entity : entities) {
			final Sprite sprite = entity.update(delta);
			entitySprites.add(sprite);
		}
	}

	private Array<Sprite> getVisibleSprites() {
		final Array<Sprite> sprites = new Array<Sprite>();
		for (final Sprite sprite : entitySprites) {
			if (isInScreen(sprite)) {
				sprites.add(sprite);
			}
		}
		return entitySprites;
	}

	private boolean isInScreen(final Sprite sprite) {
		final float topEdge = camera.position.y + (camera.viewportHeight / 2);
		final float bottomEdge = camera.position.y - (camera.viewportHeight / 2);
		final float leftEdge = camera.position.x - (camera.viewportWidth / 2);
		final float rightEdge = camera.position.x + (camera.viewportWidth / 2);
		final float errorMargin = GameObjectSize.OBJECT_SIZE;

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
		for (final Sprite sprite : visibleEntitySprite) {
			sprite.draw(batch);
		}
		for (final Sprite sprite : backgroundSprites) {
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
	 * Hide method is invoked automatically when the screen is switch to another screen & is an appropriate place to dispose of the assets being used
	 * in this screen
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
	 * Dispose method needs to be invoked manually when this class is no longer being used to dispose of the assets it's using
	 */
	@Override
	public void dispose() {
		// Dispose assets being used in this screen
	}
}