package com.vertextrigger.factory;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
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

	private StaticPlatform getStaticPlatform(Body body, Sprite sprite) {
		final StaticPlatform platform = new StaticPlatform(sprite, body);
		platform.setSpritePosition();
		return platform;
	}
}
