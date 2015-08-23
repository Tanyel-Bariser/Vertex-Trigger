package com.vertextrigger.factories;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.vertextrigger.entities.StaticPlatform;
import com.vertextrigger.util.ContactBody;
import com.vertextrigger.util.GameObjectSize;

public class PlatformFactory {
	SpriteFactory spriteFactory;
	World world;
	
	public PlatformFactory(World world) {
		this.world = world;
		this.spriteFactory = new SpriteFactory();
	}
	
	public StaticPlatform createPlatform(String name, GameObjectSize size) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(0,0);
		Body body = world.createBody(bodyDef);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(size.getPhysicalWidth(), size.getPhysicalHeight());
		body.createFixture(shape, 0).setUserData(ContactBody.GROUND);
		shape.dispose();

		Sprite sprite = spriteFactory.createPlatform(
				name, size.getSpriteWidth(), size.getSpriteHeight()
		);
		

		body.setUserData(ContactBody.GROUND);
		return new StaticPlatform(sprite, body);
	}
}
