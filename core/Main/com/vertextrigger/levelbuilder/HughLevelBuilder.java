package com.vertextrigger.levelbuilder;

import static com.badlogic.gdx.math.MathUtils.degreesToRadians;
import static com.vertextrigger.inanimate.portal.PortalTeleportation.*;
import static com.vertextrigger.util.GameObjectSize.*;

import java.util.Collections;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.assets.AudioManager;
import com.vertextrigger.entities.*;
import com.vertextrigger.entities.callback.*;
import com.vertextrigger.entities.callback.Runnable;
import com.vertextrigger.entities.enemy.*;
import com.vertextrigger.entities.mortalplatform.FadingPlatform;
import com.vertextrigger.entities.movingplatform.MovingPlatform;
import com.vertextrigger.factory.*;
import com.vertextrigger.factory.bodyfactory.PlatformBodyFactory.Friction;
import com.vertextrigger.factory.entityfactory.*;
import com.vertextrigger.inanimate.portal.Portal;
import com.vertextrigger.screen.AbstractGameScreen;

public class HughLevelBuilder extends AbstractLevelBuilder {

	private static final int CONTAINER_WIDTH = 8;
	private static final int CONTAINER_HEIGHT = 9;

	private final AbstractGameScreen screen;
	private final PlatformFactory platformFactory;
	private static boolean lowerDifficultyEnabled;

	public HughLevelBuilder(final World world, final AbstractGameScreen screen) {
		super(world, screen, CONTAINER_WIDTH, CONTAINER_HEIGHT);
		this.screen = screen;
		platformFactory = new PlatformFactory(world);
		AudioManager.playLevelOneMusic();

		// start
		player = PlayerFactory.createPlayer(world, new Vector2(fromLeft(1), fromBottom(0)), screen, magnetFlowField);

		// before poker #2
		// player = PlayerFactory.createPlayer(world, new Vector2(fromLeft(8), fromBottom(5.6f)), screen, magnetFlowField);

		// pre bossfight
		// player = PlayerFactory.createPlayer(world, new Vector2(fromLeft(3f), fromBottom(13f)), screen, magnetFlowField);

		// end
		// player = PlayerFactory.createPlayer(world, new Vector2(fromLeft(9.5f), fromBottom(16.1f)), screen, magnetFlowField);

		player.addCallbacks(spawnBoss(), lowerDifficulty());
	}

	@Override
	public MagnetFlowField createMagnetFlowField() {
		return null;
	}

	@Override
	protected Array<Enemy> createEnemies(final Steerable<Vector2> target) {
		final Array<Enemy> enemies = new Array<>();
		final Poker poker1 = EnemyFactory.createPokerEnemy(world, new Vector2(fromLeft(14.95f), fromBottom(2)));
		enemies.add(poker1);

		if (!lowerDifficultyEnabled) {
			createSpikes(fromLeft(2));
		}

		final Poker poker2 = EnemyFactory.createPokerEnemy(world, new Vector2(fromLeft(6.5f), fromBottom(6)));
		poker2.getBody().setFixedRotation(false);
		poker2.getBody().setTransform(new Vector2(fromLeft(6.5f), fromBottom(5.6f)), 180 * degreesToRadians);
		poker2.getBody().setFixedRotation(true);
		poker2.getBody().setGravityScale(0);
		enemies.add(poker2);
		return enemies;
	}

	private RepeatedDeathCallback lowerDifficulty() {
		return new RepeatedDeathCallback(new Runnable() {
			@Override
			public void run() {
				System.out.println("CALLBACK");
				lowerDifficultyEnabled = true;
			}
		}, 5);
	}

	private PositionCallback spawnBoss() {
		return new PositionCallback(new Runnable() {
			@Override
			public void run() {
				final Bee bee = EnemyFactory.createBeeEnemy(world, new Vector2(fromLeft(2), fromBottom(15)), player.getSteerable(), screen,
						magnetFlowField);
				mortals.add(bee);
				bee.addCallbacks(spawnVictoryPlatform(bee));
				AudioManager.playLevelTwoMusic();
			}
		}, new Vector2(fromLeft(0.5f), fromBottom(14)), player.getPosition());
	}

