package com.vertextrigger.factories;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.vertextrigger.util.ContactBody;
import com.vertextrigger.util.GameObjectSize;

public class PlatformFactory {
	SpriteFactory spriteFactory;
	World world;
	
	public PlatformFactory(World world) {
		this.world = world;
		this.spriteFactory = new SpriteFactory();
	}
	
	public Sprite createPlatform(String name, GameObjectSize size) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(0,0);
		Body platform = world.createBody(bodyDef);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(size.getPhysicalWidth(), size.getPhysicalHeight());
		platform.createFixture(shape, 0).setUserData(ContactBody.GROUND);
		shape.dispose();

		Sprite sprite = spriteFactory.createPlatform(
				name, size.getSpriteWidth(), size.getSpriteHeight()
		);
		
		sprite.setPosition(platform.getPosition().x - sprite.getWidth() / 2, platform.getPosition().y - sprite.getHeight() / 2);

		platform.setUserData(ContactBody.GROUND);
		return sprite;
	}
}
