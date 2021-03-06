package com.vertextrigger.levelbuilder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.ai.Magnet;
import com.vertextrigger.assets.AudioManager;
import com.vertextrigger.entities.MagnetFlowField;
import com.vertextrigger.entities.enemy.Bee;
import com.vertextrigger.entities.enemy.Poker;
import com.vertextrigger.factory.BackgroundFactory;
import com.vertextrigger.factory.EnemyFactory;
import com.vertextrigger.factory.MagnetFactory;
import com.vertextrigger.factory.PlatformFactory;
import com.vertextrigger.factory.entityfactory.PlayerFactory;
import com.vertextrigger.factory.entityfactory.ShieldFactory;
import com.vertextrigger.inanimate.StaticPlatform;
import com.vertextrigger.inanimate.portal.Portal;
import com.vertextrigger.inanimate.portal.PortalFactory;
import com.vertextrigger.screen.AbstractGameScreen;
import com.vertextrigger.util.GameObjectSize;

import static com.badlogic.gdx.math.MathUtils.radiansToDegrees;
import static com.vertextrigger.inanimate.portal.PortalTeleportation.MOVING_SAME_DIRECTION;
import static com.vertextrigger.util.GameObjectSize.GROUND_SIZE;
import static com.vertextrigger.util.GameObjectSize.MAGNET_SIZE;
import static com.vertextrigger.util.GameObjectSize.SMALL_PLATFORM_SIZE;

/**
 * A prototype level to allow manual testing of player controls & game objects
 */
public class PrototypeLevelBuilder extends AbstractLevelBuilder {
	private static final int CONTAINER_WIDTH = 4;
	private static final int CONTAINER_HEIGHT = 3;
	private final AbstractGameScreen screen;

	public PrototypeLevelBuilder(final World world, final AbstractGameScreen screen) {
		super(world, screen, CONTAINER_WIDTH, CONTAINER_HEIGHT);
		this.screen = screen;
		AudioManager.playLevelOneMusic();
		player = PlayerFactory.createPlayer(world, new Vector2(0, 0), screen, magnetFlowField);
	}

	@Override
	protected void createEnemies(final Steerable<Vector2> target) {
		final Poker poker = EnemyFactory.createPokerEnemy(world, new Vector2(0.5f, 0f));
		entities.add(poker);
		screen.addMortal(poker);

		final Bee bee = EnemyFactory.createBeeEnemy(world, new Vector2(0, 1), player.getSteerable(), screen, magnetFlowField);
		entities.add(bee);
		screen.addMortal(bee);
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
		final PlatformFactory factory = new PlatformFactory(world);
		final GameObjectSize size = SMALL_PLATFORM_SIZE;
		float positionX = 0;
		float positionY = -2.2f;

		for (int i = 0; i < 5; i++) {
			final Vector2 p0 = new Vector2(positionX, positionY);
			final StaticPlatform platform = factory.createPlatform("slice17", size, p0);
			platform.setRotation(0);
			sprites.add(platform.getSprite());
			positionX += size.getPhysicalWidth() * 2;
			positionY += 5f * GameObjectSize.OBJECT_SIZE;
		}

		final Vector2 p = new Vector2(-1.5f, -2.5f);
		final StaticPlatform bouncePlatform = factory.createPlatform("slice17", size, p);
		bouncePlatform.setRotation(200 * radiansToDegrees);
		sprites.add(bouncePlatform.getSprite());
		final Vector2 q = new Vector2(2.5f, -1.5f);
		final StaticPlatform bouncePlatform2 = factory.createPlatform("slice17", size, q);
		bouncePlatform2.setRotation(90);
		sprites.add(bouncePlatform2.getSprite());

		final Vector2 position = new Vector2(-CONTAINER_WIDTH, -CONTAINER_HEIGHT);
		final StaticPlatform groundPlatform = factory.createPlatform("snakeLava", GROUND_SIZE, position);
		sprites.add(groundPlatform.getSprite());

	}

	@Override
	protected void createGroundWalls() {
		super.createGroundWalls(CONTAINER_WIDTH, CONTAINER_HEIGHT);
	}

	@Override
	public void resetLevelLayout() {
		// TO BE USED WHEN WE GET ROUND TO CACHING EVERYTHING UPFRONT!!!!!!!!!!!!!
	}

	@Override
	public Array<Portal> createPortals() {
		final float portalHeight = GameObjectSize.PORTAL_SIZE.getPhysicalHeight();
		final float portalWidth = GameObjectSize.PORTAL_SIZE.getPhysicalWidth();
		final Vector2 portal1Position = new Vector2(-.8f + portalWidth, -2.5f + portalHeight);
		final Vector2 portal2Position = new Vector2(2.2f - portalWidth, -2f + portalHeight);
		final Array<Portal> portals = PortalFactory.createPortalPair(world, portal1Position, portal2Position, MOVING_SAME_DIRECTION);

		for (final Portal portal : portals) {
			sprites.add(portal.getSprite());
		}
		return portals;
	}

	@Override
	public Sprite getBackground() {
		final Sprite background = BackgroundFactory.getPrototypeLevelBackground();
		background.setPosition(-Gdx.graphics.getWidth() / 1.25f, -Gdx.graphics.getHeight() / 1.875f);
		background.setScale(0.012f);
		return background;
	}

	@Override
	public MagnetFlowField createMagnetFlowField() {
		final MagnetFactory factory = new MagnetFactory(world, CONTAINER_WIDTH, CONTAINER_HEIGHT);
		final int magnetStrength = 2;
		final Vector2[] worldPositions = new Vector2[] { new Vector2(-3.8f, -2.2f), new Vector2(), new Vector2(3.6f, 2.8f), };
		final Magnet[] magnets = new Magnet[worldPositions.length];

		for (int i = 0; i < worldPositions.length; i++) {
			magnets[i] = factory.createMagnet(MAGNET_SIZE, worldPositions[i], magnetStrength);
			sprites.add(magnets[i].getSprite());
		}

		return new MagnetFlowField(CONTAINER_WIDTH, CONTAINER_HEIGHT, magnets);
	}

	@Override
	public void createPowerUps() {
		ShieldFactory.createShield(world, new Vector2(0, -2.7f), screen);
	}
}