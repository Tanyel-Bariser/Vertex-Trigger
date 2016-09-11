package com.vertextrigger.levelbuilder;

import static com.vertextrigger.inanimate.portal.PortalTeleportation.MOVING_SAME_DIRECTION;
import static com.vertextrigger.util.GameObjectSize.SMALL_PLATFORM_SIZE;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.assets.AudioManager;
import com.vertextrigger.entities.enemy.AbstractEnemy;
import com.vertextrigger.factory.*;
import com.vertextrigger.factory.entityfactory.PlayerFactory;
import com.vertextrigger.inanimate.*;
import com.vertextrigger.inanimate.portal.*;
import com.vertextrigger.screen.AbstractGameScreen;
import com.vertextrigger.util.GameObjectSize;

/**
 * A prototype level to allow manual testing of player controls & game objects
 */
public class PrototypeLevelBuilder extends AbstractLevelBuilder {
	private final AbstractGameScreen screen;

	public PrototypeLevelBuilder(final World world, final AbstractGameScreen screen, final float CONTAINER_WIDTH, final float CONTAINER_HEIGHT) {
		super(world, screen, CONTAINER_WIDTH, CONTAINER_HEIGHT);
		this.screen = screen;
		AudioManager.playLevelOneMusic();
		player = PlayerFactory.createPlayer(world, new Vector2(0, 0), screen);
	}

	@Override
	protected void createEnemies(final Steerable<Vector2> target) {
		AbstractEnemy enemy = EnemyFactory.createPokerEnemy(world, new Vector2(0.5f, 0f));
		entities.add(enemy);
		screen.addMortal(enemy);

		enemy = EnemyFactory.createBeeEnemy(world, new Vector2(0, 1), player, screen);
		entities.add(enemy);
		screen.addMortal(enemy);
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
		bouncePlatform.setRotation(200);
		sprites.add(bouncePlatform.getSprite());
		final Vector2 q = new Vector2(2.5f, -1.5f);
		final StaticPlatform bouncePlatform2 = factory.createPlatform("slice17", size, q);
		bouncePlatform2.setRotation((float) Math.PI / 2);
		sprites.add(bouncePlatform2.getSprite());

	}

	@Override
	protected void createGroundWalls() {
		final Vector2 bottomLeft = new Vector2(-CONTAINER_WIDTH, -CONTAINER_HEIGHT);
		final Vector2 bottomRight = new Vector2(-CONTAINER_WIDTH, CONTAINER_HEIGHT);
		final Vector2 topRight = new Vector2(CONTAINER_WIDTH, CONTAINER_HEIGHT);
		final Vector2 topLeft = new Vector2(CONTAINER_WIDTH, -CONTAINER_HEIGHT);

		new Ground(world, new Vector2[] { topLeft, bottomLeft, bottomRight, topRight, topLeft });
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
		final Vector2 portal3Position = new Vector2(-.5f + portalWidth, -3f + portalHeight);
		final Vector2 portal4Position = new Vector2(.2f - portalWidth, -1f + portalHeight);
		final Vector2 portal5Position = new Vector2(-3f + portalWidth, -0.5f + portalHeight);
		final Vector2 portal6Position = new Vector2(2.9f - portalWidth, -1f + portalHeight);

		final Array<Portal> portals = PortalFactory.createPortalPair(world, portal1Position, portal2Position, MOVING_SAME_DIRECTION);
		final Array<Portal> portals1 = PortalFactory.createPortalPair(world, portal3Position, portal4Position, MOVING_SAME_DIRECTION);
		final Array<Portal> portals2 = PortalFactory.createPortalPair(world, portal5Position, portal6Position, MOVING_SAME_DIRECTION);

		for (final Portal portal : portals) {
			sprites.add(portal.getSprite());
		}
		for (final Portal portal : portals1) {
			sprites.add(portal.getSprite());
		}
		for (final Portal portal : portals2) {
			sprites.add(portal.getSprite());
		}
		return portals;
	}
}