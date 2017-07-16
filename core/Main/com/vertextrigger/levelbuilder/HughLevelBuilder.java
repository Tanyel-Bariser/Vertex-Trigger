package com.vertextrigger.levelbuilder;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.assets.AudioManager;
import com.vertextrigger.entities.AbstractEntity;
import com.vertextrigger.entities.MagnetFlowField;
import com.vertextrigger.entities.callback.DeathCallback;
import com.vertextrigger.entities.callback.PositionCallback;
import com.vertextrigger.entities.callback.Runnable;
import com.vertextrigger.entities.enemy.Bee;
import com.vertextrigger.entities.enemy.Poker;
import com.vertextrigger.entities.player.Player;
import com.vertextrigger.factory.EnemyFactory;
import com.vertextrigger.factory.PlatformFactory;
import com.vertextrigger.factory.bodyfactory.PlatformBodyFactory.Friction;
import com.vertextrigger.factory.entityfactory.PlayerFactory;
import com.vertextrigger.factory.entityfactory.ShieldFactory;
import com.vertextrigger.inanimate.portal.Portal;
import com.vertextrigger.screen.AbstractGameScreen;

import static com.badlogic.gdx.math.MathUtils.degreesToRadians;
import static com.vertextrigger.inanimate.portal.PortalTeleportation.MOVING_DIFFERENT_XY_AXIS_DIRECTION;
import static com.vertextrigger.inanimate.portal.PortalTeleportation.MOVING_OPPOSITE_HORIZONTAL_DIRECTION;
import static com.vertextrigger.inanimate.portal.PortalTeleportation.MOVING_SAME_DIRECTION;
import static com.vertextrigger.util.GameObjectSize.LARGE_PLATFORM_SIZE;
import static com.vertextrigger.util.GameObjectSize.MEDIUM_PLATFORM_SIZE;
import static com.vertextrigger.util.GameObjectSize.SIGN_SIZE;
import static com.vertextrigger.util.GameObjectSize.SMALL_PLATFORM_SIZE;
import static com.vertextrigger.util.GameObjectSize.TINY_PLATFORM_SIZE;

public class HughLevelBuilder extends AbstractLevelBuilder {

	private static final int CONTAINER_WIDTH = 8;
	private static final int CONTAINER_HEIGHT = 9;

	private final AbstractGameScreen screen;
	private final PlatformFactory platformFactory;

	private boolean madeBee;

	public HughLevelBuilder(final World world, final AbstractGameScreen screen) {
		super(world, screen, CONTAINER_WIDTH, CONTAINER_HEIGHT);
		this.screen = screen;
		platformFactory = new PlatformFactory(world);
		AudioManager.playLevelOneMusic();

		// start
		//player = PlayerFactory.createPlayer(world, new Vector2(fromLeft(1), fromBottom(0)), screen, magnetFlowField);

		// before poker #2
		// player = PlayerFactory.createPlayer(world, new Vector2(fromLeft(8), fromBottom(5.6f)), screen, magnetFlowField);

		// pre bossfight
		player = PlayerFactory.createPlayer(world, new Vector2(fromLeft(3f), fromBottom(13f)), screen, magnetFlowField);

		// end
		// player = PlayerFactory.createPlayer(world, new Vector2(fromLeft(9.5f), fromBottom(16.1f)), screen, magnetFlowField);
	}

	@Override
	public MagnetFlowField createMagnetFlowField() {
		return null;
	}

