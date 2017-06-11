package com.vertextrigger.factory;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.vertextrigger.entities.enemy.Pit;
import com.vertextrigger.entities.movingplatform.MovingPlatform;
import com.vertextrigger.entities.movingplatform.RectanglePath;
import com.vertextrigger.entities.movingplatform.SimplePath;
import com.vertextrigger.factory.bodyfactory.PlatformBodyFactory;
import com.vertextrigger.factory.bodyfactory.PlatformBodyFactory.Friction;
import com.vertextrigger.inanimate.StaticPlatform;
import com.vertextrigger.util.GameObjectSize;

public class PlatformFactory {
	private final SpriteFactory spriteFactory;
	private final World world;
	private final PlatformBodyFactory bodyFactory;

	public PlatformFactory(final World world) {
		this.world = world;
		spriteFactory = new SpriteFactory();
		bodyFactory = new PlatformBodyFactory();
	}

	public MovingPlatform createSimpleMovingPlatform(final String name, final GameObjectSize size, final Vector2 pathStart, final Vector2 pathEnd) {
		final Body body = bodyFactory.createMovingPlatformBody(world, pathStart, size, Friction.STICKY);
		return new MovingPlatform(body, size, name, new SimplePath(pathStart, pathEnd));
	}

	public MovingPlatform createRectangleMovingPlatform(final String name, final GameObjectSize size, final Vector2 topLeft, final Vector2 bottomRight, final boolean clockwise) {
		final Body body = bodyFactory.createMovingPlatformBody(world, topLeft, size, Friction.STICKY);
		return new MovingPlatform(body, size, name, new RectanglePath(topLeft, bottomRight, clockwise));
	}

	public StaticPlatform createPlatform(final String name, final GameObjectSize size, final Vector2 position) {
		final Body body = bodyFactory.createPlatformBody(world, position, size, Friction.NORMAL);
		final Sprite sprite = new SpriteFactory().createLevelSprite(name, size);

		return getStaticPlatform(body, sprite);
	}

	public StaticPlatform createPlatform(final String name, final GameObjectSize size, final Vector2 position, final Friction friction) {
		final Body body = bodyFactory.createPlatformBody(world, position, size, friction);
		final Sprite sprite = new SpriteFactory().createLevelSprite(name, size);

		return getStaticPlatform(body, sprite);
	}

	private StaticPlatform getStaticPlatform(final Body body, final Sprite sprite) {
		final StaticPlatform platform = new StaticPlatform(sprite, body);
		platform.setSpritePosition();
		return platform;
	}

	public Pit createPit(final String name, final GameObjectSize size, final Vector2 position) {
		final Body body = bodyFactory.createPlatformBody(world, position, size, Friction.NORMAL);
		final Sprite sprite = new SpriteFactory().createLevelSprite(name, size);

		final Pit pit = new Pit(sprite, body);
		pit.setSpritePosition();
		return pit;
	}
}
