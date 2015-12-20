package com.vertextrigger.factory;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.vertextrigger.factory.animationfactory.AbstractAnimationFactory;
import com.vertextrigger.factory.bodyfactory.PlatformBodyFactory;
import com.vertextrigger.inanimate.StaticPlatform;
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
		Sprite sprite = new SpriteFactory().createLevelSprite(name, size);

		StaticPlatform platform = new StaticPlatform(sprite, body);
		platform.setPosition();
		return platform;
	}
}
