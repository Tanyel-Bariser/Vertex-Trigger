package com.vertextrigger.factories;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.vertextrigger.entities.StaticPlatform;
import com.vertextrigger.factories.bodyfactory.PlatformBodyFactory;
import com.vertextrigger.util.ContactBody;
import com.vertextrigger.util.GameObjectSize;

public class PlatformFactory {
	SpriteFactory spriteFactory;
	World world;
	PlatformBodyFactory bodyFactory;
	
	public PlatformFactory(World world) {
		this.world = world;
		this.spriteFactory = new SpriteFactory();
		this.bodyFactory = new PlatformBodyFactory();
	}
	
	public StaticPlatform createPlatform(String name, GameObjectSize size, Vector2 position) {
		Body body = bodyFactory.createPlatformBody(world, position, size);

		Sprite sprite = spriteFactory.createPlatform(
				name, size.getSpriteWidth(), size.getSpriteHeight()
		);

		StaticPlatform platform = new StaticPlatform(sprite, body);
		platform.setPosition();
		return platform;
	}
}
