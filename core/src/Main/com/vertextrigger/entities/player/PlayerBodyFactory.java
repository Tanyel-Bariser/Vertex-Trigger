package com.vertextrigger.entities.player;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.vertextrigger.util.ContactBody;

final class PlayerBodyFactory {
	static final float DENSITY = 3f;
	private static BodyDef bodyDefinition;
	private static Body body;
	private static PolygonShape shape;

	static Body getPlayerBody(World world, Vector2 initialPosition) {
		buildPlayerBodyDefinition(initialPosition);
		buildPlayerBody(world);
		buildPlayerShape();
		buildPlayerFixture();
		return body;
	}
	
	private static void buildPlayerBodyDefinition(Vector2 initialPosition) {
		bodyDefinition = new BodyDef();
		bodyDefinition.type = BodyType.DynamicBody;
		bodyDefinition.position.set(initialPosition);
	}
	
	private static void buildPlayerBody(World world) {
		body = world.createBody(bodyDefinition);
	}
	
	private static void buildPlayerShape() {
		shape = new PolygonShape();
		float playerWidth = 0.5f;
		float playerHeight = 1.5f;
		shape.setAsBox(playerWidth, playerHeight);
	}
	
	private static void buildPlayerFixture() {
		Fixture fixture = body.createFixture(shape, DENSITY);
		shape.dispose();
		fixture.setUserData(ContactBody.PLAYER);
	}
}