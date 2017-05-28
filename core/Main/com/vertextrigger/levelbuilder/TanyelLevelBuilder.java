package com.vertextrigger.levelbuilder;

import static com.vertextrigger.util.GameObjectSize.*;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.ai.Magnet;
import com.vertextrigger.assets.AudioManager;
import com.vertextrigger.entities.MagnetFlowField;
import com.vertextrigger.entities.enemy.*;
import com.vertextrigger.factory.*;
import com.vertextrigger.factory.entityfactory.*;
import com.vertextrigger.inanimate.*;
import com.vertextrigger.inanimate.portal.*;
import com.vertextrigger.screen.AbstractGameScreen;
import com.vertextrigger.util.GameObjectSize;

public class TanyelLevelBuilder extends AbstractLevelBuilder {
	private static final int CONTAINER_WIDTH = 4;
	private static final int CONTAINER_HEIGHT = 3;
	private final AbstractGameScreen screen;

	public TanyelLevelBuilder(final World world, final AbstractGameScreen screen) {
		super(world, screen, CONTAINER_WIDTH, CONTAINER_HEIGHT);
		this.screen = screen;
		AudioManager.playLevelOneMusic();
		player = PlayerFactory.createPlayer(world, new Vector2(-3.8f, -2.2f), screen, magnetFlowField);
	}

	@Override
	public MagnetFlowField createMagnetFlowField() {
		final MagnetFactory factory = new MagnetFactory(world, CONTAINER_WIDTH, CONTAINER_HEIGHT);
		final int magnetStrength = 2;
		final Vector2[] worldPositions = new Vector2[] { new Vector2(-2f, 2f) };
		final Magnet[] magnets = new Magnet[worldPositions.length];

		for (int i = 0; i < worldPositions.length; i++) {
			magnets[i] = factory.createMagnet(MAGNET_SIZE, worldPositions[i], magnetStrength);
			sprites.add(magnets[i].getSprite());
		}

		return new MagnetFlowField(CONTAINER_WIDTH, CONTAINER_HEIGHT, magnets);
	}

	@Override
	protected void createEnemies(final Steerable<Vector2> target) {
		final Bee bee = EnemyFactory.createBeeEnemy(world, new Vector2(-1, 1), player.getSteerable(), screen, magnetFlowField);
		entities.add(bee);
		screen.addMortal(bee);
		final Bee bee1 = EnemyFactory.createBeeEnemy(world, new Vector2(1, -1), player.getSteerable(), screen, magnetFlowField);
		entities.add(bee1);
		screen.addMortal(bee1);
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
		final String spriteName = "slice17";
		createPlatform(factory, spriteName, SMALL_PLATFORM_SIZE,
				new Vector2(-4 + SMALL_PLATFORM_SIZE.getPhysicalWidth(), -3 + SMALL_PLATFORM_SIZE.getPhysicalHeight()), 0);
		createPlatform(factory, spriteName, SMALL_PLATFORM_SIZE, new Vector2(-.5f, -2.55f), 23.5f);
		createPlatform(factory, spriteName, SMALL_PLATFORM_SIZE, new Vector2(-.7f, -.4f), 68f);
		createPlatform(factory, spriteName, TANYEL_LEVEL_LONG_PLATFORM_SIZE, new Vector2(CONTAINER_WIDTH * .44f, -CONTAINER_HEIGHT + .3f), 0);
		createPlatform(factory, spriteName, TANYEL_LEVEL_LONG_PLATFORM_SIZE, new Vector2(CONTAINER_WIDTH * .44f, 0), 0);
		createPlatform(factory, spriteName, TANYEL_LEVEL_MEDIUM_PLATFORM_SIZE, new Vector2(-.35f, 1.55f), 90f);
		createPlatform(factory, spriteName, SMALL_PLATFORM_SIZE, new Vector2(-4 + SMALL_PLATFORM_SIZE.getPhysicalWidth(), 0), 0);
		createPit(factory, spriteName, TANYEL_LEVEL_PIT_SIZE, new Vector2(-2f, -CONTAINER_HEIGHT - GameObjectSize.PLAYER_SIZE.getPhysicalHeight()));
	}

	private StaticPlatform createPlatform(final PlatformFactory factory, final String sprite, final GameObjectSize size, final Vector2 position,
			final float rotation) {
		final StaticPlatform platform = factory.createPlatform(sprite, size, position);
		platform.setRotation(rotation);
		sprites.add(platform.getSprite());
		return platform;
	}

	private Pit createPit(final PlatformFactory factory, final String sprite, final GameObjectSize size, final Vector2 position) {
		return factory.createPit(sprite, size, position);
	}

	@Override
	protected void createGroundWalls() {
		final Vector2 bottomLeft = new Vector2(-CONTAINER_WIDTH, -CONTAINER_HEIGHT - GameObjectSize.PLAYER_SIZE.getPhysicalHeight() * 2);
		final Vector2 bottomRight = new Vector2(CONTAINER_WIDTH, -CONTAINER_HEIGHT - GameObjectSize.PLAYER_SIZE.getPhysicalHeight() * 2);
		final Vector2 topLeft = new Vector2(-CONTAINER_WIDTH, CONTAINER_HEIGHT);
		final Vector2 topRight = new Vector2(CONTAINER_WIDTH, CONTAINER_HEIGHT);

		new Ground(world, new Vector2[] { bottomRight, bottomLeft, topLeft, topRight, bottomRight });
	}

	@Override
	public Array<Portal> createPortals() {
		final float portalHeight = GameObjectSize.PORTAL_SIZE.getPhysicalHeight();
		final float portalWidth = GameObjectSize.PORTAL_SIZE.getPhysicalWidth();
		final Vector2 portal1Position = new Vector2(CONTAINER_WIDTH - portalWidth * 3, -2.5f + portalHeight);
		final Vector2 portal2Position = new Vector2(-CONTAINER_WIDTH / 3, 0);
		final Array<Portal> portals = PortalFactory.createPortalPair(world, portal1Position, portal2Position,
				PortalTeleportation.MOVING_OPPOSITE_HORIZONTAL_DIRECTION);

		for (final Portal portal : portals) {
			sprites.add(portal.getSprite());
		}
		return portals;
	}

	@Override
	public void createPowerUps() {
		ShieldFactory.createShield(world, new Vector2(.5f, -2.3f), screen);
	}

	@Override
	public void resetLevelLayout() {
	}

	@Override
	public Sprite getBackground() {
		return null;
	}
}