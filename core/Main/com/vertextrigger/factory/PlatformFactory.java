package com.vertextrigger.factory;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.vertextrigger.factory.bodyfactory.PlatformBodyFactory;
import com.vertextrigger.inanimate.StaticPlatform;
import com.vertextrigger.util.GameObjectSize;

public class PlatformFactory {
	SpriteFactory spriteFactory;
	World world;
	PlatformBodyFactory bodyFactory;

	public PlatformFactory(final World world) {
		this.world = world;
		spriteFactory = new SpriteFactory();
		bodyFactory = new PlatformBodyFactory();
	}

	public StaticPlatform createPlatform(final String name, final GameObjectSize size, final Vector2 position) {
		final Body body = bodyFactory.createPlatformBody(world, position, size);
		final Sprite sprite = new SpriteFactory().createLevelSprite(name, size);

		final StaticPlatform platform = new StaticPlatform(sprite, body);
		platform.setSpritePosition();
		return platform;
	}
}