	private DeathCallback spawnVictoryPlatform(final Mortal enemy) {
		return new DeathCallback(new Runnable() {
			@Override
			public void run() {
				AudioManager.playPickUpSound();
				AudioManager.playLevelOneMusic();
				simpleMovingPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(fromLeft(5), fromBottom(15.5f)), new Vector2(fromLeft(10),
						fromBottom(15.5f)));
			}

		}, enemy);
	}

	@Override
	protected Array<Entity> createDangerousBalls() {
		return EMPTY_ARRAY;
	}

	@Override
	protected Array<MovingPlatform> createMovingPlatforms() {
		final Array<MovingPlatform> movingPlatforms = new Array<MovingPlatform>();
		movingPlatforms.add(simpleMovingPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(fromLeft(2.5f), fromBottom(9.3f)), new Vector2(
				fromLeft(5.5f), fromBottom(9.3f))));
		movingPlatforms.add(simpleMovingPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(fromLeft(6.5f), fromBottom(9.3f)), new Vector2(
				fromLeft(6.5f), fromBottom(11.5f))));
		movingPlatforms.add(simpleMovingPlatform("snowCenter", TINY_PLATFORM_SIZE, new Vector2(fromLeft(2.5f), fromBottom(11.5f)), new Vector2(
				fromLeft(5.5f), fromBottom(11.5f))));
		return movingPlatforms;
	}

	@Override
	protected Array<FadingPlatform> createTimedPlatforms() {
		final Array<FadingPlatform> fadingPlatforms = new Array<FadingPlatform>();
		fadingPlatforms.add(fadingPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(fromLeft(3.5f), fromBottom(1))));
		return fadingPlatforms;
	}

	@Override
	protected void createStaticPlatforms() {
		createSign("signRight", fromLeft(0), fromBottom(0));
		createSign("signLeft", fromLeft(15.03f), fromBottom(2.75f));
		createSign("signLeft", fromLeft(6.5f), fromBottom(6));
		createSign("signRight", fromLeft(0.5f), fromBottom(14));
		createSign("signExit", fromLeft(10.5f), fromBottom(16));
		createSign("window", fromLeft(10), fromBottom(16));

		staticPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(fromLeft(0.5f), fromBottom(2)));
		staticPlatform("snowCenter", SMALL_PLATFORM_SIZE, new Vector2(fromLeft(1.5f), fromBottom(1)));
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
		for (int i = 0; i < 71; i++) {
			final Spike spike = spike("stoneCaveSpikeBottom", SPIKE_SIZE, new Vector2(startX, fromBottom(0)));
			sprites.add(spike.getSprite());
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
		portals.addAll(portalPair(fromLeft(8.4f), fromBottom(0.42f), fromLeft(10.4f), fromBottom(2.2f), MOVING_SAME_DIRECTION));
		portals.addAll(portalPair(fromLeft(13.1f), fromBottom(1.4f), fromLeft(15.3f), fromBottom(2.4f), MOVING_OPPOSITE_HORIZONTAL_DIRECTION));
		portals.addAll(portalPair(fromLeft(6.1f), fromBottom(5.5f), fromLeft(6.1f), fromBottom(6.6f), MOVING_SAME_DIRECTION));
		portals.addAll(portalPair(fromLeft(1), fromBottom(12), fromLeft(0.1f), fromBottom(15), MOVING_DIFFERENT_XY_AXIS_DIRECTION));
		portals.addAll(portalPair(fromLeft(5), fromBottom(14.5f), fromLeft(5), fromBottom(16.5f), MOVING_OPPOSITE_HORIZONTAL_DIRECTION));
		return portals;
	}

	@Override
	public void createPowerUps() {
		ShieldFactory.createShield(world, new Vector2(fromLeft(0.5f), fromBottom(2.4f)), screen);
	}

	@Override
	public void resetLevelLayout() {

	}

	@Override
	public Sprite getBackground() {
		return null;
	}
}