	@Override
	protected void createEnemies(final Steerable<Vector2> target) {
		final Poker poker1 = EnemyFactory.createPokerEnemy(world, new Vector2(fromLeft(15.1f), fromBottom(2)));
		entities.add(poker1);
		screen.addMortal(poker1);

		final Poker poker2 = EnemyFactory.createPokerEnemy(world, new Vector2(fromLeft(6.5f), fromBottom(6)));
		poker2.getBody().setFixedRotation(false);
		poker2.getBody().setTransform(new Vector2(fromLeft(6.5f), fromBottom(5.6f)), 180 * degreesToRadians);
		poker2.getBody().setFixedRotation(true);
		poker2.getBody().setGravityScale(0);
		entities.add(poker2);
		screen.addMortal(poker2);

		player.addCallbacks(new PositionCallback(new Runnable() {
			@Override
			public void run() {
				if (!madeBee) {
					final Bee bee = EnemyFactory.createBeeEnemy(world, new Vector2(fromLeft(2), fromBottom(15)), player.getSteerable(), screen, magnetFlowField);
					entities.add(bee);
					screen.addMortal(bee);
					bee.addCallbacks(new DeathCallback(new Runnable() {
						@Override
						public void run() {
							AudioManager.playPickUpSound();
							AudioManager.playLevelOneMusic();
							simpleMovingPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(fromLeft(5), fromBottom(15.5f)),  new Vector2(fromLeft(10), fromBottom(15.5f)));
						}

					}, bee));
					madeBee = true;
					AudioManager.playLevelTwoMusic();
				}
			}
		}, new Vector2(fromLeft(0.5f), fromBottom(14)), player.getPosition()));
	}

	@Override
	protected void createDangerousBalls() {
		AudioManager.toggleMute();
	}

	@Override
	protected void createMovingPlatforms() {
		simpleMovingPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(fromLeft(2.5f), fromBottom(9.3f)), new Vector2(fromLeft(5.5f), fromBottom(9.3f)));
		simpleMovingPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(fromLeft(6.5f), fromBottom(9.3f)), new Vector2(fromLeft(6.5f), fromBottom(11.5f)));
		simpleMovingPlatform("snowCenter", TINY_PLATFORM_SIZE, new Vector2(fromLeft(2.5f), fromBottom(11.5f)), new Vector2(fromLeft(5.5f), fromBottom(11.5f)));
	}

	@Override
	protected void createTimedPlatforms() {

	}

	@Override
	protected void createStaticPlatforms() {
		createSign("signRight", fromLeft(0), fromBottom(0));
		createSign("signLeft", fromLeft(15.03f), fromBottom(2.75f));
		createSign("signLeft", fromLeft(6.5f), fromBottom(6));
		createSign("signRight", fromLeft(0.5f), fromBottom(14));
		createSign("signExit", fromLeft(10.5f), fromBottom(16));
		createSign("window", fromLeft(10), fromBottom(16));
		createSpikes(fromLeft(2));

		staticPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(fromLeft(1.5f), fromBottom(1)));
		staticPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(fromLeft(3.5f), fromBottom(1)));
		staticPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(fromLeft(5.5f), fromBottom(1)));
		staticPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(fromLeft(10.5f), fromBottom(1)));
		staticPlatform("snowCenter", MEDIUM_PLATFORM_SIZE, new Vector2(fromLeft(12.5f), fromBottom(1)));
		staticPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(fromLeft(15), fromBottom(2)));
		staticPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(fromLeft(13), fromBottom(3)));
		staticPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(fromLeft(11), fromBottom(3.5f)));
		staticPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(fromLeft(8), fromBottom(4)));

		staticPlatform("snowMid", SMALL_PLATFORM_SIZE, new Vector2(fromLeft(6.5f), fromBottom(4.3f)), Friction.NONE, -45);
		staticPlatform("snowMid", SMALL_PLATFORM_SIZE, new Vector2(fromLeft(6.5f), fromBottom(6)), Friction.SNOW, 0);
		staticPlatform("snowMid", MEDIUM_PLATFORM_SIZE, new Vector2(fromLeft(5), fromBottom(5.3f)), Friction.SNOW, 0);
		staticPlatform("snowMid", SMALL_PLATFORM_SIZE, new Vector2(fromLeft(2), fromBottom(5.3f)), Friction.SNOW, 0);

		staticPlatform("snowCenter", TINY_PLATFORM_SIZE, new Vector2(fromLeft(1), fromBottom(6.3f)));
		staticPlatform("snowCenter", TINY_PLATFORM_SIZE, new Vector2(fromLeft(1.5f), fromBottom(7.3f)));
		staticPlatform("snowCenter", TINY_PLATFORM_SIZE, new Vector2(fromLeft(2), fromBottom(8.3f)));

		staticPlatform("purpleMid", LARGE_PLATFORM_SIZE, new Vector2(fromLeft(0.5f), fromBottom(14)), Friction.VERY_STICKY, 0);
		staticPlatform("purpleMid", LARGE_PLATFORM_SIZE, new Vector2(fromLeft(2.25f), fromBottom(14)), Friction.VERY_STICKY, 0);
		staticPlatform("purpleMid", LARGE_PLATFORM_SIZE, new Vector2(fromLeft(4), fromBottom(14)), Friction.VERY_STICKY, 0);

		staticPlatform("snowCenter", LARGE_PLATFORM_SIZE, new Vector2(fromLeft(10), fromBottom(16)));
	}

	private void createSpikes(float startX) {
		for (int i = 0; i < 60; i++) {
			final Sprite spike = spriteFactory.createLevelSprite("stoneCaveSpikeBottom", SIGN_SIZE);
			spike.setPosition(startX, fromBottom(0));
			sprites.add(spike);
			startX += 0.2f;
		}
	}

	private void createSign(final String sprite, final float x, final float y) {
		final Sprite signRight = spriteFactory.createLevelSprite(sprite, SIGN_SIZE);
		signRight.setPosition(x, y);
		sprites.add(signRight);
	}

	@Override
	protected void createGroundWalls() {
		super.createGroundWalls(CONTAINER_WIDTH, CONTAINER_HEIGHT);
	}

	@Override
	public Array<Portal> createPortals() {
		final Array<Portal> portals = new Array<>();
		portals.addAll(portalPair(fromLeft(8.4f), fromBottom(0.2f), fromLeft(10.4f), fromBottom(2.2f), MOVING_SAME_DIRECTION));
		portals.addAll(portalPair(fromLeft(13.1f), fromBottom(1.4f), fromLeft(15.3f), fromBottom(2.4f), MOVING_OPPOSITE_HORIZONTAL_DIRECTION));
		portals.addAll(portalPair(fromLeft(6.1f), fromBottom(5.5f), fromLeft(6.1f), fromBottom(6.6f), MOVING_SAME_DIRECTION));
		portals.addAll(portalPair(fromLeft(1), fromBottom(12), fromLeft(0.1f), fromBottom(15), MOVING_DIFFERENT_XY_AXIS_DIRECTION));
		portals.addAll(portalPair(fromLeft(5), fromBottom(14.5f), fromLeft(5), fromBottom(16.5f), MOVING_OPPOSITE_HORIZONTAL_DIRECTION));
		return portals;
	}

	@Override
	public void createPowerUps() {
		ShieldFactory.createShield(world, new Vector2(fromLeft(2), fromBottom(0)), screen);
	}

	@Override
	public void resetLevelLayout() {

	}

	@Override
	public Sprite getBackground() {
		return null;
	}
}
