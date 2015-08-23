package com.vertextrigger.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.vertextrigger.util.*;

public class BodyFactory {
	
	public static Body getBody(BodyBuilder builder) {
		BodyDef bodyDef = buildBodyDefinition(builder.initialPosition, builder.bodyType);
		Body body = builder.world.createBody(bodyDef);
		buildFixture(builder.contactBody, builder.shape, body, builder.density);
		return body;
	}
	
	private static BodyDef buildBodyDefinition(Vector2 initialPosition, BodyType bodyType) {
		BodyDef bodyDefinition = new BodyDef();
		bodyDefinition.type = bodyType;
		bodyDefinition.fixedRotation = true;
		bodyDefinition.position.set(initialPosition);
		return bodyDefinition;
	}
		
	private static void buildFixture(ContactBody contactBody, Shape shape, Body body, float density) {
		Fixture fixture = body.createFixture(shape, density);
		shape.dispose();
		fixture.setUserData(contactBody);
	}
}