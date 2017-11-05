//package com.vertextrigger.levelbuilder;
//
//import com.badlogic.gdx.ai.steer.Steerable;
//import com.badlogic.gdx.graphics.g2d.Sprite;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.physics.box2d.World;
//import com.badlogic.gdx.utils.Array;
//import com.vertextrigger.ai.Magnet;
//import com.vertextrigger.assets.AudioManager;
//import com.vertextrigger.entities.MagnetFlowField;
//import com.vertextrigger.entities.enemy.Bee;
//import com.vertextrigger.factory.EnemyFactory;
//import com.vertextrigger.factory.MagnetFactory;
//import com.vertextrigger.factory.entityfactory.PlayerFactory;
//import com.vertextrigger.factory.entityfactory.ShieldFactory;
//import com.vertextrigger.inanimate.portal.Portal;
//import com.vertextrigger.inanimate.portal.PortalFactory;
//import com.vertextrigger.inanimate.portal.PortalTeleportation;
//import com.vertextrigger.screen.AbstractGameScreen;
//import com.vertextrigger.util.GameObjectSize;
//
//import static com.vertextrigger.util.GameObjectSize.MAGNET_SIZE;
//import static com.vertextrigger.util.GameObjectSize.PLAYER_SIZE;
//import static com.vertextrigger.util.GameObjectSize.SMALL_PLATFORM_SIZE;
//import static com.vertextrigger.util.GameObjectSize.TANYEL_LEVEL_LONG_PLATFORM_SIZE;
//import static com.vertextrigger.util.GameObjectSize.TANYEL_LEVEL_MEDIUM_PLATFORM_SIZE;
//import static com.vertextrigger.util.GameObjectSize.TANYEL_LEVEL_PIT_SIZE;
//
//public class TanyelLevelBuilder extends AbstractLevelBuilder {
//	private static final int CONTAINER_WIDTH = 4;
//	private static final int CONTAINER_HEIGHT = 3;
//	private final AbstractGameScreen screen;
//
//	public TanyelLevelBuilder(final World world, final AbstractGameScreen screen) {
//		super(world, screen, CONTAINER_WIDTH, CONTAINER_HEIGHT);
//		this.screen = screen;
//		AudioManager.playLevelOneMusic();
//		player = PlayerFactory.createPlayer(world, new Vector2(-3.8f, -2.2f), screen, magnetFlowField);
//	}
//
//	@Override
//	public MagnetFlowField createMagnetFlowField() {
//		final MagnetFactory factory = new MagnetFactory(world, CONTAINER_WIDTH, CONTAINER_HEIGHT);
//		final int magnetStrength = 2;
//		final Vector2[] worldPositions = new Vector2[] { new Vector2(-2f, 2.0f) };
//		final Magnet[] magnets = new Magnet[worldPositions.length];
//
//		for (int i = 0; i < worldPositions.length; i++) {
//			magnets[i] = factory.createMagnet(MAGNET_SIZE, worldPositions[i], magnetStrength);
//			sprites.add(magnets[i].getSprite());
//		}
//
//		return new MagnetFlowField(CONTAINER_WIDTH, CONTAINER_HEIGHT, magnets);
//	}
//
//	@Override
//	protected void createEnemies(final Steerable<Vector2> target) {
//		final Bee bee = EnemyFactory.createBeeEnemy(world, new Vector2(-1, 1), player.getSteerable(), screen, magnetFlowField);
//		entities.add(bee);
//		screen.addMortal(bee);
//		final Bee bee1 = EnemyFactory.createBeeEnemy(world, new Vector2(1, -1), player.getSteerable(), screen, magnetFlowField);
//		entities.add(bee1);
//		screen.addMortal(bee1);
//	}
//
//	@Override
//	protected void createDangerousBalls() {
//	}
//
//	@Override
//	protected void createMovingPlatforms() {
//		final String spriteName = "slice17";
//		//simpleMovingPlatform(spriteName, SMALL_PLATFORM_SIZE, new Vector2(-3.8f, -2.5f), new Vector2(-2, -2.5f));
//		//simpleMovingPlatform(spriteName, SMALL_PLATFORM_SIZE, new Vector2(-3.8f, 0), new Vector2(-2, 0));
//		rectangleMovingPlatform(spriteName, SMALL_PLATFORM_SIZE, new Vector2(-3.5f, 0), new Vector2(-2, -2.5f), false);
//	}
//
//	@Override
//	protected void createTimedPlatforms() {
//	}
//
//	@Override
//	protected void createStaticPlatforms() {
//		final String spriteName = "slice17";
//		staticPlatform(spriteName, SMALL_PLATFORM_SIZE, new Vector2(-.5f, -2.55f), 20f);
//		staticPlatform(spriteName, SMALL_PLATFORM_SIZE, new Vector2(-.7f, -.4f), 73.5f);
//		staticPlatform(spriteName, TANYEL_LEVEL_LONG_PLATFORM_SIZE, new Vector2(CONTAINER_WIDTH * .44f, -CONTAINER_HEIGHT + .3f), 0);
//		staticPlatform(spriteName, TANYEL_LEVEL_LONG_PLATFORM_SIZE, new Vector2(CONTAINER_WIDTH * .44f, 0), 0);
//		staticPlatform(spriteName, TANYEL_LEVEL_MEDIUM_PLATFORM_SIZE, new Vector2(-.35f, 1.55f), 90f);
//		pit(spriteName, TANYEL_LEVEL_PIT_SIZE, new Vector2(-2f, -CONTAINER_HEIGHT - PLAYER_SIZE.getPhysicalHeight()));
//	}
//
//	@Override
//	protected void createGroundWalls() {
//		super.createGroundWalls(CONTAINER_WIDTH, CONTAINER_HEIGHT, PLAYER_SIZE.getPhysicalHeight() * 2);
//	}
//
//	@Override
//	public Array<Portal> createPortals() {
//		final float portalHeight = GameObjectSize.PORTAL_SIZE.getPhysicalHeight();
//		final float portalWidth = GameObjectSize.PORTAL_SIZE.getPhysicalWidth();
//		final Vector2 portal1Position = new Vector2(CONTAINER_WIDTH - portalWidth * 3, -2.5f + portalHeight);
//		final Vector2 portal2Position = new Vector2(-CONTAINER_WIDTH / 3, 0);
//		final Array<Portal> portals = PortalFactory.createPortalPair(world, portal1Position, portal2Position,
//				PortalTeleportation.MOVING_OPPOSITE_HORIZONTAL_DIRECTION);
//
//		for (final Portal portal : portals) {
//			sprites.add(portal.getSprite());
//		}
//		return portals;
//	}
//
//	@Override
//	public void createPowerUps() {
//		ShieldFactory.createShield(world, new Vector2(.5f, -2.3f), screen);
//	}
//
//	@Override
//	public void resetLevelLayout() {
//	}
//
//	@Override
//	public Sprite getBackground() {
//		return null;
//	}
//}