package com.vertextrigger.factory;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.vertextrigger.ai.Magnet;
import com.vertextrigger.factory.bodyfactory.MagnetBodyFactory;
import com.vertextrigger.util.*;

import static com.vertextrigger.util.GameObjectSize.*;

public class MagnetFactory {
	private final SpriteFactory spriteFactory;
	private final World world;
	private final MagnetBodyFactory bodyFactory;
	private final int containerWidth;
	private final int containerHeight;

	public MagnetFactory(final World world, final int containerWidth, final int containerHeight) {
		this.world = world;
		this.containerWidth = containerWidth;
		this.containerHeight = containerHeight;
		spriteFactory = new SpriteFactory();
		bodyFactory = new MagnetBodyFactory();
	}

	public Magnet createMagnet(final GameObjectSize size, final Vector2 worldPosition, final int strength) {
		final Body body = bodyFactory.createMagnetBody(world, worldPosition, size);
		final Sprite active = new SpriteFactory().createCoreSprite("magnetActive", MAGNET_SIZE);
		final Sprite inactive = new SpriteFactory().createCoreSprite("magnetInactive", MAGNET_SIZE);
		active.setPosition(worldPosition.x - size.getSpriteWidth() / 2, worldPosition.y - size.getSpriteHeight() / 2);
		inactive.setPosition(worldPosition.x - size.getSpriteWidth() / 2, worldPosition.y - size.getSpriteHeight() / 2);

		final Vector2 flowField2dArrayPosition = PositionConverter.convertPosition(containerWidth, containerHeight, worldPosition);
		return new Magnet(body, active, inactive, strength, flowField2dArrayPosition);
	}
}