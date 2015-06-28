package com.vertextrigger.factories.platform;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.vertextrigger.factories.SpriteFactory;
import com.vertextrigger.util.ContactBody;

public class PlatformFactory {
	
	SpriteFactory spriteFactory;
	World world;
	
	public PlatformFactory(World world) {
		this.world = world;
		this.spriteFactory = new SpriteFactory();
	}
	
	public Sprite createPlatform(String name, PlatformSize params) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(0,0);
		Body platform = world.createBody(bodyDef);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(params.getPhysicalWidth(), params.getPhysicalHeight());
		platform.createFixture(shape, 0).setUserData("platform");
		shape.dispose();

		Sprite sprite = spriteFactory.createPlatform(
				name, params.getSpriteWidth(), params.getSpriteHeight()
		);

		platform.setUserData(ContactBody.STATIC_PLATFORM);
		return sprite;
	}
}
